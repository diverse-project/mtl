package org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java;

import org.irisa.triskell.MT.visitors.Java.GenericVisitor.*;
import org.irisa.triskell.MT.visitors.Java.AnalysingVisitor.*;
import java.util.*;
import org.irisa.triskell.MT.visitors.Java.GenericVisitor.Visitor;
import org.irisa.triskell.MT.visitors.Java.AnalysingVisitor.Property;
import java.util.Map;
import java.lang.Object;

/**
  * A string value.
  */
public class StringLiteral 
    extends org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.Literal
{
    public String value;
    public String getValue () {
        return this.value;
    }


    public StringLiteral(
        String theString)
    {
		this.value = theString;
    }
}
