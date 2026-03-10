package rs.ac.bg.etf.pp1;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import rs.ac.bg.etf.pp1.ast.*;
import rs.etf.pp1.mj.runtime.Code;
import rs.etf.pp1.symboltable.Tab;
import rs.etf.pp1.symboltable.concepts.*;

public class CodeGenerator extends VisitorAdaptor {

    private int mainPc;

    public int getMainPc() {
        return mainPc;
    }

    ////////////////// METODE ZA METODE START ////////////////////////////////////////
    public void visit(MethodTypeName methodTypeName) {
        if ("main".equalsIgnoreCase(methodTypeName.getMethName())) {
            mainPc = Code.pc;
        }
        methodTypeName.obj.setAdr(Code.pc);

        int argCount = methodTypeName.obj.getLevel();
        int varCount = methodTypeName.obj.getLocalSymbols().size();

        Code.put(Code.enter);
        Code.put(argCount);
        Code.put(varCount);
    }

    public void visit(MethodDecl methodDecl) {
    	if (methodDecl.getMethodTypeName().getRetType().struct != Tab.noType) {  
            Code.put(Code.const_n + 0);  //zastita od zaboravljenog return-a u nekim granama ne void metoda da ne bi doslo do uderflowa
        }
        Code.put(Code.exit);
        Code.put(Code.return_);
    }
    ////////////////// METODE ZA METODE END ////////////////////////////////////////

    // --- DESIGNATORI I LOAD/STORE ---
    public void visit(DesignatorName designatorName) {
        Obj obj = designatorName.obj;
        // ako je niz, stavljamo adresu na stek (priprema za adr[index])
        if (obj.getKind() == Obj.Var && obj.getType().getKind() == Struct.Array) {
            Code.load(obj);
        }
    }

    public void visit(FactorDesignator factorDesignator) {
        Obj obj = factorDesignator.getDesignator().obj;
        // specijalni slucaj za .length iz semantike
        if ("len".equals(obj.getName())) return;
        Code.load(obj);
    }

    public void visit(DesignatorStmtAssign assignment) {
        Code.store(assignment.getDesignator().obj);
    }

    // --- INKREMENT / DEKREMENT ---
    public void visit(DesignatorStmtInc inc) {
        Obj obj = inc.getDesignator().obj;
        if (obj.getKind() == Obj.Elem) Code.put(Code.dup2); // [adr, index, adr, index]
        Code.load(obj);                                    // [adr, index, val]
        Code.put(Code.const_1);
        Code.put(Code.add);
        Code.store(obj);
    }

    public void visit(DesignatorStmtDec dec) {
        Obj obj = dec.getDesignator().obj;
        if (obj.getKind() == Obj.Elem) Code.put(Code.dup2);
        Code.load(obj);
        Code.put(Code.const_1);
        Code.put(Code.sub);
        Code.store(obj);
    }

    // --- ARITMETIKA ---
    public void visit(AddopTerms terms) {
        if (terms.getAddop() instanceof MinusAddop) Code.put(Code.sub);
        else Code.put(Code.add);
    }

    public void visit(TermMulop term) {
        if (term.getMulop() instanceof MulMulop) Code.put(Code.mul);
        else if (term.getMulop() instanceof DivMulop) Code.put(Code.div);
        else Code.put(Code.rem);
    }


    // --- KONSTANTE ---
    public void visit(FactorNumber factor) { 
    	Code.loadConst(factor.getValue()); 
    	}
    public void visit(FactorChar factor)   { 
    	Code.loadConst(factor.getValue()); 
    	}
    public void visit(FactorBool factor)   { 
    	Code.loadConst(factor.getValue() ? 1 : 0); 
    	}

    // --- NIZOVI ---
    public void visit(FactorNewSpecific factorNew) {
        Code.put(Code.newarray);
        // 0 za char, 1 za int/ostalo po specifikaciji
        Code.put(factorNew.getType().struct == Tab.charType ? 0 : 1);
    }

    public void visit(DotLengthDesignator dotLength) {
        Code.put(Code.arraylength);
    }

