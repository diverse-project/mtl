/*
* $Id: MTLBuilder.java,v 1.1 2004-07-30 14:09:27 sdzale Exp $
* Authors : ${user}
*
* Created on ${date}
* Copyright 2004 - INRIA - LGPL license
*/ 

package org.inria.mtl.builders;

import java.util.ArrayList;
import java.util.Map;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IncrementalProjectBuilder;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;

import org.inria.mtl.MTLPlugin;
import org.inria.mtl.markers.MTLMarkers;
import org.inria.mtl.views.MTLConsole;
import org.inria.mtl.preferences.PreferencesConstants;

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
	
	private static boolean noClean =true; 
	/**
	 *  Constructor for MTLBuilder
	 */
	public MTLBuilder() {
		super();
	}

	public static IMarker[] getProblemsFor(IResource resource) {
		try {
			if (resource != null && resource.exists())
				return resource.findMarkers(MTLMarkers.JAVA_MODEL_PROBLEM_MARKER, false, IResource.DEPTH_INFINITE);
		} catch (CoreException e) {} // assume there are no problems
		return new IMarker[0];
	}

	public static IMarker[] getTasksFor(IResource resource) {
		try {
			if (resource != null && resource.exists())
				return resource.findMarkers(MTLMarkers.TASK_MARKER, false, IResource.DEPTH_INFINITE);
		} catch (CoreException e) {} // assume there are no tasks
		return new IMarker[0];
	}

	public static void removeProblemsFor(IResource resource) {
		try {
			if (resource != null && resource.exists())
				resource.deleteMarkers(MTLMarkers.JAVA_MODEL_PROBLEM_MARKER, false, IResource.DEPTH_INFINITE);
		} catch (CoreException e) {} // assume there were no problems
	}

	public static void removeTasksFor(IResource resource) {
		try {
			if (resource != null && resource.exists())
				resource.deleteMarkers(MTLMarkers.TASK_MARKER, false, IResource.DEPTH_INFINITE);
		} catch (CoreException e) {} // assume there were no problems
	}

	public static void removeProblemsAndTasksFor(IResource resource) { 
		try {
			if (resource != null && resource.exists()) {
				resource.deleteMarkers(MTLMarkers.JAVA_MODEL_PROBLEM_MARKER, false, IResource.DEPTH_INFINITE);
				resource.deleteMarkers(MTLMarkers.TASK_MARKER, false, IResource.DEPTH_INFINITE);
			}
		} catch (CoreException e) {} // assume there were no problems
	}

	/**
	 *@param  kind               the type of build
	 *@param  args               
	 *@param  monitor            monitor to use throughout operation
	 *@return                    
	 *@exception  CoreException  
	 *@see                       IncrementalProjectBuilder#build(int, Map,
	 *      IProgressMonitor)
	 */
	protected IProject[] build(int kind, Map args, IProgressMonitor monitor)
			 throws CoreException {
			 	
	MTLModel.cFolders=new ArrayList();
	//System.out.println(" BUILDER CALL AUTO:"+MTLPlugin.getDefault().getPreferenceStore().getBoolean(PreferencesConstants.AUTO_COMPILE));
	// compile or not, according to the user preferences
	if (MTLPlugin.getDefault().getPreferenceStore().getBoolean(PreferencesConstants.AUTO_COMPILE)/*|| MTLPlugin.MenuAction*/) {
	        monitor.beginTask("Begining MTL transformations ", TOTAL_WORK);
					 	
		if (kind == IncrementalProjectBuilder.FULL_BUILD) {
				MTLConsole.cleanConsole();
				System.out.println(" BUILD PROJ1"+getProject());
				System.out.println(" BUILD PROJECT "+getProject());
				fullBuild(monitor);
				
		} else {
			IResourceDelta delta = getDelta(getProject());
			System.out.println(" BUILD PROJ2"+getProject());
			//IMarker[] marks =getTasksFor(getProject());
			//printMarkers(marks);
			if (delta == null) {
				System.out.println("delta");
				fullBuild(monitor);
			} else {
				if (!MTLPlugin.MenuAction) MTLConsole.cleanConsole();
					 incrementalBuild(delta, monitor);
				 	 noClean=false;
				 	 System.out.println("delta non null");
					}
				 }
	}else{
				if (MTLPlugin.MenuAction){
					MTLConsole.cleanConsole();
					System.out.println("console vidée IncrementalProjectBuilder.FULL_BUILD");
					System.out.println(" BUILD PROJ3"+getProject());
					//MTLModel.buildReferencesProjects(getProject());
					System.out.println(" AVANT");
					fullBuild(monitor);
					System.out.println(" MENUACTION EST FAUX");
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
		
	}
	
	protected void printMarkers(IMarker[] res) {
		for (int i=0;i<res.length;i++){
			try{
				  System.out.println("Marker  " + res[i].getCreationTime()+"  "+ res[i].getType() + "...");
			}catch(Exception E){
				System.out.println("Erreur print markers");
			}
		}
		
		}
	

}
