/*
* $Id: MTLPerspective.java,v 1.3 2004-06-15 15:14:03 sdzale Exp $
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
		//layout.addShowViewShortcut(IPageLayout.ID_OUTLINE);
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
		//on récupère l'éditeur
		String editorArea = layout.getEditorArea();

		//		navigator
		IFolderLayout topLeft = layout.createFolder("topLeft", IPageLayout.LEFT, 0.20f, editorArea); //$NON-NLS-1$
		topLeft.addView(IPageLayout.ID_RES_NAV);
		topLeft.addView(IPageLayout.ID_BOOKMARKS);
		
		//		Outline
		IFolderLayout topRight =
					layout.createFolder(
						"topRight",
						IPageLayout.RIGHT,
						0.80f,
						editorArea);
				topRight.addView(IPageLayout.ID_OUTLINE);
				
		
		//		console at bottom
			  IFolderLayout bottom =
				  layout.createFolder(
					  "bottom",
					  IPageLayout.BOTTOM,
					  0.80f,
					  editorArea);
			  bottom.addView("org.inria.mtl.plugin.views.mtlconsoleview");
		
		   	bottom.addView(IPageLayout.ID_TASK_LIST);
		   	
		//		assistants
			layout.addNewWizardShortcut(
				"org.inria.plugin.mtl.wizard.MTLProjectCreationWizard");
			layout.addNewWizardShortcut(
				"org.inria.plugin.mtl.wizard.MTLFolderCreationWizard");
		    layout.addNewWizardShortcut(
						"org.inria.plugin.mtl.wizard.MTLFileCreationWizard");
		    
			
		
	}
        
    
}
