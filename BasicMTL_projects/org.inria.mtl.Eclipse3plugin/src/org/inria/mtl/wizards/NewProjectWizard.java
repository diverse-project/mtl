/*
 * $Id: NewProjectWizard.java,v 1.5 2005-02-24 16:43:54 dvojtise Exp $ Authors :
 * Authors: sdzale, dvojtise
 * 
 * Created on ${date} Copyright 2004 - INRIA - LGPL license
 * 
 * Note: this seem to be the good file (remove the MTLnewProjectWizard file ?
 */

package org.inria.mtl.wizards;

import java.io.File;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

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
import org.eclipse.core.runtime.QualifiedName;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.ui.wizards.NewJavaProjectWizardPage;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.dialogs.WizardNewProjectCreationPage;
import org.inria.mtl.MTLPlugin;
import org.inria.mtl.builders.MTLBuilder;
import org.inria.mtl.builders.MTLModel;
import org.inria.mtl.builders.MTLNature;
import org.inria.mtl.core.MTLCore;
import org.inria.mtl.preferences.PreferencesConstants;

public class NewProjectWizard extends Wizard implements INewWizard,
		IWorkspaceRunnable {
	public static final String NEW_PROJECT_WIZARD_ID = "org.inria.mtl.plugin.wizards.NewProjectWizard"; //$NON-NLS-1$

	private NewJavaProjectWizardPage fJavaPage;

	private WizardNewProjectCreationPage fMainPage;

	private IConfigurationElement fConfigElement;

	private IWorkbench workbench;

	private IStructuredSelection selection;

	private String RuntimetllFolder;

	public void init(IWorkbench workbench, IStructuredSelection selection) {
		this.workbench = workbench;
		this.selection = selection;
		setWindowTitle(MTLImportMessages
				.getString("NewProjectCreationWizard.title"));
		//initializeDefaultPageImageDescriptor();
	}

	/*
	 * @see Wizard#addPages
	 */
	public void addPages() {
		super.addPages();
		fMainPage = new WizardNewProjectCreationPage("NewProjectCreationWizard"); //$NON-NLS-1$
		fMainPage.setTitle(MTLImportMessages
				.getString("NewProjectCreationWizard.MainPage.title"));//$NON-NLS-1$
		fMainPage.setDescription(MTLImportMessages
				.getString("NewProjectCreationWizard.MainPage.description")); //$NON-NLS-1$
		addPage(fMainPage);
		fJavaPage = new NewJavaProjectWizardPage(ResourcesPlugin.getWorkspace()
				.getRoot(), fMainPage);

	}

	public void setInitializationData(IConfigurationElement cfig,
			String propertyName, Object data) {
		fConfigElement = cfig;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see IWizard#performCancel()
	 */
	public boolean performCancel() {
		//	fJavaPage.performCancel();
		return super.performCancel();
	}

	////////////////////////
	/**
	 * (non-Javadoc) Method declared on IWizard
	 * 
	 * @return true if successfull
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
	 * Main processing method for the JAXBProjectCreationWizard object
	 * 
	 * @param monitor
	 *            monitor to use during project creation
	 * @exception CoreException
	 */
	public void run(IProgressMonitor monitor) throws CoreException {
		finish(monitor);
	}

	/**
	 * Guts of wizard where folders build, src, tll are created The project
	 * created is based on the JDT project with the MTLNature added
	 * 
	 * @param monitor
	 *            monitor used during project creation
	 * @return true created ok, false not ok
	 */
	public boolean finish(IProgressMonitor monitor) {
		IPreferenceStore store = PreferencesConstants.getPreferenceStore();
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
			IFolder output = newProject.getFolder(store
					.getString(PreferencesConstants.OUTPUT_BUILDNAME));
			createFolder(output);

			//		model folder
			IFolder model = newProject.getFolder(store
					.getString(PreferencesConstants.MODEL_NAME));
			createFolder(model);

			//		metamodel folder
			IFolder metamodel = newProject.getFolder(store
					.getString(PreferencesConstants.METAMODEL_NAME));
			createFolder(metamodel);

			//
			//store.s
			IFolder javasrc = output.getFolder(store
					.getString(PreferencesConstants.FJAVA_SRCNAME));
			createFolder(javasrc);
			IFolder binjava = output.getFolder(store
					.getString(PreferencesConstants.FJAVA_BINNAME));
			createFolder(binjava);
			IFolder mtlsrc = newProject.getFolder(store
					.getString(PreferencesConstants.FMTL_SRCNAME));
			createFolder(mtlsrc);

			IFolder binmtl = output.getFolder(store
					.getString(PreferencesConstants.FMTL_BINNAME));
			createFolder(binmtl);

			//A revoir
			String pluginPath = MTLPlugin.getDefault().getLocation();
			String MTLcompiler_path = store
					.getString(PreferencesConstants.MTL_COMPILER_PATH);
			if (MTLcompiler_path.length() == 0) {
				RuntimeFilePath = pluginPath
						.concat("MTL\\bin\\BasicMTLruntime.jar");
				pluginPath = pluginPath.concat("MTL");
				File runtimeFile = new File(RuntimeFilePath);
				//					System.out.println("File :"+compFile.toString());
				if (!(runtimeFile.exists())) {
					Shell shell = new Shell();
					MessageDialog.openInformation(shell,
							"MTL Runtime Compiler",
							"Compiler path not define...");
					return false;
				}
				MTLcompiler_path = pluginPath;
				store.setValue(PreferencesConstants.MTL_COMPILER_PATH,
						MTLcompiler_path);

			} else {
				RuntimeFilePath = MTLcompiler_path
						.concat("\\bin\\BasicMTLruntime.jar");
			}

			IPath BasicMTLRuntimePath = new Path(RuntimeFilePath);

			if (MTLcompiler_path.length() == 0) {
				IClasspathEntry[] newcpe1 = new IClasspathEntry[1];
				IClasspathEntry[] newcpe = new IClasspathEntry[1];
				newcpe[0] = JavaCore.newSourceEntry(javasrc.getFullPath());
				fJavaPage.setDefaultClassPath(newcpe, true);
				fJavaPage.setDefaultOutputFolder(binjava.getFullPath());
				fJavaPage.getRunnable().run(null);

				MTLCore.setProject(newProject);
				newcpe1[0] = MTLCore
						.newSourceEntry(mtlsrc.getFullPath()/* ,MtlClasspathEntry.NO_EXCLUSION_PATTERNS,output.getFullPath() */);

				try {
					boolean bol = MTLCore.saveClasspath(newcpe1, null, null);
					IClasspathEntry[] entries = MTLCore.readClasspathFile();
				} catch (Exception E) {
					System.out.println("Nom de projet inexistant");
				}
			} else {
				IClasspathEntry[] newcpe1 = new IClasspathEntry[1];
				IClasspathEntry[] newcpe = new IClasspathEntry[2];
				newcpe[0] = JavaCore.newSourceEntry(javasrc.getFullPath());
				newcpe[1] = JavaCore.newLibraryEntry(BasicMTLRuntimePath, null,
						null);

				fJavaPage.setDefaultClassPath(newcpe, true);
				fJavaPage.setDefaultOutputFolder(binjava.getFullPath());
				fJavaPage.getRunnable().run(null);

				//Insertion des librairies de base
				IClasspathEntry[] newcpe2 = new IClasspathEntry[] {};
				String filesList[] = new File("").list();
				if (!(MTLcompiler_path.charAt(MTLcompiler_path.length() - 1) == '/' || MTLcompiler_path
						.charAt(MTLcompiler_path.length() - 1) == '\\'))
					MTLcompiler_path = MTLcompiler_path + '/';
				RuntimetllFolder = MTLcompiler_path
						.concat("Runtime\\src\\TLL\\");
				System.out.println("Runtime :" + RuntimetllFolder);
				java.util.Vector tllfilenames = new java.util.Vector();
				//System.out.println("Runtime :"+(new
				// Path(RuntimetllFolder)).isValidPath());
				if (!(RuntimetllFolder == null)) {
					filesList = new java.io.File(RuntimetllFolder).list();
					System.out.println("Filelist :" + filesList);
					if (!((filesList == null) || (filesList.length == 0))) {
						newcpe2 = new IClasspathEntry[filesList.length + 1];
						for (int i = 0; i < filesList.length; i++) {
							if (filesList[i].endsWith(".tll")) {

								File aFile = new File(RuntimetllFolder
										+ filesList[i]);
								if (!aFile.exists())
									System.err.println("Cannot find fileName :"
											+ aFile);
								else {
									newcpe2[i] = MTLCore.newLibraryEntry(
											new Path(aFile.getPath()), null,
											null);
								}
							}

						}
					}
				}
				/////////////////////////////////////////////////////
				MTLCore.setProject(newProject);
				if (!(filesList == null)) {// Aucun fichier ne se trouve dans le
										   // repertoire du compilateur
					if (filesList.length == 0) {
						newcpe1[0] = MTLCore.newSourceEntry(mtlsrc
								.getFullPath());
						boolean bol = MTLCore
								.saveClasspath(newcpe1, null, null);
					} else {
						newcpe2[filesList.length] = MTLCore
								.newSourceEntry(mtlsrc.getFullPath());
						boolean bol = MTLCore.saveClasspath(newcpe2, null, null);
					}
				} else {
					newcpe1[0] = MTLCore.newSourceEntry(mtlsrc.getFullPath());
					boolean bol = MTLCore.saveClasspath(newcpe1, null, null);
				}

			}
			try {
				IClasspathEntry[] entries = MTLCore.readClasspathFile();
			} catch (Exception E) {
				System.out.println("Nom de projet inexistant");
			}

			//Settings MTL folder output and input
			setBuilder(newProject);
			//Stocker le chemin des tll
			newProject.setPersistentProperty(new QualifiedName(
					MTLPlugin.PLUGIN_ID, MTLModel.MTL_TLLFolder), binmtl
					.getFullPath().toString());
			newProject.setPersistentProperty(new QualifiedName(
					MTLPlugin.PLUGIN_ID, MTLModel.TLL_RUNTIMEFOLDER),
					RuntimetllFolder);
			MTLCore.loadMtlClasspath();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * helper method that recusrively create the requested folder
	 * 
	 * @param folder
	 */
	private void createFolder(IFolder folder) throws CoreException {
		if (!folder.getParent().exists())
			createFolder((IFolder) folder.getParent());
		folder.create(true, true, null);

	}

	/**
	 * Helper method used to copy a resource from the plugin directory to the
	 * new project
	 * 
	 * @param newFile
	 *            file resource for the destination
	 * @param referenceFilename
	 *            file resource for the source
	 * @exception Exception
	 */
	private void createDefaultResource(IFile newFile, String referenceFilename)
			throws Exception {
		InputStream fileStream = MTLPlugin.instance().getResourceStream(
				referenceFilename);
		newFile.create(fileStream, false, null);
		fileStream.close();
	}

	/**
	 * Attaches the MTLNature and MTLBuilder to the project ensuring it is
	 * attached after the JDT
	 * 
	 * @param newProject
	 *            The project
	 * @exception Exception
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

	/*
	 * (non-Javadoc) Method declared on BasicNewResourceWizard.
	 */
	protected void initializeDefaultPageImageDescriptor() {
		String iconPath = "icons/";//$NON-NLS-1$		
		try {
			//URL installURL =
			// MTLPlugin.getDefault().getDescriptor().getInstallURL();
			URL url = new URL(MTLPlugin.getBaseURL(), iconPath
					+ "wizban/fmtl.gif");//$NON-NLS-1$
			ImageDescriptor desc = ImageDescriptor.createFromURL(url);
			setDefaultPageImageDescriptor(desc);
		} catch (MalformedURLException e) {
			// Should not happen. Ignore.
		}
	}

}