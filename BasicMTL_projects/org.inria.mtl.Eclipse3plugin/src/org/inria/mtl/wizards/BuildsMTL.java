/**
* $Id: BuildsMTL.java,v 1.4 2005-02-24 16:43:55 dvojtise Exp $
* Authors : sdzale, dvojtise
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
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.internal.ui.IJavaHelpContextIds;
import org.eclipse.jdt.internal.ui.JavaPlugin;
import org.eclipse.jdt.internal.ui.JavaPluginImages;
import org.eclipse.jdt.internal.ui.dialogs.StatusInfo;
import org.eclipse.jdt.internal.ui.dialogs.StatusUtil;
import org.eclipse.jdt.internal.ui.util.CoreUtility;
import org.eclipse.jdt.internal.ui.util.PixelConverter;
import org.eclipse.jdt.internal.ui.util.TabFolderLayout;
import org.eclipse.jdt.internal.ui.viewsupport.ImageDisposer;
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
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Widget;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.dialogs.ISelectionStatusValidator;
import org.eclipse.ui.help.WorkbenchHelp;
import org.eclipse.ui.model.WorkbenchContentProvider;
import org.eclipse.ui.model.WorkbenchLabelProvider;
import org.inria.mtl.MTLPlugin;
import org.inria.mtl.builders.MTLModel;
import org.inria.mtl.core.MTLCore;
import org.inria.mtl.core.MtlClasspathEntry;
import org.inria.mtl.preferences.PreferencesConstants;
import org.inria.mtl.wizards.pages.ClasspathOrderingWorkbookPage;
import org.inria.mtl.wizards.pages.LibrairiesPage;
import org.inria.mtl.wizards.pages.ProjectsPage;
import org.inria.mtl.wizards.pages.SourcesPage;

public class BuildsMTL {

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
	private IPath fOutputTllLocationPath;
	
	private IStatusChangeListener fContext;
	private Control fSWTWidget;	
	
	private int fPageIndex;
	
	private SourcesPage fSourceContainerPage;
	private ProjectsPage fProjectsPage;
	private LibrairiesPage fLibrariesPage;
		
	private BuildPathBasePage fCurrPage;
	
	public static final String SOURCEATTACHMENT= "sourcepath"; //$NON-NLS-1$
	public static final String SOURCEATTACHMENTROOT= "rootpath"; //$NON-NLS-1$
	public static final String OUTPUT= "output"; //$NON-NLS-1$
	public static final String EXCLUSION= "exclusion"; //$NON-NLS-1$

		
	public BuildsMTL(IStatusChangeListener context, int pageToShow) {
		fWorkspaceRoot= MTLPlugin.getWorkspace().getRoot();
		fContext= context;
		
		fPageIndex= pageToShow;
		
		
		fSourceContainerPage= null;
		fLibrariesPage= null;
		fProjectsPage= null;
			
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
		
				
		fSourceContainerPage= new SourcesPage(fWorkspaceRoot, fClassPathList);		
		item= new TabItem(folder, SWT.NONE);
		item.setText(NewWizardMessages.getString("BuildPathsBlock.tab.source")); //$NON-NLS-1$
		item.setImage(imageRegistry.get(JavaPluginImages.IMG_OBJS_PACKFRAG_ROOT));
		item.setData(fSourceContainerPage);		
		item.setControl(fSourceContainerPage.getControl(folder));
		
				
		IWorkbench workbench= MTLPlugin.getDefault().getWorkbench();	
		Image projectImage= workbench.getSharedImages().getImage(org.eclipse.ui.ide.IDE.SharedImages.IMG_OBJ_PROJECT);
		
		fProjectsPage= new ProjectsPage(fClassPathList);		
		item= new TabItem(folder, SWT.NONE);
		item.setText(NewWizardMessages.getString("BuildPathsBlock.tab.projects")); //$NON-NLS-1$
		item.setImage(projectImage);
		item.setData(fProjectsPage);
		item.setControl(fProjectsPage.getControl(folder));
		
		fLibrariesPage= new LibrairiesPage(fWorkspaceRoot, fClassPathList);		
		item= new TabItem(folder, SWT.NONE);
		item.setText(NewWizardMessages.getString("BuildPathsBlock.tab.libraries")); //$NON-NLS-1$
		item.setImage(imageRegistry.get(JavaPluginImages.IMG_OBJS_LIBRARY));
		item.setData(fLibrariesPage);
		item.setControl(fLibrariesPage.getControl(folder));
		
		// a non shared image
		Image cpoImage= JavaPluginImages.DESC_TOOL_CLASSPATH_ORDER.createImage();
		composite.addDisposeListener(new ImageDisposer(cpoImage));	
		
		ClasspathOrderingWorkbookPage ordpage= new ClasspathOrderingWorkbookPage(fClassPathList);		
		item= new TabItem(folder, SWT.NONE);
		item.setText("Order"); //$NON-NLS-1$
		item.setImage(cpoImage);
		item.setData(ordpage);
		item.setControl(ordpage.getControl(folder));
				
		if (fCurrJProject != null) {
			fSourceContainerPage.init(fCurrJProject);
			fLibrariesPage.init(fCurrJProject);
			fProjectsPage.init(fCurrJProject);
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
//				if (outputLocation == null) {
//					outputLocation=  fCurrJProject.getOutputLocation();
//				}
				if (classpathEntries == null) {
					MTLCore.setProject(MTLModel.getProject());
					classpathEntries=  MTLCore.readClasspathFile();
				}
			}
			if (classpathEntries != null) {
					newClassPath= getExistingEntries(classpathEntries);
			}
		} catch (Exception e) {
			MTLPlugin.log(e);
		}
	//	if (newClassPath == null) {
	//		newClassPath= (List)MTLCore.defaultClasspath();
	//	}
		
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
		
		if (fSourceContainerPage != null) {
			fSourceContainerPage.init(fCurrJProject);
			fProjectsPage.init(fCurrJProject);
			fLibrariesPage.init(fCurrJProject);
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
	public IMtlJavaProject getMtlProject() {
		return fCurrMtlProject;
	}
	
	public IJavaProject getProject() {
			return fCurrJProject;
		}
		
	/**
	 * Returns the current class path (raw). Note that the entries returned must not be valid.
	 */	
	public IClasspathEntry[] getRawClassPath() {
		List elements=  fClassPathList.getElements();
		int nElements= elements.size();
		IClasspathEntry[] entries= new IClasspathEntry[elements.size()];

		for (int i= 0; i < nElements; i++) {
			CPListElement currElement= (CPListElement) elements.get(i);
			entries[i]= currElement.getClasspathEntry();
		}
		return entries;
	}
	
	public int getPageIndex() {
		return fPageIndex;
	}
	
	
	// -------- evaluate default settings --------
	
	private List getDefaultClassPath() {
		List list= new ArrayList();
		IFolder srcFolder;
		IProject proj =getProject().getProject();
		IPreferenceStore store= PreferencesConstants.getPreferenceStore();
		String sourceFolderName= store.getString(PreferencesConstants.FMTL_SRCNAME); 
		srcFolder= proj.getFolder(sourceFolderName);
		list.add(new CPListElement(getProject(), IClasspathEntry.CPE_SOURCE, srcFolder.getFullPath(), srcFolder));

		return list;
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
				
/*		if (fCurrJProject.hasClasspathCycle(entries)) {
			fClassPathStatus.setWarning(NewWizardMessages.getString("BuildPathsBlock.warning.CycleInClassPath")); //$NON-NLS-1$
		}
		
*/		

	}

	/**
	 * Validates output location & build path.
	 */	
	
	// -------- creation -------------------------------
	
	public static void createProject(IProject project, IPath locationPath, IProgressMonitor monitor) throws CoreException {
		if (monitor == null) {
			monitor= new NullProgressMonitor();
		}				
		monitor.beginTask(NewWizardMessages.getString("BuildPathsBlock.operationdesc_project"), 10); //$NON-NLS-1$

		// create the project
		try {
			if (!project.exists()) {
				IProjectDescription desc= project.getWorkspace().newProjectDescription(project.getName());
				if (Platform.getLocation().equals(locationPath)) {
					locationPath= null;
				}
				desc.setLocation(locationPath);
				project.create(desc, monitor);
				monitor= null;
			}
			if (!project.isOpen()) {
				project.open(monitor);
				monitor= null;
			}
		} finally {
			if (monitor != null) {
				monitor.done();
			}
		}
	}

	public static void addJavaNature(IProject project, IProgressMonitor monitor) throws CoreException {
		if (!project.hasNature(JavaCore.NATURE_ID)) {
			IProjectDescription description = project.getDescription();
			String[] prevNatures= description.getNatureIds();
			String[] newNatures= new String[prevNatures.length + 1];
			System.arraycopy(prevNatures, 0, newNatures, 0, prevNatures.length);
			newNatures[prevNatures.length]= JavaCore.NATURE_ID;
			description.setNatureIds(newNatures);
			project.setDescription(description, monitor);
		} else {
			monitor.worked(1);
		}
	}
	
	public void configureMTLProject(IProgressMonitor monitor) throws CoreException, InterruptedException {
		if (monitor == null) {
			monitor= new NullProgressMonitor();
		}				
		monitor.beginTask(NewWizardMessages.getString("BuildPathsBlock.operationdesc_java"), 10); //$NON-NLS-1$

		try {
			Shell shell= null;
			if (fSWTWidget != null && !fSWTWidget.getShell().isDisposed()) {
				shell= fSWTWidget.getShell();
			}
				internalConfigureMTLProject(fClassPathList.getElements(), shell, monitor);
		} finally {
			monitor.done();
		}
	}
	
	/**
	 * Creates the MTL project and sets the configured build path and output location.
	 * If the project already exists only build paths are updated.
	 */
	private void internalConfigureMTLProject(List classPathEntries , Shell shell, IProgressMonitor monitor) throws CoreException, InterruptedException {
		// 10 monitor steps to go
		
		IRemoveOldBinariesQuery reorgQuery= null;
		if (shell != null) {
			reorgQuery= getRemoveOldBinariesQuery(shell);
		}

					
		int nEntries= classPathEntries.size();
		IClasspathEntry[] classpath= new IClasspathEntry[nEntries];
		IClasspathEntry[] newcpe1 = new IClasspathEntry[1] ;

		IPath outputLocation=null;
		IPath outputTllLocation=null;
		
		// create and set the class path
		for (int i= 0; i < nEntries; i++) {
			CPListElement entry= ((CPListElement)classPathEntries.get(i));
			
			IResource res= entry.getResource();
			if ((res instanceof IFolder) && !res.exists()) {
				CoreUtility.createFolder((IFolder)res, true, true, null);
			}
			if (entry.getEntryKind() == IClasspathEntry.CPE_SOURCE) {
					classpath[i]=MTLCore.newSourceEntry(entry.getPath());
			}
			else if(entry.getEntryKind() == MtlClasspathEntry.K_OUTPUT){
				outputLocation = entry.getPath();
				fOutputLocationPath = outputLocation;
			}
			else if(entry.getEntryKind() == MtlClasspathEntry.K_OUTPUT_TLL){
				outputTllLocation = entry.getPath();
				fOutputTllLocationPath = outputTllLocation;
			}
			else {			
			classpath[i]= findClasspathEntry(entry);
			}
						
			
		}
		boolean bol=MTLCore.saveClasspath(classpath,outputLocation, outputTllLocation);
		
 
		monitor.worked(1);
				
	}
	
	private IClasspathEntry findClasspathEntry(CPListElement entry) {
	switch (entry.getEntryKind()) {
		case IClasspathEntry.CPE_SOURCE:
			IPath outputLocation= (IPath) entry.getAttribute(OUTPUT);
			IPath[] exclusionPattern= (IPath[]) entry.getAttribute(EXCLUSION);
			return MTLCore.newSourceEntry(entry.getPath(), exclusionPattern, outputLocation);
		case IClasspathEntry.CPE_LIBRARY:
			IPath attach= (IPath) entry.getAttribute(SOURCEATTACHMENT);
			return MTLCore.newLibraryEntry(entry.getPath(), attach, null, entry.isExported());
		case IClasspathEntry.CPE_PROJECT:
			//Insert the project in the Java Builder Classpath
			//JavaCore.newProjectEntry(entry.getPath(), entry.isExported());
			//JavaCore.run();
			return MTLCore.newProjectEntry(entry.getPath(), entry.isExported());
		case MtlClasspathEntry.K_COMP:
			return MTLCore.newContainerEntry(entry.getPath());
		default:
			return null;
	}
}
	
	
	
	public static boolean hasClassfiles(IResource resource) throws CoreException {
		if (resource.isDerived()) { //$NON-NLS-1$
			return true;
		}		
		if (resource instanceof IContainer) {
			IResource[] members= ((IContainer) resource).members();
			for (int i= 0; i < members.length; i++) {
				if (hasClassfiles(members[i])) {
					return true;
				}
			}
		}
		return false;
	}
	

	public static void removeOldTLLfiles(IResource resource) throws CoreException {
		if (resource.isDerived()) {
			resource.delete(false, null);
		} else if (resource instanceof IContainer) {
			IResource[] members= ((IContainer) resource).members();
			for (int i= 0; i < members.length; i++) {
				removeOldTLLfiles(members[i]);
			}
		}
	}
	
	public static IRemoveOldBinariesQuery getRemoveOldBinariesQuery(final Shell shell) {
		return new IRemoveOldBinariesQuery() {
			public boolean doQuery(final IPath oldOutputLocation) throws InterruptedException {
				final int[] res= new int[] { 1 };
				shell.getDisplay().syncExec(new Runnable() {
					public void run() {
						String title= NewWizardMessages.getString("BuildPathsBlock.RemoveBinariesDialog.title"); //$NON-NLS-1$
						String message= NewWizardMessages.getFormattedString("BuildPathsBlock.RemoveBinariesDialog.description", oldOutputLocation.toString()); //$NON-NLS-1$
						MessageDialog dialog= new MessageDialog(shell, title, null, message, MessageDialog.QUESTION, new String[] { IDialogConstants.YES_LABEL, IDialogConstants.NO_LABEL, IDialogConstants.CANCEL_LABEL }, 2);
						res[0]= dialog.open();
					}
				});
				if (res[0] == 0) {
					return true;
				} else if (res[0] == 1) {
					return false;
				}
				throw new InterruptedException();
			}
		};
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
	
	private void test() {
		
			
	}
	

}