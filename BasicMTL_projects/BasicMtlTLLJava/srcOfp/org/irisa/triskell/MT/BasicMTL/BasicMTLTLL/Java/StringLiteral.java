/*
 * $Id: StringLiteral.java,v 1.4 2004-04-29 13:55:38 edrezen Exp $
 * Authors : modelware
 * 
 * Copyright 2004 - INRIA - LGPL license
 */
package org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java;



/**
  * A string value.
  */
public class StringLiteral 
    extends org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.Literal
{
    public String value;
    public String getValue () {
        return this.value;
    }


    public StringLiteral(
        String theString)
    {
		this.value = theString;
    }
}
