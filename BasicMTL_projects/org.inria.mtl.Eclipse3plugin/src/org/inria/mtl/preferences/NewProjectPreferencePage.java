/*
* $Id: NewProjectPreferencePage.java,v 1.1 2004-07-30 14:09:35 sdzale Exp $
* Authors : ${user}
*
* Created on ${date}
* Copyright 2004 - INRIA - LGPL license
*/ 
package org.inria.mtl.preferences;

import java.util.ArrayList;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jdt.internal.ui.dialogs.StatusUtil;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Widget;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;
import org.inria.mtl.MTLPlugin;


public class NewProjectPreferencePage extends PreferencePage implements IWorkbenchPreferencePage {
	private static final String OUTPUT_BUILDNAME= PreferencesConstants.OUTPUT_BUILDNAME;
	private static final String JAVA_SRCNAME= PreferencesConstants.FJAVA_SRCNAME;
	private static final String JAVA_BINNAME= PreferencesConstants.FJAVA_BINNAME;
	private static final String MTL_BINNAME= PreferencesConstants.FMTL_BINNAME;
	private static final String MTL_SRCNAME= PreferencesConstants.FMTL_SRCNAME;
	private static final String MODEL_NAME= PreferencesConstants.MODEL_NAME;
	private static final String METAMODEL_NAME= PreferencesConstants.METAMODEL_NAME;


	
	private ArrayList fCheckBoxes;
	private ArrayList fRadioButtons;
	private ArrayList fTextControls;
	
	private SelectionListener fSelectionListener;
	private ModifyListener fModifyListener;
	
	private Text fJavaBinFolderNameText;
	private Text fJavaSrcFolderNameText;
	private Text fMtlBinFolderNameText;
	private Text fMtlSrcFolderNameText;
	private Text fOutputFolderNameText;
	private Text fModelFolderNameText;
	private Text fMetaModelFolderNameText;

	private Combo fJRECombo;

	private Button fProjectAsSourceFolder;
	private Button fFoldersAsSourceFolder;

	private Label fOutputFolderNameLabel;
	private Label fJavaBinFolderNameLabel;
	private Label fJavaSrcFolderNameLabel;
	private Label fMtlSrcFolderNameLabel;
	private Label fMtlBinFolderNameLabel;
	private Label fModelFolderNameLabel;
	private Label fMetaModelFolderNameLabel;


