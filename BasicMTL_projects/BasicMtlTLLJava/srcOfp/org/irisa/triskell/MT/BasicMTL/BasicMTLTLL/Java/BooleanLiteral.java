/*
 * $Id: BooleanLiteral.java,v 1.4 2004-04-29 13:55:38 edrezen Exp $
 * Authors : modelware
 * 
 * Copyright 2004 - INRIA - LGPL license
 */
package org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java;



/**
  * A boolean value.
  */
public class BooleanLiteral 
    extends org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.Literal
{
    public boolean value;
    public boolean getValue () {
        return this.value;
    }


    public BooleanLiteral(
        boolean theBoolean)
    {
		this.value = theBoolean;
    }
}
