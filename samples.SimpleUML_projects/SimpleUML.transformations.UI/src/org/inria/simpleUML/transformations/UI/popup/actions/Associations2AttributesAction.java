/* $Id: Associations2AttributesAction.java,v 1.1 2004-08-10 12:18:03 dvojtise Exp $
 * Authors : dvojtise
 * Created on 01/08/2004
 * Copyright 2003 - INRIA - LGPL license
 */
package org.inria.simpleUML.transformations.UI.popup.actions;


import java.util.Map;


import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
//import org.eclipse.core.runtime.QualifiedName;
import org.eclipse.emf.ecore.EPackage;
//import org.eclipse.emf.ecore.EcoreFactory;
//import org.eclipse.emf.ecore.EcorePackage;
//import org.eclipse.emf.ecore.provider.EcoreItemProviderAdapterFactory;
import SimpleUML.*;
import SimpleUML.provider.SimpleUMLItemProviderAdapterFactory;
import SimpleUmlTransformationsWithModelLoader.BMTLLib_SimpleUmlTransformationsWithModelLoader;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;
import org.inria.EMFDriver.EMFDriver;
import org.inria.EMFDriver.EditingDomainProvider;

public class Associations2AttributesAction implements IObjectActionDelegate {
	private StructuredSelection currentSelection = null;
	private IProject currentProject = null;
	private org.eclipse.core.resources.IFile selectedFile=null;
	private ISelection selection=null;

	/**
	 * Constructor for Associations2AttributesAction.
	 */
	public Associations2AttributesAction() {
		super();
	}

	/**
	 * @see IObjectActionDelegate#setActivePart(IAction, IWorkbenchPart)
	 */
	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
	}

	/**
	 * @see IActionDelegate#run(IAction)
	 */
	public void run(IAction action) {
		IFile anIFile;
		Shell shell = new Shell();
		
		anIFile = getIFileFromSelection();
		
		Map registry = EPackage.Registry.INSTANCE;
		/* SimpleUML editing domain creation as provider */
		EditingDomainProvider provider = new EditingDomainProvider("SimpleUML",new SimpleUMLItemProviderAdapterFactory());

		String aSimpleUMLPackageURI = SimpleUMLPackage.eNS_URI;
		SimpleUMLPackage aSimpleUMLPackage = (SimpleUMLPackage) registry.get(aSimpleUMLPackageURI);
		SimpleUMLFactory aSimpleUMLFactory = aSimpleUMLPackage.getSimpleUMLFactory();
		provider.setRootElement(aSimpleUMLFactory.createSimpleUmlMM());

		EMFDriver.addEditingDomainProvider("SimpleUmlMM",provider);
		/* ========================================================================= */


		//RUN THE APPROPRIATE APPLICATION BY CALLING ITS ENTRYPOINT METHOD
		try {
			String args[] = new String[3];
			args[0] = "anIFile.getLocation().toString() + anIFile.getName()";
			args[1] = "anIFile.getLocation().toString() + anIFile.getName()";
			args[2] = "EMF";
			
			BMTLLib_SimpleUmlTransformationsWithModelLoader.main(args);
		}
		catch (Throwable e) {System.out.println("Application terminated with  exception :"+e);
							 e.printStackTrace();}

		
		// free memory
		provider.dispose();
		
		// Confirm end of transformation
		MessageDialog.openInformation(
				shell,
				"SimpleUML transformations UI Plug-in",
				"Associations2AttributesAction was executed on " + anIFile.getLocation().toString() + anIFile.getName());
	}

	/**
	 * @see IActionDelegate#selectionChanged(IAction, ISelection)
	 */
	public void selectionChanged(IAction action, ISelection selection) {
		this.selection=selection;
	}

	
	
	// util methods
	/**
	 * retreives the IFile from the selection
	 * It is supposed only one selection. (currently returns the last one)
	 * @return IFile
	 */
	public IFile getIFileFromSelection()
	{
		IFile anIFile = null;
		currentSelection = null;
	
		if (selection instanceof StructuredSelection)
		{		
			currentSelection = (StructuredSelection)selection;
			java.util.Iterator it = currentSelection.iterator();					
			while (it.hasNext() )
			{						
				IResource item = (IResource) it.next ();
				if (item instanceof IFile){					
					currentProject=item.getProject();
					anIFile=(IFile)item;											
				}
			}
		}		
		return anIFile;
	}
}
