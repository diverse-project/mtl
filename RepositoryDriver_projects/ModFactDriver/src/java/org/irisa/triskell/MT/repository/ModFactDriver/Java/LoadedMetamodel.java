/*
 * $Id: LoadedMetamodel.java,v 1.1 2004-10-25 13:57:13 dvojtise Exp $
 * Authors : ffondeme xblanc dvojtise
 * 
 * Copyright 2004 - INRIA - LGPL license
 */
package org.irisa.triskell.MT.repository.ModFactDriver.Java;

// import javax.jmi.xmi.*;
import javax.jmi.reflect.*;
import javax.jmi.model.*;
//import org.irisa.triskell.MT.repository.API.Java.*;
import org.irisa.triskell.MT.DataTypes.Java.*;
//import org.netbeans.api.mdr.*;
//import java.util.Map;
//import java.util.ArrayList;
//import java.util.Collection;
//import org.irisa.triskell.MT.DataTypes.Java.*;
//import org.irisa.triskell.MT.DataTypes.Java.Value;
//
//import javax.jmi.xmi.*;
//import javax.jmi.xmi.XmiReader;
//import javax.jmi.xmi.XmiWriter;
//import org.netbeans.api.mdr.*;
//import org.netbeans.api.mdr.MDRManager;
//import org.netbeans.api.mdr.MDRepository;
//import javax.jmi.reflect.*;
import javax.jmi.reflect.RefPackage;
//import org.irisa.triskell.MT.repository.API.Java.*;
//import org.irisa.triskell.MT.repository.API.Java.MetaAssociation;
//import org.irisa.triskell.MT.repository.API.Java.MetaAssociationEnd;
//import org.irisa.triskell.MT.repository.API.Java.MetaFeature;
//import org.irisa.triskell.MT.repository.API.Java.MetaAttribute;
//import org.irisa.triskell.MT.repository.API.Java.MetaOperation;
//import org.irisa.triskell.MT.repository.API.Java.ModelRole;
//import org.irisa.triskell.MT.repository.API.Java.ModelElement;
//import org.irisa.triskell.MT.repository.genericJMIDriver.*;
import org.irisa.triskell.MT.utils.Java.AWK;
//import org.apache.log4j.Logger;
//import javax.jmi.model.*;
import javax.jmi.model.MofPackage;

public class LoadedMetamodel 
    extends org.irisa.triskell.MT.repository.genericJMIDriver.Metamodel
{
    public final String metamodelPackageName;
    public String getMetamodelPackageName () {
        return this.metamodelPackageName;
    }

    public String[] rootPackage = null;
    public String getRootPackage (int i) {
        return this.rootPackage[i];
    }
    public String[] getRootPackage () {
        return this.rootPackage;
    }
    public int cardRootPackage () {
        return this.rootPackage.length;
    }


    public LoadedMetamodel(
        String metamodelPackageName,
        String[] rootPackage)
    {
			this.metamodelPackageName = metamodelPackageName;
			this.rootPackage = rootPackage;
    }

    public javax.jmi.reflect.RefPackage getRefPackage(
        org.irisa.triskell.MT.repository.genericJMIDriver.JMIAPI api)
        throws java.lang.Exception
    {
		api.getLog().debug("Retreiving metamodel repository " + this.getMetamodelPackageName() + '.');
		RefPackage metamodelModel = ((ModFactAPI)api).getModfactRepository().getExtent(this.getMetamodelPackageName());
		if (metamodelModel == null)
			throw new Exception("The model " + this.getMetamodelPackageName() + " does not exists.");
		if (! (metamodelModel instanceof ModelPackage))
			throw new Exception("The model " + this.getMetamodelPackageName() + " is not instance of MOF.");
		
		String qualifiedName = AWK.merge(this.getRootPackage(), Type.PackageIndirection);
		api.getLog().debug("Retreiving root package " + this.getRootPackage() + " for metamodel in repository " + qualifiedName.toString() + '.');
		java.util.List rootQualifiedName = java.util.Arrays.asList(this.getRootPackage());
		java.util.Iterator packageIt = metamodelModel.refClass("Package").refAllOfType().iterator();
		RefObject o, metaPackageToInstanciate = null;
		
		while (packageIt.hasNext() && (metaPackageToInstanciate == null)) {
			 o = (RefObject)packageIt.next();
			 if (o.refGetValue("qualifiedName").equals(rootQualifiedName))
			metaPackageToInstanciate = o;
		}
		if (! (metaPackageToInstanciate instanceof MofPackage)) {
			if (metaPackageToInstanciate == null)
				throw new Exception("The package " + this.getRootPackage() + " in the model " + this.getRootPackage() + this.getMetamodelPackageName() + " does not exists.");
			else
				throw new Exception("The package " + this.getRootPackage() + " in the model " + this.getRootPackage() + this.getMetamodelPackageName() + " is not a MOF model.");
		}
		
		api.getLog().debug("Instanciating metamodel from repository " + this.getMetamodelPackageName() + " to repository " + api.getModelName() + " with root package " + this.getRootPackage());
		return ((ModFactAPI)api).getModfactRepository().createExtent(api.getModelName(), (MofPackage)metaPackageToInstanciate);
    }
}
