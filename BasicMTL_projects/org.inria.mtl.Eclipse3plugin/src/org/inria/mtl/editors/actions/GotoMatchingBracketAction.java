/*
* $Id: GotoMatchingBracketAction.java,v 1.2 2004-08-26 12:40:18 sdzale Exp $
* Authors : ${user}
*
* Created on ${date}
* Copyright 2004 - INRIA - LGPL license
*/ 
/*
 * (c) Copyright IBM Corp. 2000, 2001.
 * All Rights Reserved.
 */
/*******************************************************************************
 * Copyright (c) 2002 International Business Machines Corp. and others.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Common Public License v0.5 
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v05.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 ******************************************************************************/
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
