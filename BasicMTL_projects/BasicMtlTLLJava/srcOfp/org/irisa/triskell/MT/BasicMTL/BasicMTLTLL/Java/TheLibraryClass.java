/*
 * $Id: TheLibraryClass.java,v 1.4 2004-04-29 13:55:34 edrezen Exp $
 * Authors : modelware
 * 
 * Copyright 2004 - INRIA - LGPL license
 */
package org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java;



public class TheLibraryClass 
    extends org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.UserDefinedClass
{
    public org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.BasicMtlLibrary theLibrary;
    public org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.BasicMtlLibrary getTheLibrary () {
        return this.theLibrary;
    }
    public void setTheLibrary (org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.BasicMtlLibrary value) {
        this.theLibrary = value; 
    }
    public int cardTheLibrary () {
        if ( this.theLibrary == null ) return 0;
        else return 1;
    }


    public TheLibraryClass(
        String name,
        String mangle,
        int lineNumber)
    {
super(name,mangle,lineNumber);
    }
}
