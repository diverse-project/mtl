package org.inria.mtl.plugin.editors.completion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.ITextViewer;
import org.eclipse.jface.text.TextPresentation;
import org.eclipse.jface.text.contentassist.ICompletionProposal;
import org.eclipse.jface.text.contentassist.IContentAssistProcessor;
import org.eclipse.jface.text.contentassist.IContextInformation;
import org.eclipse.jface.text.contentassist.IContextInformationExtension;
import org.eclipse.jface.text.contentassist.IContextInformationPresenter;
import org.eclipse.jface.text.contentassist.IContextInformationValidator;
import org.eclipse.swt.graphics.Image;
import org.inria.mtl.plugin.editors.completion.template.CompilationUnitContextType;
import org.inria.mtl.plugin.editors.completion.template.ContextType;
import org.inria.mtl.plugin.editors.completion.template.ContextTypeRegistry;
import org.inria.mtl.plugin.editors.completion.template.MTLUnitContext;
import org.inria.mtl.plugin.editors.mtlsyntax.MTLSyntax;



/**
 * MTL completion processor.
 */
public class MTLCompletionProcessor implements IContentAssistProcessor {
		
	/**
	  * Simple content assist tip closer. The tip is valid in a range
	  * of 5 characters around its popup location.
	  */
	 protected static class Validator implements IContextInformationValidator, IContextInformationPresenter {

	   protected int fInstallOffset;

	   /*
		* @see IContextInformationValidator#isContextInformationValid(int)
		*/
	   public boolean isContextInformationValid(int offset) {
		 return Math.abs(fInstallOffset - offset) < 5;
	   }

	   /*
		* @see IContextInformationValidator#install(IContextInformation, ITextViewer, int)
		*/
	   public void install(IContextInformation info, ITextViewer viewer, int offset) {
		 fInstallOffset = offset;
	   }

	   /*
		* @see org.eclipse.jface.text.contentassist.IContextInformationPresenter#updatePresentation(int, TextPresentation)
		*/
	   public boolean updatePresentation(int documentPosition, TextPresentation presentation) {
		 return false;
	   }
	 };

	 private static class ContextInformationWrapper implements IContextInformation, IContextInformationExtension {

	   private final IContextInformation fContextInformation;
	   private int fPosition;

	   public ContextInformationWrapper(IContextInformation contextInformation) {
		 fContextInformation = contextInformation;
	   }

	   /*
		* @see IContextInformation#getContextDisplayString()
		*/
	   public String getContextDisplayString() {
		 return fContextInformation.getContextDisplayString();
	   }

	   /*
	   * @see IContextInformation#getImage()
	   */
	   public Image getImage() {
		 return fContextInformation.getImage();
	   }

	   /*
		* @see IContextInformation#getInformationDisplayString()
		*/
	   public String getInformationDisplayString() {
		 return fContextInformation.getInformationDisplayString();
	   }

	   /*
		* @see IContextInformationExtension#getContextInformationPosition()
		*/
	   public int getContextInformationPosition() {
		 return fPosition;
	   }

	   public void setContextInformationPosition(int position) {
		 fPosition = position;
	   }
	 };

	 private char[] fProposalAutoActivationSet;
	 protected IContextInformationValidator fValidator = new Validator();
	 private TemplateEngine fTemplateEngine;
	 private MTLCompletionProposalComparator fComparator;
	 private int fNumberOfComputedResults = 0;

	 public MTLCompletionProcessor() {

	   ContextType contextType = ContextTypeRegistry.getInstance().getContextType("php"); //$NON-NLS-1$
	   if (contextType != null)
		 fTemplateEngine = new TemplateEngine(contextType);

	   fComparator = new MTLCompletionProposalComparator();
	 }

	 /**
	  * Tells this processor to order the proposals alphabetically.
	  * 
	  * @param order <code>true</code> if proposals should be ordered.
	  */
	 public void orderProposalsAlphabetically(boolean order) {
	   fComparator.setOrderAlphabetically(order);
	 }

