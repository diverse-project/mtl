/* $Id: EMFDriver.java,v 1.2 2004-03-08 08:18:19 jpthibau Exp $
 * Authors : 
 * 
 * Copyright 2003 - INRIA - LGPL license
 */
package org.inria.EMFDriver;

import java.io.*;
import org.apache.log4j.Logger;
import org.apache.log4j.LogManager;
import org.apache.log4j.xml.DOMConfigurator;
import org.irisa.triskell.MT.BasicMTL.DataTypes.impl.BMTLSet;
import org.irisa.triskell.MT.BasicMTL.DataTypes.impl.BMTLString;
import org.irisa.triskell.MT.DataTypes.Java.Value;
import org.irisa.triskell.MT.utils.Java.Directories;

import java.util.Hashtable;

import org.eclipse.emf.edit.domain.EditingDomain;


import org.eclipse.emf.common.util.URI;

import org.eclipse.emf.ecore.resource.Resource;

import org.eclipse.emf.common.util.TreeIterator;

import java.util.Iterator;
import java.util.Collections;
import java.util.Collection;
import java.util.ArrayList;
import java.util.Hashtable;

import org.eclipse.emf.edit.command.CreateChildCommand;
import org.eclipse.emf.edit.command.*;
import org.eclipse.emf.common.command.*;
import org.eclipse.emf.ecore.*;

/**
 * @author jpthibau
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class EMFDriver {

	public static final org.apache.log4j.Logger log = Logger.getLogger("EMFDriver");

	
	// the editing domains registered to manage models with specific fileExetension
	private static Hashtable editingDomainsTable = new Hashtable();
	
	//model(s) under reading/transformation are registered in the modelsTable
	private static Hashtable modelsTable = new Hashtable();

/* The below methods are called by the EMFDriver launcher
 * to initialise the driver with some available editing Domains
 */	
	//Constructor : just register a specific logger for this driver
	public EMFDriver() {
		try {
			String filePath = new java.io.File("workspace/org.inria.EMFDriver/log4j_configuration.xml").getCanonicalPath();
			LogManager.resetConfiguration();
			DOMConfigurator.configure(filePath); }
		catch(java.io.IOException e) {
							System.err.println("Can't state log4j in EMFDriver"); }
	}
	
	public static void addEditingDomain(String fileExtension,EditingDomain ed){
		editingDomainsTable.put(fileExtension,ed);
	}

