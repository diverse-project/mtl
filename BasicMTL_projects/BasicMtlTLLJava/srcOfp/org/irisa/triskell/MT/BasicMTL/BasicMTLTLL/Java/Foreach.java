package org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java;

import java.util.*;


public class Foreach 
{
    public Vector body = new Vector();
    public org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.Instruction getBody (int i) {
        return (org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.Instruction)this.body.elementAt(i);
    }
    public void setBody (int i, org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.Instruction value) {
        this.body.setElementAt(value, i);
    }
    public void appendBody (org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.Instruction value) {
        this.body.addElement(value);
    }
    public void eraseBody (org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.Instruction value)  {
        this.body.removeElement(value);
    }
    public void eraseBody (int i)  {
        this.body.removeElementAt(i);
    }
    public int cardBody () {
        return this.body.size();
    }

    public org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.Expression collection;
    public org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.Expression getCollection () {
        return this.collection;
    }
    public void setCollection (org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.Expression value) {
        this.collection = value; 
    }
    public int cardCollection () {
        if ( this.collection == null ) return 0;
        else return 1;
    }

    public org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.VarDeclaration varDeclaration;
    public org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.VarDeclaration getVarDeclaration () {
        return this.varDeclaration;
    }
    public void setVarDeclaration (org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.VarDeclaration value) {
        this.varDeclaration = value; 
    }
    public int cardVarDeclaration () {
        if ( this.varDeclaration == null ) return 0;
        else return 1;
    }

}
