/* $Id: EMFDriver.java,v 1.1 2004-02-27 08:30:52 jpthibau Exp $
 * Authors : 
 * 
 * Copyright 2003 - INRIA - LGPL license
 */
package org.inria.EMFDriver;

import java.util.Hashtable;

import org.eclipse.emf.edit.domain.EditingDomain;


import org.eclipse.emf.common.util.URI;

import org.eclipse.emf.ecore.resource.Resource;

import org.eclipse.emf.common.util.TreeIterator;

import java.util.Iterator;
import java.util.Collection;
import java.util.ArrayList;

import org.eclipse.emf.edit.command.CreateChildCommand;

/**
 * @author jpthibau
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class EMFDriver {
	
	// the editing domains registered to manage models with specific fileExetension
	private static Hashtable editingDomainsTable = new Hashtable();
	
	//model(s) under reading/transformation are registered in the modelsTable
	private static Hashtable modelsTable = new Hashtable();
	
	public static void addEditingDomain(String fileExtension,EditingDomain ed){
		editingDomainsTable.put(fileExtension,ed);
	}

	public static EMFAPI getModel(String modelName) {
		return null;	
	}
	
	public static EMFAPI createModel(String modelName,
									String modelFileExtension,
									String modelXmiInputFileName)
									 throws Exception {
		return null;
	}
		
	public static EMFAPI loadModelFromXMI(String modelName,
									String modelFileExtension,
									String modelXmiInputFileName)
									 throws Exception {
		return null;
	}
	
	public static void saveModelToXMI(String modelName)
									 throws Exception {
	}

	public static void saveModelAsXMI(String modelName,
									String modelXmiOutputFileName)
									 throws Exception {
	}
	
	public static void saveAllModelsToXMI()
			 throws Exception {
	}
	
	public static org.irisa.triskell.MT.DataTypes.Java.CollectionValue dispose() {
		return null;
	}

	public static void runTransformation() {
/*		//Application.start(this);
		Resource resource = null;
		try {
			// Load the resource through the editing domain.
			//
			resource = 
				editingDomain.loadResource
//					(URI.createFileURI("c:/ECLIPSEEMFDRIVER/eclipse/workspace/library/src/model/library.ecore").toString());
			(URI.createPlatformResourceURI("platform:/eclipse/workspace/library/src/model/library.ecore").toString());
		}
		catch (Exception exception) {
			System.out.println("LOAD exception:"+exception);
		}
		System.out.println("LOADED");
		TreeIterator itmoe = resource.getAllContents();
		while (itmoe.hasNext()){
			Object memoe = itmoe.next();
			System.out.println(memoe);
			Collection selection=new ArrayList();
			selection.add(memoe);
			Collection newChildDescriptors = editingDomain.getNewChildDescriptors(memoe,null);
			if (newChildDescriptors != null) {
				for (Iterator i = newChildDescriptors.iterator(); i.hasNext(); ) {
					System.out.println("  ==> "+CreateChildCommand.create(editingDomain,memoe,i.next(),selection));
				}
			}
			Iterator properties = editingDomain.getChildren(memoe).iterator();
			while (properties.hasNext())
				System.out.println("***>"+properties.next());
		}*/
	}

}
