/*
* $Id: MTLPlugin.java,v 1.6 2004-06-24 09:23:33 sdzale Exp $
* Authors : ${user}
*
* Created on ${date}
* Copyright 2004 - INRIA - LGPL license
*/ 
package org.inria.mtl.plugin;

import java.io.InputStream;
import java.net.URL;
import java.net.MalformedURLException;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.eclipse.core.resources.IProject; 
import org.eclipse.core.resources.ISaveContext;
import org.eclipse.core.resources.ISaveParticipant;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IPluginDescriptor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.PreferenceConverter;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.jdt.ui.IWorkingCopyManager;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.inria.mtl.plugin.builders.MTLModel;
import org.inria.mtl.plugin.builders.MTLNature;
import org.inria.mtl.plugin.editors.utils.MTLEditorColorProvider;
import org.inria.mtl.plugin.preferences.PreferenceConstants;
import org.inria.mtl.plugin.preferences.Log4jPreferencePage;
import org.inria.mtl.plugin.editors.utils.MTLEditorEnvironment;
import org.inria.mtl.plugin.editors.MTLDocumentProviders;
import org.inria.mtl.plugin.views.controller.Controller;




/**
 * The main plugin class to be used in the desktop.
 */
public class MTLPlugin extends AbstractUIPlugin implements ISaveParticipant {
	//The shared instance.
	private static MTLPlugin plugin;
	//Resource bundle.
	private ResourceBundle resourceBundle;
	
	private MTLEditorEnvironment fMTLEditorEnvironment;
	
	private IWorkingCopyManager fWorkingCopyManager;
	
	//Flag qui permet de savoir que la compilation a été déclenchée par une action du Menu 
	public static boolean MenuAction =false;
	

	/**
	 * The maximum number of allowed proposals by category
	 */
	public final static int MAX_PROPOSALS = 200;

	
	//
	private URL baseURL;
	
	public final static String PLUGIN_ID = "org.irisa.mtl.plugin"; 
	
	public final static String MTL_PROBLEM = "org.irisa.mtl.plugin.MTLPlugin.mtlproblem";
	
	public static IPath[] srcFolders ;
	public static IPath[] libFolders ;
	public static IPath[] projFolders ;
	
	private MTLDocumentProviders fCompilationUnitDocumentProvider;
	
	
	

	
	/**
	 * The constructor.
	 * Creates the MTLPlugin and caches its default instance
	 * @param descriptor the plugin descriptor which the receiver is made from
	 */
	public MTLPlugin(IPluginDescriptor descriptor) {
		super(descriptor);
		plugin = this;
		initializeDefaultPluginPreferences();
		Controller.getInstance().acquaint(this);
		try {
			resourceBundle= ResourceBundle.getBundle("org.irisa.mtl.plugin.MTLPluginResources");
		} catch (MissingResourceException x) {
			resourceBundle = null;
		}
		baseURL = MTLPlugin.instance().getDescriptor().getInstallURL();
	}
	
	/**
	 * Returns the shared instance.
	 */
	public static MTLPlugin getDefault() {
		return plugin;
	}

	public static IWorkbenchWindow getActiveWorkbenchWindow() {
		return getDefault().getWorkbench().getActiveWorkbenchWindow();
	}
	
	
	public static Shell getActiveWorkbenchShell() {
		return getActiveWorkbenchWindow().getShell();
	}
	/**
	 * Returns the workspace instance.
	 */
	public static IWorkspace getWorkspace() {
		return ResourcesPlugin.getWorkspace();
	}

	/**
	 * Returns the string from the plugin's resource bundle,
	 * or 'key' if not found.
	 */
	public static String getResourceString(String key) {
		ResourceBundle bundle= MTLPlugin.getDefault().getResourceBundle();
		try {
			return bundle.getString(key);
		} catch (MissingResourceException e) {
			return key;
		}
	}

	/**
	 * Returns the plugin's resource bundle,
	 */
	public ResourceBundle getResourceBundle() {
		return resourceBundle;
	}
	
