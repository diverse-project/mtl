/*
* $Id: buildfullprojectAction.java,v 1.1 2004-10-22 07:45:25 edrezen Exp $
* Authors : ${user}
*
* Created on ${date}
* Copyright 2004 - INRIA - LGPL license
*/ 
package org.inria.mtl.popup.actions;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.inria.mtl.commands.MTLCommandExecutor;
/**
 * @author sdzale
 * Build a project 
 */

public class buildfullprojectAction extends MTLActionWithSelection
{
	////////////////////////////////////////////////////////////////////////////////
	// METHODS
	////////////////////////////////////////////////////////////////////////////////

	/** Implementation of a Template Method primitive. */ 
	public void ProcessResource (IResource item) throws Exception 
	{
		// we are interested in IProject resources
		if (item instanceof IProject)
		{
			// we launch a command that do our job.
			MTLCommandExecutor.buildFullProject (item.getProject());
		}
	}
}
