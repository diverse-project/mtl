/*
 * Created on 13 oct. 2004
 *
 */
package org.inria.mtl.commands.tag;

import org.eclipse.core.resources.IResource;

/**
 * @author edrezen
 *
 */
public class SetTagResourceCommand extends MTLTagCommand
{
	////////////////////////////////////////////////////////////////////////////////
	// ATTRIBUTES
	////////////////////////////////////////////////////////////////////////////////
	private boolean setUnset;

	////////////////////////////////////////////////////////////////////////////////
	// GETTERS AND SETTERS
	////////////////////////////////////////////////////////////////////////////////
	public boolean isSetUnset() {
		return setUnset;
	}
	public void setSetUnset(boolean setUnset) {
		this.setUnset = setUnset;
	}

	
	////////////////////////////////////////////////////////////////////////////////
	// CONSTRUCTORS
	////////////////////////////////////////////////////////////////////////////////
	public SetTagResourceCommand (IResource resource, boolean setUnset)
	{
		super (resource);
		setSetUnset (setUnset);
	}
	
	
	////////////////////////////////////////////////////////////////////////////////
	// METHODS
	////////////////////////////////////////////////////////////////////////////////

	/** */
	public Object common (IResource res) throws Exception
	{
		//MTLCommand.log().debug ("TagResourceCommand : resource is '" + res + "' and the tag is '" + getTagName() + "'");

		if (isSetUnset())
		{
			// we set the property
			res.setPersistentProperty (MTLTagCommand.tagName, res.getName());
		}
		else
		{
			// we set the property
			res.setPersistentProperty (MTLTagCommand.tagName, null);
		}
		
		return null;
	}

}
