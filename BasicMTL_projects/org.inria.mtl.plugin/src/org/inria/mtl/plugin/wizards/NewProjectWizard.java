/*
* $Id: NewProjectWizard.java,v 1.4 2004-06-15 15:12:58 sdzale Exp $
* Authors : ${user}
*
* Created on ${date}
* Copyright 2004 - INRIA - LGPL license
*/ 

package org.inria.mtl.plugin.wizards; 
 
import java.io.InputStream;
import java.io.File;
import java.net.URL;
import java.net.MalformedURLException;

import org.eclipse.core.resources.ICommand;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IWorkspaceRunnable;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.ui.wizards.NewJavaProjectWizardPage;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.ui.dialogs.WizardNewProjectCreationPage;
import org.inria.mtl.plugin.MTLPlugin;
import org.inria.mtl.plugin.builders.MTLBuilder;
import org.inria.mtl.plugin.builders.MTLNature;
import org.inria.mtl.plugin.core.MTLCore;
import org.inria.mtl.plugin.preferences.PreferenceConstants;

public class NewProjectWizard extends Wizard implements INewWizard, IWorkspaceRunnable {
	public static final String NEW_PROJECT_WIZARD_ID= "org.inria.mtl.plugin.wizards.NewProjectWizard"; //$NON-NLS-1$
		
	private NewJavaProjectWizardPage fJavaPage;
	private WizardNewProjectCreationPage fMainPage;
	private IConfigurationElement fConfigElement;
	
	private IWorkbench workbench;
	private IStructuredSelection selection;
 
	public void init(IWorkbench workbench, IStructuredSelection selection) {
	   this.workbench = workbench;
	   this.selection = selection;
	   setWindowTitle(MTLImportMessages.getString("NewProjectCreationWizard.title"));
	   //initializeDefaultPageImageDescriptor();
   }
 
	/*
	 * @see Wizard#addPages
	 */	
	public void addPages() {
		super.addPages();
		fMainPage= new WizardNewProjectCreationPage("NewProjectCreationWizard"); //$NON-NLS-1$
		fMainPage.setTitle(MTLImportMessages.getString("NewProjectCreationWizard.MainPage.title"));//$NON-NLS-1$
		fMainPage.setDescription(MTLImportMessages.getString("NewProjectCreationWizard.MainPage.description")); //$NON-NLS-1$
		addPage(fMainPage);
		fJavaPage= new NewJavaProjectWizardPage(ResourcesPlugin.getWorkspace().getRoot(),fMainPage);

	}		
	
	public void setInitializationData(IConfigurationElement cfig, String propertyName, Object data) {
		fConfigElement= cfig;
	}
	
