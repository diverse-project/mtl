/*
* $Id: MTLEditor.java,v 1.1 2004-07-30 14:10:07 sdzale Exp $
* Authors : ${user}
*
* Created on ${date}
* Copyright 2004 - INRIA - LGPL license
*/

package org.inria.mtl.editors;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jdt.internal.ui.actions.CompositeActionGroup;
import org.eclipse.jdt.internal.ui.text.JavaPairMatcher;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.GroupMarker;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.ITextOperationTarget;
import org.eclipse.jface.text.ITextViewer;
import org.eclipse.jface.text.ITextViewerExtension3;
import org.eclipse.jface.text.ITypedRegion;
import org.eclipse.jface.text.Region;
import org.eclipse.jface.text.source.IOverviewRuler;
import org.eclipse.jface.text.source.ISharedTextColors;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.jface.text.source.IVerticalRuler;
import org.eclipse.jface.text.source.OverviewRuler;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.swt.custom.BidiSegmentEvent;
import org.eclipse.swt.custom.BidiSegmentListener;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.actions.ActionContext;
import org.eclipse.ui.actions.ActionGroup;
import org.eclipse.ui.editors.text.DefaultEncodingSupport;
import org.eclipse.ui.editors.text.TextEditor;
import org.eclipse.ui.texteditor.AddTaskAction;
import org.eclipse.ui.texteditor.ContentAssistAction;
import org.eclipse.ui.texteditor.IAbstractTextEditorHelpContextIds;
import org.eclipse.ui.texteditor.IDocumentProvider;
import org.eclipse.ui.texteditor.IEditorStatusLine;
import org.eclipse.ui.texteditor.ITextEditorActionConstants;
import org.eclipse.ui.texteditor.ITextEditorActionDefinitionIds;
import org.eclipse.ui.texteditor.ResourceAction;
import org.eclipse.ui.texteditor.SourceViewerDecorationSupport;
import org.eclipse.ui.texteditor.TextOperationAction;
import org.eclipse.ui.views.contentoutline.IContentOutlinePage;
import org.inria.mtl.MTLPlugin;
import org.inria.mtl.editors.actions.GenerateActionGroup;
import org.inria.mtl.editors.actions.GotoMatchingBracketAction;
import org.inria.mtl.editors.actions.MTLEditorActionDefinitionIds;
import org.inria.mtl.editors.outline.MTLElement;
import org.inria.mtl.editors.outline.MTLOutlinerPage;
import org.inria.mtl.editors.utils.AnnotationType;
import org.inria.mtl.editors.utils.FastMTLPartitionScanner;
import org.inria.mtl.editors.utils.IContextMenuConstants;
import org.inria.mtl.editors.utils.MTLEditorEnvironment;
import org.inria.mtl.preferences.PreferencesConstants;

public class MTLEditor extends TextEditor implements ISelectionChangedListener{
	protected ColorManager colorManager = new ColorManager();
	protected MTLOutlinerPage outlinePage;
	protected MTLSourceViewerConfiguration configuration;
	
	protected CompositeActionGroup fActionGroups;
	protected CompositeActionGroup fContextMenuGroup;
	
	/** The standard action groups added to the menu */
		protected GenerateActionGroup fGenerateActionGroup;
		
	/** The editor's bracket matcher */
	protected JavaPairMatcher fBracketMatcher= new JavaPairMatcher(BRACKETS);
		
