/* $Id: EMFException.java,v 1.1 2004-04-28 10:20:49 jpthibau Exp $
 * Authors : 
 * 
 * Copyright 2003 - INRIA - LGPL license
 */
package org.inria.EMFDriver;

import org.irisa.triskell.MT.DataTypes.Java.*;
import org.irisa.triskell.MT.DataTypes.Java.commands.OclAny.OclAnyType;

/**
 * @author jpthibau
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class EMFException
	extends EMFUndefinedModelElement
{
	private final String message;


	public EMFException(
		String message,
		EMFAPI api)
	{
		super(api);
		this.message = message;
	}

	/**
	  * @see org.irisa.triskell.MT.DataTypes.Java.ModelElementValue#getTheModelElement() 
	  */
	public String getTheModelElement()
	{
		return null;
	}

	public boolean equals(
		org.irisa.triskell.MT.DataTypes.Java.Value rhs)
	{
		return (this == rhs) || ((rhs instanceof EMFException) && ((this.getErrorMessage() == null) && (((EMFException)rhs).getErrorMessage() == null) || this.getErrorMessage().equals(((EMFException)rhs).getErrorMessage())));
	}

	public String getErrorMessage()
	{
		return this.message;
	}

	public String toString()
	{
		return "Exception " + this.getErrorMessage();
	}

	public String getUniqId()
	{
		return this.getErrorMessage();
	}

	public Type getType() {
		return OclAnyType.TheInstance;
	}

	public void delete() {
	}
	
	public void deleteTheModelElement () {
		this.delete();
	}


}
