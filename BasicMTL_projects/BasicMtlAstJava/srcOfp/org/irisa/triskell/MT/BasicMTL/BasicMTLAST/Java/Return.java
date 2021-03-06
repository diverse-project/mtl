/*
 * $Id: Return.java,v 1.3 2004-04-29 13:38:53 edrezen Exp $
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
  * Stops the execution of the owning operation. It may return a value according to the operation return type.
  */
public class Return 
    extends org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.Instruction
{
    /**
      * The value returned by the operation. Its tupe must match the Operation.returedType; that is be of a class or a subclass of the return type of the owning operation.
      */
    protected org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.Expression returnedExpression;
    public org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.Expression getReturnedExpression () {
        return this.returnedExpression;
    }
    public void setReturnedExpression (org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.Expression value) {
        this.returnedExpression = value; 
    }
    public int cardReturnedExpression () {
        if ( this.returnedExpression == null ) return 0;
        else return 1;
    }


    public Return(
        org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.Expression expr)
    {
this.returnedExpression=expr;
    }
}
