/*
 * Created on 12 août 2003
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package org.irisa.triskell.MT.utils.Java;

import java.util.Collection;
import java.util.Iterator;

/**
 * @author ffondeme
 *
 * A collection applying a filter method on each on of the real collection.
 */
public abstract class FilteredCollection implements Collection {
	private final Collection originalCollection;

	public FilteredCollection(Collection originalCollection) {
		super();
		this.originalCollection = originalCollection;
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
	
	/**
	 * The unfilter method ; unfilter(filter(o)).equals(o)
	 */
	public abstract Object unfilter (Object o);


	public int size() {
		return this.originalCollection.size();
	}

	public boolean isEmpty() {
		return this.originalCollection.isEmpty();
	}

	public boolean contains(Object o) {
		return this.originalCollection.contains(this.unfilter(o));
	}

	public Iterator iterator() {
		return new FilteredIterator (this.originalCollection.iterator()) {
			public Object filter (Object o) {
				return FilteredCollection.this.filter(o);
			}
			public Object filterInternal (Object o) {
				return FilteredCollection.this.filterInternal(o);
			}
		};
	}

	public Object[] toArray() {
		Object [] ret = this.originalCollection.toArray();
		for (int i = 0; i < ret.length; ++i)
			ret[i] = this.filter(ret[i]);
		return ret;
	}

	public Object[] toArray(Object[] a) {
		Iterator it = this.iterator();
		int i = 0;
		while (it.hasNext()) {
			a[i++] = it.next();
		}
		return a;
	}

	public boolean add(Object o) {
		return this.originalCollection.add(this.unfilter(o));
	}

	public boolean remove(Object o) {
		return this.originalCollection.remove(this.unfilter(o));
	}
	
	/**
	 * Returns the unfiltered collection ; this is used for methods containsAll, addAll, removeAll and retainAll
	 * @param c
	 * @return Collection
	 */
	protected Collection getUnfilteredCollection (Collection c) {
		return new FilteredCollection(c) {
			public Object filter (Object o) {
				return FilteredCollection.this.unfilter(o);
			}
			public Object unfilter (Object o) {
				return FilteredCollection.this.filter(o);
			}
		};
	}

	public boolean containsAll(Collection c) {
		return this.originalCollection.containsAll(this.getUnfilteredCollection(c));
	}

	public boolean addAll(Collection c) {
		return this.originalCollection.addAll(this.getUnfilteredCollection(c));
	}


	public boolean removeAll(Collection c) {
		return this.originalCollection.removeAll(this.getUnfilteredCollection(c));
	}

	public boolean retainAll(Collection c) {
		return this.originalCollection.retainAll(this.getUnfilteredCollection(c));
	}

	public void clear() {
		this.originalCollection.clear();
	}
}
