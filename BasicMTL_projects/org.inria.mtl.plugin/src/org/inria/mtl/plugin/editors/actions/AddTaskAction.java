/*
* $Id: AddTaskAction.java,v 1.2 2004-05-19 09:21:02 sdzale Exp $
* Authors : ${user}
*
* Created on ${date}
* Copyright 2004 - INRIA - LGPL license
*/ 
package org.inria.mtl.plugin.editors.actions;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IWorkbenchSite;
import org.eclipse.ui.views.tasklist.TaskPropertiesDialog;

public class AddTaskAction extends SelectionDispatchAction {

	public AddTaskAction(IWorkbenchSite site) {
		super(site);
		setEnabled(false);
	//	WorkbenchHelp.setHelp(this, IJavaHelpContextIds.ADD_TASK_ACTION);
	}

	protected void selectionChanged(IStructuredSelection selection) {
		setEnabled(getElement(selection) != null);
	}

	protected void run(IStructuredSelection selection) {
		IResource resource= getElement(selection);
		if (resource == null)
			return;

		TaskPropertiesDialog dialog= new TaskPropertiesDialog(getShell());
		dialog.setResource(resource);
		dialog.open();
	}
	
	private IResource getElement(IStructuredSelection selection) {
		if (selection.size() != 1)
			return null;

		Object element= selection.getFirstElement();
		if (!(element instanceof IAdaptable))
			return null;
		return (IResource)((IAdaptable)element).getAdapter(IResource.class);
	}	
}
