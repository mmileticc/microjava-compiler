// generated with ast extension for cup
// version 0.8
// 17/1/2026 1:5:10


package rs.ac.bg.etf.pp1.ast;

public class DesignatorList extends DesignListOptional {

    private Design Design;
    private DesignListOptional DesignListOptional;

    public DesignatorList (Design Design, DesignListOptional DesignListOptional) {
        this.Design=Design;
        if(Design!=null) Design.setParent(this);
        this.DesignListOptional=DesignListOptional;
        if(DesignListOptional!=null) DesignListOptional.setParent(this);
    }

    public Design getDesign() {
        return Design;
    }

    public void setDesign(Design Design) {
        this.Design=Design;
    }

    public DesignListOptional getDesignListOptional() {
        return DesignListOptional;
    }

    public void setDesignListOptional(DesignListOptional DesignListOptional) {
        this.DesignListOptional=DesignListOptional;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Design!=null) Design.accept(visitor);
        if(DesignListOptional!=null) DesignListOptional.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Design!=null) Design.traverseTopDown(visitor);
        if(DesignListOptional!=null) DesignListOptional.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Design!=null) Design.traverseBottomUp(visitor);
        if(DesignListOptional!=null) DesignListOptional.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("DesignatorList(\n");

        if(Design!=null)
            buffer.append(Design.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(DesignListOptional!=null)
            buffer.append(DesignListOptional.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [DesignatorList]");
        return buffer.toString();
    }
}
