/*
* $Id: buildfolderAction.java,v 1.5 2004-06-18 14:20:31 sdzale Exp $
* Authors : ${user}
*
* Created on ${date}
* Copyright 2004 - INRIA - LGPL license
*/ 
package org.inria.mtl.plugin.popup.actions;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.QualifiedName;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;
import org.inria.mtl.plugin.MTLPlugin;
import org.inria.mtl.plugin.builders.MTLModel;

/**
 * @author sdzale
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
/**
 * @author sdzale
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class buildfolderAction implements IObjectActionDelegate {
	private StructuredSelection currentSelection = null;
	private IProject currentProject = null;
	private IFolder srcFolder=null;
	private ISelection selection=null;


	/**
	 * Constructor for buildprojectAction.
	 */
	public buildfolderAction() {
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
		Shell shell = new Shell();
	currentSelection = null;
try{
	if (selection instanceof StructuredSelection)
		{
				
			currentSelection = (StructuredSelection)selection;
			java.util.Iterator it = currentSelection.iterator();
				
			while (it.hasNext() )
			{	
				IResource item = (IResource) it.next ();
				if (item instanceof IFolder){
					currentProject=item.getProject();
					srcFolder=(IFolder)item;
																	
					long oldGen = srcFolder.getModificationStamp();
					//On fait en sorte que le fichier soit obligatoirement compilé
					String newGen=((oldGen==100)?new Long(oldGen-1).toString():new Long(oldGen+1).toString());
					srcFolder.setPersistentProperty(new QualifiedName(MTLPlugin.PLUGIN_ID, MTLModel.TLL_LASTGENTIME), newGen);
					//Remove old resources generated 
							
				}
			}
		}
			boolean i=MTLPlugin.instance().getModel(currentProject).processResource(srcFolder);
	}catch(Exception E){
			System.out.println(E.getMessage());
		}
	}

	/**
	 * @see IActionDelegate#selectionChanged(IAction, ISelection)
	 */
	public void selectionChanged(IAction action, ISelection selection) {
		this.selection=selection;
		}

	
   
}
