// generated with ast extension for cup
// version 0.8
// 17/1/2026 1:5:10


package rs.ac.bg.etf.pp1.ast;

public class OneFormPar extends FormPars {

    private FormPar FormPar;

    public OneFormPar (FormPar FormPar) {
        this.FormPar=FormPar;
        if(FormPar!=null) FormPar.setParent(this);
    }

    public FormPar getFormPar() {
        return FormPar;
    }

    public void setFormPar(FormPar FormPar) {
        this.FormPar=FormPar;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(FormPar!=null) FormPar.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(FormPar!=null) FormPar.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(FormPar!=null) FormPar.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("OneFormPar(\n");

        if(FormPar!=null)
            buffer.append(FormPar.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [OneFormPar]");
        return buffer.toString();
    }
}
