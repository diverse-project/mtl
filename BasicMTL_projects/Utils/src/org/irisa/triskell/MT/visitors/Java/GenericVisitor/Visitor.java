/*
 * $Id: Visitor.java,v 1.2 2004-02-16 17:12:04 dvojtise Exp $
 * Authors : ffondeme
 * 
 * Copyright 2004 - INRIA - LGPL license
 */
package org.irisa.triskell.MT.visitors.Java.GenericVisitor;

// import java.util.Map;

public interface Visitor 
{

     void visit(
        org.irisa.triskell.MT.visitors.Java.GenericVisitor.Visitable node,
        java.util.Map context);
}
