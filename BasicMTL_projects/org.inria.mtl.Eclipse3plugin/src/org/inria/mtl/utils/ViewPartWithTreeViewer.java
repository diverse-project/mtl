/*
 * Created on 26 mai 2004
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package org.inria.mtl.utils;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;

/** This class extends ViewPart and add to it a TreeViewer.
 * Two actions in the menu are added : collapse all the tree and expand all the tree.
 */
public class ViewPartWithTreeViewer extends ViewPart 
{
	private PatchedTreeViewer treeViewer;
	
	/** */
	public PatchedTreeViewer getTreeViewer() 
	{
		return treeViewer;
	}

	/** */
	public void setTreeViewer(PatchedTreeViewer treeViewer) 
	{
		this.treeViewer = treeViewer;
	}

	/** */
	public void createPartControl (Composite parent) 
	{
		// we build the tree viewer
		setTreeViewer (new PatchedTreeViewer (parent));

		// we init the menu
		initMenu ();
		
		// we init the listener
		initListeners ();
	}

	/** */
	public void setFocus() 
	{
	}

	/** */
	protected void initMenu ()
	{
		IMenuManager menu = getViewSite().getActionBars().getMenuManager();
		
		menu.add (new Action() {
			public void run() 
			{
				getTreeViewer().expandAll();
			}
			public String getText() 
			{
				return "expand all";
			}
		});

		menu.add (new Action() {
			public void run() 
			{
				getTreeViewer().collapseAll();
			}
			public String getText() 
			{
				return "collapse all";
			}
		});
	}
	
	
	/** */
	protected void initListeners ()
	{
		getTreeViewer().addSelectionChangedListener(new ISelectionChangedListener() {
			public void selectionChanged(SelectionChangedEvent event) {
				handleSelectionChanged (event);
			}
		});
		
		getTreeViewer().addDoubleClickListener(new IDoubleClickListener() {
			public void doubleClick(DoubleClickEvent event) {
				handleDoubleClick (event);
			}
		});

		getTreeViewer().getControl().addKeyListener(new KeyListener() {
			public void keyPressed(KeyEvent event) {
				handleKeyPressed(event);
			}
			public void keyReleased(KeyEvent event) {
				handleKeyReleased(event);
			}
		});
	}


	/** */
	protected void handleKeyReleased(KeyEvent event) 
	{
	}

	/** */
	protected void handleKeyPressed(KeyEvent event) 
	{
	}

	/** */
	protected void handleDoubleClick (DoubleClickEvent event) 
	{
	}

	/** */
	protected void handleSelectionChanged(SelectionChangedEvent event) 
	{
		setCurrentSelection((IStructuredSelection) event.getSelection());
	}
	
	
	private IStructuredSelection currentSelection;
	
	/** */
	public IStructuredSelection getCurrentSelection() 
	{
		return currentSelection;
	}

	/** */
	public void setCurrentSelection(IStructuredSelection currentSelection) 
	{
		this.currentSelection = currentSelection;
	}
	
	
}
