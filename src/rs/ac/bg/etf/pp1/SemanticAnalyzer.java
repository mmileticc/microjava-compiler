package rs.ac.bg.etf.pp1;

import org.apache.log4j.Logger;

import rs.ac.bg.etf.pp1.ast.*;
import rs.etf.pp1.symboltable.*;
import rs.etf.pp1.symboltable.concepts.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack; 


public class SemanticAnalyzer extends VisitorAdaptor {

	int printCallCount = 0;
	int varDeclCount = 0;
	Obj currentMethod = null;
	boolean returnFound = false;
	boolean errorDetected = false;
	int nVars;
	Struct currentType = null;
	Obj currentEnum = null;
	int currentEnumValue = 0;
	int formalParamCnt = 0;
	
	// stek designatora dok se gnezde sa . ili [] i cuvanje na vrhu najsvezijeg
	Stack<Obj> designatorStack = new Stack<>(); 
	
	// stek listi vrednosti konstanti u case-ovima jer se mogu ugnjezdavati
	Stack<Set<Integer>> switchConstantsStack = new Stack<>();
	
	// brojac ugnezdenih petlji (za break/continue) 
	int loopDepth = 0;

	// mapa za cuvanje tipova stvarnih argumenata (ActPars)
	// kljuc je čvor sintaksnog stabla, vrednost je lista tipova
	Map<SyntaxNode, List<Struct>> actualParamsMap = new HashMap<>();
	
	
	Logger log = Logger.getLogger(getClass());

	public void report_error(String message, SyntaxNode info) {
		errorDetected = true;
		StringBuilder msg = new StringBuilder(message);
		int line = (info == null) ? 0: info.getLine();
		if (line != 0)
			msg.append (" na liniji ").append(line);
		log.error(msg.toString());
	}

	public void report_info(String message, SyntaxNode info) {
		StringBuilder msg = new StringBuilder(message); 
		int line = (info == null) ? 0: info.getLine();
		if (line != 0)
			msg.append (" na liniji ").append(line);
		log.info(msg.toString());
	}
	
	
	private boolean isInt(Struct s) {
	    return s == Tab.intType || s.getKind() == Struct.Enum;
	}
//	------------------------- Visitori -------------------------------------------

    public void visit(ProgName progName){
    	progName.obj = Tab.insert(Obj.Prog, progName.getProgramName(), Tab.noType);
    	Tab.openScope();
    }
    
    public void visit(Program program){
    	nVars = Tab.currentScope.getnVars();
        Obj mainMethod = Tab.find("main");
       
        if (mainMethod == Tab.noObj || mainMethod.getKind() != Obj.Meth || mainMethod.getType() != Tab.noType) {
            report_error("GRESKA MAIN METODE: Program mora imati void main() metodu!", null);
        } else if (mainMethod.getLevel() != 0) {
            report_error("GRESKA MAIN METODE: main metoda ne sme imati parametre!", null);
        }
        
        
    	Tab.chainLocalSymbols(program.getProgName().obj);
    	Tab.closeScope();
    }
    
    public void visit(Type type){
    	Obj typeNode = Tab.find(type.getTypeName());
    	if(typeNode == Tab.noObj){
    		report_error("GRESKA PRI POZIVANJU TIPA: Nije pronadjen tip " + type.getTypeName() + " u tabeli simbola! ", type);
    		type.struct = Tab.noType;
    	}else{
    		if(Obj.Type == typeNode.getKind()){
    			currentType = typeNode.getType();
    			type.struct = typeNode.getType(); 
    		}else{
    			report_error("GRESKA PRI POZIVANJU TIPA: Ime \"" + type.getTypeName() + "\" ne predstavlja tip!", type);
    			type.struct = Tab.noType;
    		}
    	}
    }
    
    
//    CONSTANTS START ----------------------------------------------------------------------------------
    public void visit(ConstDecl constDeclaration) {
    	currentType = null;
    }
    
