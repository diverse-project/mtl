package org.inria.mtl.plugin.editors.doc;

import java.util.Iterator;
import java.util.Vector;

import org.eclipse.jface.text.ITextViewer;
import org.eclipse.jface.text.contentassist.CompletionProposal;
import org.eclipse.jface.text.contentassist.ICompletionProposal;
import org.eclipse.jface.text.contentassist.IContentAssistProcessor;
import org.eclipse.jface.text.contentassist.IContextInformation;
import org.eclipse.jface.text.contentassist.IContextInformationValidator;
import org.inria.mtl.plugin.editors.MTLWords;
import org.inria.mtl.plugin.editors.utils.PieceOfWord;

/**
 * C Sharp completion processor.
 */
public class MTLCompletionProcessor
	implements IContentAssistProcessor, MTLWords {

	
	protected Vector proposalVector = new Vector();

	protected int currentIndex = 0;


	/* (non-Javadoc)
	 * Method declared on IContentAssistProcessor
	 */
	public ICompletionProposal[] computeCompletionProposals(
		ITextViewer viewer,
		int documentOffset) {
		
		PieceOfWord word = new PieceOfWord(viewer, documentOffset);
		
		// iterate over all the different categories
		for (int i = 0; i < allWords.length; i++) {
			String[] list = (String[]) allWords[i];
			// iterate over the current category
			for (int y = 0; y < list.length; y++) {
				if (list[y].startsWith(word.getString())) 
					proposalVector.add(list[y]);
			}			
		}		
			
		return turnProposalVectorIntoAdaptedArray(word);
	}


	/*
	 * Turns the vector into an Array of ICompletionProposal objects
	 */
	protected ICompletionProposal[] turnProposalVectorIntoAdaptedArray(PieceOfWord word) {
		ICompletionProposal[] result = new ICompletionProposal[proposalVector.size()];
		int index = 0;
		
		for (Iterator i = proposalVector.iterator(); i.hasNext(); ) {
			String keyWord = (String) i.next();
			result[index] =
				new CompletionProposal(
					keyWord,
					word.getOffset(),
					word.getString().length(),
					keyWord.length());
			index++;
		}
		//System.out.println("result : " + result.length);
		proposalVector.removeAllElements();
		return result;
	}

	/* (non-Javadoc)
	 * Method declared on IContentAssistProcessor
	 */
	public IContextInformation[] computeContextInformation(
		ITextViewer viewer,
		int documentOffset) {
		return null;
	}

	/* (non-Javadoc)
	 * Method declared on IContentAssistProcessor
	 */
	public char[] getCompletionProposalAutoActivationCharacters() {
		// former code: return new char[] { '.', '(' };
		// we can't propose C# methods for a given type so far, so we just don't enable auto activation
		return null;
	}

	/* (non-Javadoc)
	 * Method declared on IContentAssistProcessor
	 */
	public char[] getContextInformationAutoActivationCharacters() {
		return null;
	}

	/* (non-Javadoc)
	 * Method declared on IContentAssistProcessor
	 */
	public IContextInformationValidator getContextInformationValidator() {
		return null;
	}

	/* (non-Javadoc)
	 * Method declared on IContentAssistProcessor
	 */
	public String getErrorMessage() {
		return null;
	}
}