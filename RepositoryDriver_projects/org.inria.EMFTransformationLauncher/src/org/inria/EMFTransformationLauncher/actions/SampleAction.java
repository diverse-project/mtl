package org.inria.EMFTransformationLauncher.actions;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;
import org.eclipse.jface.dialogs.MessageDialog;

import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.common.notify.AdapterFactory;

import org.inria.EMFDriver.EMFDriver;
import org.inria.EMFDriver.EditingDomainProvider;
import org.eclipse.emf.ecore.*;

import java.util.Map;

//***************************************************************
//===============================================================
//change this import according to the application you want to run
import FileAccessTest.*;
import AllInstancesTest.*;
import EltsCreationAndSettingTest.*;
//==============================================================


/******** CHANGE TO THE APPROPRIATE FACTORY FOR THE MODEL YOU WANT TO ACCESS
 * *************************************************************************
 * =========================================================================
 * @author jpthibau
 */
import org.eclipse.emf.ecore.impl.EcoreFactoryImpl;
import org.eclipse.emf.ecore.impl.EcorePackageImpl;
import org.eclipse.emf.ecore.provider.EcoreItemProviderAdapterFactory;
import org.eclipse.example.library.impl.LibraryFactoryImpl;
import org.eclipse.example.library.LibraryPackage;
import org.eclipse.example.library.LibraryFactory;
import org.eclipse.example.library.provider.LibraryItemProviderAdapterFactory;
/* *************************************************************************
* =========================================================================
*/

/**
 * Our sample action implements workbench action delegate.
 * The action proxy will be created by the workbench and
 * shown in the UI. When the user tries to use the action,
 * this delegate will be created and execution will be 
 * delegated to it.
 * @see IWorkbenchWindowActionDelegate
 */
public class SampleAction implements IWorkbenchWindowActionDelegate {
	private IWorkbenchWindow window;
	/**
	 * The constructor.
	 */
	public SampleAction() {
	}

	/**
	 * The action has been activated. The argument of the
	 * method represents the 'real' action sitting
	 * in the workbench UI.
	 * @see IWorkbenchWindowActionDelegate#run
	 */
	public void run(IAction action) {
		/******** CHANGE TO THE APPROPRIATE FACTORY FOR THE MODEL YOU WANT TO ACCESS
		 * *************************************************************************
		 * =========================================================================
		 */
		EditingDomainProvider provider1 = new EditingDomainProvider("ecore",new EcoreItemProviderAdapterFactory());

		EditingDomainProvider provider2 = new EditingDomainProvider("library",new LibraryItemProviderAdapterFactory());

		Map registry = EPackage.Registry.INSTANCE;
		String ecorePackageURI = EcorePackage.eNS_URI;
		EcorePackage ecorePackage = (EcorePackage) registry.get(ecorePackageURI);
		EcoreFactory ecoreFactory = ecorePackage.getEcoreFactory();
		provider1.setRootElement(ecoreFactory.createEPackage());

		String libraryPackageURI = LibraryPackage.eNS_URI;
		LibraryPackage libraryPackage = (LibraryPackage) registry.get(libraryPackageURI);
		LibraryFactory libraryFactory = libraryPackage.getLibraryFactory();
		provider2.setRootElement(libraryFactory.createLibrary());
		/* *************************************************************************
		* =========================================================================
		*/
		//ADD HERE ANY EDITING DOMAIN YOU NEED
		EMFDriver.addEditingDomainProvider("ecore",provider1);
		EMFDriver.addEditingDomainProvider("library",provider2);

		//RUN THE APPROPRIATE APPLICATION BY CALLING ITS ENTRYPOINT METHOD
		try {
//			new BMTLLib_FileAccessTest().BMTL_runTransformation();
//			new BMTLLib_AllInstancesTest().BMTL_runTransformation();
			new BMTLLib_EltsCreationAndSettingTest().BMTL_runTransformation();
		}
		catch (Throwable e) {System.out.println("Application terminated with  exception :"+e);
							 e.printStackTrace();}
		provider1.dispose();
		provider2.dispose();
	}

	/**
	 * Selection in the workbench has been changed. We 
	 * can change the state of the 'real' action here
	 * if we want, but this can only happen after 
	 * the delegate has been created.
	 * @see IWorkbenchWindowActionDelegate#selectionChanged
	 */
	public void selectionChanged(IAction action, ISelection selection) {
	}

	/**
	 * We can use this method to dispose of any system
	 * resources we previously allocated.
	 * @see IWorkbenchWindowActionDelegate#dispose
	 */
	public void dispose() {
	}

	/**
	 * We will cache window object in order to
	 * be able to provide parent shell for the message dialog.
	 * @see IWorkbenchWindowActionDelegate#init
	 */
	public void init(IWorkbenchWindow window) {
		this.window = window;
	}
}