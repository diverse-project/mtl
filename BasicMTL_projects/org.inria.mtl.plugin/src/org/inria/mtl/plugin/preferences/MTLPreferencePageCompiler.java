/*
* $Id: MTLPreferencePageCompiler.java,v 1.2 2004-05-19 09:21:35 sdzale Exp $
* Authors : ${user}
*
* Created on ${date}
* Copyright 2004 - INRIA - LGPL license
*/ 
package org.inria.mtl.plugin.preferences;

import java.net.URL;
import java.util.ArrayList;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.internal.ui.dialogs.StatusInfo;
import org.eclipse.jdt.internal.ui.util.PixelConverter;
import org.eclipse.jdt.internal.ui.util.SWTUtil;
import org.eclipse.jdt.internal.ui.wizards.IStatusChangeListener;
import org.eclipse.jdt.internal.ui.wizards.dialogfields.DialogField;
import org.eclipse.jdt.internal.ui.wizards.dialogfields.IDialogFieldListener;
import org.eclipse.jdt.internal.ui.wizards.dialogfields.IStringButtonAdapter;
import org.eclipse.jdt.internal.ui.wizards.dialogfields.LayoutUtil;
import org.eclipse.jdt.internal.ui.wizards.dialogfields.StringButtonDialogField;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.BusyIndicator;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;
import org.inria.mtl.plugin.MTLPlugin;
import org.inria.mtl.plugin.core.MTLCore;

/**
 * This class represents a preference page that
 * is contributed to the Preferences dialog. By 
 * subclassing <samp>FieldEditorPreferencePage</samp>, we
 * can use the field support built into JFace that allows
 * us to create a page that is small and knows how to 
 * save, restore and apply itself.
 * <p>
 * This page is used to modify preferences only. They
 * are stored in the preference store that belongs to
 * the main plug-in class. That way, preferences can
 * be accessed directly via the preference store.
 */

public class MTLPreferencePageCompiler extends PreferencePage implements IWorkbenchPreferencePage {
	
	private StringButtonDialogField fCompilerField;	
	private ModifyListener fModifyListener;
	private Button fValidateButton;
	private URL fCompilerLocation;
	private IStatusChangeListener fContext;
	private String CompilerPath;
	private ArrayList fTextControls;
	
	//public final Path pathCompiler;
	

	public MTLPreferencePageCompiler() {
		super();
		setPreferenceStore(MTLPlugin.getDefault().getPreferenceStore());
		setDescription(MTLMessages.getString("CompilerPreferencePage.description")); //$NON-NLS-1$
	}
	/**
	 * Sets the default values of the preferences.
	 */
	private void initializeDefaults() {
			//IPreferenceStore store = getPreferenceStore();
		//System.out.println("Initializedefaults");
		
		}
	
	
	public  void initDefaults(IPreferenceStore store){
		//System.out.println("MTL_PATH COMPILER"+store.getString(PreferenceConstants.MTL_COMPILER_PATH)); //$NON-NLS-1$
		//setValues();
		//System.out.println("Initdefaults");
	}
	
	
/**
 * Creates the field editors. Field editors are abstractions of
 * the common GUI blocks needed to manipulate various types
 * of preferences. Each field editor knows how to save and
 * restore itself.
 */

