/*
 * Created on 12 août 2003
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package org.irisa.triskell.MT.utils.Java;

import java.util.Iterator;

/**
 *An iterator applying a filter method on each on of the iterated object.
 */
public abstract class FilteredIterator implements Iterator {
	private Iterator originalIterator;

	public FilteredIterator(Iterator originalIterator) {
		super();
		this.originalIterator = originalIterator;
	}
	
	/**
	 * The filter method.
	 * @param o will never be null
	 * @return Object
	 */
	public abstract Object filter (Object o);
	
	/**
	 * The filter method which calls the filter method avoiding null objects.
	 * @see filter
	 * @param o
	 * @return Object
	 */
	protected Object filterInternal (Object o) {
		return o == null ? null : this.filter(o);
	}
	
	public boolean hasNext() {
		return this.originalIterator.hasNext();
	}

	public Object next() {
		return this.filterInternal(this.originalIterator.next());
	}

	public void remove() {
		this.originalIterator.remove();
	}

}
