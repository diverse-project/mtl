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
	
//	private AddImportOnSelectionAction fAddImport;
//	private OverrideMethodsAction fOverrideMethods;
//	private AddGetterSetterAction fAddGetterSetter;
//	private AddUnimplementedConstructorsAction fAddUnimplementedConstructors;
//	private AddJavaDocStubAction fAddJavaDocStub;
	private AddBookmarkAction fAddBookmark;
	private AddTaskAction fAddTaskAction;
//	private ExternalizeStringsAction fExternalizeStrings;
//	private FindStringsToExternalizeAction fFindStringsToExternalize;
//	private SurroundWithTryCatchAction fSurroundWithTryCatch;
	
	// private OrganizeImportsAction fOrganizeImports;

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

//		fAddImport= new AddImportOnSelectionAction(editor);
//		fAddImport.setActionDefinitionId(IJavaEditorActionDefinitionIds.ADD_IMPORT);
//		fAddImport.update();
//		editor.setAction("AddImport", fAddImport); //$NON-NLS-1$
		
//		fOrganizeImports= new OrganizeImportsAction(editor);
//		fOrganizeImports.setActionDefinitionId(IJavaEditorActionDefinitionIds.ORGANIZE_IMPORTS);
//		fOrganizeImports.editorStateChanged();
//		editor.setAction("OrganizeImports", fOrganizeImports); //$NON-NLS-1$

//		fOverrideMethods= new OverrideMethodsAction(editor);
//		fOverrideMethods.setActionDefinitionId(IJavaEditorActionDefinitionIds.OVERRIDE_METHODS);
//		fOverrideMethods.editorStateChanged();
//		editor.setAction("OverrideMethods", fOverrideMethods); //$NON-NLS-1$
		
//		fAddGetterSetter= new AddGetterSetterAction(editor);
//		fAddGetterSetter.setActionDefinitionId(IJavaEditorActionDefinitionIds.CREATE_GETTER_SETTER);
//		fAddGetterSetter.editorStateChanged();
//		editor.setAction("AddGetterSetter", fAddGetterSetter); //$NON-NLS-1$
		
//		fAddUnimplementedConstructors= new AddUnimplementedConstructorsAction(editor);
//		fAddUnimplementedConstructors.setActionDefinitionId(IJavaEditorActionDefinitionIds.ADD_UNIMPLEMENTED_CONTRUCTORS);
//		fAddUnimplementedConstructors.editorStateChanged();
//		editor.setAction("AddUnimplementedConstructors", fAddUnimplementedConstructors); //$NON-NLS-1$		
		
//		fAddJavaDocStub= new AddJavaDocStubAction(editor);
//		fAddJavaDocStub.editorStateChanged();
//		
//		fSurroundWithTryCatch= new SurroundWithTryCatchAction(editor);
//		fSurroundWithTryCatch.setActionDefinitionId(IJavaEditorActionDefinitionIds.SURROUND_WITH_TRY_CATCH);
//		fSurroundWithTryCatch.update(selection);
//		provider.addSelectionChangedListener(fSurroundWithTryCatch);
//		editor.setAction("SurroundWithTryCatch", fSurroundWithTryCatch); //$NON-NLS-1$		
//		
//		fExternalizeStrings= new ExternalizeStringsAction(editor);
//		fExternalizeStrings.setActionDefinitionId(IJavaEditorActionDefinitionIds.EXTERNALIZE_STRINGS);
//		fExternalizeStrings.editorStateChanged();
//		editor.setAction("ExternalizeStrings", fExternalizeStrings); //$NON-NLS-1$		
		
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
		
//		fOverrideMethods= new OverrideMethodsAction(site);
//		fAddGetterSetter= new AddGetterSetterAction(site);
//		fAddUnimplementedConstructors= new AddUnimplementedConstructorsAction(site);
//		fAddJavaDocStub= new AddJavaDocStubAction(site);
		fAddBookmark= new AddBookmarkAction(site.getShell());
		fAddTaskAction= new AddTaskAction(site);
