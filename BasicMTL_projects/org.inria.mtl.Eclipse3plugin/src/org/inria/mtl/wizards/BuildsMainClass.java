/*
* $Id: BuildsMainClass.java,v 1.1 2004-07-30 14:08:39 sdzale Exp $
* Authors : ${user}
*
* Created on ${date}
* Copyright 2004 - INRIA - LGPL license
*/ 
package org.inria.mtl.wizards;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.internal.ui.IJavaHelpContextIds;
import org.eclipse.jdt.internal.ui.JavaPlugin;
import org.eclipse.jdt.internal.ui.JavaPluginImages;
import org.eclipse.jdt.internal.ui.dialogs.StatusInfo;
import org.eclipse.jdt.internal.ui.dialogs.StatusUtil;
import org.eclipse.jdt.internal.ui.util.PixelConverter;
import org.eclipse.jdt.internal.ui.util.TabFolderLayout;
import org.eclipse.jdt.internal.ui.wizards.IStatusChangeListener;
import org.eclipse.jdt.internal.ui.wizards.NewWizardMessages;
import org.eclipse.jdt.internal.ui.wizards.TypedElementSelectionValidator;
import org.eclipse.jdt.internal.ui.wizards.TypedViewerFilter;
import org.eclipse.jdt.internal.ui.wizards.buildpaths.BuildPathBasePage;
import org.eclipse.jdt.internal.ui.wizards.buildpaths.CPListElement;
import org.eclipse.jdt.internal.ui.wizards.buildpaths.FolderSelectionDialog;
import org.eclipse.jdt.internal.ui.wizards.dialogfields.CheckedListDialogField;
import org.eclipse.jdt.internal.ui.wizards.dialogfields.DialogField;
import org.eclipse.jdt.internal.ui.wizards.dialogfields.IDialogFieldListener;
import org.eclipse.jdt.internal.ui.wizards.dialogfields.IStringButtonAdapter;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Widget;
import org.eclipse.ui.dialogs.ISelectionStatusValidator;
import org.eclipse.ui.help.WorkbenchHelp;
import org.eclipse.ui.model.WorkbenchContentProvider;
import org.eclipse.ui.model.WorkbenchLabelProvider;
import org.inria.mtl.MTLPlugin;
import org.inria.mtl.builders.MTLModel;
import org.inria.mtl.core.MTLCore;
import org.inria.mtl.wizards.pages.MainClassChoicePage;

public class BuildsMainClass {

	public static interface IRemoveOldBinariesQuery {
		
		/**
		 * Do the callback. Returns <code>true</code> if .class files should be removed from the
		 * old output location.
		 */
		boolean doQuery(IPath oldOutputLocation) throws InterruptedException;
		
	}


	private IWorkspaceRoot fWorkspaceRoot;

	private CheckedListDialogField fClassPathList;
	private CheckedListDialogField fMainClassBuildDialogField;
	
	private StatusInfo fClassPathStatus;
	private StatusInfo fOutputFolderStatus;	
	private StatusInfo fBuildPathStatus;

	private IJavaProject fCurrJProject;
	
	private IMtlJavaProject fCurrMtlProject;
		
	private IPath fOutputLocationPath;
	
	private IStatusChangeListener fContext;
	private Control fSWTWidget;	
	
	private int fPageIndex;
	
	private MainClassChoicePage fMainClassPage;
	
	private BuildPathBasePage fCurrPage;
	
	
		
	public BuildsMainClass(IStatusChangeListener context, int pageToShow) {
		fWorkspaceRoot= MTLPlugin.getWorkspace().getRoot();
		fContext= context;
		
		fPageIndex= pageToShow;
		
		fMainClassPage=null;
		
		fCurrPage= null;
				
		BuildPathAdapter adapter= new BuildPathAdapter();			
	
		String[] buttonLabels= new String[] {
			/* 0 */ NewWizardMessages.getString("BuildPathsBlock.classpath.up.button"), //$NON-NLS-1$
			/* 1 */ NewWizardMessages.getString("BuildPathsBlock.classpath.down.button"), //$NON-NLS-1$
			/* 2 */ null,
			/* 3 */ NewWizardMessages.getString("BuildPathsBlock.classpath.checkall.button"), //$NON-NLS-1$
			/* 4 */ NewWizardMessages.getString("BuildPathsBlock.classpath.uncheckall.button") //$NON-NLS-1$
		
		};
		
		fClassPathList= new CheckedListDialogField(null, buttonLabels, new CPListLabelProvider());
		fClassPathList.setDialogFieldListener(adapter);
		fClassPathList.setLabelText(NewWizardMessages.getString("BuildPathsBlock.classpath.label"));  //$NON-NLS-1$
		fClassPathList.setUpButtonIndex(0);
		fClassPathList.setDownButtonIndex(1);
		fClassPathList.setCheckAllButtonIndex(3);
		fClassPathList.setUncheckAllButtonIndex(4);		
			

		fBuildPathStatus= new StatusInfo();
		fClassPathStatus= new StatusInfo();
		fOutputFolderStatus= new StatusInfo();
		
		fCurrJProject= null;
	}
	
