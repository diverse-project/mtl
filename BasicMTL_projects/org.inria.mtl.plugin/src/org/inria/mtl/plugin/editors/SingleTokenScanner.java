package org.inria.mtl.plugin.editors;


import java.util.List;
import org.inria.mtl.plugin.editors.utils.AbstractMTLScanner;
import org.inria.mtl.plugin.editors.utils.IColorManager; 

//import net.sourceforge.phpdt.internal.ui.text.AbstractJavaScanner;

import org.eclipse.jface.preference.IPreferenceStore;


/**
 * 
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

