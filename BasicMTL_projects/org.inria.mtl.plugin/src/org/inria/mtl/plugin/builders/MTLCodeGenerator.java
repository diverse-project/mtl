/*
* $Id: MTLCodeGenerator.java,v 1.2 2004-05-19 09:22:18 sdzale Exp $
* Authors : ${user}
*
* Created on ${date}
* Copyright 2004 - INRIA - LGPL license
*/ 
package org.inria.mtl.plugin.builders;

import java.io.IOException;
import java.io.InputStream;
import java.util.Hashtable;
import java.util.StringTokenizer;
import java.util.Vector;

import org.apache.log4j.xml.DOMConfigurator;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRunnable;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.QualifiedName;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.inria.mtl.plugin.MTLPlugin;
import org.inria.mtl.plugin.core.MTLCore;
import org.inria.mtl.plugin.preferences.MTLMessages;
import org.inria.mtl.plugin.preferences.PreferenceConstants;
import org.inria.mtl.plugin.views.MTLConsole;


/**
 *  Class used to call into the MTL API to generate tll and java sources from
 * the given mtl files
 *
 *@author     serge DZALE
 *@created    20 April 2004
 */
public class MTLCodeGenerator implements IWorkspaceRunnable {

	private IFolder srcJavaFolder;
	private IFolder srcMtlFolder;
	private IFolder binMtlFolder;
	private IFolder outputFolder;;
	
	private MTLModel model;


	/**
	 *  Main processing method for the MTLCodeGenerator object
	 *
	 *@param  monitor            monitor to use throughout operation
	 *@exception  CoreException  
	 */
	public void run(IProgressMonitor monitor) throws CoreException {
		try {
			monitor.beginTask("Generating TLL and JAVA Objects for ...", IProgressMonitor.UNKNOWN);
			if (checkSourcePath(srcMtlFolder)){
				generate(srcMtlFolder,binMtlFolder,srcJavaFolder);
			}
		} catch (Exception e) {
			throw new CoreException(new Status(Status.ERROR, MTLPlugin.PLUGIN_ID, 0, e.getMessage(), e));
		} finally {
			monitor.done();
		}
	}


	/**
	 *  Constructor for MTLCodeGenerator
	 *
	 *@param  srcJavaFolder    folder resource for the output java files
	 *@param  binMtlFolderf    folder resource for the output tll files
	 *@param  srcMtlFolder     folder resource for the mtl files
	 *@param  outputFolder     root folder resource for all output files
	 */
	public  MTLCodeGenerator(IFolder srcMtlFolder ,IFolder binMtlFolder,IFolder srcJavaFolder,IFolder output) {
		super();
		this.srcMtlFolder = srcMtlFolder;
		this.binMtlFolder = binMtlFolder;
		this.srcJavaFolder = srcJavaFolder;  
		this.outputFolder =output;
		model = MTLPlugin.instance().getModel(srcMtlFolder.getProject());
		MTLCore.findFolders();
		
	}


	/**
	 *  helper method used to call the generate method for each
	 * mtl file within the mtl source folder
	 *
	 *@param  srcJavaFolder    folder resource for the output java files
	 *@param  binMtlFolderf    folder resource for the output tll files
	 *@param  srcMtlFolder     folder resource for the mtl files
	 *@exception  Exception  all errors reported by
	 */

