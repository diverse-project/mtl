/*
 * Created on 21 sept. 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package org.irisa.triskell.MT.repository.MDRDriver.Java.events;


import org.irisa.triskell.MT.repository.API.Java.AssociationEventKind;
import org.irisa.triskell.MT.repository.MDRDriver.Java.MDRAPI;
import org.netbeans.api.mdr.events.AssociationEvent;
import org.netbeans.api.mdr.events.MDRChangeEvent;

/** This class is specific event related to associations. 
 * It provides more information about the kind of event that occured.
 * Note that it adapts the enumerations defined in MDR to the enumerations defined in the API.
 */
public class MDRAssociationEvent extends MDREvent implements org.irisa.triskell.MT.repository.API.Java.AssociationEvent
{
	private AssociationEventKind kind;
	
	/** */
	public MDRAssociationEvent (MDRAPI api, MDRChangeEvent event) 
	{
		super (api, event);

		if (event.getType() == AssociationEvent.EVENT_ASSOCIATION_ADD)
		{
			setKind (AssociationEventKind.associationAdd);
		}
		else if (event.getType() == AssociationEvent.EVENT_ASSOCIATION_REMOVE)
		{
			setKind (AssociationEventKind.associationRemove);
		}
		else if (event.getType() == AssociationEvent.EVENT_ASSOCIATION_SET)
		{
			setKind (AssociationEventKind.associationSet);
		}
		else 
		{
			api.getLog().error ("UNKNOWN TYPE OF EVENT...");
		}
	}

	/** */
	public AssociationEventKind getKind() 
	{
		return this.kind;
	}
	
	/** */
	public void setKind (AssociationEventKind kind)
	{
		this.kind = kind;
	}

	/** */
	public String getEndName ()
	{
		org.netbeans.api.mdr.events.AssociationEvent evt = (org.netbeans.api.mdr.events.AssociationEvent)getRef();
		return evt.getEndName();
	}
}
