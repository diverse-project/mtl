/*
 * Created on 12 oct. 2004
 *
 */
package org.inria.mtl.popup.actions;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IActionDelegate;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;

/**
 * @author edrezen
 *
 * Abstract class that encapsulates some selection management.
 */
 abstract public class MTLAction implements IObjectActionDelegate 
{
	////////////////////////////////////////////////////////////////////////////////
	// ATTRIBUTES
	////////////////////////////////////////////////////////////////////////////////
	private ISelection selection = null;


	////////////////////////////////////////////////////////////////////////////////
	// GETTERS AND SETTERS
	////////////////////////////////////////////////////////////////////////////////
	public ISelection getSelection() {
		return selection;
	}
	
	public void setSelection(ISelection selection) {
		this.selection = selection;
	}

	
	////////////////////////////////////////////////////////////////////////////////
	// ABSTRACT METHODS TO BE IMPLEMENTED
	////////////////////////////////////////////////////////////////////////////////

	/** @see org.eclipse.ui.IActionDelegate#run(org.eclipse.jface.action.IAction) */
	abstract public void run (IAction action); 

	
	////////////////////////////////////////////////////////////////////////////////
	// METHODS WITH DEFAULT BEHAVIOR
	////////////////////////////////////////////////////////////////////////////////

	/**
	 * @see IObjectActionDelegate#setActivePart(IAction, IWorkbenchPart)
	 */
	public void setActivePart (IAction action, IWorkbenchPart targetPart) 
	{
		// by default, do nothing
	}

	/**
	 * @see IActionDelegate#selectionChanged(IAction, ISelection)
	 */
	public void selectionChanged (IAction action, ISelection selection) 
	{
		// we remember the selection when it is changed.
		setSelection (selection);
	}
}
