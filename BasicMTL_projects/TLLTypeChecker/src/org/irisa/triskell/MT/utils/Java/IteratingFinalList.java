/*
 * Created on May 27, 2003
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package org.irisa.triskell.MT.utils.Java;

import java.lang.ref.WeakReference;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 * @author ffondeme
 *
 * An unmodifiable collection representing an iterator.
 * The iterated elements are handled with strong references.
 * null value contents are ignored...
 */
public class IteratingFinalList implements List {
	protected LinkedList delegate;
	protected Iterator originalCollection;
	
	protected transient Collection iterators = null;
	
	public IteratingFinalList (Iterator it) {
		this.originalCollection = it;
		this.delegate = new LinkedList();
	}

	public LinkedList getDelegate() {
		return delegate;
	}

	public Iterator getOriginalCollection() {
		return originalCollection;
	}
	
	protected UnsupportedOperationException getNonQueryException () {
		return new UnsupportedOperationException("Cannot modify an IteratingFinalList");
	}

	public void add(int index, Object element) {
		throw this.getNonQueryException();
	}
	
	public boolean add(Object o) {
		throw this.getNonQueryException();
	}

	public boolean addAll(Collection c) {
		throw this.getNonQueryException();
	}

	public boolean addAll(int index, Collection c) {
		throw this.getNonQueryException();
	}

	public void clear() {
		throw this.getNonQueryException();
	}
	
	protected synchronized Object fillNext() {
		if ( this.originalCollection.hasNext()) {
			Object o;
			do {
				o = this.getOriginalCollection().next();
			} while ((o==null) && (this.getOriginalCollection().hasNext()));
			if (o != null)
				this.delegate.addLast(o);
			return o;
		} else
			return null;
	}
	
	protected synchronized boolean fillTo (int index) {
		boolean ret = false;
		while ((index >= this.delegate.size()) && (this.fillNext() != null))
			ret = true;
		if (ret)
			this.invalidateIterators();
		return ret;
	}
	
	protected synchronized boolean fillAll() {
		boolean ret = false;
		while (this.fillNext() != null)
			ret = true;
		if (ret)
			this.invalidateIterators();
		return ret;
	}
	
	protected synchronized void invalidateIterators () {
		if (this.iterators != null) {
			Iterator it = this.iterators.iterator();
			Itr i;
			while (it.hasNext()) {
				i = (Itr)((WeakReference)it.next()).get();
				if (i == null)
					it.remove();
				else
					i.invalidateDelegate();
			}
		}
	}

	public boolean contains(Object o) {
		if (o == null)
			return false;
		if (this.delegate.contains(o))
			return true;
		else {
			for (Object o2;;) {
				o2 = this.fillNext();
				if (o2 == null)
					return false;
				else {
					if (o2.equals(o))
						return true;
				}
			}
		}
	}
	
    public boolean containsAll(Collection c) {
		Iterator e = c.iterator();
		while (e.hasNext())
		    if(!contains(e.next()))
			return false;
	
		return true;
    }

	public Object get(int index) {
		this.fillTo(index);
		return this.delegate.get(index); 
	}

	public int indexOf(Object o) {
		if (o == null)
			return -1;
		int ret = this.delegate.indexOf(o);
		if (ret == -1) {
			ret = this.delegate.size();
			for (Object o2;;ret++) {
				o2 = this.fillNext();
				if (o2 == null)
					return -1;
				else if (o2.equals(o))
					return ret;
			}
		}
		return ret;
	}

	public boolean isEmpty() {
		return this.delegate.isEmpty() && ! this.getOriginalCollection().hasNext();
	}

	public Iterator iterator() {
		return this.listIterator();
	}

	public int lastIndexOf(Object o) {
		this.fillAll();
		return this.delegate.lastIndexOf(o);
	}

	public ListIterator listIterator() {
		return this.listIterator(0);
	}

	public ListIterator listIterator(int index) {
		Itr ret = new Itr(index);
		if (this.iterators == null)
			this.iterators = new LinkedList();
		this.iterators.add(new WeakReference(ret));
		return ret;
	}

	public Object remove(int index) {
		throw this.getNonQueryException();
	}
	
	public boolean remove(Object o) {
		throw this.getNonQueryException();
	}

	public boolean removeAll(Collection c) {
		throw this.getNonQueryException();
	}

	public boolean retainAll(Collection c) {
		throw this.getNonQueryException();
	}

	public Object set(int index, Object element) {
		throw this.getNonQueryException();
	}

	public int size() {
		this.fillAll();
		return this.delegate.size();
	}

	public List subList(int fromIndex, int toIndex) {
		this.fillTo(toIndex);
		return this.delegate.subList(fromIndex, toIndex);
	}

	public Object[] toArray() {
		this.fillAll();
		return this.delegate.toArray();
	}

	public Object[] toArray(Object[] a) {
		this.fillAll();
		return this.delegate.toArray(a);
	}
	
	protected class Itr implements ListIterator {
		protected int nextIndex;
		protected ListIterator delegate;
		
		public Itr (int index) {
			this.nextIndex = index;
		}
		
		public void invalidateDelegate () {
			this.delegate = null;
		}
		
		protected void checkDelegate () {
			synchronized (IteratingFinalList.this) {
				if (this.delegate == null)
					this.delegate = IteratingFinalList.this.delegate.listIterator(this.nextIndex);		
			}
		}
		
		public void add(Object o) {
			throw IteratingFinalList.this.getNonQueryException();
		}

		public boolean hasNext() {
			IteratingFinalList.this.fillTo(nextIndex);
			return IteratingFinalList.this.delegate.size() > nextIndex;
		}

		public boolean hasPrevious() {
	    	return nextIndex != 0;
		}

		public Object next() {
			if (this.hasNext()){
				this.checkDelegate();
				Object o = this.delegate.next();
				this.nextIndex++;
				return o;
			} else
				throw new NoSuchElementException();
		}

		public int nextIndex() {
		    return nextIndex;
		}

		public Object previous() {
			if (this.hasPrevious()){
				this.checkDelegate();
				Object o = this.delegate.previous();
				this.nextIndex--;
				return o;
			} else
				throw new NoSuchElementException();
		}
		
		public int previousIndex() {
		    return nextIndex-1;
		}

		public void remove() {
			throw IteratingFinalList.this.getNonQueryException();
		}

		public void set(Object o) {
			throw IteratingFinalList.this.getNonQueryException();
		}

	}

}
