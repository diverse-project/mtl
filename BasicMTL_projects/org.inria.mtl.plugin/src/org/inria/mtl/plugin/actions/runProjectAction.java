/*
* $Id: runProjectAction.java,v 1.1 2004-06-22 08:39:24 sdzale Exp $
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
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.ILaunchManager;
import org.eclipse.debug.core.ILaunchConfigurationType;
import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.jdt.launching.*;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;
import org.inria.mtl.plugin.MTLPlugin;
import org.inria.mtl.plugin.builders.MTLModel;
import org.inria.mtl.plugin.core.MTLCore;


public class runProjectAction implements IWorkbenchWindowActionDelegate {
	private IWorkbenchWindow window;
	private StructuredSelection currentSelection = null;
	private ISelection selection=null;
	private IProject currentProject = null;
	private IFolder srcFolder=null;

	/**
	 * Constructor for buildprojectAction.
	 */
	public runProjectAction() {
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
		
		try{
			System.out.println("main class action");
		if (selection instanceof StructuredSelection)
			{
				System.out.println("main class action2");
				currentSelection = (StructuredSelection)selection;
				java.util.Iterator it = currentSelection.iterator();
				while (it.hasNext())
					{
						System.out.println("main class action 3"+selection.toString());
					//  if (it instanceof IResource){
						System.out.println("main class action 4");
						IResource item = (IResource) it.next ();
						System.out.println("main class action 5 " +item.getType()+"  "+item.getName());
							if (item instanceof IProject){
								//the project selected
								currentProject=item.getProject();
								MTLCore.setProject(currentProject);
								String mainClass=MTLPlugin.getDefault().getModel(currentProject).getMainClassFolder();
								System.out.println("main class :"+mainClass);
								
//								ILaunchManager manager = DebugPlugin.getDefault().getLaunchManager();   
//								ILaunchConfigurationType type = manager.getLaunchConfigurationType(IJavaLaunchConfigurationConstants.ID_JAVA_APPLICATION);   
//								ILaunchConfigurationWorkingCopy wc = type.newInstance(null, "SampleConfig");   
//								wc.setAttribute(IJavaLaunchConfigurationConstants.ATTR_PROJECT_NAME, currentProject.getName());   
//								wc.setAttribute(IJavaLaunchConfigurationConstants.ATTR_MAIN_TYPE_NAME, "myClass");   
//								ILaunchConfiguration config = wc.doSave();	   
//								config.launch(ILaunchManager.RUN_MODE, null); 
								
							 
						  }else{
							it.next();
							System.out.println("main class action 6");
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
