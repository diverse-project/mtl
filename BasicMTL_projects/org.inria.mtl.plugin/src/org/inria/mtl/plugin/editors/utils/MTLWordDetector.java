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
