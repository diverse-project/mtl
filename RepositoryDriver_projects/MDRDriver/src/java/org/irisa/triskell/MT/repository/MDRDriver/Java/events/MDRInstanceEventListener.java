/*
 * Created on 21 sept. 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package org.irisa.triskell.MT.repository.MDRDriver.Java.events;

import org.irisa.triskell.MT.repository.API.Java.Event;
import org.irisa.triskell.MT.repository.API.Java.EventListenerCommand;
import org.irisa.triskell.MT.repository.MDRDriver.Java.MDRAPI;


/** This class is a special event listener that listens for instances related events.
 * It inherits :
 *    - MDREventListener  : common behavior and impementation of the "update" method
 *    - MDRChangeListener : the class needs to be the true MDR listener, i.e. it needs
 *                          to be be notified when events occurs (implemting "change" method)  
 *  
 * The implemented MDR specific "change" method filters events of interest.
 * 
 * Note also that this class creates MDRInstanceEvent objects that are proxies for the 
 * true MDR events (i.e. MDRChangeEvent)
 */
public class MDRInstanceEventListener extends MDREventListener 
{
	/** */
	public MDRInstanceEventListener (		
		MDRAPI api, 
		EventListenerCommand preCommand,
		EventListenerCommand postCommand
	) 
	{
		super (api, preCommand, postCommand);
	}


	/** */
	public Event createEvent () 
	{
		return new MDRInstanceEvent (getAPI());
	}
	
	

	/** */
	public int isOfType() 
	{
		return org.netbeans.api.mdr.events.InstanceEvent.EVENTMASK_INSTANCE;
	}
}
