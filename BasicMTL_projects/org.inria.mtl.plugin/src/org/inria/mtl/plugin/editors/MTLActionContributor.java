/*
* $Id: MTLActionContributor.java,v 1.4 2004-05-19 09:22:36 sdzale Exp $
* Authors : ${user}
*
* Created on ${date}
* Copyright 2004 - INRIA - LGPL license
*/ 
package org.inria.mtl.plugin.editors;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;

import org.eclipse.core.resources.IFile;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IStatusLineManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.actions.RetargetAction;
import org.eclipse.ui.editors.text.EncodingActionGroup;
import org.eclipse.ui.texteditor.BasicTextEditorActionContributor;
import org.eclipse.ui.texteditor.ITextEditor;
import org.eclipse.ui.texteditor.ITextEditorActionDefinitionIds;
import org.eclipse.ui.texteditor.RetargetTextEditorAction;
import org.inria.mtl.plugin.editors.actions.GotoMatchingBracketAction;
import org.inria.mtl.plugin.editors.actions.MTLEditorActionDefinitionIds;
import org.inria.mtl.plugin.editors.utils.MTLActionConstants;
import org.inria.mtl.plugin.preferences.PreferenceConstants;
/**
 * Contributes interesting MTL actions to the desktop's Edit menu and the toolbar.
 */
public class MTLActionContributor extends BasicTextEditorActionContributor {

  protected RetargetTextEditorAction fContentAssistTip;
  protected RetargetAction fRetargetContentAssist;
  protected RetargetTextEditorAction fContentAssist;
  private 	 RetargetTextEditorAction fGotoMatchingBracket;
  private List fRetargetToolbarActions = new ArrayList();
  private List fPartListeners = new ArrayList();

  private EncodingActionGroup fEncodingActionGroup;
  /**
   * Default constructor.
   */
  public MTLActionContributor() {
	super();

	ResourceBundle b = MTLEditorMessages.getResourceBundle();

	fRetargetContentAssist = new RetargetAction(MTLActionConstants.CONTENT_ASSIST, MTLEditorMessages.getString("ContentAssistProposal.label")); //$NON-NLS-1$
	fRetargetContentAssist.setActionDefinitionId(ITextEditorActionDefinitionIds.CONTENT_ASSIST_PROPOSALS);
	markAsPartListener(fRetargetContentAssist);

	fContentAssist = new RetargetTextEditorAction(b, "ContentAssistProposal."); //$NON-NLS-1$
	fContentAssist.setActionDefinitionId(ITextEditorActionDefinitionIds.CONTENT_ASSIST_PROPOSALS);

	fGotoMatchingBracket = new RetargetTextEditorAction(b, "GotoMatchingBracket."); //$NON-NLS-1$
	fGotoMatchingBracket.setActionDefinitionId(MTLEditorActionDefinitionIds.GOTO_MATCHING_BRACKET);

	fContentAssistTip = new RetargetTextEditorAction(MTLEditorMessages.getResourceBundle(), "ContentAssistTip."); //$NON-NLS-1$


//	character encoding
	fEncodingActionGroup = new EncodingActionGroup();

  }

  protected final void markAsPartListener(RetargetAction action) {
	fPartListeners.add(action);
  }

 
  /*
	 * @see org.eclipse.ui.part.EditorActionBarContributor#contributeToMenu(org.eclipse.jface.action.IMenuManager)
	 */
  public void contributeToMenu(IMenuManager menu) {

	super.contributeToMenu(menu);

	IMenuManager editMenu = menu.findMenuUsingPath(IWorkbenchActionConstants.M_EDIT);
	if (editMenu != null) {
	  editMenu.add(new Separator(PreferenceConstants.GROUP_OPEN));
	  editMenu.add(new Separator(PreferenceConstants.GROUP_GENERATE));
	  editMenu.add(new Separator(PreferenceConstants.GROUP_ADDITIONS));

	}

	IMenuManager gotoMenu = menu.findMenuUsingPath("navigate/goTo"); //$NON-NLS-1$
	if (gotoMenu != null) {
	  gotoMenu.add(new Separator("additions2")); //$NON-NLS-1$
	  gotoMenu.appendToGroup("additions2", fGotoMatchingBracket); //$NON-NLS-1$
	}
  }
  /*
   * @see EditorActionBarContributor#contributeToToolBar(IToolBarManager)
   */
  public void contributeToToolBar(IToolBarManager tbm) {
	tbm.add(new Separator());
	Iterator e = fRetargetToolbarActions.iterator();
	while (e.hasNext())
	  tbm.add((IAction) e.next());
  }