    /////////////////////  I/O OPERACIJE START ////////////////////////////////////////////////////
    public void visit(PrintStmt printStmt) {
        Struct s = printStmt.getExpr().struct;
        
        if (s == Tab.charType) { 
        	Code.loadConst(1);
        	Code.put(Code.bprint);
        }
        else {
        	Code.loadConst(3); 
        	Code.put(Code.print);
        }
        
    }

    public void visit(ReadStmt read) {
        Obj obj = read.getDesignator().obj;
        if (obj.getType() == Tab.charType) Code.put(Code.bread);
        else Code.put(Code.read);
        Code.store(obj);
    }
    /////////////////////  I/O OPERACIJE END //////////////////////////////////////////////////////

    private boolean negateNextTerm = false;

	 // ako postoji minus, postaviti zastavicu
	public void visit(MinusPrefix minusPrefix) {
	    negateNextTerm = true;
	}
	
	 // ako ne postoji minus, obavezno ponistiti zastavicu (bitno zbog prethodnih izraza)
	public void visit(NoMinus noMinus) {
	    negateNextTerm = false;
	}
	
	public void visit(BasicTerm basicTerm) {
	    if (negateNextTerm) {
	        Code.put(Code.neg);
	        negateNextTerm = false; // resetuj za sledeci izraz
	    }
	}
	
////////////////////////// TERNARNI OPERATOR START //////////////////////////////////////
	private Stack<Integer> ternaryJumpsToElse = new Stack<>();
	private Stack<Integer> ternaryJumpsToEnd = new Stack<>();

	public void visit(TernaryStart ternaryStart) {
	    // na steku je rezultat Condition-a (0 ili 1)
	    Code.put(Code.const_n + 0); // stavi 0 na stek za poredjenje
	    Code.putFalseJump(Code.ne, 0); // ako Condition == 0, skoci na ELSE deo
	    ternaryJumpsToElse.push(Code.pc - 2); // pamti adresu za fixup
	}

	public void visit(TernaryElse ternaryElse) {
	    // zavrsena je TRUE grana. mora da se preskoci ELSE grana
	    Code.putJump(0);
	    ternaryJumpsToEnd.push(Code.pc - 2);
	    
	    // krpi skok iz TernaryStart da pokazuje ovde (na pocetak ELSE grane)
	    Code.fixup(ternaryJumpsToElse.pop());
	}

	public void visit(ExprTernar exprTernar) {
	    // zavrsena je i ELSE grana. krpi skok iz TernaryElse da pokazuje ovde (kraj)
	    Code.fixup(ternaryJumpsToEnd.pop());
	}
//////////////////////////TERNARNI OPERATOR END //////////////////////////////////////

	
	// --- RELACIONI OPERATORI (RelopCondFact) ---
	public void visit(RelopCondFact condFact) {
	    // na expr steku su vec: [expr1, expr2]
	    
	    int opCode = Code.eq; // Default
	    Relop relop = condFact.getRelop();
	    
	    // mapiranje AST cvorova na MJ operacije skoka
	    if (relop instanceof EqualRelop) opCode = Code.eq;
	    else if (relop instanceof NotEqualRelop) opCode = Code.ne;
	    else if (relop instanceof GreaterRelop) opCode = Code.gt;
	    else if (relop instanceof GreatEqRelop) opCode = Code.ge;
	    else if (relop instanceof LessRelop) opCode = Code.lt;
	    else if (relop instanceof LessEqRelop) opCode = Code.le;

	    // ako je uslov (opCode) ispunjen, skoci na "stavi 1", inace "stavi 0"
	    // putFalseJump koristi inverznu logiku, pa cu koristiti običan uslovni skok
	    
	    Code.putFalseJump(opCode, Code.pc + 7); // Ako uslov NIJE ispunjen, skoni na PC+7 (gde je const_0)
	    Code.put(Code.const_1);                 // ispunjen: stavi 1
	    Code.putJump(Code.pc + 4);              // preskoci "stavi 0"
	    Code.put(Code.const_n);                 // nije ispunjen: stavi 0
	    
	    // sada je na vrhu steka 1 ili 0, sto TernaryStart moze da koristi
	}
	
