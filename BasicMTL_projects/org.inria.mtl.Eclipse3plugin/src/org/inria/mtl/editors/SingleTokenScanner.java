/*
* $Id: SingleTokenScanner.java,v 1.2 2004-08-26 12:40:42 sdzale Exp $
* Authors : ${user}
*
* Created on ${date}
* Copyright 2004 - INRIA - LGPL license
*/ 
package org.inria.mtl.editors;


import java.util.List;
import org.inria.mtl.editors.utils.AbstractMTLScanner;
import org.inria.mtl.editors.utils.IColorManager; 
import org.eclipse.jface.preference.IPreferenceStore;


/**
 * Single token scanner
 */
public final class SingleTokenScanner extends AbstractMTLScanner{
	
	
	private String[] fProperty;
	
	public SingleTokenScanner(IColorManager manager, IPreferenceStore store, String property) {
		super(manager, store);
		fProperty= new String[] { property };
		initialize();
	}

	/*
	 * @see AbstractMTLScanner#getTokenProperties()
	 */
	protected String[] getTokenProperties() {
		return fProperty;
	}

	/*
	 * @see AbstractMTLScanner#createRules()
	 */
	protected List createRules() {
		setDefaultReturnToken(getToken(fProperty[0]));
		return null;
	}
}

