/*
* $Id: MTLUnitEditor.java,v 1.2 2004-08-26 12:40:40 sdzale Exp $
* Authors : ${user}
*
* Created on ${date}
* Copyright 2004 - INRIA - LGPL license
*/ 
package org.inria.mtl.editors;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Preferences;
import org.eclipse.jdt.core.formatter.DefaultCodeFormatterConstants;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.PreferenceConverter;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.DocumentCommand;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.ILineTracker;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.ITextSelection;
import org.eclipse.jface.text.ITextViewerExtension;
import org.eclipse.jface.text.ITypedRegion;
import org.eclipse.jface.text.IWidgetTokenKeeper;
import org.eclipse.jface.text.contentassist.ContentAssistant;
import org.eclipse.jface.text.contentassist.IContentAssistant;
import org.eclipse.jface.text.source.IOverviewRuler;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.jface.text.source.IVerticalRuler;
import org.eclipse.jface.text.source.SourceViewer;
import org.eclipse.jface.text.source.SourceViewerConfiguration;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.swt.custom.VerifyKeyListener;
import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.actions.ActionContext;
import org.eclipse.ui.editors.text.IStorageDocumentProvider;
import org.eclipse.ui.help.WorkbenchHelp;
import org.eclipse.ui.texteditor.IDocumentProvider;
import org.inria.mtl.MTLPlugin;
import org.inria.mtl.editors.completion.ContentAssistPreferences;
import org.inria.mtl.editors.completion.link.LinkedPositionManager;
import org.inria.mtl.editors.completion.link.LinkedPositionUI;
import org.inria.mtl.editors.completion.link.LinkedPositionUI.ExitFlags;
import org.inria.mtl.editors.utils.BracketPainter;
import org.inria.mtl.editors.utils.LinePainter;
import org.inria.mtl.editors.utils.MTLEditorEnvironment;
import org.inria.mtl.editors.utils.MTLPairMatcher;
import org.inria.mtl.editors.utils.PrintMarginPainter;
import org.inria.mtl.preferences.PreferencesConstants;

/**
 * MTL specific text editor.
 */
public class MTLUnitEditor extends MTLEditor {

  interface ITextConverter {
	void customizeDocumentCommand(IDocument document, DocumentCommand command);
  };


  class AdaptedSourceViewer extends SourceViewer {

	private List fTextConverters;
	private boolean fIgnoreTextConverters = false;
	

	public AdaptedSourceViewer(
	  Composite parent,
	  IVerticalRuler verticalRuler,
	  IOverviewRuler overviewRuler,
	  boolean showAnnotationsOverview,
	  int styles) {
	  super(parent, verticalRuler, overviewRuler, showAnnotationsOverview, styles);
	}

	public IContentAssistant getContentAssistant() {
	  return fContentAssistant;
	}

	/*
	 * @see ITextOperationTarget#doOperation(int)
	 */
	public void doOperation(int operation) {

	  if (getTextWidget() == null)
		return;

	  switch (operation) {
		case CONTENTASSIST_PROPOSALS :
		  String msg = fContentAssistant.showPossibleCompletions();
		  setStatusLineErrorMessage(msg);
		  return;
		case UNDO :
		  fIgnoreTextConverters = true;
		  break;
		case REDO :
		  fIgnoreTextConverters = true;
		  break;
	  }

	  super.doOperation(operation);
	}

	/*
	 * @see ITextOperationTarget#canDoOperation(int)
	 */
	public boolean canDoOperation(int operation) {
	  return super.canDoOperation(operation);
	}

	/*
	 * @see TextViewer#handleDispose()
	 */
	protected void handleDispose() {
	  super.handleDispose();
	}

	public void insertTextConverter(ITextConverter textConverter, int index) {
	  throw new UnsupportedOperationException();
	}

	public void addTextConverter(ITextConverter textConverter) {
	  if (fTextConverters == null) {
		fTextConverters = new ArrayList(1);
		fTextConverters.add(textConverter);
	  } else if (!fTextConverters.contains(textConverter))
		fTextConverters.add(textConverter);
	}

	public void removeTextConverter(ITextConverter textConverter) {
	  if (fTextConverters != null) {
		fTextConverters.remove(textConverter);
		if (fTextConverters.size() == 0)
		  fTextConverters = null;
	  }
	}

	/*
	 * @see TextViewer#customizeDocumentCommand(DocumentCommand)
	 */
	protected void customizeDocumentCommand(DocumentCommand command) {
	  super.customizeDocumentCommand(command);
	  if (!fIgnoreTextConverters && fTextConverters != null) {
		for (Iterator e = fTextConverters.iterator(); e.hasNext();)
		   ((ITextConverter) e.next()).customizeDocumentCommand(getDocument(), command);
	  }
	  fIgnoreTextConverters = false;
	}

