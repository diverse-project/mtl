package org.inria.mtl.editors.outline;


import java.util.HashMap;
import org.eclipse.jface.resource.ImageDescriptor;

public class MTLFunctionOutline extends MTLElement
{
	protected HashMap childrenByName;
	protected boolean isPrototype = false;

	public MTLFunctionOutline(String aName, int offset, int length)
	{
		super(aName, offset, length);
		childrenByName = new HashMap();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.model.IWorkbenchAdapter#getImageDescriptor(java.lang.Object)
	 */
	public ImageDescriptor getImageDescriptor(Object object)
	{	
				return MTLImages.ICON_VIEW_LIBRARY_METHOD;
	}
	
	
	public void addChildElement(MTLElement anElement)
	{
		String elementName = anElement.getName();
		if(!childrenByName.containsKey(elementName))
		{
			this.children.add(anElement);
			this.childrenByName.put(elementName, anElement);
			anElement.setParent(this);
		}
	}

	public int category(){ return LIBRARY_METHOD;}
	public boolean isPrototype(){ return isPrototype;}
	public void setPrototype(boolean b){ isPrototype = b;}
}
