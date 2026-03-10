// generated with ast extension for cup
// version 0.8
// 17/1/2026 1:5:10


package rs.ac.bg.etf.pp1.ast;

public class ForStmt extends Statement {

    private DesignStmtOptional DesignStmtOptional;
    private ForCondMark ForCondMark;
    private ConditionOptional ConditionOptional;
    private ForIncMark ForIncMark;
    private DesignStmtOptional DesignStmtOptional1;
    private ForBodyMark ForBodyMark;
    private Statement Statement;

    public ForStmt (DesignStmtOptional DesignStmtOptional, ForCondMark ForCondMark, ConditionOptional ConditionOptional, ForIncMark ForIncMark, DesignStmtOptional DesignStmtOptional1, ForBodyMark ForBodyMark, Statement Statement) {
        this.DesignStmtOptional=DesignStmtOptional;
        if(DesignStmtOptional!=null) DesignStmtOptional.setParent(this);
        this.ForCondMark=ForCondMark;
        if(ForCondMark!=null) ForCondMark.setParent(this);
        this.ConditionOptional=ConditionOptional;
        if(ConditionOptional!=null) ConditionOptional.setParent(this);
        this.ForIncMark=ForIncMark;
        if(ForIncMark!=null) ForIncMark.setParent(this);
        this.DesignStmtOptional1=DesignStmtOptional1;
        if(DesignStmtOptional1!=null) DesignStmtOptional1.setParent(this);
        this.ForBodyMark=ForBodyMark;
        if(ForBodyMark!=null) ForBodyMark.setParent(this);
        this.Statement=Statement;
        if(Statement!=null) Statement.setParent(this);
    }

    public DesignStmtOptional getDesignStmtOptional() {
        return DesignStmtOptional;
    }

    public void setDesignStmtOptional(DesignStmtOptional DesignStmtOptional) {
        this.DesignStmtOptional=DesignStmtOptional;
    }

    public ForCondMark getForCondMark() {
        return ForCondMark;
    }

    public void setForCondMark(ForCondMark ForCondMark) {
        this.ForCondMark=ForCondMark;
    }

    public ConditionOptional getConditionOptional() {
        return ConditionOptional;
    }

    public void setConditionOptional(ConditionOptional ConditionOptional) {
        this.ConditionOptional=ConditionOptional;
    }

    public ForIncMark getForIncMark() {
        return ForIncMark;
    }

    public void setForIncMark(ForIncMark ForIncMark) {
        this.ForIncMark=ForIncMark;
    }

    public DesignStmtOptional getDesignStmtOptional1() {
        return DesignStmtOptional1;
    }

    public void setDesignStmtOptional1(DesignStmtOptional DesignStmtOptional1) {
        this.DesignStmtOptional1=DesignStmtOptional1;
    }

    public ForBodyMark getForBodyMark() {
        return ForBodyMark;
    }

    public void setForBodyMark(ForBodyMark ForBodyMark) {
        this.ForBodyMark=ForBodyMark;
    }

    public Statement getStatement() {
        return Statement;
    }

    public void setStatement(Statement Statement) {
        this.Statement=Statement;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(DesignStmtOptional!=null) DesignStmtOptional.accept(visitor);
        if(ForCondMark!=null) ForCondMark.accept(visitor);
        if(ConditionOptional!=null) ConditionOptional.accept(visitor);
        if(ForIncMark!=null) ForIncMark.accept(visitor);
        if(DesignStmtOptional1!=null) DesignStmtOptional1.accept(visitor);
        if(ForBodyMark!=null) ForBodyMark.accept(visitor);
        if(Statement!=null) Statement.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(DesignStmtOptional!=null) DesignStmtOptional.traverseTopDown(visitor);
        if(ForCondMark!=null) ForCondMark.traverseTopDown(visitor);
        if(ConditionOptional!=null) ConditionOptional.traverseTopDown(visitor);
        if(ForIncMark!=null) ForIncMark.traverseTopDown(visitor);
        if(DesignStmtOptional1!=null) DesignStmtOptional1.traverseTopDown(visitor);
        if(ForBodyMark!=null) ForBodyMark.traverseTopDown(visitor);
        if(Statement!=null) Statement.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(DesignStmtOptional!=null) DesignStmtOptional.traverseBottomUp(visitor);
        if(ForCondMark!=null) ForCondMark.traverseBottomUp(visitor);
        if(ConditionOptional!=null) ConditionOptional.traverseBottomUp(visitor);
        if(ForIncMark!=null) ForIncMark.traverseBottomUp(visitor);
        if(DesignStmtOptional1!=null) DesignStmtOptional1.traverseBottomUp(visitor);
        if(ForBodyMark!=null) ForBodyMark.traverseBottomUp(visitor);
        if(Statement!=null) Statement.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ForStmt(\n");

        if(DesignStmtOptional!=null)
            buffer.append(DesignStmtOptional.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ForCondMark!=null)
            buffer.append(ForCondMark.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ConditionOptional!=null)
            buffer.append(ConditionOptional.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ForIncMark!=null)
            buffer.append(ForIncMark.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(DesignStmtOptional1!=null)
            buffer.append(DesignStmtOptional1.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ForBodyMark!=null)
            buffer.append(ForBodyMark.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Statement!=null)
            buffer.append(Statement.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ForStmt]");
        return buffer.toString();
    }
}
