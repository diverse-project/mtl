/*
 * $Id: Operation.java,v 1.8 2004-11-03 09:11:21 jpthibau Exp $
 * Authors : modelware
 * 
 * Copyright 2004 - INRIA - LGPL license
 */
package org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java;

import java.util.*;


/**
  * The usual concept of operation. It is defined within a class. An operation have some typed parameters (FormalParameter). The signature of an operation is the composition of its name and the number of its parameters. This signature is deterministic within the owning class. If this signature is already in the class type hierarchy, this operation is the redefinition of the one(s) of the superclass. Redefining operations must have the same types for its formal parameters than the redefined operations(s).
  * If the implementation of the operation or any one of its redefining operation has side effect, this operation is a side effect.
  */
public class Operation 
    extends org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.Feature
{
    /**
      * The implementation of the operation. These instructions are executed in sequence up to a return, a throw or the end of the sequence. These instructions are not saved by the "Library.store" method.
      */
    public Vector instructions = new Vector();
    public org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.Instruction getInstructions (int i) {
        return (org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.Instruction)this.instructions.elementAt(i);
    }
    public void setInstructions (int i, org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.Instruction value) {
        this.instructions.setElementAt(value, i);
    }
    public void appendInstructions (org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.Instruction value) {
        this.instructions.addElement(value);
    }
    public void eraseInstructions (org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.Instruction value) throws Exception {
        if (this.instructions.size() == 1)
            throw new Exception ( "TooFewArgsInAssociation(1)" );
        else
            this.instructions.removeElement(value);
    }
    public void eraseInstructions (int i) throws Exception {
        if (this.instructions.size() == 1)
            throw new Exception ( "TooFewArgsInAssociation(1)" );
        else
            this.instructions.removeElementAt(i);
    }
    public int cardInstructions () {
        return this.instructions.size();
    }

    public org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.OpSignature theSignature;
    public org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.OpSignature getTheSignature () {
        return this.theSignature;
    }
    public void setTheSignature (org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.OpSignature value) {
        this.theSignature = value; 
    }
    public int cardTheSignature () {
        if ( this.theSignature == null ) return 0;
        else return 1;
    }

    /**
      * The parameters of the operation.
      */
    public Vector Parameters = new Vector();
    public org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.VarDeclaration getParameters (int i) {
        return (org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.VarDeclaration)this.Parameters.elementAt(i);
    }
    public void setParameters (int i, org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.VarDeclaration value) {
        this.Parameters.setElementAt(value, i);
    }
    public void appendParameters (org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.VarDeclaration value) {
        this.Parameters.addElement(value);
    }
    public void eraseParameters (org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.VarDeclaration value)  {
        this.Parameters.removeElement(value);
    }
    public void eraseParameters (int i)  {
        this.Parameters.removeElementAt(i);
    }
    public int cardParameters () {
        return this.Parameters.size();
    }

    /**
      * Known variables are defined at the beginning of the operation. The formal parameters are redefined there. These variables are not saved by the "Library.store" method.
      */
    public Vector declaredVariables = new Vector();
    public org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.VarDeclaration getDeclaredVariables (int i) {
        return (org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.VarDeclaration)this.declaredVariables.elementAt(i);
    }
    public void setDeclaredVariables (int i, org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.VarDeclaration value) {
        this.declaredVariables.setElementAt(value, i);
    }
    public void appendDeclaredVariables (org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.VarDeclaration value) {
        this.declaredVariables.addElement(value);
    }
    public void eraseDeclaredVariables (org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.VarDeclaration value)  {
        this.declaredVariables.removeElement(value);
    }
    public void eraseDeclaredVariables (int i)  {
        this.declaredVariables.removeElementAt(i);
    }
    public int cardDeclaredVariables () {
        return this.declaredVariables.size();
    }

    /**
      * Indicates if the operation may throw an exception.
      */
    public boolean throwsException;
    public boolean getThrowsException () {
        return this.throwsException;
    }
    public void setThrowsException (boolean value) {
        this.throwsException = value; 
    }

    public boolean isAbstract;
    public boolean getIsAbstract () {
        return this.isAbstract;
    }
    public void setIsAbstract (boolean value) {
        this.isAbstract = value; 
    }

    public boolean isConstructor;
    public boolean getIsConstructor () {
        return this.isConstructor;
    }

    public org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.Attribute isGetterFor;
    public org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.Attribute getIsGetterFor () {
        return this.isGetterFor;
    }
    public void setIsGetterFor (org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.Attribute value) {
        this.isGetterFor = value; 
    }
    public int cardIsGetterFor () {
        if ( this.isGetterFor == null ) return 0;
        else return 1;
    }

    public org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.Attribute isSetterFor;
    public org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.Attribute getIsSetterFor () {
        return this.isSetterFor;
    }
    public void setIsSetterFor (org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.Attribute value) {
        this.isSetterFor = value; 
    }
    public int cardIsSetterFor () {
        if ( this.isSetterFor == null ) return 0;
        else return 1;
    }


    public Operation(
        String name,
        String mangle,
        boolean throwsException,
        boolean isConstructor,
        int lineNumber)
    {
super(name,mangle,lineNumber);
this.throwsException=throwsException;
this.isConstructor=isConstructor;
    }
}
