package org.inria.simpleUML.transformations.UI;

import org.eclipse.core.runtime.ILibrary;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IPluginDescriptor;
import org.eclipse.ui.plugin.*;
import org.inria.BasicMTL.runtime.JarClassLoader;
import org.osgi.framework.BundleContext;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

/**
 * The main plugin class to be used in the desktop.
 */
public class UIPlugin extends AbstractUIPlugin {
	//The shared instance.
	private static UIPlugin plugin;
	//Resource bundle.
	private ResourceBundle resourceBundle;
	
	/**
	 * The constructor.
	 */
	public UIPlugin() {
		super();
		plugin = this;
		try {
			resourceBundle = ResourceBundle.getBundle("simpleUML.transformations.UI.UIPluginResources");
			System.out.println("UIPlugin got bundle");
		} catch (MissingResourceException x) {
			resourceBundle = null;
		}
	}

	/**
	 * This method is called upon plug-in activation
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
		System.out.println("SimpleUML.transformations.UIPlugin activated");
		showRuntimeLibraries();
		System.out.println(context.toString());
		//context.installBundle();
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
	public static UIPlugin getDefault() {
		return plugin;
	}

	/**
	 * Returns the string from the plugin's resource bundle,
	 * or 'key' if not found.
	 */
	public static String getResourceString(String key) {
		ResourceBundle bundle = UIPlugin.getDefault().getResourceBundle();
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
	
	public void  showBundleContent() throws Exception
	{
		//getBundle().loadClass("org.openide.util.Lookup");
		 getBundle().loadClass("org.inria.BasicMTL.runtime.JarClassLoader");
		org.osgi.framework.ServiceReference[] registeredServices = getBundle().getRegisteredServices();
		org.osgi.framework.ServiceReference[] usedServices = getBundle().getServicesInUse();
	}
	private void  showRuntimeLibraries()
	{
		String requires = (String)getBundle().getHeaders().get(org.osgi.framework.Constants.BUNDLE_CLASSPATH);
		System.out.println(" " + requires);
		//getBundle().
		/* deprecated code
		  org.eclipse.core.runtime.IPluginDescriptor pd = getDefault().getDescriptor();
		java.net.URL url = pd.getInstallURL();
		String urlString = url.toString();
		org.eclipse.core.runtime.ILibrary[] libraries = pd.getRuntimeLibraries();
		for (int i = 0; i < libraries.length; i++) {
			org.eclipse.core.runtime.ILibrary iLibrary = libraries[i];
			org.eclipse.core.runtime.IPath libPath = iLibrary.getPath();
			String libPathStr = libPath.toString();
			String libUrlStr = urlString + libPathStr;
			System.out.println(" " + libUrlStr );
		}
		*/
		
	}
}
