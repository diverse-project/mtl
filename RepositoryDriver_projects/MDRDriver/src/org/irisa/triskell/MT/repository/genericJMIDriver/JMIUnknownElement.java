/*
 * $Id: JMIUnknownElement.java,v 1.2 2004-09-22 15:09:04 edrezen Exp $
 * Authors : ffondeme dvojtise
 */
package org.irisa.triskell.MT.repository.genericJMIDriver;

import org.irisa.triskell.MT.repository.API.Java.EventListener;

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
    
    /** */
	public void addListener    (EventListener listener) {}
	public void removeListener (EventListener listener) {}
}
