package org.inria.mtl.editors.completion.template;

/*
 * (c) Copyright IBM Corp. 2000, 2001.
 * All Rights Reserved.
 */

//import net.sourceforge.phpdt.internal.corext.template.TemplateContext;

/**
 * A context type for java code.
 */
public class MTLContextType extends CompilationUnitContextType {

	/**
	 * Creates a mtl context type.
	 */
	public MTLContextType() {
		super("mtl"); //$NON-NLS-1$
		
		// global
		addVariable(new GlobalVariables.Cursor());
		addVariable(new GlobalVariables.Dollar());
		addVariable(new GlobalVariables.Date());
		addVariable(new GlobalVariables.Year());
		addVariable(new GlobalVariables.Time());
		addVariable(new GlobalVariables.User());
		
		}
	
	/*
	 * @see ContextType#createContext()
	 */	
	public TemplateContext createContext() {
		return new MTLUnitContext(this, fDocument, fOffset); //, fCompilationUnit);
	}

}

