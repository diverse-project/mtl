/*
* $Id: MTLCodeScanner.java,v 1.2 2004-08-26 12:40:40 sdzale Exp $
* Authors : ${user}
*
* Created on ${date}
* Copyright 2004 - INRIA - LGPL license
*/ 
package org.inria.mtl.editors;


import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.text.rules.EndOfLineRule;
import org.eclipse.jface.text.rules.ICharacterScanner;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.IWordDetector;
import org.eclipse.jface.text.rules.MultiLineRule;
import org.eclipse.jface.text.rules.SingleLineRule;
import org.eclipse.jface.text.rules.Token;
import org.eclipse.jface.text.rules.WhitespaceRule;
import org.eclipse.jface.text.rules.WordRule;
import org.inria.mtl.editors.mtlsyntax.MTLConstantObject;
import org.inria.mtl.editors.mtlsyntax.MTLKeywordObject;
import org.inria.mtl.editors.mtlsyntax.MTLMethodObject;
import org.inria.mtl.editors.mtlsyntax.MTLObject;
import org.inria.mtl.editors.mtlsyntax.MTLSyntax;
import org.inria.mtl.editors.mtlsyntax.MTLTypeObject;
import org.inria.mtl.editors.outline.MTLReferenceDetector;
import org.inria.mtl.editors.outline.PredicateWordRuleOutline;
import org.inria.mtl.editors.utils.AbstractMTLScanner;
import org.inria.mtl.editors.utils.IColorManager;
import org.inria.mtl.editors.utils.MTLWordDetector;
import org.inria.mtl.editors.utils.MTLWhitespaceDetector;
import org.inria.mtl.preferences.PreferencesConstants;


/**
 * A MTL code scanner.
 */
public final class MTLCodeScanner extends AbstractMTLScanner /*implements MTLWords*/{
	
	public  static Token TOKEN_STRING =new Token(PreferencesConstants.MTL_STRING);
	public  static Token TOKEN_M_COMMENT =new Token(PreferencesConstants.MTL_MULTILINE_COMMENT);
	public  static Token TOKEN_S_COMMENT =new Token(PreferencesConstants.MTL_SINGLELINE_COMMENT);
	public  static Token TOKEN_DEFAULT =new Token(PreferencesConstants.MTL_DEFAULT);
	public  static Token TOKEN_FUNCTION =new Token(PreferencesConstants.MTL_FUNCTIONNAME);
	public  static Token TOKEN_MEMBER =new Token(PreferencesConstants.MTL_MEMBER);
	public  static Token TOKEN_KEYWORD =new Token(PreferencesConstants.MTL_KEYWORD);;
	public  static Token TOKEN_CONSTANT =new Token(PreferencesConstants.MTL_CONSTANT);
	public  static Token TOKEN_TYPE =new Token(PreferencesConstants.MTL_TYPE);
	

	private static String memberToken=  ".extension";

	
	
	private static class VersionedWordRule extends WordRule {

		private final IToken fDefaultToken;
		private final String fVersion;
		private final boolean fEnable;
		
		private String fCurrentVersion;

		public VersionedWordRule(IWordDetector detector, IToken defaultToken, String version, boolean enable, String currentVersion) {
			super(detector);

			fDefaultToken= defaultToken;
			fVersion= version;
			fEnable= enable;
			fCurrentVersion= currentVersion;
		}
		
		public void setCurrentVersion(String version) {
			fCurrentVersion= version;
		}
	
