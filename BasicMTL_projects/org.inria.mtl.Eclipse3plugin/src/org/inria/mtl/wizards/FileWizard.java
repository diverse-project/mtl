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
package org.inria.mtl.wizards;

import java.net.MalformedURLException;
import java.net.URL;

import org.eclipse.core.resources.IFile;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.ide.IDE;
import org.eclipse.ui.internal.ide.DialogUtil;
import org.eclipse.ui.wizards.newresource.BasicNewResourceWizard;
import org.inria.mtl.MTLPlugin;

/**
 * Standard workbench wizard that create a new file resource in the workspace.
 * <p>
 * This class may be instantiated and used without further configuration;
 * this class is not intended to be subclassed.
 * </p>
 * <p>
 * Example:
 * <pre>
 * IWorkbenchWizard wizard = new BasicNewFileResourceWizard();
 * wizard.init(workbench, selection);
 * WizardDialog dialog = new WizardDialog(shell, wizard);
 * dialog.open();
 * </pre>
 * During the call to <code>open</code>, the wizard dialog is presented to the
 * user. When the user hits Finish, a file resource at the user-specified
 * workspace path is created, the dialog closes, and the call to
 * <code>open</code> returns.
 * </p>
 */
public class FileWizard extends BasicNewResourceWizard {
	private WizardNewFilePage mainPage;
/**
 * Creates a wizard for creating a new file resource in the workspace.
 */
public FileWizard() {
	super();
}
/* (non-Javadoc)
 * Method declared on IWizard.
 */
public void addPages() {
	super.addPages();
	mainPage = new WizardNewFilePage("newFilePage1",  getSelection());//$NON-NLS-1$
	mainPage.setTitle(MTLImportMessages.getString("FileResource.pageTitle")); //$NON-NLS-1$
	mainPage.setDescription(MTLImportMessages.getString("FileResource.description")); //$NON-NLS-1$
	addPage(mainPage);
}
/* (non-Javadoc)
 * Method declared on IWorkbenchWizard.
 */
public void init(IWorkbench workbench, IStructuredSelection currentSelection) {
	super.init(workbench, currentSelection);
	setWindowTitle(MTLImportMessages.getString("FileResource.shellTitle")); //$NON-NLS-1$
	setNeedsProgressMonitor(true);
}
/* (non-Javadoc)
 * Method declared on BasicNewResourceWizard.
 */
protected void initializeDefaultPageImageDescriptor() {
	String iconPath = "icons/full/";//$NON-NLS-1$
	try {
		//URL installURL = Platform.getPlugin(PlatformUI.PLUGIN_ID).getDescriptor().getInstallURL();
		URL url = new URL(MTLPlugin.getBaseURL(), iconPath + "wizban/newfile_wiz.gif");//$NON-NLS-1$
		ImageDescriptor desc = ImageDescriptor.createFromURL(url);
		setDefaultPageImageDescriptor(desc);
	}
	catch (MalformedURLException e) {
		// Should not happen.  Ignore.
	}
}
/* (non-Javadoc)
 * Method declared on IWizard.
 */
public boolean performFinish() {
	IFile file = mainPage.createNewFile();
	if (file == null)
		return false;

	selectAndReveal(file);

	// Open editor on new file.
	IWorkbenchWindow dw = getWorkbench().getActiveWorkbenchWindow();
	try {
		if (dw != null) {
			IWorkbenchPage page = dw.getActivePage();
			if (page != null) {
				IDE.openEditor(page, file, true);
			}
		}
	} catch (PartInitException e) {
		DialogUtil.openError(
			dw.getShell(),
			MTLImportMessages.getString("FileResource.errorMessage"), //$NON-NLS-1$
			e.getMessage(),
			e);
	}
			
	return true;
}
}