    public void visit(OneConstDecl oneConst) {
    	String name = oneConst.getConstsName();
    	if(Tab.currentScope.findSymbol(name) == null) {
    		//sme da se definise ovo ime
    		if(oneConst.getConst().struct == currentType) {
    			Obj con = Tab.insert(Obj.Con, name, currentType);
                  
                SyntaxNode c = oneConst.getConst();
                int val = 0;
                if (c instanceof NumConst) val = ((NumConst)c).getNumvalue();
                else if (c instanceof CharConst) val = ((CharConst)c).getCharValue();
                else if (c instanceof BoolConst) val = ((BoolConst)c).getBoolValue() ? 1 : 0;
                
                con.setAdr(val);
                oneConst.obj = con;
                
                oneConst.obj = con;
                report_info("Definisanje konstante: \"" + name + "\" sa vrednošću " + val, oneConst);
    		}
			else {
				oneConst.obj = Tab.noObj;
				report_error("GRESKA PRI DEFINISANJU KONSTANTE: losi tipovi definisanja konstante", oneConst);
			}
    	}else {
    		//ne sme da se definise ime vec postoji 
    		report_error("GRESKA PRI DEFINISANJU KONSTANTE, u opsegu vec postoji simbol sa imenom " + name, oneConst);

    	}
    }
    public void visit(NumConst numConst) {
		numConst.struct = Tab.intType;
	}
	
	public void visit(CharConst charConst) {
		charConst.struct = Tab.charType;
	}
	
	public void visit(BoolConst boolConst) {
		boolConst.struct = MyTab.boolType; 
	}
//  CONSTANTS END ----------------------------------------------------------------------------------

//  VAR START  -------------------------------------------------------------------------------------
    
     
	public void visit(SimpleVariabl simpleVar) {
		String name = simpleVar.getVarName();
		if(Tab.currentScope.findSymbol(name) == null) {
    		//sme da se definise ovo ime
    		simpleVar.obj = Tab.insert(Obj.Var, name, currentType);	
    		report_info("Deklarisana promenljiva: " + name, simpleVar);
    	}else {
    		//ne sme da se definise ime vec postoji 
    		report_error("GRESKA PRI DEFINISANJU PROMENLJIVE, u opsegu vec postoji simbol sa imenom " + name, simpleVar);
    	}
	}
	
	public void visit(ArrayVar arrVar) {
		String name = arrVar.getArrVarName();
		if(Tab.currentScope.findSymbol(name) == null) {
    		//sme da se definise ovo ime
			arrVar.obj = Tab.insert(Obj.Var, name, new Struct(Struct.Array, currentType));			
			report_info("Deklarisan niz: " + name, arrVar);
		}else {
    		//ne sme da se definise ime vec postoji 
    		report_error("GRESKA PRI DEFINISANJU NIZOVSKE PROMENLJIVE, u opsegu vec postoji simbol sa imenom " + name, arrVar);
    	}
	}
	
	public void visit(VarType varType) {
		currentType = varType.getType().struct;
	}
	
	public void visit(VarDecl varDecl) {
    	currentType = null;
	}
    
//  VAR END  ---------------------------------------------------------------------------------------   

//  ENUMS START ------------------------------------------------------------------------------------

	public void visit(EnumName enumName) {
	    String name = enumName.getEnumName();
	    // ubacujem ime enuma kao novi tip u globalni opseg ako ne postoji vec enum sa istim imenom
	    if(Tab.currentScope.findSymbol(name) == null) {
	    	
	    	currentEnum = Tab.insert(Obj.Type, enumName.getEnumName(), MyTab.enumType);
		    enumName.obj = currentEnum;
		    Tab.openScope();
		    currentEnumValue = 0;
		    report_info("POCETAK DEKARACIJE ENUMA: " + enumName.getEnumName(), enumName);
	    
	    }else {
	    	//ne sme da se definise ime vec postoji 
    		report_error("GRESKA PRI DEFINISANJU ENUMA: U opsegu vec postoji simbol sa imenom " + name, enumName);
	    }
	}
	
