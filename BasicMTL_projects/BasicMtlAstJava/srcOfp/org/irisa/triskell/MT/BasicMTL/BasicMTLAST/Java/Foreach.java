package org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java;

import java.util.*;
import org.irisa.triskell.MT.visitors.Java.GenericVisitor.*;
import org.irisa.triskell.MT.visitors.Java.AnalysingVisitor.*;
import org.irisa.triskell.MT.visitors.Java.GenericVisitor.Visitor;
import org.irisa.triskell.MT.visitors.Java.AnalysingVisitor.Property;
import java.util.Map;
import java.lang.Object;

public class Foreach 
    extends org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.Instruction
{
    protected Vector body = new Vector();
    public org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.Instruction getBody (int i) {
        return (org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.Instruction)this.body.elementAt(i);
    }
    public void setBody (int i, org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.Instruction value) {
        this.body.setElementAt(value, i);
    }
    public void appendBody (org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.Instruction value) {
        this.body.addElement(value);
    }
    public void eraseBody (org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.Instruction value)  {
        this.body.removeElement(value);
    }
    public void eraseBody (int i)  {
        this.body.removeElementAt(i);
    }
    public int cardBody () {
        return this.body.size();
    }

    protected org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.VarDeclaration varDeclaration;
    public org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.VarDeclaration getVarDeclaration () {
        return this.varDeclaration;
    }
    public void setVarDeclaration (org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.VarDeclaration value) {
        this.varDeclaration = value; 
    }
    public int cardVarDeclaration () {
        if ( this.varDeclaration == null ) return 0;
        else return 1;
    }

    protected org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.Expression collection;
    public org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.Expression getCollection () {
        return this.collection;
    }
    public void setCollection (org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.Expression value) {
        this.collection = value; 
    }
    public int cardCollection () {
        if ( this.collection == null ) return 0;
        else return 1;
    }

    protected org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.Expression condition;
    public org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.Expression getCondition () {
        return this.condition;
    }
    public void setCondition (org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.Expression value) {
        this.condition = value; 
    }
    public int cardCondition () {
        if ( this.condition == null ) return 0;
        else return 1;
    }


    public Foreach(
        org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.VarDeclaration varDeclaration,
        org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.Expression collection,
        org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.Expression condition)
    {
setVarDeclaration (varDeclaration);
    	setCollection (collection);
    	setCondition (condition);
    }
}