	/* (non-Javadoc)
	 * @see IWizard#performCancel()
	 */
	public boolean performCancel() {
	//	fJavaPage.performCancel();
		return super.performCancel();
	}

////////////////////////
/**
 *  (non-Javadoc) Method declared on IWizard
 *
 *@return    true if successfull
 */
public boolean performFinish() {
	try {
		MTLPlugin.getWorkspace().run(this, null);
	} catch (Exception e) {
		e.printStackTrace();
	}
	return true;
}



/**
 *  Main processing method for the JAXBProjectCreationWizard object
 *
 *@param  monitor            monitor to use during project creation
 *@exception  CoreException  
 */
public void run(IProgressMonitor monitor) throws CoreException {
	finish(monitor);
}



/**
 *  Guts of wizard where folders build, src, tll are created
  * The project created is based on the JDT project with the MTLNature added
 *
 *@param  monitor  monitor used during project creation
 *@return          true created ok, false not ok
 */
public boolean finish(IProgressMonitor monitor) {
	IPreferenceStore store=PreferenceConstants.getPreferenceStore();
	String RuntimeFilePath;
	
	
	// create the new file resource
	IProject newProject = fMainPage.getProjectHandle();
	if (newProject == null) {
		return false;
	}
	// ie.- creation was unsuccessful

	// Since the file resource was created fine, open it for editing
	// if requested by the user
	try {


		newProject.create(null);
		newProject.open(null);
		
		//output folder
		IFolder output = newProject.getFolder(store.getString(PreferenceConstants.OUTPUT_BUILDNAME));
		output.create(true, true, null);
		
		//		model folder
		IFolder model = newProject.getFolder(store.getString(PreferenceConstants.MODEL_NAME));
		model.create(true, true, null);
		
		//		metamodel folder
		IFolder metamodel = newProject.getFolder(store.getString(PreferenceConstants.METAMODEL_NAME));
		metamodel.create(true, true, null);
		
		
		//
		//store.s
		IFolder javasrc =output.getFolder(store.getString(PreferenceConstants.FJAVA_SRCNAME));
		javasrc.create(true,true,null);	
		IFolder binjava = output.getFolder(store.getString(PreferenceConstants.FJAVA_BINNAME));
		binjava.create(true, true, null);
		IFolder mtlsrc = newProject.getFolder(store.getString(PreferenceConstants.FMTL_SRCNAME));
		mtlsrc.create(true, true, null);
		IFolder binmtl = output.getFolder(store.getString(PreferenceConstants.FMTL_BINNAME));
		binmtl.create(true, true, null);
		
		//A revoir
		String pluginPath =MTLPlugin.getDefault().getLocation();
		String MTLcompiler_path=store.getString(PreferenceConstants.MTL_COMPILER_PATH);
		if (MTLcompiler_path.length()==0){
					RuntimeFilePath=pluginPath.concat("MTL\\bin\\BasicMTLruntime.jar");
					pluginPath=pluginPath.concat("MTL");
					File runtimeFile = new File(RuntimeFilePath); 
//					System.out.println("File :"+compFile.toString());
					if (!(runtimeFile.exists())){
						Shell shell = new Shell();
									MessageDialog.openInformation(
									shell,
									"MTL Runtime Compiler",
									"Compiler path not define...");
									return false;
					}
					MTLcompiler_path=pluginPath;
					store.setValue(PreferenceConstants.MTL_COMPILER_PATH,MTLcompiler_path);
			
		}else{
			RuntimeFilePath=MTLcompiler_path.concat("\\bin\\BasicMTLruntime.jar");
		}
		
		IPath BasicMTLRuntimePath=new Path(RuntimeFilePath);
		
		if (MTLcompiler_path.length()==0){
					IClasspathEntry[] newcpe1 = new IClasspathEntry[1] ;
					IClasspathEntry[] newcpe = new IClasspathEntry[1] ;			
					newcpe[0] = JavaCore.newSourceEntry(javasrc.getFullPath());
					fJavaPage.setDefaultClassPath(newcpe, true);
					fJavaPage.setDefaultOutputFolder(binjava.getFullPath());
					fJavaPage.getRunnable().run(null);
					
					MTLCore.setProject(newProject);
					newcpe1[0]=MTLCore.newSourceEntry(mtlsrc.getFullPath()/*,MtlClasspathEntry.NO_EXCLUSION_PATTERNS,output.getFullPath()*/);
		
					try{
						boolean bol=MTLCore.saveClasspath(newcpe1,null);
						IClasspathEntry[] entries = MTLCore.readClasspathFile();
					} catch(Exception E){
						System.out.println("Nom de projet inexistant");
					}			
		  }else{
					IClasspathEntry[] newcpe1 = new IClasspathEntry[1] ;
					IClasspathEntry[] newcpe = new IClasspathEntry[2] ;			
					newcpe[0] = JavaCore.newSourceEntry(javasrc.getFullPath());
					newcpe[1] = JavaCore.newLibraryEntry(BasicMTLRuntimePath,null,null);

					fJavaPage.setDefaultClassPath(newcpe, true);
					fJavaPage.setDefaultOutputFolder(binjava.getFullPath());
					fJavaPage.getRunnable().run(null);
					
					MTLCore.setProject(newProject);
					try{
						newcpe1[0]=MTLCore.newSourceEntry(mtlsrc.getFullPath()/*,MtlClasspathEntry.NO_EXCLUSION_PATTERNS,output.getFullPath()*/);			
					}catch (Exception E){System.out.println("Ajout source incorrect");
					}
//					try{
//					}catch (Exception E){
//						System.out.println("Ajout container incorrect");
//					}
					
					boolean bol=MTLCore.saveClasspath(newcpe1,null);
					
					try{
					
					IClasspathEntry[] entries = MTLCore.readClasspathFile();
					} catch(Exception E){
						System.out.println("Nom de projet inexistant");
					}
		  }
		
		
		
		
		
		
		//Settings MTL folder output and input
		
//		store.setValue(PreferenceConstants.FJAVA_SRCNAME,javasrc.getFullPath().toString());
//		store.setValue(PreferenceConstants.FJAVA_BINNAME,binjava.getFullPath().toString());
//		store.setValue(PreferenceConstants.FMTL_BINNAME,binmtl.getFullPath().toString());
//		store.setValue(PreferenceConstants.FMTL_SRCNAME,mtlsrc.getFullPath().toString());
//		store.setValue(PreferenceConstants.OUTPUT_BUILDNAME,output.getFullPath().toString());
		setBuilder(newProject);
		
//		System.out.println("jsrc :"+javasrc.getFullPath().toString());
//		System.out.println("jcl :"+binjava.getFullPath().toString());
//		System.out.println("smtl :"+mtlsrc.getFullPath().toString());
//		System.out.println("tll :"+binmtl.getFullPath().toString());
		MTLCore.findFolders();
		
		} catch (Exception e) {
		e.printStackTrace();
		return false;
	}
	return true;
}


/**
 *  Helper method used to copy a resource from the plugin directory to the new
 * project
 *
 *@param  newFile            file resource for the destination
 *@param  referenceFilename  file resource for the source
 *@exception  Exception      
 */
private void createDefaultResource(IFile newFile, String referenceFilename) throws Exception {
	InputStream fileStream = MTLPlugin.instance().getResourceStream(referenceFilename);
	newFile.create(fileStream, false, null);
	fileStream.close();
}


/**
 *  Attaches the MTLNature and MTLBuilder to the project ensuring it is
 * attached after the JDT
 *
 *@param  newProject     The project 
 *@exception  Exception  
 */
private void setBuilder(IProject newProject) throws Exception {
	IProjectDescription desc = newProject.getDescription();
	ICommand[] commands = desc.getBuildSpec();
	ICommand cmd = desc.newCommand();
	cmd.setBuilderName(MTLBuilder.BUILDER_ID);

	ICommand[] newCommands = new ICommand[commands.length + 1];
	System.arraycopy(commands, 0, newCommands, 1, commands.length);
	newCommands[0] = cmd;

	desc.setBuildSpec(newCommands);

	String[] natures = desc.getNatureIds();
	String[] newNatures = new String[natures.length + 1];
	//The java nature is the first
	System.arraycopy(natures, 0, newNatures, 1, natures.length);
	newNatures[0] = MTLNature.NATURE_ID;
	desc.setNatureIds(newNatures);
	
	newProject.setDescription(desc, null);
	


}

/* (non-Javadoc)
 * Method declared on BasicNewResourceWizard.
 */
 protected void initializeDefaultPageImageDescriptor() {
	String iconPath = "icons/";//$NON-NLS-1$		
	try {
		URL installURL = MTLPlugin.getDefault().getDescriptor().getInstallURL();
		URL url = new URL(installURL, iconPath + "wizban/fmtl.gif");//$NON-NLS-1$
		ImageDescriptor desc = ImageDescriptor.createFromURL(url);
		setDefaultPageImageDescriptor(desc);
	}
	catch (MalformedURLException e) {
		// Should not happen.  Ignore.
	}
}

		

}