/*
 * Created on 21 sept. 2004
 *
 */
package org.irisa.triskell.MT.repository.MDRDriver.Java.events;

import org.irisa.triskell.MT.repository.API.Java.EventListener;
import org.irisa.triskell.MT.repository.MDRDriver.Java.MDRAPI;


/** This class implements the EventListener interface for the MDR implementation of the API.
 * 
 * Actually, the implemented interface is the "update" method (called when a wanted event
 * occurs on the object holding the listener); the MDR implementation uses Command design
 * pattern (see GOF) which allows to share some behaviours intended to be reused. Note that
 * at the interface (i.e. EventListener) level, no such design choices are done.
 * 
 * This class is not intended to be instantiated (see children classes); it just contains
 * useful information for all children classes :
 *    - the API object is needed for retrieving the source object (in the API terms) from the MDR object
 *    - the command to be runned when the update occurs 
 * 
 */
abstract public class MDREventListener implements EventListener
{
	private MDRAPI api;
	private MDREventListenerCommand cmd;
	
	/** */
	public MDREventListener (MDRAPI api, MDREventListenerCommand cmd) 
	{
		this.api = api;
		this.cmd = cmd;
	}

	/** */
	public void update (org.irisa.triskell.MT.repository.API.Java.Event event)
	{
		cmd.execute (event);
	}
	
	/** */
	public MDRAPI getAPI ()
	{
		return this.api;
	}
}
