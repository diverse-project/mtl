package org.inria.mtl.editors.outline;

import java.util.ArrayList;
import java.util.List;
import org.eclipse.jface.text.rules.MultiLineRule;
import org.eclipse.jface.text.rules.EndOfLineRule;
import org.eclipse.jface.text.rules.BufferedRuleBasedScanner;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.SingleLineRule;
import org.eclipse.jface.text.rules.Token;
import org.eclipse.jface.text.rules.WhitespaceRule;
import org.eclipse.jface.text.rules.WordRule;
import org.eclipse.jface.text.rules.IRule;
import org.inria.mtl.editors.utils.MTLWhitespaceDetector;
import org.inria.mtl.editors.utils.MTLWordDetector;
/**
 * Scanneur pour détecter la syntaxe des élémets: comments, strings, classes, functions
 */

public class MTLSyntaxScanner extends BufferedRuleBasedScanner {
	public final static String MTL_DEFAULT = "__MTL_default";
	public final static String MTL_COMMENT = "__MTL_comment";
	public final static String MTL_FUNCTION = "__MTL_function";
	public final static String MTL_STRING = "__MTL_string";
	public final static String MTL_MEMBER = "__MTL_member";
	public final static String MTL_PERIOD = "__MTL_period";

	public final static IToken TOKEN_STRING = new Token(MTL_STRING);
	public final static IToken TOKEN_COMMENT = new Token(MTL_COMMENT);
	public final static IToken TOKEN_DEFAULT = new Token(MTL_DEFAULT);
	public final static IToken TOKEN_FUNCTION = new Token(MTL_FUNCTION);
	public final static IToken TOKEN_MEMBER = new Token(MTL_MEMBER);
	public final static IToken TOKEN_PERIOD = new Token(MTL_PERIOD);

	/**
	 * String for detecting member declarations.
	 */
	private static String memberToken=  ".prototype";


	public MTLSyntaxScanner() {
		List rules = new ArrayList();

		//rules.add(new EndOfLineRule("--", TOKEN_COMMENT));
		rules.add(new SingleLineRule("//", "", TOKEN_COMMENT));
		rules.add(new MultiLineRule("/*", "*/", TOKEN_COMMENT));
		rules.add(new SingleLineRule("'", "'", TOKEN_STRING, '\\'));
		
		rules.add(new WhitespaceRule(new MTLWhitespaceDetector()));
		
		//rules.add(new MultiLineRule("class", "end", TOKEN_FUNCTION, '\r', true));
		//rules.add(new MultiLineRule("class", "end", TOKEN_FUNCTION, '\t', true));
		rules.add(new MultiLineRule("class ", "class", TOKEN_FUNCTION));
		//rules.add(new MultiLineRule("deferred class ", "class", TOKEN_FUNCTION, '\t', true));

		rules.add(new WordRule(new MTLWordDetector(), TOKEN_DEFAULT));
		rules.add(new PredicateWordRuleOutline(new MTLReferenceDetector(), memberToken, TOKEN_MEMBER));
		
		setRuleList(rules);
	}

	private void setRuleList(List rules)
	{
		IRule[] result = new IRule[rules.size()];
		rules.toArray(result);
		setRules(result);
	}
}