	public synchronized MTLEditorEnvironment getMTLEditorEnvironment() {
		if (fMTLEditorEnvironment == null)
			fMTLEditorEnvironment= new MTLEditorEnvironment(getPreferenceStore());
		return fMTLEditorEnvironment;
	}
	/**
		 * Gestion des erreurs
		 */

	 public static void error(String message, Throwable t){
			 getDefault().getLog().log(new Status(IStatus.ERROR, PLUGIN_ID, IStatus.ERROR, String.valueOf(message), t));
		 }
		 
	public static void log(int severity, String message) {
	   Status status = new Status(severity, PLUGIN_ID, IStatus.OK, message, null);
	   log(status);
	 }

	 public static void log(IStatus status) {
	   getDefault().getLog().log(status);
	 }
	 public static void log(Throwable e) {
	   log(new Status(IStatus.ERROR, PLUGIN_ID, IStatus.ERROR, "PHPeclipsePlugin.internalErrorOccurred", e)); //$NON-NLS-1$
	 }

/**
	 * @see AbstractUIPlugin#initializeDefaultPreferences
	 */
	 protected void initializeDefaultPreferences(IPreferenceStore store) {
		super.initializeDefaultPreferences(store);
		store.setDefault(PreferenceConstants.AUTO_COMPILE, false);
		store.setDefault(PreferenceConstants.SHOW_OUTPUT_IN_CONSOLE, false);
		PreferenceConverter.setDefault(store, PreferenceConstants.EDITOR_MULTI_LINE_COMMENT_COLOR, MTLEditorColorProvider.MULTI_LINE_COMMENT);
		PreferenceConverter.setDefault(store, PreferenceConstants.EDITOR_SINGLE_LINE_COMMENT_COLOR, MTLEditorColorProvider.SINGLE_LINE_COMMENT);
		PreferenceConverter.setDefault(store, PreferenceConstants.EDITOR_MTL_TAG_COLOR, MTLEditorColorProvider.MTLDOC_TAG);
		PreferenceConverter.setDefault(store, PreferenceConstants.EDITOR_MTL_KEYWORD_COLOR, MTLEditorColorProvider.KEYWORD);
		PreferenceConverter.setDefault(store, PreferenceConstants.EDITOR_MTL_VARIABLE_COLOR, MTLEditorColorProvider.VARIABLE);
		PreferenceConverter.setDefault(store, PreferenceConstants.MTL_FUNCTIONNAME,MTLEditorColorProvider.FUNCTION_NAME);
		PreferenceConverter.setDefault(store, PreferenceConstants.MTL_CONSTANT, MTLEditorColorProvider.CONSTANT);
		PreferenceConverter.setDefault(store, PreferenceConstants.MTL_TYPE, MTLEditorColorProvider.TYPE);
		PreferenceConverter.setDefault(store, PreferenceConstants.EDITOR_STRING_COLOR, MTLEditorColorProvider.STRING);
		
		store.setDefault(Log4jPreferencePage.P_PORT, 4445);
		
		PreferenceConverter.setDefault(store, PreferenceConstants.EDITOR_DEBUG_INDICATION_COLOR, new RGB(192, 192, 255));
		PreferenceConverter.setDefault(store, PreferenceConstants.EDITOR_INFO_INDICATION_COLOR, new RGB(255, 255, 255));
		PreferenceConverter.setDefault(store, PreferenceConstants.EDITOR_WARNING_INDICATION_COLOR, new RGB(255, 255, 192));
		PreferenceConverter.setDefault(store, PreferenceConstants.EDITOR_PROBLEM_INDICATION_COLOR, new RGB(192, 255, 192));
		PreferenceConverter.setDefault(store, PreferenceConstants.EDITOR_FATAL_INDICATION_COLOR, new RGB(255, 0, 0));

		PreferenceConstants.initializeDefaultValues(store);
	}
	
	/**
       *  called by framework after calling saving 
     * not implemented
     *@param  context
   	**/  
    public void doneSaving(ISaveContext context) { }
    
