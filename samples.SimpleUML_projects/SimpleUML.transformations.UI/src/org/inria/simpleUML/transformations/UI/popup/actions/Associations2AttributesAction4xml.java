/* $Id: Associations2AttributesAction4xml.java,v 1.1 2004-08-19 10:18:45 dvojtise Exp $
 * Authors : dvojtise
 * Created on 01/08/2004
 * Copyright 2003 - INRIA - LGPL license
 */
package org.inria.simpleUML.transformations.UI.popup.actions;


//import java.util.Map;


import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.ui.plugin.*;
//import org.eclipse.core.runtime.QualifiedName;
import SimpleUmlTransformationsWithModelLoader.BMTLLib_SimpleUmlTransformationsWithModelLoader;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;
import org.inria.BasicMTL.runtime.JarClassLoader;
import org.inria.BasicMTL.runtime.RuntimePlugin;
import org.inria.simpleUML.transformations.UI.UIPlugin;
import org.irisa.triskell.MT.repository.MDRDriver.Java.MDRAPI;
import org.irisa.triskell.MT.repository.MDRDriver.Java.SimpleStandaloneModelManager;


import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.core.runtime.ILibrary;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IPluginDescriptor;
import org.inria.simpleUML.transformations.UI.dialogs.*;



public class Associations2AttributesAction4xml implements IObjectActionDelegate {
	private StructuredSelection currentSelection = null;
	private IProject currentProject = null;
	private org.eclipse.core.resources.IFile selectedFile=null;
	private ISelection selection=null;

	/**
	 * Constructor for Associations2AttributesAction4xml.
	 */
	public Associations2AttributesAction4xml() {
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
	//	JarClassLoader truc;
		
		anIFile = getIFileFromSelection();	
		sourceFileName = anIFile.getLocation().toString();
		
		XMLDriverChoice mydialog = new XMLDriverChoice(
				sourceFileName,
				sourceFileName,
				"SimpleUML transformations UI Plug-in",
				"Remove all the associations and replace them by a pair of attributes",
				shell);
		if ( mydialog.open() == IDialogConstants.OK_ID)
		{
			//RUN THE APPROPRIATE APPLICATION BY CALLING ITS ENTRYPOINT METHOD
			try {
				Associations2AttributesActions actionClass;
				actionClass = new Associations2AttributesActions(UIPlugin.getDefault());
			//	UIPlugin.getDefault().showBundleContent();
				if(mydialog.isMDR())
				{
					actionClass.transformSimpleUML_XML(sourceFileName,
							mydialog.getDestFileName(),
							"MDR");
				}
				else if (mydialog.isModFact())
				{
					actionClass.transformSimpleUML_XML(sourceFileName,
							mydialog.getDestFileName(),
							"ModFact");
				}
				else
				{
					MessageDialog.openError(
							shell,
							"SimpleUML transformations UI Plug-in",
							"Error: neither MDR nor ModFact was selected");
				}
			}
			catch (Throwable e) {System.out.println("Application terminated with  exception :"+e+"\n"+e.getMessage());
								 e.printStackTrace();}
	
			
			
			// Confirm end of transformation
			MessageDialog.openInformation(
					shell,
					"SimpleUML transformations UI Plug-in",
					"Associations2AttributesAction4xml was executed on \n" + anIFile.getLocation().toString() + anIFile.getName());
		}
		else
		{
			MessageDialog.openInformation(
					shell,
					"SimpleUML transformations UI Plug-in",
					"Action cancelled");
		
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
/*	private void  showRuntimeLibraries()
	{
		String ret;
		org.eclipse.core.runtime.IPluginDescriptor pd = UIPlugin.getDefault().getDescriptor();
		java.net.URL url = pd.getInstallURL();
		String urlString = url.toString();
		org.eclipse.core.runtime.ILibrary[] libraries = pd.getRuntimeLibraries();
		for (int i = 0; i < libraries.length; i++) {
			org.eclipse.core.runtime.ILibrary iLibrary = libraries[i];
			org.eclipse.core.runtime.IPath libPath = iLibrary.getPath();
			String libPathStr = libPath.toString();
			String libUrlStr = urlString + libPathStr;
			System.out.println(" " + libUrlStr );
		}
		
	}
	*/
	/*private ClassLoader fBasicMTLRuntimeLoader;
	private ClassLoader getBasicMTLRuntimeClassLoader() {
		try {
			if (fBasicMTLRuntimeLoader == null) {
				// Use JarClassLoader to force Xerces 2 classes to be loaded locally
				IPluginDescriptor pd = org.inria.BasicMTL.runtime.RuntimePlugin.getDefault().getDescriptor();
	 			URL url = pd.getInstallURL();
	 			String urlString = url.toString();
	 			ILibrary[] libraries = pd.getRuntimeLibraries();
				LinkedList list = new LinkedList();
	 			for (int i = 0; i < libraries.length; i++) {
					ILibrary iLibrary = libraries[i];
					IPath libPath = iLibrary.getPath();
					String libPathStr = libPath.toString();
					String libUrlStr = urlString + libPathStr;
					URL libUrl = new URL(libUrlStr);
					list.add(libUrl);
					System.out.println(" new classloader with: "+libUrlStr);
				}
	 			URL[] libUrls = (URL[]) list.toArray(new URL[list.size()]);
	 			fBasicMTLRuntimeLoader = new JarClassLoader(libUrls, getClass().getClassLoader());
			}
			return fBasicMTLRuntimeLoader;
		} catch (MalformedURLException mue) {
			//??? log error
			mue.printStackTrace();
		}
		return null;
	}
*/
	
}
