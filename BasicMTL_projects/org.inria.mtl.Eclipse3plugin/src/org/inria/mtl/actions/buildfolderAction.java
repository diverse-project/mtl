/*
* $Id: buildfolderAction.java,v 1.1 2004-07-30 14:11:15 sdzale Exp $
* Authors : ${user}
*
* Created on ${date}
* Copyright 2004 - INRIA - LGPL license
*/ 
package org.inria.mtl.actions;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.QualifiedName;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;
import org.inria.mtl.builders.MTLModel;
import org.inria.mtl.core.MTLCore;
import org.inria.mtl.MTLPlugin;
import org.inria.mtl.views.MTLConsole;

public class buildfolderAction implements IWorkbenchWindowActionDelegate {
	private IWorkbenchWindow window;
	private StructuredSelection currentSelection = null;
	private IProject currentProject = null;
	private IFolder srcFolder=null;
	private ISelection selection=null;
	
	private boolean cleanconsole=true; 
	
	
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
		
		//La compilation est lancée par une action du menu
		if (!MTLPlugin.MenuAction){
			MTLPlugin.MenuAction=cleanconsole;
		}else{
			MTLConsole.cleanConsole();
		}
		
	try{
		//System.out.println("Build folder :"+MTLPlugin.MenuAction);
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
						boolean i=MTLPlugin.instance().getModel(currentProject).processResource(srcFolder);
										
//						System.out.println("FOLDER ACTION COMPILE:"+srcFolder.getFullPath()+"   "+ oldGen+"  new gen "+newGen);
//						System.out.println("*******************************************************");
					}
				}
			}
		
	}catch(Exception E){
		System.out.println(E.getMessage());
	}
	}

	/**
	 * @see IActionDelegate#selectionChanged(IAction, ISelection)
	 */
	public void selectionChanged(IAction action, ISelection selection) {
		this.selection=selection;
		if (selection instanceof StructuredSelection)
		{
			
			currentSelection = (StructuredSelection)selection;
			java.util.Iterator it = currentSelection.iterator();
			
			while (it.hasNext() )
			{	
				if (it instanceof IResource){
				IResource item = (IResource) it.next ();
			
				if (item instanceof IFolder){
			
					currentProject=item.getProject();
					MTLCore.setProject(currentProject);
					MTLModel.setProject(currentProject);
				}
				}else{
					it.next();
				}
			}
		}
		}

	/**
	 * compile a directory
	 */
	public boolean compileDirectory(IFolder srcFolder, IProject currProject) {
		boolean ok=MTLPlugin.instance().getModel(currProject).processResource(srcFolder);;
		return ok;		
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
