/*
 * Created on 18 oct. 2004
 *
 */
package org.inria.mtl.commands.tag;

import org.eclipse.core.resources.IResource;
import org.inria.mtl.commands.MTLCommand;

/**
 * @author edrezen
 *
 * A factory to create commands related to tags (i.e. tagging IResource objects)
 */
public class TagCommandFactory 
{
	/** */
	public MTLCommand createSetTagResourceCommand (IResource resource, boolean setUnset)
	{
		return 	new SetTagResourceCommand (resource, setUnset);
	}

	/** */
	public MTLCommand createGetTagResourceCommand (IResource resource)
	{
		return 	new GetTagResourceCommand (resource);
	}
}
