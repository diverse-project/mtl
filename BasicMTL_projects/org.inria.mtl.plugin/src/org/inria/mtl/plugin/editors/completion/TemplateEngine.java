/*
 * (c) Copyright IBM Corp. 2000, 2001.
 * All Rights Reserved.
 */
package org.inria.mtl.plugin.editors.completion;

import java.util.ArrayList;

import org.inria.mtl.plugin.editors.completion.template.ContextType;
import org.inria.mtl.plugin.editors.completion.template.DocumentTemplateContext;
import org.inria.mtl.plugin.editors.completion.template.Template;
import org.inria.mtl.plugin.editors.completion.template.Templates;
import org.inria.mtl.plugin.editors.completion.template.CompilationUnitContextType;
import  org.inria.mtl.plugin.MTLPlugin;

import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.ITextViewer;
import org.eclipse.jface.text.Region;
import org.eclipse.swt.graphics.Point;
import org.inria.mtl.plugin.editors.completion.link.LinkedPositionManager;

public class TemplateEngine {

  /** The context type. */
  private ContextType fContextType;
  /** The result proposals. */
  private ArrayList fProposals = new ArrayList();

  /**
   * Creates the template engine for a particular context type.
   * See <code>TemplateContext</code> for supported context types.
   */
  public TemplateEngine(ContextType contextType) {
	//	Assert.isNotNull(contextType);
	fContextType = contextType;
  }

  /**
   * Empties the collector.
   * 
   * @param viewer the text viewer  
   * @param unit   the compilation unit (may be <code>null</code>)
   */
  public void reset() {
	fProposals.clear();
  }

  /**
   * Returns the array of matching templates.
   */
  public IMTLCompletionProposal[] getResults() {
	return (IMTLCompletionProposal[]) fProposals.toArray(new IMTLCompletionProposal[fProposals.size()]);
  }

  /**
   * Inspects the context of the compilation unit around <code>completionPosition</code>
   * and feeds the collector with proposals.
   * @param viewer the text viewer
   * @param completionPosition the context position in the document of the text viewer
   * @param compilationUnit the compilation unit (may be <code>null</code>)
   */
  public void complete(ITextViewer viewer, int completionPosition)
  //,ICompilationUnit compilationUnit)
  //hrows JavaModelException
  {
	IDocument document = viewer.getDocument();

	if (!(fContextType instanceof CompilationUnitContextType))
	  return;

	Point selection = viewer.getSelectedRange();
	// remember selected text
	String selectedText = null;
	if (selection.y != 0) {
	  try {
		selectedText = document.get(selection.x, selection.y);
	  } catch (BadLocationException e) {
	  }
	}

	((CompilationUnitContextType) fContextType).setContextParameters(document, completionPosition, selection.y); //mpilationUnit);
	DocumentTemplateContext context = (DocumentTemplateContext) fContextType.createContext();
	int start = context.getStart();
	int end = context.getEnd();
	IRegion region = new Region(start, end - start);

	Template[] templates = Templates.getInstance().getTemplates();
	int maxProposals = MTLPlugin.MAX_PROPOSALS;

	for (int i = 0; i != templates.length; i++)
	  if (context.canEvaluate(templates[i])) {
		if (maxProposals-- < 0) {
		  return;
		}
		fProposals.add(new TemplateProposal(templates[i], context, region, viewer, MTLImages.get(MTLImages.IMG_OBJS_TEMPLATE)));
	  }
  }

}
