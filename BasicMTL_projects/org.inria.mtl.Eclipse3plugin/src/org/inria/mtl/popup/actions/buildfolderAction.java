/*
* $Id: buildfolderAction.java,v 1.3 2004-10-22 07:45:25 edrezen Exp $
* Authors : ${user}
*
* Created on ${date}
* Copyright 2004 - INRIA - LGPL license
*/ 
package org.inria.mtl.popup.actions;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IResource;
import org.inria.mtl.commands.MTLCommandExecutor;

/**
 * @author sdzale
 * Build a folder source 
 */
public class buildfolderAction extends MTLActionWithSelection 
{
	////////////////////////////////////////////////////////////////////////////////
	// METHODS
	////////////////////////////////////////////////////////////////////////////////

	/** Implementation of a Template Method primitive. */ 
	public void ProcessResource (IResource item) throws Exception 
	{
		// we are interested in Folder resources
		if (item instanceof IFolder)
		{
			// we launch a command that do our job.
			MTLCommandExecutor.buildFolder ((IFolder)item);
		}
	}
}
