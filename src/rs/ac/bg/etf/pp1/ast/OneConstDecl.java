// generated with ast extension for cup
// version 0.8
// 17/1/2026 1:5:10


package rs.ac.bg.etf.pp1.ast;

public class OneConstDecl implements SyntaxNode {

    private SyntaxNode parent;
    private int line;
    public rs.etf.pp1.symboltable.concepts.Obj obj = null;

    private String constsName;
    private Const Const;

    public OneConstDecl (String constsName, Const Const) {
        this.constsName=constsName;
        this.Const=Const;
        if(Const!=null) Const.setParent(this);
    }

    public String getConstsName() {
        return constsName;
    }

    public void setConstsName(String constsName) {
        this.constsName=constsName;
    }

    public Const getConst() {
        return Const;
    }

    public void setConst(Const Const) {
        this.Const=Const;
    }

    public SyntaxNode getParent() {
        return parent;
    }

    public void setParent(SyntaxNode parent) {
        this.parent=parent;
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line=line;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Const!=null) Const.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Const!=null) Const.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Const!=null) Const.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("OneConstDecl(\n");

        buffer.append(" "+tab+constsName);
        buffer.append("\n");

        if(Const!=null)
            buffer.append(Const.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [OneConstDecl]");
        return buffer.toString();
    }
}
