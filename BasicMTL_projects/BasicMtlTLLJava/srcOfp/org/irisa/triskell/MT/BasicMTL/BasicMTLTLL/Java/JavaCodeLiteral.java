/*
 * $Id: JavaCodeLiteral.java,v 1.3 2004-04-29 13:55:35 edrezen Exp $
 * Authors : modelware
 * 
 * Copyright 2004 - INRIA - LGPL license
 */
package org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java;



/**
  * A string value.
  */
public class JavaCodeLiteral 
    extends org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.Literal
{
    public String value;
    public String getValue () {
        return this.value;
    }


    public JavaCodeLiteral(
        String theCodeString)
    {
		this.value = theCodeString;
    }
}
