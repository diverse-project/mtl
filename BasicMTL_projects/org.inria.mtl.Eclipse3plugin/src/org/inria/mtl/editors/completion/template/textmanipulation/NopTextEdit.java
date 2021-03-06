/*
* $Id: NopTextEdit.java,v 1.2 2004-08-26 12:40:27 sdzale Exp $
* Authors : ${user}
*
* Created on ${date}
* Copyright 2004 - INRIA - LGPL license
*/ 
package org.inria.mtl.editors.completion.template.textmanipulation;

import org.eclipse.core.runtime.CoreException;

/**
 * A text edit that does nothing. A <code>NopTextEdit</code> can be used to track
 * positions when executing <code>TextEdits</code> associated with a <code>
 * TextBufferEditor</code>.
 */
public class NopTextEdit extends TextEdit {
	
	private TextRange fTextRange;
	
	/**
	 * Creates a new <code>NopTextEdit</code> for the given
	 * offset and length.
	 * 
	 * @param offset the starting offset this text edit is "working on"
	 * @param length the length this text edit is "working on"
	 */
	public NopTextEdit(int offset, int length) {
		this(new TextRange(offset, length));
	}
	
	/**
	 * Creates a new <code>NopTextEdit</code> for the given
	 * range.
	 * 
	 * @param range the <code>TextRange</code> this text edit is "working on"
	 */
	public NopTextEdit(TextRange range) {
		fTextRange= range;
	}

	/* non Java-doc
	 * @see TextEdit#getTextRange
	 */	
	public TextRange getTextRange() {
		return fTextRange;
	}

	/* non Java-doc
	 * @see TextEdit#perform
	 */	
	public TextEdit perform(TextBuffer buffer) throws CoreException {
		return new NopTextEdit(fTextRange);
	}
	
	/* non Java-doc
	 * @see TextEdit#perform
	 */	
	public TextEdit copy() {
		return new NopTextEdit(fTextRange.copy());
	}	
}

