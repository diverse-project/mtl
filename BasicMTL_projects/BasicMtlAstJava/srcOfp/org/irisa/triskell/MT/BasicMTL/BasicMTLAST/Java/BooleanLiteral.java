package org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java;

import org.irisa.triskell.MT.visitors.Java.GenericVisitor.*;
import org.irisa.triskell.MT.visitors.Java.AnalysingVisitor.*;
import java.util.*;
import org.irisa.triskell.MT.visitors.Java.GenericVisitor.Visitor;
import org.irisa.triskell.MT.visitors.Java.AnalysingVisitor.Property;
import java.util.Map;
import java.lang.Object;

/**
  * A boolean value.
  */
public class BooleanLiteral 
    extends org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.Literal
{
    public boolean value;
    public boolean getValue () {
        return this.value;
    }


    public BooleanLiteral(
        boolean theBoolean)
    {
		this.value = theBoolean;
    }
}