	public NewProjectPreferencePage() {
		super();
		setPreferenceStore(org.inria.mtl.MTLPlugin.getDefault().getPreferenceStore());
		setDescription(MTLMessages.getString("NewProjectPreferencePage.description")); //$NON-NLS-1$
	
		fRadioButtons= new ArrayList();
		fCheckBoxes= new ArrayList();
		fTextControls= new ArrayList();
		
		fSelectionListener= new SelectionListener() {
		public void widgetDefaultSelected(SelectionEvent e) {}

		public void widgetSelected(SelectionEvent e) {
				//controlChanged(e.widget);
			}
		};
		
		fModifyListener= new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				controlModified(e.widget);
			}
		};
		
	}

	public static void initDefaults(IPreferenceStore store) {
		store.setDefault(OUTPUT_BUILDNAME, store.getString(PreferencesConstants.OUTPUT_BUILDNAME)); //$NON-NLS-1$
		store.setDefault(JAVA_SRCNAME, store.getString(PreferencesConstants.FJAVA_SRCNAME)); //$NON-NLS-1$
		store.setDefault(MTL_SRCNAME, store.getString(PreferencesConstants.FMTL_SRCNAME)); //$NON-NLS-1$
		store.setDefault(JAVA_BINNAME, store.getString(PreferencesConstants.FJAVA_BINNAME)); //$NON-NLS-1$
		store.setDefault(MTL_BINNAME, store.getString(PreferencesConstants.FMTL_BINNAME)); //$NON-NLS-1$ 
		store.setDefault(MODEL_NAME, store.getString(PreferencesConstants.MODEL_NAME)); //$NON-NLS-1$
		store.setDefault(METAMODEL_NAME, store.getString(PreferencesConstants.METAMODEL_NAME)); //$NON-NLS-1$
		org.inria.mtl.MTLPlugin.getDefault().savePluginPreferences();
	}
	
	/*
	 * @see IWorkbenchPreferencePage#init(IWorkbench)
	 */
	public void init(IWorkbench workbench) {
		IPreferenceStore store= getPreferenceStore(); 
		initDefaults(store);
	}		
	
	/**
	 * @see PreferencePage#createControl(Composite)
	 */
	public void createControl(Composite parent) {
		super.createControl(parent);
	//	WorkbenchHelp.setHelp(getControl(), IJavaHelpContextIds.NEW_JAVA_PROJECT_PREFERENCE_PAGE);
	}	


	private Button addRadioButton(Composite parent, String label, String key, String value, int indent) { 
		GridData gd= new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		gd.horizontalSpan= 2;
		gd.horizontalIndent= indent;
		
		Button button= new Button(parent, SWT.RADIO);
		button.setText(label);
		button.setData(new String[] { key, value });
		button.setLayoutData(gd);

		button.setSelection(value.equals(getPreferenceStore().getString(key)));
		
		fRadioButtons.add(button);
		return button;
	}
	
	private Text addTextControl(Composite parent, Label labelControl, String key, int indent) {
		GridData gd= new GridData();
		gd.horizontalIndent= indent;
		
		labelControl.setLayoutData(gd);
		
		gd= new GridData(GridData.FILL_HORIZONTAL);
		gd.widthHint= convertWidthInCharsToPixels(40);
		
		Text text= new Text(parent, SWT.SINGLE | SWT.BORDER);
		text.setText(getPreferenceStore().getString(key));
		text.setData(key);
		text.setLayoutData(gd);
		
		fTextControls.add(text);
		return text;
	}	
	
	
	protected Control createContents(Composite parent) {
		initializeDialogUnits(parent);
		IPreferenceStore store= getPreferenceStore();
		
		Composite result= new Composite(parent, SWT.NONE);
		GridLayout layout= new GridLayout();
		layout.marginHeight= convertVerticalDLUsToPixels(IDialogConstants.VERTICAL_MARGIN);
		layout.marginWidth= 0;
		layout.verticalSpacing= convertVerticalDLUsToPixels(10);
		layout.horizontalSpacing= convertHorizontalDLUsToPixels(IDialogConstants.HORIZONTAL_SPACING);
		layout.numColumns= 2;
		result.setLayout(layout);
		
		GridData gd= new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan= 5;
		
		Group sourceFolderGroup= new Group(result, SWT.WRAP);
		layout= new GridLayout();
		layout.numColumns= 2;
		sourceFolderGroup.setLayout(layout);
		sourceFolderGroup.setLayoutData(gd);
		sourceFolderGroup.setText(MTLMessages.getString("NewMtlProjectPreferencePage.sourcefolder.label")); //$NON-NLS-1$
		
		int indent= 0;
		
		fOutputFolderNameLabel= new Label(sourceFolderGroup, SWT.NONE);
		fOutputFolderNameLabel.setText(MTLMessages.getString("NewMtlProjectPreferencePage.outputfolders")); //$NON-NLS-1$
		fOutputFolderNameText= addTextControl(sourceFolderGroup, fOutputFolderNameLabel, OUTPUT_BUILDNAME, indent); //$NON-NLS-1$
		fOutputFolderNameText.addModifyListener(fModifyListener);
		
		indent= convertWidthInCharsToPixels(4);
				
		fJavaSrcFolderNameLabel= new Label(sourceFolderGroup, SWT.NONE);
		fJavaSrcFolderNameLabel.setText(MTLMessages.getString("NewMtlProjectPreferencePage.folders.srcjava")); //$NON-NLS-1$
		fJavaSrcFolderNameText= addTextControl(sourceFolderGroup, fJavaSrcFolderNameLabel, JAVA_SRCNAME, indent); //$NON-NLS-1$
		fJavaSrcFolderNameText.addModifyListener(fModifyListener);
		
		//indent= convertWidthInCharsToPixels(8);

		fJavaBinFolderNameLabel= new Label(sourceFolderGroup, SWT.NONE);
		fJavaBinFolderNameLabel.setText(MTLMessages.getString("NewMtlProjectPreferencePage.folders.binjava")); //$NON-NLS-1$
		fJavaBinFolderNameText= addTextControl(sourceFolderGroup, fJavaBinFolderNameLabel, JAVA_BINNAME, indent); //$NON-NLS-1$
		fJavaBinFolderNameText.addModifyListener(fModifyListener);
		
		//indent= convertWidthInCharsToPixels(12);

		fMtlSrcFolderNameLabel= new Label(sourceFolderGroup, SWT.NONE);
		fMtlSrcFolderNameLabel.setText(MTLMessages.getString("NewMtlProjectPreferencePage.folders.srcMtl")); //$NON-NLS-1$
		fMtlSrcFolderNameText= addTextControl(sourceFolderGroup, fMtlSrcFolderNameLabel, MTL_SRCNAME, indent); //$NON-NLS-1$
		fMtlSrcFolderNameText.addModifyListener(fModifyListener);
		
		//indent= convertWidthInCharsToPixels(16);

		fMtlBinFolderNameLabel= new Label(sourceFolderGroup, SWT.NONE);
		fMtlBinFolderNameLabel.setText(MTLMessages.getString("NewMtlProjectPreferencePage.folders.binMtl")); //$NON-NLS-1$
		fMtlBinFolderNameText= addTextControl(sourceFolderGroup, fMtlBinFolderNameLabel, MTL_BINNAME, indent); //$NON-NLS-1$
		fMtlBinFolderNameText.addModifyListener(fModifyListener);
		
		fModelFolderNameLabel= new Label(sourceFolderGroup, SWT.NONE);
		fModelFolderNameLabel.setText(MTLMessages.getString("NewMtlProjectPreferencePage.folders.model")); //$NON-NLS-1$
		fModelFolderNameText= addTextControl(sourceFolderGroup, fModelFolderNameLabel, MODEL_NAME, indent); //$NON-NLS-1$
		fModelFolderNameText.addModifyListener(fModifyListener);
		
		fMetaModelFolderNameLabel= new Label(sourceFolderGroup, SWT.NONE);
		fMetaModelFolderNameLabel.setText(MTLMessages.getString("NewMtlProjectPreferencePage.folders.metamodel")); //$NON-NLS-1$
		fMetaModelFolderNameText= addTextControl(sourceFolderGroup, fMetaModelFolderNameLabel, METAMODEL_NAME, indent); //$NON-NLS-1$
		fMetaModelFolderNameText.addModifyListener(fModifyListener);
		
		
		initDefaults(store);
		validateFolders();
		Dialog.applyDialogFont(result);
		return result;
	}
	
	private void validateFolders() {
	
			String OutputName= fOutputFolderNameText.getText();
			String srcJavaName= fJavaSrcFolderNameText.getText();
			String binJavaName= fJavaBinFolderNameText.getText();
			String srcMtlName= fMtlSrcFolderNameText.getText();
			String binMtlName= fMtlBinFolderNameText.getText();
			String ModelName= fModelFolderNameText.getText();
			String MetaModelName= fMetaModelFolderNameText.getText();
			if (srcJavaName.length() + binJavaName.length()+ binMtlName.length()+ srcMtlName.length() == 0) {
				updateStatus(new StatusInfo(IStatus.ERROR,  MTLMessages.getString("NewJavaProjectPreferencePage.folders.error.namesempty"))); //$NON-NLS-1$
				return;
			}
			IWorkspace workspace= MTLPlugin.getWorkspace();
			IProject currProject= workspace.getRoot().getProject("project"); //$NON-NLS-1$
			
			
			IStatus status;
			IPath srcPath= currProject.getFullPath().append(srcMtlName);
			if (srcMtlName.length() != 0) {
				status= workspace.validatePath(srcPath.toString(), IResource.FOLDER);
				if (!status.isOK()) {
					String message= MTLMessages.getFormattedString("NewJavaProjectPreferencePage.folders.error.invalidsrcname", status.getMessage()); //$NON-NLS-1$
					updateStatus(new StatusInfo(IStatus.ERROR, message));
					return;
				}
			}
			
			IPath srcJavaPath= currProject.getFullPath().append(srcJavaName);
			if (srcJavaName.length() != 0) {
			status= workspace.validatePath(srcJavaPath.toString(), IResource.FOLDER);
			if (!status.isOK()) {
					String message= MTLMessages.getFormattedString("NewJavaProjectPreferencePage.folders.error.invalidsrcname", status.getMessage()); //$NON-NLS-1$
					updateStatus(new StatusInfo(IStatus.ERROR, message));
					return;
				}
			}
			
			IPath binJavaPath= currProject.getFullPath().append(binJavaName);
			if (binJavaName.length() != 0) {
				status= workspace.validatePath(binJavaPath.toString(), IResource.FOLDER);
				if (!status.isOK()) {
						String message= MTLMessages.getFormattedString("NewJavaProjectPreferencePage.folders.error.invalidsrcname", status.getMessage()); //$NON-NLS-1$
			    		updateStatus(new StatusInfo(IStatus.ERROR, message));
					return;
					}
			}

			IPath binMtlPath= currProject.getFullPath().append(binMtlName);
			if (binMtlName.length() != 0) {
				status= workspace.validatePath(binMtlPath.toString(), IResource.FOLDER);
				if (!status.isOK()) {
						String message= MTLMessages.getFormattedString("NewJavaProjectPreferencePage.folders.error.invalidsrcname", status.getMessage()); //$NON-NLS-1$
						updateStatus(new StatusInfo(IStatus.ERROR, message));
					return;
					}
			}
	
		
			IPath binPath= currProject.getFullPath().append(OutputName);
			if (OutputName.length() != 0) {
				status= workspace.validatePath(binPath.toString(), IResource.FOLDER);
				if (!status.isOK()) {
					String message= MTLMessages.getFormattedString("NewJavaProjectPreferencePage.folders.error.invalidbinname", status.getMessage()); //$NON-NLS-1$
					updateStatus(new StatusInfo(IStatus.ERROR, message));
					return;
				}
			}
			
			
		    IPath modelPath= currProject.getFullPath().append(OutputName);
			if (OutputName.length() != 0) {
				status= workspace.validatePath(binPath.toString(), IResource.FOLDER);
				if (!status.isOK()) {
					String message= MTLMessages.getFormattedString("NewJavaProjectPreferencePage.folders.error.invalidbinname", status.getMessage()); //$NON-NLS-1$
					updateStatus(new StatusInfo(IStatus.ERROR, message));
					return;
				}
			}
			
			
			IPath metamodelPath= currProject.getFullPath().append(OutputName);
			if (OutputName.length() != 0) {
				status= workspace.validatePath(binPath.toString(), IResource.FOLDER);
				if (!status.isOK()) {
					String message= MTLMessages.getFormattedString("NewJavaProjectPreferencePage.folders.error.invalidbinname", status.getMessage()); //$NON-NLS-1$
					updateStatus(new StatusInfo(IStatus.ERROR, message));
					return;
				}
			}
	
	
	    this.performOk();
		updateStatus(new StatusInfo()); // set to OK
	}
		
	private void updateStatus(IStatus status) {
		setValid(!status.matches(IStatus.ERROR));
		StatusUtil.applyToStatusLine(this, status);
	}		
	