	/**
	 *  removes all MTL problems associated with a mtl file resource
	 *
	 *@param  newMtlFile  Description of the Parameter
	 */
	private void removeMTLProblems(IFolder newMtlFolder) { 
		try {
			newMtlFolder.deleteMarkers(MTLPlugin.MTL_PROBLEM, false, IResource.DEPTH_INFINITE);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	/**
	 *  The guts of this class, used to call into the MTL API to generate the tll and java files
	 * for a given list of mtl file resource
	 *
	 *@param  tllpath      folder resource for the output tll files
	 *@param  javapath      folder resource for the output java files
	 *@param  mtlFile	  file resource for mtl 
	 *@exception  Exception  all errors reported by
	 */
	public void generate(IFolder srcmtlFolder,IFolder tllFolder, IFolder javaFolder) throws Exception {
		IPreferenceStore store=MTLPlugin.getDefault().getPreferenceStore();
//		Default package prefix
		 String defaultPrefix =srcmtlFolder.getName();
	  try {
		  String lastGen = srcmtlFolder.getPersistentProperty(new QualifiedName(MTLPlugin.PLUGIN_ID, MTLModel.TLL_LASTGENTIME));
		  String newGen = new Long(srcmtlFolder.getModificationStamp()).toString();
		  if (lastGen != null && lastGen.equals(newGen)) {
			  return;
		  }
		srcmtlFolder.setPersistentProperty(new QualifiedName(MTLPlugin.PLUGIN_ID, MTLModel.TLL_LASTGENTIME), newGen);
		  
		  //Find sourceDir parameter for codeGenerator
		  String sourceDir= srcmtlFolder.getLocation().toOSString();
		  sourceDir=this.checkPathEnd(sourceDir);
		  //System.out.println("sourceDir :"+sourceDir);
		
		 //Find defaultPackagePrefix parameter for codeGenerator
		 //String defaultPackagePrefix=srcJavaFolder.getFullPath().toString();

		//		Find defaultPackagePrefix parameter for codeGenerator
		   String defaultPackagePrefix=srcmtlFolder.getName();

		  
		  //Find java Folder source parameter for codeGenerator
		  String javaDestDir= javaFolder.getLocation().toOSString();
		  javaDestDir=this.checkPathEnd(javaDestDir);
		 // System.out.println("javaDestDir :"+javaDestDir);
		  
		  //Find tll Folder source parameter for codeGenerator
		  String tllDestDir= tllFolder.getLocation().toOSString();
		  tllDestDir=this.checkPathEnd(tllDestDir);
		  //System.out.println("tllDestDir :"+tllDestDir);
		  
		  //Compiler parameters
		  // Find list of tll exported Folders parameter for codeGenerator
		  //Verify that the compiler path have been defined
		
		  String MTLcompiler_path=store.getString(PreferenceConstants.MTL_COMPILER_PATH);
		  if (MTLcompiler_path.length()==0){
			Shell shell = new Shell();
				MessageDialog.openInformation(
					shell,
					"MTL Compiler",
					"Compiler path not define...");
			//System.out.println("Error: Compiler path not define");
			return;
		  }
		  //MTLcompiler_path=this.checkPathEnd(MTLcompiler_path);
		  
		  //
		  String MTLcompiler_jar_path=MTLcompiler_path.concat("\\bin");
		  //MTLcompiler_jar_path=this.checkPathEnd(MTLcompiler_jar_path);
		  String BasicMTLc_jar=MTLcompiler_jar_path.concat("\\BasicMTLc.jar");
		  String Runtime_TLL_path=MTLcompiler_path.concat("\\Runtime\\src\\TLL\\");
		  String BasicMTLRuntime_jar=MTLcompiler_jar_path.concat("\\BasicMTLruntime.jar");
		  //IFolder runtimePath= new Folder(Runtime_TLL_path);
		  //boolean test=runtimePath.
		  
		System.out.println("javaDestDir :"+javaDestDir );
		System.out.println("tllDestDir :"+tllDestDir);
		System.out.println("sourceDir :"+sourceDir);
		System.out.println("Runtime_TLL_path :"+Runtime_TLL_path);
		System.out.println("defaultPackagePrefix :"+defaultPackagePrefix);
		  
		 		  
		  //remove old problems on the mtl file
		removeMTLProblems(srcmtlFolder);

		  try {
		  	//comment retrouver le dossier où se trouve les .exe de ce fichier
			  model.removeMTLResources(binMtlFolder,srcmtlFolder);
			  model.removeMTLResources(srcJavaFolder,srcmtlFolder);
			  
		  } catch (Exception e) {
			  System.out.println("Warning: problems removing files :" + e.getMessage());
		  }

		  System.out.println("Generating tll and java objects for " + srcmtlFolder.getName() + "...");
		  
		  
		  //Call the MTL Compiler here
		  try{
		  	
		  BasicMtlCompiler.Compiler Comp=new BasicMtlCompiler.Compiler();
			System.out.println("Objet créé...");
			DOMConfigurator.configure ("E:/Projet/org.irisa.mtl.plugin/ThirdParty/MTL/log4j_configuration.xml");
		  Comp.compileFromDirectory(sourceDir,defaultPackagePrefix,tllDestDir,Runtime_TLL_path,javaDestDir);
		  }catch (Exception E){
			System.out.println("Problème de compilation..");
			return;
		  }
			
			System.out.println("Compilation terminée...");
		  //call the java compiler here 
		  
		  //refresh all output folders
		  outputFolder.refreshLocal(IResource.DEPTH_INFINITE, null);		
		
		 // Tag all new resources
		 model.tagMTLResources(outputFolder, srcmtlFolder);
		  
	  } catch (Exception e) {
//		  IMarker marker = srcmtlFolder.createMarker(MTLPlugin.MTL_PROBLEM);
//		  if (marker.exists()) {
//			  try {
//				  marker.setAttribute(IMarker.MESSAGE, "MTL Generation : " + e.getMessage());
//				  marker.setAttribute(IMarker.SEVERITY, IMarker.SEVERITY_ERROR);
//				  marker.setAttribute(IMarker.PRIORITY, IMarker.PRIORITY_HIGH);
//			  } catch (CoreException e1) {
//				  //ignore
//			  }
//		  }
//		  throw e;
			System.out.println(e.getMessage());
  }
  }
  
  
  
  public  String checkPathEnd(String path)
  {
  	
  	if ( path.charAt(path.length()-1)=='/' || path.charAt(path.length()-1)=='\\'){
  		return path;
  	}
  	return path+'/';//pb avec linux??????????
  }
  
  public  boolean checkSourcePath(IFolder srcFolder) throws Exception {
		
			IResource[] res = srcFolder.members();
			//System.out.println("Nb fichiers mtl :"+res.length);
			for (int i=0;i<res.length;i++){
				if ((res[i].getType()==IResource.FILE) && (res[i].toString().endsWith(".mtl"))){
					System.out.println(res[i]);
							return true;
						}
					}
		return false;
	}
	  	
	/**
	 * Prints out the string represented by the string buffer
	 */
	protected void printResultInConsole(String output) {
		try {
			IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
			MTLConsole console = (MTLConsole)page.findView(MTLConsole.CONSOLE_ID);
			
			if (console!=null) {
				console.setOutputText(output);
			} else if (MTLPlugin.getDefault().getPreferenceStore().getBoolean(PreferenceConstants.SHOW_OUTPUT_IN_CONSOLE)==true) {
				page.showView(MTLConsole.CONSOLE_ID);
				console = (MTLConsole)page.findView(MTLConsole.CONSOLE_ID);			
				console.setOutputText(output);
			}
		} catch (PartInitException e) {
			MTLPlugin.getDefault().getLog().log(
				new Status(
					IStatus.ERROR,
					MTLPlugin.PLUGIN_ID,
					0,
					MTLMessages.getString("CompilerAction.consoleViewOpeningProblem"),
					e));
		}
		
	}	
	
	/**
		 * Creates a string buffer from the given input stream
		 */
		protected String getStringFromStream(InputStream stream) throws IOException {
			StringBuffer buffer = new StringBuffer();
			byte[] b = new byte[100];
			int finished = 0;
			while (finished != -1) {
				finished = stream.read(b);
				if (finished != -1) {
					String current = new String(b, 0, finished);
					buffer.append(current);
				}
			}
			return buffer.toString();
		}
		
	/**
	 * Create markers according to the compiler output
	 */
	protected void createMarkers(String output, IFile file) throws CoreException {
		// first delete all the previous markers
		file.deleteMarkers(IMarker.PROBLEM, false, 0);

		// and then create the new ones
		Vector result = new Vector();
		String fullPath = file.getName();

		StringTokenizer tokenizer = new StringTokenizer(output, "\n"); //$NON-NLS-1$
		while (tokenizer.hasMoreElements()) {
			String current = (String) tokenizer.nextElement();
			if (current.indexOf(fullPath) != -1) {
				String line =
					current.substring(
						current.indexOf(fullPath) + fullPath.length(),
						current.length());
				String errorsLocation = line.substring(1, line.indexOf(":") - 1); //$NON-NLS-1$
				String message = line.substring(line.indexOf(":") + 2, line.length() - 1); //$NON-NLS-1$

				int lineNumber =
					Integer.parseInt(errorsLocation.substring(0, errorsLocation.indexOf(",")));	//$NON-NLS-1$

				Hashtable attributes = new Hashtable();
//				MarkerUtilities.setMessage(attributes, message);
//				if (message.startsWith(ERROR))
//					attributes.put(IMarker.SEVERITY, new Integer(IMarker.SEVERITY_ERROR));
//				else if (message.startsWith(WARNING))
//					attributes.put(IMarker.SEVERITY, new Integer(IMarker.SEVERITY_WARNING));
//				else
//					attributes.put(IMarker.SEVERITY, new Integer(IMarker.SEVERITY_INFO));
//				MarkerUtilities.setLineNumber(attributes, lineNumber);
//				MarkerUtilities.createMarker(file, attributes, IMarker.PROBLEM);
//
			}
		}

	}


}
