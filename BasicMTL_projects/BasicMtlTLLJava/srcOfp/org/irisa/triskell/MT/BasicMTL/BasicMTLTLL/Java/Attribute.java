/*
 * $Id: Attribute.java,v 1.6 2004-04-29 13:55:33 edrezen Exp $
 * Authors : modelware
 * 
 * Copyright 2004 - INRIA - LGPL license
 */
package org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java;



/**
  * The usual concept of attribute. It is defined within a class and has a type. Its name is deterministic within the class.
  * The access to an attribute is realized by accessor operations (AttributeGetter and AttributeSetter).
  */
public class Attribute 
    extends org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.Feature
{
    public org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.Operation getter;
    public org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.Operation getGetter () {
        return this.getter;
    }
    public void setGetter (org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.Operation value) {
        this.getter = value; 
    }
    public int cardGetter () {
        if ( this.getter == null ) return 0;
        else return 1;
    }

    public org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.Operation setter;
    public org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.Operation getSetter () {
        return this.setter;
    }
    public void setSetter (org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.Operation value) {
        this.setter = value; 
    }
    public int cardSetter () {
        if ( this.setter == null ) return 0;
        else return 1;
    }


    public Attribute(
        String name,
        String mangle,
        int lineNumber)
    {
super(name,mangle,lineNumber);
    }
}
