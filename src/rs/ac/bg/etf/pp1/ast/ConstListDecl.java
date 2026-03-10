// generated with ast extension for cup
// version 0.8
// 17/1/2026 1:5:10


package rs.ac.bg.etf.pp1.ast;

public class ConstListDecl extends ConstList {

    private ConstList ConstList;
    private OneConstDecl OneConstDecl;

    public ConstListDecl (ConstList ConstList, OneConstDecl OneConstDecl) {
        this.ConstList=ConstList;
        if(ConstList!=null) ConstList.setParent(this);
        this.OneConstDecl=OneConstDecl;
        if(OneConstDecl!=null) OneConstDecl.setParent(this);
    }

    public ConstList getConstList() {
        return ConstList;
    }

    public void setConstList(ConstList ConstList) {
        this.ConstList=ConstList;
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
        if(ConstList!=null) ConstList.accept(visitor);
        if(OneConstDecl!=null) OneConstDecl.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ConstList!=null) ConstList.traverseTopDown(visitor);
        if(OneConstDecl!=null) OneConstDecl.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ConstList!=null) ConstList.traverseBottomUp(visitor);
        if(OneConstDecl!=null) OneConstDecl.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ConstListDecl(\n");

        if(ConstList!=null)
            buffer.append(ConstList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(OneConstDecl!=null)
            buffer.append(OneConstDecl.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ConstListDecl]");
        return buffer.toString();
    }
}
