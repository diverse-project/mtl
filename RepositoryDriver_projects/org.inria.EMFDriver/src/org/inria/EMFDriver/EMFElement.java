/* $Id: EMFElement.java,v 1.1 2004-03-08 08:18:18 jpthibau Exp $
 * Authors : 
 * 
 * Copyright 2003 - INRIA - LGPL license
 */
package org.inria.EMFDriver;

/**
 * @author jpthibau
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public abstract class EMFElement
	implements org.irisa.triskell.MT.repository.API.Java.Element
 {
	protected EMFAPI api;

	protected final java.lang.Object ref;
	public java.lang.Object getRef () {
		return this.ref;
	}
	public int cardRef () {
		if ( this.ref == null ) return 0;
		else return 1;
	}


	public EMFElement(
		EMFAPI api,
		java.lang.Object ref)
	{
		this.ref = ref;
		this.api = api;
		if (ref != null)
			api.setElement(this, ref);
	}

	public org.irisa.triskell.MT.repository.API.Java.API getAPI()
	{
		return this.api;
	}

	public EMFAPI getSpecificAPI()
	{
		return this.api;
	}

	public abstract String toString();

}
