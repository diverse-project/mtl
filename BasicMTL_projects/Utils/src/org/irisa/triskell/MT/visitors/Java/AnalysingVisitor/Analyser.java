/*
 * $Id: Analyser.java,v 1.2 2004-02-16 17:12:06 dvojtise Exp $
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

abstract public class Analyser 
{
    protected java.lang.Class nodeType;
    public java.lang.Class getNodeType () {
        return this.nodeType;
    }
    public int cardNodeType () {
        if ( this.nodeType == null ) return 0;
        else return 1;
    }


    public Analyser(
        java.lang.Class nodeType)
    {
	this.nodeType = nodeType;
    }

    public abstract void analyse(
        org.irisa.triskell.MT.visitors.Java.GenericVisitor.Visitable node,
        org.irisa.triskell.MT.visitors.Java.GenericVisitor.Visitor visitor,
        java.util.Map context);
}
