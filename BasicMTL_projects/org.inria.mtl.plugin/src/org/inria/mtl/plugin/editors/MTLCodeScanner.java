package org.inria.mtl.plugin.editors;


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
import org.inria.mtl.plugin.editors.mtlsyntax.MTLConstantObject;
import org.inria.mtl.plugin.editors.mtlsyntax.MTLKeywordObject;
import org.inria.mtl.plugin.editors.mtlsyntax.MTLMethodObject;
import org.inria.mtl.plugin.editors.mtlsyntax.MTLObject;
import org.inria.mtl.plugin.editors.mtlsyntax.MTLSyntax;
import org.inria.mtl.plugin.editors.mtlsyntax.MTLTypeObject;
import org.inria.mtl.plugin.editors.utils.AbstractMTLScanner;
import org.inria.mtl.plugin.editors.utils.IColorManager;
import org.inria.mtl.plugin.editors.utils.MTLWordDetector;
import org.inria.mtl.plugin.editors.utils.MTLWhitespaceDetector;
import org.inria.mtl.plugin.preferences.PreferenceConstants;


/**
 * A MTL code scanner.
 */
public final class MTLCodeScanner extends AbstractMTLScanner /*implements MTLWords*/{
	
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
		PreferenceConstants.MTL_KEYWORD,
		PreferenceConstants.MTL_STRING,
		PreferenceConstants.MTL_DEFAULT,
		PreferenceConstants.MTL_MULTILINE_COMMENT,
		PreferenceConstants.MTL_SINGLELINE_COMMENT,
		PreferenceConstants.MTL_CONSTANT,
		PreferenceConstants.MTL_TYPE,
		PreferenceConstants.MTL_FUNCTIONNAME
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
	  Token token= getToken(PreferenceConstants.MTL_MULTILINE_COMMENT);
	  rules.add(new MultiLineRule("/*", "*/", token)); //$NON-NLS-2$ //$NON-NLS-1$


	  token= getToken(PreferenceConstants.MTL_SINGLELINE_COMMENT);
	  rules.add(new EndOfLineRule("//", token)); //$NON-NLS-2$ //$NON-NLS-1$
			
		
		
		// Add rule for character constants and strings.
	  token= getToken(PreferenceConstants.MTL_STRING);
	  rules.add(new SingleLineRule("'", "'", token, '\\')); //$NON-NLS-2$ //$NON-NLS-1$
				
		
		// Add generic whitespace rule.
		rules.add(new WhitespaceRule(new MTLWhitespaceDetector()));
		
		//		Add word rule for keywords, types, and constants.
		token= getToken(PreferenceConstants.MTL_DEFAULT);
		WordRule wordRule= new WordRule(new MTLWordDetector(), token);
		
		
		Token keyword = getToken(PreferenceConstants.MTL_KEYWORD);
	    Token functionName = getToken(PreferenceConstants.MTL_FUNCTIONNAME);
		Token type = getToken(PreferenceConstants.MTL_TYPE);
		Token constant = getToken(PreferenceConstants.MTL_CONSTANT);
		
		ArrayList buffer = MTLSyntax.getSyntaxData();
		MTLObject elbuffer = null;
				
		   for (int i = 0; i < buffer.size(); i++) {
			 elbuffer = (MTLObject) buffer.get(i);
			 if (elbuffer instanceof MTLKeywordObject)
				wordRule.addWord(((MTLKeywordObject) elbuffer).getName(), keyword);
			 if (elbuffer instanceof MTLMethodObject)
			   wordRule.addWord(((MTLMethodObject) elbuffer).getName(), functionName);
			 if (elbuffer instanceof MTLTypeObject)
			   wordRule.addWord(elbuffer.getName(), type);
			 if (elbuffer instanceof MTLConstantObject)
			   wordRule.addWord(elbuffer.getName(), constant);
		   }
		   rules.add(wordRule);
		   return rules;

	}
}


