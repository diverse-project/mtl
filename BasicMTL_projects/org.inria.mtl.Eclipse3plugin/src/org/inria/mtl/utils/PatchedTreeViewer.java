/*
 * Created on 25 mai 2004
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package org.inria.mtl.utils;

import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

/** PATCHED VERSION !!!
 WHEN USING A RUNNABLE, IT LEADS TO A STACK OVERFLOW...
 */
public class PatchedTreeViewer extends TreeViewer 
{
	/** */
	public PatchedTreeViewer (Composite parent) 
	{
		super(parent);
	}

	/** */
	protected void inputChanged(Object input, Object oldInput) 
	{
		Control tree = getControl();
		boolean useRedraw = true;
		// (size > REDRAW_THRESHOLD) || (table.getItemCount() > REDRAW_THRESHOLD);
		if (useRedraw)
			tree.setRedraw(false);
		removeAll(tree);
		tree.setData(getRoot());
		createChildren(tree);
		
		if (useRedraw)
			tree.setRedraw(true);
	}

}
