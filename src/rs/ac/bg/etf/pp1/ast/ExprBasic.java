// generated with ast extension for cup
// version 0.8
// 17/1/2026 1:5:10


package rs.ac.bg.etf.pp1.ast;

public class ExprBasic implements SyntaxNode {

    private SyntaxNode parent;
    private int line;
    public rs.etf.pp1.symboltable.concepts.Struct struct = null;

    private MinusOption MinusOption;
    private TermList TermList;

    public ExprBasic (MinusOption MinusOption, TermList TermList) {
        this.MinusOption=MinusOption;
        if(MinusOption!=null) MinusOption.setParent(this);
        this.TermList=TermList;
        if(TermList!=null) TermList.setParent(this);
    }

    public MinusOption getMinusOption() {
        return MinusOption;
    }

    public void setMinusOption(MinusOption MinusOption) {
        this.MinusOption=MinusOption;
    }

    public TermList getTermList() {
        return TermList;
    }

    public void setTermList(TermList TermList) {
        this.TermList=TermList;
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
        if(MinusOption!=null) MinusOption.accept(visitor);
        if(TermList!=null) TermList.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(MinusOption!=null) MinusOption.traverseTopDown(visitor);
        if(TermList!=null) TermList.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(MinusOption!=null) MinusOption.traverseBottomUp(visitor);
        if(TermList!=null) TermList.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ExprBasic(\n");

        if(MinusOption!=null)
            buffer.append(MinusOption.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(TermList!=null)
            buffer.append(TermList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ExprBasic]");
        return buffer.toString();
    }
}