//		fExternalizeStrings= new ExternalizeStringsAction(site);
//		fFindStringsToExternalize= new FindStringsToExternalizeAction(site);
//		fOrganizeImports= new OrganizeImportsAction(site);
//		
//		fOverrideMethods.update(selection);
//		fAddGetterSetter.update(selection);
//		fAddUnimplementedConstructors.update(selection);	
//		fAddJavaDocStub.update(selection);
//		fExternalizeStrings.update(selection);
//		fFindStringsToExternalize.update(selection);
		fAddTaskAction.update(selection);
//		fOrganizeImports.update(selection);
		if (selection instanceof IStructuredSelection) {
			IStructuredSelection ss= (IStructuredSelection)selection;
			fAddBookmark.selectionChanged(ss);
		} else {
			fAddBookmark.setEnabled(false);
		}
		
//		registerSelectionListener(provider, fOverrideMethods);
//		registerSelectionListener(provider, fAddGetterSetter);
//		registerSelectionListener(provider, fAddUnimplementedConstructors);
//		registerSelectionListener(provider, fAddJavaDocStub);
		registerSelectionListener(provider, fAddBookmark);
//		registerSelectionListener(provider, fExternalizeStrings);
//		registerSelectionListener(provider, fFindStringsToExternalize);
//		registerSelectionListener(provider, fOrganizeImports);
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
//	public void fillContextMenu(IMenuManager menu) {
//		super.fillContextMenu(menu);
//		if (fEditorIsOwner) {
//			IMenuManager subMenu= createEditorSubMenu(menu);
//			if (subMenu != null)
//				menu.appendToGroup(fGroupName, subMenu);
//		} else {
////			appendToGroup(menu, fOrganizeImports);
////			appendToGroup(menu, fOverrideMethods);
////			appendToGroup(menu, fAddGetterSetter);
////			appendToGroup(menu, fAddUnimplementedConstructors);
////			appendToGroup(menu, fAddJavaDocStub);
//			appendToGroup(menu, fAddBookmark);
//		}
//	}
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
//	  result.add(new Separator());
//	  added+= addAction(result, fOrganizeImports);
//	  added+= addAction(result, fAddImport);
//	  result.add(new Separator());
//	  added+= addAction(result, fOverrideMethods);
//	  added+= addAction(result, fAddGetterSetter);
//	  added+= addAction(result, fAddUnimplementedConstructors);
//	  added+= addAction(result, fAddJavaDocStub);
//	  added+= addAction(result, fAddBookmark);
//	  result.add(new Separator());		
//	  added+= addAction(result, fSurroundWithTryCatch);
//	  added+= addAction(result, fExternalizeStrings);
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
//		actionBar.setGlobalActionHandler(JdtActionConstants.ADD_IMPORT, fAddImport);
//		actionBar.setGlobalActionHandler(JdtActionConstants.SURROUND_WITH_TRY_CATCH, fSurroundWithTryCatch);
//		actionBar.setGlobalActionHandler(JdtActionConstants.OVERRIDE_METHODS, fOverrideMethods);
//		actionBar.setGlobalActionHandler(JdtActionConstants.GENERATE_GETTER_SETTER, fAddGetterSetter);
//		actionBar.setGlobalActionHandler(JdtActionConstants.ADD_CONSTRUCTOR_FROM_SUPERCLASS, fAddUnimplementedConstructors);
//		actionBar.setGlobalActionHandler(JdtActionConstants.ADD_JAVA_DOC_COMMENT, fAddJavaDocStub);
//		actionBar.setGlobalActionHandler(JdtActionConstants.EXTERNALIZE_STRINGS, fExternalizeStrings);
//		actionBar.setGlobalActionHandler(JdtActionConstants.FIND_STRINGS_TO_EXTERNALIZE, fFindStringsToExternalize);
//		actionBar.setGlobalActionHandler(JdtActionConstants.ORGANIZE_IMPORTS, fOrganizeImports);
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
