package org.inria.mtl.plugin.wizards;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class MTLImportMessages {

	private static final String RESOURCE_BUNDLE=MTLImportMessages.class.getName();//$NON-NLS-1$

	private static ResourceBundle fgResourceBundle= ResourceBundle.getBundle(RESOURCE_BUNDLE);

	private MTLImportMessages() {
	}

	public static String getString(String key) {
		try {
			return fgResourceBundle.getString(key);
		} catch (MissingResourceException e) {
			return "!" + key + "!";//$NON-NLS-2$ //$NON-NLS-1$
		}
	}
}