	/** 	 called by framework if required to rollback a save 
 	* 		not implemented
 	*		@param  context  Description of the Parameter
 	**/
	public void rollback(ISaveContext context) { }
	
 /**  called by framework prior calling saving 
 * not implemented
 *
 *@param  context            
 *@exception  CoreException 
 **/
	public void prepareToSave(ISaveContext context) throws CoreException { }

 /**  called by framework prior to saving, project data is persisted
 *
 *@param  context            
 *@exception  CoreException  
 **/
	public void saving(ISaveContext context) throws CoreException {
	switch (context.getKind()) {
		case ISaveContext.FULL_SAVE:
			System.out.println(this.getClass().getName() + " %%%%%% Saving");
			break;
		case ISaveContext.PROJECT_SAVE:
			// get the project related to this save operation
			IProject project = context.getProject();
		//	this.getModel(project).persist();
			// save its information, if necessary
			break;
		case ISaveContext.SNAPSHOT:
			// This operation needs to be really fast because
			// snapshots can be requested frequently by the
			// workspace.
			break;
		}
	}

 /**  Gets the model of the given project
 *
 *@param  proj  The project 
 *@return       The model value
 **/
public MTLModel getModel(IProject proj) {
	try {
		return ((MTLNature) proj.getNature(MTLNature.NATURE_ID)).getModel();
	} catch (CoreException e) {
		e.printStackTrace();
	}
	return null;
}

/**
	*  Gets the plugin singleton.
	*
	*@return    the default MTLPlugin instance
	*/
public static MTLPlugin instance() {
		return plugin;
	}

/**
  *  Gets the resourceStream for the given file
  *
  *@param  filename                 Name of file from plugin directory
  *@return                          The resourceStream value
  *@exception  java.io.IOException  Description of the Exception
  */
 public InputStream getResourceStream(String filename)
		  throws java.io.IOException {
	 return new URL(getBaseURL(), filename).openStream();
 }


 /**
  *  Gets the IPath object for the given file
  *
  *@param  filename                 Name of file from plugin directory
  *@return                          The file value
  *@exception  java.io.IOException  Description of the Exception
  */
 public IPath getFile(String filename) throws java.io.IOException {
	 return new Path(new URL(MTLPlugin.instance().getBaseURL(), filename).getPath());
 }


 /**
  *  Gets the location of the JAXBPlugin directory
  *
  *@return    The location value
  */
 public String getLocation() {
	 try {
		 return Platform.resolve(getBaseURL()).getFile();
	 } catch (Exception e) {
		 e.printStackTrace();
		 return null;
	 }
 }


 /**
  *  Gets the baseURL attribute of the MTLPlugin directory
  *
  *@return    The baseURL value
  */
 URL getBaseURL() {
	 return baseURL;
 }
 
public static IWorkbenchPage getActivePage() {
	return getDefault().internalGetActivePage();
  }

private IWorkbenchPage internalGetActivePage() {
		IWorkbenchWindow window= getWorkbench().getActiveWorkbenchWindow();
		if (window == null)
			return null;
		return getWorkbench().getActiveWorkbenchWindow().getActivePage();
	}
	
/**
	*  called by framework to initialise plugin
	*
	*@exception  CoreException  
	*/
   public void startup() throws CoreException {
	   super.startup();
	   ResourcesPlugin.getWorkspace().addSaveParticipant(this, this);

	   }
	   

// TODO: refactor this into a better method name !
public synchronized MTLDocumentProviders getCompilationUnitDocumentProvider() {
  if (fCompilationUnitDocumentProvider == null)
	fCompilationUnitDocumentProvider = new MTLDocumentProviders();
  return fCompilationUnitDocumentProvider;
}

  public ImageDescriptor getImageDescriptor(String name) {
		try {
			URL url= new URL(getDescriptor().getInstallURL(), name);
			return ImageDescriptor.createFromURL(url);
		} catch (MalformedURLException e) {
			return ImageDescriptor.getMissingImageDescriptor();
		}
	}
 	
}
