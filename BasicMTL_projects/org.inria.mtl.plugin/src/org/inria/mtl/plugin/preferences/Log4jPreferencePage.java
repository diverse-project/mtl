/*
* $Id: Log4jPreferencePage.java,v 1.1 2004-05-25 09:07:42 sdzale Exp $
* Authors : ${user}
*
* Created on ${date}
* Copyright 2004 - INRIA - LGPL license
*/ 
package org.inria.mtl.plugin.preferences;

import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.IntegerFieldEditor;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;
import org.inria.mtl.plugin.MTLPlugin;

/**
 * This class represents a preference page that is contributed to the
 * Preferences dialog. By subclassing <samp>FieldEditorPreferencePage</samp>,
 * we can use the field support built into JFace that allows us to create a
 * page that is small and knows how to save, restore and apply itself.
 * <p>
 * This page is used to modify preferences only. They are stored in the
 * preference store that belongs to the main plug-in class. That way,
 * preferences can be accessed directly via the preference store.
 */
public class Log4jPreferencePage extends FieldEditorPreferencePage implements IWorkbenchPreferencePage
{
	public static final String P_PORT = "port";
	public Log4jPreferencePage()
	{
		super(GRID);
		setPreferenceStore(MTLPlugin.getDefault().getPreferenceStore());
		setDescription("MTL log4J preference page");
	}

	public void init(IWorkbench workbench)
	{
	}

	/**
	 * Creates the field editors. Field editors are abstractions of the common
	 * GUI blocks needed to manipulate various types of preferences. Each field
	 * editor knows how to save and restore itself.
	 */
	public void createFieldEditors()
	{
		addField(new IntegerFieldEditor(PreferenceConstants.LOG4_PORT, "Server &Port", getFieldEditorParent()));
	}
}