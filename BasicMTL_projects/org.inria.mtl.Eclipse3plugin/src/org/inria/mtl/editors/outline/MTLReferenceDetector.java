package org.inria.mtl.editors.outline;

import org.inria.mtl.editors.utils.MTLWordDetector;

public class MTLReferenceDetector extends MTLWordDetector {
	/**
	 * @see IWordDetector#isWordStart
	 * Try to detect tokens starting with a reference operator.
	 */
	public boolean isWordStart(char c) {
		return (c == '.');
	}
}
