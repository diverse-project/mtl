/*
 * Package créé le 25 février 2004, debuggé et modifié le 27
 * Pamplemouss
 */

package org.inria.mtl.editors.outline;


import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.model.WorkbenchContentProvider;
import org.eclipse.ui.model.WorkbenchLabelProvider;
import org.eclipse.ui.views.contentoutline.ContentOutlinePage;

/**
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates. To enable and disable the creation of type comments
 * go to Window>Preferences>Java>Code Generation.
 */
public class MTLOutlinerPage extends ContentOutlinePage
{
	protected IDocument input;
	private Object fInput;

	public MTLOutlinerPage(IDocument input)
	{
		super();
		this.input = input;
		System.out.println("Outline lancée");
	}

	/**
	 * Creates the control and registers the popup menu for this outlinePage Menu id
	 * "org.eclipse.ui.examples.readmetool.outline"
	 */
	public void createControl(Composite parent)
	{
		super.createControl(parent);

		TreeViewer viewer = getTreeViewer();
		viewer.setContentProvider(new WorkbenchContentProvider());
		viewer.setLabelProvider(new WorkbenchLabelProvider());

		// Faire attention de placer l'entrée ici maintenant 
		viewer.setSorter(new MTLNameSorter());
	}

	private IAdaptable getContentOutline(IDocument input)
	{
		return MTLSyntaxModelFactory.getInstance().getContentOutline(input);
	}

	public void update()
	{
		getControl().setRedraw(false);
		TreeViewer viewer = getTreeViewer();
		
		Object[] expanded =  viewer.getExpandedElements();
		MTLElementList currentNodes = (MTLElementList) getContentOutline(input); 		
		viewer.setInput(currentNodes);

		/*Is automatically expanding the tree helpful? Should this be a preference?
		 * Or should we only expand those nodes that are already expanded?
		 */
		if(currentNodes.size() == 1) {
			getTreeViewer().expandAll();
		}
		
		
		//Attempt to determine which nodes are already expanded bearing in mind that the object is not the same.
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
	
	/**
	 * Sets the input of the outline page
	 * @param input The Input for the outline page
	 */
	public final void setInput(final Object input) {
		fInput = input;
		update();
	}
}
