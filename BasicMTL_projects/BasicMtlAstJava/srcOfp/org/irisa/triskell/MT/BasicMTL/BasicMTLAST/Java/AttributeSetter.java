/*
 * $Id: AttributeSetter.java,v 1.3 2004-04-29 13:38:54 edrezen Exp $
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
