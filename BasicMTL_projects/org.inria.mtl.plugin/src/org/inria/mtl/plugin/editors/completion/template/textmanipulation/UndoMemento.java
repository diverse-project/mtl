/*
 * (c) Copyright IBM Corp. 2000, 2001.
 * All Rights Reserved.
 */
package org.inria.mtl.plugin.editors.completion.template.textmanipulation;

import java.util.ArrayList;
import java.util.List;


/**
 * This class encapsulates the reverse change of a number of <code>TextEdit</code>s
 * executed on a <code>TextBufferEditor</code>
 */
public final class UndoMemento {

	/* package */ int fMode;
	/* package */ List fEdits; 

	/* package */ UndoMemento(int mode) {
		fMode= mode;
		fEdits= new ArrayList(10);
	}
	
	/* package */ void add(TextEdit edit) {
		fEdits.add(edit);
	}	
}

