/*
* $Id: IMTLCompletionProposal.java,v 1.1 2004-07-30 14:09:31 sdzale Exp $
* Authors : ${user}
*
* Created on ${date}
* Copyright 2004 - INRIA - LGPL license
*/ 
package org.inria.mtl.editors.completion;

import org.eclipse.jface.text.contentassist.ICompletionProposal;

/*
 * CompletionProposal with a relevance value.
 * The relevance value is used to sort the completion proposals. Proposals with higher relevance
 * should be listed before proposals with lower relevance.
 * A completion proposal contains information used to present the proposed completion to the user, to insert the completion should the user select it, and to present context information for the choosen completion once it has been inserted
 */
public interface IMTLCompletionProposal extends ICompletionProposal {
	
	/**
	 * Returns the relevance of the proposal.
	 */
	int getRelevance();

}
