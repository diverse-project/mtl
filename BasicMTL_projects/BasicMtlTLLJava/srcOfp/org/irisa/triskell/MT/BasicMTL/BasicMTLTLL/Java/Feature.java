/*
 * $Id: Feature.java,v 1.4 2004-04-29 13:55:32 edrezen Exp $
 * Authors : modelware
 * 
 * Copyright 2004 - INRIA - LGPL license
 */
package org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java;



abstract public class Feature 
    extends org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.ASTNode
{
    public String name;
    public String getName () {
        return this.name;
    }

    public String mangle;
    public String getMangle () {
        return this.mangle;
    }

    public org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.QualifiedName featureType;
    public org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.QualifiedName getFeatureType () {
        return this.featureType;
    }
    public void setFeatureType (org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.QualifiedName value) {
        this.featureType = value; 
    }
    public int cardFeatureType () {
        if ( this.featureType == null ) return 0;
        else return 1;
    }

    public int lineNumber;
    public int getLineNumber () {
        return this.lineNumber;
    }


    public Feature(
        String name,
        String mangle,
        int lineNumber)
    {
this.name=name;
this.mangle=mangle;
this.lineNumber=lineNumber;
    }
}
