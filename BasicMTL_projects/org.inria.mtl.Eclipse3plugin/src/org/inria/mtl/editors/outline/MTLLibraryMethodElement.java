package org.inria.mtl.editors.outline;

import org.eclipse.jface.resource.ImageDescriptor;

public class MTLLibraryMethodElement extends MTLElement
{
	public MTLLibraryMethodElement(String aName, int offset, int length)
	{
		super(aName, offset, length);
	}
	
	public ImageDescriptor getImageDescriptor(Object object)
	{	/* Voir MTLImages pour les icones*/
		return MTLImages.ICON_VIEW_CLASS_METHOD;
	}

	public int category()
	{
		return LIBRARY_METHOD;	
	}

}
