/*
 * $Id: RealLiteral.java,v 1.5 2004-04-29 13:55:33 edrezen Exp $
 * Authors : modelware
 * 
 * Copyright 2004 - INRIA - LGPL license
 */
package org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java;



/**
  * A real value.
  */
public class RealLiteral 
    extends org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.Literal
{
    public float value;
    public float getValue () {
        return this.value;
    }


    public RealLiteral(
        float theReal)
    {
		this.value = theReal;		
    }
}
