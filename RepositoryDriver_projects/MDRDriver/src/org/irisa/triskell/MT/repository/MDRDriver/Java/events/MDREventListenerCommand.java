/*
 * Created on 22 sept. 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package org.irisa.triskell.MT.repository.MDRDriver.Java.events;

import org.irisa.triskell.MT.repository.API.Java.Event;

/** The MDR implementation of the repository API uses commands for implementing event listeners.
 * The "update" method (called when an event of interest occurs) calls this command and gives to
 * it the concerned event.
 */
public interface MDREventListenerCommand 
{
	public void execute (Event e);
}
