/* $Id: EditingDomainProvider.java,v 1.2 2004-06-23 15:14:36 dvojtise Exp $
 * Authors : 
 * 
 * Copyright 2003 - INRIA - LGPL license
 */
package org.inria.EMFDriver;

import org.eclipse.emf.common.command.BasicCommandStack;
import org.eclipse.emf.common.notify.AdapterFactory;

import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.domain.IEditingDomainProvider;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.resource.ResourceItemProviderAdapterFactory;

import org.eclipse.emf.common.util.URI;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.*;

import java.util.ArrayList;
import java.util.List;
//import java.util.Map;

import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;

/**
 * @author jpthibau
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class EditingDomainProvider
	implements IEditingDomainProvider {
	/**
	 * This keeps track of the editing domain that is used to track all changes to the model.
	 */
	protected AdapterFactoryEditingDomain editingDomain;

	/**
	 * This is the one adapter factory used for providing views of the model.
	 */
	protected ComposedAdapterFactory adapterFactory;
	
	/* FileExtension of models files taht may be read/written
	 * 
	 */
	private String FileExtension;

	/* root Element : owner for all possible created subElements
	 * generally the EPackage that defines the model and my contain all its elements
	 */
	private EObject rootElement;

	/**
	 * This creates a model editing domain.
	 */
	public EditingDomainProvider(String fileExtension,AdapterFactory adequateFactory) {

		this.FileExtension = fileExtension;
		// Create an adapter factory that yields item providers.
		//
		List factories = new ArrayList();
		factories.add(new ResourceItemProviderAdapterFactory());
		factories.add(adequateFactory);

		adapterFactory = new ComposedAdapterFactory(factories);

		// Create the command stack that will notify this editor as commands are executed.
		//
		BasicCommandStack commandStack = new BasicCommandStack();

		// Create the editing domain with a special command stack.
		//
		editingDomain = new AdapterFactoryEditingDomain(adapterFactory, commandStack);
		
		//Put the XMIFactory to read/write ressources of the whose filenames have the specific extension(for instance ".ecore")
		Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put(fileExtension, new XMIResourceFactoryImpl());
	}
	/**
	 * This returns the editing domain as required by the {@link IEditingDomainProvider} interface.
	 * This is important for implementing the static methods of {@link AdapterFactoryEditingDomain}	
	 * and for supporting {@link org.eclipse.emf.edit.ui.action.CommandAction}.
	 */
	public EditingDomain getEditingDomain() {
		return editingDomain;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AdapterFactory getAdapterFactory() {
		return adapterFactory;
	}

	public void setRootElement(EObject rootElement) {
		//Set the rootElement
		 this.rootElement = rootElement;
	}
	//create a new resource having the root element
	//the root element needs to be added to the resource in order to allow children creation
	public Resource getANewResource(String fileName) {
		Resource resource = null;
		resource = editingDomain.createResource (URI.createFileURI(fileName).toString());
		resource.getContents().add(this.rootElement);
		return resource;
	}
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void dispose() {
		Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().remove(this.FileExtension);
		adapterFactory.dispose();
	}

}