	public void visit(NoValEnum noValEnum) {
		if(currentEnum != null) {
		    Obj enumConstant = Tab.insert(Obj.Con, noValEnum.getName(), Tab.intType);
		    enumConstant.setAdr(currentEnumValue++);
		    noValEnum.obj = enumConstant;
		    
		    report_info("  Enum konstanta: " + noValEnum.getName() + " = " + (currentEnumValue-1), noValEnum);
		}
	}

	public void visit(EnumWithVal enumWithVal) {
		if(currentEnum != null) {
		    currentEnumValue = enumWithVal.getValue();
		    Obj enumConstant = Tab.insert(Obj.Con, enumWithVal.getName(), Tab.intType);
		    enumConstant.setAdr(currentEnumValue++);
		    enumWithVal.obj = enumConstant;
		    
		    report_info("  Enum konstanta: " + enumWithVal.getName() + " = " + (currentEnumValue-1), enumWithVal);
		}
	}
	
	public void visit(EnumDecl enumDecl) {
		if(currentEnum != null) {
			Tab.chainLocalSymbols(currentEnum);
		    Tab.closeScope();
		    currentEnum = null;
		    currentEnumValue = 0;
		    report_info("KRAJ DEKARACIJE ENUMA: " + enumDecl.getEnumName().getEnumName(), enumDecl);
		}	
	}
	
//	ENUMS END   ------------------------------------------------------------------------------------

// --- DEKLARACIJA METODA START ---------------------------------------------------------------------------------

    public void visit(VoidRet voidRet) {
        voidRet.struct = Tab.noType;
    }

    public void visit(TypeRet typeRet) {
        typeRet.struct = typeRet.getType().struct;
    }

    public void visit(MethodTypeName methodTypeName) {
        if (Tab.currentScope.findSymbol(methodTypeName.getMethName()) != null) {
            report_error("GRESKA PRI DEFINISANJU METODE: U opsegu vec postoji simbol sa imenom: " + methodTypeName.getMethName(), methodTypeName);
            methodTypeName.obj = Tab.noObj;
        } else {
            currentMethod = Tab.insert(Obj.Meth, methodTypeName.getMethName(), methodTypeName.getRetType().struct);
            methodTypeName.obj = currentMethod;
        }
        formalParamCnt = 0;
        Tab.openScope();
        report_info("OBRADJUJE SE FUNKCIJA: " + methodTypeName.getMethName(), methodTypeName);
    }

    public void visit(FormPar formPar) {
        String name = formPar.getParamName();
        Struct type = formPar.getType().struct;

        // provera za niz (ugnjezdeni MaybeBrackets)
        if (formPar.getMaybeBrackets() instanceof ExistingBrackets) {
            type = new Struct(Struct.Array, type);
        }

        if (Tab.currentScope.findSymbol(name) == null) {
        	formPar.obj = Tab.insert(Obj.Var, name, type);
        } else {
            report_error("GRESKA PRI DEKLARISANJU PARAMETARA FUNKCIJE: Parametar " + name + " je vec deklarisan!", formPar);
            formPar.obj = Tab.noObj;
        }
        formalParamCnt++;
    }

    public void visit(MethodDecl methodDecl) {
     
        if (currentMethod != null) {
            currentMethod.setLevel(formalParamCnt);
            
    
            Tab.chainLocalSymbols(currentMethod);
            Tab.closeScope();

            report_info("Zavrsena se funkcija: " + methodDecl.getMethodTypeName().getMethName(), methodDecl);

            // provera return iskaza (ako metoda nije void)
            if (!returnFound && currentMethod.getType() != Tab.noType) {
                report_error("GRESKA NEPOSTOJECEG RETURNA U FUNKCIJI: Funkcija " + currentMethod.getName() + " mora imati return iskaz!", methodDecl);
            }
        }

       
        currentMethod = null;
        returnFound = false;
    }

// --- DEKLARACIJA METODA END -----------------------------------------------------------------------------------
	
// --- STATEMENT START ------------------------------------------------------------------------------------------
    public void visit(ReturnStmtWithExpr returnStmt) {
        returnFound = true;
        if (currentMethod == null) {
            report_error("GRESKA: return ne sme biti izvan metode!", returnStmt);
            return;
        }
        Struct currMethType = currentMethod.getType();
        if (!currMethType.compatibleWith(returnStmt.getExpr().struct)) {
            report_error("GRESKA: Tip povratne vrednosti se ne slaze sa deklaracijom metode " + currentMethod.getName(), returnStmt);
        }
    }