	public void updateIndentationPrefixes() {
	  SourceViewerConfiguration configuration = getSourceViewerConfiguration();
	  String[] types = configuration.getConfiguredContentTypes(this);
	  for (int i = 0; i < types.length; i++) {
		String[] prefixes = configuration.getIndentPrefixes(this, types[i]);
		if (prefixes != null && prefixes.length > 0)
		  setIndentPrefixes(prefixes, types[i]);
	  }
	}

	/*
	 * @see IWidgetTokenOwner#requestWidgetToken(IWidgetTokenKeeper)
	 */
	public boolean requestWidgetToken(IWidgetTokenKeeper requester) {
	  if (WorkbenchHelp.isContextHelpDisplayed())
		return false;
	  return super.requestWidgetToken(requester);
	}

	/*
	 * @see org.eclipse.jface.text.source.ISourceViewer#configure(org.eclipse.jface.text.source.SourceViewerConfiguration)
	 */
	public void configure(SourceViewerConfiguration configuration) {
	  super.configure(configuration);
	}
  };
  static class TabConverter implements ITextConverter {

	private int fTabRatio;
	private ILineTracker fLineTracker;

	public TabConverter() {
	}

	public void setNumberOfSpacesPerTab(int ratio) {
	  fTabRatio = ratio;
	}

	public void setLineTracker(ILineTracker lineTracker) {
	  fLineTracker = lineTracker;
	}

	private int insertTabString(StringBuffer buffer, int offsetInLine) {

	  if (fTabRatio == 0)
		return 0;

	  int remainder = offsetInLine % fTabRatio;
	  remainder = fTabRatio - remainder;
	  for (int i = 0; i < remainder; i++)
		buffer.append(' ');
	  return remainder;
	}

	public void customizeDocumentCommand(IDocument document, DocumentCommand command) {
	  String text = command.text;
	  if (text == null)
		return;

	  int index = text.indexOf('\t');
	  if (index > -1) {

		StringBuffer buffer = new StringBuffer();

		fLineTracker.set(command.text);
		int lines = fLineTracker.getNumberOfLines();

		try {

		  for (int i = 0; i < lines; i++) {

			int offset = fLineTracker.getLineOffset(i);
			int endOffset = offset + fLineTracker.getLineLength(i);
			String line = text.substring(offset, endOffset);

			int position = 0;
			if (i == 0) {
			  IRegion firstLine = document.getLineInformationOfOffset(command.offset);
			  position = command.offset - firstLine.getOffset();
			}

			int length = line.length();
			for (int j = 0; j < length; j++) {
			  char c = line.charAt(j);
			  if (c == '\t') {
				position += insertTabString(buffer, position);
			  } else {
				buffer.append(c);
				++position;
			  }
			}

		  }

		  command.text = buffer.toString();

		} catch (BadLocationException x) {
		}
	  }
	}
  };

  private static class ExitPolicy implements LinkedPositionUI.ExitPolicy {

	final char fExitCharacter;

	public ExitPolicy(char exitCharacter) {
	  fExitCharacter = exitCharacter;
	}

	/*
	 * @see org.phpeclipse.phpdt.internal.ui.text.link.LinkedPositionUI.ExitPolicy#doExit(org.phpeclipse.phpdt.internal.ui.text.link.LinkedPositionManager, org.eclipse.swt.events.VerifyEvent, int, int)
	 */
	public ExitFlags doExit(LinkedPositionManager manager, VerifyEvent event, int offset, int length) {

	  if (event.character == fExitCharacter) {
		if (manager.anyPositionIncludes(offset, length))
		  return new ExitFlags(LinkedPositionUI.COMMIT | LinkedPositionUI.UPDATE_CARET, false);
		else
		  return new ExitFlags(LinkedPositionUI.COMMIT, true);
	  }

	  switch (event.character) {
		case '\b' :
		  if (manager.getFirstPosition().length == 0)
			return new ExitFlags(0, false);
		  else
			return null;

		case '\n' :
		case '\r' :
		  return new ExitFlags(LinkedPositionUI.COMMIT, true);

		default :
		  return null;
	  }
	}

  }

  
  private class BracketInserter implements VerifyKeyListener, LinkedPositionUI.ExitListener {

	private boolean fCloseBracketsMTL = true;
	private boolean fCloseStringsMTL = true;
	private boolean fCloseBracketsHTML = true;
	private boolean fCloseStringsHTML = true;

	private int fOffset;
	private int fLength;

	public void setCloseBracketsMTLEnabled(boolean enabled) {
	  fCloseBracketsMTL = enabled;
	}

	public void setCloseStringsMTLEnabled(boolean enabled) {
	  fCloseStringsMTL = enabled;
	}

	public void setCloseBracketsHTMLEnabled(boolean enabled) {
	  fCloseBracketsHTML = enabled;
	}

	public void setCloseStringsHTMLEnabled(boolean enabled) {
	  fCloseStringsHTML = enabled;
	}

