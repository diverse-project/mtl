/*
 * $Id: buildAllAction.java,v 1.2 2004-08-26 12:40:16 sdzale Exp $
 * 
 * Licence LGPL - Inria 
 */
package org.inria.mtl.actions;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.QualifiedName;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;
import org.inria.mtl.MTLPlugin;
import org.inria.mtl.builders.MTLModel;
import org.inria.mtl.builders.MTLNature;
import org.inria.mtl.core.MTLCore;
import org.inria.mtl.preferences.PreferencesConstants;
import org.inria.mtl.views.MTLConsole;
import org.inria.mtl.views.ProjectExploreView;

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
	private ISelection selection=null;
	//private boolean auto_build=false;
	
	private boolean cleanconsole=true; 

	/**
	 * The constructor.
	 */
	public buildAllAction() {
		super();
		store=MTLPlugin.getDefault().getPreferenceStore();
		//auto_build=store.getBoolean(PreferencesConstants.AUTO_COMPILE);
		//System.out.println("auto build :"+auto_build);

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
		
//		La compilation est lancée par une action du menu
			  if (!MTLPlugin.MenuAction){
				  MTLPlugin.MenuAction=cleanconsole;
			  }else{
				  MTLConsole.cleanConsole();
			  }
		
	if (store.getBoolean(PreferencesConstants.AUTO_COMPILE)){
			
		for (int i=0;i<projects.length;i++){
			try{
			
			//voir comment contrôler cette exécution
				if (projects[i].hasNature(MTLNature.NATURE_ID)){
					//System.out.println("build All"+projects[i].getName());
					
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
		this.selection=selection;
		System.out.println("Selection ");
		try{
				
			if (selection instanceof StructuredSelection){
				currentSelection = (StructuredSelection)selection;
				java.util.Iterator it = currentSelection.iterator();
				while (it.hasNext())
				{
					if (it instanceof IResource){
					 IResource item = (IResource) it.next ();
						if (item instanceof IProject){
							currentProject=item.getProject();
							MTLCore.setProject(currentProject);
							MTLPlugin.instance().getModel(item.getProject()).setProject(currentProject);
//							MTLModel.setProject(currentProject);
							//System.out.println("PROJET :"+currentProject.getName());
						}else{
								it.next();
				 		}
					}else{
						it.next();
					}
				}
			}	
		}catch (Exception E){
			MTLPlugin.log(E);
			System.out.println("Erreur build all Action");
				//System.out.println(E.getMessage());
		}
	//MAJ Console
		try{
			ProjectExploreView.refresh();
			ProjectExploreView.update();
			}catch(Exception e)
			{
				//To do
			}
		
		
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