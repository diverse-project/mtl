/*
* $Id: MTLWhitespaceDetector.java,v 1.2 2004-08-26 12:40:09 sdzale Exp $
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
	
}
