/*
 * Created on 13 oct. 2004
 *
 */
package org.inria.mtl.commands.resources;

import org.eclipse.core.resources.IResource;

/**
 * @author edrezen
 *
 * This command is intended to remove resources (from the MTL plugin point of view)
 */
public class RemoveResourceCommand extends AbstractResourceCommand
{
	////////////////////////////////////////////////////////////////////////////////
	// CONSTRUCTORS
	////////////////////////////////////////////////////////////////////////////////
	public RemoveResourceCommand (IResource resource, String tagName)
	{
		super (resource);
	}
	
	
	////////////////////////////////////////////////////////////////////////////////
	// METHODS
	////////////////////////////////////////////////////////////////////////////////

	/** */
	public Object common (IResource res) throws Exception
	{
		//MTLCommand.log().debug ("RemoveResourceCommand : the resource to be deleted is '" + res + "'");
		
		// a little check on the resource
		if (! res.exists()) { return null; }
		
		res.delete (true,null);

		return null;
	}
}
