/*
 * Created on 18 oct. 2004
 *
 */
package org.inria.mtl.commands.tag;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.QualifiedName;
import org.inria.mtl.MTLPlugin;
import org.inria.mtl.builders.MTLModel;
import org.inria.mtl.commands.resources.AbstractResourceCommand;

/**
 * @author edrezen
 *
 */
public class MTLTagCommand extends AbstractResourceCommand
{
	////////////////////////////////////////////////////////////////////////////////
	// ATTRIBUTES
	////////////////////////////////////////////////////////////////////////////////
	final protected static QualifiedName tagName = new QualifiedName(MTLPlugin.PLUGIN_ID, MTLModel.MTL_OWNER_PROP);
	
	
	////////////////////////////////////////////////////////////////////////////////
	// CONSTRUCTORS
	////////////////////////////////////////////////////////////////////////////////
	public MTLTagCommand (IResource resource) 
	{
		super (resource);
	}
}