/* These below methods are called by the application that use the EMFDriver.
 * It assumes Editing domains are available for load,creation and use of models
 */
	public static EMFAPI getModel(String modelName) {
		return (EMFAPI)modelsTable.get(modelName);	
	}
	
	public static EMFAPI createModel(String modelName,
									String modelFileExtension,
									String modelXmiOutputFileName)
									 throws Exception {
//		assert modelsTable != null;
		if ((modelName == null) || (modelFileExtension == null) || (modelXmiOutputFileName == null))
			throw new Exception("One of the arguments to createModel has a null value !");
		if (modelsTable.get(modelName) != null)
			throw new Exception("The model \""+modelName+"\" is already managed; dispose this model before to try to create it !");
		log.info("Creating file"+modelXmiOutputFileName+" as model named:"+modelName);
		EditingDomain ed = (EditingDomain)editingDomainsTable.get(modelFileExtension);
		if (ed == null) throw new Exception("No EditingDomain available for \""+modelFileExtension+"\" models extension.");
		Resource resource = null;
				// create the resource through the editing domain.
				//
		resource = ed.createResource(URI.createFileURI(modelXmiOutputFileName).toString());
		if (resource == null) throw new Exception("No resource created to file \""+modelXmiOutputFileName+"\".");
		EMFAPI api = new EMFAPI(modelName,modelFileExtension,modelXmiOutputFileName,ed,resource);
		//register the new model in the managed models Table
		modelsTable.put(modelName,api);
		return api;
	}
	
	//check if the asked model is not already managed;
	// if not load it and return a new EMFAPI to manipulate it.	
	public static EMFAPI loadModelFromXMI(String modelName,
									String modelFileExtension,
									String modelXmiInputFileName)
									 throws Exception {
		if ((modelName == null) || (modelFileExtension == null) || (modelXmiInputFileName == null))
			throw new Exception("One of the arguments to load has a null value !");
		if (modelsTable.get(modelName) != null)
			throw new Exception("The model \""+modelName+"\" is already managed; dispose this model before to try to load it !");
		log.info("Loading file"+modelXmiInputFileName+" as model named:"+modelName);
		EditingDomain ed = (EditingDomain)editingDomainsTable.get(modelFileExtension);
		if (ed == null) throw new Exception("No EditingDomain available for \""+modelFileExtension+"\" models extension.");
		Resource resource = null;
			// Load the resource through the editing domain.
			//
		resource = ed.loadResource (URI.createFileURI(modelXmiInputFileName).toString());
		if (resource == null) throw new Exception("No resource loaded from file \""+modelXmiInputFileName+"\".");
		EMFAPI api = new EMFAPI(modelName,modelFileExtension,modelXmiInputFileName,ed,resource);
		//register the new model in the managed models Table
		modelsTable.put(modelName,api);
		return api;
	}
	
		//save the model to its related current filename
	public static void saveModelToXMI(String modelName)
									 throws Exception {
		if (modelName == null)
			throw new Exception("The 'modelname' arguments to saveModelToXmi has a null value !");
		EMFAPI api = (EMFAPI)modelsTable.get(modelName);
		if (api == null)
			throw new Exception("The model \""+modelName+"\" is not found in managed models table !");
//		log.info("Saving model named:"+modelName+" ...");
		api.resource.save(Collections.EMPTY_MAP);
	}

	//change the current filename related to the model and call saveToXMI
	// in order to write it on disk. 
	public static void saveModelAsXMI(String modelName,
									String modelXmiOutputFileName)
									 throws Exception {
		if ((modelName == null) || (modelXmiOutputFileName == null))
			throw new Exception("One of the arguments to saveModelAsXmi has a null value !");
		EMFAPI api = (EMFAPI)modelsTable.get(modelName);
		if (api == null)
			throw new Exception("The model \""+modelName+"\" is not found in managed models table !");
//	log.info("Saving model named:"+modelName+"to file"+modelXmiOutputFileName);
		api.resource.setURI(URI.createFileURI(modelXmiOutputFileName));
		EMFDriver.saveModelToXMI(modelName);
		//memorize the resource don't need to be saved
		api.needSave = false;
		}
	
	//for all models registered in the models table, do save them
	public static void saveAllModelsToXMI()
			 throws Exception {
		Iterator modelsIterator = modelsTable.keySet().iterator();
		while (modelsIterator.hasNext())
			EMFDriver.saveModelToXMI((String)modelsIterator.next());	 
	}
	
	//removes the specified model from managed models table
	public static void disposeModel(String modelName)
								 throws Exception {
		if (modelName == null)
			throw new Exception("The 'modelName' arguments to disposeModel has a null value !");
		EMFAPI api = (EMFAPI)modelsTable.get(modelName);
		if (api == null)
			throw new Exception("The model \""+modelName+"\" is not found in managed models table !");
		if (api.needSave) EMFDriver.saveModelToXMI(modelName);
		modelsTable.remove(modelName);
	}

	//removes all saved models from managed models table;
	//return the collection of models that need to be saved
	// and might be disposed when saved.
	public static org.irisa.triskell.MT.DataTypes.Java.CollectionValue dispose()
				throws Exception {
		Collection unsavedModels = new ArrayList();
		Collection disposedModels = new ArrayList();
		Iterator modelsIterator = modelsTable.keySet().iterator();
		while (modelsIterator.hasNext()) {
			String modelName = (String)modelsIterator.next();
			EMFAPI api = (EMFAPI)modelsTable.get(modelName);
			if (api.needSave)
				unsavedModels.add(new BMTLString(modelName).getDelegate());
			else disposedModels.add(modelName);
		}
		modelsIterator = disposedModels.iterator();
		while (modelsIterator.hasNext())
			EMFDriver.disposeModel((String)modelsIterator.next());
		Value [] result = (Value [])unsavedModels.toArray(new Value[unsavedModels.size()]);
		return new BMTLSet(result);
	}

	public static Hashtable collectMetaClasses(EMFAPI api) {
		Hashtable metaClasses = new Hashtable();
		TreeIterator iter = api.resource.getAllContents();
		while (iter.hasNext()) {
			Object modelElt = iter.next();
			Collection selection = new ArrayList();
			selection.add(modelElt);
			Iterator childrenDescrIter = api.editingDomain.getNewChildDescriptors(modelElt,null).iterator();
			while (childrenDescrIter.hasNext()) {
				CommandParameter descriptor = (CommandParameter)childrenDescrIter.next();
				Command cmd = CreateChildCommand.create(api.editingDomain,modelElt,descriptor,selection);
				if (! metaClasses.containsKey(cmd.getDescription()))
					metaClasses.put(cmd.getDescription(),new EMFChildElement(modelElt,descriptor,cmd));
			}
		}
		return metaClasses;
	}
	
	public static void displayModelInformation(String modelName) {
		EMFAPI api = (EMFAPI)modelsTable.get(modelName);
		if (api != null) {
			Hashtable metaClassesReferences = EMFDriver.collectMetaClasses(api);
//			log.info("The following metaclasses may be instantited on model :"+modelName);
			System.out.println("The following metaclasses may be instantited on model :"+modelName);
			Iterator metaClassesIter = metaClassesReferences.keySet().iterator();
			while (metaClassesIter.hasNext()) {
				EMFChildElement elt = (EMFChildElement)metaClassesReferences.get(metaClassesIter.next());
//				log.info("=> "+"=> "+elt.creationCommand.getDescription());
				System.out.println("=> "+elt.creationCommand.getDescription());
				EReference ref = elt.childDescriptor.getEReference();
				System.out.print("metaClass name : "+ref.getContainerClass().getName()+"::");
				if (elt.childDescriptor.getEValue() != null)
					System.out.println(elt.childDescriptor.getEValue().eClass().getName());
				else System.out.println("null");
			}
			metaClassesIter = metaClassesReferences.keySet().iterator();
			while (metaClassesIter.hasNext()) {
				EMFChildElement elt = (EMFChildElement)metaClassesReferences.get(metaClassesIter.next());
				EClass metaClass = elt.childDescriptor.getEReference().getEReferenceType();
//				log.info("metaClass "+aClass.getName()+" has the following attributes :");
				System.out.println("metaClass "+metaClass.getName()+" has the following attributes :");
				Iterator attributesIter = metaClass.getEAllAttributes().iterator();
				while (attributesIter.hasNext())
//					log.info("=> "+((EAttribute)attributesIter.next()).getName());
					System.out.println("=> "+((EAttribute)attributesIter.next()).getName());
			}
		}
	}

}
