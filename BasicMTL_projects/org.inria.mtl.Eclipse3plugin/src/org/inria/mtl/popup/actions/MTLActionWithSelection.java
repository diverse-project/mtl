/*
 * Created on 12 oct. 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package org.inria.mtl.popup.actions;

import org.eclipse.core.resources.IResource;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.StructuredSelection;

/**
 * @author edrezen
 * 
 * This abstract provides a template method for action that uses StructuredSelection objects.
 */

abstract public class MTLActionWithSelection extends MTLAction 
{
	////////////////////////////////////////////////////////////////////////////////
	// METHODS
	////////////////////////////////////////////////////////////////////////////////

	/** TEMPLATE METHOD (see GOF) */
	final public void run (IAction action) 
	{
		try {
			// we retrieve the current selection if it is a structured one.
			if (getSelection() instanceof StructuredSelection)
			{
				StructuredSelection currentSelection = (StructuredSelection)getSelection();

				// we loop over the selected items of the current selection.
				java.util.Iterator it = currentSelection.iterator();
				while (it.hasNext() )
				{
					// we process the loop item resource.
					ProcessResource ((IResource) it.next ());
				}
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}


	/** Primitive of the Template Method 'Run' 
	 * Note that it is abstract since there is no default behavior for it.
	 */
	abstract public void ProcessResource (IResource item) throws Exception; 
}
