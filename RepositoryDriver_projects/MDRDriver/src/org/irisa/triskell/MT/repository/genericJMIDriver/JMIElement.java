/**
 * $Id: JMIElement.java,v 1.2 2004-07-30 13:20:12 ffondeme Exp $
 * author : dvojtise
 */
package org.irisa.triskell.MT.repository.genericJMIDriver;

/**
 * Generic implementation of the repository API (org.irisa.triskell.MT.repository.API.Java.API)
 * This serve as the base for all Driver that uses JMI to connect to the repository 
 */
abstract public class JMIElement 
    implements org.irisa.triskell.MT.repository.API.Java.Element
{
    protected org.irisa.triskell.MT.repository.genericJMIDriver.JMIAPI api;

    protected final java.lang.Object ref;
    public java.lang.Object getRef () {
        return this.ref;
    }
    public int cardRef () {
        if ( this.ref == null ) return 0;
        else return 1;
    }


    public JMIElement(
        org.irisa.triskell.MT.repository.genericJMIDriver.JMIAPI api,
        java.lang.Object ref)
    {
		this.ref = ref;
		this.api = api;
		this.cache();
    }

    public org.irisa.triskell.MT.repository.API.Java.API getAPI()
    {
		return this.api;
    }

    public org.irisa.triskell.MT.repository.genericJMIDriver.JMIAPI getSpecificAPI()
    {
		return this.api;
    }
    
    protected abstract void cache();

    public abstract String toString();
}
