package org.inria.mtl.plugin.editors.doc;


import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.ITextDoubleClickStrategy;
import org.eclipse.jface.text.ITextViewer;

/**
 * Double click strategy aware of C Sharp identifier syntax rules.
 */
public class MTLDoubleClickSelector implements ITextDoubleClickStrategy {

	protected ITextViewer textViewer;
	protected int position;
	protected int startPosition;
	protected int endPosition;

	protected static char[] brackets= { '{', '}', '(', ')', '[', ']', '"', '"' };

	/* 
	 * Create a MTLDoubleClickSelector.
	 */
	 public MTLDoubleClickSelector() {
		super();
	}
	
	/* (non-Javadoc)
	 * Method declared on ITextDoubleClickStrategy
	 */
	public void doubleClicked(ITextViewer text) {

		position= text.getSelectedRange().x;

		if (position < 0)
			return;

		textViewer= text;

		if (!selectBracketBlock())
			selectWord();
	}
	
	/**
	 * Match the brackets at the current selection. Return true if successful,
	 * false otherwise.
	 */
	 protected boolean matchBracketsAt() {

		char prevChar, nextChar;

		int i;
		int bracketIndex1= brackets.length;
		int bracketIndex2= brackets.length;

		startPosition= -1;
		endPosition= -1;

		// get the chars preceding and following the start position
		try {

			IDocument doc= textViewer.getDocument();

			prevChar= doc.getChar(position - 1);
			nextChar= doc.getChar(position);

			// is the char either an open or close bracket?
			for (i= 0; i < brackets.length; i= i + 2) {
				if (prevChar == brackets[i]) {
					startPosition= position - 1;
					bracketIndex1= i;
				}
			}
			for (i= 1; i < brackets.length; i= i + 2) {
				if (nextChar == brackets[i]) {
					endPosition= position;
					bracketIndex2= i;
				}
			}

			if (startPosition > -1 && bracketIndex1 < bracketIndex2) {
				endPosition= searchForClosingBracket(startPosition, prevChar, brackets[bracketIndex1 + 1], doc);
				if (endPosition > -1)
					return true;
				else
					startPosition= -1;
			} else if (endPosition > -1) {
				startPosition= searchForOpenBracket(endPosition, brackets[bracketIndex2 - 1], nextChar, doc);
				if (startPosition > -1)
					return true;
				else
					endPosition= -1;
			}

		} catch (BadLocationException x) {
		}

		return false;
	}
	
	/**
	 * Select the word at the current selection. Return true if successful,
	 * false otherwise.
	 */
	 protected boolean matchWord() {

		IDocument doc= textViewer.getDocument();

		try {

			int pos= position;
			char c;

			while (pos >= 0) {
				c= doc.getChar(pos);
				// [PENDING] Gotta change this
				if (!Character.isJavaIdentifierPart(c))
					break;
				--pos;
			}

			startPosition= pos;

			pos= position;
			int length= doc.getLength();

			while (pos < length) {
				c= doc.getChar(pos);
				// [PENDING] Gotta change this
				if (!Character.isJavaIdentifierPart(c))
					break;
				++pos;
			}

			endPosition= pos;

			return true;

		} catch (BadLocationException x) {
		}

		return false;
	}
	
	/**
	 * Returns the position of the closing bracket after startPosition.
	 * @returns the location of the closing bracket.
	 * @param startPosition - the beginning position
	 * @param openBracket - the character that represents the open bracket
	 * @param closeBracket - the character that represents the close bracket
	 * @param document - the document being searched
	 */
	 protected int searchForClosingBracket(int startPosition, char openBracket, char closeBracket, IDocument document) throws BadLocationException {
		int stack= 1;
		int closePosition= startPosition + 1;
		int length= document.getLength();
		char nextChar;

		while (closePosition < length && stack > 0) {
			nextChar= document.getChar(closePosition);
			if (nextChar == openBracket && nextChar != closeBracket)
				stack++;
			else if (nextChar == closeBracket)
				stack--;
			closePosition++;
		}

		if (stack == 0)
			return closePosition - 1;
		else
			return -1;

	}
	
	/**
	 * Returns the position of the open bracket before startPosition.
	 * @returns the location of the starting bracket.
	 * @param startPosition - the beginning position
	 * @param openBracket - the character that represents the open bracket
	 * @param closeBracket - the character that represents the close bracket
	 * @param document - the document being searched
	 */
	 protected int searchForOpenBracket(int startPosition, char openBracket, char closeBracket, IDocument document) throws BadLocationException {
		int stack= 1;
		int openPos= startPosition - 1;
		char nextChar;

		while (openPos >= 0 && stack > 0) {
			nextChar= document.getChar(openPos);
			if (nextChar == closeBracket && nextChar != openBracket)
				stack++;
			else if (nextChar == openBracket)
				stack--;
			openPos--;
		}

		if (stack == 0)
			return openPos + 1;
		else
			return -1;
	}
	
	/**
	 * Select the area between the selected bracket and the closing bracket. Return
	 * true if successful.
	 */
	 protected boolean selectBracketBlock() {
		if (matchBracketsAt()) {

			if (startPosition == endPosition)
				textViewer.setSelectedRange(startPosition, 0);
			else
				textViewer.setSelectedRange(startPosition + 1, endPosition - startPosition - 1);

			return true;
		}
		return false;
	}
	
	/**
	 * Select the word at the current selection. 
	 */
	 protected void selectWord() {
		if (matchWord()) {

			if (startPosition == endPosition)
				textViewer.setSelectedRange(startPosition, 0);
			else
				textViewer.setSelectedRange(startPosition + 1, endPosition - startPosition - 1);
		}
	}
}
