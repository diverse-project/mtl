/*
 * $Id: ImplementedMetamodel.java,v 1.1 2004-10-25 12:32:23 dvojtise Exp $
 * Authors : ffondeme
 * 
 * Copyright 2004 - INRIA - LGPL license
 */
package org.irisa.triskell.MT.repository.MDRDriver.Java;

// import javax.jmi.xmi.*;
import javax.jmi.reflect.*;
//import org.irisa.triskell.MT.DataTypes.Java.*;
//import org.netbeans.api.mdr.*;
//import org.irisa.triskell.MT.DataTypes.Java.defaultImpl.*;
//import javax.jmi.model.*;
//import java.lang.*;
//import org.apache.log4j.*;
//import org.irisa.triskell.MT.repository.API.Java.*;

public class ImplementedMetamodel 
    extends org.irisa.triskell.MT.repository.genericJMIDriver.Metamodel
{

    public javax.jmi.reflect.RefPackage getRefPackage(
        org.irisa.triskell.MT.repository.genericJMIDriver.JMIAPI api)
        throws java.lang.Exception
    {
			api.getLog().debug("Retreiving repository " + api.getModelName());
			RefPackage ret = ((MDRAPI)api).getMdrRepository().getExtent(api.getModelName());
			if (ret == null)
				throw new Exception("Cannot find model " + api.getModelName() + '.');
        	return ret;
    }
}
