// generated with ast extension for cup
// version 0.8
// 17/1/2026 1:5:10


package rs.ac.bg.etf.pp1.ast;

public class ClassDecl implements SyntaxNode {

    private SyntaxNode parent;
    private int line;
    private String I1;
    private Extending Extending;
    private MaybeVarDecl MaybeVarDecl;
    private MaybeBody MaybeBody;

    public ClassDecl (String I1, Extending Extending, MaybeVarDecl MaybeVarDecl, MaybeBody MaybeBody) {
        this.I1=I1;
        this.Extending=Extending;
        if(Extending!=null) Extending.setParent(this);
        this.MaybeVarDecl=MaybeVarDecl;
        if(MaybeVarDecl!=null) MaybeVarDecl.setParent(this);
        this.MaybeBody=MaybeBody;
        if(MaybeBody!=null) MaybeBody.setParent(this);
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

    public MaybeBody getMaybeBody() {
        return MaybeBody;
    }

    public void setMaybeBody(MaybeBody MaybeBody) {
        this.MaybeBody=MaybeBody;
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
        if(MaybeBody!=null) MaybeBody.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Extending!=null) Extending.traverseTopDown(visitor);
        if(MaybeVarDecl!=null) MaybeVarDecl.traverseTopDown(visitor);
        if(MaybeBody!=null) MaybeBody.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Extending!=null) Extending.traverseBottomUp(visitor);
        if(MaybeVarDecl!=null) MaybeVarDecl.traverseBottomUp(visitor);
        if(MaybeBody!=null) MaybeBody.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ClassDecl(\n");

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

        if(MaybeBody!=null)
            buffer.append(MaybeBody.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ClassDecl]");
        return buffer.toString();
    }
}