    public void visit(ReturnStmt returnStmt) {
        returnFound = true;
        if (currentMethod != null && currentMethod.getType() != Tab.noType) {
            report_error("GRESKA: Metoda " + currentMethod.getName() + " mora da vrati vrednost!", returnStmt);
        }
    }

    public void visit(DesignatorStmtAssign assign) {
        Obj dest = assign.getDesignator().obj;
        Struct src = assign.getExpr().struct;
        
        if (dest != Tab.noObj && src != Tab.noType) {
            // provera kompatibilnosti uz uvazavanje enuma jer bez ovoga mi nije radilo int x; enum b{a}; x == b.a jer nije konstantovalo enum kao int za kompatibilnost
            boolean compatible = src.assignableTo(dest.getType()) || (isInt(src) && isInt(dest.getType()));
            
            if (!compatible) {
                report_error("GRESKA: Nekompatibilni tipovi pri dodeli!", assign);
            } else {
//            	report_info("Izvrsena provera tipa za dodelu u \"" + dest.getName() + "\"", assign);
            }
            
            // provera da li je leva strana promenljiva (ne sme biti metoda ili konstanta)
            if (dest.getKind() != Obj.Var && dest.getKind() != Obj.Elem && dest.getKind() != Obj.Fld) {
                report_error("GRESKA: Leva strana dodele mora biti promenljiva, polje ili element niza!", assign);
            }
        }
    }
    
    public void visit(ReadStmt readStmt) {
        Obj obj = readStmt.getDesignator().obj;
        if (obj.getKind() != Obj.Var && obj.getKind() != Obj.Elem && obj.getKind() != Obj.Fld) {
            report_error("GRESKA: Argument metode read() mora biti promenljiva, element niza ili polje objekta!", readStmt);
        }
        
        Struct t = obj.getType();
        if (t != Tab.intType && t != Tab.charType && t != MyTab.boolType && t.getKind() != Struct.Enum) {
            report_error("GRESKA: Argument metode read() mora biti int, char ili bool (ili enum)!", readStmt);
        }
    }

    public void visit(PrintStmt printStmt) {
        // printStmt.getExpr() vraca izraz koji se ispisuje
        Struct t = printStmt.getExpr().struct;
        if (t != Tab.intType && t != Tab.charType && t != MyTab.boolType && t.getKind() != Struct.Enum) {
            report_error("GRESKA: Argument metode print() mora biti int, char ili bool (ili enum)!", printStmt);
        }
    }

    public void visit(DesignatorStmtFunctionCall stmt) {
        Obj meth = stmt.getDesignator().obj;
        checkMethodCall(meth, stmt, stmt.getActParsOption());
        stmt.obj = meth;
    }
    
    
    // povećavam dubinu cim uđemo u for
    public void visit(ForLoopStart forLoopStart) {
        loopDepth++;
    }

    // smanjujem dubinu kada izadjem iz celog for-a
    public void visit(ForStmt forStmt) {
        loopDepth--;
    }

    public void visit(BreakStmt breakStmt) {
        if (loopDepth == 0) {
            report_error("GRESKA: 'break' se može koristiti samo unutar petlje!", breakStmt);
        }
    }

    public void visit(ContinueStmt continueStmt) {
        if (loopDepth == 0) {
            report_error("GRESKA: 'continue' se može koristiti samo unutar petlje!", continueStmt);
        }
    }
    
