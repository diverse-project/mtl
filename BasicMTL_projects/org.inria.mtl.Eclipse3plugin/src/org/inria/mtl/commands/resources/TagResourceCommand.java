/*
 * Created on 13 oct. 2004
 *
 */
package org.inria.mtl.commands.resources;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.QualifiedName;
import org.inria.mtl.MTLPlugin;
import org.inria.mtl.builders.MTLModel;

/**
 * @author edrezen
 *
 */
public class TagResourceCommand extends MTLResourceCommand
{
	////////////////////////////////////////////////////////////////////////////////
	// CONSTRUCTORS
	////////////////////////////////////////////////////////////////////////////////
	public TagResourceCommand (IResource resource, String tagName)
	{
		super (resource, tagName);
	}
	
	
	////////////////////////////////////////////////////////////////////////////////
	// METHODS
	////////////////////////////////////////////////////////////////////////////////

	/** */
	public Object common (IResource res) throws Exception
	{
		//MTLCommand.log().debug ("TagResourceCommand : resource is '" + res + "' and the tag is '" + getTagName() + "'");

		// we set the property
		res.setPersistentProperty (new QualifiedName(MTLPlugin.PLUGIN_ID, MTLModel.MTL_OWNER_PROP), getTagName());
		
		return null;
	}
}
