/*
* $Id: TextWords.java,v 1.2 2004-05-19 09:21:10 sdzale Exp $
* Authors : ${user}
*
* Created on ${date}
* Copyright 2004 - INRIA - LGPL license
*/ 
package org.inria.mtl.plugin.editors.outline;

import java.util.HashMap;

import org.eclipse.jface.text.rules.IWordDetector;


/**
 * @author sdzale
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class TextWords implements IWordDetector{

	HashMap ignoreChars;

	public TextWords() {
		super();
		ignoreChars = new HashMap();
		makeIgnoreChars();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.text.rules.IWordDetector#isWordStart(char)
	 */
	public boolean isWordStart(char c) {
		if (ignoreChars.containsKey(new Character(c))) {
			return false;
		}
		return true;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.text.rules.IWordDetector#isWordPart(char)
	 */
	public boolean isWordPart(char c) {
		if (ignoreChars.containsKey(new Character(c))) {
			return false;
		}
		return true;
	}

	private void makeIgnoreChars() {
		ignoreChars = new HashMap();
		ignoreChars.put(new Character(' '), new Object());
		ignoreChars.put(new Character('\n'), new Object());
		ignoreChars.put(new Character('\t'), new Object());
		ignoreChars.put(new Character('\r'), new Object());
		ignoreChars.put(new Character('{'), new Object());
		ignoreChars.put(new Character('}'), new Object());
		ignoreChars.put(new Character('['), new Object());
		ignoreChars.put(new Character(']'), new Object());
		ignoreChars.put(new Character('!'), new Object());
		ignoreChars.put(new Character('.'), new Object());
		ignoreChars.put(new Character(','), new Object());
		ignoreChars.put(new Character('?'), new Object());
		ignoreChars.put(new Character('"'), new Object());
		ignoreChars.put(new Character('£'), new Object());
		ignoreChars.put(new Character('$'), new Object());
		ignoreChars.put(new Character('%'), new Object());
		ignoreChars.put(new Character('^'), new Object());
		ignoreChars.put(new Character('&'), new Object());
		ignoreChars.put(new Character('*'), new Object());
		ignoreChars.put(new Character(':'), new Object());
		ignoreChars.put(new Character(';'), new Object());
		ignoreChars.put(new Character('@'), new Object());
		ignoreChars.put(new Character('\''), new Object());
		ignoreChars.put(new Character('#'), new Object());
		ignoreChars.put(new Character('~'), new Object());
		ignoreChars.put(new Character('/'), new Object());
		//ignoreChars.put(new Character('-'), new Object());
		ignoreChars.put(new Character('+'), new Object());
		ignoreChars.put(new Character('|'), new Object());
		ignoreChars.put(new Character('\\'), new Object());
		ignoreChars.put(new Character('<'), new Object());
		ignoreChars.put(new Character('>'), new Object());
		ignoreChars.put(new Character('('), new Object());
		ignoreChars.put(new Character(')'), new Object());
		ignoreChars.put(new Character(((char)65535)), new Object());
		
	}
}
