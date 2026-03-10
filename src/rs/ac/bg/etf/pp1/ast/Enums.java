// generated with ast extension for cup
// version 0.8
// 17/1/2026 1:5:10


package rs.ac.bg.etf.pp1.ast;

public class Enums extends EnumList {

    private EnumList EnumList;
    private SingleEnum SingleEnum;

    public Enums (EnumList EnumList, SingleEnum SingleEnum) {
        this.EnumList=EnumList;
        if(EnumList!=null) EnumList.setParent(this);
        this.SingleEnum=SingleEnum;
        if(SingleEnum!=null) SingleEnum.setParent(this);
    }

    public EnumList getEnumList() {
        return EnumList;
    }

    public void setEnumList(EnumList EnumList) {
        this.EnumList=EnumList;
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
        if(EnumList!=null) EnumList.accept(visitor);
        if(SingleEnum!=null) SingleEnum.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(EnumList!=null) EnumList.traverseTopDown(visitor);
        if(SingleEnum!=null) SingleEnum.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(EnumList!=null) EnumList.traverseBottomUp(visitor);
        if(SingleEnum!=null) SingleEnum.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("Enums(\n");

        if(EnumList!=null)
            buffer.append(EnumList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(SingleEnum!=null)
            buffer.append(SingleEnum.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [Enums]");
        return buffer.toString();
    }
}