	/** Preference key for matching brackets */
	protected final static String MATCHING_BRACKETS=  org.eclipse.jdt.ui.PreferenceConstants.EDITOR_MATCHING_BRACKETS;
	/** Preference key for matching brackets color */
	protected final static String MATCHING_BRACKETS_COLOR=  org.eclipse.jdt.ui.PreferenceConstants.EDITOR_MATCHING_BRACKETS_COLOR;
	/** Preference key for highlighting current line */
	protected final static String CURRENT_LINE= PreferencesConstants.EDITOR_CURRENT_LINE;
	/** Preference key for highlight color of current line */
	protected final static String CURRENT_LINE_COLOR= PreferencesConstants.EDITOR_CURRENT_LINE_COLOR;
	/** Preference key for showing print marging ruler */
	protected final static String PRINT_MARGIN= PreferencesConstants.EDITOR_PRINT_MARGIN;
	/** Preference key for print margin ruler color */
	protected final static String PRINT_MARGIN_COLOR= org.eclipse.jdt.ui.PreferenceConstants.EDITOR_PRINT_MARGIN_COLOR;
	/** Preference key for print margin ruler column */
	protected final static String PRINT_MARGIN_COLUMN= PreferencesConstants.EDITOR_PRINT_MARGIN_COLUMN;
	/** Preference key for highlighting current line */	
	protected final static char[] BRACKETS= { '{', '}', '(', ')', '[', ']' };
  

	/**
	 * Constructeur for SampleEditor.
	 */
	public MTLEditor() {
		super();
		MTLEditorEnvironment	textTools= MTLPlugin.getDefault().getMTLEditorEnvironment();
		configuration=new MTLSourceViewerConfiguration(textTools, this);
		setSourceViewerConfiguration(configuration);
		setPreferenceStore(MTLPlugin.getDefault().getPreferenceStore());
		setDocumentProvider(new MTLDocumentProviders());
		
	}
	/**
	 * Methode declaré dans IEditorPart
	 */
	public void doSave(IProgressMonitor monitor)
	{
		super.doSave(monitor);

		if (outlinePage != null)
		{
			outlinePage.update();
		}
	}

	/**
	 * Laissée telle quelle
	 */	
	public void dispose() {
		if (outlinePage != null) {
			outlinePage.setInput(null);
		}
		colorManager.dispose();
		super.dispose();
	}
	/**
	 * Methode declaré dans IAdaptable
	 * @param key
	 * 
	 * @return
	 */
	
	public Object getAdapter(Class key)
	{
		if (key.equals(IContentOutlinePage.class))
		{
			IDocument document = getDocumentProvider().getDocument(getEditorInput());

			outlinePage = new MTLOutlinerPage(document);
			outlinePage.addSelectionChangedListener(this);
			return outlinePage;
		}

		return super.getAdapter(key);
	}
	
	/**
	 * @see org.eclipse.jface.viewers.ISelectionChangedListener#selectionChanged(SelectionChangedEvent)
	 */
	public void selectionChanged(SelectionChangedEvent event)
	{
		if (null != event)
		{
			if (event.getSelection() instanceof IStructuredSelection)
			{
				IStructuredSelection sel = (IStructuredSelection) event.getSelection();
				if (null != sel)
				{
					MTLElement fe = (MTLElement) sel.getFirstElement();
					if (null != fe)
					{
						selectAndReveal(fe.getStart(), fe.getLength());
					}
				}
			}
		}
	}

	/**
	 * Updates all content dependent actions.
	 * 
	 * This might be a hack: We're trapping this update to ensure that the 
	 * outline is always up to date.
	 */
	protected void updateContentDependentActions()
	{
		super.updateContentDependentActions();
		
		if(configuration.getAutomaticOutliningPreference()) {
			if (outlinePage != null)
			{
				outlinePage.update();
			}
		}
	}
	