	private boolean hasIdentifierToTheRight(IDocument document, int offset) {
	  try {
		int end = offset;
		IRegion endLine = document.getLineInformationOfOffset(end);
		int maxEnd = endLine.getOffset() + endLine.getLength();
		while (end != maxEnd && Character.isWhitespace(document.getChar(end)))
		  ++end;

		return end != maxEnd /*&& Scanner.isPHPIdentifierPart(document.getChar(end))*/;

	  } catch (BadLocationException e) {
		// be conservative
		return true;
	  }
	}

	private boolean hasIdentifierToTheLeft(IDocument document, int offset) {
	  try {
		int start = offset;
		IRegion startLine = document.getLineInformationOfOffset(start);
		int minStart = startLine.getOffset();
		while (start != minStart && Character.isWhitespace(document.getChar(start - 1)))
		  --start;

		return start != minStart /*&& Scanner.isPHPIdentifierPart(document.getChar(start - 1))*/;

	  } catch (BadLocationException e) {
		return true;
	  }
	}

	private boolean hasCharacterToTheRight(IDocument document, int offset, char character) {
	  try {
		int end = offset;
		IRegion endLine = document.getLineInformationOfOffset(end);
		int maxEnd = endLine.getOffset() + endLine.getLength();
		while (end != maxEnd && Character.isWhitespace(document.getChar(end)))
		  ++end;

		return end != maxEnd && document.getChar(end) == character;

	  } catch (BadLocationException e) {
		// be conservative
		return true;
	  }
	}

	/*
	 * @see org.eclipse.swt.custom.VerifyKeyListener#verifyKey(org.eclipse.swt.events.VerifyEvent)
	 */
	public void verifyKey(VerifyEvent event) {

	  if (!event.doit)
		return;

	  final ISourceViewer sourceViewer = getSourceViewer();
	  IDocument document = sourceViewer.getDocument();

	  final Point selection = sourceViewer.getSelectedRange();
	  final int offset = selection.x;
	  final int length = selection.y;

	  try {
		ITypedRegion partition = document.getPartition(offset);
		String type = partition.getType();
		if (type.equals(PreferencesConstants.MTL)) {
		  switch (event.character) {
			case '(' :
			  if (hasCharacterToTheRight(document, offset + length, '('))
				return;

			  // fall through

			case '{' :
			  if (!fCloseBracketsMTL)
				return;
			  if (hasIdentifierToTheRight(document, offset + length))
				return;

			  // fall through

			case '"' :
			  if (event.character == '"') {
				if (!fCloseStringsMTL)
				  return;
				// changed for statements like echo ""  print ""
				//    if (hasIdentifierToTheLeft(document, offset) || hasIdentifierToTheRight(document, offset + length))
				if (hasIdentifierToTheRight(document, offset + length))
				  return;
			  }

			  final char character = event.character;
			  final char closingCharacter = getPeerCharacter(character);
			  final StringBuffer buffer = new StringBuffer();
			  buffer.append(character);
			  buffer.append(closingCharacter);

			  document.replace(offset, length, buffer.toString());

			  LinkedPositionManager manager = new LinkedPositionManager(document);
			  manager.addPosition(offset + 1, 0);

			  fOffset = offset;
			  fLength = 2;

			  LinkedPositionUI editor = new LinkedPositionUI(sourceViewer, manager);
			  editor.setCancelListener(this);
			  editor.setExitPolicy(new ExitPolicy(closingCharacter));
			  editor.setFinalCaretOffset(offset + 2);
			  editor.enter();

			  IRegion newSelection = editor.getSelectedRegion();
			  sourceViewer.setSelectedRange(newSelection.getOffset(), newSelection.getLength());

			  event.doit = false;
		  }
		} else if (type.equals(IDocument.DEFAULT_CONTENT_TYPE)) {
		  switch (event.character) {
			case '(' :
			  if (hasCharacterToTheRight(document, offset + length, '('))
				return;

			  // fall through

			case '{' :
			  if (!fCloseBracketsHTML)
				return;
			  if (hasIdentifierToTheRight(document, offset + length))
				return;

			  // fall through

			case '\'' :
			  if (event.character == '\'') {
				if (!fCloseStringsHTML)
				  return;

				if (hasIdentifierToTheLeft(document, offset) || hasIdentifierToTheRight(document, offset + length))
				  return;
			  }

			  //     ITypedRegion partition= document.getPartition(offset);
			  //       if (! IDocument.DEFAULT_CONTENT_TYPE.equals(partition.getType()) &&  (partition.getOffset() != offset))
			  //         return;

			  final char character = event.character;
			  final char closingCharacter = getPeerCharacter(character);
			  final StringBuffer buffer = new StringBuffer();
			  buffer.append(character);
			  buffer.append(closingCharacter);

			  document.replace(offset, length, buffer.toString());

			  LinkedPositionManager manager = new LinkedPositionManager(document);
			  manager.addPosition(offset + 1, 0);

			  fOffset = offset;
			  fLength = 2;

			  LinkedPositionUI editor = new LinkedPositionUI(sourceViewer, manager);
			  editor.setCancelListener(this);
			  editor.setExitPolicy(new ExitPolicy(closingCharacter));
			  editor.setFinalCaretOffset(offset + 2);
			  editor.enter();

			  IRegion newSelection = editor.getSelectedRegion();
			  sourceViewer.setSelectedRange(newSelection.getOffset(), newSelection.getLength());

			  event.doit = false;
		  }
		}
	  } catch (BadLocationException e) {
	  }

	}

