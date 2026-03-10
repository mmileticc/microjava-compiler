// generated with ast extension for cup
// version 0.8
// 17/1/2026 1:5:10


package rs.ac.bg.etf.pp1.ast;

public class FormPar implements SyntaxNode {

    private SyntaxNode parent;
    private int line;
    public rs.etf.pp1.symboltable.concepts.Obj obj = null;

    private Type Type;
    private String paramName;
    private MaybeBrackets MaybeBrackets;

    public FormPar (Type Type, String paramName, MaybeBrackets MaybeBrackets) {
        this.Type=Type;
        if(Type!=null) Type.setParent(this);
        this.paramName=paramName;
        this.MaybeBrackets=MaybeBrackets;
        if(MaybeBrackets!=null) MaybeBrackets.setParent(this);
    }

    public Type getType() {
        return Type;
    }

    public void setType(Type Type) {
        this.Type=Type;
    }

    public String getParamName() {
        return paramName;
    }

    public void setParamName(String paramName) {
        this.paramName=paramName;
    }

    public MaybeBrackets getMaybeBrackets() {
        return MaybeBrackets;
    }

    public void setMaybeBrackets(MaybeBrackets MaybeBrackets) {
        this.MaybeBrackets=MaybeBrackets;
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
        if(Type!=null) Type.accept(visitor);
        if(MaybeBrackets!=null) MaybeBrackets.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Type!=null) Type.traverseTopDown(visitor);
        if(MaybeBrackets!=null) MaybeBrackets.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Type!=null) Type.traverseBottomUp(visitor);
        if(MaybeBrackets!=null) MaybeBrackets.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("FormPar(\n");

        if(Type!=null)
            buffer.append(Type.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(" "+tab+paramName);
        buffer.append("\n");

        if(MaybeBrackets!=null)
            buffer.append(MaybeBrackets.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [FormPar]");
        return buffer.toString();
    }
}
