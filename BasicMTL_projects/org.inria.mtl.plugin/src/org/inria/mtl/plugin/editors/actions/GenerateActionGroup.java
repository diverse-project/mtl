package org.inria.mtl.plugin.editors.actions;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.inria.mtl.plugin.preferences.PreferenceConstants;
import org.inria.mtl.plugin.editors.utils.*;
import org.inria.mtl.plugin.editors.MTLEditor;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.util.Assert;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.IWorkbenchSite;
import org.eclipse.ui.actions.ActionGroup;
import org.eclipse.ui.actions.AddBookmarkAction;
import org.eclipse.ui.part.Page;
import org.eclipse.ui.texteditor.ConvertLineDelimitersAction;
import org.eclipse.ui.texteditor.ITextEditorActionDefinitionIds;
import org.eclipse.ui.texteditor.IUpdate;

/**
 * Action group that adds the source and generate actions to a part's context
 * menu and installs handlers for the corresponding global menu actions.
 * 
 * <p>
 * This class may be instantiated; it is not intended to be subclassed.
 * </p>
 * 
 * @since 2.0
 */
public class GenerateActionGroup extends ActionGroup {
	
  private MTLEditor fEditor;
//	private boolean fEditorIsOwner;
	private IWorkbenchSite fSite;
	private String fGroupName= PreferenceConstants.GROUP_SOURCE;
	private List fRegisteredSelectionListeners;
	
	private AddBookmarkAction fAddBookmark;
	private AddTaskAction fAddTaskAction;

	private ConvertLineDelimitersAction fConvertToWindows;
	private ConvertLineDelimitersAction fConvertToUNIX;
	private ConvertLineDelimitersAction fConvertToMac;
	
	/**
	 * Note: This constructor is for internal use only. Clients should not call this constructor.
	 */
	public GenerateActionGroup(MTLEditor editor, String groupName) {
		fSite= editor.getSite();
		fEditor= editor;
		fGroupName= groupName;
				
		ISelectionProvider provider= fSite.getSelectionProvider();
		ISelection selection= provider.getSelection();
		
		fConvertToWindows= new ConvertLineDelimitersAction(editor, "\r\n"); //$NON-NLS-1$
		fConvertToWindows.setActionDefinitionId( ITextEditorActionDefinitionIds .CONVERT_LINE_DELIMITERS_TO_WINDOWS);
		editor.setAction("ConvertLineDelimitersToWindows", fConvertToWindows); //$NON-NLS-1$		
		
		fConvertToUNIX= new ConvertLineDelimitersAction(editor, "\n"); //$NON-NLS-1$
		fConvertToUNIX.setActionDefinitionId( ITextEditorActionDefinitionIds .CONVERT_LINE_DELIMITERS_TO_UNIX);
		editor.setAction("ConvertLineDelimitersToUNIX", fConvertToUNIX); //$NON-NLS-1$		

		fConvertToMac= new ConvertLineDelimitersAction(editor, "\r"); //$NON-NLS-1$
		fConvertToMac.setActionDefinitionId( ITextEditorActionDefinitionIds .CONVERT_LINE_DELIMITERS_TO_MAC);
		editor.setAction("ConvertLineDelimitersToMac", fConvertToMac); //$NON-NLS-1$		
	}
	
	/**
	 * Creates a new <code>GenerateActionGroup</code>. The group 
	 * requires that the selection provided by the page's selection provider 
	 * is of type <code>org.eclipse.jface.viewers.IStructuredSelection</code>.
	 * 
	 * @param page the page that owns this action group
	 */
	public GenerateActionGroup(Page page) {
		this(page.getSite());
	}

	/**
	 * Creates a new <code>GenerateActionGroup</code>. The group 
	 * requires that the selection provided by the part's selection provider 
	 * is of type <code>org.eclipse.jface.viewers.IStructuredSelection</code>.
	 * 
	 * @param part the view part that owns this action group
	 */
	public GenerateActionGroup(IViewPart part) {
		this(part.getSite());
	}
	
	private GenerateActionGroup(IWorkbenchSite site) {
		fSite= site;
		ISelectionProvider provider= fSite.getSelectionProvider();
		ISelection selection= provider.getSelection();
		
		fAddBookmark= new AddBookmarkAction(site.getShell());
		fAddTaskAction= new AddTaskAction(site);
		fAddTaskAction.update(selection);
		if (selection instanceof IStructuredSelection) {
			IStructuredSelection ss= (IStructuredSelection)selection;
			fAddBookmark.selectionChanged(ss);
		} else {
			fAddBookmark.setEnabled(false);
		}
		
		registerSelectionListener(provider, fAddBookmark);
		registerSelectionListener(provider, fAddTaskAction);
	}
	
