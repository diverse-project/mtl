/*
* $Id: buildAllAction.java,v 1.2 2004-08-26 12:40:22 sdzale Exp $
* Authors : ${user}
*
* Created on ${date}
* Copyright 2004 - INRIA - LGPL license
*/ 
package org.inria.mtl.popup.actions;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
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
import org.inria.mtl.MTLPlugin;
import org.inria.mtl.builders.MTLModel;
import org.inria.mtl.builders.MTLNature;
import org.inria.mtl.core.MTLCore;
import org.inria.mtl.preferences.PreferencesConstants;
import org.inria.mtl.views.ProjectExploreView;


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
		auto_build=store.getBoolean(PreferencesConstants.AUTO_COMPILE);
		//System.out.println("auto build :"+auto_build);
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
		for (int i=0;i<projects.length;i++){
			try{
			
			//voir comment contrôler cette exécution
				if (projects[i].hasNature(MTLNature.NATURE_ID)){
					System.out.println("build All"+projects[i].getName());
					
					currentProject=projects[i].getProject();
					MTLPlugin.instance().getModel(currentProject).setProject(currentProject);
					MTLCore.loadMtlClasspath();
					IPath[] srcPaths=MTLModel.srcFolders;
					for (int j =0;j<srcPaths.length;j++){
						IFolder srcFolder= currentProject.getFolder(srcPaths[j]);
						long oldGen = srcFolder.getModificationStamp();
						String newGen=((oldGen==100)?new Long(oldGen-1).toString():new Long(oldGen+1).toString());
						boolean b=MTLPlugin.instance().getModel(currentProject).processResource(srcFolder);
					}			
				}
			}catch (Exception E){
				System.out.println("Error :Build all");
			}
		}
		
		
	}

	/**
	 * @see IActionDelegate#selectionChanged(IAction, ISelection)
	 */
	public void selectionChanged(IAction action, ISelection selection) {
//		MAJ Console
		try{
			ProjectExploreView.refresh();
			ProjectExploreView.update();
			}catch(Exception e)
			{
				e.printStackTrace();
			}	
	}
	
   

}