	// -------- UI creation ---------
	
	public Control createControl(Composite parent) {
		fSWTWidget= parent;
		
		PixelConverter converter= new PixelConverter(parent);
		
		Composite composite= new Composite(parent, SWT.NONE);	
		
		GridLayout layout= new GridLayout();
		layout.marginWidth= 0;
		layout.numColumns= 1;		
		composite.setLayout(layout);
		
		TabFolder folder= new TabFolder(composite, SWT.NONE);
		folder.setLayout(new TabFolderLayout());	
		folder.setLayoutData(new GridData(GridData.FILL_BOTH));
		
		ImageRegistry imageRegistry= JavaPlugin.getDefault().getImageRegistry();
		
		TabItem item;
		fMainClassPage= new MainClassChoicePage(fClassPathList);		
		item= new TabItem(folder, SWT.NONE);
		item.setText(MTLImportMessages.getString("BuildPathsBlock.tab.mainClass")); //$NON-NLS-1$
		item.setImage(imageRegistry.get(JavaPluginImages.IMG_OBJS_LIBRARY));
		item.setData(fMainClassPage);
		item.setControl(fMainClassPage.getControl(folder));
		
		if (fMainClassPage != null) {
			fMainClassPage.init(fCurrJProject);
		}else{
			System.out.println("'Main class page null");		
		}
						

		WorkbenchHelp.setHelp(composite, IJavaHelpContextIds.BUILD_PATH_BLOCK);				
		return composite;
	}
	
	private Shell getShell() {
		if (fSWTWidget != null) {
			return fSWTWidget.getShell();
		}
		return MTLPlugin.getActiveWorkbenchShell();
	}
	
	
	/**
	 * Initializes the classpath for the given project. Multiple calls to init are allowed,
	 * but all existing settings will be cleared and replace by the given or default paths.
	 * @param project The java project to configure. Does not have to exist.
	 * @param outputLocation The output location to be set in the page. If <code>null</code>
	 * is passed, jdt default settings are used, or - if the project is an existing Java project- the
	 * output location of the existing project 
	 * @param classpathEntries The classpath entries to be set in the page. If <code>null</code>
	 * is passed, jdt default settings are used, or - if the project is an existing Java project - the
	 * classpath entries of the existing project
	 */	
	public void init(IJavaProject jproject, IPath outputLocation, IClasspathEntry[] classpathEntries) {
		
		fCurrJProject= jproject;
		
		boolean projectExists= false;
		List newClassPath= null;
		
		try {
			IProject project= fCurrJProject.getProject();
			projectExists= (project.exists() && project.getFile(".mtlclasspath").exists()); //$NON-NLS-1$
		
			if  (projectExists) {
				if (outputLocation == null) {
					outputLocation=  fCurrJProject.getOutputLocation();
				}
				if (classpathEntries == null) {
					MTLCore.setProject(MTLModel.getProject());
					classpathEntries=  MTLCore.readClasspathFile();
				}
			}
				if (classpathEntries != null) {
					newClassPath= getExistingEntries(classpathEntries);
			}
		} catch (CoreException e) {
			MTLPlugin.log(e);
		}
	
		List exportedEntries = new ArrayList();
		for (int i= 0; i < newClassPath.size(); i++) {
			CPListElement curr= (CPListElement) newClassPath.get(i);
			if (curr.isExported() || curr.getEntryKind() == IClasspathEntry.CPE_SOURCE) {
				exportedEntries.add(curr);
			}
		}
		
		// inits the dialog field
		fClassPathList.setElements(newClassPath);
		fClassPathList.setCheckedElements(exportedEntries);
		
		if (fMainClassPage != null) {
			fMainClassPage.init(fCurrJProject);
		}else{
			System.out.println("Projet vide ");
		}

		doStatusLineUpdate();
	}

	private ArrayList getExistingEntries(IClasspathEntry[] classpathEntries) {
		ArrayList newClassPath= new ArrayList();
		for (int i= 0; i < classpathEntries.length; i++) {
			IClasspathEntry curr= classpathEntries[i];
			newClassPath.add(CPListElement.createFromExisting(curr, fCurrJProject));
		}
		return newClassPath;
	}
	
