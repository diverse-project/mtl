/*
* $Id: MTLCodeGenerator.java,v 1.1 2004-07-30 14:09:28 sdzale Exp $
* Authors : ${user}
*
* Created on ${date}
* Copyright 2004 - INRIA - LGPL license
*/ 
package org.inria.mtl.builders;
import java.io.EOFException;
import java.io.File;
import java.util.Hashtable; 

import org.apache.log4j.xml.DOMConfigurator;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.IWorkspaceRunnable;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException; 
//import org.eclipse.jdt.internal.core.Util;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.runtime.QualifiedName; 
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.preference.IPreferenceStore; 
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.texteditor.MarkerUtilities; 
import org.eclipse.ui.views.tasklist.TaskList;
import org.inria.mtl.MTLPlugin;
import org.inria.mtl.core.MTLCore;
import org.inria.mtl.markers.MTLMarkers;
import org.inria.mtl.preferences.PreferencesConstants;
import org.inria.mtl.views.MTLConsole;
import org.irisa.triskell.MT.utils.MessagesHandler.CompilerMessage;
import org.irisa.triskell.MT.utils.MessagesHandler.MSGHandler;
//import org.irisa.triskell.MT.utils.MessagesHandler.MSGHandler;

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
	private IFolder outputFolder;
	private static IPath[] srcFolders ;
	private static IPath[] projFolders ;
	
	private MTLModel model;
	
	/** The error level of the  Parse Exceptions. */
	private static int errorLevel = MTLMarkers.ERROR;
	
	//boolean cleanMarkers=true;


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
		projFolders=model.projFolders;
		MTLCore.loadMtlClasspath();
		
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
		
		//cleanMarkers=true;
			
		//Manage folders
		IFolder output = srcmtlFolder.getProject().getFolder(store.getString(PreferencesConstants.OUTPUT_BUILDNAME));
		IFolder outputF = model.getOutputFolder();
			
		IFolder javasrcAll =outputF.getFolder(model.getJavaFolder().getName());
		IFolder binjava = outputF.getFolder(model.getBinJavaFolder().getName());
		//IFolder mtlsrc = srcmtlFolder.getProject().getFolder(model.getMtlFolder().getName());
		IFolder mtlsrc=srcmtlFolder;
		IFolder javasrcProj=javasrcAll.getFolder(mtlsrc.getName());
		if (!javasrcProj.exists())
			javasrcProj.create(true, true, null);
		
		
		IFolder binmtl = outputF.getFolder(model.getTllFolder().getName());
			
		//Manage Compiler path
		String MTLcompiler_path=store.getString(PreferencesConstants.MTL_COMPILER_PATH);
		String pluginPath =MTLPlugin.getDefault().getLocation();
		if (MTLcompiler_path.length()==0){
			String compilerFilePath=pluginPath.concat("MTL\\bin\\BasicMTLc.jar");
			pluginPath=pluginPath.concat("MTL");
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
			store.setValue(PreferencesConstants.MTL_COMPILER_PATH,MTLcompiler_path);
			
		}
   try {
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
		  String javaDestDir= javasrcProj.getLocation().toOSString();
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
		
		int nbProj=projFolders.length;
		//	   System.out.println("NB PROJ:"+nbProj);
		for ( int k=0;k<nbProj;k++){ 
			
					IWorkspaceRoot workspaceRoot = ResourcesPlugin.getWorkspace().getRoot();
					IProject[] jprojects =workspaceRoot.getProjects();
				try{
					
					for (int i=0;i<jprojects.length;i++){
						if (jprojects[i].getFullPath().toString().compareTo(projFolders[k].toString())==0){
							if (!jprojects[i].getProject().hasNature(MTLNature.NATURE_ID)){
										Shell pshell = new Shell();
														MessageDialog.openInformation(
														pshell,
														"MTL Compiler",
														"The incluse project is not a MTL project...");	
							}else{
								String installPath=jprojects[i].getProject().getLocation().toString();
								Runtime_TLL_path=Runtime_TLL_path.concat(";");
								Runtime_TLL_path=Runtime_TLL_path.concat(installPath);//permet de g�rer le fait que installPath se termine par /
								//??????????????????? We can have a problem here if all the projects are not in, folders (build\tll)
								Runtime_TLL_path=Runtime_TLL_path.concat("\\build\\tll\\");
						}
						}
					}
			
				}catch(Exception E){
					//System.out.println("MONPROJ NOT OK:"+projFolders[k].toString());
				}
		}
		  
 	
		//Run Server
	if (!(MTLConsole.serverAction==null))
	{
		System.out.println(" SERVER CODE GEN :"+MTLConsole.serverAction.isRunning);
		if (!MTLConsole.serverAction.isRunning){
			MTLConsole.serverAction.setRunning(false);
			MTLConsole.serverAction.run();
			System.out.println(" SERVER CODE GEN AFTER :"+MTLConsole.serverAction.isRunning);
		}
		//MTLConsole.getConsole().
	}
	
		//
		try {
			//comment retrouver le dossier o� se trouve les .exe de ce fichier
			  model.removeMTLResources(binmtl,mtlsrc);
			  model.removeMTLResources(javasrcProj,mtlsrc);
			  
		  } catch (Exception e) {
			  System.out.println("Warning: problems removing files :" + e.getMessage());
		  }

		System.out.println("Generating tll and java objects for " + mtlsrc.getName() + "...");
		
	
		//	model.readTag(outputF);
		
		
		//initialiser le vecteur des messages
			MSGHandler.reinit();
			
			System.out.println("REINIT..."); 
		  
		 //Call the MTL Compiler here
			  	
		try{
			BasicMtlCompiler.Compiler Comp=new BasicMtlCompiler.Compiler();
				//
			String logFile=MTLPlugin.getDefault().getLocation();
			logFile=logFile.concat("\\MTL\\bin\\log4j_configuration.xml");
			//
			DOMConfigurator.configure (logFile);
			System.out.println("Problem with log4j...");
		  	System.out.println("LOG4J...");		 
	      	Comp.compileFromDirectory(sourceDir,defaultPackagePrefix,tllDestDir,Runtime_TLL_path,javaDestDir);
		  }catch (Exception E){
			System.out.println("Erreur Compilation MTL");
			System.out.println(" Call Compiler : "+E.getMessage());
			
			// Markers li�s au r�sultat du d�roulement de la compilation
			IWorkspaceRoot wRoot = MTLPlugin.getWorkspace().getRoot();
			
			try{
			
				wRoot.deleteMarkers(IMarker.PROBLEM, false, IResource.DEPTH_ZERO);
				
			}catch (CoreException e){
					//Something when wrong
				}
			
			
			
			IMarker marker =wRoot.createMarker(IMarker.PROBLEM);
			if (marker.exists()) {
			      try {
			         marker.setAttribute(IMarker.MESSAGE, "FATAL ERROR IN COMPILATION");
			         marker.setAttribute(IMarker.PRIORITY, IMarker.PRIORITY_HIGH);
			      } catch (CoreException e) {
			         // You need to handle the case where the marker no longer exists      }
			      }
			}
		    }
	
			
			System.out.println("Compilation termin�e...");
		  //call the java compiler here
		  
		  //Manage compiler messages
			java.util.Vector vMessages= MSGHandler.allMessages;
			createMarkers(vMessages);
			
		 
			//	Tag all new resources
			model.tagMTLResources(javasrcProj, srcmtlFolder);
			
			model.tagTLLResources(binmtl,mtlsrc);
			
		  
		  //refresh all output folders
		   outputF.refreshLocal(IResource.DEPTH_INFINITE, null);
		   
		   
		   model.readTag(outputF);	
		
		 
		  
	  } catch (Exception e) {
		  throw e;
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
		//System.out.println("CHECK");
			IResource[] res = srcFolder.members();
			for (int i=0;i<res.length;i++){
				if ((res[i].getType()==IResource.FILE) && (res[i].toString().endsWith(".mtl"))){
							return true;
						}
					}
		return false;
	}
	  	
		
	/**
	 * Create markers according to the compiler output
	 */
	protected void createMarkers(java.util.Vector v) throws CoreException {
		
		// and then create the new ones
		
		System.out.println("CREATE MARKERS :"+v.size());
		for (int i=0;i<v.size();i++){
			if (v.elementAt(i) instanceof CompilerMessage){ 
		    
		//			first delete all the previous markers
				CompilerMessage ReX=(CompilerMessage)v.elementAt(i);
		 		System.out.println("Fichier :"+ReX.getFileName());
		 		System.out.println("Ligne :"+ReX.getLineNumber());
		 		System.out.println("Message :"+ReX.getMessage());
		 		System.out.println("Type de message :"+ReX.getMessageType());
		 		System.out.println("Ligne origine :"+ReX.getOriginLineNumber());
//				On supprime les markers au premier passage seulement
				createMarkersTask(ReX, MTLMarkers.cleanMarkers);
		 		System.out.println("*************************************");
		 		//if (ReX.getMessageType().compareTo("warn")==0){
		 		
		 		
		 		
		 		
			}
		}

	}
	
	/**
	 * Create markers according to the compiler output
	 */
		public void createMarkersTask(CompilerMessage cMessage,boolean cleanMarkers) throws CoreException {
			System.out.println("MARKER TASK");
		String Message=cMessage.getMessage();
		IFile currFile=findFile(cMessage);
		IMarker[] problems = null;
		int depth = IResource.DEPTH_INFINITE;
		if (!(currFile==null)){
		if (currFile.exists()){
		  try {
		      problems = currFile.findMarkers(IMarker.PROBLEM, true, depth);
		      System.out.println("Probl�mes :"+problems.length);
		 } catch (CoreException e) {
		 	System.out.println("Probl�mes :"+e);
		      // something went wrong
		   }
		}
		}
		//currFile.d
		//System.out.println("Currfile :"+currFile+"  clean :"+cleanMarkers);
		if (cleanMarkers && (currFile!=null)){
			System.out.println("Enlever les MARKERS sur :"+currFile.getName());
			currFile.deleteMarkers(IMarker.PROBLEM, true, 0);
			MTLMarkers.cleanMarkers=false;
		}
		//System.out.println("Message Trait�:"+Message);
		 
		
		if ((Message.indexOf("ANTLRException")==0) &&(cMessage.getMessageType().compareTo("warn")==0)){
		
			String messageAll = Message.substring(Message.indexOf(" on ") + 4, Message.length() ); //$NON-NLS-1$
			String messageFile = messageAll.substring(0, messageAll.indexOf(",")); //$NON-NLS-1$
			String messageDelta = messageAll.substring(messageAll.indexOf(", ") + 2, messageAll.length()); //$NON-NLS-1$
			String messageLine = messageDelta.substring(messageDelta.indexOf("line ")+5 ,messageDelta.indexOf(":")); //$NON-NLS-1$
			String messageDesc = messageDelta.substring(messageDelta.indexOf(":",messageDelta.indexOf(":")+1)+1, messageDelta.length()); //$NON-NLS-1$
			Hashtable attributes = new Hashtable();
			MarkerUtilities.setMessage(attributes, messageDesc);
			attributes.put(IMarker.SEVERITY, new Integer(IMarker.SEVERITY_ERROR));
		
			try{
					int lineNumber =Integer.parseInt(messageLine);	//$NON-NLS-1$
					MarkerUtilities.setLineNumber(attributes, lineNumber);
			}catch (Exception e){
				System.out.println("Error Line number :");
				}
			
			try{
			
			System.out.println("Cr�er les MARKERS sur :"+currFile.getName()+"  Message :"+cMessage.getMessage());
			MarkerUtilities.createMarker(currFile, attributes, IMarker.PROBLEM);
				
			//And refresh the compilation unit folder
			currFile.getParent().refreshLocal(IResource.DEPTH_ONE, null);
			}catch(Exception E)
			{
				System.out.println("Pb sur les markers :"+E.getMessage());
			}
		 }else{
					Hashtable attributes = new Hashtable();
					MarkerUtilities.setMessage(attributes, Message);
					if (cMessage.getMessageType()=="error")attributes.put(IMarker.SEVERITY, new Integer(IMarker.SEVERITY_ERROR));
					if (cMessage.getMessageType()=="info")attributes.put(IMarker.SEVERITY, new Integer(IMarker.SEVERITY_INFO));
					if (cMessage.getMessageType()=="debug")attributes.put(IMarker.SEVERITY, new Integer(IMarker.SEVERITY_WARNING));
					
					if ((cMessage.getFileName()!=null) ){
						if ((cMessage.getMessageType()=="error")||(cMessage.getMessageType()=="warn")){
						//String subPath=cMessage.getFileName().substring(MTLCore.getProject().getLocation().toString().length()+1,cMessage.getFileName().length());
						//IFile currFile=MTLCore.getProject().getFile(subPath);
						
						System.out.println("Cr�er les MARKERS sur 2 :"+currFile.getName()+"  Message :"+cMessage.getMessage());
						MarkerUtilities.createMarker(currFile, attributes, IMarker.PROBLEM);
					}
					}	
				}
		}

	
		public IFile findFile(CompilerMessage cMessage){
			IFile currFile=null;
			String Message=cMessage.getMessage();
			if ((Message.indexOf("ANTLRException")==0) &&(cMessage.getMessageType().compareTo("warn")==0)){
			//Erreur g�n�r�e par ANTLR
				String messageAll = Message.substring(Message.indexOf(" on ") + 4, Message.length() ); //$NON-NLS-1$
				String messageFile = messageAll.substring(0, messageAll.indexOf(",")); //$NON-NLS-1$
				String N=messageFile.substring(messageFile.indexOf(MTLCore.getProject().getName())+MTLCore.getProject().getName().length()+1);
				String subPath=cMessage.getFileName().substring(MTLCore.getProject().getLocation().toString().length()+1,cMessage.getFileName().length());
			
				 currFile=MTLCore.getProject().getFile(subPath);
			}else{//Propri�t� file non nulle
				if (cMessage.getFileName()!=null){
					String subPath=cMessage.getFileName().substring(MTLCore.getProject().getLocation().toString().length()+1,cMessage.getFileName().length());
					 currFile=MTLCore.getProject().getFile(subPath);
				}
				//autres cas
			}
			return currFile;
		}

		


}