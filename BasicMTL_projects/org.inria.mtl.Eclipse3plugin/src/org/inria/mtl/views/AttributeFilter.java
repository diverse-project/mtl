package org.inria.mtl.views;

import org.inria.mtl.views.model.Attribute;
import org.inria.mtl.views.model.MTLNode;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;

public class AttributeFilter extends ViewerFilter {

	/*
	 * @see ViewerFilter#select(Viewer, Object, Object)
	 */
	public boolean select(Viewer viewer, Object parentElement, Object element) {
		return element instanceof Attribute || element instanceof MTLNode;
	}

}