	 /**
	  * Sets this processor's set of characters triggering the activation of the
	  * completion proposal computation.
	  * 
	  * @param activationSet the activation set
	  */
	 public void setCompletionProposalAutoActivationCharacters(char[] activationSet) {
	   fProposalAutoActivationSet = activationSet;
	 }

	 /* (non-Javadoc)
	  * Method declared on IContentAssistProcessor
	  */
	 public ICompletionProposal[] computeCompletionProposals(ITextViewer viewer, int documentOffset) {
	   int contextInformationPosition = guessContextInformationPosition(viewer, documentOffset);
	   return internalComputeCompletionProposals(viewer, documentOffset, contextInformationPosition);
	 }

//	 private int getLastToken(ITextViewer viewer, int completionPosition, MTLUnitContext context) {
//	   IDocument document = viewer.getDocument();
//	   int start = context.getStart();
//	   int end = context.getEnd();
//
//	   String startText;
//	  // int lastSignificantToken = ITerminalSymbols.TokenNameEOF;
//
//	   try {
//		 // begin search 2 lines behind of this
//		 int j = start;
//		 if (j != 0) {
//		   char ch;
//		   while (j-- > 0) {
//			 ch = document.getChar(j);
//			 if (ch == '\n') {
//			   break;
//			 }
//		   }
//		   while (j-- > 0) {
//			 ch = document.getChar(j);
//			 if (ch == '\n') {
//			   break;
//			 }
//		   }
//		 }
//		 if (j != start) {
//		   // scan the line for the dereferencing operator '->'
//		   startText = document.get(j, start - j);
//		   //						System.out.println(startText);
//		   Scanner scanner = ToolFactory.createScanner(false, false, false);
//		   scanner.setSource(startText.toCharArray());
//		   scanner.setPHPMode(true);
//		   int token = ITerminalSymbols.TokenNameEOF;
//		   int beforeLastToken = ITerminalSymbols.TokenNameEOF;
//		   int lastToken = ITerminalSymbols.TokenNameEOF;
//
//		   try {
//			 token = scanner.getNextToken();
//			 lastToken = token;
//			 while (token != ITerminalSymbols.TokenNameERROR && token != ITerminalSymbols.TokenNameEOF) {
//			   beforeLastToken = lastToken;
//			   lastToken = token;
//			   //								System.out.println(scanner.toStringAction(lastToken));
//			   token = scanner.getNextToken();
//			 }
//		   } catch (InvalidInputException e1) {
//		   }
//		   switch (lastToken) {
//			 case ITerminalSymbols.TokenNameMINUS_GREATER :
//			   // dereferencing operator '->' found
//			   lastSignificantToken = ITerminalSymbols.TokenNameMINUS_GREATER;
//			   if (beforeLastToken == ITerminalSymbols.TokenNamethis) {
//				 lastSignificantToken = ITerminalSymbols.TokenNamethis;
//			   }
//			   break;
//			 case ITerminalSymbols.TokenNamenew :
//			   lastSignificantToken = ITerminalSymbols.TokenNamenew;
//			   break;
//		   }
//		 }
//	   } catch (BadLocationException e) {
//	   }
//	   return lastSignificantToken;
//	 }
//
	 private ICompletionProposal[] internalComputeCompletionProposals(ITextViewer viewer, int offset, int contextOffset) {
	   IDocument document = viewer.getDocument();
	   Object[] identifiers = null;
	   IFile file = null;
	   IProject project = null;
	   if (offset > 0) {

	   ContextType MTLContextType = ContextTypeRegistry.getInstance().getContextType("mtl"); //$NON-NLS-1$
		((CompilationUnitContextType) MTLContextType).setContextParameters(document, offset, 0);

	   MTLUnitContext context = (MTLUnitContext) MTLContextType.createContext();
	   String prefix = context.getKey();
	   boolean emptyPrefix = prefix == null || prefix.equals("");
	   
	   IMTLCompletionProposal[] templateResults = new IMTLCompletionProposal[0];
	   ICompletionProposal[] results;

	   if (fTemplateEngine != null) {
		// IMTLCompletionProposal[] templateResults = new IMTLCompletionProposal[0];

		//ICompletionProposal[] results;
		 if (!emptyPrefix) {
		   fTemplateEngine.reset();
		   fTemplateEngine.complete(viewer, offset); //, unit);
		   templateResults = fTemplateEngine.getResults();
		 }
	   }
    
		 IMTLCompletionProposal[] identifierResults = new IMTLCompletionProposal[0];
		 // built in function names from MTLsyntax.xml
		 ArrayList syntaxbuffer = MTLSyntax.getSyntaxData();
		 IMTLCompletionProposal[] builtinResults = new IMTLCompletionProposal[0];
		 boolean useClassMembers =false;
		 if ((!useClassMembers) && syntaxbuffer != null) {
		   BuiltInEngine builtinEngine;
		   String proposal;

		   ContextType contextType = ContextTypeRegistry.getInstance().getContextType("mtl"); //$NON-NLS-1$
		   if (contextType != null) {
			 builtinEngine = new BuiltInEngine(contextType);
			 builtinEngine.complete(viewer, offset, syntaxbuffer);
			 builtinResults = builtinEngine.getResults();
		   }
		 }
		 // concatenate the result arrays
		 IMTLCompletionProposal[] total;
		 total =
		   new IMTLCompletionProposal[templateResults.length
			 + builtinResults.length];

		 System.arraycopy(templateResults, 0, total, 0, templateResults.length);
		 System.arraycopy(builtinResults, 0, total, templateResults.length , builtinResults.length);

		 results = total;

		 fNumberOfComputedResults = (results == null ? 0 : results.length);
		
		 /*
		  * Order here and not in result collector to make sure that the order
		  * applies to all proposals  
		  */
		return order(results); 
	   
	   
	   }
	   return new IMTLCompletionProposal[0];
	 }

