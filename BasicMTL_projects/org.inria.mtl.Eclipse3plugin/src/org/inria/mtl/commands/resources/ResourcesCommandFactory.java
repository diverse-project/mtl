/*
 * Created on 13 oct. 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package org.inria.mtl.commands.resources;

import org.eclipse.core.resources.IResource;
import org.inria.mtl.commands.MTLCommand;
import org.inria.mtl.commands.tag.GetTagResourceCommand;
import org.inria.mtl.commands.tag.SetTagResourceCommand;


/**
 * @author edrezen
 *
 */
public class ResourcesCommandFactory 
{
	////////////////////////////////////////////////////////////////////////////////
	// RESOURCES COMMANDS
	////////////////////////////////////////////////////////////////////////////////
	
	/** */
	public MTLCommand createRemoveResourceCommand (IResource resource, String tagName)
	{
		return new RemoveResourceCommand (resource, tagName); 
	}

	/** */
	public MTLCommand createRemoveContentsCommand (IResource resource, String tagName)
	{
		return new RemoveContentsCommand (resource, tagName); 
	}
	
	/** */
	public MTLCommand createSetTagResourceCommand (IResource resource, boolean isSet)
	{
		return new SetTagResourceCommand (resource, isSet);
	}

	/** */
	public MTLCommand createGetTagResourceCommand (IResource resource, String tagName)
	{
		return new GetTagResourceCommand (resource);
	}

	/** */
	public MTLCommand createRefreshResourceCommand (IResource resource, String tagName)
	{
		return new RefreshResourceCommand (resource);
	}

}
