/*
* $Id: MTLCompletionProposalComparator.java,v 1.1 2004-07-30 14:09:31 sdzale Exp $
* Authors : ${user}
*
* Created on ${date}
* Copyright 2004 - INRIA - LGPL license
*/ 
package org.inria.mtl.editors.completion;

import java.util.Comparator;



/**
 * Order completion proposal 
 * @author sdzale
 *
 */
public class MTLCompletionProposalComparator implements Comparator {

	private boolean fOrderAlphabetically;

	/**
	 * Constructor for CompletionProposalComparator.
	 */
	public MTLCompletionProposalComparator() {
		fOrderAlphabetically= false;
	}
	
	public void setOrderAlphabetically(boolean orderAlphabetically) {
		fOrderAlphabetically= orderAlphabetically;
	}
	
	/* (non-Javadoc)
	 * @see Comparator#compare(Object, Object)
	 */
	public int compare(Object o1, Object o2) {
		IMTLCompletionProposal c1= (IMTLCompletionProposal) o1;
		IMTLCompletionProposal c2= (IMTLCompletionProposal) o2;
		if (!fOrderAlphabetically) {
			int relevanceDif= c2.getRelevance() - c1.getRelevance();
			if (relevanceDif != 0) {
				return relevanceDif;
			}
		}
		return c1.getDisplayString().compareToIgnoreCase(c2.getDisplayString());
	}	
	
}
