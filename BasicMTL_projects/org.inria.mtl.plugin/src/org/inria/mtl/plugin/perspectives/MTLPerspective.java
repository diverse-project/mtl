/*
* $Id: MTLPerspective.java,v 1.2 2004-05-19 09:22:52 sdzale Exp $
* Authors : ${user}
*
* Created on ${date}
* Copyright 2004 - INRIA - LGPL license
*/ 
package org.inria.mtl.plugin.perspectives;

import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

/**
 * Provides an Eclipse perspective for this plugin.
 * 
 * @author Serge DZALE
 */
public class MTLPerspective implements IPerspectiveFactory
{     
   /**
	* Creates the default initial layout for this plugin.  This method fufills
	* the contract for the IPerspectiveFactory interface
	* 
	* @param IPageLayout
	*/                      
	public void createInitialLayout(IPageLayout layout)
	{
		defineActions(layout);
		defineLayout(layout);
		
	}

   /** 
	* Define the actions and views you want to make available from the menus.
	* 
	* @param IPageLayout
	*/                      
	private void defineActions(IPageLayout layout)
	{
		//add new wizards
		layout.addNewWizardShortcut("org.eclipse.ui.wizards.new.folder");
		//layout.addNewWizardShortcut("org.eclipse.ui.wizards.new.file");
		

		// Add "show views".
		layout.addShowViewShortcut(IPageLayout.ID_RES_NAV);
		layout.addShowViewShortcut(IPageLayout.ID_BOOKMARKS);
		layout.addShowViewShortcut(IPageLayout.ID_OUTLINE);
		layout.addShowViewShortcut(IPageLayout.ID_PROP_SHEET);
		layout.addShowViewShortcut(IPageLayout.ID_TASK_LIST);
  
	}
    
   /** 
	* Controls the physical default layout of the perspective
	* 
	* @param IPageLayout
	*/                      
	private void defineLayout(IPageLayout layout)
	{
		// Hide the editor.  Note the user can always make it come back.  
		//layout.setEditorAreaVisible(true);
        
		String editorArea = layout.getEditorArea();

		IFolderLayout topLeft = layout.createFolder("topLeft", IPageLayout.LEFT, 0.25f,editorArea); //$NON-NLS-1$
		topLeft.addView(IPageLayout.ID_RES_NAV);
		topLeft.addView(IPageLayout.ID_BOOKMARKS);
		
		//bottom left
		IFolderLayout bottom = 
						 layout.createFolder("bottom", IPageLayout.BOTTOM, 0.75f,editorArea); //$NON-NLS-1$
		
	
		bottom.addView(IPageLayout.ID_TASK_LIST);
		bottom.addView(IPageLayout.ID_BOOKMARKS);
		//bottom.addView(IDebugUIConstants.);
		
		layout.addView(IPageLayout.ID_OUTLINE,
						IPageLayout.RIGHT,
						0.75f,
						editorArea);
			
		
	}
        
    
}