	//////////////////////////////// AND I OR CONDS START ////////////////////////////////////

	private Stack<Integer> andShortCircuitJumps = new Stack<>();

	public void visit(AndStart andStart) {
	    // na steku je: [L]
	    Code.put(Code.dup);             // Stek: [L, L]
	    Code.loadConst(0);              // Stek: [L, L, 0]
	    Code.putFalseJump(Code.ne, 0);  // Poredi L != 0. Ako je L=0, skaci. Stek: [L] (ostaje nula)
	    
	    andShortCircuitJumps.push(Code.pc - 2);
	    
	    // Ako nismo skocili, znaci L je bio 1. 
	    // Ta jedinica nam vise ne treba jer rezultat sada zavisi od desne strane.
	    Code.put(Code.pop);             // Stek: []
	}

	public void visit(AndCondTerm andCond) {
	    // ako smo ovde, ili smo skocili (na steku je 0) 
	    // ili smo prosli (na steku je rezultat desne strane R).
	    Code.fixup(andShortCircuitJumps.pop());
	}
	
	private Stack<Integer> orShortCircuitJumps = new Stack<>();

	public void visit(OrStart orStart) {
	    // Na steku je: [L]
	    Code.put(Code.dup);             // stek: [L, L]
	    Code.loadConst(1);              // stek: [L, L, 1]
	    Code.putFalseJump(Code.ne, 0);  // poredi L != 1. ako je L=1, skaci. stek: [L] (ostaje jedinica)
	    
	    orShortCircuitJumps.push(Code.pc - 2);
	    
	    // ako nismo skocili, L je bio 0. skidamo ga.
	    Code.put(Code.pop);             // stek: []
	}

	public void visit(OrCondition orCond) {
	    Code.fixup(orShortCircuitJumps.pop());
	}
	//////////////////////////////// AND I OR CONDS END ////////////////////////////////////

	
	////////////////////////////// IF ELSE START/////////////////////////////////////
	
	private Stack<Integer> ifJumpsToElse = new Stack<>();
	private Stack<Integer> ifJumpsToEnd = new Stack<>();

	// ovde ulazim cim se izracuna uslov u zagradi
	public void visit(IfClause ifClause) {
	    // Na steku je rezultat Condition-a (0 ili 1)
	    Code.put(Code.const_n + 0); 
	    Code.putFalseJump(Code.ne, 0); // ako je uslov 0, bezi u ELSE (ili na kraj ako nema ELSE)
	    ifJumpsToElse.push(Code.pc - 2);
	}

	// ovde ulazimo samo ako postoji ELSE, i to NAKON sto se zavrsi IF blok
	public void visit(ElseClause elseClause) {
	    Code.putJump(0); // Pošto se IF blok završio, preskoci ELSE blok i idi na kraj
	    ifJumpsToEnd.push(Code.pc - 2);
	    
	    // sada moze da se "zakrpi" onaj skok iz IfClause-a da dodje OVDE (na pocetak ELSE-a)
	    Code.fixup(ifJumpsToElse.pop());
	}

	// poziva se na kraju "if (cond) stmt ELSE stmt"
	public void visit(IfElseStmt ifElseStmt) {
	    // ovde krpim onaj Jump koji je preskakao else blok
	    Code.fixup(ifJumpsToEnd.pop());
	}

	// poziva se na kraju "if (cond) stmt" (kad nema ELSE)
	public void visit(IfStmt ifStmt) {
	    // posto nema ELSE-a, onaj lazni skok iz IfClause-a vodi ovde (na kraj)
	    Code.fixup(ifJumpsToElse.pop());
	}
	
	////////////////////////////// IF ELSE END/////////////////////////////////////

	
	////////////////////////////// POZIVI FUNKCIJA START /////////////////////////////////////
	
	public void visit(DesignatorStmtFunctionCall call) {
	    Obj funcObj = call.getDesignator().obj;
	    
	    // izracunavanje relativne adrese (offset)
	    // funcObj.getAdr() je adresa pocetka funkcije
	    // Code.pc je trenutna pozicija
	    int offset = funcObj.getAdr() - Code.pc;
	    
	    Code.put(Code.call);
	    Code.put2(offset);

	    // ciscenje steka: ako funkcija nije void, a pozvana je kao Statement,
	    // niko ne koristi njenu povratnu vrednost -> skida se sa steka! Ziza naglasio
	    if (funcObj.getType() != Tab.noType) {
	        Code.put(Code.pop);
	    }
	}
	
