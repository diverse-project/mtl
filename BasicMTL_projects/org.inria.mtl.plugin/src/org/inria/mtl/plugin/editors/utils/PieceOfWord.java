package org.inria.mtl.plugin.editors.utils;

import org.eclipse.jface.text.*;

/**
 * Class used by the completion processors.
 */

public class PieceOfWord {
	String string = ""; //$NON-NLS-1$
	int offset;

	public PieceOfWord(ITextViewer viewer, int documentOffset) {
		offset = documentOffset - 1;
		try {
			while (Character.isLetterOrDigit(viewer.getDocument().getChar(offset))) {
				offset--;
			}
			//we've been one step too far : increase the offset
			offset++;
			string = viewer.getDocument().get(offset, documentOffset - offset);
		} catch (BadLocationException e) {
			// do nothing
		}
	}
	
	public String getString() {
		return string;
	}
	
	public int getOffset() {
		return offset;
	}
}