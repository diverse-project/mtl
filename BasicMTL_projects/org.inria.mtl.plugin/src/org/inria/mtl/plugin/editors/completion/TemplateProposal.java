/*
 * (c) Copyright IBM Corp. 2000, 2001.
 * All Rights Reserved.
 */
package org.inria.mtl.plugin.editors.completion;


import org.inria.mtl.plugin.editors.completion.template.*;
import org.inria.mtl.plugin.MTLPlugin;
import org.inria.mtl.plugin.editors.completion.link.LinkedPositionManager;
import org.inria.mtl.plugin.editors.completion.link.LinkedPositionUI;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.ITextViewer;
import org.eclipse.jface.text.contentassist.IContextInformation;
import org.eclipse.swt.graphics.Image;

/**
 * A template proposal.
 */
public class TemplateProposal extends AbstractProposal {
  private final TemplateContext fContext;
  private final Image fImage;
  private String fOldText;
  private final IRegion fRegion;

  private final Template fTemplate;

  private TemplateBuffer fTemplateBuffer;

  /**
   * Creates a template proposal with a template and its context.
   * @param template  the template
   * @param context   the context in which the template was requested.
   * @param image     the icon of the proposal.
   */
  public TemplateProposal(Template template, TemplateContext context, IRegion region, ITextViewer viewer, Image image) {
	super(viewer);

	fTemplate = template;
	fContext = context;
	//    fViewer = viewer;
	fImage = image;
	fRegion = region;
  }

  private static int getCaretOffset(TemplateBuffer buffer) {
	TemplatePosition[] variables = buffer.getVariables();
	for (int i = 0; i != variables.length; i++) {
	  TemplatePosition variable = variables[i];

	  if (variable.getName().equals(TemplateMessages.getString("GlobalVariables.variable.name.cursor"))) //$NON-NLS-1$
		return variable.getOffsets()[0];
	}

	return buffer.getString().length();
  }

  /*
   * @see ICompletionProposal#apply(IDocument)
   */
  public void apply(IDocument document) {
	try {
	  if (fTemplateBuffer == null)
		fTemplateBuffer = fContext.evaluate(fTemplate);

	  int start = fRegion.getOffset();
	  int end = fRegion.getOffset() + fRegion.getLength();

	  // insert template string
	  String templateString = fTemplateBuffer.getString();
	  document.replace(start, end - start, templateString);

	  // translate positions
	  LinkedPositionManager manager = new LinkedPositionManager(document);
	  TemplatePosition[] variables = fTemplateBuffer.getVariables();
	  for (int i = 0; i != variables.length; i++) {
		TemplatePosition variable = variables[i];

		if (variable.isResolved())
		  continue;

		int[] offsets = variable.getOffsets();
		int length = variable.getLength();

		for (int j = 0; j != offsets.length; j++)
		  manager.addPosition(offsets[j] + start, length);
	  }

	  LinkedPositionUI editor = new LinkedPositionUI(fViewer, manager);
	  editor.setFinalCaretOffset(getCaretOffset(fTemplateBuffer) + start);
	  editor.enter();

	  fSelectedRegion = editor.getSelectedRegion();

	} catch (BadLocationException e) {
	  MTLPlugin.log(e);
	  openErrorDialog(e);

	} catch (CoreException e) {
	  handleException(e);
	}
  }

  /*
   * @see ICompletionProposal#getAdditionalProposalInfo()
   */
  public String getAdditionalProposalInfo() {
	try {
	  if (fTemplateBuffer == null)
		fTemplateBuffer = fContext.evaluate(fTemplate);

	  return textToHTML(fTemplateBuffer.getString());

	} catch (CoreException e) {
	  handleException(e);
	  return null;
	}
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
	return fTemplate.getName() + TemplateMessages.getString("TemplateProposal.delimiter") + fTemplate.getDescription(); // $NON-NLS-1$ //$NON-NLS-1$
  }

  /*
   * @see ICompletionProposal#getImage()
   */
  public Image getImage() {
	return fImage;
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