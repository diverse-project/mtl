package org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java;

import org.irisa.triskell.MT.visitors.Java.GenericVisitor.*;
import org.irisa.triskell.MT.visitors.Java.AnalysingVisitor.*;
import java.util.*;
import org.irisa.triskell.MT.visitors.Java.GenericVisitor.Visitor;
import org.irisa.triskell.MT.visitors.Java.AnalysingVisitor.Property;
import java.util.Map;

/**
  * Stops the execution of the operation returning a value using the exception mechanisme, that is this value may have a treatment within a catch.
  */
public class Throws 
    extends org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.Instruction
{
    /**
      * The value to be thrown.
      */
    public org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.Expression thrownExpression;
    public org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.Expression getThrownExpression () {
        return this.thrownExpression;
    }
    public void setThrownExpression (org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.Expression value) {
        this.thrownExpression = value; 
    }
    public int cardThrownExpression () {
        if ( this.thrownExpression == null ) return 0;
        else return 1;
    }


    public Throws(
        int lineNumber)
    {
this.lineNumber=lineNumber;
    }
}
