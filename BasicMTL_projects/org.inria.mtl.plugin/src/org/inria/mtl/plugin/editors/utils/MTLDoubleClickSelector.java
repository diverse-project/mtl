/*
* $Id: MTLDoubleClickSelector.java,v 1.2 2004-05-19 09:22:12 sdzale Exp $
* Authors : ${user}
*
* Created on ${date}
* Copyright 2004 - INRIA - LGPL license
*/ 
package org.inria.mtl.plugin.editors.utils;

import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.ITextDoubleClickStrategy;
import org.eclipse.jface.text.ITextViewer;

/**
 * Double click strategy aware of MTL identifier syntax rules.
 */
public class MTLDoubleClickSelector implements ITextDoubleClickStrategy {
	
	
	protected static final char[] BRACKETS= {'{', '}', '(', ')', '[', ']' };
	
	protected MTLPairMatcher fPairMatcher= new MTLPairMatcher(BRACKETS);
		
	
	public MTLDoubleClickSelector() {
		super();
	}
	
	/**
	 * @see ITextDoubleClickStrategy#doubleClicked
	 */
	public void doubleClicked(ITextViewer textViewer) {
		
		int offset= textViewer.getSelectedRange().x;
		
		if (offset < 0)
			return;
			
		IDocument document= textViewer.getDocument();
		
		IRegion region= fPairMatcher.match(document, offset);
		if (region != null && region.getLength() >= 2)
			textViewer.setSelectedRange(region.getOffset() + 1, region.getLength() - 2);
		else
			selectWord(textViewer, document, offset);
	}

	protected void selectWord(ITextViewer textViewer, IDocument document, int anchor) {

		try {

			int offset= anchor;
			char c;

			while (offset >= 0) {
				c= document.getChar(offset);
				if (!Character.isJavaIdentifierPart(c))
					break;
				--offset;
			}

			int start= offset;

			offset= anchor;
			int length= document.getLength();

			while (offset < length) {
				c= document.getChar(offset);
				if (!Character.isJavaIdentifierPart(c))
					break;
				++offset;
			}
			
			int end= offset;
			
			if (start == end)
				textViewer.setSelectedRange(start, 0);
			else
				textViewer.setSelectedRange(start + 1, end - start - 1);
			
		} catch (BadLocationException x) {
		}
	}
}
