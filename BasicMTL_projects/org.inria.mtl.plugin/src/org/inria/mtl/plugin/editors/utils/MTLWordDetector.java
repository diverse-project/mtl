package org.inria.mtl.plugin.editors.utils;

import org.eclipse.jface.text.rules.IWordDetector;
import org.inria.mtl.plugin.editors.MTLWords;

/**
 * A c sharp aware word detector.
 */
public class MTLWordDetector
	implements IWordDetector, MTLWords {


	/* (non-Javadoc)
	 * Method declared on IWordDetector.
	 */
	public boolean isWordPart(char character) {
		for (int i = 0; i < keywords.length; i++) 
			if (((String) keywords[i]).indexOf(character) != -1)
				return true;	

		for (int i = 0; i < types.length; i++) 
			if (((String) types[i]).indexOf(character) != -1) 
				return true;

		for (int i = 0; i < constants.length; i++) 
			if (((String) constants[i]).indexOf(character) != -1) 
				return true;
		
		return false;
	}

	/* (non-Javadoc)
	 * Method declared on IWordDetector.
	 */
	public boolean isWordStart(char character) {
		for (int i = 0; i < keywords.length; i++) 
			if (((String) keywords[i]).charAt(0) == character)
				return true;	

		for (int i = 0; i < types.length; i++) 
			if (((String) types[i]).charAt(0) == character) 
				return true;

		for (int i = 0; i < constants.length; i++) 
			if (((String) constants[i]).charAt(0) == character) 
				return true;	
		
		return false;

	}

}