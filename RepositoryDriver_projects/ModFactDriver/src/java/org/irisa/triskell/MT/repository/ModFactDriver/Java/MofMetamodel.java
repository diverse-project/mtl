/*
 * $Id: MofMetamodel.java,v 1.1 2004-10-25 13:57:12 dvojtise Exp $
 * Authors : ffondeme xblanc dvojtise
 * 
 * Copyright 2004 - INRIA - LGPL license
 */
package org.irisa.triskell.MT.repository.ModFactDriver.Java;

//import java.util.*;
//import javax.jmi.xmi.*;
//import javax.jmi.reflect.*;
//import org.irisa.triskell.MT.DataTypes.Java.*;
//import org.irisa.triskell.MT.DataTypes.Java.defaultImpl.*;
//import javax.jmi.model.*;
//import java.lang.*;
//import org.apache.log4j.*;
//import org.irisa.triskell.MT.repository.API.Java.*;

public class MofMetamodel 
    extends org.irisa.triskell.MT.repository.genericJMIDriver.Metamodel
{
    protected static final org.irisa.triskell.MT.repository.ModFactDriver.Java.MofMetamodel TheInstance = new MofMetamodel();
    public static org.irisa.triskell.MT.repository.ModFactDriver.Java.MofMetamodel getTheInstance () {
        return MofMetamodel.TheInstance;
    }
    public static int cardTheInstance () {
        if ( MofMetamodel.TheInstance == null ) return 0;
        else return 1;
    }


    protected MofMetamodel()
    {
    }

    public javax.jmi.reflect.RefPackage getRefPackage(
        org.irisa.triskell.MT.repository.genericJMIDriver.JMIAPI api)
        throws java.lang.Exception
    {
		api.getLog().debug("Instanciating MOF repository for model " + api.getModelName());
			return ((ModFactAPI)api).getModfactRepository().createExtent(api.getModelName());
    }
}
