package org.inria.mtl.editors;

import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.source.IAnnotationHover;
import org.eclipse.jface.text.source.ISourceViewer;

import org.inria.mtl.MTLPlugin;

/**
 * The MTLAnnotationHover provides the hover support for MTL editors.
 */
public class MTLAnnotationHover implements IAnnotationHover {

	/**
	 * @see IAnnotationHover#getHoverInfo
	 */
	public final String getHoverInfo(final ISourceViewer sourceViewer, final int lineNumber) {
		IDocument document = sourceViewer.getDocument();

		try {
			IRegion info = document.getLineInformation(lineNumber);
			return document.get(info.getOffset(), info.getLength());
		} catch (BadLocationException x) {
			MTLPlugin.log(x);
		}

		return null;
	}
}
