/*
 * Package créé le 25 février 2004, debuggé et modifié le 27
 * Pamplemouss
 */

package org.inria.mtl.editors.outline;

import org.eclipse.jface.viewers.ViewerSorter;


/**
 * Name sorter @author alain
 * en tout cas il aurait pu mettre un nom au moins
 */
class MTLNameSorter extends ViewerSorter
{

	/**
	 * Returns the category of the given element. The category is a
	 * number used to allocate elements to bins; the bins are arranged
	 * in ascending numeric order. The elements within a bin are arranged
	 * via a second level sort criterion.
	 * @param element the element
	 * @return the category
	 */
	public int category(Object element) {
		return ((MTLElement) element).category();
	}
}