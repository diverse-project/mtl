package org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java;

import java.util.*;
import org.irisa.triskell.MT.visitors.Java.GenericVisitor.*;
import org.irisa.triskell.MT.visitors.Java.AnalysingVisitor.*;
import org.irisa.triskell.MT.visitors.Java.GenericVisitor.Visitor;
import org.irisa.triskell.MT.visitors.Java.AnalysingVisitor.Property;

/**
  * Accessible variable for the implementation of the owning operation / catch. A variable is defined by its name (unique within the operation and the catch if owner) and its type. During the execution, a variable contains a value whose type is compatible (that is is of a class or subclass of the type of the variable). This name cannot be null or self.
  */
public class VarDeclaration 
    extends org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.ASTNode
{
    /**
      * The type of the variable.
      */
    protected org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.UserClass type;
    public org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.UserClass getType () {
        return this.type;
    }
    public void setType (org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.UserClass value) {
        this.type = value; 
    }
    public int cardType () {
        if ( this.type == null ) return 0;
        else return 1;
    }

    public String name;
    public String getName () {
        return this.name;
    }

    protected boolean isFormalParameter;
    public boolean getIsFormalParameter () {
        return this.isFormalParameter;
    }
    public void setIsFormalParameter (boolean value) {
        this.isFormalParameter = value; 
    }


    public VarDeclaration(
        String theVar,
        boolean isFormalParameter)
    {
this.name=theVar;
this.isFormalParameter=isFormalParameter;
    }
}
