package org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java;

import org.irisa.triskell.MT.visitors.Java.GenericVisitor.*;
import org.irisa.triskell.MT.visitors.Java.AnalysingVisitor.*;
import java.util.*;
import org.irisa.triskell.MT.visitors.Java.GenericVisitor.Visitor;
import org.irisa.triskell.MT.visitors.Java.AnalysingVisitor.Property;
import java.util.Map;

/**
  * The usual concept of attribute. It is defined within a class and has a type. Its name is deterministic within the class.
  * The access to an attribute is realized by accessor operations (AttributeGetter and AttributeSetter).
  */
public class Attribute 
    extends org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.Feature
{

    public Attribute(
        String name,
        String mangle,
        int lineNumber)
    {
super(name,mangle,lineNumber);
    }
}
