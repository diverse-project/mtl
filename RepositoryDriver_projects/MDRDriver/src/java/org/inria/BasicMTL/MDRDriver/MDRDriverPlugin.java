package org.inria.BasicMTL.MDRDriver;

import org.eclipse.core.runtime.Plugin;
import org.osgi.framework.BundleContext;
import java.util.*;

/**
 * The main plugin class to be used in the desktop.
 */
public class MDRDriverPlugin extends Plugin {
	//The shared instance.
	private static MDRDriverPlugin plugin;
	//Resource bundle.
	private ResourceBundle resourceBundle;
	
	/**
	 * The constructor.
	 */
	public MDRDriverPlugin() {
		super();
		plugin = this;
		try {
			resourceBundle = ResourceBundle.getBundle("org.inria.BasicMTL.MDRDriver.MDRDriverPluginResources");
		} catch (MissingResourceException x) {
			resourceBundle = null;
		}
		System.out.println("MDRDriverPlugin created");
	}

	/**
	 * This method is called upon plug-in activation
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
		System.out.println("MDRDriverPlugin activated");
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
	public static MDRDriverPlugin getDefault() {
		return plugin;
	}




}