	/**
		 *  Returns the standard action group of this editor.
		 */
		protected ActionGroup getActionGroup() {
			return fActionGroups;
		} 
	protected void createActions() {
	   super.createActions();

//	   ResourceAction resAction = new AddTaskAction(MTLEditorMessages.getResourceBundle(), "AddTask.", this); //$NON-NLS-1$
//	   resAction.setHelpContextId(IAbstractTextEditorHelpContextIds.ADD_TASK_ACTION);
//	   resAction.setActionDefinitionId(ITextEditorActionDefinitionIds.ADD_TASK);
//	   setAction(ITextEditorActionConstants.ADD_TASK, resAction);
	  

//	   resAction = new TextOperationAction(MTLEditorMessages.getResourceBundle(), "ShowJavaDoc.", this, ISourceViewer.INFORMATION, true); //$NON-NLS-1$
//	   resAction = new InformationDispatchAction(MTLEditorMessages.getResourceBundle(), "ShowJavaDoc.", (TextOperationAction) resAction); //$NON-NLS-1$
//	   resAction.setActionDefinitionId(MTLEditorActionDefinitionIds.SHOW_JAVADOC);
//	   setAction("ShowJavaDoc", resAction); //$NON-NLS-1$
//	  
	   Action action;

	   setAction( "ContentAssistTip", new TextOperationAction(  MTLEditorMessages.getResourceBundle(),
		   "ContentAssistTip.",
		   this,
		   ISourceViewer.CONTENTASSIST_CONTEXT_INFORMATION));
	   setAction("ContentAssistProposal", new TextOperationAction(MTLEditorMessages.getResourceBundle(),
			"ContentAssistProposal.", this, ISourceViewer.CONTENTASSIST_PROPOSALS));

	

//	   action = new ContentAssistAction(MTLEditorMessages.getResourceBundle(), "ContentAssistProposal.", this); //$NON-NLS-1$
//	   action.setActionDefinitionId(ITextEditorActionDefinitionIds.CONTENT_ASSIST_PROPOSALS);
//	   setAction("ContentAssistProposal", action); //$NON-NLS-1$
//	   markAsStateDependentAction("ContentAssistProposal", true); //$NON-NLS-1$
	
	   fEncodingSupport = new DefaultEncodingSupport();
	   fEncodingSupport.initialize(this);

	   action = new TextOperationAction(MTLEditorMessages.getResourceBundle(), "Comment.", this, ITextOperationTarget.PREFIX); //$NON-NLS-1$
	   action.setActionDefinitionId(MTLEditorActionDefinitionIds.COMMENT);
	   setAction("Comment", action); //$NON-NLS-1$
	   markAsStateDependentAction("Comment", true); //$NON-NLS-1$
	  

	   action = new TextOperationAction(MTLEditorMessages.getResourceBundle(), "Uncomment.", this, ITextOperationTarget.STRIP_PREFIX); //$NON-NLS-1$
	   action.setActionDefinitionId(MTLEditorActionDefinitionIds.UNCOMMENT);
	   setAction("Uncomment", action); //$NON-NLS-1$
	   markAsStateDependentAction("Uncomment", true); //$NON-NLS-1$
	  
	   action = new TextOperationAction(MTLEditorMessages.getResourceBundle(), "Format.", this, ISourceViewer.FORMAT); //$NON-NLS-1$
	   action.setActionDefinitionId(MTLEditorActionDefinitionIds.FORMAT);
	   setAction("Format", action); //$NON-NLS-1$
	   markAsStateDependentAction("Format", true); //$NON-NLS-1$
	   markAsSelectionDependentAction("Format", true); //$NON-NLS-1$		
	  
	   action = new GotoMatchingBracketAction(this);
	   
	   action.setActionDefinitionId(MTLEditorActionDefinitionIds.GOTO_MATCHING_BRACKET);
	   setAction(GotoMatchingBracketAction.GOTO_MATCHING_BRACKET, action);

	   fGenerateActionGroup = new GenerateActionGroup(this, ITextEditorActionConstants.GROUP_EDIT);

	   fActionGroups = new CompositeActionGroup(new ActionGroup[] { fGenerateActionGroup });

	   // We have to keep the context menu group separate to have better control over positioning
	   fContextMenuGroup = new CompositeActionGroup(new ActionGroup[] { fGenerateActionGroup });
	  
	 }

