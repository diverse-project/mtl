package org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java;



/**
  * Accessible variable for the implementation of the owning operation / catch. A variable is defined by its name (unique within the operation and the catch if owner) and its type. During the execution, a variable contains a value whose type is compatible (that is is of a class or subclass of the type of the variable). This name cannot be null or self.
  */
public class VarDeclaration 
    extends org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.ASTNode
{
    /**
      * The type of the variable.
      */
    public org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.QualifiedName type;
    public org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.QualifiedName getType () {
        return this.type;
    }
    public void setType (org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.QualifiedName value) {
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

    public boolean isFormalParameter;
    public boolean getIsFormalParameter () {
        return this.isFormalParameter;
    }
    public void setIsFormalParameter (boolean value) {
        this.isFormalParameter = value; 
    }

    public String mangle;
    public String getMangle () {
        return this.mangle;
    }
    public void setMangle (String value) {
        this.mangle = value; 
    }

    public int lineNumber;
    public int getLineNumber () {
        return this.lineNumber;
    }

    public boolean isCatchParameter = false;
    public boolean getIsCatchParameter () {
        return this.isCatchParameter;
    }
    public void setIsCatchParameter (boolean value) {
        this.isCatchParameter = value; 
    }


    public VarDeclaration(
        String theVar,
        String mangle,
        boolean isFormalParameter,
        int lineNumber)
    {
this.name=theVar;
this.mangle=mangle;
this.isFormalParameter=isFormalParameter;
this.lineNumber=lineNumber;
    }
}
