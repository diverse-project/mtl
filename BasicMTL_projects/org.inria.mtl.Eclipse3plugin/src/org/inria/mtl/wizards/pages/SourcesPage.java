/*
* $Id: SourcesPage.java,v 1.3 2005-02-08 15:44:30 dvojtise Exp $
* Authors : ${user}
*
* Created on ${date}
* Copyright 2004 - INRIA - LGPL license
*/ 
package org.inria.mtl.wizards.pages;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.internal.corext.util.JavaModelUtil;
import org.eclipse.jdt.internal.ui.util.PixelConverter;
import org.eclipse.jdt.internal.ui.wizards.NewWizardMessages;
import org.eclipse.jdt.internal.ui.wizards.TypedElementSelectionValidator;
import org.eclipse.jdt.internal.ui.wizards.TypedViewerFilter;
import org.eclipse.jdt.internal.ui.wizards.buildpaths.CPListElement;
import org.eclipse.jdt.internal.ui.wizards.buildpaths.CPListElementAttribute;
import org.eclipse.jdt.internal.ui.wizards.buildpaths.CPListElementSorter;
import org.eclipse.jdt.internal.ui.wizards.buildpaths.ExclusionInclusionDialog;
//import org.eclipse.jdt.internal.ui.wizards.buildpaths.ExclusionPatternDialog;
import org.eclipse.jdt.internal.ui.wizards.buildpaths.FolderSelectionDialog;
import org.eclipse.jdt.internal.ui.wizards.buildpaths.NewContainerDialog;
import org.eclipse.jdt.internal.ui.wizards.buildpaths.NewSourceFolderDialog;
import org.eclipse.jdt.internal.ui.wizards.buildpaths.OutputLocationDialog;
import org.eclipse.jdt.internal.ui.wizards.dialogfields.DialogField;
import org.eclipse.jdt.internal.ui.wizards.dialogfields.IDialogFieldListener;
import org.eclipse.jdt.internal.ui.wizards.dialogfields.ITreeListAdapter;
import org.eclipse.jdt.internal.ui.wizards.dialogfields.LayoutUtil;
import org.eclipse.jdt.internal.ui.wizards.dialogfields.ListDialogField;
import org.eclipse.jdt.internal.ui.wizards.dialogfields.SelectionButtonDialogField;
import org.eclipse.jdt.internal.ui.wizards.dialogfields.StringDialogField;
import org.eclipse.jdt.internal.ui.wizards.dialogfields.TreeListDialogField;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.util.Assert;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.model.WorkbenchContentProvider;
import org.eclipse.ui.model.WorkbenchLabelProvider;
import org.inria.mtl.MTLPlugin;
import org.inria.mtl.wizards.BuildPathBasePage;

public class SourcesPage extends BuildPathBasePage {

	private ListDialogField fClassPathList;
	private IJavaProject fCurrJProject;
	private IPath fProjPath;
	
	private Control fSWTControl;
	
	private IWorkspaceRoot fWorkspaceRoot;
	
	private TreeListDialogField fFoldersList;
	
	private StringDialogField fOutputLocationField;
	
	private SelectionButtonDialogField fUseFolderOutputs;
	
	private final int IDX_ADD= 0;
	private final int IDX_EDIT= 2;
	private final int IDX_REMOVE= 3;
	
