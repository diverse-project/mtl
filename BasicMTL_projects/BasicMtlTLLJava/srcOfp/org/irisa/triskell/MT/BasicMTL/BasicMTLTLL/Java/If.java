/*
 * $Id: If.java,v 1.5 2004-04-29 13:55:32 edrezen Exp $
 * Authors : modelware
 * 
 * Copyright 2004 - INRIA - LGPL license
 */
package org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java;

import java.util.*;


/**
  * A branch beween two sequences of instructions according to a condition.
  */
public class If 
    extends org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.Instruction
{
    /**
      * The condition deciding wether the thenBody or elseBody has to be perforems; this must be a boolean value.
      */
    public org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.Expression condition;
    public org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.Expression getCondition () {
        return this.condition;
    }
    public void setCondition (org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.Expression value) {
        this.condition = value; 
    }
    public int cardCondition () {
        if ( this.condition == null ) return 0;
        else return 1;
    }

    /**
      * The sequence of instruction to be executed if the condition is true.
      */
    public Vector thenBody = new Vector();
    public org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.Instruction getThenBody (int i) {
        return (org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.Instruction)this.thenBody.elementAt(i);
    }
    public void setThenBody (int i, org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.Instruction value) {
        this.thenBody.setElementAt(value, i);
    }
    public void appendThenBody (org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.Instruction value) {
        this.thenBody.addElement(value);
    }
    public void eraseThenBody (org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.Instruction value)  {
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
    public Vector elseBody = new Vector();
    public org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.Instruction getElseBody (int i) {
        return (org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.Instruction)this.elseBody.elementAt(i);
    }
    public void setElseBody (int i, org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.Instruction value) {
        this.elseBody.setElementAt(value, i);
    }
    public void appendElseBody (org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.Instruction value) {
        this.elseBody.addElement(value);
    }
    public void eraseElseBody (org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.Instruction value)  {
        this.elseBody.removeElement(value);
    }
    public void eraseElseBody (int i)  {
        this.elseBody.removeElementAt(i);
    }
    public int cardElseBody () {
        return this.elseBody.size();
    }


    public If(
        int lineNumber)
    {
this.lineNumber=lineNumber;
    }
}
