/*
 * Created on 20 sept. 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package org.irisa.triskell.MT.repository.MDRDriver.Java.events.tests;

import org.irisa.triskell.MT.DataTypes.Java.defaultImpl.StringValueImpl;
import org.irisa.triskell.MT.repository.API.Java.AttributeEvent;
import org.irisa.triskell.MT.repository.API.Java.Element;
import org.irisa.triskell.MT.repository.API.Java.Event;
import org.irisa.triskell.MT.repository.API.Java.EventListenerCommand;
import org.irisa.triskell.MT.repository.API.Java.InstanceEvent;
import org.irisa.triskell.MT.repository.API.Java.InstanceEventKind;
import org.irisa.triskell.MT.repository.API.Java.MetaAttribute;
import org.irisa.triskell.MT.repository.API.Java.MetaClass;
import org.irisa.triskell.MT.repository.API.Java.ModelElement;


//////////////////////////////////////////////////////////////////
//
//////////////////////////////////////////////////////////////////
public class Test5 extends TestCommand 
{
	boolean isSynchro = true;
	
	/** */
	public Object mainExecute (Object context) throws Exception  
	{
		////////////////////////////////////////////////////////////////////////////////
		// we start the API 
		////////////////////////////////////////////////////////////////////////////////
		getAPI().startup(null); 
		
		////////////////////////////////////////////////////////////////////////////////
		// we retrieve some meta elements from the API
		////////////////////////////////////////////////////////////////////////////////
        MetaClass     mc_Class = getAPI().getMetaClass(new String [] {"SimpleUmlMM","Class"});
        MetaAttribute ma_name  = getAPI().getMetaAttribute("name",null);
        
        
        ////////////////////////////////////////////////////////////////////////////////
        // we add an Instance listener on one element
		////////////////////////////////////////////////////////////////////////////////
        getAPI().addListenerToElement (
        	mc_Class,
			getAPI().getEventListenerFactory().createInstanceEventListener (
				new EventListenerCommand () {
					public void execute(Event event) {
						//System.out.println ("PRE INSTANCE");
					}
				},
				new EventListenerCommand () {
					public void execute(Event event) {
						InstanceEvent theEvent = (InstanceEvent)event;
						System.out.println ("POST INSTANCE : " + theEvent.getKind());

						if (theEvent.getKind()==InstanceEventKind.instanceCreate)
						{
							System.out.println (theEvent.getInstance().toString());
							getAPI().addListenerToElement (
								(Element)theEvent.getInstance(),
								getAPI().getEventListenerFactory().createAttributeEventListener (
									new EventListenerCommand () {
										public void execute(Event event) {
											//System.out.println ("PRE ATTRIBUTE");
										}
									},
									new EventListenerCommand () {
										public void execute(Event event) {
											AttributeEvent theEvent = (AttributeEvent)event;
											System.out.println ("POST ATTRIBUTE : " + theEvent.getAttributeName() + " : " + theEvent.getOldValue() + " - " + theEvent.getNewValue());
										}
									}
								)
							);
						}
					}
				}
			)
		);

		////////////////////////////////////////////////////////////////////////////////
        // we perform some actions with the metaclass; we should have notifications
		////////////////////////////////////////////////////////////////////////////////
        ModelElement me_compositeur=null;
        for (int i=0; i<500; i++)
        {
            me_compositeur = mc_Class.instanciate (null,null);
            me_compositeur.setAttributeValue (me_compositeur,ma_name,new StringValueImpl(false,null, new Integer(i).toString()));
            me_compositeur.delete();
        }

		////////////////////////////////////////////////////////////////////////////////
		// we stop the API 
		////////////////////////////////////////////////////////////////////////////////
        getAPI().shutdown(null);

        return null;
	}
	
}
