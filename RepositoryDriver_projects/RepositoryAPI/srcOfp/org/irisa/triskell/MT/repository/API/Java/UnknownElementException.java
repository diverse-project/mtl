package org.irisa.triskell.MT.repository.API.Java;

import java.lang.Exception;

public class UnknownElementException 
    extends java.lang.Exception
{
    public final org.irisa.triskell.MT.repository.API.Java.Element unknownElement;
    public org.irisa.triskell.MT.repository.API.Java.Element getUnknownElement () {
        return this.unknownElement;
    }
    public int cardUnknownElement () {
        if ( this.unknownElement == null ) return 0;
        else return 1;
    }


    public UnknownElementException(
        org.irisa.triskell.MT.repository.API.Java.Element unknownElement)
    {
        super("Element " + unknownElement + " does not exists.");
    	this.unknownElement = unknownElement;
    }
}
