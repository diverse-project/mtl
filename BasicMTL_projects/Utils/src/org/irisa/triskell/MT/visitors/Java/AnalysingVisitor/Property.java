/*
 * $Id: Property.java,v 1.2 2004-02-16 17:12:05 dvojtise Exp $
 * Authors : ffondeme
 * 
 * Copyright 2004 - INRIA - LGPL license
 */
package org.irisa.triskell.MT.visitors.Java.AnalysingVisitor;

//import java.util.*;
//import org.irisa.triskell.MT.visitors.Java.GenericVisitor.*;
//import java.lang.*;
//import java.io.*;
//import org.irisa.triskell.MT.visitors.Java.GenericVisitor.Visitable;
//import org.irisa.triskell.MT.visitors.Java.GenericVisitor.Visitor;
//import java.lang.Class;
//import java.util.Map;

public class Property 
    implements java.io.Serializable
{
    public final String name;
    public String getName () {
        return this.name;
    }

    protected java.lang.Object value;
    public java.lang.Object getValue () {
        return this.value;
    }
    public void setValue (java.lang.Object value) {
        this.value = value; 
    }
    public int cardValue () {
        if ( this.value == null ) return 0;
        else return 1;
    }

    protected String tagType;
    public String getTagType () {
        return this.tagType;
    }
    public void setTagType (String value) {
        this.tagType = value; 
    }


    public Property(
        String name,
        java.lang.Object value,
        String tagType)
    {
this.name=name;
this.value=value;
    }
}