    // --- IF START ------------------------------------------------------------------------------
//    public void visit(IfStmt ifStmt) {
//        report_info("Detektovana 'if' naredba", ifStmt); 
//        if (ifStmt.getCondition().struct != MyTab.boolType) {
//            report_error("GRESKA: Uslov u 'if' naredbi mora biti tipa bool!", ifStmt);
//        }
//    }
//
//    public void visit(IfElseStmt ifElseStmt) {
//    	  report_info("Detektovana 'if else' naredba", ifElseStmt); 
//        if (ifElseStmt.getCondition().struct != MyTab.boolType) {
//            report_error("GRESKA: Uslov u 'if' naredbi mora biti tipa bool!", ifElseStmt);
//        }
//    }
    public void visit(IfClause ifClause) {
        Struct conditionType = ifClause.getCondition().struct;
        if (!conditionType.assignableTo(Tab.find("bool").getType())) {
            report_error("Greska: Uslov u IF-u mora biti logickog tipa (bool)!", ifClause);
        }
    }
    // --- IF END   ------------------------------------------------------------------------------
    
    
    
    
// --- STATEMENT END --------------------------------------------------------------------------------------------

    
// --- DESIGNATOR START --------------------------------------------------------------------------------------------
 
    public void visit(DesignatorName designatorName) {
        Obj obj = Tab.find(designatorName.getName());
        if (obj == Tab.noObj) {
            report_error("Greska: Ime " + designatorName.getName() + " nije deklarisano!", designatorName);
        }
        
        // ubacujem na stek trenutni simbol
        designatorStack.push(obj);
        designatorName.obj = obj;
    }
    
    public void visit(DotLengthDesignator dotLength) {
        // uzimam sa vrha steka, proveravam, modifikujem i vraćam na vrh novi designator
        if (!designatorStack.isEmpty()) {
            Obj current = designatorStack.pop();
            if (current.getType().getKind() == Struct.Array) {
                designatorStack.push(new Obj(Obj.Con, "len", Tab.intType));
            	report_info("Dohvatanje duzine elementa: \"" + current.getName() + "\"", dotLength);

            } else {
                report_error("Greska: .length se moze koristiti samo uz nizove!", dotLength);
                designatorStack.push(Tab.noObj);
            }
        }else {
        	report_error("Greska: .length se mora koristiti uz designatora pr njega!", dotLength);
            designatorStack.push(Tab.noObj);
        }
    }

    // [ index ]
    public void visit(ArrayDesignator arrayDes) {
    
    	if (!isInt(arrayDes.getExpr().struct)) {
            report_error("GRESKA: Indeks niza mora biti int!", arrayDes);
        }

        // skidam niz sa steka da bih ga pretvoro u element
        if (!designatorStack.isEmpty()) {
            Obj current = designatorStack.pop();
            if (current.getType().getKind() != Struct.Array) {
                report_error("GRESKA: Pokusaj indeksiranja tipa koji nije niz!", arrayDes);
                designatorStack.push(Tab.noObj);
            } else {
            	report_info("Pristup elementu niza " + current.getName(), arrayDes);
                designatorStack.push(new Obj(Obj.Elem, "elem", current.getType().getElemType()));
            }
        }
    }

    public void visit(Designator designator) {
       
        if (!designatorStack.isEmpty()) {
            designator.obj = designatorStack.pop();
        } else {
            designator.obj = Tab.noObj;
        }
    }
    
    public void visit(DotIdentDesignator dotIdent) {
        if (!designatorStack.isEmpty()) {
            Obj current = designatorStack.pop();
            Struct type = current.getType();
            Obj member = null;

            // provera da li je u pitanju Enum tip (npr Broj.NULA)
            if (current.getKind() == Obj.Type && type.getKind() == Struct.Enum) {
                // clanovi enuma su u locals tabeli Obj cvora koji predstavlja tip
            	for (Obj o : current.getLocalSymbols()) {
                    if (o.getName().equals(dotIdent.getEnumsName())) {
                        member = o;
                        break;
                    }
                }
            } else {
                // za klase/objekte se trazi u members tabeli Struct-a
                member = type.getMembersTable().searchKey(dotIdent.getEnumsName());
            }

            if (member == null) {
                report_error("Greska: Simbol " + dotIdent.getEnumsName() + " nije clan!", dotIdent);
                designatorStack.push(Tab.noObj);
            } else {
                report_info("Nadjeno: " + member.getName(), dotIdent);
                designatorStack.push(member);
            }
        }
    }
    