	public SourcesPage(IWorkspaceRoot root, ListDialogField classPathList) {
		fWorkspaceRoot= root;
		fClassPathList= classPathList;
	
			
		fSWTControl= null;
				
		SourceContainerAdapter adapter= new SourceContainerAdapter();
					
		String[] buttonLabels;  

		buttonLabels= new String[] { 
			/* 0 = IDX_ADDEXIST */ NewWizardMessages.getString("SourceContainerWorkbookPage.folders.add.button"), //$NON-NLS-1$
			/* 1 */ null,
			/* 2 = IDX_EDIT */ NewWizardMessages.getString("SourceContainerWorkbookPage.folders.edit.button"), //$NON-NLS-1$
			/* 3 = IDX_REMOVE */ NewWizardMessages.getString("SourceContainerWorkbookPage.folders.remove.button") //$NON-NLS-1$
		};
		
		fFoldersList= new TreeListDialogField(adapter, buttonLabels, new CPListLabelProvider());
		fFoldersList.setDialogFieldListener(adapter);
		fFoldersList.setLabelText(NewWizardMessages.getString("SourceContainerWorkbookPage.folders.label")); //$NON-NLS-1$
		
		fFoldersList.setViewerSorter(new CPListElementSorter());
		fFoldersList.enableButton(IDX_EDIT, false);
		
		//DVK
		fOutputLocationField = new StringDialogField();
		fOutputLocationField.setDialogFieldListener(adapter);
		fOutputLocationField.setLabelText(NewWizardMessages.getString("BuildPathsBlock.buildpath.label")); //$NON-NLS-1$
		
		
	
	}
	
	public void init(IJavaProject jproject) {
		fCurrJProject= jproject;
		fProjPath= fCurrJProject.getProject().getFullPath();	
		updateFoldersList();
	}
	
	private void updateFoldersList() {	
		ArrayList folders= new ArrayList();
	
		boolean useFolderOutputs= false;
		List cpelements= fClassPathList.getElements();
		for (int i= 0; i < cpelements.size(); i++) {
			CPListElement cpe= (CPListElement)cpelements.get(i);
			if (cpe.getEntryKind() == IClasspathEntry.CPE_SOURCE) {
				folders.add(cpe);
				boolean hasOutputFolder= (cpe.getAttribute(CPListElement.OUTPUT) != null);
				if (hasOutputFolder) {
					useFolderOutputs= true;
				}

			}
		}
		fFoldersList.setElements(folders);
		//fUseFolderOutputs.setSelection(useFolderOutputs);
		
		for (int i= 0; i < folders.size(); i++) {
			CPListElement cpe= (CPListElement) folders.get(i);
			IPath[] patterns= (IPath[]) cpe.getAttribute(CPListElement.EXCLUSION);
			boolean hasOutputFolder= (cpe.getAttribute(CPListElement.OUTPUT) != null);
			if (patterns.length > 0 || hasOutputFolder) {
				fFoldersList.expandElement(cpe, 3);
			}				
		}
	}			
	
	public Control getControl(Composite parent) {
		PixelConverter converter= new PixelConverter(parent);
		Composite composite= new Composite(parent, SWT.NONE);
		
		try
		{
			LayoutUtil.doDefaultLayout(composite, new DialogField[] { fFoldersList, fOutputLocationField}, true);
			LayoutUtil.setHorizontalGrabbing(fFoldersList.getTreeControl(null));
		}
		catch (Exception E){
			System.out.println("Erreur à ce niveau " +E.getMessage());
		}
		
		int buttonBarWidth= converter.convertWidthInCharsToPixels(24);
		fFoldersList.setButtonsMinWidth(buttonBarWidth);
			
		fSWTControl= composite;
		
		// expand
		List elements= fFoldersList.getElements();
		for (int i= 0; i < elements.size(); i++) {
			CPListElement elem= (CPListElement) elements.get(i);
			IPath[] patterns= (IPath[]) elem.getAttribute(CPListElement.EXCLUSION);
			IPath output= (IPath) elem.getAttribute(CPListElement.OUTPUT);
			if (patterns.length > 0 || output != null) {
				fFoldersList.expandElement(elem, 3);
			}
		}
		return composite;
	}
	
	private Shell getShell() {
		if (fSWTControl != null) {
			return fSWTControl.getShell();
		}
		return MTLPlugin.getActiveWorkbenchShell();
	}
	
	private class OutputContainerAdapter implements IDialogFieldListener {
//		 ---------- IDialogFieldListener --------
		public void dialogFieldChanged(DialogField field) {
			sourcePageDialogFieldChanged(field);
		}
	}
	private class SourceContainerAdapter implements ITreeListAdapter, IDialogFieldListener {
	
		private final Object[] EMPTY_ARR= new Object[0];
		
