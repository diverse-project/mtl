/*
 * $Id: JMIModelElementIterator.java,v 1.1 2004-10-25 12:32:56 dvojtise Exp $
 * Authors : ffondeme dvojtise
 */
package org.irisa.triskell.MT.repository.genericJMIDriver;

import javax.jmi.reflect.RefObject;
import java.util.Collection;

/**
 * Generic implementation of the repository API (org.irisa.triskell.MT.repository.API.Java.API)
 * This serve as the base for all Driver that uses JMI to connect to the repository 
 */
public class JMIModelElementIterator 
    implements org.irisa.triskell.MT.repository.API.Java.ModelElementIterator
{
    protected final org.irisa.triskell.MT.repository.genericJMIDriver.JMIAPI api;

    protected final Collection refObjects;

    private java.util.Iterator refIterator = null;


    public JMIModelElementIterator(
        org.irisa.triskell.MT.repository.genericJMIDriver.JMIAPI api,
        java.util.Collection ref)
    {
    	this.api = api;
    	this.refObjects = ref == null ? java.util.Arrays.asList(new RefObject [0]) : ref;
    }

    public int size()
    {
    	return this.refObjects.size();
    }

    public boolean hasNext()
    {
    	return this.getRefIterator().hasNext();
    }

    public org.irisa.triskell.MT.repository.API.Java.ModelElement next()
        throws org.irisa.triskell.MT.repository.API.Java.NoMoreElementException
    {
    	try {
			org.irisa.triskell.MT.repository.API.Java.ModelElement ret;
			do {
				ret = this.api.getModelElement((RefObject)this.getRefIterator().next());
			} while (ret == null);
			return ret;
    	} catch (java.util.NoSuchElementException x) {
    		throw new org.irisa.triskell.MT.repository.API.Java.NoMoreElementException();
    	}
    }

    public void reset()
    {
    	this.refIterator = null;
    }

    protected java.util.Iterator getRefIterator()
    {
		if (this.refIterator == null)
			this.refIterator = this.refObjects.iterator();
		return this.refIterator;
    }
}
