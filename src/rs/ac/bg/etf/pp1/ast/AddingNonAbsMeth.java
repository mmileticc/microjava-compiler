// generated with ast extension for cup
// version 0.8
// 17/1/2026 1:5:10


package rs.ac.bg.etf.pp1.ast;

public class AddingNonAbsMeth extends AbsAndNoAbsMethodsList {

    private AbsAndNoAbsMethodsList AbsAndNoAbsMethodsList;
    private MethodDecl MethodDecl;

    public AddingNonAbsMeth (AbsAndNoAbsMethodsList AbsAndNoAbsMethodsList, MethodDecl MethodDecl) {
        this.AbsAndNoAbsMethodsList=AbsAndNoAbsMethodsList;
        if(AbsAndNoAbsMethodsList!=null) AbsAndNoAbsMethodsList.setParent(this);
        this.MethodDecl=MethodDecl;
        if(MethodDecl!=null) MethodDecl.setParent(this);
    }

    public AbsAndNoAbsMethodsList getAbsAndNoAbsMethodsList() {
        return AbsAndNoAbsMethodsList;
    }

    public void setAbsAndNoAbsMethodsList(AbsAndNoAbsMethodsList AbsAndNoAbsMethodsList) {
        this.AbsAndNoAbsMethodsList=AbsAndNoAbsMethodsList;
    }

    public MethodDecl getMethodDecl() {
        return MethodDecl;
    }

    public void setMethodDecl(MethodDecl MethodDecl) {
        this.MethodDecl=MethodDecl;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(AbsAndNoAbsMethodsList!=null) AbsAndNoAbsMethodsList.accept(visitor);
        if(MethodDecl!=null) MethodDecl.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(AbsAndNoAbsMethodsList!=null) AbsAndNoAbsMethodsList.traverseTopDown(visitor);
        if(MethodDecl!=null) MethodDecl.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(AbsAndNoAbsMethodsList!=null) AbsAndNoAbsMethodsList.traverseBottomUp(visitor);
        if(MethodDecl!=null) MethodDecl.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("AddingNonAbsMeth(\n");

        if(AbsAndNoAbsMethodsList!=null)
            buffer.append(AbsAndNoAbsMethodsList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(MethodDecl!=null)
            buffer.append(MethodDecl.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [AddingNonAbsMeth]");
        return buffer.toString();
    }
}
