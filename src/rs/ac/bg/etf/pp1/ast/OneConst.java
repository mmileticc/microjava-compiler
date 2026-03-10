// generated with ast extension for cup
// version 0.8
// 17/1/2026 1:5:10


package rs.ac.bg.etf.pp1.ast;

public class OneConst extends ConstList {

    private OneConstDecl OneConstDecl;

    public OneConst (OneConstDecl OneConstDecl) {
        this.OneConstDecl=OneConstDecl;
        if(OneConstDecl!=null) OneConstDecl.setParent(this);
    }

    public OneConstDecl getOneConstDecl() {
        return OneConstDecl;
    }

    public void setOneConstDecl(OneConstDecl OneConstDecl) {
        this.OneConstDecl=OneConstDecl;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(OneConstDecl!=null) OneConstDecl.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(OneConstDecl!=null) OneConstDecl.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(OneConstDecl!=null) OneConstDecl.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("OneConst(\n");

        if(OneConstDecl!=null)
            buffer.append(OneConstDecl.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [OneConst]");
        return buffer.toString();
    }
}