		// -------- IListAdapter --------
		public void customButtonPressed(TreeListDialogField field, int index) {
			sourcePageCustomButtonPressed(field, index);
		}
		
		public void selectionChanged(TreeListDialogField field) {
			sourcePageSelectionChanged(field);
		}
		
		public void doubleClicked(TreeListDialogField field) {
			sourcePageDoubleClicked(field);
		}
		
		public void keyPressed(TreeListDialogField field, KeyEvent event) {
			sourcePageKeyPressed(field, event);
		}	

		public Object[] getChildren(TreeListDialogField field, Object element) {
			if (element instanceof CPListElement) {
				return ((CPListElement) element).getChildren(false);//peut causer des soucis
			}
			return EMPTY_ARR;
		}

		public Object getParent(TreeListDialogField field, Object element) {
			if (element instanceof CPListElementAttribute) {
				return ((CPListElementAttribute) element).getParent();
			}
			return null;
		}

		public boolean hasChildren(TreeListDialogField field, Object element) {
			return (element instanceof CPListElement);
		}		
		
		// ---------- IDialogFieldListener --------
		public void dialogFieldChanged(DialogField field) {
			sourcePageDialogFieldChanged(field);
		}

	}
	
	protected void sourcePageKeyPressed(TreeListDialogField field, KeyEvent event) {
		if (field == fFoldersList) {
			if (event.character == SWT.DEL && event.stateMask == 0) {
				List selection= field.getSelectedElements();
				if (canRemove(selection)) {
					removeEntry();
				}
			}
		}	
	}	
	
	protected void sourcePageDoubleClicked(TreeListDialogField field) {
		if (field == fFoldersList) {
			List selection= field.getSelectedElements();
			if (canEdit(selection)) {
				editEntry();
			}
		}
	}
	
	protected void sourcePageCustomButtonPressed(DialogField field, int index) {
		if (field == fFoldersList) {
			if (index == IDX_ADD) {
				List elementsToAdd= new ArrayList(10);
				if (fCurrJProject.getProject().exists()) {
					CPListElement[] srcentries= openSourceContainerDialog(null);
					if (srcentries != null) {
						for (int i= 0; i < srcentries.length; i++) {
							elementsToAdd.add(srcentries[i]);
						}
					}						
				} else {
					CPListElement entry= openNewSourceContainerDialog(null);
					if (entry != null) {
						elementsToAdd.add(entry);
					}
				}
				if (!elementsToAdd.isEmpty()) {
					if (fFoldersList.getSize() == 1) {
						CPListElement existing= (CPListElement) fFoldersList.getElement(0);
						if (existing.getResource() instanceof IProject) {
						//	askForChangingBuildPathDialog(existing);
						}
					}
					HashSet modifiedElements= new HashSet();
					askForAddingExclusionPatternsDialog(elementsToAdd, modifiedElements);
					
					fFoldersList.addElements(elementsToAdd);
					fFoldersList.postSetSelection(new StructuredSelection(elementsToAdd));
					
					if (!modifiedElements.isEmpty()) {
						for (Iterator iter= modifiedElements.iterator(); iter.hasNext();) {
							Object elem= iter.next();
							fFoldersList.refresh(elem);
							fFoldersList.expandElement(elem, 3);
						}
					}

				}				
			} else if (index == IDX_EDIT) {
				editEntry();
			} else if (index == IDX_REMOVE) {
				removeEntry();
			}
		}
	}

	private void editEntry() {
		List selElements= fFoldersList.getSelectedElements();
		if (selElements.size() != 1) {
			return;
		}
		Object elem= selElements.get(0);
		if (fFoldersList.getIndexOfElement(elem) != -1) {
			editElementEntry((CPListElement) elem);
		} else if (elem instanceof CPListElementAttribute) {
			editAttributeEntry((CPListElementAttribute) elem);
		}
	}

