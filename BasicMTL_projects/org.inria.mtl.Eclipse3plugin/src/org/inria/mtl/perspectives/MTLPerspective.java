/*
* $Id: MTLPerspective.java,v 1.3 2005-02-24 16:43:52 dvojtise Exp $
* Authors : sdzale, dvojtise
*
* Created on ${date}
* Copyright 2004 - INRIA - LGPL license
*/ 
package org.inria.mtl.perspectives;

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
		//layout.addNewWizardShortcut("org.eclipse.ui.wizards.new.folder");
		//layout.addNewWizardShortcut("org.eclipse.ui.wizards.new.file");
		

		// Add "show views".
//		layout.addShowViewShortcut(IPageLayout.ID_RES_NAV);
//		layout.addShowViewShortcut(IPageLayout.ID_BOOKMARKS);
//		layout.addShowViewShortcut(IPageLayout.ID_PROP_SHEET);
//		layout.addShowViewShortcut(IPageLayout.ID_TASK_LIST);
		//layout.addShowViewShortcut(IPageLayout.);
		
//		 Add "new wizards".
//		layout.addNewWizardShortcut("org.eclipse.ui.wizards.new.folder");//$NON-NLS-1$
//		layout.addNewWizardShortcut("org.eclipse.ui.wizards.new.file");//$NON-NLS-1$

		// Add "show views".
		layout.addShowViewShortcut(IPageLayout.ID_RES_NAV);
		layout.addShowViewShortcut(IPageLayout.ID_BOOKMARKS);
		layout.addShowViewShortcut(IPageLayout.ID_OUTLINE);
		layout.addShowViewShortcut(IPageLayout.ID_PROP_SHEET);
		layout.addShowViewShortcut(IPageLayout.ID_TASK_LIST);
		
//		layout.addActionSet(IPageLayout.ID_NAVIGATE_ACTION_SET);
  
	}
    
   /** 
	* Controls the physical default layout of the perspective
	* 
	* @param IPageLayout
	* Note: we can do the same adding view to the plugin.xml definition 
	* ( in <extension point="org.eclipse.ui.perspectiveExtensions"> <perspectiveExtension targetID="org.inria.mtl.MTLPerspective">)
	*/                      
	private void defineLayout(IPageLayout layout)
	{
		//on récupère l'éditeur
		String editorArea = layout.getEditorArea();
		
//		 Top left.
		IFolderLayout topLeft = layout.createFolder("topLeft", IPageLayout.LEFT, (float)0.26, editorArea);//$NON-NLS-1$
		topLeft.addView(IPageLayout.ID_RES_NAV);
		topLeft.addPlaceholder(IPageLayout.ID_BOOKMARKS);

		// Bottom left.
		IFolderLayout bottomLeft = layout.createFolder("bottomLeft", IPageLayout.BOTTOM, (float)0.50,//$NON-NLS-1$
			"topLeft");//$NON-NLS-1$
		bottomLeft.addView(IPageLayout.ID_OUTLINE);
		bottomLeft.addPlaceholder("org.inria.mtl.views.projectexplorer.ProjectExplorerView");
									
		// Bottom right.
		//layout.addView(IPageLayout.ID_TASK_LIST, IPageLayout.BOTTOM, (float)0.66, editorArea);

		//		console at bottom
			  IFolderLayout bottom =
				  layout.createFolder(
					  "bottom",
					  IPageLayout.BOTTOM,
					  (float)0.66,
					  editorArea);
			  bottom.addView("org.inria.mtl.views.mtlconsoleview");
		
		   	bottom.addView(IPageLayout.ID_TASK_LIST);
		   	bottom.addView(IPageLayout.ID_PROBLEM_VIEW);
		   	
		//		assistants
			layout.addNewWizardShortcut(
				"org.inria.mtl.wizard.MTLProjectCreationWizard");
			layout.addNewWizardShortcut(
				"org.inria.mtl.wizard.MTLFolderCreationWizard");
		    layout.addNewWizardShortcut(
						"org.inria.mtl.wizard.MTLFileCreationWizard");
		    
			
		
	}
        
    
}
