/* $Id: EMFUnknownElement.java,v 1.1 2004-04-28 10:20:50 jpthibau Exp $
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
public class EMFUnknownElement
	implements org.irisa.triskell.MT.repository.API.Java.Element
{
	private String description;

	private EMFAPI api;


	public EMFUnknownElement(
		EMFAPI api,
		String description)
	{
		this.api = api;
		this.description = description;
	}

	public org.irisa.triskell.MT.repository.API.Java.API getAPI()
	{
		return this.api;
	}

	public String toString()
	{
		return this.description;
	}

}
