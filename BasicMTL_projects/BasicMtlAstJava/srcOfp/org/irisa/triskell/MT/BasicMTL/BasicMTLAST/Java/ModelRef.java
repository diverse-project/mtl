package org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java;

import org.irisa.triskell.MT.visitors.Java.GenericVisitor.*;
import org.irisa.triskell.MT.visitors.Java.AnalysingVisitor.*;
import java.util.*;
import org.irisa.triskell.MT.visitors.Java.GenericVisitor.Visitor;
import org.irisa.triskell.MT.visitors.Java.AnalysingVisitor.Property;
import java.util.Map;
import java.lang.Object;

/**
  * An acces to a model. Used elements (operation, classes, attributes...) in this model are contained as a library does. The difference is that these elements are proxies for the real ones, given when the library is "instaciated" (see Use and ParameterAssociation).
  */
abstract public class ModelRef 
    extends org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.Library
    implements org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.LibParameter
{
    protected String name;
    public String getName () {
        return this.name;
    }


    public ModelRef(
        String name)
    {
        super(name);
this.name=name;
    }

    public boolean isNative()
    {
return false;
    }
}
