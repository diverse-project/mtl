/**
 * Created on 12 mai 2004
 * @author sdzale
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package org.inria.mtl.plugin.editors.actions;

import org.inria.mtl.plugin.editors.utils.Assert;
import org.inria.mtl.plugin.editors.MTLEditor;
import org.inria.mtl.plugin.editors.MTLEditorMessages;

import org.eclipse.jface.action.Action;


public class GotoMatchingBracketAction extends Action {

	public final static String GOTO_MATCHING_BRACKET= "GotoMatchingBracket"; //$NON-NLS-1$
	
	private final MTLEditor fEditor;
	
	public GotoMatchingBracketAction(MTLEditor editor) {
		super(MTLEditorMessages.getString("GotoMatchingBracket.label"));
		Assert.isNotNull(editor);
		fEditor= editor;
		setEnabled(null != fEditor);
	}
	
	public void run() {
		fEditor.gotoMatchingBracket();
	}
}