		/*
		 * @see IRule#evaluate
		 */
		public IToken evaluate(ICharacterScanner scanner) {
			IToken token= super.evaluate(scanner);

			if (fEnable) {
				if (fCurrentVersion.equals(fVersion) || token.isUndefined())
					return token;
//
				return fDefaultToken;

			} else {
				if (fCurrentVersion.equals(fVersion))
					return Token.UNDEFINED;
					
				return token;
			}
		}
	}
	
	
	private static String[] fgTokenProperties= {
		PreferencesConstants.MTL_KEYWORD,
		PreferencesConstants.MTL_STRING,
		PreferencesConstants.MTL_DEFAULT,
		PreferencesConstants.MTL_MULTILINE_COMMENT,
		PreferencesConstants.MTL_SINGLELINE_COMMENT,
		PreferencesConstants.MTL_CONSTANT,
		PreferencesConstants.MTL_TYPE,
		PreferencesConstants.MTL_FUNCTIONNAME,
		PreferencesConstants.MTL_MEMBER
	};
	
	private VersionedWordRule fVersionedWordRule;

	/**
	 * Creates a MTL code scanner
	 */
	public MTLCodeScanner(IColorManager manager, IPreferenceStore store) {
		super(manager, store);
		initialize();
	}
	
	/*
	 * @see AbstractJavaScanner#getTokenProperties()
	 */
	protected String[] getTokenProperties() {
		return fgTokenProperties;
	}

	/*
	 * @see AbstractJavaScanner#createRules()
	 */
	protected List createRules() {
				
		List rules= new ArrayList();	 
			
	//		Add rule for character constants and strings.
	  TOKEN_M_COMMENT= getToken(PreferencesConstants.MTL_MULTILINE_COMMENT);
	  rules.add(new MultiLineRule("/*", "*/", TOKEN_M_COMMENT)); //$NON-NLS-2$ //$NON-NLS-1$
	


	  TOKEN_S_COMMENT= getToken(PreferencesConstants.MTL_SINGLELINE_COMMENT);
	  rules.add(new EndOfLineRule("//", TOKEN_S_COMMENT)); //$NON-NLS-2$ //$NON-NLS-1$
	  
	// Add rule for character constants and strings.
	  TOKEN_STRING= getToken(PreferencesConstants.MTL_STRING);
	  rules.add(new SingleLineRule("'", "'", TOKEN_STRING, '\\')); //$NON-NLS-2$ //$NON-NLS-1$
	
	// Add generic whitespace rule.
	rules.add(new WhitespaceRule(new MTLWhitespaceDetector()));
		
	//		Add word rule for keywords, types, and constants.
		TOKEN_DEFAULT= getToken(PreferencesConstants.MTL_DEFAULT);
		WordRule wordRule= new WordRule(new MTLWordDetector(), TOKEN_DEFAULT);
	
		
		TOKEN_MEMBER= getToken(PreferencesConstants.MTL_MEMBER);
		rules.add(new PredicateWordRuleOutline(new MTLReferenceDetector(), memberToken, TOKEN_MEMBER));
	
		
		
		TOKEN_KEYWORD = getToken(PreferencesConstants.MTL_KEYWORD);
		TOKEN_FUNCTION=getToken(PreferencesConstants.MTL_FUNCTIONNAME);
		TOKEN_TYPE = getToken(PreferencesConstants.MTL_TYPE);
		TOKEN_CONSTANT = getToken(PreferencesConstants.MTL_CONSTANT);
			
		ArrayList buffer = MTLSyntax.getSyntaxData();
		MTLObject elbuffer = null;
				
		   for (int i = 0; i < buffer.size(); i++) {
			 elbuffer = (MTLObject) buffer.get(i);
			 if (elbuffer instanceof MTLKeywordObject)
				wordRule.addWord(((MTLKeywordObject) elbuffer).getName(), TOKEN_KEYWORD);
			 if (elbuffer instanceof MTLMethodObject)
			   wordRule.addWord(((MTLMethodObject) elbuffer).getName(), TOKEN_FUNCTION);
			 if (elbuffer instanceof MTLTypeObject)
			   wordRule.addWord(elbuffer.getName(), TOKEN_TYPE);
			 if (elbuffer instanceof MTLConstantObject)
			   wordRule.addWord(elbuffer.getName(), TOKEN_CONSTANT);
		   }
		   rules.add(wordRule);
		   return rules;

	}
}


