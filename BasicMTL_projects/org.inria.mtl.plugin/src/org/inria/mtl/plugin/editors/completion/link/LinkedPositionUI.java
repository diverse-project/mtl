/*******************************************************************************
 * Copyright (c) 2000, 2003 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.inria.mtl.plugin.editors.completion.link;

import java.lang.reflect.InvocationTargetException;

import org.inria.mtl.plugin.preferences.PreferenceConstants;
import org.inria.mtl.plugin.MTLPlugin;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.PreferenceConverter;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.BadPositionCategoryException;
import org.eclipse.jface.text.DefaultPositionUpdater;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IPositionUpdater;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.ITextInputListener;
import org.eclipse.jface.text.ITextListener;
import org.eclipse.jface.text.ITextViewer;
import org.eclipse.jface.text.ITextViewerExtension;
import org.eclipse.jface.text.ITextViewerExtension2;
import org.eclipse.jface.text.ITextViewerExtension3;
import org.eclipse.jface.text.Position;
import org.eclipse.jface.text.Region;
import org.eclipse.jface.text.TextEvent;
import org.eclipse.jface.util.Assert;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.custom.VerifyKeyListener;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.events.ShellEvent;
import org.eclipse.swt.events.ShellListener;
import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.events.VerifyListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

/**
 * A user interface for <code>LinkedPositionManager</code>, using <code>ITextViewer</code>.
 */
