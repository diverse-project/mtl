/*
 * $Id: Literal.java,v 1.4 2004-04-29 13:55:38 edrezen Exp $
 * Authors : modelware
 * 
 * Copyright 2004 - INRIA - LGPL license
 */
package org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java;



/**
  * A non-computed value.
  */
abstract public class Literal 
    extends org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.Expression
{
    /**
      * The type of this value.
      */
    public org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.UserClass type;
    public org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.UserClass getType () {
        return this.type;
    }
    public int cardType () {
        if ( this.type == null ) return 0;
        else return 1;
    }

}
