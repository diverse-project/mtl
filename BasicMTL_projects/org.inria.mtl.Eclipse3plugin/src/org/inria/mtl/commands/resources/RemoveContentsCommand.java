/*
 * Created on 14 oct. 2004
 *
 */
package org.inria.mtl.commands.resources;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;

/**
 * @author edrezen
 *
 * This class removes the contents of Resource that are containers (IFolder, IProject...)
 */

public class RemoveContentsCommand extends RemoveResourceCommand 
{
	////////////////////////////////////////////////////////////////////////////////
	// CONSTRUCTORS
	////////////////////////////////////////////////////////////////////////////////

	public RemoveContentsCommand (IResource resource, String tagName) 
	{
		super(resource, tagName);
	}
	
	////////////////////////////////////////////////////////////////////////////////
	// METHODS
	////////////////////////////////////////////////////////////////////////////////
	public Object processFolder  (IFolder folder)   throws Exception  { return null;  }
	public Object processProject (IProject project) throws Exception  { return null; }

}
