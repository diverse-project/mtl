/*
 * $Id: Attribute.java,v 1.4 2004-04-29 13:38:53 edrezen Exp $
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
  * The usual concept of attribute. It is defined within a class and has a type. Its name is deterministic within the class.
  * The access to an attribute is realized by accessor operations (AttributeGetter and AttributeSetter).
  */
public class Attribute 
    extends org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.ASTNode
{
    protected org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.UserClass type;
    public org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.UserClass getType () {
        return this.type;
    }
    public void setType (org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.UserClass value) {
        this.type = value; 
    }
    public int cardType () {
        if ( this.type == null ) return 0;
        else return 1;
    }

    public String name;
    public String getName () {
        return this.name;
    }

    protected org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.Operation getter;
    public org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.Operation getGetter () {
        return this.getter;
    }
    public void setGetter (org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.Operation value) {
        this.getter = value; 
    }
    public int cardGetter () {
        if ( this.getter == null ) return 0;
        else return 1;
    }

    protected org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.Operation setter;
    public org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.Operation getSetter () {
        return this.setter;
    }
    public void setSetter (org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.Operation value) {
        this.setter = value; 
    }
    public int cardSetter () {
        if ( this.setter == null ) return 0;
        else return 1;
    }


    public Attribute(
        String name)
    {
this.name=name;
    }
}
