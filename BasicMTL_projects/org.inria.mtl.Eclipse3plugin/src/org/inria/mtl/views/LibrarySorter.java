package org.inria.mtl.views;

import org.inria.mtl.views.model.Library;
import org.inria.mtl.views.model.MTLNode;

import org.eclipse.jface.viewers.ViewerSorter;

public class LibrarySorter extends ViewerSorter {
	
	/*
	 * @see ViewerSorter#category(Object)
	 */
	/** Orders the items in such a way that books appear 
	 * before moving boxes, which appear before board games. */
	public int category(Object element) {
		if(element instanceof Library) return 1;
		if(element instanceof MTLNode) return 2;
		return 3;
	}

}
