// generated with ast extension for cup
// version 0.8
// 17/1/2026 1:5:10


package rs.ac.bg.etf.pp1.ast;

public class IfElseStmt extends Statement {

    private IfClause IfClause;
    private Statement Statement;
    private ElseClause ElseClause;
    private Statement Statement1;

    public IfElseStmt (IfClause IfClause, Statement Statement, ElseClause ElseClause, Statement Statement1) {
        this.IfClause=IfClause;
        if(IfClause!=null) IfClause.setParent(this);
        this.Statement=Statement;
        if(Statement!=null) Statement.setParent(this);
        this.ElseClause=ElseClause;
        if(ElseClause!=null) ElseClause.setParent(this);
        this.Statement1=Statement1;
        if(Statement1!=null) Statement1.setParent(this);
    }

    public IfClause getIfClause() {
        return IfClause;
    }

    public void setIfClause(IfClause IfClause) {
        this.IfClause=IfClause;
    }

    public Statement getStatement() {
        return Statement;
    }

    public void setStatement(Statement Statement) {
        this.Statement=Statement;
    }

    public ElseClause getElseClause() {
        return ElseClause;
    }

    public void setElseClause(ElseClause ElseClause) {
        this.ElseClause=ElseClause;
    }

    public Statement getStatement1() {
        return Statement1;
    }

    public void setStatement1(Statement Statement1) {
        this.Statement1=Statement1;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(IfClause!=null) IfClause.accept(visitor);
        if(Statement!=null) Statement.accept(visitor);
        if(ElseClause!=null) ElseClause.accept(visitor);
        if(Statement1!=null) Statement1.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(IfClause!=null) IfClause.traverseTopDown(visitor);
        if(Statement!=null) Statement.traverseTopDown(visitor);
        if(ElseClause!=null) ElseClause.traverseTopDown(visitor);
        if(Statement1!=null) Statement1.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(IfClause!=null) IfClause.traverseBottomUp(visitor);
        if(Statement!=null) Statement.traverseBottomUp(visitor);
        if(ElseClause!=null) ElseClause.traverseBottomUp(visitor);
        if(Statement1!=null) Statement1.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("IfElseStmt(\n");

        if(IfClause!=null)
            buffer.append(IfClause.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Statement!=null)
            buffer.append(Statement.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ElseClause!=null)
            buffer.append(ElseClause.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Statement1!=null)
            buffer.append(Statement1.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [IfElseStmt]");
        return buffer.toString();
    }
}
