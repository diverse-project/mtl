package org.inria.mtl.editors.outline;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.model.IWorkbenchAdapter;

public class MTLElementList implements IWorkbenchAdapter, IAdaptable
{
	protected List children = new ArrayList();

	/**
	 * Creates a new adaptable list with the given children.
	 * Utilisé dans MTLOutline
	 */
	public MTLElementList() 
	{
	}
	/**
	 * 2 fonctions identiques
	 * Creates a new adaptable list with the given children.
	 */
	public MTLElementList(MTLElement[] newChildren)
	{
		for (int i = 0; i < newChildren.length; i++)
		{
			children.add(newChildren[i]);
		}
	}

	public MTLElementList(List newChildren)
	{
		for (int i = 0; i < newChildren.size(); i++)
		{
			children.add(newChildren.get(i));
		}
	}

	/**
	 * Adds all the adaptable objects in the given enumeration to this list. Returns this list.
	 */
	public MTLElementList add(Iterator iterator)
	{
		while (iterator.hasNext())
		{
			add((MTLElement) iterator.next());
		}
		return this;
	}

	/**
	 * Adds the given adaptable object to this list. Returns this list.
	 */
	public MTLElementList add(MTLElement anElement)
	{
		children.add(anElement);
		return this;
	}

	public Object getAdapter(Class adapter)
	{
		if (adapter == IWorkbenchAdapter.class)
		{
			return this;
		}
		return null;
	}

	/**
	 * Returns the elements in this list.
	 */
	public Object[] getChildren(){ return children.toArray();}
	public Object[] getChildren(Object o){ return children.toArray();}
	public ImageDescriptor getImageDescriptor(Object object){ return null;}
	public String getLabel(Object object){ return object == null ? "" : object.toString();}
	public Object getParent(Object object){	return null;}

	/**
	 * Removes the given adaptable object from this list.
	 */
	public void remove(MTLElement anElement)
	{
		children.remove(anElement);
	}

	/**
	 * Returns the number of items in the list
	 */
	public int size(){ return children.size();}
	public MTLElement findEquivilent(MTLElement anElement)
	{	for(int i = 0; i < size();i++)
		{	MTLElement aCandidate = (MTLElement) children.get(i);
			if(anElement.equals(aCandidate))
			{ 	return aCandidate;
			}
		}
		return null;
	}

	public MTLElement get(int index)
	{	if(index >= size())
		{	return null;
		}
		return (MTLElement) children.get(index);
	}
}
