package org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java;

import java.util.*;
import org.irisa.triskell.MT.visitors.Java.GenericVisitor.*;
import org.irisa.triskell.MT.visitors.Java.AnalysingVisitor.*;
import org.irisa.triskell.MT.visitors.Java.GenericVisitor.Visitor;
import org.irisa.triskell.MT.visitors.Java.AnalysingVisitor.Property;

/**
  * The usual concept of operation. It is defined within a class. An operation have some typed parameters (FormalParameter). The signature of an operation is the composition of its name and the number of its parameters. This signature is deterministic within the owning class. If this signature is already in the class type hierarchy, this operation is the redefinition of the one(s) of the superclass. Redefining operations must have the same types for its formal parameters than the redefined operations(s).
  * If the implementation of the operation or any one of its redefining operation has side effect, this operation is a side effect.
  */
public class Operation 
    extends org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.ASTNode
{
    /**
      * The implementation of the operation. These instructions are executed in sequence up to a return, a throw or the end of the sequence. These instructions are not saved by the "Library.store" method.
      */
    protected transient Vector instructions = new Vector();
    public org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.Instruction getInstructions (int i) {
        return (org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.Instruction)this.instructions.elementAt(i);
    }
    public void setInstructions (int i, org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.Instruction value) {
        this.instructions.setElementAt(value, i);
    }
    public void appendInstructions (org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.Instruction value) {
        this.instructions.addElement(value);
    }
    public void eraseInstructions (org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.Instruction value) throws Exception {
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

    protected org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.Attribute isGetterFor;
    public org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.Attribute getIsGetterFor () {
        return this.isGetterFor;
    }
    public void setIsGetterFor (org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.Attribute value) {
        this.isGetterFor = value; 
    }
    public int cardIsGetterFor () {
        if ( this.isGetterFor == null ) return 0;
        else return 1;
    }

    protected org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.Attribute isSetterFor;
    public org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.Attribute getIsSetterFor () {
        return this.isSetterFor;
    }
    public void setIsSetterFor (org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.Attribute value) {
        this.isSetterFor = value; 
    }
    public int cardIsSetterFor () {
        if ( this.isSetterFor == null ) return 0;
        else return 1;
    }

    /**
      * The type of the returned value of the operation. Empty if the operation does ot return a value.
      */
    protected org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.UserClass returnedType;
    public org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.UserClass getReturnedType () {
        return this.returnedType;
    }
    public void setReturnedType (org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.UserClass value) {
        this.returnedType = value; 
    }
    public int cardReturnedType () {
        if ( this.returnedType == null ) return 0;
        else return 1;
    }

    /**
      * The parameters of the operation.
      */
    protected Vector Parameters = new Vector();
    public org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.VarDeclaration getParameters (int i) {
        return (org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.VarDeclaration)this.Parameters.elementAt(i);
    }
    public void setParameters (int i, org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.VarDeclaration value) {
        this.Parameters.setElementAt(value, i);
    }
    public void appendParameters (org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.VarDeclaration value) {
        this.Parameters.addElement(value);
    }
    public void eraseParameters (org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.VarDeclaration value)  {
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
    protected transient Vector declaredVariables = new Vector();
    public org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.VarDeclaration getDeclaredVariables (int i) {
        return (org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.VarDeclaration)this.declaredVariables.elementAt(i);
    }
    public void setDeclaredVariables (int i, org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.VarDeclaration value) {
        this.declaredVariables.setElementAt(value, i);
    }
    public void appendDeclaredVariables (org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.VarDeclaration value) {
        this.declaredVariables.addElement(value);
    }
    public void eraseDeclaredVariables (org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.VarDeclaration value)  {
        this.declaredVariables.removeElement(value);
    }
    public void eraseDeclaredVariables (int i)  {
        this.declaredVariables.removeElementAt(i);
    }
    public int cardDeclaredVariables () {
        return this.declaredVariables.size();
    }

    public String name;
    public String getName () {
        return this.name;
    }
    public void setName (String value) {
        this.name = value; 
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

    /**
      * The class the operation may throw. If there is at lest one, the attribute throwsExeption must be true.
      */
    protected Vector thrownClass = new Vector();
    public org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.UserClass getThrownClass (int i) {
        return (org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.UserClass)this.thrownClass.elementAt(i);
    }
    public void setThrownClass (int i, org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.UserClass value) {
        this.thrownClass.setElementAt(value, i);
    }
    public void appendThrownClass (org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.UserClass value) {
        this.thrownClass.addElement(value);
    }
    public void eraseThrownClass (org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.UserClass value)  {
        this.thrownClass.removeElement(value);
    }
    public void eraseThrownClass (int i)  {
        this.thrownClass.removeElementAt(i);
    }
    public int cardThrownClass () {
        return this.thrownClass.size();
    }

    /**
      * The redefined operations.
      */
    protected Vector redefinedOperations = new Vector();
    public org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.Operation getRedefinedOperations (int i) {
        return (org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.Operation)this.redefinedOperations.elementAt(i);
    }
    public void setRedefinedOperations (int i, org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.Operation value) {
        this.redefinedOperations.setElementAt(value, i);
    }
    public void appendRedefinedOperations (org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.Operation value) {
        this.redefinedOperations.addElement(value);
    }
    public void eraseRedefinedOperations (org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.Operation value)  {
        this.redefinedOperations.removeElement(value);
    }
    public void eraseRedefinedOperations (int i)  {
        this.redefinedOperations.removeElementAt(i);
    }
    public int cardRedefinedOperations () {
        return this.redefinedOperations.size();
    }


    public Operation(
        String name)
    {
this.name=name;
    }
}
