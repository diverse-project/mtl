/*
 * $Id: IntLiteral.java,v 1.4 2004-04-29 13:55:39 edrezen Exp $
 * Authors : modelware
 * 
 * Copyright 2004 - INRIA - LGPL license
 */
package org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java;



/**
  * An integer value.
  */
public class IntLiteral 
    extends org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.Literal
{
    public int value;
    public int getValue () {
        return this.value;
    }


    public IntLiteral(
        int theInt)
    {
		this.value = theInt;
    }
}