    public void visit(FactorDesignator factor) {
        Obj obj = factor.getDesignator().obj;
        if (obj != null && obj != Tab.noObj) {
            factor.struct = obj.getType();
        } else {
            report_error("GRESKA: Nevalidan designator!", factor);
            factor.struct = Tab.noType;
        }
    }
    
    public void visit(DesignatorStmtInc inc) {
        Obj obj = inc.getDesignator().obj;
        if (obj.getKind() != Obj.Var && obj.getKind() != Obj.Elem && obj.getKind() != Obj.Fld) {
            report_error("GRESKA: ++ se moze primeniti samo na promenljivu, element niza ili polje!", inc);
        }
        if (!isInt(obj.getType())) {
            report_error("GRESKA: ++ se moze primeniti samo na int tip!", inc);
        }
    }

    public void visit(DesignatorStmtDec dec) {
        Obj obj = dec.getDesignator().obj;
        if (obj.getKind() != Obj.Var && obj.getKind() != Obj.Elem && obj.getKind() != Obj.Fld) {
            report_error("GRESKA: -- se moze primeniti samo na promenljivu, element niza ili polje!", dec);
        }
        if (!isInt(obj.getType())) {
            report_error("GRESKA: -- se moze primeniti samo na int tip!", dec);
        }
    }
// --- DESIGNATOR END --------------------------------------------------------------------------------------------

    
// --- EXPR START --------------------------------------------------------------------------------------------
    public void visit(FactorNumber factor) {
        factor.struct = Tab.intType;
    }

    public void visit(FactorChar factor) {
        factor.struct = Tab.charType;
    }

    public void visit(FactorBool factor) {
        factor.struct = MyTab.boolType; 
    }

    public void visit(FactorNewSpecific factor) {
        Struct exprType = factor.getExpr().struct;
        if (!isInt(exprType)) {
            report_error("GRESKA: Indeks niza kod 'new' operatora mora biti celobrojnog tipa!", factor);
        }
        factor.struct = new Struct(Struct.Array, factor.getType().struct);
    }
    
    public void visit(FactorNew factor) {
        factor.struct = factor.getType().struct;
    }
    
    public void visit(FactorParens factor) {
        factor.struct = factor.getExpr().struct;
    }
    
    public void visit(TermFactor term) {
        term.struct = term.getFactor().struct;
    }

    // poziv funkcije unutar izraza (npr. x = f(a) + 2)
    public void visit(FactorDesignatorParens factor) {
        Obj meth = factor.getDesignator().obj;
        checkMethodCall(meth, factor, factor.getActParsOption());
        // rezultat izraza je povratni tip funkcije
        factor.struct = meth.getType();
    }
    
    public void visit(TermMulop term) {
        Struct t = term.getTerm().struct;
        Struct f = term.getFactor().struct;
        
        if (isInt(t) && isInt(f)) {
            term.struct = Tab.intType;
        } else {
            report_error("GRESKA: Operandi u mnozenju/deljenju moraju biti int ili enum!", term);
            term.struct = Tab.noType;
        }
    }

    public void visit(BasicTerm termList) {
        termList.struct = termList.getTerm().struct;
    }

    public void visit(AddopTerms termList) {
        Struct tl = termList.getTermList().struct;
        Struct t = termList.getTerm().struct;
        
        if (isInt(tl) && isInt(t)) {
            termList.struct = Tab.intType;
        } else {
            report_error("GRESKA: Operandi moraju biti int ili enum!", termList);
            termList.struct = Tab.noType;
        }
    }
    