	public void init(IWorkbench workbench) {
		IPreferenceStore store = getPreferenceStore();
		initDefaults( store);
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
	
	public Control createContents(Composite parent) {
		final Composite topComp= new Composite(parent, SWT.NONE);
		GridLayout topLayout= new GridLayout();
		topLayout.numColumns= 3;
		topLayout.marginWidth= 0;
		topLayout.marginHeight= 0;
		topComp.setLayout(topLayout);
		
		CompilationAdapter adapter= new CompilationAdapter();
	
		fCompilerField= new StringButtonDialogField(adapter);
		fCompilerField.setDialogFieldListener(adapter);
		fCompilerField.setLabelText(MTLMessages.getString("MTLCompilerConfiguration.location.label")); //$NON-NLS-1$
		fCompilerField.setButtonLabel(MTLMessages.getString("MTLCompilerConfiguration.location.button")); //$NON-NLS-1$

		fCompilerField.doFillIntoGrid(topComp, 3);
	
		PixelConverter converter= new PixelConverter(topComp);
		LayoutUtil.setWidthHint(fCompilerField.getTextControl(null), converter.convertWidthInCharsToPixels(50));
		LayoutUtil.setHorizontalGrabbing(fCompilerField.getTextControl(null));

		// Fillers;
		DialogField.createEmptySpace(topComp, 2);
			
		fValidateButton= new Button(topComp, SWT.PUSH);
		fValidateButton.setText(MTLMessages.getString("MTLCompilerBlock.ValidateButton.label")); //$NON-NLS-1$
		GridData gd= new GridData(GridData.HORIZONTAL_ALIGN_END);
		fValidateButton.setLayoutData(gd);
		SWTUtil.setButtonDimensionHint(fValidateButton);
			
		fValidateButton.addSelectionListener(new SelectionAdapter() {
				public void widgetSelected(SelectionEvent event) {
				  EntryValidator validator= new EntryValidator();
				  BusyIndicator.showWhile(topComp.getDisplay(), validator);
			}
		});
		setValues();
		
		return topComp;
	}
	
	//	Sets the default by getting the stored URL setting if it exists
	//otherwise the text box is left empty.
	public void setValues() {
		   IPreferenceStore store = getPreferenceStore();
		   IPath cPath=new Path(store.getString(PreferenceConstants.MTL_COMPILER_PATH));
		   String initialValue = (cPath.toOSString().length()==0)?"":cPath.toOSString(); //$NON-NLS-1$
		  //System.out.println("setvalues :"+fCompilerField.getText());
		  //System.out.println(fCompilerField.i
		  try{
				fCompilerField.setText(initialValue); 
				//System.out.println("ComField après :"+fCompilerField.getTextControl(parent));
		}catch (Exception E){
			//System.out.println("ComField après :"+fCompilerField.getTextControl(parent));
			//System.out.println("ComField inexistant :"+E.getMessage() +"   "+fCompilerField.getText());
		  }
		  
		CompilerPath=fCompilerField.getText();
		  
	  }
	
	public void setFocus() {
		fCompilerField.postSetFocusOnDialogField(this.getShell().getDisplay());
		}
	
	
	public class CompilationAdapter implements IStringButtonAdapter, IDialogFieldListener {

			// -------- IStringButtonAdapter --------
			public void changeControlPressed(DialogField field) {
				compChangeControlPressed(field);
			}

			// ---------- IDialogFieldListener --------
			public void dialogFieldChanged(DialogField field) {
				compDialogFieldChanged(field);
			}
		}
	
			private void compChangeControlPressed(DialogField field) {
			if (field == fCompilerField) {
				String jdocURL= chooseCompilerLocation();
				if (jdocURL != null) {
					try{
						fCompilerField.setText(jdocURL);
					}catch (Exception E){
						//System.out.println("Encore setText");
					}
					
		
				}
			}
		}

	public boolean performOk() {
			IPreferenceStore store= getPreferenceStore();
			store.setValue(PreferenceConstants.MTL_COMPILER_PATH,CompilerPath);
			if (store.getString(PreferenceConstants.MTL_COMPILER_PATH).length()==0){
				System.out.println("Error :MTL Compiler path have to be instanciated");
			}
			//System.out.println("PATH "+store.getString(PreferenceConstants.MTL_COMPILER_PATH));
			MTLPlugin.getDefault().savePluginPreferences();
			return super.performOk();
		}
		
	public String chooseCompilerLocation() {
			String initPath= ""; //$NON-NLS-1$
			//			if (fCompilerLocation != null ) { //$NON-NLS-1$
//				initPath= (new Folder(fCompilerLocation.getPath();
//			}
			DirectoryDialog dialog= new DirectoryDialog(this.getShell());
			dialog.setText(MTLMessages.getString("MTLCompiler.LocationDialog.label")); //$NON-NLS-1$
			dialog.setMessage(MTLMessages.getString("MTLCompiler.LocationDialog.message")); //$NON-NLS-1$
			dialog.setFilterPath(initPath);
			String res= dialog.open();
			//System.out.println("res"+res);
			if (res != null) {
				try {
					return (new Path(res)).toString();
				} catch (Exception e) {
					// should not happen
					MTLPlugin.log(e);
				}
			}
			return null;
		}
	public IStatus updateCompilerLocationStatus() {
			StatusInfo status= new StatusInfo();
			fCompilerLocation= null;
			String compLocation= fCompilerField.getText();
		    CompilerPath= fCompilerField.getText();
			if (compLocation.length() > 0) {
				try {
					Path url= new Path(compLocation);
					if (url.isEmpty()) { //$NON-NLS-1$
						status.setError(MTLMessages.getString("MTL Compiler.error.notafolder")); //$NON-NLS-1$
						return status;
					}
				//	fCompilerLocation= url.toString();
				} catch (Exception e) {
					status.setError(MTLMessages.getString("error"));  //$NON-NLS-1$
					return status;
				}
			} 
			//else status.setWarning(PreferencesMessages.getString("JavadocConfigurationBlock.EmptyJavadocLocation.warning")); //$NON-NLS-1$
			return status;
		}

	public void compDialogFieldChanged(DialogField field) {
			if (field == fCompilerField) {
				IStatus status= updateCompilerLocationStatus();
				fValidateButton.setEnabled(!status.matches(IStatus.ERROR) && fCompilerField.getText().length() > 0);
				fContext.statusChanged(status);
			}
		}

	 public void performDefaults() {
			setValues();
		}
	
		public URL getCompilerLocation() {
			return fCompilerLocation;
		}
		
		private class EntryValidator implements Runnable {

			private String fInvalidMessage= MTLMessages.getString("Compiler.InvalidLocation.message"); //$NON-NLS-1$
			private String fValidMessage= MTLMessages.getString("Compiler.ValidLocation.message"); //$NON-NLS-1$
			private String fTitle=  MTLMessages.getString("Compiler.MessageDialog.title"); //$NON-NLS-1$
			//System.out.println("entry validator0");
			public void run() {
				IPreferenceStore store = getPreferenceStore();
			    
			//System.out.println("entry validator1");
		 	Path path = new Path(fCompilerField.getText());
			CompilerPath= fCompilerField.getText();
			// Save MTL COMPILER PATH
			store.setValue(PreferenceConstants.MTL_COMPILER_PATH,CompilerPath); //$NON-NLS-1$
			//System.out.println("Path in validate :"+store.getString(PreferenceConstants.MTL_COMPILER_PATH));
			String MTLcompiler_path=CompilerPath;
			if (MTLcompiler_path.length()==0){
				  System.out.println("Error: Compiler path not define");
			}
			//MTLcompiler_path=this.checkPathEnd(MTLcompiler_path);
		  
					//
			String MTLcompiler_jar_path=MTLcompiler_path.concat("/bin");
			//MTLcompiler_jar_path=this.checkPathEnd(MTLcompiler_jar_path);
			String BasicMTLc_jar=MTLcompiler_jar_path.concat("/BasicMTLc.jar");
			IPath BasicMTLcPath=new Path(BasicMTLc_jar); 
			
			String Runtime_TLL_path=MTLcompiler_path.concat("/Runtime/src/TLL");
			String BasicMTLRuntime_jar=MTLcompiler_jar_path.concat("/BasicMTLruntime.jar");
			IPath BasicMTLRuntimePath=new Path(BasicMTLRuntime_jar);
			
			JavaCore.newLibraryEntry(BasicMTLcPath,null,null);
			JavaCore.newLibraryEntry(BasicMTLRuntimePath,null,null);
			MTLCore.newContainerEntry(path); 
//		  
//			System.out.println("BasicMTLcPath :"+BasicMTLcPath );
//			System.out.println("BasicMTLRuntimePath :"+BasicMTLRuntimePath);
//			System.out.println("BasicMTLc_jar :"+BasicMTLc_jar);
//			System.out.println("BasicMTLRuntime_jar :"+BasicMTLRuntime_jar);
			
			}
		}
		

}