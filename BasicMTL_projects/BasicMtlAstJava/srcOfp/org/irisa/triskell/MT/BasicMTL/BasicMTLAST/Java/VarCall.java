/*
 * $Id: VarCall.java,v 1.3 2004-04-29 13:38:52 edrezen Exp $
 * Authors : modelware
 * 
 * Copyright 2004 - INRIA - LGPL license
 */
package org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java;

import org.irisa.triskell.MT.visitors.Java.GenericVisitor.*;
import org.irisa.triskell.MT.visitors.Java.AnalysingVisitor.*;
import java.util.*;
import org.irisa.triskell.MT.visitors.Java.GenericVisitor.Visitor;
import org.irisa.triskell.MT.visitors.Java.AnalysingVisitor.Property;

/**
  * The value contained by a variable.
  */
public class VarCall 
    extends org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.Expression
{
    /**
      * The varaibel withing tha value is.
      */
    protected org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.VarDeclaration relatedDecl;
    public org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.VarDeclaration getRelatedDecl () {
        return this.relatedDecl;
    }
    public int cardRelatedDecl () {
        if ( this.relatedDecl == null ) return 0;
        else return 1;
    }

    protected String varName;
    public String getVarName () {
        return this.varName;
    }
    public void setVarName (String value) {
        this.varName = value; 
    }


    public VarCall(
        String varName)
    {
this.varName=varName;
    }
}
