package org.irisa.triskell.MT.repository.API.Java;

import org.irisa.triskell.MT.DataTypes.Java.*;
import java.lang.*;

public interface EventListenerFactory 
{

     org.irisa.triskell.MT.repository.API.Java.EventListener createAttributeEventListener(
        org.irisa.triskell.MT.repository.API.Java.EventListenerCommand preCommand,
        org.irisa.triskell.MT.repository.API.Java.EventListenerCommand postCommand);

     org.irisa.triskell.MT.repository.API.Java.EventListener createAssociationEventListener(
        org.irisa.triskell.MT.repository.API.Java.EventListenerCommand preCommand,
        org.irisa.triskell.MT.repository.API.Java.EventListenerCommand postCommand);

     org.irisa.triskell.MT.repository.API.Java.EventListener createInstanceEventListener(
        org.irisa.triskell.MT.repository.API.Java.EventListenerCommand preCommand,
        org.irisa.triskell.MT.repository.API.Java.EventListenerCommand postCommand);
}