	/*
	 * @see org.phpeclipse.phpdt.internal.ui.text.link.LinkedPositionUI.ExitListener#exit(boolean)
	 */
	public void exit(boolean accept) {
	  if (accept)
		return;

	  // remove brackets
	  try {
		final ISourceViewer sourceViewer = getSourceViewer();
		IDocument document = sourceViewer.getDocument();
		document.replace(fOffset, fLength, null);
	  } catch (BadLocationException e) {
	  }
	}

  }
  /** The editor's paint manager */
  //  private PaintManager fPaintManager;
  /** The editor's bracket painter */
  private BracketPainter fBracketPainter;
  /** The editor's bracket matcher */
  private MTLPairMatcher fBracketMatcher;
  /** The editor's line painter */
  private LinePainter fLinePainter;
  /** The editor's print margin ruler painter */
  private PrintMarginPainter fPrintMarginPainter;
  /** The editor's problem painter */
  //  private ProblemPainter fProblemPainter;
  /** The editor's tab converter */
  private TabConverter fTabConverter;
  /** History for structure select action */
  //private SelectionHistory fSelectionHistory;

  /** The preference property change listener for php core. */
  //  private IPropertyChangeListener fPropertyChangeListener = new PropertyChangeListener();
  /** The remembered selection */
  private ITextSelection fRememberedSelection;
  /** The remembered php element offset */
  private int fRememberedElementOffset;
  /** The bracket inserter. */
  private BracketInserter fBracketInserter = new BracketInserter();

  /* Preference key for code formatter tab size */
  private final static String CODE_FORMATTER_TAB_SIZE =DefaultCodeFormatterConstants.FORMATTER_TAB_SIZE;// JavaCore.FORMATTER_TAB_SIZE;
  /** Preference key for matching brackets */
  private final static String MATCHING_BRACKETS = PreferencesConstants.EDITOR_MATCHING_BRACKETS;
  /** Preference key for matching brackets color */
  private final static String MATCHING_BRACKETS_COLOR = PreferencesConstants.EDITOR_MATCHING_BRACKETS_COLOR;
  /** Preference key for highlighting current line */
  private final static String CURRENT_LINE = PreferencesConstants.EDITOR_CURRENT_LINE;
  /** Preference key for highlight color of current line */
  private final static String CURRENT_LINE_COLOR = PreferencesConstants.EDITOR_CURRENT_LINE_COLOR;
  /** Preference key for showing print marging ruler */
  private final static String PRINT_MARGIN = PreferencesConstants.EDITOR_PRINT_MARGIN;
  /** Preference key for print margin ruler color */
  private final static String PRINT_MARGIN_COLOR =org.eclipse.ui.texteditor.AbstractDecoratedTextEditorPreferenceConstants.EDITOR_PRINT_MARGIN_COLOR;
  /** Preference key for print margin ruler column */
  private final static String PRINT_MARGIN_COLUMN = PreferencesConstants.EDITOR_PRINT_MARGIN_COLUMN;
  /** Preference key for inserting spaces rather than tabs */
  private final static String SPACES_FOR_TABS = PreferencesConstants.EDITOR_SPACES_FOR_TABS;
  /** Preference key for error indication */
  private final static String ERROR_INDICATION = PreferencesConstants.EDITOR_PROBLEM_INDICATION;
  /** Preference key for error color */
  private final static String ERROR_INDICATION_COLOR = PreferencesConstants.EDITOR_PROBLEM_INDICATION_COLOR;
  /** Preference key for warning indication */
  private final static String WARNING_INDICATION = PreferencesConstants.EDITOR_WARNING_INDICATION;
  /** Preference key for warning color */
  private final static String WARNING_INDICATION_COLOR = PreferencesConstants.EDITOR_WARNING_INDICATION_COLOR;
  /** Preference key for task indication */
  private final static String TASK_INDICATION = PreferencesConstants.EDITOR_TASK_INDICATION;
  /** Preference key for task color */
  private final static String TASK_INDICATION_COLOR = PreferencesConstants.EDITOR_TASK_INDICATION_COLOR;
  /** Preference key for bookmark indication */
  private final static String BOOKMARK_INDICATION = PreferencesConstants.EDITOR_BOOKMARK_INDICATION;
  /** Preference key for bookmark color */
  private final static String BOOKMARK_INDICATION_COLOR = PreferencesConstants.EDITOR_BOOKMARK_INDICATION_COLOR;
  /** Preference key for search result indication */
  private final static String SEARCH_RESULT_INDICATION = PreferencesConstants.EDITOR_SEARCH_RESULT_INDICATION;
  /** Preference key for search result color */
  private final static String SEARCH_RESULT_INDICATION_COLOR = PreferencesConstants.EDITOR_SEARCH_RESULT_INDICATION_COLOR;
  /** Preference key for unknown annotation indication */
  private final static String UNKNOWN_INDICATION = PreferencesConstants.EDITOR_UNKNOWN_INDICATION;
  /** Preference key for unknown annotation color */
  private final static String UNKNOWN_INDICATION_COLOR = PreferencesConstants.EDITOR_UNKNOWN_INDICATION_COLOR;
  /** Preference key for linked position color */
  private final static String LINKED_POSITION_COLOR = PreferencesConstants.EDITOR_LINKED_POSITION_COLOR;
  /** Preference key for shwoing the overview ruler */
  private final static String OVERVIEW_RULER = PreferencesConstants.EDITOR_OVERVIEW_RULER;