	public void visit(FactorDesignatorParens call) {
	    Obj funcObj = call.getDesignator().obj;
	    
	    String funcName = funcObj.getName();
	 // AKO JE UGRADJENA FUNKCIJA, NE GENERISI CALL
	    if ("ord".equals(funcName) || "chr".equals(funcName)) {
	        // Ne radi nista, vrednost je ve na steku
	        return; 
	    }
	    
	    if ("len".equals(funcName)) {
	        Code.put(Code.arraylength);
	        return;
	    }
	    
	    int offset = funcObj.getAdr() - Code.pc;
	    
	    Code.put(Code.call);
	    Code.put2(offset);
	    
	    // ovde NE radim pop, rezultat ostaje na vrhu steka za ostale operacije
	}
	
	public void visit(ReturnStmtWithExpr returnExpr) {
	    Code.put(Code.exit);
	    Code.put(Code.return_);
	}

	public void visit(ReturnStmt returnNoExpr) {
	    Code.put(Code.exit);
	    Code.put(Code.return_);
	}
	
	
	////////////////////////////// POZIVI FUNKCIJA END /////////////////////////////////////

	
	////////////////////////////// FOR START /////////////////////////////////////

	
	private Stack<Integer> forCondStack = new Stack<>();
	private Stack<Integer> forIncStack = new Stack<>();
	private Stack<Integer> forToBodyStack = new Stack<>();
	private Stack<Integer> forEndStack = new Stack<>();

	// za break i continue mi trebaju liste adresa koje treba zakrpiti
	private Stack<java.util.List<Integer>> breakStack = new Stack<>();

	// pocetak provere uslova
	public void visit(ForCondMark forCondMark) {
	    forCondStack.push(Code.pc);
	    breakStack.push(new java.util.ArrayList<>()); // nova lista break-ova za ovu petlju
	}

	// kraj uslova, pre inkrementa
	public void visit(ForIncMark forIncMark) {
	    // Da li postoji uslov?
	    ForStmt parent = (ForStmt) forIncMark.getParent();
	    if (parent.getConditionOptional() instanceof ConditionOpt) {
	        Code.put(Code.const_n + 0);
	        Code.putFalseJump(Code.ne, 0);
	        forEndStack.push(Code.pc - 2);
	    } else {
	        forEndStack.push(-1); // marker da nema uslova (beskonacna petlja)
	    }

	    Code.putJump(0); // preskoci inkrement, idi prvo u telo
	    forToBodyStack.push(Code.pc - 2);

	    forIncStack.push(Code.pc); // ovde pocinje kod za inkrement (cilj za continue)
	}

	// pocetak tela petlje
	public void visit(ForBodyMark forBodyMark) {
	    // na kraju tela se uvek skace na proveru uslova
	    Code.putJump(forCondStack.peek());
	    
	    // krpi skok koji je preskakao inkrement da dodje OVDE
	    Code.fixup(forToBodyStack.pop());
	}

	// kraj cele petlje (posle tela)
	public void visit(ForStmt forStmt) {
	    // skoci na inkrement deo
	    Code.putJump(forIncStack.pop());

	    // krpi skok iz uslova (ako je postojao) da dodje ovde na kraj
	    Integer endJump = forEndStack.pop();
	    if (endJump != -1) Code.fixup(endJump);

	    // krpi sve break-ove koji su se desili u ovoj petlji
	    for (Integer breakAddr : breakStack.pop()) {
	        Code.fixup(breakAddr);
	    }
	    
	    forCondStack.pop();
	}
	
	
	
	public void visit(ContinueStmt continueStmt) {
	    // Continue uvek skace na pocetak INKREMENT dela petlje
	    Code.putJump(forIncStack.peek());
	}
	////////////////////////////// FOR END /////////////////////////////////////

	
	////////////////////////////// SWITCH START /////////////////////////////////////