	/**
			 * Jumps to the matching bracket.
			 */
			public void gotoMatchingBracket() {
		
				ISourceViewer sourceViewer= getSourceViewer();
				IDocument document= sourceViewer.getDocument();
				if (document == null)
					return;
		
				IRegion selection= getSignedSelection(sourceViewer);

				int selectionLength= Math.abs(selection.getLength());
				if (selectionLength > 1) {
					setStatusLineErrorMessage(MTLEditorMessages.getString("GotoMatchingBracket.error.invalidSelection"));	//$NON-NLS-1$		
					sourceViewer.getTextWidget().getDisplay().beep();
					return;
				}

				// #26314
				int sourceCaretOffset= selection.getOffset() + selection.getLength();
				if (isSurroundedByBrackets(document, sourceCaretOffset))
					sourceCaretOffset -= selection.getLength();

				IRegion region= fBracketMatcher.match(document, sourceCaretOffset);
				if (region == null) {
					setStatusLineErrorMessage(MTLEditorMessages.getString("GotoMatchingBracket.error.noMatchingBracket"));	//$NON-NLS-1$		
					sourceViewer.getTextWidget().getDisplay().beep();
					return;		
				}
		
				int offset= region.getOffset();
				int length= region.getLength();
		
				if (length < 1)
					return;
			
				int anchor= fBracketMatcher.getAnchor();
				int targetOffset= (JavaPairMatcher.RIGHT == anchor) ? offset : offset + length - 1;
		
				boolean visible= false;
				if (sourceViewer instanceof ITextViewerExtension3) {
					ITextViewerExtension3 extension= (ITextViewerExtension3) sourceViewer;
					visible= (extension.modelOffset2WidgetOffset(targetOffset) > -1);
				} else {
					IRegion visibleRegion= sourceViewer.getVisibleRegion();
					visible= (targetOffset >= visibleRegion.getOffset() && targetOffset < visibleRegion.getOffset() + visibleRegion.getLength());
				}
		
				if (!visible) {
					setStatusLineErrorMessage(MTLEditorMessages.getString("GotoMatchingBracket.error.bracketOutsideSelectedElement"));	//$NON-NLS-1$		
					sourceViewer.getTextWidget().getDisplay().beep();
					return;
				}
		
				if (selection.getLength() < 0)
					targetOffset -= selection.getLength();
			
				sourceViewer.setSelectedRange(targetOffset, selection.getLength());
				sourceViewer.revealRange(targetOffset, selection.getLength());
			}
			
	/**
			 * Ses the given message as error message to this editor's status line.
			 * @param msg message to be set
			 */
			protected void setStatusLineErrorMessage(String msg) {
				IEditorStatusLine statusLine= (IEditorStatusLine) getAdapter(IEditorStatusLine.class);
				if (statusLine != null)
					statusLine.setMessage(true, msg, null);	
			}
	
			private static IRegion getSignedSelection(ITextViewer viewer) {

				StyledText text= viewer.getTextWidget();
				int caretOffset= text.getCaretOffset();
				Point selection= text.getSelection();
		
				// caret left
				int offset, length;
				if (caretOffset == selection.x) {
					offset= selection.y;
					length= selection.x - selection.y;			
			
				// caret right
				} else {
					offset= selection.x;
					length= selection.y - selection.x;			
				}
		
				return new Region(offset, length);
			}
	
			private static boolean isBracket(char character) {
				for (int i= 0; i != BRACKETS.length; ++i)
					if (character == BRACKETS[i])
						return true;
				return false;
			}

			private static boolean isSurroundedByBrackets(IDocument document, int offset) {
				if (offset == 0 || offset == document.getLength())
					return false;

				try {
					return
						isBracket(document.getChar(offset - 1)) &&
						isBracket(document.getChar(offset));
			
				} catch (BadLocationException e) {
					return false;	
				}
			}

	/** The <code>MTLEditor</code> implementation of this 
		 * <code>AbstractTextEditor</code> method adds any 
		 * MTLEditor specific entries.
		 */
		public void editorContextMenuAboutToShow(MenuManager menu) {
			super.editorContextMenuAboutToShow(menu);
			//addAction(menu, "ContentAssistProposal"); //$NON-NLS-1$
			menu.appendToGroup(ITextEditorActionConstants.GROUP_UNDO, new Separator(IContextMenuConstants.GROUP_OPEN));
			menu.insertAfter(IContextMenuConstants.GROUP_OPEN, new GroupMarker(IContextMenuConstants.GROUP_SHOW));

			ActionContext context = new ActionContext(getSelectionProvider().getSelection());
			fContextMenuGroup.setContext(context);
			fContextMenuGroup.fillContextMenu(menu);
			fContextMenuGroup.setContext(null);
 
		}

