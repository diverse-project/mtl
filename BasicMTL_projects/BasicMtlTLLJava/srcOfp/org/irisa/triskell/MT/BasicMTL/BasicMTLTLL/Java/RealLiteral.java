package org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java;

import org.irisa.triskell.MT.visitors.Java.GenericVisitor.*;
import org.irisa.triskell.MT.visitors.Java.AnalysingVisitor.*;
import java.util.*;
import org.irisa.triskell.MT.visitors.Java.GenericVisitor.Visitor;
import org.irisa.triskell.MT.visitors.Java.AnalysingVisitor.Property;

/**
  * A real value.
  */
public class RealLiteral 
    extends org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.Literal
{
    public float value;
    public float getValue () {
        return this.value;
    }


    public RealLiteral(
        float theReal)
    {
		this.value = theReal;		
    }
}
