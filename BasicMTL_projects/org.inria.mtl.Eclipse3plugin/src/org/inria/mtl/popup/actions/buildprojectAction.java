/*
* $Id: buildprojectAction.java,v 1.1 2004-07-30 14:08:43 sdzale Exp $
* Authors : ${user}
*
* Created on ${date}
* Copyright 2004 - INRIA - LGPL license
*/ 
package org.inria.mtl.popup.actions;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.QualifiedName;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;
import org.inria.mtl.MTLPlugin;
import org.inria.mtl.builders.MTLModel;
import org.inria.mtl.core.MTLCore;


public class buildprojectAction implements IObjectActionDelegate {
	private StructuredSelection currentSelection = null;
	private IProject currentProject = null;
	private IFolder srcFolder=null;
	private ISelection selection=null;

	/**
	 * Constructor for buildprojectAction.
	 */
	public buildprojectAction() {
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
		IWorkspaceRoot workspaceRoot = ResourcesPlugin.getWorkspace().getRoot();
		IProject[] projects =workspaceRoot.getProjects();
		
		try{
			
	    if (selection instanceof StructuredSelection)
				{
					currentSelection = (StructuredSelection)selection;
					java.util.Iterator it = currentSelection.iterator();
					while (it.hasNext())
						{
						
							// if (it instanceof IResource){
						   //	System.out.println("Build project 2:");
								 IResource item = (IResource) it.next ();
								   if (item instanceof IProject){
						   //			System.out.println("Build project 3:");
									   currentProject=item.getProject();
									   MTLCore.setProject(currentProject);
									   MTLPlugin.instance().getModel(currentProject).setProject(currentProject);
									   MTLCore.loadMtlClasspath();
									   IPath[] srcPaths=MTLModel.srcFolders;
									   for (int i =0;i<srcPaths.length;i++){
										   IFolder srcFolder= currentProject.getFolder(srcPaths[i].removeFirstSegments(1));
									
										   long oldGen = srcFolder.getModificationStamp();
										   String newGen=((oldGen==100)?new Long(oldGen-1).toString():new Long(oldGen+1).toString());
										   srcFolder.setPersistentProperty(new QualifiedName(MTLPlugin.PLUGIN_ID, MTLModel.TLL_LASTGENTIME), newGen);
										   boolean b=MTLPlugin.instance().getModel(currentProject).processResource(srcFolder);
									
									   }
							  
									//}
								 }else{
								   it.next();
								 }
						}
						}
		}catch (Exception E){
			//??????????????? lié au setPersitentProperty
		}
		
		
	}

	/**
	 * @see IActionDelegate#selectionChanged(IAction, ISelection)
	 */
	public void selectionChanged(IAction action, ISelection selection) {
		this.selection=selection;
			
	}

}
