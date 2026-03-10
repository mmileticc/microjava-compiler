// generated with ast extension for cup
// version 0.8
// 17/1/2026 1:5:10


package rs.ac.bg.etf.pp1.ast;

public class AddingAbstractMeth extends AbsAndNoAbsMethodsList {

    private AbsAndNoAbsMethodsList AbsAndNoAbsMethodsList;
    private AbstractMethodDecl AbstractMethodDecl;

    public AddingAbstractMeth (AbsAndNoAbsMethodsList AbsAndNoAbsMethodsList, AbstractMethodDecl AbstractMethodDecl) {
        this.AbsAndNoAbsMethodsList=AbsAndNoAbsMethodsList;
        if(AbsAndNoAbsMethodsList!=null) AbsAndNoAbsMethodsList.setParent(this);
        this.AbstractMethodDecl=AbstractMethodDecl;
        if(AbstractMethodDecl!=null) AbstractMethodDecl.setParent(this);
    }

    public AbsAndNoAbsMethodsList getAbsAndNoAbsMethodsList() {
        return AbsAndNoAbsMethodsList;
    }

    public void setAbsAndNoAbsMethodsList(AbsAndNoAbsMethodsList AbsAndNoAbsMethodsList) {
        this.AbsAndNoAbsMethodsList=AbsAndNoAbsMethodsList;
    }

    public AbstractMethodDecl getAbstractMethodDecl() {
        return AbstractMethodDecl;
    }

    public void setAbstractMethodDecl(AbstractMethodDecl AbstractMethodDecl) {
        this.AbstractMethodDecl=AbstractMethodDecl;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(AbsAndNoAbsMethodsList!=null) AbsAndNoAbsMethodsList.accept(visitor);
        if(AbstractMethodDecl!=null) AbstractMethodDecl.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(AbsAndNoAbsMethodsList!=null) AbsAndNoAbsMethodsList.traverseTopDown(visitor);
        if(AbstractMethodDecl!=null) AbstractMethodDecl.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(AbsAndNoAbsMethodsList!=null) AbsAndNoAbsMethodsList.traverseBottomUp(visitor);
        if(AbstractMethodDecl!=null) AbstractMethodDecl.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("AddingAbstractMeth(\n");

        if(AbsAndNoAbsMethodsList!=null)
            buffer.append(AbsAndNoAbsMethodsList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(AbstractMethodDecl!=null)
            buffer.append(AbstractMethodDecl.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [AddingAbstractMeth]");
        return buffer.toString();
    }
}
