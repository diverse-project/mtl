package org.inria.BasicMTL.runtime;

import org.eclipse.core.runtime.Plugin;
import org.osgi.framework.BundleContext;
import java.util.*;

/**
 * The main plugin class to be used in the desktop.
 */
public class RuntimePlugin extends Plugin {
	//The shared instance.
	private static RuntimePlugin plugin;
	//Resource bundle.
	private ResourceBundle resourceBundle;
	
	/**
	 * The constructor.
	 */
	public RuntimePlugin() {
		super();
		plugin = this;
		try {
			resourceBundle = ResourceBundle.getBundle("org.inria.BasicMTL.runtime.RuntimePluginResources");
		} catch (MissingResourceException x) {
			resourceBundle = null;
		}
		System.out.println("RuntimePlugin created");
	}

	/**
	 * This method is called upon plug-in activation
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
		System.out.println("RuntimePlugin activated");
		showRuntimeLibraries();
		context.toString();
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
	public static RuntimePlugin getDefault() {
		return plugin;
	}

	/**
	 * Returns the string from the plugin's resource bundle,
	 * or 'key' if not found.
	 */
	public static String getResourceString(String key) {
		ResourceBundle bundle = RuntimePlugin.getDefault().getResourceBundle();
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
	private void  showRuntimeLibraries()
	{
		String ret;
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
		
	}
}
