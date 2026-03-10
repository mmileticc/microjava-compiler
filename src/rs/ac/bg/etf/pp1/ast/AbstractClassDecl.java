// generated with ast extension for cup
// version 0.8
// 17/1/2026 1:5:10


package rs.ac.bg.etf.pp1.ast;

public class AbstractClassDecl implements SyntaxNode {

    private SyntaxNode parent;
    private int line;
    private String I1;
    private Extending Extending;
    private MaybeVarDecl MaybeVarDecl;
    private MaybeAbstractBody MaybeAbstractBody;

    public AbstractClassDecl (String I1, Extending Extending, MaybeVarDecl MaybeVarDecl, MaybeAbstractBody MaybeAbstractBody) {
        this.I1=I1;
        this.Extending=Extending;
        if(Extending!=null) Extending.setParent(this);
        this.MaybeVarDecl=MaybeVarDecl;
        if(MaybeVarDecl!=null) MaybeVarDecl.setParent(this);
        this.MaybeAbstractBody=MaybeAbstractBody;
        if(MaybeAbstractBody!=null) MaybeAbstractBody.setParent(this);
    }

    public String getI1() {
        return I1;
    }

    public void setI1(String I1) {
        this.I1=I1;
    }

    public Extending getExtending() {
        return Extending;
    }

    public void setExtending(Extending Extending) {
        this.Extending=Extending;
    }

    public MaybeVarDecl getMaybeVarDecl() {
        return MaybeVarDecl;
    }

    public void setMaybeVarDecl(MaybeVarDecl MaybeVarDecl) {
        this.MaybeVarDecl=MaybeVarDecl;
    }

    public MaybeAbstractBody getMaybeAbstractBody() {
        return MaybeAbstractBody;
    }

    public void setMaybeAbstractBody(MaybeAbstractBody MaybeAbstractBody) {
        this.MaybeAbstractBody=MaybeAbstractBody;
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
        if(Extending!=null) Extending.accept(visitor);
        if(MaybeVarDecl!=null) MaybeVarDecl.accept(visitor);
        if(MaybeAbstractBody!=null) MaybeAbstractBody.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Extending!=null) Extending.traverseTopDown(visitor);
        if(MaybeVarDecl!=null) MaybeVarDecl.traverseTopDown(visitor);
        if(MaybeAbstractBody!=null) MaybeAbstractBody.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Extending!=null) Extending.traverseBottomUp(visitor);
        if(MaybeVarDecl!=null) MaybeVarDecl.traverseBottomUp(visitor);
        if(MaybeAbstractBody!=null) MaybeAbstractBody.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("AbstractClassDecl(\n");

        buffer.append(" "+tab+I1);
        buffer.append("\n");

        if(Extending!=null)
            buffer.append(Extending.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(MaybeVarDecl!=null)
            buffer.append(MaybeVarDecl.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(MaybeAbstractBody!=null)
            buffer.append(MaybeAbstractBody.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [AbstractClassDecl]");
        return buffer.toString();
    }
}