  /** Preference key for error indication in overview ruler */
  private final static String ERROR_INDICATION_IN_OVERVIEW_RULER = PreferencesConstants.EDITOR_ERROR_INDICATION_IN_OVERVIEW_RULER;
  /** Preference key for warning indication in overview ruler */
  private final static String WARNING_INDICATION_IN_OVERVIEW_RULER =
	PreferencesConstants.EDITOR_WARNING_INDICATION_IN_OVERVIEW_RULER;
  /** Preference key for task indication in overview ruler */
  private final static String TASK_INDICATION_IN_OVERVIEW_RULER = PreferencesConstants.EDITOR_TASK_INDICATION_IN_OVERVIEW_RULER;
  /** Preference key for bookmark indication in overview ruler */
  private final static String BOOKMARK_INDICATION_IN_OVERVIEW_RULER =
	PreferencesConstants.EDITOR_BOOKMARK_INDICATION_IN_OVERVIEW_RULER;
  /** Preference key for search result indication in overview ruler */
  private final static String SEARCH_RESULT_INDICATION_IN_OVERVIEW_RULER =
	PreferencesConstants.EDITOR_SEARCH_RESULT_INDICATION_IN_OVERVIEW_RULER;
  /** Preference key for unknown annotation indication in overview ruler */
  private final static String UNKNOWN_INDICATION_IN_OVERVIEW_RULER =
	PreferencesConstants.EDITOR_UNKNOWN_INDICATION_IN_OVERVIEW_RULER;
  /** Preference key for automatically closing strings */
  private final static String CLOSE_STRINGS_MTL = PreferencesConstants.EDITOR_CLOSE_STRINGS_MTL;
  /** Preference key for automatically wrapping Java strings */
  private final static String WRAP_STRINGS = org.eclipse.jdt.ui.PreferenceConstants.EDITOR_WRAP_STRINGS;
  /** Preference key for automatically closing brackets and parenthesis */
  private final static String CLOSE_BRACKETS_MTL = PreferencesConstants.EDITOR_CLOSE_BRACKETS_MTL;
  /** Preference key for automatically closing phpdocs and comments */
  //private final static String CLOSE_JAVADOCS = PreferencesConstants.EDITOR_CLOSE_JAVADOCS;
  /** Preference key for automatically adding phpdoc tags */
  //private final static String ADD_JAVADOC_TAGS = PreferencesConstants.EDITOR_ADD_JAVADOC_TAGS;
  /** Preference key for automatically formatting phpdocs */
  //private final static String FORMAT_JAVADOCS = PreferencesConstants.EDITOR_FORMAT_JAVADOCS;
  /** Preference key for automatically closing strings */
  //private final static String CLOSE_STRINGS_HTML = PreferencesConstants.EDITOR_CLOSE_STRINGS_HTML;
  /** Preference key for automatically closing brackets and parenthesis */
  //private final static String CLOSE_BRACKETS_HTML = PreferencesConstants.EDITOR_CLOSE_BRACKETS_HTML;

  /** Preference key for smart paste */
  private final static String SMART_PASTE = PreferencesConstants.EDITOR_SMART_PASTE;
	/**
   * Creates a new mtl unit editor.
   */
  public MTLUnitEditor() {
	super();
	setDocumentProvider(MTLPlugin.getDefault().getCompilationUnitDocumentProvider());
	setEditorContextMenuId("#MTLEditorContext"); //$NON-NLS-1$
	setRulerContextMenuId("#MTLRulerContext"); //$NON-NLS-1$

  }