		/*
		 * @see AbstractTextEditor#affectsTextPresentation(PropertyChangeEvent)
		 */
		protected boolean affectsTextPresentation(PropertyChangeEvent event) {
			MTLEditorEnvironment textTools= MTLPlugin.getDefault().getMTLEditorEnvironment();
			return textTools.affectsBehavior(event);
		}
		
		protected final ISourceViewer createSourceViewer(Composite parent, IVerticalRuler verticalRuler, int styles) {
			
				ISharedTextColors sharedColors= MTLPlugin.getDefault().getMTLEditorEnvironment().getColorManager();
			
				fOverviewRuler= new OverviewRuler(fAnnotationAccess, VERTICAL_RULER_WIDTH, sharedColors);		
				fOverviewRuler.addHeaderAnnotationType(AnnotationType.WARNING);
				fOverviewRuler.addHeaderAnnotationType(AnnotationType.ERROR);
			
				ISourceViewer viewer= createMTLSourceViewer(parent, verticalRuler, fOverviewRuler, isOverviewRulerVisible(), styles);
			
				StyledText text= viewer.getTextWidget();
				text.addBidiSegmentListener(new  BidiSegmentListener() {
					public void lineGetSegments(BidiSegmentEvent event) {
						event.segments= getBidiLineSegments(event.lineOffset, event.lineText);
					}
				});
			
			//	JavaUIHelp.setHelp(this, text, IJavaHelpContextIds.JAVA_EDITOR);
					
				fSourceViewerDecorationSupport= new SourceViewerDecorationSupport(viewer, fOverviewRuler, fAnnotationAccess, sharedColors);
				configureSourceViewerDecorationSupport();
			
				return viewer;
			}
		
			/*
			 * @see AbstractTextEditor#createSourceViewer(Composite, IVerticalRuler, int)
			 */
			protected ISourceViewer createMTLSourceViewer(Composite parent, IVerticalRuler verticalRuler, IOverviewRuler overviewRuler, boolean isOverviewRulerVisible, int styles) {
					return new MTLSourceViewer(parent, verticalRuler, fOverviewRuler, isOverviewRulerVisible(), styles);
			}
		
			/**
			 * Returns a segmentation of the line of the given document appropriate for bidi rendering.
			 * The default implementation returns only the string literals of a java code line as segments.
			 * 
			 * @param document the document
			 * @param lineOffset the offset of the line
			 * @return the line's bidi segmentation
			 * @throws BadLocationException in case lineOffset is not valid in document
			 */
			public static int[] getBidiLineSegments(IDocument document, int lineOffset) throws BadLocationException {
				
				IRegion line= document.getLineInformationOfOffset(lineOffset);
				ITypedRegion[] linePartitioning= document.computePartitioning(lineOffset, line.getLength());
			
				List segmentation= new ArrayList();
				for (int i= 0; i < linePartitioning.length; i++) {
					if (FastMTLPartitionScanner.MTL_STRING.equals(linePartitioning[i].getType()))
						segmentation.add(linePartitioning[i]);
				}
			
			
				if (segmentation.size() == 0) 
					return null;
				
				int size= segmentation.size();
				int[] segments= new int[size * 2 + 1];
			
				int j= 0;
				for (int i= 0; i < size; i++) {
					ITypedRegion segment= (ITypedRegion) segmentation.get(i);
				
					if (i == 0)
						segments[j++]= 0;
					
					int offset= segment.getOffset() - lineOffset;
					if (offset > segments[j - 1])
						segments[j++]= offset;
					
					if (offset + segment.getLength() >= line.getLength())
						break;
					
					segments[j++]= offset + segment.getLength();
				}
			
				if (j < segments.length) {
					int[] result= new int[j];
					System.arraycopy(segments, 0, result, 0, j);
					segments= result;
				}
			
				return segments;
			}
			
