/*
* $Id: MainClassChoicePage.java,v 1.2 2004-08-26 12:40:49 sdzale Exp $
* Authors : ${user}
*
* Created on ${date}
* Copyright 2004 - INRIA - LGPL license
*/ 
package org.inria.mtl.wizards.pages;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.QualifiedName;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.internal.ui.util.PixelConverter;
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
import org.inria.mtl.MTLPlugin;
import org.inria.mtl.builders.MTLModel;
import org.inria.mtl.wizards.BuildPathBasePage;


public class MainClassChoicePage extends BuildPathBasePage {
	
	private ListDialogField fMainClassPathList;
	
	private IProject fCurrProject;
	
	private IPath fProjPath;
	
	private CheckedListDialogField fMainClass;
	
	public MainClassChoicePage(ListDialogField classPathList) {
		fMainClassPathList= classPathList;
		
		MainClassListener listener= new MainClassListener();
		
		String[] buttonLabels= new String[] {};
		
		fMainClass= new CheckedListDialogField(null, buttonLabels, new CPListLabelProvider());
		fMainClass.setDialogFieldListener(listener);
		fMainClass.setLabelText(TabMessages.getString("MainClassPage.label")); //$NON-NLS-1$
//		fMainClass.setCheckAllButtonIndex(0);
//		fMainClass.setUncheckAllButtonIndex(1);
		
		fMainClass.setViewerSorter(new CPListElementSorter());
	}
	
	
	public void init(IJavaProject jproject) {
			fCurrProject= jproject.getProject();
			fProjPath= fCurrProject.getProject().getFullPath();	
			updateSourceFoldersList(fCurrProject);
			}
	
	public Control getControl(Composite parent) {
		PixelConverter converter= new PixelConverter(parent);
		
		Composite composite= new Composite(parent, SWT.NONE);
		
		LayoutUtil.doDefaultLayout(composite, new DialogField[] { fMainClass }, true);
		LayoutUtil.setHorizontalGrabbing(fMainClass.getListControl(null));

		int buttonBarWidth= converter.convertWidthInCharsToPixels(24);
		fMainClassPathList.setButtonsMinWidth(buttonBarWidth);
			
		return composite;
	}
	
	private void updateSourceFoldersList(IProject project) {
				ArrayList folders= new ArrayList();
				final List checkedFolder= new ArrayList();
				//		Find main class Folder
				
				//String strMainclass=MTLPlugin.getDefault().getModel(project).getMainClassFolder();
		
			try {
				String strMainclass = project.getPersistentProperty(new QualifiedName(MTLPlugin.PLUGIN_ID, MTLModel.MTL_MAINCLASS));
				List cpelements= fMainClassPathList.getElements();
				for (int i= 0; i < cpelements.size(); i++) {
					CPListElement cpe= (CPListElement)cpelements.get(i);
					if (cpe.getEntryKind() == IClasspathEntry.CPE_SOURCE) {
						folders.add(cpe);
						if (cpe.getPath().toString().equals(strMainclass)){
							checkedFolder.add(cpe);
						}
					}
				}
				fMainClass.setElements(folders);
				fMainClass.setCheckedElements(checkedFolder);
						
			} catch (Exception e) {
				// no solution exists or other problems: create an empty list
				fMainClass.setElements(new ArrayList(5));
			}
			fCurrProject= project;
		}		
		
	
	
	private class MainClassListener implements IDialogFieldListener {
			
			// ---------- IDialogFieldListener --------
	
			public void dialogFieldChanged(DialogField field) {
				if (fCurrProject != null) {
					// already initialized
					updateMainClass();
				}
			}
		}
	
		private void updateMainClass() {
			MTLPlugin.getDefault().getModel(fCurrProject).setMainClassFolder("");
			List projelements= fMainClass.getCheckedElements();
			if (projelements.size()>2){
				
			}else{
				for (int i= 0; i < projelements.size(); i++) {
					CPListElement cpe= (CPListElement)projelements.get(i);
					MTLPlugin.getDefault().getModel(fCurrProject).setMainClassFolder(cpe.getPath().toString());
					try{
						fCurrProject.setPersistentProperty(new QualifiedName(MTLPlugin.PLUGIN_ID, MTLModel.MTL_MAINCLASS), cpe.getPath().toString());
					}catch(Exception E){
						//
					}
				}
			}
		}
	
	
	/*
	 * @see BuildPathBasePage#getSelection
	 */
	public List getSelection() {
			return fMainClassPathList.getSelectedElements();
	}

	/*
	 * @see BuildPathBasePage#setSelection
	 */	
	public void setSelection(List selElements) {
		fMainClassPathList.selectElements(new StructuredSelection(selElements));
	}
			
	public boolean isEntryKind(int kind) {
		return kind == IClasspathEntry.CPE_SOURCE;
	}

	

}