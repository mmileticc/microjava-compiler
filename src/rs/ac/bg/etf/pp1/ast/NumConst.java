// generated with ast extension for cup
// version 0.8
// 17/1/2026 1:5:10


package rs.ac.bg.etf.pp1.ast;

public class NumConst extends Const {

    private Integer numvalue;

    public NumConst (Integer numvalue) {
        this.numvalue=numvalue;
    }

    public Integer getNumvalue() {
        return numvalue;
    }

    public void setNumvalue(Integer numvalue) {
        this.numvalue=numvalue;
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
        buffer.append("NumConst(\n");

        buffer.append(" "+tab+numvalue);
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [NumConst]");
        return buffer.toString();
    }
}
