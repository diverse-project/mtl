package org.inria.mtl.plugin.preferences;

import org.eclipse.jface.preference.FileFieldEditor;
import org.eclipse.jface.preference.DirectoryFieldEditor;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import org.inria.mtl.plugin.MTLPlugin;

/**
 * Implements the plugin preference page.
 * 
 * @author gbrocker
 */
public class MTLCompilerPreferencePage extends org.eclipse.jface.preference.FieldEditorPreferencePage implements IWorkbenchPreferencePage
{
	/**
	 * Constructor.
	 */
	public MTLCompilerPreferencePage() {
		super(GRID);
		setPreferenceStore(MTLPlugin.getDefault().getPreferenceStore());
		setDescription(MTLMessages.getString("CompilerPreferencePage.description")); //$NON-NLS-1$
	}
	
	public void init( IWorkbench workbench ) {
	}
	
	/**
	 * Creates the preference page fields.
	 */
	protected void createFieldEditors() {
		Composite			rootControl = getFieldEditorParent();
		IPreferenceStore	preferenceStore = MTLPlugin.getDefault().getPreferenceStore();
		
		// Create the controls.
		DirectoryFieldEditor	mtlPath = new DirectoryFieldEditor( PreferenceConstants.MTL_COMPILER_PATH, "",  rootControl );
		mtlPath.setPreferenceName( PreferenceConstants.MTL_COMPILER_PATH );
		mtlPath.setPreferenceStore( preferenceStore );
		addField( mtlPath );
	}
}
