/*
* $Id: buildprojectAction.java,v 1.6 2004-06-24 09:23:23 sdzale Exp $
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
import org.eclipse.core.runtime.QualifiedName;
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
import org.inria.mtl.plugin.views.MTLConsole;


public class buildprojectAction implements IWorkbenchWindowActionDelegate {
	private IWorkbenchWindow window;
	private StructuredSelection currentSelection = null;
	private ISelection selection=null;
	private IProject currentProject = null;
	private IFolder srcFolder=null;
	
	private boolean cleanconsole=true; 

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
		
//		La compilation est lancée par une action du menu
			  if (!MTLPlugin.MenuAction){
				  MTLPlugin.MenuAction=cleanconsole;
			  }else{
				  MTLConsole.cleanConsole();
			  }
		
		try{
			System.out.println("Build project 1:");	
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
			System.out.println(E.getMessage());
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
