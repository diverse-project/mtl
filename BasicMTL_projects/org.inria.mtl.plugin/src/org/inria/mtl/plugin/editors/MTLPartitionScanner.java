/*
* $Id: MTLPartitionScanner.java,v 1.4 2004-05-19 09:22:38 sdzale Exp $
* Authors : ${user}
*
* Created on ${date}
* Copyright 2004 - INRIA - LGPL license
*/ 
package org.inria.mtl.plugin.editors;


import java.util.ArrayList;
import java.util.List;
import org.eclipse.jface.text.rules.*;

/**
 * This scanner recognizes the MTL Doc comments and MTL multi line comments.
 */
public class MTLPartitionScanner extends RuleBasedPartitionScanner {

	public final static String MTL_MULTILINE_COMMENT= "__mtl_multiline_comment"; //$NON-NLS-1$
	public final static String MTL_DOC= "__mtl_doc"; //$NON-NLS-1$
	private final static String SKIP= "__skip"; //$NON-NLS-1$
	public final static String MTL_STRING= "__mtl_string"; //$NON-NLS-1$
	public final static String MTL_SINGLE_LINE_COMMENT= "__mtl_singleline_comment"; //$NON-NLS-1$
	public final static String MTL_MULTI_LINE_COMMENT= "__mtl_multiline_comment"; //$NON-NLS-1$
	


	/**
	 * Detector for empty comments.
	 */
	static class EmptyCommentDetector implements IWordDetector {

		/* (non-Javadoc)
		* Method declared on IWordDetector
		*/
		public boolean isWordStart(char c) {
			return (c == '/');
		}

		/* (non-Javadoc)
		* Method declared on IWordDetector
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

		IToken mtlDoc= new Token(MTL_DOC);
		//IToken comment= new Token(MTL_MULTILINE_COMMENT);
		IToken skip= new Token(SKIP);
		IToken string= new Token(MTL_STRING);
		IToken multiLineComment= new Token(MTL_MULTI_LINE_COMMENT);
		IToken singleLineComment= new Token(MTL_SINGLE_LINE_COMMENT);


		List rules= new ArrayList();


		// Add rules for multi-line comments 
		rules.add(new MultiLineRule("/*", "*/", multiLineComment)); //$NON-NLS-1$ //$NON-NLS-2$
		
		// Add rule for single line comments.
			rules.add(new EndOfLineRule("//", singleLineComment)); //$NON-NLS-1$

		// Add rule for strings.
		rules.add(new SingleLineRule("'", "'", string, '\\')); //$NON-NLS-2$ //$NON-NLS-1$
		
		// Add special case word rule.
		EmptyCommentRule wordRule= new EmptyCommentRule(multiLineComment);
		rules.add(wordRule);


		IPredicateRule[] result= new IPredicateRule[rules.size()];
		rules.toArray(result);
		setPredicateRules(result);
	}
}