  public void createPartControl(Composite parent) {
	super.createPartControl(parent);

	LinePainter linePainter;
	linePainter = new LinePainter(getSourceViewer());

	linePainter.setHighlightColor(new Color(Display.getCurrent(), 225, 235, 224));

	if (isBracketHighlightingEnabled())
	  startBracketHighlighting();
	if (isLineHighlightingEnabled())
	  startLineHighlighting();
	if (isPrintMarginVisible())
	  showPrintMargin();


	if (isTabConversionEnabled())
	  startTabConversion();

	IPreferenceStore preferenceStore = getPreferenceStore();
	boolean closeBracketsMTL = preferenceStore.getBoolean(CLOSE_BRACKETS_MTL);
	boolean closeStringsMTL = preferenceStore.getBoolean(CLOSE_STRINGS_MTL);

	fBracketInserter.setCloseBracketsMTLEnabled(closeBracketsMTL);
	fBracketInserter.setCloseStringsMTLEnabled(closeStringsMTL);

	ISourceViewer sourceViewer = getSourceViewer();
	if (sourceViewer instanceof ITextViewerExtension)
	   ((ITextViewerExtension) sourceViewer).prependVerifyKeyListener(fBracketInserter);

  }

  private static char getPeerCharacter(char character) {
	switch (character) {
	  case '(' :
		return ')';

	  case ')' :
		return '(';

	  case '{' :
		return '}';

	  case '}' :
		return '{';

	  case '\'' :
		return character;

	  default :
		throw new IllegalArgumentException();
	}
  }

  /** The <code>MTLEditor</code> implementation of this
	 * <code>AbstractTextEditor</code> method performs sets the
	 * input of the outline page after AbstractTextEditor has set input.
	 *
	 * @param input The input for the output page to review.
	 * @throws CoreException if fOutlinePage throws
	 */
  protected void doSetInput(IEditorInput input) throws CoreException {
	super.doSetInput(input);
//	if (outlinePage != null) {
//		outlinePage.setInput(input);
//	}
	configureTabConverter();
  }

  private void startBracketHighlighting() {
	if (fBracketPainter == null) {
	  ISourceViewer sourceViewer = getSourceViewer();
	  fBracketPainter = new BracketPainter(sourceViewer);
	  fBracketPainter.setHighlightColor(getColor(MATCHING_BRACKETS_COLOR));
	  //      fPaintManager.addPainter(fBracketPainter);
	}
  }

  private void stopBracketHighlighting() {
	if (fBracketPainter != null) {
	  fBracketPainter.deactivate(true);
	  fBracketPainter.dispose();
	  fBracketPainter = null;
	}
  }

  private boolean isBracketHighlightingEnabled() {
	IPreferenceStore store = getPreferenceStore();
	return store.getBoolean(MATCHING_BRACKETS);
  }

  private void startLineHighlighting() {
	if (fLinePainter == null) {
	  ISourceViewer sourceViewer = getSourceViewer();
	  fLinePainter = new LinePainter(sourceViewer);
	  fLinePainter.setHighlightColor(getColor(CURRENT_LINE_COLOR));
	}
  }

  private void stopLineHighlighting() {
	if (fLinePainter != null) {
	  fLinePainter.deactivate(true);
	  fLinePainter.dispose();
	  fLinePainter = null;
	}
  }

  private boolean isLineHighlightingEnabled() {
	IPreferenceStore store = getPreferenceStore();
	return store.getBoolean(CURRENT_LINE);
  }

  private void showPrintMargin() {
	if (fPrintMarginPainter == null) {
	  fPrintMarginPainter = new PrintMarginPainter(getSourceViewer());
	  fPrintMarginPainter.setMarginRulerColor(getColor(PRINT_MARGIN_COLOR));
	  fPrintMarginPainter.setMarginRulerColumn(getPreferenceStore().getInt(PRINT_MARGIN_COLUMN));
	}
  }

  private void hidePrintMargin() {
	if (fPrintMarginPainter != null) {
	  fPrintMarginPainter.deactivate(true);
	  fPrintMarginPainter.dispose();
	  fPrintMarginPainter = null;
	}
  }

  private boolean isPrintMarginVisible() {
	IPreferenceStore store = getPreferenceStore();
	return store.getBoolean(PRINT_MARGIN);
  }

 
  private void configureTabConverter() {
	if (fTabConverter != null) {
	  IDocumentProvider provider = getDocumentProvider();
	  if (provider instanceof MTLDocumentProviders) {
		MTLDocumentProviders cup = (MTLDocumentProviders) provider;
		IEditorInput input = getEditorInput();
		fTabConverter.setLineTracker(cup.createLineTracker(getEditorInput()));
	  }
	}
  }

  private int getTabSize() {
	Preferences preferences = MTLPlugin.getDefault().getPluginPreferences();
	return preferences.getInt(CODE_FORMATTER_TAB_SIZE);
  }

  private void startTabConversion() {
	if (fTabConverter == null) {
	  fTabConverter = new TabConverter();
	  configureTabConverter();
	  fTabConverter.setNumberOfSpacesPerTab(getTabSize());
	  AdaptedSourceViewer asv = (AdaptedSourceViewer) getSourceViewer();
	  asv.addTextConverter(fTabConverter);
	  asv.updateIndentationPrefixes();
	}
  }

  private void stopTabConversion() {
	if (fTabConverter != null) {
	  AdaptedSourceViewer asv = (AdaptedSourceViewer) getSourceViewer();
	  asv.removeTextConverter(fTabConverter);
	  asv.updateIndentationPrefixes();
	  fTabConverter = null;
	}
  }

