package org.inria.mtl;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IPluginDescriptor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.PreferenceConverter;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.plugin.*;
import org.inria.mtl.builders.MTLModel;
import org.inria.mtl.builders.MTLNature;
import org.inria.mtl.editors.MTLDocumentProviders;
import org.inria.mtl.editors.utils.MTLEditorColorProvider;
import org.inria.mtl.editors.utils.MTLEditorEnvironment;
import org.inria.mtl.preferences.Log4jPreferencePage;
import org.inria.mtl.preferences.PreferencesConstants;
import org.inria.mtl.views.controller.Controller;
import org.osgi.framework.BundleContext;

import java.io.InputStream;
import java.net.URL;
import java.util.*;

/**
 * The main plugin class to be used in the desktop.
 */
public class MTLPlugin extends AbstractUIPlugin {
	//The shared instance.
	private static MTLPlugin plugin;
	//Resource bundle.
	private ResourceBundle resourceBundle; 
	
	private MTLEditorEnvironment fMTLEditorEnvironment;
	
	//private IWorkingCopyManager fWorkingCopyManager;
	
	
	public final static String PLUGIN_ID = "org.irisa.mtl";
	
	public final static String MTL_PROBLEM = "org.irisa.mtl.MTLPlugin.mtlproblem";
	
//	Flag qui permet de savoir que la compilation a �t� d�clench�e par une action du Menu 
	public static boolean MenuAction =false;
	
	//
	private static  URL baseURL;
	
	/**
	 * The maximum number of allowed proposals by category in help completion
	 */
	public final static int MAX_PROPOSALS = 200;
	
	
	public static IPath[] srcFolders ;
	public static IPath[] libFolders ;
	public static IPath[] projFolders ;
	
	private MTLDocumentProviders fCompilationUnitDocumentProvider;
	
