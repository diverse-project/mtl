package org.inria.mtl.editors.outline;

import org.eclipse.jface.resource.ImageDescriptor;

public class MTLModelElement extends MTLElement
{
	public MTLModelElement(String aName, int offset, int length)
	{
		super(aName, offset, length);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.model.IWorkbenchAdapter#getImageDescriptor(java.lang.Object)
	 */
	public ImageDescriptor getImageDescriptor(Object object)
	{
		/* Voir MTLImages pour les icones*/
		return MTLImages.ICON_MODEL;
	}

	public int category()
	{
		return MODEL;	
	}
 
}
