/*
 * Created on 20 sept. 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package org.irisa.triskell.MT.repository.MDRDriver.Java.events.tests;

import org.irisa.triskell.MT.DataTypes.Java.defaultImpl.StringValueImpl;
import org.irisa.triskell.MT.repository.API.Java.MetaAssociationEnd;
import org.irisa.triskell.MT.repository.API.Java.MetaAttribute;
import org.irisa.triskell.MT.repository.API.Java.MetaClass;
import org.irisa.triskell.MT.repository.API.Java.ModelElement;



/**
 */
public class Test1 extends TestCommand 
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
        MetaClass mc_Attribute = getAPI().getMetaClass(new String [] {"SimpleUmlMM","Attribute"});

        MetaAttribute ma_name  = getAPI().getMetaAttribute("name",null);
        MetaAttribute ma_kind  = getAPI().getMetaAttribute("kind",null);
        
        MetaAssociationEnd mae_owner_Class         = getAPI().getMetaAssociationEnd("owner",mc_Class,mc_Attribute);
        MetaAssociationEnd mae_attribute_Attribute = getAPI().getMetaAssociationEnd("attribute",mc_Attribute,mc_Class);
		

		////////////////////////////////////////////////////////////////////////////////
        // we add an Attribute listener on one element
		////////////////////////////////////////////////////////////////////////////////
//        getAPI().addListenerToElement (
//    		mc_Class, 
//			getAPI().getEventListenerFactory().createAttributeEventListener (
//				new EventListenerCommand () {
//        			public void update (Event e) {
//        				getAPI().getLog().info ("ATTRIBUTE kind : " + ((AttributeEvent)e).getKind());
//        			}
//        		}
//			)
//		);

        
        ////////////////////////////////////////////////////////////////////////////////
        // we add an Instance listener on one element
		////////////////////////////////////////////////////////////////////////////////
//        getAPI().addListenerToElement (
//        	mc_Class,
//			getAPI().getEventListenerFactory().createInstanceEventListener (
//				new EventListenerCommand () {
//        			public void update (Event e) {
//        				getAPI().getLog().info ("INSTANCE kind : " + ((InstanceEvent)e).getKind());
//        			}
//        		}
//			)
//		);

        
        ////////////////////////////////////////////////////////////////////////////////
        // we add an Association listener on one element
		////////////////////////////////////////////////////////////////////////////////
//        getAPI().addListenerToElement (
//        	mc_Class,
//        	getAPI().getEventListenerFactory().createAssociationEventListener (
//				new EventListenerCommand () {
//        			public void update (Event e) {
//        				getAPI().getLog().info ("ASSOCIATION kind : " + ((AssociationEvent)e).getKind());
//        			}
//        		}
//			)
//		);

        
		////////////////////////////////////////////////////////////////////////////////
        // we perform some actions with the metaclass; we should have notifications
		////////////////////////////////////////////////////////////////////////////////
        ModelElement me_compositeur   = mc_Class.instanciate (null,null);
        ModelElement me_interprete    = mc_Class.instanciate (null,null);
        ModelElement me_oeuvre        = mc_Class.instanciate (null,null);
        ModelElement me_date          = mc_Class.instanciate (null,null);
        ModelElement me_date_creation = mc_Attribute.instanciate (null,null);

        me_compositeur.setAttributeValue (me_compositeur,ma_name,new StringValueImpl(false,null,"compositeur"));
        me_compositeur.setAttributeValue (me_compositeur,ma_kind,new StringValueImpl(false,null,"persistent"));
        me_interprete.setAttributeValue  (me_interprete,ma_name,new StringValueImpl(false,null,"interprete"));
        me_interprete.setAttributeValue  (me_interprete,ma_kind,new StringValueImpl(false,null,"persistent"));
        me_oeuvre.setAttributeValue      (me_oeuvre,ma_name,new StringValueImpl(false,null,"oeuvre"));
        me_oeuvre.setAttributeValue      (me_oeuvre,ma_kind,new StringValueImpl(false,null,"persistent"));
        me_date.setAttributeValue        (me_date,ma_name,new StringValueImpl(false,null,"date"));
        me_date.setAttributeValue        (me_date,ma_kind,new StringValueImpl(false,null,"persistent"));

        linkTwoModelElements (getAPI(), me_oeuvre, mae_owner_Class, me_date_creation, mae_attribute_Attribute);

        me_compositeur.delete();
        me_date_creation.delete();
        
        
		////////////////////////////////////////////////////////////////////////////////
		// we stop the API 
		////////////////////////////////////////////////////////////////////////////////
        getAPI().shutdown(null);

        return null;
	}
	
}
