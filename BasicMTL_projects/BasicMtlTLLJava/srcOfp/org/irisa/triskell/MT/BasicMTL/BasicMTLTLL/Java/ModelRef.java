package org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java;

import org.irisa.triskell.MT.visitors.Java.GenericVisitor.*;
import org.irisa.triskell.MT.visitors.Java.AnalysingVisitor.*;
import java.util.*;
import org.irisa.triskell.MT.visitors.Java.GenericVisitor.Visitor;
import org.irisa.triskell.MT.visitors.Java.AnalysingVisitor.Property;
import java.util.Map;

/**
  * An acces to a model. Used elements (operation, classes, attributes...) in this model are contained as a library does. The difference is that these elements are proxies for the real ones, given when the library is "instaciated" (see Use and ParameterAssociation).
  */
abstract public class ModelRef 
    extends org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.ASTNode
{
    public String name;
    public String getName () {
        return this.name;
    }
    public void setName (String value) {
        this.name = value; 
    }

    public int lineNumber;
    public int getLineNumber () {
        return this.lineNumber;
    }

    public boolean isTyped;
    public boolean getIsTyped () {
        return this.isTyped;
    }
    public void setIsTyped (boolean value) {
        this.isTyped = value; 
    }


    public ModelRef(
        String name,
        int lineNumber,
        boolean isTyped)
    {
this.name=name;
this.lineNumber=lineNumber;
this.isTyped=isTyped;
    }
}
