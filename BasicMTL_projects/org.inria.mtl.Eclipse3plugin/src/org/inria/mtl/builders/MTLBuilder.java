/*
* $Id: MTLBuilder.java,v 1.3 2004-09-10 12:41:48 dvojtise Exp $
* Authors : ${user}
*
* Created on ${date}
* Copyright 2004 - INRIA - LGPL license
*/ 

package org.inria.mtl.builders;

import java.util.ArrayList;
import java.util.Map;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IncrementalProjectBuilder;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.inria.mtl.MTLPlugin;
import org.inria.mtl.preferences.PreferencesConstants;
import org.inria.mtl.views.MTLConsole;

/**
 *  The MTL builder class sinks resource events and directs TLL and Java code generation
 * accordingly.
 */
public class MTLBuilder extends IncrementalProjectBuilder {

	/**
	 *  The Id of the eclipse builder asscoiated with MTL projects
	 */
	public final static String BUILDER_ID ="org.inria.mtl.MTLBuilder";

	private final static int TOTAL_WORK=100;
	
	private static int noClean =0; 
	
	public static boolean acceptClean =true;
	
	private ArrayList buildProjects =new ArrayList();
	/**
	 *  Constructor for MTLBuilder
	 */
	public MTLBuilder() {
		super();
	}

	/**
	 *@param  kind               the type of build
	 *@param  args               
	 *@param  monitor            monitor to use throughout operation
	 *@return                    
	 *@exception  CoreException  
	 *@see                       IncrementalProjectBuilder#build(int, Map,   IProgressMonitor)
	 */
	protected IProject[] build(int kind, Map args, IProgressMonitor monitor)
			 throws CoreException {
	//System.out.println("NO CLEAN :"+noClean + "  "+MTLPlugin.videConsole);
	//	noClean=1;
	//acceptClean=true;
	MTLModel.cFolders=new ArrayList();
	if (MTLPlugin.getDefault().getPreferenceStore().getBoolean(PreferencesConstants.AUTO_COMPILE)/*|| MTLPlugin.MenuAction*/) {
	        monitor.beginTask("Begining MTL transformations ", TOTAL_WORK);
					 	
		if (kind == IncrementalProjectBuilder.FULL_BUILD) {
				MTLConsole.cleanConsole();
				System.out.println(" BUILD PROJ1"+getProject());
				System.out.println(" BUILD PROJECT "+getProject());
				fullBuild(monitor);
				
		} else {
			IResourceDelta delta = getDelta(getProject());
			IResource res = delta.getResource();
			//System.out.println(" BUILD PROJ2"+getProject()+"   DELTA :"+res.toString()+"   Kind :"+res.getType()+"  Folder :"+res.FOLDER);
			//IMarker[] marks =getTasksFor(getProject());
			//printMarkers(marks);
			if (delta == null) {
				System.out.println("delta");
				fullBuild(monitor);
			} else {
				//if ((!MTLPlugin.MenuAction)&&(acceptClean==0)) MTLConsole.cleanConsole();
					 incrementalBuild(delta, monitor);
				 	// ++noClean;
				 	// MTLPlugin.videConsole=false;
				 	// System.out.println("NO CLEAN  DANS:"+noClean+"   "+MTLPlugin.videConsole);
				 	 buildProjects.add(getProject());
				 	 System.out.println("delta non null");
					}
				 }
	}else{
				if (MTLPlugin.MenuAction){
					MTLConsole.cleanConsole();
					//System.out.println("console vidée IncrementalProjectBuilder.FULL_BUILD");
					//System.out.println(" BUILD PROJ3"+getProject());
					//MTLModel.buildReferencesProjects(getProject());
					//System.out.println(" AVANT");
					fullBuild(monitor);
					MTLPlugin.MenuAction=false;
					//System.out.println(" MENUACTION EST VRAI");
				}else{
					System.out.println("AUTOCOMPILE & MENUACTION ST FAUX");
				}
		}
		return null;
	}


	/**
	 *  initialises a full build
	 *
	 *@param  monitor            monitor to use throughout operation
	 *@exception  CoreException  
	 */
	protected void fullBuild(final IProgressMonitor monitor) throws CoreException {
		try {
			MTLPlugin.instance().getModel(getProject()).prepareFullBuild();
			getProject().accept(new ResourceChangeListener());
		} catch (CoreException e) {
			System.out.println(e);
		}
	}


	/**
	 *  initialises an incremental build
	 *
	 *@param  delta              Delta object containing change info
	 *@param  monitor            monitor to use throughout operation
	 *@exception  CoreException  
	 */
	protected void incrementalBuild(IResourceDelta delta, IProgressMonitor monitor)
			 throws CoreException {
		// the visitor does the work.
		delta.accept(new ResourceChangeListener());
	}


	/**
	 *  method called by framework upon startup of this builder
	 */
	protected void startupOnInitialize() {
		  System.out.println("Starting  " + this.getClass().getName() + "...");
		  buildProjects.clear();
		
	}
	
	
	

}
