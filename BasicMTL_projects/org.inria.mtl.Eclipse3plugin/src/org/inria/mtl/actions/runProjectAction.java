/*
* $Id: runProjectAction.java,v 1.2 2004-08-26 12:40:16 sdzale Exp $
* Authors : ${user}
*
* Created on ${date}
* Copyright 2004 - INRIA - LGPL license
*/ 
package org.inria.mtl.actions;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IActionDelegate;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;
import org.inria.mtl.MTLPlugin;
import org.inria.mtl.core.MTLCore;
import org.irisa.triskell.MT.utils.Java.Mangler;


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
			if (selection instanceof StructuredSelection)
			{
				currentSelection = (StructuredSelection)selection;
				java.util.Iterator it = currentSelection.iterator();
				while (it.hasNext())
					{
						IResource item = (IResource) it.next ();
						if (item instanceof IProject){
								//the project selected
								currentProject=item.getProject();
								MTLCore.setProject(currentProject);
								String mainClass=MTLPlugin.getDefault().getModel(currentProject).getMainClassFolder();
								//System.out.println("main class :"+mainClass);
								IPath pClass=new Path(mainClass);
								//System.out.println("main class Name:"+pClass.removeFirstSegments(pClass.segmentCount()-1));
								String className=Mangler.mangle("BMTL",pClass.removeFirstSegments(pClass.segmentCount()-1).lastSegment());
								//System.out.println("main class Name last:"+className);
								
								
								
//								ILaunchManager manager = DebugPlugin.getDefault().getLaunchManager();   
//								ILaunchConfigurationType type = manager.getLaunchConfigurationType(IJavaLaunchConfigurationConstants.ID_JAVA_APPLICATION);   
//								ILaunchConfigurationWorkingCopy wc = type.newInstance(null, "SampleConfig");   
//								wc.setAttribute(IJavaLaunchConfigurationConstants.ATTR_PROJECT_NAME, currentProject.getName());   
//								wc.setAttribute(IJavaLaunchConfigurationConstants.ATTR_MAIN_TYPE_NAME, "myClass");   
//								ILaunchConfiguration config = wc.doSave();	   
//								config.launch(ILaunchManager.RUN_MODE, null); 
								
							 
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
