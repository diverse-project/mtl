package org.irisa.triskell.MT.repository.API.Java;



public interface EventListener 
{

     void preNotify(
        org.irisa.triskell.MT.repository.API.Java.Event event);

     void postNotify(
        org.irisa.triskell.MT.repository.API.Java.Event event);

     int isOfType();
}
