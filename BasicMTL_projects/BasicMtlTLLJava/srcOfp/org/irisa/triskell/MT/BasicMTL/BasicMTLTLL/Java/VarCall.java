/*
 * $Id: VarCall.java,v 1.5 2004-04-29 13:55:33 edrezen Exp $
 * Authors : modelware
 * 
 * Copyright 2004 - INRIA - LGPL license
 */
package org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java;



/**
  * The value contained by a variable.
  */
public class VarCall 
    extends org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.Expression
{
    /**
      * The varaibel withing tha value is.
      */
    public org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.VarDeclaration relatedDecl;
    public org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.VarDeclaration getRelatedDecl () {
        return this.relatedDecl;
    }
    public void setRelatedDecl (org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.VarDeclaration value) {
        this.relatedDecl = value; 
    }
    public int cardRelatedDecl () {
        if ( this.relatedDecl == null ) return 0;
        else return 1;
    }

    public String varName;
    public String getVarName () {
        return this.varName;
    }
    public void setVarName (String value) {
        this.varName = value; 
    }

    public boolean modelEltVar;
    public boolean getModelEltVar () {
        return this.modelEltVar;
    }
    public void setModelEltVar (boolean value) {
        this.modelEltVar = value; 
    }


    public VarCall(
        String varName)
    {
this.varName=varName;
this.modelEltVar=false;
    }
}
