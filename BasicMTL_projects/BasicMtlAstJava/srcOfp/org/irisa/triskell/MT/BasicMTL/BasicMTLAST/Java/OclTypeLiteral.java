/*
 * $Id: OclTypeLiteral.java,v 1.3 2004-04-29 13:38:56 edrezen Exp $
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
import java.util.Map;
import java.lang.Object;

public class OclTypeLiteral 
    extends org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.Literal
{
    protected java.util.Vector typeValue;
    public java.util.Vector getTypeValue () {
        return this.typeValue;
    }
    public void setTypeValue (java.util.Vector value) {
        this.typeValue = value; 
    }


    public OclTypeLiteral(
        java.util.Vector type)
    {
this.typeValue=type;
    }
}
