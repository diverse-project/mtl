/*
* $Id: MTLCodeGenerator.java,v 1.3 2004-05-28 16:52:46 sdzale Exp $
* Authors : ${user}
*
* Created on ${date}
* Copyright 2004 - INRIA - LGPL license
*/ 
package org.inria.mtl.plugin.builders;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import org.apache.log4j.xml.DOMConfigurator;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRunnable;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.QualifiedName;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.swt.widgets.Shell;
import org.inria.mtl.plugin.MTLPlugin;
import org.inria.mtl.plugin.core.MTLCore;
import org.inria.mtl.plugin.markers.MTLMarkers;
import org.inria.mtl.plugin.preferences.PreferenceConstants;


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
	
	/** The error level of the  Parse Exceptions. */
	private static int errorLevel = MTLMarkers.ERROR;


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
				System.out.println("Generator run:"+srcMtlFolder.toString());
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
		String defaultPrefix =srcmtlFolder.getName();
		
		//Manage folders
		IFolder output = srcmtlFolder.getProject().getFolder(store.getString(PreferenceConstants.OUTPUT_BUILDNAME));
		//store.s
		//Si on choisit de stocker les bin d'un rep source dans un repertoire particulier de même
		//nom alors c'est ici qu'on fera les changements
		IFolder javasrc =output.getFolder(store.getString(PreferenceConstants.FJAVA_SRCNAME));
		IFolder binjava = output.getFolder(store.getString(PreferenceConstants.FJAVA_BINNAME));
		IFolder mtlsrc = srcmtlFolder.getProject().getFolder(store.getString(PreferenceConstants.FMTL_SRCNAME));
		IFolder binmtl = output.getFolder(store.getString(PreferenceConstants.FMTL_BINNAME));
		
		
		//Manage Compiler path
		String MTLcompiler_path=store.getString(PreferenceConstants.MTL_COMPILER_PATH);
		String pluginPath =MTLPlugin.getDefault().getLocation();
		if (MTLcompiler_path.length()==0){
			String compilerFilePath=pluginPath.concat("\\MTL\\bin\\BasicMTLc.jar");
			String compilerPath=pluginPath.concat("\\MTL");
			File compFile = new File(compilerFilePath); 
			if (!(compFile.exists())){
				Shell shell = new Shell();
							MessageDialog.openInformation(
							shell,
							"MTL Compiler",
							"Compiler path not define...");
							return;
			}
			MTLcompiler_path=pluginPath;
		}
   try {
   		  System.out.println("Generate :"+mtlsrc.toString()+" exists  "+mtlsrc.exists());
		  String lastGen = mtlsrc.getPersistentProperty(new QualifiedName(MTLPlugin.PLUGIN_ID, MTLModel.TLL_LASTGENTIME));
		  String newGen = new Long(srcmtlFolder.getModificationStamp()).toString();
		  if (lastGen != null && lastGen.equals(newGen)) {
			  return;
		  }
	mtlsrc.setPersistentProperty(new QualifiedName(MTLPlugin.PLUGIN_ID, MTLModel.TLL_LASTGENTIME), newGen);
		
		
	   	
		  
		  //Find sourceDir parameter for codeGenerator
		  String sourceDir= mtlsrc.getLocation().toOSString();
		  sourceDir=checkPathEnd(sourceDir);
		  //System.out.println("sourceDir :"+sourceDir);
		
		  //		Find defaultPackagePrefix parameter for codeGenerator
		   String defaultPackagePrefix=mtlsrc.getName();

		  
		  //Find java Folder source parameter for codeGenerator
		  String javaDestDir= javasrc.getLocation().toOSString();
		  javaDestDir=checkPathEnd(javaDestDir);
		 
		  
		  //Find tll Folder source parameter for codeGenerator
		  String tllDestDir= binmtl.getLocation().toOSString();
		  tllDestDir=checkPathEnd(tllDestDir);
		 		  
		  //Compiler parameters
		  // Find list of tll exported Folders parameter for codeGenerator
		  //Verify that the compiler path have been defined
		
		 
		MTLcompiler_path=checkPathEnd(MTLcompiler_path);
		  
		String BasicMTLc_jar=MTLcompiler_path.concat("bin\\BasicMTLc.jar");
		String Runtime_TLL_path=MTLcompiler_path.concat("Runtime\\src\\TLL\\");
		String BasicMTLRuntime_jar=MTLcompiler_path.concat("bin\\BasicMTLruntime.jar");
		  
//		System.out.println("javaDestDir :"+javaDestDir );
//		System.out.println("tllDestDir :"+tllDestDir);
//		System.out.println("sourceDir :"+sourceDir);
//		System.out.println("Runtime_TLL_path :"+Runtime_TLL_path);
//		System.out.println("defaultPackagePrefix :"+defaultPackagePrefix);
//		System.out.println("BasicMTLRuntime :"+BasicMTLRuntime_jar);
//		  
		 		  
		  //remove old problems on the mtl file
		removeMTLProblems(mtlsrc);

		  try {
		  	//comment retrouver le dossier où se trouve les .exe de ce fichier
			  model.removeMTLResources(binmtl,mtlsrc);
			  model.removeMTLResources(javasrc,mtlsrc);
			  
		  } catch (Exception e) {
			  System.out.println("Warning: problems removing files :" + e.getMessage());
		  }

		  System.out.println("Generating tll and java objects for " + mtlsrc.getName() + "...");
		  
		  
		  //Call the MTL Compiler here
		  try{
		  	
		  BasicMtlCompiler.Compiler Comp=new BasicMtlCompiler.Compiler();
		  Comp.LogExceptions.clearCompilerResult();
		  System.out.println("Objet créé...");
		  String logFile=MTLPlugin.getDefault().getLocation();
		  logFile=logFile.concat("\\MTL\\bin\\log4j_configuration.xml");
		  DOMConfigurator.configure (logFile);
		  Comp.compileFromDirectory(sourceDir,defaultPackagePrefix,tllDestDir,Runtime_TLL_path,javaDestDir);
		  java.util.Vector problems = Comp.LogExceptions.getAntLrException();
		  createMarkers(problems);
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
//		  IMarker marker = mtlsrc.createMarker(MTLPlugin.MTL_PROBLEM);
//		  if (marker.exists()) {
//			  try {
//				  marker.setAttribute(IMarker.MESSAGE, "MTL Generation : " + e.getMessage());
//				  marker.setAttribute(IMarker.SEVERITY, IMarker.SEVERITY_ERROR);
//				  marker.setAttribute(IMarker.PRIORITY, IMarker.PRIORITY_HIGH);
//			  	} catch (CoreException e1) {
//				  //ignore
//			  }
//		  }
		  throw e;
//			System.out.println(e.getMessage());
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
					//System.out.println(res[i]);
							return true;
						}
					}
		return false;
	}
	  	
	/**
	 * Prints out the string represented by the string buffer
	 */
	protected void printResultInConsole(String output) {
//		try {
//			IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
//			MTLConsole console = (MTLConsole)page.findView(MTLConsole.CONSOLE_ID);
//			
//			if (console!=null) {
//				console.setOutputText(output);
//			} else if (MTLPlugin.getDefault().getPreferenceStore().getBoolean(PreferenceConstants.SHOW_OUTPUT_IN_CONSOLE)==true) {
//				page.showView(MTLConsole.CONSOLE_ID);
//				console = (MTLConsole)page.findView(MTLConsole.CONSOLE_ID);			
//				console.setOutputText(output);
//			}
//		} catch (PartInitException e) {
//			MTLPlugin.getDefault().getLog().log(
//				new Status(
//					IStatus.ERROR,
//					MTLPlugin.PLUGIN_ID,
//					0,
//					MTLMessages.getString("CompilerAction.consoleViewOpeningProblem"),
//					e));
//		}
//		
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
	protected void createMarkers(java.util.Vector v) throws CoreException {
		
		// and then create the new ones
			for (int i=0;i<v.size();i++){
		    if (v.elementAt(i) instanceof antlr.RecognitionException){ 
		    
		//			first delete all the previous markers
				antlr.RecognitionException ReX=(antlr.RecognitionException)v.elementAt(i);
				System.out.println(ReX.fileName+"   "+ReX.line+ "  "+ReX.getMessage()+"  "+ReX.getFilename());
				//IFile file= IFile(new File(ReX.fileName)); 
		 		//file.deleteMarkers(IMarker.PROBLEM, false, 0);
		//new markers
				//MTLMarkers.setMarker(file,ReX.getMessage(),ReX.getLine(),errorLevel,ReX.getLocalizedMessage());	 		
			}
		}

	}

//
//	/** Create a new task. */
//	public static final void createNewTask(final int todoStart) {
//	  final String  todo = SimpleCharStream.currentBuffer.substring(todoStart,
//																	SimpleCharStream.currentBuffer.indexOf("\n",
//																										   todoStart)-1);
//	  if (!PARSER_DEBUG) {
//		try {
//		  setMarker(fileToParse,
//					todo,
//					SimpleCharStream.getBeginLine(),
//					MTLMarkers.TASK,
//					"Line "+SimpleCharStream.getBeginLine());
//		} catch (CoreException e) {
//		  MTLPlugin.log(e);
//		}
//	  }
//	}
//
	/**
	  * Create marker for the parse error.
	  * @param e the ParseException
	  */
//	 private static void setMarker(final antlr.RecognitionException e) {
//	   try {
//		 if (errorStart == -1) {
//		   setMarker(fileToParse,
//					 errorMessage,
//					 e.currentToken.sourceStart,
//					 e.currentToken.sourceEnd,
//					 errorLevel,
//					 "Line " + e.currentToken.beginLine+", "+e.currentToken.sourceStart+":"+e.currentToken.sourceEnd);
//		 } else {
//		   setMarker(fileToParse,
//					 errorMessage,
//					 errorStart,
//					 errorEnd,
//					 errorLevel,
//					 "Line " + e.currentToken.beginLine+", "+errorStart+":"+errorEnd);
//		   errorStart = -1;
//		   errorEnd = -1;
//		 }
//	   } catch (CoreException e2) {
//		 MTLPlugin.log(e2);
//	   }
//	 }

}