	/**
	 * The constructor.
	 */
	public MTLPlugin() {
		super();
		plugin = this;
		try {
			resourceBundle = ResourceBundle.getBundle("org.inria.mtl.MTLPluginResources");
		} catch (MissingResourceException x) {
			resourceBundle = null;
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
	 * The constructor.
	 * Creates the MTLPlugin and caches its default instance
	 * @param descriptor the plugin descriptor which the receiver is made from
	 */
	public MTLPlugin(IPluginDescriptor descriptor) {
		super(descriptor);
		plugin = this;
		initializeDefaultPluginPreferences();
		baseURL = MTLPlugin.instance().getDescriptor().getInstallURL();
		Controller.getInstance().acquaint(this);
		try {
			resourceBundle= ResourceBundle.getBundle("org.irisa.mtl.MTLPluginResources");
		} catch (MissingResourceException x) {
			resourceBundle = null;
		}
		
	}
	
	/**
	 * Returns the workspace instance.
	 */
	public static IWorkspace getWorkspace() {
		return ResourcesPlugin.getWorkspace();
	}
	
	public static IWorkbenchWindow getActiveWorkbenchWindow() {
		return getDefault().getWorkbench().getActiveWorkbenchWindow();
	}
	
	
	public static Shell getActiveWorkbenchShell() {
		return getActiveWorkbenchWindow().getShell();
	}
	/**
	 * This method is called upon plug-in activation
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
	}

//	 TODO: refactor this into a better method name !
	public synchronized MTLDocumentProviders getCompilationUnitDocumentProvider() {
	  if (fCompilationUnitDocumentProvider == null)
		fCompilationUnitDocumentProvider = new MTLDocumentProviders();
	  return fCompilationUnitDocumentProvider;
	}

	/**
	 * This method is called when the plug-in is stopped
	 */
	public void stop(BundleContext context) throws Exception {
		super.stop(context);
	}

	/**
	 * Returns the shared instance.
	 */
	public static MTLPlugin getDefault() {
		return plugin;
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
	 * Returns the string from the plugin's resource bundle,
	 * or 'key' if not found.
	 */
	public static String getResourceString(String key) {
		ResourceBundle bundle = MTLPlugin.getDefault().getResourceBundle();
		try {
			return (bundle != null) ? bundle.getString(key) : key;
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
	
	/**
	 * @see AbstractUIPlugin#initializeDefaultPreferences
	 */
	 protected void initializeDefaultPreferences(IPreferenceStore store) {
		super.initializeDefaultPreferences(store);
		store.setDefault(PreferencesConstants.AUTO_COMPILE, false);
		store.setDefault(PreferencesConstants.SHOW_OUTPUT_IN_CONSOLE, false);
		PreferenceConverter.setDefault(store, PreferencesConstants.EDITOR_MULTI_LINE_COMMENT_COLOR, MTLEditorColorProvider.MULTI_LINE_COMMENT);
		PreferenceConverter.setDefault(store, PreferencesConstants.EDITOR_SINGLE_LINE_COMMENT_COLOR, MTLEditorColorProvider.SINGLE_LINE_COMMENT);
		PreferenceConverter.setDefault(store, PreferencesConstants.EDITOR_MTL_TAG_COLOR, MTLEditorColorProvider.MTLDOC_TAG);
		PreferenceConverter.setDefault(store, PreferencesConstants.EDITOR_MTL_KEYWORD_COLOR, MTLEditorColorProvider.KEYWORD);
		PreferenceConverter.setDefault(store, PreferencesConstants.EDITOR_MTL_VARIABLE_COLOR, MTLEditorColorProvider.VARIABLE);
		PreferenceConverter.setDefault(store, PreferencesConstants.MTL_FUNCTIONNAME,MTLEditorColorProvider.FUNCTION_NAME);
		PreferenceConverter.setDefault(store, PreferencesConstants.MTL_CONSTANT, MTLEditorColorProvider.CONSTANT);
		PreferenceConverter.setDefault(store, PreferencesConstants.MTL_TYPE, MTLEditorColorProvider.TYPE);
		PreferenceConverter.setDefault(store, PreferencesConstants.EDITOR_STRING_COLOR, MTLEditorColorProvider.STRING);
		
		store.setDefault(Log4jPreferencePage.P_PORT, 4445);
		
		PreferenceConverter.setDefault(store, PreferencesConstants.EDITOR_DEBUG_INDICATION_COLOR, new RGB(192, 192, 255));
		PreferenceConverter.setDefault(store, PreferencesConstants.EDITOR_INFO_INDICATION_COLOR, new RGB(255, 255, 255));
		PreferenceConverter.setDefault(store, PreferencesConstants.EDITOR_WARNING_INDICATION_COLOR, new RGB(255, 255, 192));
		PreferenceConverter.setDefault(store, PreferencesConstants.EDITOR_PROBLEM_INDICATION_COLOR, new RGB(192, 255, 192));
		PreferenceConverter.setDefault(store, PreferencesConstants.EDITOR_FATAL_INDICATION_COLOR, new RGB(255, 0, 0));

		PreferencesConstants.initializeDefaultValues(store);
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
	   log(new Status(IStatus.ERROR, PLUGIN_ID, IStatus.ERROR, "MTLPlugin.internalErrorOccurred", e)); //$NON-NLS-1$
	 } 
	 /**
	  *  Gets the baseURL attribute of the MTLPlugin directory
	  *
	  *@return    The baseURL value
	  */
	 public static URL getBaseURL() {
	 	URL installUrl=getDefault().getBundle().getEntry("/");
		 return installUrl;
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
	  *  Gets the location of the MTLPlugin directory
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
	 
		public synchronized MTLEditorEnvironment getMTLEditorEnvironment() {
			if (fMTLEditorEnvironment == null)
				fMTLEditorEnvironment= new MTLEditorEnvironment(getPreferenceStore());
			return fMTLEditorEnvironment;
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

}