package org.inria.mtl.plugin.editors.utils;

/*
 * (c) Copyright IBM Corp. 2000, 2001.
 * All Rights Reserved.
 */

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.text.rules.EndOfLineRule;
import org.eclipse.jface.text.rules.ICharacterScanner;
import org.eclipse.jface.text.rules.IPredicateRule;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.IWordDetector;
import org.eclipse.jface.text.rules.MultiLineRule;
import org.eclipse.jface.text.rules.RuleBasedPartitionScanner;
import org.eclipse.jface.text.rules.SingleLineRule;
import org.eclipse.jface.text.rules.Token;
import org.eclipse.jface.text.rules.WordRule;


/**
 * This scanner recognizes the MTL comments and MTL multi line comments.
 */
public class MTLPartitionScanner extends RuleBasedPartitionScanner {

	private final static String SKIP= "__skip"; //$NON-NLS-1$
	
	public final static String MTL_STRING= "__mtl_string"; //$NON-NLS-1$
	public final static String MTL_SINGLE_LINE_COMMENT= "__mtl_singleline_comment"; //$NON-NLS-1$
	public final static String MTL_MULTI_LINE_COMMENT= "__mtl_multiline_comment"; //$NON-NLS-1$
	public final static String MTL_DOC= "__mtl_MTLdoc"; //$NON-NLS-1$

	
	/**
	 * Detector for empty comments.
	 */
	static class EmptyCommentDetector implements IWordDetector {

		/*
		 * @see IWordDetector#isWordStart
		 */
		public boolean isWordStart(char c) {
			return (c == '/');
		}

		/*
		 * @see IWordDetector#isWordPart
		 */
		public boolean isWordPart(char c) {
			return (c == '*' || c == '/');
		}
	};
	
	
	/**
	 * Word rule for empty comments.
	 */
	static class EmptyCommentRule extends WordRule implements IPredicateRule {
		
		private IToken fSuccessToken;
		/**
		 * Constructor for EmptyCommentRule.
		 * @param defaultToken
		 */
		public EmptyCommentRule(IToken successToken) {
			super(new EmptyCommentDetector());
			fSuccessToken= successToken;
			addWord("/**/", fSuccessToken); //$NON-NLS-1$
		}
		
		/*
		 * @see IPredicateRule#evaluate(ICharacterScanner, boolean)
		 */
		public IToken evaluate(ICharacterScanner scanner, boolean resume) {
			return evaluate(scanner);
		}

		/*
		 * @see IPredicateRule#getSuccessToken()
		 */
		public IToken getSuccessToken() {
			return fSuccessToken;
		}
	};

	
	
	/**
	 * Creates the partitioner and sets up the appropriate rules.
	 */
	public MTLPartitionScanner() {
		super();
		
		IToken skip= new Token(SKIP);
		IToken string= new Token(MTL_STRING);
		IToken MTLDoc= new Token(MTL_DOC);
		IToken multiLineComment= new Token(MTL_MULTI_LINE_COMMENT);
		IToken singleLineComment= new Token(MTL_SINGLE_LINE_COMMENT);

		List rules= new ArrayList();

		// Add rule for single line comments.
		rules.add(new EndOfLineRule("//", singleLineComment)); //$NON-NLS-1$

		// Add rule for strings.
		rules.add(new SingleLineRule("\"", "\"", string, '\\')); //$NON-NLS-2$ //$NON-NLS-1$
		
		// Add rule for character constants.
		rules.add(new SingleLineRule("'", "'", skip, '\\')); //$NON-NLS-2$ //$NON-NLS-1$

		// Add special case word rule.
		EmptyCommentRule wordRule= new EmptyCommentRule(multiLineComment);
		rules.add(wordRule);

		// Add rules for multi-line comments and MTLdoc.
		rules.add(new MultiLineRule("/**", "*/", MTLDoc)); //$NON-NLS-1$ //$NON-NLS-2$
		rules.add(new MultiLineRule("/*", "*/", multiLineComment)); //$NON-NLS-1$ //$NON-NLS-2$

		IPredicateRule[] result= new IPredicateRule[rules.size()];
		rules.toArray(result);
		setPredicateRules(result);
	}
}

