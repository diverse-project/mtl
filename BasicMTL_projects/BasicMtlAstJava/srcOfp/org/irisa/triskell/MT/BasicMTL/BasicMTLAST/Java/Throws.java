package org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java;

import org.irisa.triskell.MT.visitors.Java.GenericVisitor.*;
import org.irisa.triskell.MT.visitors.Java.AnalysingVisitor.*;
import java.util.*;
import org.irisa.triskell.MT.visitors.Java.GenericVisitor.Visitor;
import org.irisa.triskell.MT.visitors.Java.AnalysingVisitor.Property;
import java.util.Map;
import java.lang.Object;

/**
  * Stops the execution of the operation returning a value using the exception mechanisme, that is this value may have a treatment within a catch.
  */
public class Throws 
    extends org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.Instruction
{
    /**
      * The value to be thrown.
      */
    public org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.Expression thrownExpression;
    public org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.Expression getThrownExpression () {
        return this.thrownExpression;
    }
    public int cardThrownExpression () {
        if ( this.thrownExpression == null ) return 0;
        else return 1;
    }


    public Throws(
        org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.Expression expr)
    {
    }
}
