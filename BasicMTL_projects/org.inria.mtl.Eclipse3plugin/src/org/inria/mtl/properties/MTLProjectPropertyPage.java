/*
* $Id: MTLProjectPropertyPage.java,v 1.3 2004-08-31 13:46:09 sdzale Exp $
* Authors : ${user}
*
* Created on ${date}
* Copyright 2004 - INRIA - LGPL license
*/ 
package org.inria.mtl.properties;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.internal.ui.JavaPlugin;
import org.eclipse.jdt.internal.ui.dialogs.StatusUtil;
import org.eclipse.jdt.internal.ui.util.ExceptionHandler;
import org.eclipse.jdt.internal.ui.wizards.IStatusChangeListener;
import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.actions.WorkspaceModifyDelegatingOperation;
import org.eclipse.ui.dialogs.PropertyPage;
import org.inria.mtl.MTLPlugin;
import org.inria.mtl.core.MTLCore;
import org.inria.mtl.preferences.MTLMessages;
import org.inria.mtl.wizards.BuildsMTL;

/**
  * Property page for configuring the MTL build path
 */
public class MTLProjectPropertyPage extends PropertyPage implements IStatusChangeListener {
		
		
	private static final String PAGE_SETTINGS= "MTLPropertyPage"; //$NON-NLS-1$
	private static final String INDEX= "pageIndex"; //$NON-NLS-1$
		
	private BuildsMTL fBuildPathsBlock;
	
	/*
	 * @see PreferencePage#createControl(Composite)
	 */
	protected Control createContents(Composite parent) {
		//WorkbenchHelp.setHelp(parent, IJavaHelpContextIds.BUILD_PATH_PROPERTY_PAGE);

		// ensure the page has no special buttons
		noDefaultAndApplyButton();		
		
		IProject project= getProject();
		MTLCore.setProject(project);
		MTLPlugin.instance().getModel(project).setProject(project);
	//	MTLModel.setProject(project);
		
		System.out.println("Prop :"+project.getName());
		if (project == null || !isJavaProject(project)) {
			return createWithoutJava(parent);
		} else if (!project.isOpen()) {
			return createForClosedProject(parent);
		} else {
			return createWithJava(parent, project);
		}
	}
	
	private IDialogSettings getSettings() {
		IDialogSettings mtlSettings= MTLPlugin.getDefault().getDialogSettings();
		IDialogSettings pageSettings= mtlSettings.getSection(PAGE_SETTINGS);
		if (pageSettings == null) {
			pageSettings= mtlSettings.addNewSection(PAGE_SETTINGS);
			pageSettings.put(INDEX, 3);
		}
		return pageSettings;
	}
	
	
	/**
	 * Content for valid projects.
	 */
	private Control createWithJava(Composite parent, IProject project) {
		IJavaProject proj=JavaCore.create(project);
		fBuildPathsBlock= new BuildsMTL(this, getSettings().getInt(INDEX));
		fBuildPathsBlock.init(proj, null, null);
		return fBuildPathsBlock.createControl(parent);
	}

	/**
	 * Content for non-Java projects.
	 */	
	private Control createWithoutJava(Composite parent) {
		Label label= new Label(parent, SWT.LEFT);
		label.setText(MTLMessages.getString("BuildPathsPropertyPage.no_java_project.message")); //$NON-NLS-1$
		
		fBuildPathsBlock= null;
		setValid(true);
		return label;
	}

	/**
	 * Content for closed projects.
	 */		
	private Control createForClosedProject(Composite parent) {
		Label label= new Label(parent, SWT.LEFT);
		label.setText(MTLMessages.getString("BuildPathsPropertyPage.closed_project.message")); //$NON-NLS-1$
		
		fBuildPathsBlock= null;
		setValid(true);
		return label;
	}
	
	private IProject getProject() {
		IAdaptable adaptable= getElement();
		if (adaptable != null) {
			IJavaElement elem= (IJavaElement) adaptable.getAdapter(IJavaElement.class);
			if (elem instanceof IJavaProject) {
				return ((IJavaProject) elem).getProject();
			}
		}
		return null;
	}
	
	private boolean isJavaProject(IProject proj) {
		try {
			return proj.hasNature(JavaCore.NATURE_ID);
		} catch (CoreException e) {
			JavaPlugin.log(e);
		}
		return false;
	}
	
	/*
	 * @see IPreferencePage#performOk
	 */
	public boolean performOk() {
		if (fBuildPathsBlock != null) {
			getSettings().put(INDEX, fBuildPathsBlock.getPageIndex());
			
			Shell shell= getControl().getShell();
			IRunnableWithProgress runnable= new IRunnableWithProgress() {
				public void run(IProgressMonitor monitor)	throws InvocationTargetException, InterruptedException {
					try {
				fBuildPathsBlock.configureMTLProject(monitor);
					} catch (CoreException e) {
						throw new InvocationTargetException(e);
					} 
				}
			};
			IRunnableWithProgress op= new WorkspaceModifyDelegatingOperation(runnable);
			try {
				new ProgressMonitorDialog(shell).run(false, true, op);
			} catch (InvocationTargetException e) {
				String title= MTLMessages.getString("BuildPathsPropertyPage.error.title"); //$NON-NLS-1$
				String message= MTLMessages.getString("BuildPathsPropertyPage.error.message"); //$NON-NLS-1$
				ExceptionHandler.handle(e, shell, title, message);
				return false;
			} catch (InterruptedException e) {
				// cancelled
				return false;
			}
		}
		return true;
	}
	
	/**
	 * @see IStatusChangeListener#statusChanged
	 */
	public void statusChanged(IStatus status) {
		setValid(!status.matches(IStatus.ERROR));
		StatusUtil.applyToStatusLine(this, status);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.preference.IPreferencePage#performCancel()
	 */
	public boolean performCancel() {
		if (fBuildPathsBlock != null) {
			getSettings().put(INDEX, fBuildPathsBlock.getPageIndex());
		}
		return super.performCancel();
	}

}