package org.inria.mtl.plugin.editors.utils;


import org.eclipse.jface.text.rules.IWhitespaceDetector;

/**
 * A c sharp aware white space detector.
 */
public class MTLWhitespaceDetector implements IWhitespaceDetector {

	/* (non-Javadoc)
	 * Method declared on IWhitespaceDetector
	 */
	public boolean isWhitespace(char character) {
		return Character.isWhitespace(character);
	}
	
//	public boolean isWhitespace(char c) {
//			return (c == ' ' || c == '\t' || c == '\n' || c == '\r');
//		}
}
