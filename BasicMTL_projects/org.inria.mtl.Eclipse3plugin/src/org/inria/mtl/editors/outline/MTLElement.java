package org.inria.mtl.editors.outline;


import java.util.List;
import java.util.LinkedList;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.ui.model.IWorkbenchAdapter;

/**
 * @author Addi 
 */
abstract public class MTLElement implements IWorkbenchAdapter, IAdaptable, MTLElementCategories
{
	protected String name;
	protected int offset;
	protected int numberOfLines;
	protected int length;

	protected MTLElement parent;
	protected List children;

	/**
	 * Creates a new MTLElement and stores parent element and location in the text.
	 * 
	 * @param aName text corresponding to the func
	 * @param offset  the offset into the Readme text
	 * @param length  the length of the element
	 */
	public MTLElement(String aName, int offset, int length)
	{
		this.name = aName;
		this.offset = offset;
		this.length = length;
		this.children = new LinkedList();
	}

	/**
	 * Method declared on IAdaptable
	 */
	public Object getAdapter(Class adapter)
	{
		if (adapter == IWorkbenchAdapter.class)
		{
			return this;
		}

		return null;
	}

	/**
	 * Method declared on IWorkbenchAdapter
	 */
	public String getLabel(Object o)
	{
		return name;
	}

	/**
	 * Returns the number of characters in this section.
	 */
	public int getLength()
	{
		return length;
	}

	/**
	 * Returns the number of lines in the element.
	 */
	public int getNumberOfLines()
	{
		return numberOfLines;
	}

	/**
	 * Returns the offset of this section in the file.
	 */
	public int getStart()
	{
		return offset;
	}

	/**
	 * Sets the number of lines in the element
	 */
	public void setNumberOfLines(int newNumberOfLines)
	{
		numberOfLines = newNumberOfLines;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString()
	{
		return getLabel(this);
	}

	/**
	 * @see org.eclipse.ui.model.IWorkbenchAdapter#getChildren(Object)
	 */
	public Object[] getChildren(Object o)
	{
		Object[] result = new Object[children.size()];
		return children.toArray(result);
	}

	/**
	 * @see org.eclipse.ui.model.IWorkbenchAdapter#getParent(Object)
	 */
	public Object getParent(Object o)
	{
		return null;
	}
	
	/**
	 * A category enumeration for sub-types.
	 */
	abstract public int category();

	public String getName(){ return name;}
	public int getOffset(){	return offset;}
	public MTLElement getParent(){ return parent;}
	protected void setParent(MTLElement element){
		parent = element;
	}

	public boolean sharesParentWith(MTLElement anElement)
	{
		if (anElement == null)	{
			return false;
		}
		if (parent == null) {
			return anElement.getParent() == null;
		}
		
		return parent.equals(anElement.getParent());
	}

	public boolean equals(MTLElement anElement)
	{
		return sharesParentWith(anElement) && name.equals(anElement.getName());
	}

}