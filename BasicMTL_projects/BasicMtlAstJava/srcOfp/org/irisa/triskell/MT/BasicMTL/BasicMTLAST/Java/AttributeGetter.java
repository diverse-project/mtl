package org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java;

import org.irisa.triskell.MT.visitors.Java.GenericVisitor.*;
import org.irisa.triskell.MT.visitors.Java.AnalysingVisitor.*;
import java.util.*;
import org.irisa.triskell.MT.visitors.Java.GenericVisitor.Visitor;
import org.irisa.triskell.MT.visitors.Java.AnalysingVisitor.Property;

/**
  * The operation to access the value of an attribute. Its name must be "get_" + <the attribute name>. If this operation is not explicitly defined, it is automatically generated.
  */
public class AttributeGetter 
    extends org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.Operation
{

    public AttributeGetter(
        String name)
    {
        super(name);
    }
}