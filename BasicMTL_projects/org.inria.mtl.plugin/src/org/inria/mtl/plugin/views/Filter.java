/*
* $Id: Filter.java,v 1.1 2004-05-28 16:53:01 sdzale Exp $
* Authors : ${user}
*
* Created on ${date}
* Copyright 2004 - INRIA - LGPL license
*/
package org.inria.mtl.plugin.views;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;

/**
 * @author tcn
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class Filter extends ViewerFilter
{

	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.ViewerFilter#select(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
	 */
	public boolean select(Viewer viewer, Object parentElement, Object element)
	{
		// TODO throws exception:
//		Unhandled exception caught in event loop.
//		Reason:
//		Failed to execute runnable (java.lang.IllegalArgumentException: Index out of bounds)
//
//		*** Stack trace of contained exception ***
//		Reason:
//		Index out of bounds
//		Entry e = (Entry)element;

//		if (e.getLevel().toInt() == Level.DEBUG_INT)
//				return true;
		return false;
	}
}
