/*
* $Id: MTLPairMatcher.java,v 1.2 2004-05-19 09:21:55 sdzale Exp $
* Authors : ${user}
*
* Created on ${date}
* Copyright 2004 - INRIA - LGPL license
*/ 
package org.inria.mtl.plugin.editors.utils;

import java.io.IOException;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.Region;
import org.eclipse.jface.text.source.ICharacterPairMatcher;

/**
 * Helper class for match pairs of characters.
 */
public class MTLPairMatcher implements ICharacterPairMatcher {
	
	
	public static final int LEFT=	   1;
	public static final int RIGHT= 2;

	
	protected char[] fPairs;
	protected IDocument fDocument;
	protected int fOffset;
	
	protected int fStartPos;
	protected int fEndPos;
	protected int fAnchor;
	
	protected MTLCodeReader fReader= new MTLCodeReader();
	
	
	public MTLPairMatcher(char[] pairs) {
		fPairs= pairs;
	}
	
	/*
	 * @see org.eclipse.jface.text.source.ICharacterPairMatcher#clear()
	 */
	public void clear() {
		if (fReader != null) {
			try {
				fReader.close();
			} catch (IOException x) {
				// ignore
			}
		}
	}
	
	public IRegion match(IDocument document, int offset) {

		fOffset= offset;

		if (fOffset < 0)
			return null;

		fDocument= document;

		if (matchPairsAt() && fStartPos != fEndPos)
			return new Region(fStartPos, fEndPos - fStartPos + 1);
			
		return null;
	}
	
	public int getAnchor() {
		return fAnchor;
	}
	
	public void dispose() {
		fDocument= null;
		if (fReader != null) {
			try {
				fReader.close();
			} catch (IOException x) {
				// ignore
			}
			fReader= null;
		}
	}
	
	protected boolean matchPairsAt() {

		int i;
		int pairIndex1= fPairs.length;
		int pairIndex2= fPairs.length;

		fStartPos= -1;
		fEndPos= -1;

		// get the chars preceding and following the start position
		try {

			char prevChar= fDocument.getChar(Math.max(fOffset - 1, 0));
			char nextChar= fDocument.getChar(fOffset);

			// search for opening peer character next to the activation point
			for (i= 0; i < fPairs.length; i= i + 2) {
				if (nextChar == fPairs[i]) {
					fStartPos= fOffset;
					pairIndex1= i;
				} else if (prevChar == fPairs[i]) {
					fStartPos= fOffset - 1;
					pairIndex1= i;
				}
			}
			
			// search for closing peer character next to the activation point
			for (i= 1; i < fPairs.length; i= i + 2) {
				if (prevChar == fPairs[i]) {
					fEndPos= fOffset - 1;
					pairIndex2= i;
				} else if (nextChar == fPairs[i]) {
					fEndPos= fOffset;
					pairIndex2= i;
				}
			}

			if (fEndPos > -1) {
				fAnchor= RIGHT;
				fStartPos= searchForOpeningPeer(fEndPos, fPairs[pairIndex2 - 1], fPairs[pairIndex2], fDocument);
				if (fStartPos > -1)
					return true;
				else
					fEndPos= -1;
			}	else if (fStartPos > -1) {
				fAnchor= LEFT;
				fEndPos= searchForClosingPeer(fStartPos, fPairs[pairIndex1], fPairs[pairIndex1 + 1], fDocument);
				if (fEndPos > -1)
					return true;
				else
					fStartPos= -1;
			}

		} catch (BadLocationException x) {
		} catch (IOException x) {
		}

		return false;
	}
	
	protected int searchForClosingPeer(int offset, int openingPeer, int closingPeer, IDocument document) throws IOException {
		
		fReader.configureForwardReader(document, offset + 1, document.getLength(), true, true);
		
		int stack= 1;
		int c= fReader.read();
		while (c != MTLCodeReader.EOF) {
			if (c == openingPeer && c != closingPeer)
				stack++;
			else if (c == closingPeer)
				stack--;
				
			if (stack == 0)
				return fReader.getOffset();
				
			c= fReader.read();
		}
		
		return  -1;
	}
	
	protected int searchForOpeningPeer(int offset, int openingPeer, int closingPeer, IDocument document) throws IOException {
		
		fReader.configureBackwardReader(document, offset, true, true);
		
		int stack= 1;
		int c= fReader.read();
		while (c != MTLCodeReader.EOF) {
			if (c == closingPeer && c != openingPeer)
				stack++;
			else if (c == openingPeer)
				stack--;
				
			if (stack == 0)
				return fReader.getOffset();
				
			c= fReader.read();
		}
		
		return -1;
	}
}