	 private int guessContextInformationPosition(ITextViewer viewer, int offset) {
	   int contextPosition = offset;

	   IDocument document = viewer.getDocument();
	   return contextPosition;
	 }

		 /**
	  * @see IContentAssistProcessor#computeContextInformation(ITextViewer, int)
	  */
	 public IContextInformation[] computeContextInformation(ITextViewer viewer, int offset) {
	   int contextInformationPosition = guessContextInformationPosition(viewer, offset);
	   List result = addContextInformations(viewer, contextInformationPosition);
	   return (IContextInformation[]) result.toArray(new IContextInformation[result.size()]);
	 }

	 private List addContextInformations(ITextViewer viewer, int offset) {
	   ICompletionProposal[] proposals = internalComputeCompletionProposals(viewer, offset, -1);

	   List result = new ArrayList();
	   for (int i = 0; i < proposals.length; i++) {
		 IContextInformation contextInformation = proposals[i].getContextInformation();
		 if (contextInformation != null) {
		   ContextInformationWrapper wrapper = new ContextInformationWrapper(contextInformation);
		   wrapper.setContextInformationPosition(offset);
		   result.add(wrapper);
		 }
	   }
	   return result;
	 }

	 /**
	  * Order the given proposals.
	  */
	 private ICompletionProposal[] order(ICompletionProposal[] proposals) {
	   Arrays.sort(proposals, fComparator);
	   return proposals;
	 }

	 /* (non-Javadoc)
	  * Method declared on IContentAssistProcessor
	  */
	 public char[] getCompletionProposalAutoActivationCharacters() {
	   return fProposalAutoActivationSet;
	   //    return null; // new char[] { '$' };
	 }

	 /* (non-Javadoc)
	  * Method declared on IContentAssistProcessor
	  */
	 public char[] getContextInformationAutoActivationCharacters() {
	   return new char[] {
	   };
	 }

	 /* (non-Javadoc)
	  * Method declared on IContentAssistProcessor
	  */
	 public IContextInformationValidator getContextInformationValidator() {
	   return fValidator;
	 }

	 /* (non-Javadoc)
	  * Method declared on IContentAssistProcessor
	  */
	 public String getErrorMessage() {
	   return null;
	 }
   }