    public void visit(ExprBasic exprBasic) {
        exprBasic.struct = exprBasic.getTermList().struct;
        
        if (exprBasic.getMinusOption() instanceof MinusPrefix && !isInt(exprBasic.struct)) {
            report_error("GRESKA: Negacija se moze vrsiti samo nad int ili enum tipom!", exprBasic);
        }
    }

    public void visit(BasicCondFact condFact) {
        condFact.struct = condFact.getExprBasic().struct;
        
    }

    public void visit(RelopCondFact condFact) {
        Struct e1 = condFact.getExprBasic().struct;
        Struct e2 = condFact.getExprBasic1().struct;
        
        boolean compatible = e1.compatibleWith(e2) || (isInt(e1) && isInt(e2));
        
        if (!compatible) {
            report_error("GRESKA: Tipovi u relacionom izrazu nisu kompatibilni!", condFact);
        }
        
     // Dodatna provera za nizove
        if (e1.getKind() == Struct.Array || e2.getKind() == Struct.Array) {
            Relop op = condFact.getRelop();
            if (!(op instanceof EqualRelop) && !(op instanceof NotEqualRelop)) {
                report_error("GRESKA: Uz nizove se mogu koristiti samo == i != operatori!", condFact);
            }
        }
        
        condFact.struct = MyTab.boolType;
    }
    
    public void visit(ConditionOpt condOpt) {
        if (condOpt.getCondition().struct != MyTab.boolType) {
            report_error("GRESKA: Uslov u 'for' petlji mora biti tipa bool!", condOpt);
        }
    }

    public void visit(BasicCondTerm condTerm) {
        condTerm.struct = condTerm.getCondFact().struct;
    }

    public void visit(BasicCondition condition) {
        condition.struct = condition.getCondTerm().struct;
    }
    
    public void visit(ExprCondition expr) {
        expr.struct = expr.getCondition().struct;
    }

    public void visit(ExprTernar expr) {
        Struct condType = expr.getCondition().struct;
        Struct t1 = expr.getExpr().struct;
        Struct t2 = expr.getExpr1().struct;

        if (condType != MyTab.boolType) {
            report_error("GRESKA: Uslov u ternarnom operatoru mora biti bool!", expr);
        }

        if (!t1.compatibleWith(t2)) {
            report_error("GRESKA: Tipovi u granama ternarnog operatora moraju biti kompatibilni!", expr);
            expr.struct = Tab.noType;
        } else {
            expr.struct = t1; 
        }
    }
    
    public void visit(AndCondTerm condTerm) {
        if (condTerm.getCondTerm().struct != MyTab.boolType || condTerm.getCondFact().struct != MyTab.boolType) {
            report_error("GRESKA: Operandi && operatora moraju biti tipa bool!", condTerm);
        }
        condTerm.struct = MyTab.boolType; 
    }

    public void visit(OrCondition condition) {
        if (condition.getCondition().struct != MyTab.boolType || condition.getCondTerm().struct != MyTab.boolType) {
            report_error("GRESKA: Operandi || operatora moraju biti tipa bool!", condition);
        }
        condition.struct = MyTab.boolType;
    }
    
// --- EXPR END --------------------------------------------------------------------------------------------

    
// --- FUNCTIONS PARAMS START ----------------------------------------------------------------------------------    
    
 // prvi argument u listi (dno rekurzije)
    public void visit(ActParsExpr actPars) {
        List<Struct> types = new ArrayList<>();
        types.add(actPars.getExpr().struct);
        actualParamsMap.put(actPars, types);
    }

    // svaki sledeci argument (ActPars -> ActPars, Expr)
    public void visit(ActParsMultiple actPars) {
        // yzimam listu od deteta (prethodnih argumenata)
        List<Struct> types = actualParamsMap.get(actPars.getActPars());
        if (types == null) types = new ArrayList<>(); // Safety check
        
        types.add(actPars.getExpr().struct);
        actualParamsMap.put(actPars, types);
    }

    public void visit(ExistingActPars actParsOpt) {
        actualParamsMap.put(actParsOpt, actualParamsMap.get(actParsOpt.getActPars()));
    }

