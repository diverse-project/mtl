/*
* $Id: buildprojectAction.java,v 1.4 2004-06-15 15:13:49 sdzale Exp $
* Authors : ${user}
*
* Created on ${date}
* Copyright 2004 - INRIA - LGPL license
*/ 
package org.inria.mtl.plugin.actions;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;
import org.inria.mtl.plugin.MTLPlugin;
import org.inria.mtl.plugin.builders.MTLModel;
import org.inria.mtl.plugin.core.MTLCore;


public class buildprojectAction implements IWorkbenchWindowActionDelegate {
	private IWorkbenchWindow window;
	private StructuredSelection currentSelection = null;
	private ISelection selection=null;
	private IProject currentProject = null;
	private IFolder srcFolder=null;

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
		
		if (selection instanceof StructuredSelection)
			{
				currentSelection = (StructuredSelection)selection;
				java.util.Iterator it = currentSelection.iterator();
				while (it.hasNext())
					{
					  if (it instanceof IResource){
						  IResource item = (IResource) it.next ();
							if (item instanceof IProject){
								System.out.println(item.toString());
							    currentProject=item.getProject();
								MTLCore.setProject(currentProject);
								MTLPlugin.instance().getModel(currentProject).setProject(currentProject);
								MTLCore.findFolders();
								IPath[] srcPaths=MTLModel.srcFolders;
								for (int i =0;i<srcPaths.length;i++){
									IFolder srcFolder= currentProject.getFolder(srcPaths[i]);
									boolean b=MTLPlugin.instance().getModel(currentProject).processResource(srcFolder);
									
								}
							  
							 }
						  }
						}
					}
		

	}

	/**
	 * @see IActionDelegate#selectionChanged(IAction, ISelection)
	 */
	public void selectionChanged(IAction action, ISelection selection) {
		this.selection = selection;
	
	}
	
	/**
		 * We can use this method to dispose of any system
		 * resources we previously allocated.
		 * @see IWorkbenchWindowActionDelegate#dispose
		 */
		public void dispose() {
		}

		/**
		 * We will cache window object in order to
		 * be able to provide parent shell for the message dialog.
		 * @see IWorkbenchWindowActionDelegate#init
		 */
		public void init(IWorkbenchWindow window) {
			this.window = window;
		}

}
