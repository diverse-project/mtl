/*
 * $Id: Catch.java,v 1.3 2004-04-29 13:38:51 edrezen Exp $
 * Authors : modelware
 * 
 * Copyright 2004 - INRIA - LGPL license
 */
package org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java;

import java.util.*;
import org.irisa.triskell.MT.visitors.Java.GenericVisitor.*;
import org.irisa.triskell.MT.visitors.Java.AnalysingVisitor.*;
import org.irisa.triskell.MT.visitors.Java.GenericVisitor.Visitor;
import org.irisa.triskell.MT.visitors.Java.AnalysingVisitor.Property;

/**
  * This class represents an exception treatment. A catch is specialized in the tratment of an particular kind (= type) of exception. Note any class can be considered as an exception: the difference is made by return (normal operation termination) and throw, which initiate the catch mechanism. The selected catch is the first which can treat the thrown value (according to its type). This thrown value is placed in a variable declaration during the treatment.
  */
public class Catch 
    extends org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.ASTNode
{
    /**
      * The instructions executed during the catch mechanism. Note the thrown value is accessible for these values as a variable.
      */
    protected Vector catchBody = new Vector();
    public org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.Instruction getCatchBody (int i) {
        return (org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.Instruction)this.catchBody.elementAt(i);
    }
    public void setCatchBody (int i, org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.Instruction value) {
        this.catchBody.setElementAt(value, i);
    }
    public void appendCatchBody (org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.Instruction value) {
        this.catchBody.addElement(value);
    }
    public void eraseCatchBody (org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.Instruction value)  {
        this.catchBody.removeElement(value);
    }
    public void eraseCatchBody (int i)  {
        this.catchBody.removeElementAt(i);
    }
    public int cardCatchBody () {
        return this.catchBody.size();
    }

    /**
      * The stakeholding variable for the thrown value.
      */
    protected org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.VarDeclaration catchedException;
    public org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.VarDeclaration getCatchedException () {
        return this.catchedException;
    }
    public void setCatchedException (org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.VarDeclaration value) {
        this.catchedException = value; 
    }
    public int cardCatchedException () {
        if ( this.catchedException == null ) return 0;
        else return 1;
    }

}