	private Stack<Integer> nextCheckJump = new Stack<>();
	private Stack<List<Integer>> switchBreaks = new Stack<>();
	private Stack<Integer> skipCheckForFallThrough = new Stack<>();

	// --- SWITCH HEADER ---
	public void visit(SwitchHeader switchHeader) {
	    switchBreaks.push(new ArrayList<>());
	    nextCheckJump.push(-1); 
	    skipCheckForFallThrough.push(-1);
	}

	// --- CASE START (Ovde se vrsi poredjenje) ---
	public void visit(CaseStart caseStart) {
	    // ako nismo prvi case, moramo zakrpiti prethodni neuspesan skok da dodje ovde
	    if (nextCheckJump.peek() != -1) {
	        Code.fixup(nextCheckJump.pop());
	    }

	    // priprema za poredjenje: 
	    // na steku je [Expr]. Treba mi [Expr, Expr, Constant] za poredjenje.
	    Code.put(Code.dup); 
	    CaseItem parent = (CaseItem) caseStart.getParent();
	    Code.loadConst(parent.getValue());

	    //tacka 3: ako su JEDNAKI, skacem na izvrsavanje koda (CaseCodeStart)
	    Code.putFalseJump(Code.ne, Code.pc + 7); 
	    int jumpToBody = Code.pc - 2;

	    // ako NISU jednaki, skacem na sledecu proveru (sledeci Case)
	    Code.putJump(0);
	    nextCheckJump.push(Code.pc - 2);

	    // krpim onaj uslovni skok (iz tacke 3) da udje u telo koda
	    Code.fixup(jumpToBody);
	}

	// --- CASE CODE START (Mesto gde fall-through "slece") ---
	public void visit(CaseCodeStart caseCodeStart) {
	    // ovde zakrpi skok koji preskace provere (ako je prethodni case radio fall-through)
	    if (skipCheckForFallThrough.peek() != -1) {
	        Code.fixup(skipCheckForFallThrough.pop());
	    }
	}

	// --- CASE KRAJ (Simulacija fall-through-a) ---
	// moram dodati metodu koja se poziva na kraju StatementList-a u CaseItem-u 
	// da bi preskocila provere sledeceg case-a.
	public void visit(CaseItem caseItem) {
	    // na kraju svakog case-a (osim ako nije break), generisem skok 
	    // koji preskace provere sledeceg case-a i ide direktno u njegovo telo
	    Code.putJump(0);
	    skipCheckForFallThrough.push(Code.pc - 2);
	}

	// --- BREAK STATEMENT ---
	public void visit(BreakStmt breakStmt) {
	    if (!switchBreaks.isEmpty()) {
	        // PRE skoka, moram skinuti onaj jedan preostali [Expr] sa steka
	        // jer break prekida switch i taj podatak mi vise ne treba.
	        Code.put(Code.pop); 
	        Code.putJump(0);
	        switchBreaks.peek().add(Code.pc - 2);
	    } else {
	        // Standardni break za petlje
	        Code.putJump(0);
	        breakStack.peek().add(Code.pc - 2);
	    }
	}

	// --- SWITCH STATEMENT (Kraj) ---
	public void visit(SwitchStmt switchStmt) {
	    // ako je poslednji case promasen, on skace ovde
	    if (nextCheckJump.peek() != -1) {
	        Code.fixup(nextCheckJump.pop());
	    }

	    // skidam finalni [Expr] koji je ostao na steku
	    Code.put(Code.pop);

	    // krpim sve break-ove da skacu POSLE ovog pop-a
	    for (int adr : switchBreaks.pop()) {
	        Code.fixup(adr);
	    }

	    // cistimo fall-through marker
	    if (skipCheckForFallThrough.peek() != -1) {
	        // ako je poslednji case imao fall-through skok (koji vodi nikuda), zakrpi ga ovde
	        Code.fixup(skipCheckForFallThrough.pop());
	    } else {
	        skipCheckForFallThrough.pop();
	    }
	}
	////////////////////////////// SWITCH END   /////////////////////////////////////

	
}