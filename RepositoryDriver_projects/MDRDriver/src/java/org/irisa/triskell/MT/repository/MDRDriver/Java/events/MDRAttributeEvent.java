/*
 * Created on 21 sept. 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package org.irisa.triskell.MT.repository.MDRDriver.Java.events;

import org.irisa.triskell.MT.DataTypes.Java.StringValue;
import org.irisa.triskell.MT.DataTypes.Java.Value;
import org.irisa.triskell.MT.DataTypes.Java.defaultImpl.StringValueImpl;
import org.irisa.triskell.MT.DataTypes.Java.defaultImpl.UndefinedValueImpl;
import org.irisa.triskell.MT.repository.API.Java.AttributeEventKind;
import org.irisa.triskell.MT.repository.API.Java.Event;
import org.irisa.triskell.MT.repository.MDRDriver.Java.MDRAPI;
import org.netbeans.api.mdr.events.MDRChangeEvent;


/** This class is specific event related to attributes. 
 * It provides more information about the kind of event that occured.
 * Note that it adapts the enumerations defined in MDR to the enumerations defined in the API.
 */
public class MDRAttributeEvent extends MDREvent implements org.irisa.triskell.MT.repository.API.Java.AttributeEvent 
{
	private AttributeEventKind kind;
	
	/**
	 * @param phase */
	public MDRAttributeEvent (MDRAPI api) 
	{
		super (api);
		
	}

	/** */
	public Event init (MDRChangeEvent event)
	{
		this.ref = event;
		
		if (event.getType() == org.netbeans.api.mdr.events.AttributeEvent.EVENT_ATTRIBUTE_ADD)
		{
			setKind (AttributeEventKind.attributeAdd);
		}
		else if (event.getType() == org.netbeans.api.mdr.events.AttributeEvent.EVENT_ATTRIBUTE_REMOVE)
		{
			setKind (AttributeEventKind.attributeRemove);
		}
		else if (event.getType() == org.netbeans.api.mdr.events.AttributeEvent.EVENT_ATTRIBUTE_SET)
		{
			setKind (AttributeEventKind.attributeSet);
		}
		else 
		{
			api.getLog().error ("UNKNOWN TYPE OF EVENT...");
		}
		return this;
	}

	
	/** */
	public AttributeEventKind getKind() 
	{
		return this.kind;
	}
	
	/** */
	private void setKind (AttributeEventKind kind) 
	{
		this.kind = kind;
	}

	/** */
	public StringValue getAttributeName() 
	{
		org.netbeans.api.mdr.events.AttributeEvent evt = (org.netbeans.api.mdr.events.AttributeEvent)getRef();
		return new StringValueImpl (false,null,evt.getAttributeName());
	}

	/** */
	public Value getNewValue() 
	{
		Object value = ((org.netbeans.api.mdr.events.AttributeEvent)getRef()).getNewElement();
		return (value==null ? new UndefinedValueImpl (null) : getAPI().java2value (value,false,false,false) );  
	}

	/** */
	public Value getOldValue() 
	{
		Object value = ((org.netbeans.api.mdr.events.AttributeEvent)getRef()).getOldElement();
		return (value==null ? new UndefinedValueImpl (null) : getAPI().java2value (value,false,false,false) );  
	}
	
}
