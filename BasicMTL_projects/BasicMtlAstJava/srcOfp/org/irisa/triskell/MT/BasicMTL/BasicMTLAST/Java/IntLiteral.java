/*
 * $Id: IntLiteral.java,v 1.3 2004-04-29 13:38:49 edrezen Exp $
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
  * An integer value.
  */
public class IntLiteral 
    extends org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.Literal
{
    public int value;
    public int getValue () {
        return this.value;
    }


    public IntLiteral(
        int theInt)
    {
		this.value = theInt;
    }
}
