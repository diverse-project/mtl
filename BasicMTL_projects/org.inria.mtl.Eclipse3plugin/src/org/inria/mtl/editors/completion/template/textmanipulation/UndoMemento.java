/*
* $Id: UndoMemento.java,v 1.2 2004-08-26 12:40:28 sdzale Exp $
* Authors : ${user}
*
* Created on ${date}
* Copyright 2004 - INRIA - LGPL license
*/ 
package org.inria.mtl.editors.completion.template.textmanipulation;

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

