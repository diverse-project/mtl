/*
* $Id: MTLWordDetector.java,v 1.1 2004-07-30 14:08:17 sdzale Exp $
* Authors : ${user}
*
* Created on ${date}
* Copyright 2004 - INRIA - LGPL license
*/ 
package org.inria.mtl.editors.utils;

import org.eclipse.jface.text.rules.IWordDetector;

/**
 * A MTL word detector.
 */
public class MTLWordDetector implements IWordDetector {

	/**
	 * @see IWordDetector#isWordStart
	 */
	public boolean isWordStart(char c) {
		return Character.isJavaIdentifierStart(c);
	}
	
	/**
	 * @see IWordDetector#isWordPart
	 */
	public boolean isWordPart(char c) {
		return Character.isJavaIdentifierPart(c);
	}
}
