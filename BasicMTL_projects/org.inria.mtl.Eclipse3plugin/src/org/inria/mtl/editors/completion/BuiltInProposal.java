/*
* $Id: BuiltInProposal.java,v 1.2 2004-08-26 12:40:32 sdzale Exp $
* Authors : ${user}
*
* Created on ${date}
* Copyright 2004 - INRIA - LGPL license
*/ 
package org.inria.mtl.editors.completion;

import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.ITextViewer;
import org.eclipse.jface.text.contentassist.IContextInformation;
import org.eclipse.swt.graphics.Image;
import org.inria.mtl.MTLPlugin;
import org.inria.mtl.editors.completion.link.LinkedPositionManager;
import org.inria.mtl.editors.completion.link.LinkedPositionUI;
import org.inria.mtl.editors.completion.template.MTLUnitContext;
import org.inria.mtl.editors.completion.template.TemplateContext;
import org.inria.mtl.editors.completion.template.TemplateMessages;
import org.inria.mtl.editors.mtlsyntax.MTLMethodObject;

/**
 * A MTL identifier proposal.
 */
public class BuiltInProposal extends AbstractProposal { // implements IMTLCompletionProposal {
  private final TemplateContext fContext;
  private final MTLMethodObject fFunction;

  //private TemplateBuffer fTemplateBuffer;
  private String fOldText;
  //  private final Image fImage_builtin;

  private final IRegion fRegion;
  //  private IRegion fSelectedRegion; // initialized by apply()

  private final String fBuiltinFunctionName;
  //  private final ITextViewer fViewer;

  /**
   * Creates a template proposal with a template and its context.
   * @param template  the template
   * @param context   the context in which the template was requested.
   * @param image     the icon of the proposal.
   */
  public BuiltInProposal(String functionName, MTLMethodObject function, TemplateContext context, IRegion region, ITextViewer viewer) {
	super(viewer);
	fBuiltinFunctionName = functionName;
	fFunction = function;
	fContext = context;
	//    fViewer = viewer;
	fRegion = region;
  }

  /*
   * @see ICompletionProposal#apply(IDocument)
   */
  public void apply(IDocument document) {
	try {
	  //		    if (fTemplateBuffer == null)
	  //				fTemplateBuffer= fContext.evaluate(fTemplate);

	  int start = fRegion.getOffset();
	  int end = fRegion.getOffset() + fRegion.getLength();

	  // insert template string
	  document.replace(start, end - start, fBuiltinFunctionName + "()");

	  // translate positions
	  LinkedPositionManager manager = new LinkedPositionManager(document);
	
	  LinkedPositionUI editor = new LinkedPositionUI(fViewer, manager);
	  editor.setFinalCaretOffset(fBuiltinFunctionName.length() + start + 1);
	  editor.enter();

	  fSelectedRegion = editor.getSelectedRegion();

	} catch (BadLocationException e) {
	  MTLPlugin.log(e);
	  openErrorDialog(e);

	}
		    
  }

  /*
   * @see ICompletionProposal#getAdditionalProposalInfo()
   */
  public String getAdditionalProposalInfo() {
	return textToHTML(fFunction.getDescription()); // fTemplateBuffer.getString());
  }

  /*
   * @see ICompletionProposal#getContextInformation()
   */
  public IContextInformation getContextInformation() {
	return null;
  }

  /*
   * @see ICompletionProposal#getDisplayString()
   */
  public String getDisplayString() {
	return fBuiltinFunctionName + TemplateMessages.getString("TemplateProposal.delimiter") + fFunction.getUsage(); // $NON-NLS-1$ //$NON-NLS-1$
	//		return fTemplate.getName() + ObfuscatorMessages.getString("TemplateProposal.delimiter") + fTemplate.getDescription(); // $NON-NLS-1$ //$NON-NLS-1$
  }

  /*
   * @see ICompletionProposal#getImage()
   */
  public Image getImage() {
	return MTLImages.get(MTLImages.IMG_BUILTIN);
  }

  /*
   * @see IJavaCompletionProposal#getRelevance()
   */
  public int getRelevance() {

	if (fContext instanceof MTLUnitContext) {
	  MTLUnitContext context = (MTLUnitContext) fContext;
	  switch (context.getCharacterBeforeStart()) {
		// high relevance after whitespace
		case ' ' :
		case '\r' :
		case '\n' :
		case '\t' :
		  return 90;

		default :
		  return 0;
	  }
	} else {
	  return 90;
	}
  }

}