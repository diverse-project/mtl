package org.irisa.triskell.MT.repository.API.Java;



public interface AssociationEvent 
    extends org.irisa.triskell.MT.repository.API.Java.Event
{

     org.irisa.triskell.MT.repository.API.Java.AssociationEventKind getKind();

     org.irisa.triskell.MT.DataTypes.Java.StringValue getEndName();
}
