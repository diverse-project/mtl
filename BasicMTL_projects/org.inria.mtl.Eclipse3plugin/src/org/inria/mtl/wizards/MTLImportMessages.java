/*
* $Id: MTLImportMessages.java,v 1.1 2004-07-30 14:08:36 sdzale Exp $
* Authors : ${user}
*
* Created on ${date}
* Copyright 2004 - INRIA - LGPL license
*/ 
package org.inria.mtl.wizards;

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