    public void visit(NoActPars actParsOpt) {
        actualParamsMap.put(actParsOpt, new ArrayList<>());
    }
    
 // pomocna funkcija za proveru argumenata
    private void checkMethodCall(Obj meth, SyntaxNode callNode, ActParsOption actParsOpt) {
    	
    	if (meth == Tab.noObj || meth.getKind() != Obj.Meth) {
            report_error("GRESKA: Ime " + meth.getName() + " nije funkcija!", callNode);
            return;
        }
        
        report_info("Prepoznat poziv funkcije: " + meth.getName(), callNode);

        // dohvatam stvarne argumente iz mape
        List<Struct> actualArgs = actualParamsMap.get(actParsOpt);
        if (actualArgs == null) actualArgs = new ArrayList<>();

        // dohvatamo formalne argumente iz tabele simbola
        // u Obj.Meth, level cuva broj formalnih parametara
        int formalParamCnt = meth.getLevel();
        
        if (actualArgs.size() != formalParamCnt) {
            report_error("GRESKA: Funkcija " + meth.getName() + " ocekuje " + formalParamCnt + 
                         " argumenata, a prosledjeno je " + actualArgs.size(), callNode);
            return;
        }

        // provera tipova
        // parametri su prvi 'level' simbola u locals tabeli
        Collection<Obj> locals = meth.getLocalSymbols();
        int counter = 0;
        
        for (Obj formalPar : locals) {
            if (counter >= formalParamCnt) break; // ostalo su lokalne promenljive, ne parametri
            
            Struct actualType = actualArgs.get(counter);
            Struct formalType = formalPar.getType();
            
            if (!actualType.assignableTo(formalType) && !(isInt(actualType) && isInt(formalType))) {
                 report_error("GRESKA: Tip argumenta na poziciji " + (counter+1) + 
                              " nije kompatibilan sa parametrom " + formalPar.getName(), callNode);
            }
            counter++;
        }
    } 
      
// --- FUNCTIONS PARAMS END ----------------------------------------------------------------------------------    
    
    
   
    
// --- SWITCH START --------------------------------------------------------------------------
    
//  pocetak switch-a
    public void visit(SwitchHeader switchHeader) {
        Struct exprType = switchHeader.getExpr().struct;
        
        if (!isInt(exprType)) {
            report_error("GRESKA: Izraz u switch-u mora biti int ili enum!", switchHeader);
        }

        // povećavamo dubinu petlje jer je 'break' dozvoljen unutar switch-a
        loopDepth++;
        
        // ubacujem novi prazan set za konstante ovog switch-a na stek
        switchConstantsStack.push(new HashSet<>());
        
        report_info("Početak switch naredbe", switchHeader);
    }

    //  pojedinacni case: proveravam da li je konstanta vec koriscna u njegovom opsegu vazenja ugnjezdenosti
    public void visit(CaseItem caseItem) {
        int value = caseItem.getValue();
        
        if (switchConstantsStack.isEmpty()) {
            // ovo ne bi smelo da se desi ako je gramatika dobra
            return;
        }

        Set<Integer> currentSet = switchConstantsStack.peek();
        
        if (currentSet.contains(value)) {
            report_error("GRESKA: Duplirana konstanta '" + value + "' u case grani!", caseItem);
        } else {
            currentSet.add(value);
            report_info("Prepoznata case naredba za vrednost: " + caseItem.getValue(), caseItem);

        }
    }

    // kraj switch-a: cistim stek i smanjujem dubinu
    public void visit(SwitchStmt switchStmt) {
        if (!switchConstantsStack.isEmpty()) {
            switchConstantsStack.pop();
        }
        
        // vracamo dubinu petlje (izlazim iz opsega gde je 'break' dozvoljen)
        loopDepth--;
        
        report_info("Kraj switch naredbe", switchStmt);
    }
    
    
//  SWITCH END --------------------------------------------------------------------------

  

    
    
    public boolean passed() {
    	return !errorDetected;
    }
}


