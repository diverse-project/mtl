package org.inria.EMFDriver;

import org.eclipse.ui.plugin.*;
import org.eclipse.core.runtime.*;
import java.util.*;

/**
 * The main plugin class to be used in the desktop.
 */
public class EMFDriverPlugin extends AbstractUIPlugin {
	//The shared instance.
	private static EMFDriverPlugin plugin;
	//Resource bundle.
	private ResourceBundle resourceBundle;
	
	/**
	 * The constructor.
	 */
	public EMFDriverPlugin(IPluginDescriptor descriptor) {
		super(descriptor);
		plugin = this;
		try {
			resourceBundle   = ResourceBundle.getBundle("org.inria.EMFDriver.EMFDriverPluginResources");
		} catch (MissingResourceException x) {
			resourceBundle = null;
		}
	}

	/**
	 * Returns the shared instance.
	 */
	public static EMFDriverPlugin getDefault() {
		return plugin;
	}

	/**
	 * Returns the string from the plugin's resource bundle,
	 * or 'key' if not found.
	 */
	public static String getResourceString(String key) {
		ResourceBundle bundle = EMFDriverPlugin.getDefault().getResourceBundle();
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
