package org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java;

import org.irisa.triskell.MT.visitors.Java.GenericVisitor.*;
import org.irisa.triskell.MT.visitors.Java.AnalysingVisitor.*;
import java.util.*;
import org.irisa.triskell.MT.visitors.Java.GenericVisitor.Visitor;
import org.irisa.triskell.MT.visitors.Java.AnalysingVisitor.Property;

/**
  * An instruction representing a precise value, either computed or already known.
  */
abstract public class Expression 
    extends org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.Instruction
{
    public org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.QualifiedName toBeCasted;
    public org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.QualifiedName getToBeCasted () {
        return this.toBeCasted;
    }
    public void setToBeCasted (org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.QualifiedName value) {
        this.toBeCasted = value; 
    }
    public int cardToBeCasted () {
        if ( this.toBeCasted == null ) return 0;
        else return 1;
    }

    public String toBeCastedWithTypeVar = null;
    public String getToBeCastedWithTypeVar () {
        return this.toBeCastedWithTypeVar;
    }
    public void setToBeCastedWithTypeVar (String value) {
        this.toBeCastedWithTypeVar = value; 
    }

    public String toBeCastedWithMethodVar;
    public String getToBeCastedWithMethodVar () {
        return this.toBeCastedWithMethodVar;
    }
    public void setToBeCastedWithMethodVar (String value) {
        this.toBeCastedWithMethodVar = value; 
    }

    public String toBeCastedWithParameterVar;
    public String getToBeCastedWithParameterVar () {
        return this.toBeCastedWithParameterVar;
    }
    public void setToBeCastedWithParameterVar (String value) {
        this.toBeCastedWithParameterVar = value; 
    }

}
