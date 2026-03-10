// generated with ast extension for cup
// version 0.8
// 17/1/2026 1:5:10


package rs.ac.bg.etf.pp1.ast;

public class ArrayVar extends OneVarDecl {

    private String arrVarName;

    public ArrayVar (String arrVarName) {
        this.arrVarName=arrVarName;
    }

    public String getArrVarName() {
        return arrVarName;
    }

    public void setArrVarName(String arrVarName) {
        this.arrVarName=arrVarName;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ArrayVar(\n");

        buffer.append(" "+tab+arrVarName);
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ArrayVar]");
        return buffer.toString();
    }
}
