package org.inria.mtl.plugin.editors.doc;

import java.util.*;
import org.eclipse.jface.text.TextAttribute;
import org.eclipse.jface.text.rules.*;
import org.eclipse.swt.SWT;
import org.inria.mtl.plugin.editors.MTLWords;
import org.inria.mtl.plugin.editors.utils.*;

/**
 * A rule based C Sharp Doc scanner.
 */
public class MTLDocScanner
	extends RuleBasedScanner
	implements MTLWords {

	/**
	 * A key word detector.
	 */
	static class MTLDocKeyWordDetector implements IWordDetector {
		public boolean isWordStart(char c) {
			return c=='<';
		}

		public boolean isWordPart(char c) {
			return Character.isLetter(c) || c=='/' || c=='>';
		}
	};


	/**
	 * Create a new c sharp doc scanner.
	 */
	public MTLDocScanner(MTLEditorColorProvider provider) {
		super();
		
		IToken defaultToken =
			new Token(new TextAttribute(provider.getColor(MTLEditorColorProvider.MTLDOC_DEFAULT)));
		IToken keyword =
			new Token(new TextAttribute(provider.getColor(MTLEditorColorProvider.MTLDOC_KEYWORD), provider.getColor(MTLEditorColorProvider.BACKGROUND), SWT.BOLD));
		IToken tag =
			new Token(new TextAttribute(provider.getColor(MTLEditorColorProvider.MTLDOC_TAG)));

		setDefaultReturnToken(defaultToken);

		List list = new ArrayList();

		// Add generic whitespace rule.
		list.add(new WhitespaceRule(new MTLWhitespaceDetector()));

		// Add word rule for keywords.
//		WordRule tagRule = new WordRule(new MTLDocKeyWordDetector(), tag);
//		for (int i = 0; i < MTLDocWords.length; i++) {
//			tagRule.addWord("<" + MTLDocWords[i] + ">", keyword); //$NON-NLS-1$ //$NON-NLS-2$
//			tagRule.addWord("</" + MTLDocWords[i] + ">", keyword); //$NON-NLS-1$ //$NON-NLS-2$
//		}
//		list.add(tagRule);


		IRule[] result = new IRule[list.size()];
		list.toArray(result);
		setRules(result);
	}
}