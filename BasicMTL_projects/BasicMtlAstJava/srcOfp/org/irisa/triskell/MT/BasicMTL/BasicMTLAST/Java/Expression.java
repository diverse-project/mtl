/*
 * $Id: Expression.java,v 1.3 2004-04-29 13:38:52 edrezen Exp $
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
  * An instruction representing a precise value, either computed or already known.
  */
abstract public class Expression 
    extends org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.Instruction
{
}
