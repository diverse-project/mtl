package org.irisa.triskell.MT.repository.API.Java;

import org.irisa.triskell.MT.DataTypes.Java.*;
import java.lang.*;

abstract public class EventListenerFactory 
{

    public abstract org.irisa.triskell.MT.repository.API.Java.EventListener createAttributeEventListener(
        org.irisa.triskell.MT.repository.API.Java.EventListenerCommand cmd);

    public abstract org.irisa.triskell.MT.repository.API.Java.EventListener createAssociationEventListener(
        org.irisa.triskell.MT.repository.API.Java.EventListenerCommand cmd);

    public abstract org.irisa.triskell.MT.repository.API.Java.EventListener createInstanceEventListener(
        org.irisa.triskell.MT.repository.API.Java.EventListenerCommand cmd);

    public org.irisa.triskell.MT.repository.API.Java.EventListener createEventListenerByType(
        String type,
        org.irisa.triskell.MT.repository.API.Java.EventListenerCommand cmd)
    {
		EventListener result = null;
		if (type=="attribute")
		{
			result = this.createAttributeEventListener (cmd);
		}
		else if (type=="association")
		{
			result = this.createAssociationEventListener (cmd);
		}
		else if (type=="instance")
		{
			result = this.createInstanceEventListener (cmd);
		}
		else
		{
			System.err.println ("Impossible case in EventListenerFactory.createEventListenerByType...");
			System.exit (1);
		}
		return result;

    }
}
