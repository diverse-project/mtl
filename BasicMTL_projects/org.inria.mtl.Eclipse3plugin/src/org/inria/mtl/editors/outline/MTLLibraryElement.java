package org.inria.mtl.editors.outline;

import org.eclipse.jface.resource.ImageDescriptor;

public class MTLLibraryElement extends MTLElement
{
	public MTLLibraryElement(String aName, int offset, int length)
	{
		super(aName, offset, length);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.model.IWorkbenchAdapter#getImageDescriptor(java.lang.Object)
	 */
	public ImageDescriptor getImageDescriptor(Object object)
	{
		/* Voir MTLImages pour les icones*/
		return MTLImages.ICON_LIBRARY;
	}

	public int category()
	{
		return LIBRARY;	
	}

}
