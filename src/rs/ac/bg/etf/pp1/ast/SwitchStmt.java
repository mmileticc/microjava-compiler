// generated with ast extension for cup
// version 0.8
// 17/1/2026 1:5:10


package rs.ac.bg.etf.pp1.ast;

public class SwitchStmt extends Statement {

    private SwitchHeader SwitchHeader;
    private CaseList CaseList;

    public SwitchStmt (SwitchHeader SwitchHeader, CaseList CaseList) {
        this.SwitchHeader=SwitchHeader;
        if(SwitchHeader!=null) SwitchHeader.setParent(this);
        this.CaseList=CaseList;
        if(CaseList!=null) CaseList.setParent(this);
    }

    public SwitchHeader getSwitchHeader() {
        return SwitchHeader;
    }

    public void setSwitchHeader(SwitchHeader SwitchHeader) {
        this.SwitchHeader=SwitchHeader;
    }

    public CaseList getCaseList() {
        return CaseList;
    }

    public void setCaseList(CaseList CaseList) {
        this.CaseList=CaseList;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(SwitchHeader!=null) SwitchHeader.accept(visitor);
        if(CaseList!=null) CaseList.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(SwitchHeader!=null) SwitchHeader.traverseTopDown(visitor);
        if(CaseList!=null) CaseList.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(SwitchHeader!=null) SwitchHeader.traverseBottomUp(visitor);
        if(CaseList!=null) CaseList.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("SwitchStmt(\n");

        if(SwitchHeader!=null)
            buffer.append(SwitchHeader.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(CaseList!=null)
            buffer.append(CaseList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [SwitchStmt]");
        return buffer.toString();
    }
}
