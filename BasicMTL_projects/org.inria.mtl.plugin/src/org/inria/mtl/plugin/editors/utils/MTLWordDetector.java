/*
* $Id: MTLWordDetector.java,v 1.3 2004-05-19 09:22:15 sdzale Exp $
* Authors : ${user}
*
* Created on ${date}
* Copyright 2004 - INRIA - LGPL license
*/ 
package org.inria.mtl.plugin.editors.utils;

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
