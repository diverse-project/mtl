package org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java;

import org.irisa.triskell.MT.visitors.Java.GenericVisitor.*;
import org.irisa.triskell.MT.visitors.Java.AnalysingVisitor.*;
import java.util.*;
import org.irisa.triskell.MT.visitors.Java.GenericVisitor.Visitor;
import org.irisa.triskell.MT.visitors.Java.AnalysingVisitor.Property;
import java.util.Map;
import java.lang.Object;

/**
  * The operation to change the value of an attribute. Its name must be "set_" + <the attribute name>. If this operation is not explicitly defined, it is automatically generated.
  */
public class AttributeSetter 
    extends org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.Operation
{

    public AttributeSetter(
        String name)
    {
        super(name);
    }
}
