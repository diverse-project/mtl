/*
 * $Id: NewProjectWizardAction.java,v 1.2 2004-08-26 12:40:15 sdzale Exp $
 * 
 * Licence LGPL - Inria 
 */
package org.inria.mtl.actions;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;
import org.eclipse.ui.IWorkbenchWizard;
import org.inria.mtl.wizards.NewProjectWizard;

/**
 * Insert the type's description here.
 * @see IWorkbenchWindowActionDelegate
 */
public class NewProjectWizardAction implements IWorkbenchWindowActionDelegate {

	private ISelection selection;
	private IStructuredSelection sselection;
	private IWorkbenchWindow window;

	/**
	 * The constructor.
	 */
	public NewProjectWizardAction() {
	}

	/**
	 * Insert the method's description here.
	 * @see IWorkbenchWindowActionDelegate#run
	 */
	public void run(IAction action) {
		NewProjectWizard wizard = new NewProjectWizard();

		if (wizard instanceof IWorkbenchWizard)
			((IWorkbenchWizard) wizard).init(window.getWorkbench(), sselection);
		WizardDialog dialog = new WizardDialog(window.getShell(), wizard);
		dialog.create();
		dialog.getShell().setText("New MTL Project");
		dialog.open();

	}

	/**
	 * Insert the method's description here.
	 * @see IWorkbenchWindowActionDelegate#selectionChanged
	 */
	public void selectionChanged(IAction action, ISelection selection) {
		this.selection = selection;
		if (selection instanceof IStructuredSelection)
			sselection = (IStructuredSelection) selection;
	}

	/**
	 * Insert the method's description here.
	 * @see IWorkbenchWindowActionDelegate#dispose
	 */
	public void dispose() {
	}

	/**
	 * Insert the method's description here.
	 * @see IWorkbenchWindowActionDelegate#init
	 */
	public void init(IWorkbenchWindow window) {
		this.window = window;
	}
}