	private void editElementEntry(CPListElement elem) {
		CPListElement res= null;
		
		IResource resource= elem.getResource();
		if (resource.exists()) {
			CPListElement[] arr= openSourceContainerDialog(elem);
			if (arr != null) {
				res= arr[0];
			}
		} else {
			res= openNewSourceContainerDialog(elem);
		}
		
		if (res != null) {
			fFoldersList.replaceElement(elem, res);
		}
	}
//	 Version 3.0
	private void editAttributeEntry(CPListElementAttribute elem) {
		String key= elem.getKey();
		if (key.equals(CPListElement.OUTPUT)) {
			CPListElement selElement=  elem.getParent();
			OutputLocationDialog dialog= new OutputLocationDialog(getShell(), selElement);
			if (dialog.open() == Window.OK) {
				selElement.setAttribute(CPListElement.OUTPUT, dialog.getOutputLocation());
				fFoldersList.refresh();
				fClassPathList.dialogFieldChanged(); // validate
			}
		} else if (key.equals(CPListElement.EXCLUSION)) {
			showExclusionInclusionDialog(elem.getParent(), true);		
		} else if (key.equals(CPListElement.INCLUSION)) {
			showExclusionInclusionDialog(elem.getParent(), false);
		}
	}
	
	private void showExclusionInclusionDialog(CPListElement selElement, boolean focusOnExclusion) {
		ExclusionInclusionDialog dialog= new ExclusionInclusionDialog(getShell(), selElement, focusOnExclusion);
		if (dialog.open() == Window.OK) {
			selElement.setAttribute(CPListElement.INCLUSION, dialog.getInclusionPattern());
			selElement.setAttribute(CPListElement.EXCLUSION, dialog.getExclusionPattern());
			fFoldersList.refresh();
			fClassPathList.dialogFieldChanged(); // validate
		}
	}

	
	protected void sourcePageSelectionChanged(DialogField field) {
		List selected= fFoldersList.getSelectedElements();
		fFoldersList.enableButton(IDX_EDIT, canEdit(selected));
		fFoldersList.enableButton(IDX_REMOVE, canRemove(selected));
	}
	
	private void removeEntry() {
		List selElements= fFoldersList.getSelectedElements();
		for (int i= selElements.size() - 1; i >= 0 ; i--) {
			Object elem= selElements.get(i);
			if (elem instanceof CPListElementAttribute) {
				CPListElementAttribute attrib= (CPListElementAttribute) elem;
				String key= attrib.getKey();
				Object value= key.equals(CPListElement.EXCLUSION) ? new Path[0] : null;
				attrib.getParent().setAttribute(key, value);
				selElements.remove(i);
			}
		}
		if (selElements.isEmpty()) {
			fFoldersList.refresh();
			fClassPathList.dialogFieldChanged(); // validate
		} else {
			fFoldersList.removeElements(selElements);
		}
	}
	
	private boolean canRemove(List selElements) {
		if (selElements.size() == 0) {
			return false;
		}
		for (int i= 0; i < selElements.size(); i++) {
			Object elem= (Object) selElements.get(i);
			if (elem instanceof CPListElementAttribute) {
				CPListElementAttribute attrib= (CPListElementAttribute) elem;
				if (attrib.getKey().equals(CPListElement.EXCLUSION)) {
					if (((IPath[]) attrib.getValue()).length == 0) {
						return false;
					}
				} else if (attrib.getValue() == null) {
					return false;
				}
			} else if (elem instanceof CPListElement) {
				CPListElement curr= (CPListElement) elem;
				if (curr.getParentContainer() != null) {
					return false;
				}
			}
		}
		return true;
	}		
	
	private boolean canEdit(List selElements) {
		if (selElements.size() != 1) {
			return false;
		}
		Object elem= selElements.get(0);
		if (fFoldersList.getIndexOfElement(elem) != -1) {
			return true;
		}
		if (elem instanceof CPListElementAttribute) {
			return true;
		}
		return false;
	}	
	
