package org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java;

import java.util.*;
import org.irisa.triskell.MT.visitors.Java.GenericVisitor.*;
import org.irisa.triskell.MT.visitors.Java.AnalysingVisitor.*;
import org.irisa.triskell.MT.visitors.Java.GenericVisitor.Visitor;
import org.irisa.triskell.MT.visitors.Java.AnalysingVisitor.Property;

/**
  * A branch beween two sequences of instructions according to a condition.
  */
public class If 
    extends org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.Instruction
{
    /**
      * The condition deciding wether the thenBody or elseBody has to be perforems; this must be a boolean value.
      */
    protected org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.Expression condition;
    public org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.Expression getCondition () {
        return this.condition;
    }
    public int cardCondition () {
        if ( this.condition == null ) return 0;
        else return 1;
    }

    /**
      * The sequence of instruction to be executed if the condition is true.
      */
    protected Vector thenBody = new Vector();
    public org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.Instruction getThenBody (int i) {
        return (org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.Instruction)this.thenBody.elementAt(i);
    }
    public void setThenBody (int i, org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.Instruction value) {
        this.thenBody.setElementAt(value, i);
    }
    public void appendThenBody (org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.Instruction value) {
        this.thenBody.addElement(value);
    }
    public void eraseThenBody (org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.Instruction value)  {
        this.thenBody.removeElement(value);
    }
    public void eraseThenBody (int i)  {
        this.thenBody.removeElementAt(i);
    }
    public int cardThenBody () {
        return this.thenBody.size();
    }

    /**
      * The sequence of instruction to be executed if the condition is false.
      */
    protected Vector elseBody = new Vector();
    public org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.Instruction getElseBody (int i) {
        return (org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.Instruction)this.elseBody.elementAt(i);
    }
    public void setElseBody (int i, org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.Instruction value) {
        this.elseBody.setElementAt(value, i);
    }
    public void appendElseBody (org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.Instruction value) {
        this.elseBody.addElement(value);
    }
    public void eraseElseBody (org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.Instruction value)  {
        this.elseBody.removeElement(value);
    }
    public void eraseElseBody (int i)  {
        this.elseBody.removeElementAt(i);
    }
    public int cardElseBody () {
        return this.elseBody.size();
    }


    public If(
        org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.Expression expr)
    {
this.condition=expr;
    }
}