			/**
			 * Returns a segmentation of the given line appropriate for bidi rendering. The default
			 * implementation returns only the string literals of a java code line as segments.
			 * 
			 * @param lineOffset the offset of the line
			 * @param line the content of the line
			 * @return the line's bidi segmentation
			 */
			protected int[] getBidiLineSegments(int widgetLineOffset, String line) {
				IDocumentProvider provider= getDocumentProvider();
				if (provider != null && line != null && line.length() > 0) {
					IDocument document= provider.getDocument(getEditorInput());
					if (document != null)
						try {
						
							int lineOffset;
						
							ISourceViewer sourceViewer= getSourceViewer();
							if (sourceViewer instanceof ITextViewerExtension3) {
								ITextViewerExtension3 extension= (ITextViewerExtension3) sourceViewer;
								lineOffset= extension.widgetOffset2ModelOffset(widgetLineOffset);
							} else {
								IRegion visible= sourceViewer.getVisibleRegion();
								lineOffset= visible.getOffset() + widgetLineOffset;
							}
						
							return getBidiLineSegments(document, lineOffset);
						
						} catch (BadLocationException x) {
							// ignore
						}
				}
				return null;
			}
		
			/*
			 * @see AbstractTextEditor#handleCursorPositionChanged()
			 */
			protected void handleCursorPositionChanged() {
				super.handleCursorPositionChanged();
			}
			
			protected void configureSourceViewerDecorationSupport() {
				
						fSourceViewerDecorationSupport.setCharacterPairMatcher(fBracketMatcher);
							
						fSourceViewerDecorationSupport.setCursorLinePainterPreferenceKeys(CURRENT_LINE, CURRENT_LINE_COLOR);
						fSourceViewerDecorationSupport.setMarginPainterPreferenceKeys(PRINT_MARGIN, PRINT_MARGIN_COLOR, PRINT_MARGIN_COLUMN);
						fSourceViewerDecorationSupport.setMatchingCharacterPainterPreferenceKeys(MATCHING_BRACKETS, MATCHING_BRACKETS_COLOR);		
					}
			/*
			 * @see AbstractTextEditor#handlePreferenceStoreChanged(PropertyChangeEvent)
			 */
	protected void handlePreferenceStoreChanged(PropertyChangeEvent event) {
			
		try {			

			ISourceViewer sourceViewer= getSourceViewer();
			if (sourceViewer == null)
				return;
					
			String property= event.getProperty();	
				
			if (PreferencesConstants.EDITOR_TAB_WIDTH.equals(property)) {
				Object value= event.getNewValue();
				if (value instanceof Integer) {
						sourceViewer.getTextWidget().setTabs(((Integer) value).intValue());
				} else if (value instanceof String) {
						sourceViewer.getTextWidget().setTabs(Integer.parseInt((String) value));
				}
					return;
					}
				
				
					if (fLineNumberRulerColumn != null &&
								(PREFERENCE_COLOR_BACKGROUND_SYSTEM_DEFAULT.equals(property)  ||
								PREFERENCE_COLOR_BACKGROUND.equals(property))) {
						
							initializeLineNumberRulerColumn(fLineNumberRulerColumn);
					}
					
					} finally {
					super.handlePreferenceStoreChanged(event);
				}
			}
		
		
			private boolean isJavaEditorHoverProperty(String property) {
				return	org.eclipse.jdt.ui.PreferenceConstants.EDITOR_TEXT_HOVER_MODIFIERS.equals(property);
			}

			/** The <code>GooEditor</code> implementation of this
			 * <code>AbstractTextEditor</code> method performs any extra
			 * revert behavior required by the Goo editor.
			 */
			public final void doRevertToSaved() {
				super.doRevertToSaved();
				if (outlinePage != null) {
					outlinePage.update();
				}
			}
			
			/** The <code>MTLEditor</code> implementation of this
			 * <code>AbstractTextEditor</code> method performs sets the
			 * input of the outline page after AbstractTextEditor has set input.
			 *
			 * @param input The input for the output page to review.
			 * @throws CoreException if fOutlinePage throws
			 */
//			public final void doSetInput(final IEditorInput input) throws CoreException {
//				super.doSetInput(input);
//				if (outlinePage != null) {
//					outlinePage.setInput(input);
//				}
//			}

				

}
