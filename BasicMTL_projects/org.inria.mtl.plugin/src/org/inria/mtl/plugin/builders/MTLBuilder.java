/*
* $Id: MTLBuilder.java,v 1.2 2004-05-17 10:16:59 sdzale Exp $
* Authors : ${user}
*
* Created on ${date}
* Copyright 2004 - INRIA - LGPL license
*/ 

package org.inria.mtl.plugin.builders;

import java.util.Map;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IncrementalProjectBuilder;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;

import org.inria.mtl.plugin.MTLPlugin;

/**
 *  The MTL builder class sinks resource events and directs TLL and Java code generation
 * accordingly.
 */
public class MTLBuilder extends IncrementalProjectBuilder {

	/**
	 *  The Id of the eclipse builder asscoiated with MTL projects
	 */
	public final static String BUILDER_ID ="org.inria.mtl.plugin.MTLBuilder";

	private final static int TOTAL_WORK=100;
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
	 *@see                       IncrementalProjectBuilder#build(int, Map,
	 *      IProgressMonitor)
	 */
	protected IProject[] build(int kind, Map args, IProgressMonitor monitor)
			 throws CoreException {
		
        monitor.beginTask("Begining MTL transformations ", TOTAL_WORK);
		try{
			//System.out.println(get)
			//MTLCore.findFolders();
		}catch (Exception e){
			System.out.println("Problème avec la lecture du fichier .classpath :"+e.getMessage());
			}			 	
		if (kind == IncrementalProjectBuilder.FULL_BUILD) {
				fullBuild(monitor);
		} else {
			IResourceDelta delta = getDelta(getProject());
			if (delta == null) {
				fullBuild(monitor);
			} else {
				incrementalBuild(delta, monitor);
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

}
