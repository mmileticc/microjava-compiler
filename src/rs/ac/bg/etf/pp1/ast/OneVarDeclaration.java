// generated with ast extension for cup
// version 0.8
// 17/1/2026 1:5:10


package rs.ac.bg.etf.pp1.ast;

public class OneVarDeclaration extends VarDeclList {

    private OneVarDecl OneVarDecl;

    public OneVarDeclaration (OneVarDecl OneVarDecl) {
        this.OneVarDecl=OneVarDecl;
        if(OneVarDecl!=null) OneVarDecl.setParent(this);
    }

    public OneVarDecl getOneVarDecl() {
        return OneVarDecl;
    }

    public void setOneVarDecl(OneVarDecl OneVarDecl) {
        this.OneVarDecl=OneVarDecl;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(OneVarDecl!=null) OneVarDecl.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(OneVarDecl!=null) OneVarDecl.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(OneVarDecl!=null) OneVarDecl.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("OneVarDeclaration(\n");

        if(OneVarDecl!=null)
            buffer.append(OneVarDecl.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [OneVarDeclaration]");
        return buffer.toString();
    }
}
