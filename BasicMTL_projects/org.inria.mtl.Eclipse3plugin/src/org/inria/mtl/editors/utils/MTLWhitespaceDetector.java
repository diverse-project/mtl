/*
* $Id: MTLWhitespaceDetector.java,v 1.1 2004-07-30 14:08:17 sdzale Exp $
* Authors : ${user}
*
* Created on ${date}
* Copyright 2004 - INRIA - LGPL license
*/ 
package org.inria.mtl.editors.utils;


import org.eclipse.jface.text.rules.IWhitespaceDetector;

/**
 * A MTL aware white space detector.
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
