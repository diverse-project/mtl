/*
 * Created on 14 oct. 2004
 *
 */
package org.inria.mtl.commands.resources;

import org.eclipse.core.resources.*;

/**
 * @author edrezen
 *
 * This command refresh a resource
 */
public class RefreshResourceCommand extends AbstractResourceCommand 
{

	////////////////////////////////////////////////////////////////////////////////
	// CONSTRUCTORS
	////////////////////////////////////////////////////////////////////////////////
	public RefreshResourceCommand (IResource resource) 
	{
		super(resource);
	}
	
	
	////////////////////////////////////////////////////////////////////////////////
	// METHODS
	////////////////////////////////////////////////////////////////////////////////

	/** */
	public Object mainExecute() throws Exception  
	{	
		if (getResource() instanceof IFile)
		{
			((IFile)getResource()).getParent().refreshLocal (IResource.DEPTH_INFINITE, null);
		}
		
		else if (getResource() instanceof IFolder)
		{
			getResource().refreshLocal (IResource.DEPTH_INFINITE, null);
		}
		
		else if (getResource() instanceof IProject)
		{
			getResource().refreshLocal (IResource.DEPTH_INFINITE, null);
		}
		
		return null; 
	}

}