	private void sourcePageDialogFieldChanged(DialogField field) {
		if (fCurrJProject == null) {
			// not initialized
			return;
		}
		
		if (field == fUseFolderOutputs) {
			if (!fUseFolderOutputs.isSelected()) {
				int nFolders= fFoldersList.getSize();
				for (int i= 0; i < nFolders; i++) {
					CPListElement cpe= (CPListElement) fFoldersList.getElement(i);
					cpe.setAttribute(CPListElement.OUTPUT, null);
				}
			}
			fFoldersList.refresh();
		} else if (field == fFoldersList) {
			updateClasspathList();
		} else if (field == fOutputLocationField)
		{
			updateOutputpath();
		}
	}	
	private void updateOutputpath() {
		System.out.println("new output value" + fOutputLocationField.getText() );
	}
		
	private void updateClasspathList() {
		List cpelements= fClassPathList.getElements();
		List srcelements= fFoldersList.getElements();
		
		boolean changeDone= false;
		CPListElement lastSourceFolder= null;
		// backwards, as entries will be deleted
		for (int i= cpelements.size() - 1; i >= 0 ; i--) {
			CPListElement cpe= (CPListElement)cpelements.get(i);
			if (isEntryKind(cpe.getEntryKind())) {
				// if it is a source folder, but not one of the accepted entries, remove it
				// at the same time, for the entries seen, remove them from the accepted list
				if (!srcelements.remove(cpe)) {
					cpelements.remove(i);
					changeDone= true;
				} else if (lastSourceFolder == null) {
					lastSourceFolder= cpe;
				}
			}
		}

		if (!srcelements.isEmpty()) {
			int insertIndex= (lastSourceFolder == null) ? 0 : cpelements.indexOf(lastSourceFolder) + 1;
			cpelements.addAll(insertIndex, srcelements);
			changeDone= true;
		}

		if (changeDone) {
			fClassPathList.setElements(cpelements);
		}

	}
		
	private CPListElement openNewSourceContainerDialog(CPListElement existing) {	
		String title= (existing == null) ? NewWizardMessages.getString("SourceContainerWorkbookPage.NewSourceFolderDialog.new.title") : NewWizardMessages.getString("SourceContainerWorkbookPage.NewSourceFolderDialog.edit.title"); //$NON-NLS-1$ //$NON-NLS-2$

		IProject proj= fCurrJProject.getProject();
		NewSourceFolderDialog dialog= new NewSourceFolderDialog(getShell(), title, proj, getExistingContainers(existing), existing);
		dialog.setMessage(NewWizardMessages.getFormattedString("SourceContainerWorkbookPage.NewSourceFolderDialog.description", fProjPath.toString())); //$NON-NLS-1$
		if (dialog.open() == NewContainerDialog.OK) {
			IResource folder= dialog.getSourceFolder();
			return newCPSourceElement(folder);
		}
		return null;
	}
	
	
	private void askForAddingExclusionPatternsDialog(List newEntries, Set modifiedEntries) {
		for (int i= 0; i < newEntries.size(); i++) {
			CPListElement curr= (CPListElement) newEntries.get(i);
			addExclusionPatterns(curr, modifiedEntries);
		}
		if (!modifiedEntries.isEmpty()) {
			String title= NewWizardMessages.getString("SourceContainerWorkbookPage.exclusion_added.title"); //$NON-NLS-1$
			String message= NewWizardMessages.getString("SourceContainerWorkbookPage.exclusion_added.message"); //$NON-NLS-1$
			MessageDialog.openInformation(getShell(), title, message);
		}
	}
	
	
	private void addExclusionPatterns(CPListElement newEntry, Set modifiedEntries) {
		IPath entryPath= newEntry.getPath();
		List existing= fFoldersList.getElements();
		for (int i= 0; i < existing.size(); i++) {
			CPListElement curr= (CPListElement) existing.get(i);
			IPath currPath= curr.getPath();
			if (currPath.isPrefixOf(entryPath)) {
				IPath[] exclusionFilters= (IPath[]) curr.getAttribute(CPListElement.EXCLUSION);
				if (!JavaModelUtil.isExcludedPath(entryPath, exclusionFilters)) {
					IPath pathToExclude= entryPath.removeFirstSegments(currPath.segmentCount()).addTrailingSeparator();
					IPath[] newExclusionFilters= new IPath[exclusionFilters.length + 1];
					System.arraycopy(exclusionFilters, 0, newExclusionFilters, 0, exclusionFilters.length);
					newExclusionFilters[exclusionFilters.length]= pathToExclude;
					curr.setAttribute(CPListElement.EXCLUSION, newExclusionFilters);
					modifiedEntries.add(curr);
				}
			}
		}
	}
			
