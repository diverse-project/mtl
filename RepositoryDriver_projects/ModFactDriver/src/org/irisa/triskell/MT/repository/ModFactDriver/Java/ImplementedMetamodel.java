/*
 * $Id: ImplementedMetamodel.java,v 1.2 2004-02-16 15:46:36 dvojtise Exp $
 * Authors : ffondeme xblanc dvojtise
 * 
 * Copyright 2004 - INRIA - LGPL license
 */
package org.irisa.triskell.MT.repository.ModFactDriver.Java;

import javax.jmi.reflect.*;

public class ImplementedMetamodel 
    extends org.irisa.triskell.MT.repository.genericJMIDriver.Metamodel
{

    public javax.jmi.reflect.RefPackage getRefPackage(
        org.irisa.triskell.MT.repository.genericJMIDriver.JMIAPI api)
        throws java.lang.Exception
    {
			api.getLog().debug("Retreiving repository " + api.getModelName());
			RefPackage ret = ((ModFactAPI)api).getModfactRepository().getExtent(api.getModelName());
			if (ret == null)
				throw new Exception("Cannot find model " + api.getModelName() + '.');
        	return ret;
    }
}
