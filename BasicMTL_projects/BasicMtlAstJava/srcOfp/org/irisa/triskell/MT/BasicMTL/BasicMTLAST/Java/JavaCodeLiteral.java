/*
 * $Id: JavaCodeLiteral.java,v 1.2 2004-04-29 13:38:55 edrezen Exp $
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
  * A string value.
  */
public class JavaCodeLiteral 
    extends org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.Literal
{
    public String value;
    public String getValue () {
        return this.value;
    }


    public JavaCodeLiteral(
        String theCodeString)
    {
		this.value = theCodeString;
    }
}
