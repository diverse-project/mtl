/*
 * $Id: buildAllAction.java,v 1.7 2004-06-18 14:20:38 sdzale Exp $
 * 
 * Licence LGPL - Inria 
 */
package org.inria.mtl.plugin.actions;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.QualifiedName;
import org.eclipse.jface.action.IAction;
//import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;
import org.inria.mtl.plugin.MTLPlugin;
import org.inria.mtl.plugin.builders.MTLModel;
import org.inria.mtl.plugin.builders.MTLNature;
import org.inria.mtl.plugin.core.MTLCore;
import org.inria.mtl.plugin.preferences.PreferenceConstants;

/**
 * Our sample action implements workbench action delegate.
 * The action proxy will be created by the workbench and
 * shown in the UI. When the user tries to use the action,
 * this delegate will be created and execution will be 
 * delegated to it.
 * @see IWorkbenchWindowActionDelegate
 */
public class buildAllAction implements IWorkbenchWindowActionDelegate {
	private IWorkbenchWindow window;
	private StructuredSelection currentSelection = null;
	private IProject currentProject = null;
	private IFolder srcFolder=null;
	private IPreferenceStore store=null;
	private boolean auto_build=false;

	/**
	 * The constructor.
	 */
	public buildAllAction() {
		super();
		store=MTLPlugin.getDefault().getPreferenceStore();
		auto_build=store.getBoolean(PreferenceConstants.AUTO_COMPILE);
		System.out.println("auto build :"+auto_build);

	}

	/**
	 * The action has been activated. The argument of the
	 * method represents the 'real' action sitting
	 * in the workbench UI.
	 * @see IWorkbenchWindowActionDelegate#run
	 */
	public void run(IAction action) {
		IWorkspaceRoot workspaceRoot = ResourcesPlugin.getWorkspace().getRoot();
		IProject[] projects =workspaceRoot.getProjects();
		
	if (store.getBoolean(PreferenceConstants.AUTO_COMPILE)){
			
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
						IFolder srcFolder= currentProject.getFolder(srcPaths[j].removeFirstSegments(1));
						//Modify the timeStamp to permit automatic new exécution
						long oldGen = srcFolder.getModificationStamp();
						String newGen=((oldGen==100)?new Long(oldGen-1).toString():new Long(oldGen+1).toString());
						srcFolder.setPersistentProperty(new QualifiedName(MTLPlugin.PLUGIN_ID, MTLModel.TLL_LASTGENTIME), newGen);
						boolean b=MTLPlugin.instance().getModel(currentProject).processResource(srcFolder);
						
						
					}			
				}
			}catch (Exception E){
				System.out.println("Error :Build all");
			}
		}
	}
	}

	/**
	 * Selection in the workbench has been changed. We 
	 * can change the state of the 'real' action here
	 * if we want, but this can only happen after 
	 * the delegate has been created.
	 * @see IWorkbenchWindowActionDelegate#selectionChanged
	 */
	public void selectionChanged(IAction action, ISelection selection) {

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