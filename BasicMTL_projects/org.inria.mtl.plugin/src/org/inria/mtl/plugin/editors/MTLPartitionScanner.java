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
	 * Creates the partitioner and sets up the appropriate rules.
	 */
	public MTLPartitionScanner() {
		super();

		IToken mtlDoc= new Token(MTL_DOC);
		IToken comment= new Token(MTL_MULTILINE_COMMENT);

		List rules= new ArrayList();


		// Add rules for multi-line comments 
		rules.add(new MultiLineRule("/*", "*/", comment)); //$NON-NLS-1$ //$NON-NLS-2$

		// Add rules for c sharp doc.
		rules.add(new EndOfLineRule("///", mtlDoc)); //$NON-NLS-1$ 

		// Add rule for single line comments.
		rules.add(new EndOfLineRule("//", Token.UNDEFINED)); //$NON-NLS-1$

		// Add rule for strings and character constants.
		rules.add(new SingleLineRule("\"", "\"", Token.UNDEFINED, '\\')); //$NON-NLS-2$ //$NON-NLS-1$
		rules.add(new SingleLineRule("'", "'", Token.UNDEFINED, '\\')); //$NON-NLS-2$ //$NON-NLS-1$

		// Add special case word rule.
		WordPatternRule wordRule= new WordPatternRule(new EmptyCommentDetector(), "/*", "*/", comment); //$NON-NLS-2$ //$NON-NLS-1$
		rules.add(wordRule);
		

		IPredicateRule[] result= new IPredicateRule[rules.size()];
		rules.toArray(result);
		setPredicateRules(result);
	}
}
