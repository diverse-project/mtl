package org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java;

import org.irisa.triskell.MT.visitors.Java.GenericVisitor.*;
import org.irisa.triskell.MT.visitors.Java.AnalysingVisitor.*;
import java.util.*;
import org.irisa.triskell.MT.visitors.Java.GenericVisitor.Visitor;
import org.irisa.triskell.MT.visitors.Java.AnalysingVisitor.Property;
import java.util.Map;
import java.lang.Object;

/**
  * An instruction to change the value of a variable. This is a side effect operation.
  */
public class VarSetting 
    extends org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.Instruction
{
    /**
      * The expression representing the value to be assigned to the variable.
      */
    public org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.Expression value;
    public org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.Expression getValue () {
        return this.value;
    }
    public int cardValue () {
        if ( this.value == null ) return 0;
        else return 1;
    }

    /**
      * The variable to be modified. It must be inside the operation or the stakeholder for an exception treatment (if within a posible multy-depth catch).
      */
    public org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.VarDeclaration modifiedVar;
    public org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.VarDeclaration getModifiedVar () {
        return this.modifiedVar;
    }
    public void setModifiedVar (org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.VarDeclaration value) {
        this.modifiedVar = value; 
    }
    public int cardModifiedVar () {
        if ( this.modifiedVar == null ) return 0;
        else return 1;
    }

    public String varName;
    public String getVarName () {
        return this.varName;
    }
    public void setVarName (String value) {
        this.varName = value; 
    }

    public org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.Attribute modifiedAttribute;
    public org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.Attribute getModifiedAttribute () {
        return this.modifiedAttribute;
    }
    public void setModifiedAttribute (org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.Attribute value) {
        this.modifiedAttribute = value; 
    }
    public int cardModifiedAttribute () {
        if ( this.modifiedAttribute == null ) return 0;
        else return 1;
    }


    public VarSetting(
        String theVar,
        org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.Expression expr)
    {
this.varName=theVar;
this.value=expr;
    }
}
