/*
* $Id: MTLNature.java,v 1.1 2004-07-30 14:09:28 sdzale Exp $
* Authors : ${user}
*
* Created on ${date}
* Copyright 2004 - INRIA - LGPL license
*/ 
package org.inria.mtl.builders;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectNature;
import org.eclipse.core.runtime.CoreException;
/**
 *  The eclipse Nature class used to associate with MTL projects. Each MTL project
 * will have a MTLNature object attached to it. This is the container for the MTLProjectModel
 */
public class MTLNature implements IProjectNature {
	private IProject project;
	private MTLModel model;
	/**
	 *  The eclipse MTL id
	 **/
	public final static String NATURE_ID = "org.inria.mtl.MTLNature";
	 /**
	 *  Constructor for MTLNature
	 **/
	public MTLNature() {
		super();
	}
	 /**
	 *@exception  CoreException 
	 *@see                       IProjectNature#configure()
	 **/
	public void configure() throws CoreException {
		  System.out.println("Config Called " + this.getClass().getName() + "...");
	}
	/**
	 *@exception  CoreException  
	 *@see                       IProjectNature#deconfigure()
	 **/
	public void deconfigure() throws CoreException { }
	 
	 /**
	 *@return    The project value
	 *@see       IProjectNature#getProject()
	 **/
	public IProject getProject() {
		return project;
	}
	 /**
	 *  creates the MTLProjectModel from the IProject object
	 *@param  project  The new project value
	 *@see             IProjectNature#setProject(IProject)
	 **/
	public void setProject(IProject project) {
		this.project = project;
		model = new MTLModel(project);
	}
	 /**
	 *  Gets the model attribute of the MTLNature object
	 *
	 *@return    The model value
	 **/
	public MTLModel getModel() {
		return model;
	}
}
