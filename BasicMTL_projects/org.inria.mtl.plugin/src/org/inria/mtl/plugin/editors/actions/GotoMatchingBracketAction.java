/*
* $Id: GotoMatchingBracketAction.java,v 1.3 2004-05-19 09:21:06 sdzale Exp $
* Authors : ${user}
*
* Created on ${date}
* Copyright 2004 - INRIA - LGPL license
*/ 
package org.inria.mtl.plugin.editors.actions;

import org.eclipse.jface.action.Action;
import org.inria.mtl.plugin.editors.MTLEditor;
import org.inria.mtl.plugin.editors.utils.Assert;


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