	private CPListElement[] openSourceContainerDialog(CPListElement existing) {
		
		Class[] acceptedClasses= new Class[] { IProject.class, IFolder.class };
		TypedElementSelectionValidator validator= new TypedElementSelectionValidator(acceptedClasses, existing == null, getExistingContainers(null));
		
		IProject[] allProjects= fWorkspaceRoot.getProjects();
		ArrayList rejectedElements= new ArrayList(allProjects.length);
		IProject currProject= fCurrJProject.getProject();
		for (int i= 0; i < allProjects.length; i++) {
			if (!allProjects[i].equals(currProject)) {
				rejectedElements.add(allProjects[i]);
			}
		}
		ViewerFilter filter= new TypedViewerFilter(acceptedClasses, rejectedElements.toArray());
		
		ILabelProvider lp= new WorkbenchLabelProvider();
		ITreeContentProvider cp= new WorkbenchContentProvider();

		String title= (existing == null) ? NewWizardMessages.getString("SourceContainerWorkbookPage.ExistingSourceFolderDialog.new.title") : NewWizardMessages.getString("SourceContainerWorkbookPage.ExistingSourceFolderDialog.edit.title"); //$NON-NLS-1$ //$NON-NLS-2$
		String message= (existing == null) ? NewWizardMessages.getString("SourceContainerWorkbookPage.ExistingSourceFolderDialog.new.description") : NewWizardMessages.getString("SourceContainerWorkbookPage.ExistingSourceFolderDialog.edit.description"); //$NON-NLS-1$ //$NON-NLS-2$

		FolderSelectionDialog dialog= new FolderSelectionDialog(getShell(), lp, cp);
		dialog.setValidator(validator);
		dialog.setTitle(title);
		dialog.setMessage(message);
		dialog.addFilter(filter);
		dialog.setInput(fCurrJProject.getProject().getParent());
	//	dialog.setSorter(new ResourceSorter(ResourceSorter.NAME));
		if (existing == null) {
			dialog.setInitialSelection(fCurrJProject.getProject());
		} else {
			dialog.setInitialSelection(existing.getResource());
		}		
		if (dialog.open() == FolderSelectionDialog.OK) {
			Object[] elements= dialog.getResult();	
			CPListElement[] res= new CPListElement[elements.length];
			for (int i= 0; i < res.length; i++) {
				IResource elem= (IResource)elements[i];
				res[i]= newCPSourceElement(elem);
			}
			return res;
		}
		return null;
	}
	
	private List getExistingContainers(CPListElement existing) {
		List res= new ArrayList();
		List cplist= fFoldersList.getElements();
		for (int i= 0; i < cplist.size(); i++) {
			CPListElement elem= (CPListElement)cplist.get(i);
			if (elem != existing) {
				IResource resource= elem.getResource();
				if (resource instanceof IContainer) { // defensive code
					res.add(resource);	
				}
			}
		}
		return res;
	}
	
	private CPListElement newCPSourceElement(IResource res) {
		Assert.isNotNull(res);
		return new CPListElement(fCurrJProject, IClasspathEntry.CPE_SOURCE, res.getFullPath(), res);
	}
	
	
	/*
	 * @see BuildPathBasePage#getSelection
	 */
	public List getSelection() {
		return fFoldersList.getSelectedElements();
	}

	/*
	 * @see BuildPathBasePage#setSelection
	 */	
	public void setSelection(List selElements) {
		fFoldersList.selectElements(new StructuredSelection(selElements));
	}	
	
	/* (non-Javadoc)
	 * @see org.eclipse.jdt.internal.ui.wizards.buildpaths.BuildPathBasePage#isEntryKind(int)
	 */
	public boolean isEntryKind(int kind) {
		return kind == IClasspathEntry.CPE_SOURCE;
	}	

}