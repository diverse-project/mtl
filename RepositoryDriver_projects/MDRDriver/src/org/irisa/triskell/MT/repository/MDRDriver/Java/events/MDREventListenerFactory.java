/*
 * Created on 23 sept. 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package org.irisa.triskell.MT.repository.MDRDriver.Java.events;

import org.irisa.triskell.MT.repository.API.Java.EventListener;
import org.irisa.triskell.MT.repository.API.Java.EventListenerCommand;
import org.irisa.triskell.MT.repository.API.Java.EventListenerFactory;
import org.irisa.triskell.MT.repository.MDRDriver.Java.MDRAPI;

/**
 * @author edrezen
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class MDREventListenerFactory implements EventListenerFactory
{
	private MDRAPI api;
	/** */
	public MDREventListenerFactory (MDRAPI api)
	{
		this.api = api;
	}
	
	/** */
	public EventListener createAttributeEventListener (
		EventListenerCommand preCommand,
		EventListenerCommand postCommand
	)
	{
		return new MDRAttributeEventListener (api, preCommand, postCommand);
	}

	/** */
	public EventListener createAssociationEventListener (
		EventListenerCommand preCommand,
		EventListenerCommand postCommand
	)
	{
		return new MDRAssociationEventListener (api, preCommand, postCommand);
	}

	/** */
	public EventListener createInstanceEventListener (
		EventListenerCommand preCommand,
		EventListenerCommand postCommand
	)
	{
		return new MDRInstanceEventListener (api, preCommand, postCommand);
	}
}
