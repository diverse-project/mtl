/*
* $Id: AbstractProposal.java,v 1.2 2004-05-19 09:22:23 sdzale Exp $
* Authors : ${user}
*
* Created on ${date}
* Copyright 2004 - INRIA - LGPL license
*/ 
package org.inria.mtl.plugin.editors.completion;

import org.inria.mtl.plugin.editors.completion.template.TemplateMessages;
import org.inria.mtl.plugin.MTLPlugin;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.ITextViewer;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Shell;

/**
 * A MTL identifier proposal.
 */
public abstract class AbstractProposal implements IMTLCompletionProposal {
	protected IRegion fSelectedRegion; // initialized by apply()
	protected final ITextViewer fViewer;
	
	public AbstractProposal(ITextViewer viewer) {
		fViewer = viewer;
	}
	protected static String textToHTML(String string) {
	StringBuffer buffer = new StringBuffer(string.length());
	buffer.append("<pre>"); //$NON-NLS-1$

	for (int i = 0; i != string.length(); i++) {
	  char ch = string.charAt(i);

	  switch (ch) {
		case '&' :
		  buffer.append("&amp;"); //$NON-NLS-1$
		  break;

		case '<' :
		  buffer.append("&lt;"); //$NON-NLS-1$
		  break;

		case '>' :
		  buffer.append("&gt;"); //$NON-NLS-1$
		  break;

		case '\t' :
		  buffer.append("    "); //$NON-NLS-1$
		  break;

		case '\n' :
		  buffer.append("<br>"); //$NON-NLS-1$
		  break;

		default :
		  buffer.append(ch);
		  break;
	  }
	}

	buffer.append("</pre>"); //$NON-NLS-1$
	return buffer.toString();
  }

	/*
	 * @see ICompletionProposal#getSelection(IDocument)
	 */
	public Point getSelection(IDocument document) {
		return new Point(fSelectedRegion.getOffset(), fSelectedRegion.getLength());
	}

	protected void handleException(CoreException e) {
		Shell shell = fViewer.getTextWidget().getShell();
		MTLPlugin.log(e);
		//		ExceptionHandler.handle(e, shell, ObfuscatorMessages.getString("TemplateEvaluator.error.title"), null); //$NON-NLS-1$
	}

	protected void openErrorDialog(BadLocationException e) {
		Shell shell = fViewer.getTextWidget().getShell();
		MessageDialog.openError(shell, TemplateMessages.getString("TemplateEvaluator.error.title"), e.getMessage()); //$NON-NLS-1$
	}
}