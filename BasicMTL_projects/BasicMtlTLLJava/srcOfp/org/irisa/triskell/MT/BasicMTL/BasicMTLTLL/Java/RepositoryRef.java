/*
 * $Id: RepositoryRef.java,v 1.4 2004-04-29 13:55:39 edrezen Exp $
 * Authors : modelware
 * 
 * Copyright 2004 - INRIA - LGPL license
 */
package org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java;



/**
  * An acces to a model beside a model repository (see org.irisa.triskell.MT.repository.API).
  */
public class RepositoryRef 
    extends org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.ModelRef
{

    public RepositoryRef(
        String name,
        int lineNumber)
    {
super(name,lineNumber,false);
    }
}
