package org.inria.mtl.views;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.inria.mtl.views.model.MTLNode;

public class ThreeItemFilter extends ViewerFilter {

	/*
	 * @see ViewerFilter#select(Viewer, Object, Object)
	 */
	public boolean select(Viewer viewer, Object parentElement, Object element) {
		return parentElement instanceof MTLNode && ((MTLNode)parentElement).size() >= 3;
	}

}
