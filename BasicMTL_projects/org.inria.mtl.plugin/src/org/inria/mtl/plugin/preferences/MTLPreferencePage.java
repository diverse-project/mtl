/*
* $Id: MTLPreferencePage.java,v 1.2 2004-05-19 09:21:33 sdzale Exp $
* Authors : ${user}
*
* Created on ${date}
* Copyright 2004 - INRIA - LGPL license
*/ 
package org.inria.mtl.plugin.preferences;

import org.eclipse.jface.preference.BooleanFieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.swt.SWT;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;
import org.inria.mtl.plugin.MTLPlugin;

/**
 * This class represents a preference page that
 * is contributed to the Preferences dialog. By 
 * subclassing <samp>FieldEditorPreferencePage</samp>, we
 * can use the field support built into JFace that allows
 * us to create a page that is small and knows how to 
 * save, restore and apply itself.
 * <p>
 * This page is used to modify preferences only. They
 * are stored in the preference store that belongs to
 * the main plug-in class. That way, preferences can
 * be accessed directly via the preference store.
 */

public class MTLPreferencePage
	extends FieldEditorPreferencePage
	implements IWorkbenchPreferencePage {
		
		BooleanFieldEditor autoCompile;
		BooleanFieldEditor showOutputInConsole;




	public MTLPreferencePage() {
		super(GRID);
		setPreferenceStore(MTLPlugin.getDefault().getPreferenceStore());
		setDescription("MTL Preference Page");
		
	}
	
	
	
/**
 * Creates the field editors. Field editors are abstractions of
 * the common GUI blocks needed to manipulate various types
 * of preferences. Each field editor knows how to save and
 * restore itself.
 */

	public void createFieldEditors() {
		autoCompile =
				new BooleanFieldEditor(
					PreferenceConstants.AUTO_COMPILE,
					MTLMessages.getString("preferences.autoCompile"),
					SWT.NONE,
					this.getFieldEditorParent()); 
				
			showOutputInConsole =
				new BooleanFieldEditor(
					PreferenceConstants.SHOW_OUTPUT_IN_CONSOLE,
					MTLMessages.getString("preferences.showOutputInConsole"),
					SWT.NONE,
					this.getFieldEditorParent());

			addField(autoCompile);
			addField(showOutputInConsole);		
	}
	
	public void init(IWorkbench workbench) {
	}
	
	public boolean performOk() {
			return super.performOk();
		}
}