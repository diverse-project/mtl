/* $Id: EMFModelElementIterator.java,v 1.1 2004-03-08 08:18:18 jpthibau Exp $
 * Authors : 
 * 
 * Copyright 2003 - INRIA - LGPL license
 */
package org.inria.EMFDriver;

import java.util.Collection;
import java.util.ArrayList;

/**
 * @author jpthibau
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class EMFModelElementIterator
	implements org.irisa.triskell.MT.repository.API.Java.ModelElementIterator
{
	protected final EMFAPI api;

	protected final Collection refObjects;

	private java.util.Iterator refIterator = null;


	public EMFModelElementIterator(
		EMFAPI api,
		java.util.Collection ref)
	{
		this.api = api;
		this.refObjects = ref == null ? new ArrayList() : ref;
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
				ret = this.api.getModelElement(this.getRefIterator().next());
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
