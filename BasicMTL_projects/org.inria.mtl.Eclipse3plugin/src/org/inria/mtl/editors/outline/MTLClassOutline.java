package org.inria.mtl.editors.outline;


import java.util.HashMap;
import org.eclipse.jface.resource.ImageDescriptor;

public class MTLClassOutline extends MTLElement
{
	protected HashMap childrenByName;
	protected boolean isPrototype = false;
	
	

	public MTLClassOutline(String aName, int offset,  int length)
	{	
		super(aName, offset, length);
		childrenByName = new HashMap();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.model.IWorkbenchAdapter#getImageDescriptor(java.lang.Object)
	 */
	public ImageDescriptor getImageDescriptor(Object object)
	{	/* Voir EiffelImages pour les icones*/
		if(isPrototype) {
			return MTLImages.ICON_VIEW_DYNAMIC_CLASS;
		}
		return MTLImages.ICON_VIEW_CLASS;
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

	public int category(){ return CLASS;}
	public boolean isPrototype(){ return isPrototype;}
	public void setPrototype(boolean b){ isPrototype = b;}
}
