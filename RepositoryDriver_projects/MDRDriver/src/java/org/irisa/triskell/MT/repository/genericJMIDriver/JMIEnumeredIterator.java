/*
 * $Id: JMIEnumeredIterator.java,v 1.1 2004-10-25 12:32:45 dvojtise Exp $
 * Authors : ffondeme dvojtise
 */
package org.irisa.triskell.MT.repository.genericJMIDriver;

import org.irisa.triskell.MT.repository.API.Java.*;

/**
 * Generic implementation of the repository API (org.irisa.triskell.MT.repository.API.Java.API)
 * This serve as the base for all Driver that uses JMI to connect to the repository 
 */
public class JMIEnumeredIterator 
    implements org.irisa.triskell.MT.repository.API.Java.ModelElementIterator
{
    private final org.irisa.triskell.MT.repository.genericJMIDriver.JMIMetaEnumeration enumeration;

    private int index = 0;


    public JMIEnumeredIterator(
        org.irisa.triskell.MT.repository.genericJMIDriver.JMIMetaEnumeration enumeration)
    {
		this.enumeration = enumeration;
    }

    public int size()
    {
		return this.enumeration.enumered.length;
    }

    public boolean hasNext()
    {
		return this.index < this.size();
    }

    public org.irisa.triskell.MT.repository.API.Java.ModelElement next()
        throws NoMoreElementException
    {
		return this.enumeration.enumered[this.index++];
    }

    public void reset()
    {
		this.index = 0;

    }
}