	// -------- public api --------
	
	/**
	 * Returns the Java project. Can return <code>null<code> if the page has not
	 * been initialized.
	 */
	
	public IJavaProject getProject() {
			return fCurrJProject;
		}
		

	
	public int getPageIndex() {
		return fPageIndex;
	}
	
	

	private class BuildPathAdapter implements IStringButtonAdapter, IDialogFieldListener {

		// -------- IStringButtonAdapter --------
		public void changeControlPressed(DialogField field) {
			buildPathChangeControlPressed(field);
		}
		
		// ---------- IDialogFieldListener --------
		public void dialogFieldChanged(DialogField field) {
			buildPathDialogFieldChanged(field);
		}
	}
	
	private void buildPathChangeControlPressed(DialogField field) {
	
	}
	
	private void buildPathDialogFieldChanged(DialogField field) {
	
	}	
	

	
	// -------- verification -------------------------------
	
	private void doStatusLineUpdate() {
		IStatus res= findMostSevereStatus();
		fContext.statusChanged(res);
	}
	
	private IStatus findMostSevereStatus() {
		return StatusUtil.getMostSevere(new IStatus[] { fClassPathStatus, fOutputFolderStatus, fBuildPathStatus });
	}
	
	
	/**
	 * Validates the build path.
	 */
	public void updateClassPathStatus() {
		fClassPathStatus.setOK();
		
		List elements= fClassPathList.getElements();
	
		CPListElement entryMissing= null;
		int nEntriesMissing= 0;
		IClasspathEntry[] entries= new IClasspathEntry[elements.size()];

		for (int i= elements.size()-1 ; i >= 0 ; i--) {
			CPListElement currElement= (CPListElement)elements.get(i);
			boolean isChecked= fClassPathList.isChecked(currElement);
			if (currElement.getEntryKind() == IClasspathEntry.CPE_SOURCE) {
				if (!isChecked) {
					fClassPathList.setCheckedWithoutUpdate(currElement, true);
				}
			} else {
				currElement.setExported(isChecked);
			}

			entries[i]= currElement.getClasspathEntry();
			if (currElement.isMissing()) {
				nEntriesMissing++;
				if (entryMissing == null) {
					entryMissing= currElement;
				}
			}
		}
				
		if (nEntriesMissing > 0) {
			if (nEntriesMissing == 1) {
				fClassPathStatus.setWarning(NewWizardMessages.getFormattedString("BuildPathsBlock.warning.EntryMissing", entryMissing.getPath().toString())); //$NON-NLS-1$
			} else {
				fClassPathStatus.setWarning(NewWizardMessages.getFormattedString("BuildPathsBlock.warning.EntriesMissing", String.valueOf(nEntriesMissing))); //$NON-NLS-1$
			}
		}
				
	

	}

	// ---------- util method ------------
		
	private IContainer chooseContainer() {
		Class[] acceptedClasses= new Class[] { IProject.class, IFolder.class };
		ISelectionStatusValidator validator= new TypedElementSelectionValidator(acceptedClasses, false);
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

		IResource initSelection= null;
		if (fOutputLocationPath != null) {
			initSelection= fWorkspaceRoot.findMember(fOutputLocationPath);
		}
		
		FolderSelectionDialog dialog= new FolderSelectionDialog(getShell(), lp, cp);
		dialog.setTitle(NewWizardMessages.getString("BuildPathsBlock.ChooseOutputFolderDialog.title")); //$NON-NLS-1$
		dialog.setValidator(validator);
		dialog.setMessage(NewWizardMessages.getString("BuildPathsBlock.ChooseOutputFolderDialog.description")); //$NON-NLS-1$
		dialog.addFilter(filter);
		dialog.setInput(fWorkspaceRoot);
		dialog.setInitialSelection(initSelection);
//		dialog.setSorter(new ResourceSorter(ResourceSorter.NAME));
		
		if (dialog.open() == FolderSelectionDialog.OK) {
			return (IContainer)dialog.getFirstResult();
		}
		return null;
	}
	
	// -------- tab switching ----------
	
	private void tabChanged(Widget widget) {
		if (widget instanceof TabItem) {
			TabItem tabItem= (TabItem) widget;
			BuildPathBasePage newPage= (BuildPathBasePage) tabItem.getData();
			if (fCurrPage != null) {
				List selection= fCurrPage.getSelection();
				if (!selection.isEmpty()) {
					newPage.setSelection(selection);
				}
			}
			fCurrPage= newPage;
			fPageIndex= tabItem.getParent().getSelectionIndex();
		}
	}
	
	
	

}