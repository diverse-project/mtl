/*
* $Id: buildAllAction.java,v 1.3 2004-05-19 09:22:44 sdzale Exp $
* Authors : ${user}
*
* Created on ${date}
* Copyright 2004 - INRIA - LGPL license
*/ 
package org.inria.mtl.plugin.popup.actions;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;
import org.inria.mtl.plugin.MTLPlugin;
import org.inria.mtl.plugin.builders.MTLModel;
import org.inria.mtl.plugin.core.MTLCore;
import org.inria.mtl.plugin.preferences.PreferenceConstants;


public class buildAllAction implements IObjectActionDelegate {
	private StructuredSelection currentSelection = null;
	private IProject currentProject = null;
	private IFolder srcFolder=null; 
	private IPreferenceStore store;
	private boolean auto_build;

	/**
	 * Constructor for buildAllAction.
	 */
	public buildAllAction() {
		super();
		store=MTLPlugin.getDefault().getPreferenceStore();
		auto_build=store.getBoolean(PreferenceConstants.AUTO_COMPILE);
		System.out.println("auto build :"+auto_build);
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
		
		
	}

	/**
	 * @see IActionDelegate#selectionChanged(IAction, ISelection)
	 */
	public void selectionChanged(IAction action, ISelection selection) {
		IWorkspaceRoot workspaceRoot = ResourcesPlugin.getWorkspace().getRoot();
		IProject[] projects =workspaceRoot.getProjects();
		if (selection instanceof StructuredSelection)
			{
				currentSelection = (StructuredSelection)selection;
				java.util.Iterator it = currentSelection.iterator();
				while (it.hasNext())
					{
							IResource item = (IResource) it.next ();
							if (item instanceof IProject){
								currentProject=item.getProject();
								MTLPlugin.instance().getModel(currentProject).setProject(currentProject);
								MTLCore.findFolders();
								IPath[] srcPaths=MTLModel.srcFolders;
								for (int i =0;i<srcPaths.length;i++){
									boolean b=MTLPlugin.instance().getModel(currentProject).processResource((IFolder)srcPaths[i]);
									
								}
							  
							 }
										

											}
						}
		
	}
	
   

}
