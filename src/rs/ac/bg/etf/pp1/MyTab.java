package rs.ac.bg.etf.pp1;


import org.apache.log4j.Logger;

import rs.etf.pp1.symboltable.*;
import rs.etf.pp1.symboltable.concepts.*; 
import rs.etf.pp1.symboltable.structure.*;
import rs.etf.pp1.symboltable.visitors.SymbolTableVisitor;

public class MyTab extends Tab {
	public static final Struct boolType = new Struct(Struct.Bool), 
			enumType = new Struct(Struct.Enum);
	public static Obj tempHelp;
	
	static Logger log = Logger.getLogger(Tab.class);

	public static void myInit() {
		init();
		currentScope.addToLocals(new Obj(Obj.Type, "bool", boolType));
	}
	
	
	
	public static void dump() {
		log.info("=====================SYMBOL TABLE DUMP=========================");
//		System.out.println("=====================SYMBOL TABLE DUMP=========================");
	
		MyDumpSymbolTableVisitor stv = new MyDumpSymbolTableVisitor();
		for (Scope s = currentScope; s != null; s = s.getOuter()) {
			s.accept(stv);
		}
		
		log.info(stv.getOutput());
		
	}
	
	
}