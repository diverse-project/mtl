/*
 * $Id: OclTypeLiteral.java,v 1.4 2004-04-29 13:55:39 edrezen Exp $
 * Authors : modelware
 * 
 * Copyright 2004 - INRIA - LGPL license
 */
package org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java;



public class OclTypeLiteral 
    extends org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.Literal
{
    public org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.QualifiedName theType;
    public org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.QualifiedName getTheType () {
        return this.theType;
    }
    public void setTheType (org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.QualifiedName value) {
        this.theType = value; 
    }
    public int cardTheType () {
        if ( this.theType == null ) return 0;
        else return 1;
    }

}
