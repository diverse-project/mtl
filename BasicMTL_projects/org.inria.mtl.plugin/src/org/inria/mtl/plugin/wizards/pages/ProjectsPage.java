/*
* $Id: ProjectsPage.java,v 1.4 2004-06-22 08:39:25 sdzale Exp $
* Authors : ${user}
*
* Created on ${date}
* Copyright 2004 - INRIA - LGPL license
*/ 
package org.inria.mtl.plugin.wizards.pages; 
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaModel;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.internal.ui.util.PixelConverter;
import org.eclipse.jdt.internal.ui.wizards.NewWizardMessages;
//import org.eclipse.jdt.internal.ui.wizards.buildpaths.BuildPathBasePage;
import org.eclipse.jdt.internal.ui.wizards.buildpaths.CPListElement;
import org.eclipse.jdt.internal.ui.wizards.buildpaths.CPListElementSorter;
import org.eclipse.jdt.internal.ui.wizards.dialogfields.CheckedListDialogField;
import org.eclipse.jdt.internal.ui.wizards.dialogfields.DialogField;
import org.eclipse.jdt.internal.ui.wizards.dialogfields.IDialogFieldListener;
import org.eclipse.jdt.internal.ui.wizards.dialogfields.LayoutUtil;
import org.eclipse.jdt.internal.ui.wizards.dialogfields.ListDialogField;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

import org.inria.mtl.plugin.builders.MTLNature;
import org.inria.mtl.plugin.wizards.BuildPathBasePage;




public class ProjectsPage extends BuildPathBasePage {
			
	private ListDialogField fClassPathList;
	private IJavaProject fCurrJProject;
	
	private CheckedListDialogField fProjectsList;
	
	public ProjectsPage(ListDialogField classPathList) {
		
		fClassPathList= classPathList;
				
		ProjectsListListener listener= new ProjectsListListener();
		
		String[] buttonLabels= new String[] {
			/* 0 */ NewWizardMessages.getString("ProjectsWorkbookPage.projects.checkall.button"), //$NON-NLS-1$
			/* 1 */ NewWizardMessages.getString("ProjectsWorkbookPage.projects.uncheckall.button") //$NON-NLS-1$
		};
		
		fProjectsList= new CheckedListDialogField(null, buttonLabels, new CPListLabelProvider());
		fProjectsList.setDialogFieldListener(listener);
		fProjectsList.setLabelText(NewWizardMessages.getString("ProjectsWorkbookPage.projects.label")); //$NON-NLS-1$
		fProjectsList.setCheckAllButtonIndex(0);
		fProjectsList.setUncheckAllButtonIndex(1);
		
		fProjectsList.setViewerSorter(new CPListElementSorter());
	}
	
	public void init(IJavaProject jproject) {
		updateProjectsList(jproject);
	}
		
	private void updateProjectsList(IJavaProject currJProject) {
		try {
			IJavaModel jmodel= currJProject.getJavaModel();
			IJavaProject[] jprojects= jmodel.getJavaProjects();
					
			// regarder si ces projets ont la nature MTL avant de les afficher
			
			List projects= new ArrayList(jprojects.length);
			
			// a vector remembering all projects that dont have to be added anymore
			List existingProjects= new ArrayList(jprojects.length);
			existingProjects.add(currJProject.getProject());
					
			final List checkedProjects= new ArrayList(jprojects.length);
			
			
			// add the projects-cpentries that are already on the class path
			List cpelements= fClassPathList.getElements();
			for (int i= cpelements.size() - 1 ; i >= 0; i--) {
				CPListElement cpelem= (CPListElement)cpelements.get(i);
				if (isEntryKind(cpelem.getEntryKind())) {
					existingProjects.add(cpelem.getResource());
					projects.add(cpelem);
					checkedProjects.add(cpelem);
				}
			}
			
			for (int i= 0; i < jprojects.length; i++) {
				IProject proj= jprojects[i].getProject();
				try{
				
				if ((!existingProjects.contains(proj)) && (proj.hasNature(MTLNature.NATURE_ID)))  {
					projects.add(new CPListElement(fCurrJProject, IClasspathEntry.CPE_PROJECT, proj.getFullPath(), proj));
					
					}
				}catch (Exception e){
					e.printStackTrace();
				}
			}	
			
			fProjectsList.setElements(projects);
			fProjectsList.setCheckedElements(checkedProjects);
				
		} catch (JavaModelException e) {
			// no solution exists or other problems: create an empty list
			fProjectsList.setElements(new ArrayList(5));
			
		}
		fCurrJProject= currJProject;
	}		
		
	// -------- UI creation ---------
		
	public Control getControl(Composite parent) {
		PixelConverter converter= new PixelConverter(parent);
		
		Composite composite= new Composite(parent, SWT.NONE);
			
		LayoutUtil.doDefaultLayout(composite, new DialogField[] { fProjectsList }, true);
		LayoutUtil.setHorizontalGrabbing(fProjectsList.getListControl(null));
		
		int buttonBarWidth= converter.convertWidthInCharsToPixels(24);
		fProjectsList.setButtonsMinWidth(buttonBarWidth);
				
		return composite;
	}
	
	private class ProjectsListListener implements IDialogFieldListener {
			
		// ---------- IDialogFieldListener --------
	
		public void dialogFieldChanged(DialogField field) {
			if (fCurrJProject != null) {
				// already initialized
				updateClasspathList();
			}
		}
	}
	
	private void updateClasspathList() {
		List projelements= fProjectsList.getCheckedElements();
				
		boolean remove= false;
		List cpelements= fClassPathList.getElements();
		// backwards, as entries will be deleted
		for (int i= cpelements.size() -1; i >= 0 ; i--) {
			CPListElement cpe= (CPListElement)cpelements.get(i);
			if (isEntryKind(cpe.getEntryKind())) {
				if (!projelements.remove(cpe)) {
					cpelements.remove(i);
					remove= true;
				}	
			}
		}
		for (int i= 0; i < projelements.size(); i++) {
			cpelements.add(projelements.get(i));
			}
		if (remove || (projelements.size() > 0)) {
			fClassPathList.setElements(cpelements);
		}
	}
	
	/*
	 * @see BuildPathBasePage#getSelection
	 */
	public List getSelection() {
		return fProjectsList.getSelectedElements();
	}

	/*
	 * @see BuildPathBasePage#setSelection
	 */	
	public void setSelection(List selElements) {
		fProjectsList.selectElements(new StructuredSelection(selElements));
	}

	/* (non-Javadoc)
		 * @see org.eclipse.jdt.internal.ui.wizards.buildpaths.BuildPathBasePage#isEntryKind(int)
		 */
		public boolean isEntryKind(int kind) {
				return kind == IClasspathEntry.CPE_PROJECT;
		}


}