package org.irisa.triskell.MT.repository.API.Java;



public interface InstanceEvent 
    extends org.irisa.triskell.MT.repository.API.Java.Event
{

     org.irisa.triskell.MT.repository.API.Java.InstanceEventKind getKind();

     org.irisa.triskell.MT.DataTypes.Java.Value getInstance();
}