/*	private void controlChanged(Widget widget) {
		if (widget == fFoldersAsSourceFolder || widget == fProjectAsSourceFolder) {
			validateFolders();
		}
	}
*/	
	private void controlModified(Widget widget) {
		try {
			if (widget == fJavaSrcFolderNameText) validateFolders();
													 
			if (widget == fJavaBinFolderNameText) validateFolders();
													 
			if (widget == fMtlBinFolderNameText) validateFolders(); 
												
			if (widget == fMtlSrcFolderNameText) validateFolders();
												 
			if (widget == fOutputFolderNameText) validateFolders();
												
			if (widget == fModelFolderNameText) validateFolders();
												
			if (widget == fMetaModelFolderNameText ) validateFolders();
				} 
		catch (Exception E)
			{
				System.out.println(E.getMessage());
			}
	}	
	
	/*
	 * @see PreferencePage#performDefaults()
	 */
	protected void performDefaults() {
		IPreferenceStore store= getPreferenceStore();

		for (int i= 0; i < fTextControls.size(); i++) {
			Text text= (Text) fTextControls.get(i);
			String key= (String) text.getData();
			text.setText(store.getDefaultString(key));
		//	System.out.println(key+"    clé par défaut de  :"+store.getDefaultString(key)+ " texte");
		}
		
		validateFolders();
		super.performDefaults(); 
	}

	/*
	 * @see IPreferencePage#performOk()
	 */
	public boolean performOk() {
		IPreferenceStore store= getPreferenceStore();
		for (int i= 0; i < fTextControls.size(); i++) {
			Text text= (Text) fTextControls.get(i);
			String key= (String) text.getData();
			store.setValue(key, text.getText());
		}
		
		org.inria.mtl.MTLPlugin.getDefault().savePluginPreferences();
		return super.performOk();
	}
	

}
