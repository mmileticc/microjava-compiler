// generated with ast extension for cup
// version 0.8
// 17/1/2026 1:5:10


package rs.ac.bg.etf.pp1.ast;

public class CaseItem implements SyntaxNode {

    private SyntaxNode parent;
    private int line;
    private Integer value;
    private CaseStart CaseStart;
    private CaseCodeStart CaseCodeStart;
    private StatementList StatementList;

    public CaseItem (Integer value, CaseStart CaseStart, CaseCodeStart CaseCodeStart, StatementList StatementList) {
        this.value=value;
        this.CaseStart=CaseStart;
        if(CaseStart!=null) CaseStart.setParent(this);
        this.CaseCodeStart=CaseCodeStart;
        if(CaseCodeStart!=null) CaseCodeStart.setParent(this);
        this.StatementList=StatementList;
        if(StatementList!=null) StatementList.setParent(this);
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value=value;
    }

    public CaseStart getCaseStart() {
        return CaseStart;
    }

    public void setCaseStart(CaseStart CaseStart) {
        this.CaseStart=CaseStart;
    }

    public CaseCodeStart getCaseCodeStart() {
        return CaseCodeStart;
    }

    public void setCaseCodeStart(CaseCodeStart CaseCodeStart) {
        this.CaseCodeStart=CaseCodeStart;
    }

    public StatementList getStatementList() {
        return StatementList;
    }

    public void setStatementList(StatementList StatementList) {
        this.StatementList=StatementList;
    }

    public SyntaxNode getParent() {
        return parent;
    }

    public void setParent(SyntaxNode parent) {
        this.parent=parent;
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line=line;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(CaseStart!=null) CaseStart.accept(visitor);
        if(CaseCodeStart!=null) CaseCodeStart.accept(visitor);
        if(StatementList!=null) StatementList.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(CaseStart!=null) CaseStart.traverseTopDown(visitor);
        if(CaseCodeStart!=null) CaseCodeStart.traverseTopDown(visitor);
        if(StatementList!=null) StatementList.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(CaseStart!=null) CaseStart.traverseBottomUp(visitor);
        if(CaseCodeStart!=null) CaseCodeStart.traverseBottomUp(visitor);
        if(StatementList!=null) StatementList.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("CaseItem(\n");

        buffer.append(" "+tab+value);
        buffer.append("\n");

        if(CaseStart!=null)
            buffer.append(CaseStart.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(CaseCodeStart!=null)
            buffer.append(CaseCodeStart.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(StatementList!=null)
            buffer.append(StatementList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [CaseItem]");
        return buffer.toString();
    }
}
