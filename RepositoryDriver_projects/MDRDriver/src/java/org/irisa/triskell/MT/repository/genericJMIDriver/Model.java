/**
 * $Id: Model.java,v 1.1 2004-10-25 12:32:56 dvojtise Exp $
 * @author : dvojtise
 */

package org.irisa.triskell.MT.repository.genericJMIDriver;

/**
 * Generic implementation of the repository API (org.irisa.triskell.MT.repository.API.Java.API)
 * This serve as the base for all Driver that uses JMI to connect to the repository 
 */
abstract public class Model 
{

    public abstract void load(
        org.irisa.triskell.MT.repository.genericJMIDriver.JMIAPI api)
        throws java.lang.Exception;

    public abstract void store(
        org.irisa.triskell.MT.repository.genericJMIDriver.JMIAPI api)
        throws java.lang.Exception;
}
