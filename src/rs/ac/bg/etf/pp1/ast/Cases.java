// generated with ast extension for cup
// version 0.8
// 17/1/2026 1:5:10


package rs.ac.bg.etf.pp1.ast;

public class Cases extends CaseList {

    private CaseList CaseList;
    private CaseItem CaseItem;

    public Cases (CaseList CaseList, CaseItem CaseItem) {
        this.CaseList=CaseList;
        if(CaseList!=null) CaseList.setParent(this);
        this.CaseItem=CaseItem;
        if(CaseItem!=null) CaseItem.setParent(this);
    }

    public CaseList getCaseList() {
        return CaseList;
    }

    public void setCaseList(CaseList CaseList) {
        this.CaseList=CaseList;
    }

    public CaseItem getCaseItem() {
        return CaseItem;
    }

    public void setCaseItem(CaseItem CaseItem) {
        this.CaseItem=CaseItem;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(CaseList!=null) CaseList.accept(visitor);
        if(CaseItem!=null) CaseItem.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(CaseList!=null) CaseList.traverseTopDown(visitor);
        if(CaseItem!=null) CaseItem.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(CaseList!=null) CaseList.traverseBottomUp(visitor);
        if(CaseItem!=null) CaseItem.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("Cases(\n");

        if(CaseList!=null)
            buffer.append(CaseList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(CaseItem!=null)
            buffer.append(CaseItem.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [Cases]");
        return buffer.toString();
    }
}