  private boolean isTabConversionEnabled() {
	IPreferenceStore store = getPreferenceStore();
	return store.getBoolean(SPACES_FOR_TABS);
  }

   private Color getColor(String key) {
	RGB rgb = PreferenceConverter.getColor(getPreferenceStore(), key);
	return getColor(rgb);
  }

  private Color getColor(RGB rgb) {
	MTLEditorEnvironment textTools = MTLPlugin.getDefault().getMTLEditorEnvironment();
	return textTools.getColorManager().getColor(rgb);
  }

   public void dispose() {
	ISourceViewer sourceViewer = getSourceViewer();
	if (sourceViewer instanceof ITextViewerExtension)
	   ((ITextViewerExtension) sourceViewer).removeVerifyKeyListener(fBracketInserter);

	if (fActionGroups != null) {
	  fActionGroups.dispose();
	  fActionGroups = null;
	}

	super.dispose();
  }


  /*
   * @see AbstractTextEditor#handlePreferenceStoreChanged(PropertyChangeEvent)
   */
  protected void handlePreferenceStoreChanged(PropertyChangeEvent event) {

	try {
		ISourceViewer test=getSourceViewer();
	  AdaptedSourceViewer asv = (AdaptedSourceViewer) getSourceViewer();
	  if (asv != null) {

		String p = event.getProperty();

		if (CLOSE_BRACKETS_MTL.equals(p)) {
		  fBracketInserter.setCloseBracketsMTLEnabled(getPreferenceStore().getBoolean(p));
		  return;
		}

		if (CLOSE_STRINGS_MTL.equals(p)) {
		  fBracketInserter.setCloseStringsMTLEnabled(getPreferenceStore().getBoolean(p));
		  return;
		}

	
		if (SPACES_FOR_TABS.equals(p)) {
		  if (isTabConversionEnabled())
			startTabConversion();
		  else
			stopTabConversion();
		  return;
		}

		if (MATCHING_BRACKETS.equals(p)) {
		  if (isBracketHighlightingEnabled())
			startBracketHighlighting();
		  else
			stopBracketHighlighting();
		  return;
		}

		if (MATCHING_BRACKETS_COLOR.equals(p)) {
		  if (fBracketPainter != null)
			fBracketPainter.setHighlightColor(getColor(MATCHING_BRACKETS_COLOR));
		  return;
		}

		if (CURRENT_LINE.equals(p)) {
		  if (isLineHighlightingEnabled())
			startLineHighlighting();
		  else
			stopLineHighlighting();
		  return;
		}

		if (CURRENT_LINE_COLOR.equals(p)) {
		  if (fLinePainter != null) {
			stopLineHighlighting();
			startLineHighlighting();
		  }
		  return;
		}

		if (PRINT_MARGIN.equals(p)) {
		  if (isPrintMarginVisible())
			showPrintMargin();
		  else
			hidePrintMargin();
		  return;
		}

		if (PRINT_MARGIN_COLOR.equals(p)) {
		  if (fPrintMarginPainter != null)
			fPrintMarginPainter.setMarginRulerColor(getColor(PRINT_MARGIN_COLOR));
		  return;
		}

		if (PRINT_MARGIN_COLUMN.equals(p)) {
		  if (fPrintMarginPainter != null)
			fPrintMarginPainter.setMarginRulerColumn(getPreferenceStore().getInt(PRINT_MARGIN_COLUMN));
		  return;
		}

		if (OVERVIEW_RULER.equals(p)) {
		  if (isOverviewRulerVisible())
			showOverviewRuler();
		  else
			hideOverviewRuler();
		  return;
		}


		IContentAssistant c = asv.getContentAssistant();
		if (c instanceof ContentAssistant)
		  ContentAssistPreferences.changeConfiguration((ContentAssistant) c, getPreferenceStore(), event);
	  }

	} finally {
	  super.handlePreferenceStoreChanged(event);
	}
  }

  /*
	 * @see org.eclipse.jdt.internal.ui.javaeditor.JavaEditor#handlePreferencePropertyChanged(org.eclipse.core.runtime.Preferences.PropertyChangeEvent)
	 */
  protected void handlePreferencePropertyChanged(org.eclipse.core.runtime.Preferences.PropertyChangeEvent event) {
	AdaptedSourceViewer asv = (AdaptedSourceViewer) getSourceViewer();
	if (asv != null) {
	  String p = event.getProperty();
	  if (CODE_FORMATTER_TAB_SIZE.equals(p)) {
		asv.updateIndentationPrefixes();
		if (fTabConverter != null)
		  fTabConverter.setNumberOfSpacesPerTab(getTabSize());
	  }
	}
  }

