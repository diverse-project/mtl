/*
 * Package créé le 25 février 2004, debuggé et modifié le 27
 * Pamplemouss
 */

package org.inria.mtl.editors.outline;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.model.WorkbenchContentProvider;
import org.eclipse.ui.model.WorkbenchLabelProvider;
import org.eclipse.ui.texteditor.IDocumentProvider;
import org.eclipse.ui.views.contentoutline.ContentOutlinePage;

/**
 */
public class MTLOutlinerPage extends ContentOutlinePage
{
	IDocumentProvider provider;
	protected IDocument input;
	private Object fInput;

	public MTLOutlinerPage(IDocumentProvider provider)
	{
		super();
		this.provider = provider;
	}

	
	/** */
	public void setInput (IEditorInput editorInput) 
	{
		this.input = this.provider.getDocument (editorInput);
	}

	
	/**
	 * Creates the control and registers the popup menu for this outlinePage Menu id
	 * "org.eclipse.ui.examples.readmetool.outline"
	 */
	public void createControl (Composite parent)
	{
		super.createControl(parent);

		TreeViewer viewer = getTreeViewer();
		
		viewer.setContentProvider (new WorkbenchContentProvider());
		viewer.setLabelProvider   (new WorkbenchLabelProvider());
		viewer.setSorter          (new MTLNameSorter());
		
		viewer.addSelectionChangedListener (this);

		if (input != null)
		{
			update ();
		}
	}

	
	/** */
	private IAdaptable getContentOutline(IDocument input)
	{
		return MTLSyntaxModelFactory.getInstance().getContentOutline(input);
	}


	/** */
	public void update()
	{
		getControl().setRedraw(false);
		TreeViewer viewer = getTreeViewer();
		
		MTLElementList currentNodes = (MTLElementList) getContentOutline(input); 		
		viewer.setInput(currentNodes);

		getTreeViewer().expandAll();
		 
		//Attempt to determine which nodes are already expanded bearing in mind that the object is not the same.
		Object[] expanded =  viewer.getExpandedElements();
		for(int i= 0; i< expanded.length; i++)
		{
			MTLElement newExpandedNode = currentNodes.findEquivilent((MTLElement)expanded[i]);
			if(newExpandedNode != null)
			{
				viewer.setExpandedState(newExpandedNode, true);
			}
		}
		
		getControl().setRedraw(true);
	}
	
}