  /*
   * @see IEditorActionBarContributor#init(IActionBars, IWorkbenchPage)
   */
  public void init(IActionBars bars, IWorkbenchPage page) {
	Iterator e = fPartListeners.iterator();
	while (e.hasNext())
	  page.addPartListener((RetargetAction) e.next());
	// character encoding
	fEncodingActionGroup.fillActionBars(bars);
	super.init(bars, page);
  }
  /*
   * @see IEditorActionBarContributor#init(IActionBars)
   */
  public void init(IActionBars bars) {
	super.init(bars);

	IMenuManager menuManager = bars.getMenuManager();
	IMenuManager editMenu = menuManager.findMenuUsingPath(IWorkbenchActionConstants.M_EDIT);
	if (editMenu != null) {
	  editMenu.add(new Separator());
	  editMenu.add(fContentAssist);
	  // editMenu.add(fGotoMatchingBracket);
	  //   editMenu.add(fContentAssistTip);
	}

	bars.setGlobalActionHandler(MTLActionConstants.CONTENT_ASSIST, fContentAssist);
	  }

  /*
   * @see IEditorActionBarContributor#setActiveEditor(IEditorPart)
   */
  public void setActiveEditor(IEditorPart part) {
	super.setActiveEditor(part);

	IActionBars bars = getActionBars();
	IStatusLineManager manager = bars.getStatusLineManager();
	manager.setMessage(null);
	manager.setErrorMessage(null);

	ITextEditor textEditor = null;
	if (part instanceof ITextEditor)
	  textEditor = (ITextEditor) part;

	fContentAssist.setAction(getAction(textEditor, "ContentAssistProposal")); //$NON-NLS-1$
	fContentAssistTip.setAction(getAction(textEditor, "ContentAssistTip")); //$NON-NLS-1$
	fGotoMatchingBracket.setAction(getAction(textEditor, GotoMatchingBracketAction.GOTO_MATCHING_BRACKET));

	bars.setGlobalActionHandler(MTLActionConstants.SHIFT_RIGHT, getAction(textEditor, "ShiftRight")); //$NON-NLS-1$
	bars.setGlobalActionHandler(MTLActionConstants.SHIFT_LEFT, getAction(textEditor, "ShiftLeft")); //$NON-NLS-1$
	// character encoding
	fEncodingActionGroup.retarget(textEditor);

	bars.setGlobalActionHandler(MTLActionConstants.COMMENT, getAction(textEditor, "Comment"));
	bars.setGlobalActionHandler(MTLActionConstants.UNCOMMENT, getAction(textEditor, "Uncomment"));
	bars.setGlobalActionHandler(MTLActionConstants.FORMAT, getAction(textEditor, "Format"));

	if (part instanceof MTLEditor) {
	  MTLEditor mtlEditor = (MTLEditor) part;
	  mtlEditor.getActionGroup().fillActionBars(getActionBars());
	}
	
	if (textEditor != null) {

	  IFile file = null;
	  IEditorInput editorInput = textEditor.getEditorInput();

	  if (editorInput instanceof IFileEditorInput) {
		file = ((IFileEditorInput) editorInput).getFile();
	  }
	}
  }

  /*
   * @see IEditorActionBarContributor#dispose()
   */
  public void dispose() {
	Iterator e = fPartListeners.iterator();
	while (e.hasNext())
	  getPage().removePartListener((RetargetAction) e.next());
	fPartListeners.clear();

	setActiveEditor(null);
	super.dispose();
  }
}
