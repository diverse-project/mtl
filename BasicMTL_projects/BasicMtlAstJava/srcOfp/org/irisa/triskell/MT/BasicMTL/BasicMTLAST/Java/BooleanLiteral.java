/*
 * $Id: BooleanLiteral.java,v 1.3 2004-04-29 13:38:48 edrezen Exp $
 * Authors : modelware
 * 
 * Copyright 2004 - INRIA - LGPL license
 */
package org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java;

import org.irisa.triskell.MT.visitors.Java.GenericVisitor.*;
import org.irisa.triskell.MT.visitors.Java.AnalysingVisitor.*;
import java.util.*;
import org.irisa.triskell.MT.visitors.Java.GenericVisitor.Visitor;
import org.irisa.triskell.MT.visitors.Java.AnalysingVisitor.Property;

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
