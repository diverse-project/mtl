package org.irisa.triskell.MT.repository.API.Java;



public interface AttributeEvent 
    extends org.irisa.triskell.MT.repository.API.Java.Event
{

     org.irisa.triskell.MT.repository.API.Java.AttributeEventKind getKind();

     org.irisa.triskell.MT.DataTypes.Java.StringValue getAttributeName();

     org.irisa.triskell.MT.DataTypes.Java.Value getNewValue();

     org.irisa.triskell.MT.DataTypes.Java.Value getOldValue();
}
