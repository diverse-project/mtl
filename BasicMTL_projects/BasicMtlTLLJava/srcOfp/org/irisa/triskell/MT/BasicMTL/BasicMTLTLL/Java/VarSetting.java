/*
 * $Id: VarSetting.java,v 1.4 2004-04-29 13:55:37 edrezen Exp $
 * Authors : modelware
 * 
 * Copyright 2004 - INRIA - LGPL license
 */
package org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java;



/**
  * An instruction to change the value of a variable. This is a side effect operation.
  */
public class VarSetting 
    extends org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.Instruction
{
    /**
      * The expression representing the value to be assigned to the variable.
      */
    public org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.Expression value;
    public org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.Expression getValue () {
        return this.value;
    }
    public void setValue (org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.Expression value) {
        this.value = value; 
    }
    public int cardValue () {
        if ( this.value == null ) return 0;
        else return 1;
    }

    /**
      * The variable to be modified. It must be inside the operation or the stakeholder for an exception treatment (if within a posible multy-depth catch).
      */
    public org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.VarDeclaration modifiedVar;
    public org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.VarDeclaration getModifiedVar () {
        return this.modifiedVar;
    }
    public void setModifiedVar (org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.VarDeclaration value) {
        this.modifiedVar = value; 
    }
    public int cardModifiedVar () {
        if ( this.modifiedVar == null ) return 0;
        else return 1;
    }

    public String varName;
    public String getVarName () {
        return this.varName;
    }
    public void setVarName (String value) {
        this.varName = value; 
    }


    public VarSetting(
        String theVar,
        int lineNumber)
    {
this.varName=theVar;
this.lineNumber=lineNumber;
    }
}
