/*
 * Created on Jun 3, 2003
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package org.irisa.triskell.MT.repository.API.Java.utils;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.irisa.triskell.MT.repository.API.Java.ModelElementIterator;
import org.irisa.triskell.MT.repository.API.Java.NoMoreElementException;

/**
 * @author ffondeme
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class ModelElementIteratorToJavaIteratorConverter implements Iterator {
	private final ModelElementIterator modelElementIterator;
	
	public ModelElementIteratorToJavaIteratorConverter (ModelElementIterator it) {
		this.modelElementIterator = it;
	}


	public void remove() {
		throw new UnsupportedOperationException("Cannot remove from Model Element Iterator.");
	}

	public boolean hasNext() {
		return this.getModelElementIterator().hasNext();
	}

	public Object next() {
		try {
			return this.getModelElementIterator().next();
		} catch (NoMoreElementException x) {
			throw new NoSuchElementException(x.getMessage());
		}
	}

	public ModelElementIterator getModelElementIterator() {
		return modelElementIterator;
	}

}
