/*
 * $Id: UserClass.java,v 1.5 2004-11-03 09:11:21 jpthibau Exp $
 * Authors : modelware
 * 
 * Copyright 2004 - INRIA - LGPL license
 */
package org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java;



/**
  * The usual concept of class; this is the stakeholder for attributes and operations. Its name is deterministic within the library. It may extends other classes.
  */
public class UserClass 
    extends org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.UserDefinedClass
{

    public UserClass(
        String name,
        String mangle,
        int lineNumber)
    {
super(name,mangle,lineNumber);
    }

    public boolean isAbstract;
    public boolean getIsAbstract () {
        return this.isAbstract;
    }
    public void setIsAbstract (boolean value) {
        this.isAbstract = value; 
    }

}
