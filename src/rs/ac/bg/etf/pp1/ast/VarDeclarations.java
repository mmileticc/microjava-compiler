// generated with ast extension for cup
// version 0.8
// 17/1/2026 1:5:10


package rs.ac.bg.etf.pp1.ast;

public class VarDeclarations extends VarDeclList {

    private OneVarDecl OneVarDecl;
    private VarDeclList VarDeclList;

    public VarDeclarations (OneVarDecl OneVarDecl, VarDeclList VarDeclList) {
        this.OneVarDecl=OneVarDecl;
        if(OneVarDecl!=null) OneVarDecl.setParent(this);
        this.VarDeclList=VarDeclList;
        if(VarDeclList!=null) VarDeclList.setParent(this);
    }

    public OneVarDecl getOneVarDecl() {
        return OneVarDecl;
    }

    public void setOneVarDecl(OneVarDecl OneVarDecl) {
        this.OneVarDecl=OneVarDecl;
    }

    public VarDeclList getVarDeclList() {
        return VarDeclList;
    }

    public void setVarDeclList(VarDeclList VarDeclList) {
        this.VarDeclList=VarDeclList;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(OneVarDecl!=null) OneVarDecl.accept(visitor);
        if(VarDeclList!=null) VarDeclList.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(OneVarDecl!=null) OneVarDecl.traverseTopDown(visitor);
        if(VarDeclList!=null) VarDeclList.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(OneVarDecl!=null) OneVarDecl.traverseBottomUp(visitor);
        if(VarDeclList!=null) VarDeclList.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("VarDeclarations(\n");

        if(OneVarDecl!=null)
            buffer.append(OneVarDecl.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(VarDeclList!=null)
            buffer.append(VarDeclList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [VarDeclarations]");
        return buffer.toString();
    }
}