	private void registerSelectionListener(ISelectionProvider provider, ISelectionChangedListener listener) {
		if (fRegisteredSelectionListeners == null)
			fRegisteredSelectionListeners= new ArrayList(12);
		provider.addSelectionChangedListener(listener);
		fRegisteredSelectionListeners.add(listener);
	}
	
	/*
	 * The state of the editor owning this action group has changed. 
	 * This method does nothing if the group's owner isn't an
	 * editor.
	 */
	/**
	 * Note: This method is for internal use only. Clients should not call this method.
	 */
	public void editorStateChanged() {
		Assert.isTrue(isEditorOwner());
		
		// http://dev.eclipse.org/bugs/show_bug.cgi?id=17709
		fConvertToMac.update();
		fConvertToUNIX.update();
		fConvertToWindows.update();
	}

	/* (non-Javadoc)
	 * Method declared in ActionGroup
	 */
	public void fillActionBars(IActionBars actionBar) {
		super.fillActionBars(actionBar);
		setGlobalActionHandlers(actionBar);
	}
	
	/* (non-Javadoc)
	 * Method declared in ActionGroup
	 */
	public void fillContextMenu(IMenuManager menu) {
		super.fillContextMenu(menu);
		IMenuManager subMenu= null;
		if (isEditorOwner()) {
			subMenu= createEditorSubMenu(menu);
		} else {
	//		subMenu= createViewSubMenu(menu);
		}
		if (subMenu != null)
			menu.appendToGroup(fGroupName, subMenu);
	}
	
  private IMenuManager createEditorSubMenu(IMenuManager mainMenu) {
	IMenuManager result= new MenuManager(ActionMessages.getString("SourceMenu.label")); //$NON-NLS-1$
	int added= 0;
	added+= addEditorAction(result, "Comment"); //$NON-NLS-1$
	added+= addEditorAction(result, "Uncomment"); //$NON-NLS-1$
	added+= addEditorAction(result, "Format"); //$NON-NLS-1$
	if (added == 0)
	  result= null;
	return result;
  }
	/* (non-Javadoc)
	 * Method declared in ActionGroup
	 */
	public void dispose() {
		if (fRegisteredSelectionListeners != null) {
			ISelectionProvider provider= fSite.getSelectionProvider();
			for (Iterator iter= fRegisteredSelectionListeners.iterator(); iter.hasNext();) {
				ISelectionChangedListener listener= (ISelectionChangedListener) iter.next();
				provider.removeSelectionChangedListener(listener);
			}
		}
	fEditor= null;
		super.dispose();
	}
	
	private void setGlobalActionHandlers(IActionBars actionBar) {
		actionBar.setGlobalActionHandler(MTLActionConstants.CONVERT_LINE_DELIMITERS_TO_WINDOWS, fConvertToWindows);
		actionBar.setGlobalActionHandler(MTLActionConstants.CONVERT_LINE_DELIMITERS_TO_UNIX, fConvertToUNIX);
		actionBar.setGlobalActionHandler(MTLActionConstants.CONVERT_LINE_DELIMITERS_TO_MAC, fConvertToMac);
		if (!isEditorOwner()) {
			// editor provides its own implementation of these actions.
			actionBar.setGlobalActionHandler(IWorkbenchActionConstants.BOOKMARK, fAddBookmark);
			actionBar.setGlobalActionHandler(IWorkbenchActionConstants.ADD_TASK, fAddTaskAction);
		}
	}
	
	private int appendToGroup(IMenuManager menu, IAction action) {
		if (action != null && action.isEnabled()) {
			menu.appendToGroup(fGroupName, action);
			return 1;
		}
		return 0;
	}	

	private int addAction(IMenuManager menu, IAction action) {
		if (action != null && action.isEnabled()) {
			menu.add(action);
			return 1;
		}
		return 0;
	}	
	
  private int addEditorAction(IMenuManager menu, String actionID) {
	if (fEditor == null)
	  return 0;
	IAction action= fEditor.getAction(actionID);
	if (action == null)
	  return 0;
	if (action instanceof IUpdate)
	  ((IUpdate)action).update();
	if (action.isEnabled()) {
	  menu.add(action);
	  return 1;
	}
	return 0;
  }
  
  private boolean isEditorOwner() {
	return fEditor != null;
  }	
}
