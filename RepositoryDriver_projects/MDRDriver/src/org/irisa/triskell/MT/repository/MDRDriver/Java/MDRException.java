package org.irisa.triskell.MT.repository.MDRDriver.Java;

import javax.jmi.xmi.*;
import javax.jmi.reflect.*;
import org.irisa.triskell.MT.DataTypes.Java.*;
import org.netbeans.api.mdr.*;
import org.irisa.triskell.MT.DataTypes.Java.defaultImpl.*;
import javax.jmi.model.*;
import java.lang.*;
import org.apache.log4j.*;
import org.irisa.triskell.MT.repository.API.Java.*;

public class MDRException 
    extends org.irisa.triskell.MT.repository.MDRDriver.Java.MDRUndefinedModelElement
{
    private final String message;


    public MDRException(
        String message,
        org.irisa.triskell.MT.repository.MDRDriver.Java.MDRAPI api)
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
		return (this == rhs) || ((rhs instanceof MDRException) && ((this.getErrorMessage() == null) && (((MDRException)rhs).getErrorMessage() == null) || this.getErrorMessage().equals(((MDRException)rhs).getErrorMessage())));
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
}
