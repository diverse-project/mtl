package org.inria.mtl.editors;

import org.eclipse.swt.graphics.Point;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.ITextHover;
import org.eclipse.jface.text.ITextViewer;
import org.eclipse.jface.text.Region;

import org.inria.mtl.MTLPlugin;

/**
 * Implementation for an <code>ITextHover</code> which hovers over MTL code.
 */
public class MTLTextHover implements ITextHover {

	/**
	 * @see ITextHover#getHoverInfo
	 */
	public final String getHoverInfo(final ITextViewer textViewer, final IRegion hoverRegion) {
		if (hoverRegion != null) {
			try {
				return textViewer.getDocument().get(hoverRegion.getOffset(), hoverRegion.getLength());
			} catch (BadLocationException x) {
				MTLPlugin.log(x);
			}
		}
		return null;
	}

	/**
	 * @see ITextHover#getHoverRegion
	 */
	public final IRegion getHoverRegion(final ITextViewer textViewer, final int offset) {
		Point selection = textViewer.getSelectedRange();
		if (selection.y > 0 && selection.x <= offset && offset <= selection.x + selection.y) {
			return new Region(selection.x, selection.y);
		}
		return null;
	}
}
