package org.irisa.triskell.MT.repository.MDRDriver.Java;

import javax.jmi.reflect.RefObject;
import java.util.List;
import java.util.ArrayList;

public class MDRConstrainedModelElementIterator 
    implements org.irisa.triskell.MT.repository.API.Java.ModelElementIterator
{
    protected org.irisa.triskell.MT.repository.API.Java.LookupConstraint constraint;
    public org.irisa.triskell.MT.repository.API.Java.LookupConstraint getConstraint () {
        return this.constraint;
    }
    public int cardConstraint () {
        if ( this.constraint == null ) return 0;
        else return 1;
    }

    private List computedModelElements = new ArrayList();

    private int currentIndex = 0;

    private final org.irisa.triskell.MT.repository.API.Java.ModelElementIterator delegate;


    public MDRConstrainedModelElementIterator(
        org.irisa.triskell.MT.repository.API.Java.ModelElementIterator delegate,
        org.irisa.triskell.MT.repository.API.Java.LookupConstraint constraint)
    {
		this.delegate = delegate;
		this.constraint = constraint;
    }

    public int size()
    {
		this.fillComputed();
		return this.computedModelElements.size();
    }

    public boolean hasNext()
    {
    	return (this.currentIndex < this.computedModelElements.size()) || (this.iterateNext() != null);
    }

    public org.irisa.triskell.MT.repository.API.Java.ModelElement next()
        throws org.irisa.triskell.MT.repository.API.Java.NoMoreElementException
    {
		org.irisa.triskell.MT.repository.API.Java.ModelElement ret;
		if (this.currentIndex < this.computedModelElements.size()) {
			ret = (org.irisa.triskell.MT.repository.API.Java.ModelElement)this.computedModelElements.get(this.currentIndex);
			this.currentIndex++;
		} else {
			ret = this.iterateNext();
			this.currentIndex = this.computedModelElements.size();
			if (ret == null)
    			throw new org.irisa.triskell.MT.repository.API.Java.NoMoreElementException();
		}
		return ret;
    }

    public void reset()
    {
    	this.currentIndex = 0;
    }

    protected org.irisa.triskell.MT.repository.API.Java.ModelElement iterateNext()
    {
		org.irisa.triskell.MT.repository.API.Java.ModelElement ret = null;
		try {
			while (this.delegate.hasNext() && (ret == null)) {
				ret = this.delegate.next();
				if (! this.getConstraint().match(ret))
					ret = null;
			}
		} catch (org.irisa.triskell.MT.repository.API.Java.NoMoreElementException x) {
			throw new RuntimeException ("InternalException.", x);
		}
		if (ret != null)
			this.computedModelElements.add(ret);
		return ret;
    }

    protected void fillComputed()
    {
		org.irisa.triskell.MT.repository.API.Java.ModelElement elt;
    	do {
    		elt = this.iterateNext();
    	} while (elt != null);
    }
}
