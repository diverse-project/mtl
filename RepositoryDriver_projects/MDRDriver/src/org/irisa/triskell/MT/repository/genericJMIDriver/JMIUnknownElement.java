/*
 * $Id: JMIUnknownElement.java,v 1.1 2004-02-16 15:44:34 dvojtise Exp $
 * Authors : ffondeme dvojtise
 */
package org.irisa.triskell.MT.repository.genericJMIDriver;

/**
 * Generic implementation of the repository API (org.irisa.triskell.MT.repository.API.Java.API)
 * This serve as the base for all Driver that uses JMI to connect to the repository 
 */
public class JMIUnknownElement 
    implements org.irisa.triskell.MT.repository.API.Java.Element
{
    private String description;

    private org.irisa.triskell.MT.repository.genericJMIDriver.JMIAPI api;


    public JMIUnknownElement(
        org.irisa.triskell.MT.repository.genericJMIDriver.JMIAPI api,
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
