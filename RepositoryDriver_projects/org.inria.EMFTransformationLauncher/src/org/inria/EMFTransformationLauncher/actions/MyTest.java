/* $Id: MyTest.java,v 1.1 2004-03-10 17:18:39 jpthibau Exp $
 * Authors : 
 * 
 * Copyright 2003 - INRIA - LGPL license
 */
package org.inria.EMFTransformationLauncher.actions;

import org.inria.EMFDriver.EMFDriver;
import org.inria.EMFDriver.EditingDomainProvider;

//***************************************************************
//===============================================================
//change this import according to the application you want to run
import FileAccessTest.*;
//==============================================================


/******** CHANGE TO THE APPROPRIATE FACTORY FOR THE MODEL YOU WANT TO ACCESS
 * *************************************************************************
 * =========================================================================
 * @author jpthibau
 */
import org.eclipse.emf.ecore.impl.EcoreFactoryImpl;
import org.eclipse.emf.ecore.provider.EcoreItemProviderAdapterFactory;
//import org.eclipse.example.library.provider.LibraryItemProviderAdapterFactory;
/* *************************************************************************
* =========================================================================

/**
 * @author jpthibau
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class MyTest {

	public static void main(String[] args) {
		/******** CHANGE TO THE APPROPRIATE FACTORY FOR THE MODEL YOU WANT TO ACCESS
		 * *************************************************************************
		 * =========================================================================
		 */
		EditingDomainProvider provider1 = new EditingDomainProvider("ecore",new EcoreItemProviderAdapterFactory());
		System.out.println("PRECISE a ROOT ELEMENT => null by default after the EditingDomainProvider construction");
//		EditingDomainProvider provider2 = new EditingDomainProvider("library",new LibraryItemProviderAdapterFactory());
		/* *************************************************************************
		* =========================================================================
		*/
		//ADD HERE ANY EDITING DOMAIN YOU NEED
		EMFDriver.addEditingDomainProvider("ecore",provider1);
//		EMFDriver.addEditingDomain("library",provider2.getEditingDomain());

		//RUN THE APPROPRIATE APPLICATION BY CALLING ITS ENTRYPOINT METHOD
		try {
			new BMTLLib_FileAccessTest().BMTL_runTransformation();
		}
		catch (Throwable e) {System.out.println("Application terminated with  exception :"+e);
							 e.printStackTrace();}
		provider1.dispose();
//		provider2.dispose();
	}
}
