/*
 * $Id: Expression.java,v 1.7 2004-04-29 13:55:37 edrezen Exp $
 * Authors : modelware
 * 
 * Copyright 2004 - INRIA - LGPL license
 */
package org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java;



/**
  * An instruction representing a precise value, either computed or already known.
  */
abstract public class Expression 
    extends org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.Instruction
{
    public org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.QualifiedName toBeCasted;
    public org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.QualifiedName getToBeCasted () {
        return this.toBeCasted;
    }
    public void setToBeCasted (org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.QualifiedName value) {
        this.toBeCasted = value; 
    }
    public int cardToBeCasted () {
        if ( this.toBeCasted == null ) return 0;
        else return 1;
    }

    public String toBeCastedWithTypeVar = null;
    public String getToBeCastedWithTypeVar () {
        return this.toBeCastedWithTypeVar;
    }
    public void setToBeCastedWithTypeVar (String value) {
        this.toBeCastedWithTypeVar = value; 
    }

    public String toBeCastedWithMethodVar;
    public String getToBeCastedWithMethodVar () {
        return this.toBeCastedWithMethodVar;
    }
    public void setToBeCastedWithMethodVar (String value) {
        this.toBeCastedWithMethodVar = value; 
    }

    public String toBeCastedWithParameterVar;
    public String getToBeCastedWithParameterVar () {
        return this.toBeCastedWithParameterVar;
    }
    public void setToBeCastedWithParameterVar (String value) {
        this.toBeCastedWithParameterVar = value; 
    }

    public boolean isTrownExpression = false;
    public boolean getIsTrownExpression () {
        return this.isTrownExpression;
    }
    public void setIsTrownExpression (boolean value) {
        this.isTrownExpression = value; 
    }

}