public class LinkedPositionUI implements LinkedPositionListener,
	ITextInputListener, ITextListener, ModifyListener, VerifyListener, VerifyKeyListener, PaintListener, IPropertyChangeListener, ShellListener {

	/**
	 * A listener for notification when the user cancelled the edit operation.
	 */
	public interface ExitListener {
		void exit(boolean accept);
	}
	
	public static class ExitFlags {
		public int flags;	
		public boolean doit;
		public ExitFlags(int flags, boolean doit) {
			this.flags= flags;
			this.doit= doit;
		}						
	}
	
	public interface ExitPolicy {
		ExitFlags doExit(LinkedPositionManager manager, VerifyEvent event, int offset, int length);
	}
	
	// leave flags
	private static final int UNINSTALL= 1;			// uninstall linked position manager
	public static final int COMMIT= 2;				// commit changes
	private static final int DOCUMENT_CHANGED= 4;	// document has changed
	public static final int UPDATE_CARET= 8;		// update caret

	private static final String CARET_POSITION= "LinkedPositionUI.caret.position"; //$NON-NLS-1$
	private static final IPositionUpdater fgUpdater= new DefaultPositionUpdater(CARET_POSITION);
	private static final IPreferenceStore fgStore= MTLPlugin.getDefault().getPreferenceStore();
	
	private final ITextViewer fViewer;
	private final LinkedPositionManager fManager;	
	private Color fFrameColor;

	private int fFinalCaretOffset= -1; // no final caret offset

	private Position fFramePosition;
	private int fInitialOffset= -1;
	private int fCaretOffset;
	
	private ExitPolicy fExitPolicy;
	private ExitListener fExitListener;
	
	private boolean fNeedRedraw;
	
	private String fContentType;

	/**
	 * Creates a user interface for <code>LinkedPositionManager</code>.
	 * 
	 * @param viewer  the text viewer.
	 * @param manager the <code>LinkedPositionManager</code> managing a <code>IDocument</code> of the <code>ITextViewer</code>.
	 */
	public LinkedPositionUI(ITextViewer viewer, LinkedPositionManager manager) {
		Assert.isNotNull(viewer);
		Assert.isNotNull(manager);
		
		fViewer= viewer;
		fManager= manager;
		
		fManager.setLinkedPositionListener(this);

		initializeHighlightColor(viewer);
	}

	/*
	 * @see IPropertyChangeListener#propertyChange(PropertyChangeEvent)
	 */
	public void propertyChange(PropertyChangeEvent event) {
		if (event.getProperty().equals(PreferenceConstants.EDITOR_LINKED_POSITION_COLOR)) {
			initializeHighlightColor(fViewer);
			redrawRegion();
		}
	}

	private void initializeHighlightColor(ITextViewer viewer) {

		if (fFrameColor != null)
			fFrameColor.dispose();

		StyledText text= viewer.getTextWidget();
		if (text != null) {
			Display display= text.getDisplay();
			fFrameColor= createColor(fgStore, PreferenceConstants.EDITOR_LINKED_POSITION_COLOR, display);
		}
	}

	/**
	 * Creates a color from the information stored in the given preference store.
	 * Returns <code>null</code> if there is no such information available.
	 */
	private Color createColor(IPreferenceStore store, String key, Display display) {
	
		RGB rgb= null;		
		
		if (store.contains(key)) {
			
			if (store.isDefault(key))
				rgb= PreferenceConverter.getDefaultColor(store, key);
			else
				rgb= PreferenceConverter.getColor(store, key);
		
			if (rgb != null)
				return new Color(display, rgb);
		}
		
		return null;
	}

	/**
	 * Sets the initial offset.
	 * @param offset
	 */
	public void setInitialOffset(int offset) {
		fInitialOffset= offset;	
	}
	
	/**
	 * Sets the final position of the caret when the linked mode is exited
	 * successfully by leaving the last linked position using TAB.
	 */
	public void setFinalCaretOffset(int offset) {
		fFinalCaretOffset= offset;	
	}

	/**
	 * Sets a <code>CancelListener</code> which is notified if the linked mode
	 * is exited unsuccessfully by hitting ESC.
	 */
	public void setCancelListener(ExitListener listener) {
		fExitListener= listener;
	}

	/**
	 * Sets an <code>ExitPolicy</code> which decides when and how
	 * the linked mode is exited.
	 */
	public void setExitPolicy(ExitPolicy policy) {
		fExitPolicy= policy;
	}

	/*
	 * @see LinkedPositionManager.LinkedPositionListener#setCurrentPositions(Position, int)
	 */
	public void setCurrentPosition(Position position, int caretOffset) {
		if (!fFramePosition.equals(position)) {
			fNeedRedraw= true;
			fFramePosition= position;
		}

		fCaretOffset= caretOffset;
	}

	/**
	 * Enters the linked mode. The linked mode can be left by calling
	 * <code>exit</code>.
	 * 
	 * @see #exit(boolean)
	 */
	public void enter() {

		// track final caret
		IDocument document= fViewer.getDocument();
		document.addPositionCategory(CARET_POSITION);
		document.addPositionUpdater(fgUpdater);

		try {
			if (fFinalCaretOffset != -1)
				document.addPosition(CARET_POSITION, new Position(fFinalCaretOffset));
		} catch (BadLocationException e) {
			handleException(fViewer.getTextWidget().getShell(), e);

		} catch (BadPositionCategoryException e) {
	  MTLPlugin.log(e);
			Assert.isTrue(false);
		}

		fViewer.addTextInputListener(this);
		fViewer.addTextListener(this);
				
		ITextViewerExtension extension= (ITextViewerExtension) fViewer;
		extension.prependVerifyKeyListener(this);

		StyledText text= fViewer.getTextWidget();			
		text.addVerifyListener(this);
		text.addModifyListener(this);
		text.addPaintListener(this);
		text.showSelection();

		Shell shell= text.getShell();
		shell.addShellListener(this);

		fFramePosition= (fInitialOffset == -1) ? fManager.getFirstPosition() : fManager.getPosition(fInitialOffset);
		if (fFramePosition == null) {
			leave(UNINSTALL | COMMIT | UPDATE_CARET);
			return;
		}

		fgStore.addPropertyChangeListener(this);

		try {
			fContentType= document.getContentType(fFramePosition.offset);
			if (fViewer instanceof ITextViewerExtension2) {
				((ITextViewerExtension2) fViewer).prependAutoEditStrategy(fManager, fContentType);
			} else {
				Assert.isTrue(false);
			}

		} catch (BadLocationException e) {
			handleException(fViewer.getTextWidget().getShell(), e);
		}
	}

	/*
	 * @see LinkedPositionManager.LinkedPositionListener#exit(boolean)
	 */
	public void exit(boolean success) {
		// no UNINSTALL since manager has already uninstalled itself
		leave((success ? COMMIT : 0) | UPDATE_CARET);
	}

	/**
	 * Returns the cursor selection, after having entered the linked mode.
	 * <code>enter()</code> must be called prior to a call to this method.
	 */
	public IRegion getSelectedRegion() {
		if (fFramePosition == null)
			return new Region(fFinalCaretOffset, 0);
		else
			return new Region(fFramePosition.getOffset(), fFramePosition.getLength());
	}
	
	private void leave(int flags) {

		fInitialOffset= -1;
		
		if ((flags & UNINSTALL) != 0)
			fManager.uninstall((flags & COMMIT) != 0);

		fgStore.removePropertyChangeListener(this);
		
		if (fFrameColor != null) {
			fFrameColor.dispose();
			fFrameColor= null;
		}			
		
		StyledText text= fViewer.getTextWidget();	
		text.removePaintListener(this);
		text.removeModifyListener(this);
		text.removeVerifyListener(this);

		Shell shell= text.getShell();
		shell.removeShellListener(this);

		ITextViewerExtension extension= (ITextViewerExtension) fViewer;
		extension.removeVerifyKeyListener(this);

		if (fViewer instanceof ITextViewerExtension2 && fContentType != null)
			((ITextViewerExtension2) fViewer).removeAutoEditStrategy(fManager, fContentType);
		fContentType= null;

		fViewer.removeTextListener(this);
		fViewer.removeTextInputListener(this);
		
		try {
			IDocument document= fViewer.getDocument();

			if (((flags & COMMIT) != 0) &&
				((flags & DOCUMENT_CHANGED) == 0) &&
				((flags & UPDATE_CARET) != 0))
			{
				Position[] positions= document.getPositions(CARET_POSITION);
				if ((positions != null) && (positions.length != 0)) {
					
					if (fViewer instanceof ITextViewerExtension3) {
						ITextViewerExtension3 extension3= (ITextViewerExtension3) fViewer;
						int widgetOffset= extension3.modelOffset2WidgetOffset(positions[0].getOffset());
						if (widgetOffset >= 0)
							text.setSelection(widgetOffset, widgetOffset);
							
					} else {
						IRegion region= fViewer.getVisibleRegion();
						int offset= positions[0].getOffset() - region.getOffset();
						if ((offset >= 0) && (offset <= region.getLength()))
							text.setSelection(offset, offset);
					}
				}
			}

			document.removePositionUpdater(fgUpdater);
			document.removePositionCategory(CARET_POSITION);
			
			if (fExitListener != null)
				fExitListener.exit(
					((flags & COMMIT) != 0) ||
					((flags & DOCUMENT_CHANGED) != 0));

		} catch (BadPositionCategoryException e) {
	  MTLPlugin.log(e);
			Assert.isTrue(false);
		}

		if ((flags & DOCUMENT_CHANGED) == 0)
			text.redraw();
	}

	private void next() {
		redrawRegion();
		
		fFramePosition= fManager.getNextPosition(fFramePosition.getOffset());
		if (fFramePosition == null) {
			leave(UNINSTALL | COMMIT | UPDATE_CARET);
		} else {
			selectRegion();
			redrawRegion();
		}
	}
	
	private void previous() {
		redrawRegion();
		
		Position position= fManager.getPreviousPosition(fFramePosition.getOffset());
		if (position == null) {
			fViewer.getTextWidget().getDisplay().beep();
		} else {
			fFramePosition= position;
			selectRegion();
			redrawRegion();
		}
	}

	/*
	 * @see VerifyKeyListener#verifyKey(VerifyEvent)
	 */
	public void verifyKey(VerifyEvent event) {

		if (!event.doit)
			return;
		
		Point selection= fViewer.getSelectedRange();
		int offset= selection.x;
		int length= selection.y;
		
		ExitFlags exitFlags= fExitPolicy == null ? null : fExitPolicy.doExit(fManager, event, offset, length);
		if (exitFlags != null) {
			leave(UNINSTALL | exitFlags.flags);
			event.doit= exitFlags.doit;
			return;
		}
		
		switch (event.character) {
		// [SHIFT-]TAB = hop between edit boxes
		case 0x09:
			{
				// if tab was treated as a document change, would it exceed variable range?
				if (!LinkedPositionManager.includes(fFramePosition, offset, length)) {
					leave(UNINSTALL | COMMIT);
					return;
				}
			}
		
			if (event.stateMask == SWT.SHIFT)
				previous();
			else 
				next();			
			
			event.doit= false;
			break;

		// ENTER
		case 0x0D:
			leave(UNINSTALL | COMMIT | UPDATE_CARET);
			event.doit= false;
			break;

		// ESC
		case 0x1B:
			leave(UNINSTALL | COMMIT);
			event.doit= false;
			break;
		}
	}

	/*
	 * @see VerifyListener#verifyText(VerifyEvent)
	 */
	public void verifyText(VerifyEvent event) {
		if (!event.doit)
			return;


		int offset= 0;
		int length= 0;
		
		if (fViewer instanceof ITextViewerExtension3) {
			ITextViewerExtension3 extension= (ITextViewerExtension3) fViewer;
			IRegion modelRange= extension.widgetRange2ModelRange(new Region(event.start, event.end - event.start));
			if (modelRange == null)
				return;
				
			offset= modelRange.getOffset();
			length= modelRange.getLength();
				
		} else {
			IRegion visibleRegion= fViewer.getVisibleRegion();
			offset= event.start + visibleRegion.getOffset();
			length= event.end - event.start;
		}
		
		// allow changes only within linked positions when coming through UI
		if (!fManager.anyPositionIncludes(offset, length))
			leave(UNINSTALL | COMMIT);
	}

	/*
	 * @see PaintListener#paintControl(PaintEvent)
	 */
	public void paintControl(PaintEvent event) {	
		if (fFramePosition == null)
			return;
			
		IRegion widgetRange= asWidgetRange(fFramePosition);
		if (widgetRange == null) {
			leave(UNINSTALL | COMMIT | DOCUMENT_CHANGED);
			return;
		}

		int offset= widgetRange.getOffset();
		int length= widgetRange.getLength();

		StyledText text= fViewer.getTextWidget();
		
		// support for bidi
		Point minLocation= getMinimumLocation(text, offset, length);
		Point maxLocation= getMaximumLocation(text, offset, length);

		int x1= minLocation.x;
		int x2= minLocation.x + maxLocation.x - minLocation.x - 1;
		int y= minLocation.y + text.getLineHeight() - 1;
		
		GC gc= event.gc;
		gc.setForeground(fFrameColor);
		gc.drawLine(x1, y, x2, y);
	}
	
	protected IRegion asWidgetRange(Position position) {
		if (fViewer instanceof ITextViewerExtension3) {
			
			ITextViewerExtension3 extension= (ITextViewerExtension3) fViewer;
			return extension.modelRange2WidgetRange(new Region(position.getOffset(), position.getLength()));
		
		} else {
			
			IRegion region= fViewer.getVisibleRegion();
			if (includes(region, position))
				return new Region(position.getOffset() -  region.getOffset(), position.getLength());
		}
		
		return null;
	}

	private static Point getMinimumLocation(StyledText text, int offset, int length) {
		Point minLocation= new Point(Integer.MAX_VALUE, Integer.MAX_VALUE);

		for (int i= 0; i <= length; i++) {
			Point location= text.getLocationAtOffset(offset + i);
			
			if (location.x < minLocation.x)
				minLocation.x= location.x;			
			if (location.y < minLocation.y)
				minLocation.y= location.y;			
		}	
		
		return minLocation;
	}

	private static Point getMaximumLocation(StyledText text, int offset, int length) {
		Point maxLocation= new Point(Integer.MIN_VALUE, Integer.MIN_VALUE);

		for (int i= 0; i <= length; i++) {
			Point location= text.getLocationAtOffset(offset + i);
			
			if (location.x > maxLocation.x)
				maxLocation.x= location.x;			
			if (location.y > maxLocation.y)
				maxLocation.y= location.y;			
		}	
		
		return maxLocation;
	}

	private void redrawRegion() {
		IRegion widgetRange= asWidgetRange(fFramePosition);
		if (widgetRange == null) {
			leave(UNINSTALL | COMMIT | DOCUMENT_CHANGED);
			return;		    
		}
		
		StyledText text= fViewer.getTextWidget();
		if (text != null && !text.isDisposed())	
			text.redrawRange(widgetRange.getOffset(), widgetRange.getLength(), true);
	}

	private void selectRegion() {
		
		IRegion widgetRange= asWidgetRange(fFramePosition);
		if (widgetRange == null) {
			leave(UNINSTALL | COMMIT | DOCUMENT_CHANGED);
			return;   
		}

		StyledText text= fViewer.getTextWidget();
		if (text != null && !text.isDisposed()) {
			int start= widgetRange.getOffset();
			int end= widgetRange.getLength() + start;
			text.setSelection(start, end);
		}
	}
	
	private void updateCaret() {
		
		IRegion widgetRange= asWidgetRange(fFramePosition);
		if (widgetRange == null) {
			leave(UNINSTALL | COMMIT | DOCUMENT_CHANGED);
			return;   
		}
		
		int offset= widgetRange.getOffset() + fCaretOffset;
		StyledText text= fViewer.getTextWidget();
		if (text != null && !text.isDisposed())
			text.setCaretOffset(offset);
	}

	/*
	 * @see ModifyListener#modifyText(ModifyEvent)
	 */	 
	public void modifyText(ModifyEvent e) {
		// reposition caret after StyledText
		redrawRegion();
		updateCaret();
	}

	private static void handleException(Shell shell, Exception e) {
		String title= LinkedPositionMessages.getString("LinkedPositionUI.error.title"); //$NON-NLS-1$
		if (e instanceof CoreException)
			ExceptionHandler.handle((CoreException)e, shell, title, null);
		else if (e instanceof InvocationTargetException)
			ExceptionHandler.handle((InvocationTargetException)e, shell, title, null);
		else {
			MessageDialog.openError(shell, title, e.getMessage());
	  MTLPlugin.log(e);
		}
	}

	/*
	 * @see ITextInputListener#inputDocumentAboutToBeChanged(IDocument, IDocument)
	 */
	public void inputDocumentAboutToBeChanged(IDocument oldInput, IDocument newInput) {
		// 5326: leave linked mode on document change
		int flags= UNINSTALL | COMMIT | (oldInput.equals(newInput) ? 0 : DOCUMENT_CHANGED);
		leave(flags);
	}

	/*
	 * @see ITextInputListener#inputDocumentChanged(IDocument, IDocument)
	 */
	public void inputDocumentChanged(IDocument oldInput, IDocument newInput) {
	}

	private static boolean includes(IRegion region, Position position) {
		return
			position.getOffset() >= region.getOffset() &&
			position.getOffset() + position.getLength() <= region.getOffset() + region.getLength();
	}

	/*
	 * @see org.eclipse.jface.text.ITextListener#textChanged(TextEvent)
	 */
	public void textChanged(TextEvent event) {
		if (!fNeedRedraw)
			return;
			
		redrawRegion();
		fNeedRedraw= false;
	}

	/*
	 * @see org.eclipse.swt.events.ShellListener#shellActivated(org.eclipse.swt.events.ShellEvent)
	 */
	public void shellActivated(ShellEvent event) {
	}

	/*
	 * @see org.eclipse.swt.events.ShellListener#shellClosed(org.eclipse.swt.events.ShellEvent)
	 */
	public void shellClosed(ShellEvent event) {
		leave(UNINSTALL | COMMIT | DOCUMENT_CHANGED);
	}

	/*
	 * @see org.eclipse.swt.events.ShellListener#shellDeactivated(org.eclipse.swt.events.ShellEvent)
	 */
	public void shellDeactivated(ShellEvent event) {
		leave(UNINSTALL | COMMIT | DOCUMENT_CHANGED);
	}

	/*
	 * @see org.eclipse.swt.events.ShellListener#shellDeiconified(org.eclipse.swt.events.ShellEvent)
	 */
	public void shellDeiconified(ShellEvent event) {
	}

	/*
	 * @see org.eclipse.swt.events.ShellListener#shellIconified(org.eclipse.swt.events.ShellEvent)
	 */
	public void shellIconified(ShellEvent event) {
		leave(UNINSTALL | COMMIT | DOCUMENT_CHANGED);
	}

}
