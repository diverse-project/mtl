/*
* $Id: GotoMatchingBracketAction.java,v 1.1 2004-07-30 14:08:46 sdzale Exp $
* Authors : ${user}
*
* Created on ${date}
* Copyright 2004 - INRIA - LGPL license
*/ 
package org.inria.mtl.editors.actions;

import org.eclipse.jface.action.Action;
import org.inria.mtl.editors.MTLEditor;
import org.inria.mtl.editors.utils.Assert;


public class GotoMatchingBracketAction extends Action {

	public final static String GOTO_MATCHING_BRACKET= "GotoMatchingBracket"; //$NON-NLS-1$
	
	private final MTLEditor fEditor;
	
	public GotoMatchingBracketAction(MTLEditor editor) {
		super(ActionMessages.getString("GotoMatchingBracket.label"));
		Assert.isNotNull(editor);
		fEditor= editor;
		setEnabled(null != fEditor);
	}
	
	public void run() {
		fEditor.gotoMatchingBracket();
	}
}
