/*
* $Id: MTLEditorMessages.java,v 1.2 2004-05-19 09:22:35 sdzale Exp $
* Authors : ${user}
*
* Created on ${date}
* Copyright 2004 - INRIA - LGPL license
*/ 
package org.inria.mtl.plugin.editors;


import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class MTLEditorMessages {

	private static final String RESOURCE_BUNDLE= MTLEditorMessages.class.getName();//$NON-NLS-1$

	private static ResourceBundle fgResourceBundle= ResourceBundle.getBundle(RESOURCE_BUNDLE);

	private MTLEditorMessages() {
	}

	public static String getString(String key) {
		try {
			return fgResourceBundle.getString(key);
		} catch (MissingResourceException e) {
			return "!" + key + "!";//$NON-NLS-2$ //$NON-NLS-1$
		}
	}
	
	public static ResourceBundle getResourceBundle() {
		return fgResourceBundle;
	}
}
