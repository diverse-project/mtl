package org.inria.mtl.plugin.preferences;

import org.eclipse.jface.preference.BooleanFieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;
import org.inria.mtl.plugin.MTLPlugin;

/**
 * 
 * @author khartlage
 */
public class MTLOutlinePage extends FieldEditorPreferencePage implements IWorkbenchPreferencePage {

  public MTLOutlinePage() {
	super(FieldEditorPreferencePage.GRID);
	//Initialize the preference store we wish to use
	setPreferenceStore(MTLPlugin.getDefault().getPreferenceStore());
  }

  protected void createFieldEditors() {
	final IPreferenceStore store = MTLPlugin.getDefault().getPreferenceStore();

    
	BooleanFieldEditor outlineShowLibrary =
	  new BooleanFieldEditor(PreferenceConstants.MTL_OUTLINE_CLASS, "Show library in outline", getFieldEditorParent());
	BooleanFieldEditor outlineShowClass =
		  new BooleanFieldEditor(PreferenceConstants.MTL_OUTLINE_CLASS, "Show classes in outline", getFieldEditorParent());
	BooleanFieldEditor outlineShowFunc =
	  new BooleanFieldEditor(PreferenceConstants.MTL_OUTLINE_FUNC, "Show functions in outline", getFieldEditorParent());
	BooleanFieldEditor outlineShowVar =
	  new BooleanFieldEditor(PreferenceConstants.MTL_OUTLINE_VAR, "Show variables in outline", getFieldEditorParent());

	addField(outlineShowLibrary);
	addField(outlineShowClass);
	addField(outlineShowFunc);
	addField(outlineShowVar);
  }

  /**
   * @see IWorkbenchPreferencePage#init
   */
  public void init(IWorkbench workbench) {
  }

}
