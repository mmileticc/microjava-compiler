// generated with ast extension for cup
// version 0.8
// 17/1/2026 1:5:10


package rs.ac.bg.etf.pp1.ast;

public class OneEnum extends EnumList {

    private SingleEnum SingleEnum;

    public OneEnum (SingleEnum SingleEnum) {
        this.SingleEnum=SingleEnum;
        if(SingleEnum!=null) SingleEnum.setParent(this);
    }

    public SingleEnum getSingleEnum() {
        return SingleEnum;
    }

    public void setSingleEnum(SingleEnum SingleEnum) {
        this.SingleEnum=SingleEnum;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(SingleEnum!=null) SingleEnum.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(SingleEnum!=null) SingleEnum.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(SingleEnum!=null) SingleEnum.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("OneEnum(\n");

        if(SingleEnum!=null)
            buffer.append(SingleEnum.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [OneEnum]");
        return buffer.toString();
    }
}
