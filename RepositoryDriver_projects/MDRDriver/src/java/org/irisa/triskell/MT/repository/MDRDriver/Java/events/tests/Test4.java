/*
 * Created on 20 sept. 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package org.irisa.triskell.MT.repository.MDRDriver.Java.events.tests;

import org.irisa.triskell.MT.DataTypes.Java.defaultImpl.StringValueImpl;
import org.irisa.triskell.MT.repository.API.Java.MetaAttribute;
import org.irisa.triskell.MT.repository.API.Java.MetaClass;
import org.irisa.triskell.MT.repository.API.Java.ModelElement;


//////////////////////////////////////////////////////////////////
//
//////////////////////////////////////////////////////////////////
public class Test4 extends TestCommand 
{
	
	
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
//        Main.eventThread.addListenerToElement (
//            	mc_Class,
//    			null //Main.eventThread.createInstanceEventListener(null,null)
//    		);

//        getAPI().addListenerToElement (
//        	mc_Class,
//			Main.eventThread.getEventListenerFactory().createInstanceEventListener(null,null)
//		);

		////////////////////////////////////////////////////////////////////////////////
        // we perform some actions with the metaclass; we should have notifications
		////////////////////////////////////////////////////////////////////////////////
        ModelElement me_compositeur=null;
        for (int i=0; i<100; i++)
        {
            me_compositeur = mc_Class.instanciate (null,null);
        }

        for (int i=0; i<2; i++)
        {
            me_compositeur.setAttributeValue (me_compositeur,ma_name,new StringValueImpl(false,null, new Integer(i).toString()));
        }

        me_compositeur.delete();
        

		////////////////////////////////////////////////////////////////////////////////
		// we stop the API 
		////////////////////////////////////////////////////////////////////////////////
        getAPI().shutdown(null);

        return null;
	}
	
}
