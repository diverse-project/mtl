/*
 * Created on 21 sept. 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package org.irisa.triskell.MT.repository.MDRDriver.Java.events;

import javax.jmi.reflect.RefObject;

import org.irisa.triskell.MT.repository.API.Java.Element;
import org.irisa.triskell.MT.repository.API.Java.Event;
import org.irisa.triskell.MT.repository.MDRDriver.Java.MDRAPI;
import org.netbeans.api.mdr.events.MDRChangeEvent;

/** This class implements the Event interface (i.e. implements the "getSource" method)
 * 
 * It also holds some information :
 *    - the API object : needed for retrieving the API object from the MDR object
 *    - a reference to the true MDR event (used for retrieving the source object)
 * 
 * Children classes shares this implementation and add specific services according
 * to the type of event they support (attribute related events, instance related events...)
 */
abstract public class MDREvent implements Event 
{
	private MDRAPI api;
	private org.netbeans.api.mdr.events.MDRChangeEvent ref;
	
	/** */
	public MDREvent (MDRAPI api, MDRChangeEvent mdrEvent) 
	{
		this.api = api;
		this.ref = mdrEvent;
	}
	
	/** */
	protected MDRChangeEvent getRef ()
	{
		return this.ref;
	}

	/** */
	public Element getSource() 
	{
		return api.getModelElement ((RefObject) getRef().getSource());
	}
}
