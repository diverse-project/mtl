/**
 * $Id: JMIException.java,v 1.1 2004-10-25 12:32:54 dvojtise Exp $
 * Authors : ffondeme dvojtise
 */
package org.irisa.triskell.MT.repository.genericJMIDriver;

import org.irisa.triskell.MT.DataTypes.Java.*;
import org.irisa.triskell.MT.DataTypes.Java.commands.OclAny.OclAnyType;

/**
 * Generic implementation of the repository API (org.irisa.triskell.MT.repository.API.Java.API)
 * This serve as the base for all Driver that uses JMI to connect to the repository 
 */
public class JMIException 
    extends org.irisa.triskell.MT.repository.genericJMIDriver.JMIUndefinedModelElement
{
    private final String message;


    public JMIException(
        String message,
        org.irisa.triskell.MT.repository.genericJMIDriver.JMIAPI api)
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
		return (this == rhs) || ((rhs instanceof JMIException) && ((this.getErrorMessage() == null) && (((JMIException)rhs).getErrorMessage() == null) || this.getErrorMessage().equals(((JMIException)rhs).getErrorMessage())));
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
