/*
 * Created on 20 sept. 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package org.irisa.triskell.MT.repository.MDRDriver.Java.events.tests;

import java.io.IOException;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.irisa.triskell.MT.repository.API.Java.API;
import org.irisa.triskell.MT.repository.API.Java.MetaAssociation;
import org.irisa.triskell.MT.repository.API.Java.MetaAssociationEnd;
import org.irisa.triskell.MT.repository.API.Java.ModelElement;
import org.irisa.triskell.MT.repository.API.Java.ModelRole;
import org.irisa.triskell.MT.repository.MDRDriver.Java.MDRAPI;
import org.irisa.triskell.MT.repository.MDRDriver.Java.XmiMetamodel;
import org.irisa.triskell.MT.repository.MDRDriver.Java.XmiModel;


/**
 * @author edrezen
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public abstract class TestCommand 
{
	private MDRAPI api;
	private String rootPath = "."; 
	private org.apache.log4j.Logger log;

	
	/** */
	MDRAPI getAPI ()
	{
		if (this.api==null)
		{
			String modelsPath = "./ThirdParty/MDR/Test/models/";
			
			try {
				api = new MDRAPI (
						null, 
						new XmiMetamodel (modelsPath + "MOFmetamodel.xml", new String [] {"SimpleUmlMM"} ), 
						"MyModel", 
						new XmiModel (modelsPath + "instance.xmi", XmiModel.Write),
						true // synchronization
				);
			} 
			catch (Exception e) {
				e.printStackTrace();
			}
		}
		return this.api;
	}
	
	
	/** */
	public org.apache.log4j.Logger getLog () 
	{
		if (this.log == null)
		{ 
			String filePath;

			this.log = Logger.getLogger("MDRDriverTest");
			try {
				filePath = new java.io.File (rootPath + "/log4j_configuration.xml").getCanonicalPath();
				LogManager.resetConfiguration();
				DOMConfigurator.configure(filePath);
			} 
			catch (IOException e) {
				e.printStackTrace();
			}
		}
		return this.log;
	}
	
	
	/**
	 * @throws Exception */
	Object execute (Object context) throws Exception 
	{
		preExecute (context);
		mainExecute (context);
		return postExecute (context);
	}	
	
	/** */
	Object preExecute (Object context)
	{
		getLog().info("<" + this.getClass().getName() + ">");
		return null;
	}

	/**
	 * @throws Exception */
	Object mainExecute (Object context) throws Exception 
	{
		return null;
	}
	
	/** */
	Object postExecute (Object context)
	{
		getLog().info("</" + this.getClass().getName() + ">");
		return null;
	}

	/** */
    protected static void linkTwoModelElements (API api, ModelElement me_source, MetaAssociationEnd ae_source, ModelElement me_target, MetaAssociationEnd ae_target) throws Exception
    {
        MetaAssociation assoc = api.getMetaAssociationWithAssociationEnds(new MetaAssociationEnd[] { ae_source, ae_target });
        assoc.associateModelElements (null, new ModelRole [] { api.getRole(me_target, ae_target), api.getRole(me_source, ae_source) } );
    }


}
