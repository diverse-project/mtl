/*
 * Created on 21 sept. 2004
 *
 */
package org.irisa.triskell.MT.repository.MDRDriver.Java.events;

import org.irisa.triskell.MT.repository.API.Java.Event;
import org.irisa.triskell.MT.repository.API.Java.EventListener;
import org.irisa.triskell.MT.repository.API.Java.EventListenerCommand;
import org.irisa.triskell.MT.repository.MDRDriver.Java.MDRAPI;
import org.netbeans.api.mdr.events.MDRChangeEvent;
import org.netbeans.api.mdr.events.MDRPreChangeListener;


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
abstract public class MDREventListener implements EventListener, MDRPreChangeListener
{
	private MDRAPI api;
	private EventListenerCommand preCommand;
	private EventListenerCommand postCommand;
	
	////////////////////////////////////////////////////////////
	// CONSTRUCTORS
	////////////////////////////////////////////////////////////

	/** */
	public MDREventListener (
		MDRAPI api, 
		EventListenerCommand preCommand,
		EventListenerCommand postCommand 
	) 
	{
		setAPI (api);
		setPreCommand  (preCommand);
		setPostCommand (postCommand);
	}


	////////////////////////////////////////////////////////////
	// METHODS to be eventually redefined
	////////////////////////////////////////////////////////////

	/** */
	public void preNotify (Event event) 
	{
		if (preCommand!=null)
		{
			preCommand.execute (event);
		}
	}

	/** */
	public void postNotify(Event event) 
	{
		if (postCommand!=null)
		{
			postCommand.execute (event);
		}
	}	

	
	////////////////////////////////////////////////////////////
	// MDR METHODS FOR EVENTS HANDLING
	////////////////////////////////////////////////////////////

	private MDREvent theEvent = null;
	
	/** */
	synchronized public void plannedChange (MDRChangeEvent event)  
	{
		if (theEvent != null)
		{
			try {
				/*theEvent = null;*/
				throw new Exception ("SYNCHRONIZATION PROCESS FAILED (plannedChange)... " + event.toString());
			} 
			catch (Exception e) {
				e.printStackTrace();
			}
		} 
		else
		{
			// we may have to change the priority of the event dispatcher thread
			// note that is done only once
			getAPI().changeEventDispatcherPriority();
			
			// we create the event
			theEvent = (MDREvent)createEvent ();
			
			// we call the preNotify method (we init the API event with the MDR event)
			preNotify (theEvent.init(event));
			
			// we pause the driver thread 
			Thread.yield();
		}
	}

	/** */
	synchronized public void change (MDRChangeEvent event) 
	{
		// the MDR event should be the same as the one in 'plannedChange'
		if (theEvent.getRef() != event)
		{
			try {
				throw new Exception ("SYNCHRONIZATION PROCESS FAILED (change)... " + event.toString());
			} 
			catch (Exception e) {
				e.printStackTrace();
			}
		}

		// we call the postNotify method (we init the API event with the MDR event)
		postNotify (theEvent.init(event));
		
		// we resume the current event
		theEvent = null;

		Thread.yield();
	}
	
	/** */
	public void changeCancelled (MDRChangeEvent event) 
	{
	}

	/** */
	abstract public Event createEvent (); 

	
	////////////////////////////////////////////////////////////
	// GETTERS AND SETTERS
	////////////////////////////////////////////////////////////
	
	/** */
	public MDRAPI getAPI ()
	{
		return this.api;
	}
	
	/** */
	protected void setAPI (MDRAPI api)
	{
		this.api = api;
	}
	
	/** */
	public EventListenerCommand getPostCommand() 
	{
		return postCommand;
	}
	/** */
	protected void setPostCommand(EventListenerCommand postCommand) 
	{
		this.postCommand = postCommand;
	}

	/** */
	public EventListenerCommand getPreCommand() 
	{
		return preCommand;
	}

	/** */
	protected void setPreCommand(EventListenerCommand preCommand) 
	{
		this.preCommand = preCommand;
	}
}
