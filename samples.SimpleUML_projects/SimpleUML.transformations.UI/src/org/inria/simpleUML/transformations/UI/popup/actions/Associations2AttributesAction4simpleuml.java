/* $Id: Associations2AttributesAction4simpleuml.java,v 1.3 2004-09-13 12:18:05 dvojtise Exp $
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
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;
import org.inria.EMFDriver.EMFDriver;
import org.inria.EMFDriver.EditingDomainProvider;
import org.inria.simpleUML.transformations.UI.UIPlugin;
import org.inria.simpleUML.transformations.UI.dialogs.*;
import org.irisa.triskell.MT.BasicMTL.DataTypes.impl.BMTLSequence;

public class Associations2AttributesAction4simpleuml implements IObjectActionDelegate {
	private StructuredSelection currentSelection = null;
	private IProject currentProject = null;
	private org.eclipse.core.resources.IFile selectedFile=null;
	private ISelection selection=null;

	/**
	 * Constructor for Associations2AttributesAction4simpleuml.
	 */
	public Associations2AttributesAction4simpleuml() {
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
		String sourceFileName;
		Shell shell = new Shell();
		
		anIFile = getIFileFromSelection();
		sourceFileName = anIFile.getLocation().toString();
		DestFileDialog mydialog = new DestFileDialog(
				sourceFileName,
				sourceFileName,
				"SimpleUML transformations UI Plug-in",
				"Remove all the associations and replace them by a pair of attributes",
				shell);
		if ( mydialog.open() == IDialogConstants.OK_ID)
		{
			Map registry = EPackage.Registry.INSTANCE;
			/* SimpleUML editing domain creation as provider */
			EditingDomainProvider provider = new EditingDomainProvider("SimpleUML",new SimpleUMLItemProviderAdapterFactory());
	
			String aSimpleUMLPackageURI = SimpleUMLPackage.eNS_URI;
			SimpleUMLPackage aSimpleUMLPackage = (SimpleUMLPackage) registry.get(aSimpleUMLPackageURI);
			SimpleUMLFactory aSimpleUMLFactory = aSimpleUMLPackage.getSimpleUMLFactory();
			provider.setRootElement(aSimpleUMLFactory.createSimpleUmlMM());
	
			//EMFDriver.addEditingDomainProvider("SimpleUmlMM",provider);
			EMFDriver.addEditingDomainProvider("SimpleUML",provider);  // declares this editing domain provider
			/* ========================================================================= */
	
	
			//RUN THE APPROPRIATE APPLICATION BY CALLING ITS ENTRYPOINT METHOD
			Thread cur = Thread.currentThread();
			ClassLoader save = cur.getContextClassLoader();			
			cur.setContextClassLoader(getClass().getClassLoader());		
			try {
				Associations2AttributesActions actionClass;
				actionClass = new Associations2AttributesActions(UIPlugin.getDefault());
				actionClass.transformSimpleUML_EMF(sourceFileName,
						mydialog.getDestFileName());
				
			}
			catch (Throwable e) {System.out.println("Application terminated with  exception :"+e);
								 e.printStackTrace();}
			finally {
				  cur.setContextClassLoader(save);
			}
			
			// free memory
			provider.dispose();
		
			// Confirm end of transformation
			MessageDialog.openInformation(
				shell,
				"SimpleUML transformations UI Plug-in",
				"Associations2AttributesAction4simpleuml was executed on " + sourceFileName );
		}
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
