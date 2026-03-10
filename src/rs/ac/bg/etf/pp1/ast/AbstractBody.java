// generated with ast extension for cup
// version 0.8
// 17/1/2026 1:5:10


package rs.ac.bg.etf.pp1.ast;

public class AbstractBody extends MaybeAbstractBody {

    private AbsAndNoAbsMethodsList AbsAndNoAbsMethodsList;

    public AbstractBody (AbsAndNoAbsMethodsList AbsAndNoAbsMethodsList) {
        this.AbsAndNoAbsMethodsList=AbsAndNoAbsMethodsList;
        if(AbsAndNoAbsMethodsList!=null) AbsAndNoAbsMethodsList.setParent(this);
    }

    public AbsAndNoAbsMethodsList getAbsAndNoAbsMethodsList() {
        return AbsAndNoAbsMethodsList;
    }

    public void setAbsAndNoAbsMethodsList(AbsAndNoAbsMethodsList AbsAndNoAbsMethodsList) {
        this.AbsAndNoAbsMethodsList=AbsAndNoAbsMethodsList;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(AbsAndNoAbsMethodsList!=null) AbsAndNoAbsMethodsList.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(AbsAndNoAbsMethodsList!=null) AbsAndNoAbsMethodsList.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(AbsAndNoAbsMethodsList!=null) AbsAndNoAbsMethodsList.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("AbstractBody(\n");

        if(AbsAndNoAbsMethodsList!=null)
            buffer.append(AbsAndNoAbsMethodsList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [AbstractBody]");
        return buffer.toString();
    }
}
