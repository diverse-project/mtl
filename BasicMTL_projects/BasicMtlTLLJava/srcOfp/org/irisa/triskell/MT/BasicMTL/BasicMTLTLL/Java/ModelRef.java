/*
 * $Id: ModelRef.java,v 1.4 2004-04-29 13:55:37 edrezen Exp $
 * Authors : modelware
 * 
 * Copyright 2004 - INRIA - LGPL license
 */
package org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java;



/**
  * An acces to a model. Used elements (operation, classes, attributes...) in this model are contained as a library does. The difference is that these elements are proxies for the real ones, given when the library is "instaciated" (see Use and ParameterAssociation).
  */
abstract public class ModelRef 
    extends org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.ASTNode
{
    public String name;
    public String getName () {
        return this.name;
    }
    public void setName (String value) {
        this.name = value; 
    }

    public int lineNumber;
    public int getLineNumber () {
        return this.lineNumber;
    }

    public boolean isTyped;
    public boolean getIsTyped () {
        return this.isTyped;
    }
    public void setIsTyped (boolean value) {
        this.isTyped = value; 
    }


    public ModelRef(
        String name,
        int lineNumber,
        boolean isTyped)
    {
this.name=name;
this.lineNumber=lineNumber;
this.isTyped=isTyped;
    }
}
