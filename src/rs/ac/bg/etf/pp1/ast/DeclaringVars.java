// generated with ast extension for cup
// version 0.8
// 17/1/2026 1:5:10


package rs.ac.bg.etf.pp1.ast;

public class DeclaringVars extends MaybeVarDecl {

    private MaybeVarDecl MaybeVarDecl;
    private VarDecl VarDecl;

    public DeclaringVars (MaybeVarDecl MaybeVarDecl, VarDecl VarDecl) {
        this.MaybeVarDecl=MaybeVarDecl;
        if(MaybeVarDecl!=null) MaybeVarDecl.setParent(this);
        this.VarDecl=VarDecl;
        if(VarDecl!=null) VarDecl.setParent(this);
    }

    public MaybeVarDecl getMaybeVarDecl() {
        return MaybeVarDecl;
    }

    public void setMaybeVarDecl(MaybeVarDecl MaybeVarDecl) {
        this.MaybeVarDecl=MaybeVarDecl;
    }

    public VarDecl getVarDecl() {
        return VarDecl;
    }

    public void setVarDecl(VarDecl VarDecl) {
        this.VarDecl=VarDecl;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(MaybeVarDecl!=null) MaybeVarDecl.accept(visitor);
        if(VarDecl!=null) VarDecl.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(MaybeVarDecl!=null) MaybeVarDecl.traverseTopDown(visitor);
        if(VarDecl!=null) VarDecl.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(MaybeVarDecl!=null) MaybeVarDecl.traverseBottomUp(visitor);
        if(VarDecl!=null) VarDecl.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("DeclaringVars(\n");

        if(MaybeVarDecl!=null)
            buffer.append(MaybeVarDecl.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(VarDecl!=null)
            buffer.append(VarDecl.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [DeclaringVars]");
        return buffer.toString();
    }
}
