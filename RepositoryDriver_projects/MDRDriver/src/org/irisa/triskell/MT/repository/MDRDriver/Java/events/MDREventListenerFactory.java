/*
 * Created on 23 sept. 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package org.irisa.triskell.MT.repository.MDRDriver.Java.events;

import org.irisa.triskell.MT.repository.API.Java.API;
import org.irisa.triskell.MT.repository.API.Java.EventListener;
import org.irisa.triskell.MT.repository.API.Java.EventListenerCommand;
import org.irisa.triskell.MT.repository.API.Java.EventListenerFactory;
import org.irisa.triskell.MT.repository.MDRDriver.Java.MDRAPI;

import sun.security.action.GetLongAction;

/**
 * @author edrezen
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class MDREventListenerFactory extends EventListenerFactory
{
	private MDRAPI api;
	/** */
	public MDREventListenerFactory (MDRAPI api)
	{
		this.api = api;
	}
	
	/** */
	public EventListener createAttributeEventListener (EventListenerCommand cmd)
	{
		return new MDRAttributeEventListener (api,cmd);
	}

	/** */
	public EventListener createAssociationEventListener(EventListenerCommand cmd) 
	{
		return new MDRAssociationEventListener (api,cmd);
	}

	/** */
	public EventListener createInstanceEventListener(EventListenerCommand cmd) 
	{
		return new MDRInstanceEventListener (api,cmd);
	}
}