   /*
   * @see MTLEditor#createJavaSourceViewer(Composite, IVerticalRuler, int)
   */
  protected ISourceViewer createMTLSourceViewer(
	Composite parent,
	IVerticalRuler verticalRuler,
	IOverviewRuler overviewRuler,
	boolean isOverviewRulerVisible,
	int styles) {
	return new AdaptedSourceViewer(parent, verticalRuler, overviewRuler, isOverviewRulerVisible, styles);
  }
 
  private boolean isValidSelection(int offset, int length) {
	IDocumentProvider provider = getDocumentProvider();
	if (provider != null) {
	  IDocument document = provider.getDocument(getEditorInput());
	  if (document != null) {
		int end = offset + length;
		int documentLength = document.getLength();
		return 0 <= offset && offset <= documentLength && 0 <= end && end <= documentLength;
	  }
	}
	return false;
  }

  /*
   * @see AbstractTextEditor#canHandleMove(IEditorInput, IEditorInput)
   */
  protected boolean canHandleMove(IEditorInput originalElement, IEditorInput movedElement) {

	String oldExtension = ""; //$NON-NLS-1$
	if (originalElement instanceof IFileEditorInput) {
	  IFile file = ((IFileEditorInput) originalElement).getFile();
	  if (file != null) {
		String ext = file.getFileExtension();
		if (ext != null)
		  oldExtension = ext;
	  }
	}

	String newExtension = ""; //$NON-NLS-1$
	if (movedElement instanceof IFileEditorInput) {
	  IFile file = ((IFileEditorInput) movedElement).getFile();
	  if (file != null)
		newExtension = file.getFileExtension();
	}

	return oldExtension.equals(newExtension);
  }

  /*
   * @see AbstractTextEditor#editorContextMenuAboutToShow(IMenuManager)
   */
  public void editorContextMenuAboutToShow(IMenuManager menu) {
	super.editorContextMenuAboutToShow(menu);

	ActionContext context = new ActionContext(getSelectionProvider().getSelection());
	fContextMenuGroup.setContext(context);
	fContextMenuGroup.fillContextMenu(menu);
	fContextMenuGroup.setContext(null);
 }

  /*
   * @see AbstractTextEditor#doSaveAs
   */
  public void doSaveAs() {
	if (askIfNonWorkbenchEncodingIsOk()) {
	  super.doSaveAs();
	}
  }

  /*
	 * @see AbstractTextEditor#doSave(IProgressMonitor)
	 */
  public void doSave(IProgressMonitor progressMonitor) {

	IDocumentProvider p = getDocumentProvider();
	if (p == null) {
	  // editor has been closed
	  return;
	}

	if (!askIfNonWorkbenchEncodingIsOk()) {
	  progressMonitor.setCanceled(true);
	  return;
	}

	if (p.isDeleted(getEditorInput())) {

	  if (isSaveAsAllowed()) {

		/*
		 * 1GEUSSR: ITPUI:ALL - User should never loose changes made in the editors.
		 * Changed Behavior to make sure that if called inside a regular save (because
		 * of deletion of input element) there is a way to report back to the caller.
		 */
		//					 performSaveAs(progressMonitor);
		super.doSave(progressMonitor);
	  } else {

		/* 
		 * 1GF5YOX: ITPJUI:ALL - Save of delete file claims it's still there
		 * Missing resources.
		 */
		Shell shell = getSite().getShell();
		MessageDialog.openError(shell, MTLEditorMessages.getString("MTLUnitEditor.error.saving.title1"), MTLEditorMessages.getString("MTLUnitEditor.error.saving.message1")); //$NON-NLS-1$ //$NON-NLS-2$
	  }

	} else {
	  setStatusLineErrorMessage(null);
	  super.doSave(progressMonitor);
		}
  }
  /**
   * Asks the user if it is ok to store in non-workbench encoding.
   * @return <true> if the user wants to continue
   */
  private boolean askIfNonWorkbenchEncodingIsOk() {
	IDocumentProvider provider = getDocumentProvider();
	if (provider instanceof IStorageDocumentProvider) {
	  IEditorInput input = getEditorInput();
	  IStorageDocumentProvider storageProvider = (IStorageDocumentProvider) provider;
	  String encoding = storageProvider.getEncoding(input);
	  String defaultEncoding = storageProvider.getDefaultEncoding();
	  if (encoding != null && !encoding.equals(defaultEncoding)) {
		Shell shell = getSite().getShell();
		String title = MTLEditorMessages.getString("MTLUnitEditor.warning.save.nonWorkbenchEncoding.title"); //$NON-NLS-1$
		String msg;
		if (input != null)
		  msg = MessageFormat.format(MTLEditorMessages.getString("MTLUnitEditor.warning.save.nonWorkbenchEncoding.message1"), new String[] { input.getName(), encoding }); //$NON-NLS-1$
		else
		  msg = MessageFormat.format(MTLEditorMessages.getString("MTLUnitEditor.warning.save.nonWorkbenchEncoding.message2"), new String[] { encoding }); //$NON-NLS-1$
		return MessageDialog.openQuestion(shell, title, msg);
	  }
	}
	return true;
  }
  

  
}
