/*
 * Created on 21 sept. 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package org.irisa.triskell.MT.repository.MDRDriver.Java.events;

import org.irisa.triskell.MT.DataTypes.Java.Value;
import org.irisa.triskell.MT.DataTypes.Java.defaultImpl.UndefinedValueImpl;
import org.irisa.triskell.MT.repository.API.Java.Event;
import org.irisa.triskell.MT.repository.API.Java.InstanceEventKind;
import org.irisa.triskell.MT.repository.MDRDriver.Java.MDRAPI;
import org.netbeans.api.mdr.events.MDRChangeEvent;

/** This class is specific event related to instances. 
 * It provides more information about the kind of event that occured.
 * Note that it adapts the enumerations defined in MDR to the enumerations defined in the API.
 */
public class MDRInstanceEvent extends MDREvent implements org.irisa.triskell.MT.repository.API.Java.InstanceEvent 
{
	private InstanceEventKind kind;
	
	/** */
	public MDRInstanceEvent (MDRAPI api) 
	{
		super (api);
	}

	/** */
	public Event init (MDRChangeEvent event)
	{
		this.ref = event;

		if (event.getType() == org.netbeans.api.mdr.events.InstanceEvent.EVENT_INSTANCE_CREATE) 
		{
			setKind (InstanceEventKind.instanceCreate);
		}
		else if (event.getType() == org.netbeans.api.mdr.events.InstanceEvent.EVENT_INSTANCE_DELETE)
		{
			setKind (InstanceEventKind.instanceDelete);
		}
		else
		{
			api.getLog().error ("UNKNOWN TYPE OF EVENT...");
		}
		
		return this;
	}

	/** */
	public InstanceEventKind getKind() 
	{
		return this.kind;
	}
	
	/** */
	public void setKind (InstanceEventKind kind)
	{
		this.kind = kind;
	}
	
	/** */
	public Value getInstance () 
	{
		Object value = ((org.netbeans.api.mdr.events.InstanceEvent)getRef()).getInstance();
		return (value==null ? new UndefinedValueImpl (null) : getAPI().java2value (value,false,false,false) );
	}
	
}
