package org.inria.BasicMTL.ModFactDriver;

import org.eclipse.core.runtime.IPluginDescriptor;
import org.eclipse.core.runtime.Plugin;
import org.osgi.framework.BundleContext;


import java.util.*;

/**
 * The main plugin class to be used in the desktop.
 */
public class ModFactDriverPlugin extends Plugin {
	//The shared instance.
	private static ModFactDriverPlugin plugin;
	//Resource bundle.
	private ResourceBundle resourceBundle;
	
	/**
	 * The constructor.
	 */
	public ModFactDriverPlugin() {
		super();
		plugin = this;
		try {
			resourceBundle = ResourceBundle.getBundle("org.inria.BasicMTL.ModFactDriver.ModFactDriverPluginResources");
		} catch (MissingResourceException x) {
			resourceBundle = null;
			System.out.println("unable to load ModFactDriverPluginResources");
		}
		System.out.println("ModFactDriverPlugin created");
	}

	/**
	 * DVK note: I know, this method is deprecated but after several hours lost, this is the only way I 
	 * 		found to get rid of NoClassDefFound exception at runtime.
	 * 		If someone find a better way, please, let me know.
	 * @param descriptor
	 */
	public ModFactDriverPlugin(IPluginDescriptor descriptor)
	{
		super(descriptor);
		
	}
	
	/**
	 * This method is called upon plug-in activation
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
		System.out.println("ModFactDriverPlugin activated");
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
	public static ModFactDriverPlugin getDefault() {
		return plugin;
	}

	
	/**
	 * Returns the string from the plugin's resource bundle,
	 * or 'key' if not found.
	 */
	public static String getResourceString(String key) {
		ResourceBundle bundle = ModFactDriverPlugin.getDefault().getResourceBundle();
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

}
