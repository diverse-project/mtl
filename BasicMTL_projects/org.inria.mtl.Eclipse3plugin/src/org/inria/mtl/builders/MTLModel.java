/*
* $Id: MTLModel.java,v 1.4 2004-10-29 11:33:48 edrezen Exp $
* Authors : ${user}
*
* Created on ${date}
* Copyright 2004 - INRIA - LGPL license
*/ 
package org.inria.mtl.builders;

import java.util.ArrayList;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.QualifiedName;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.core.resources.IncrementalProjectBuilder;
import org.eclipse.core.runtime.IProgressMonitor;
import org.inria.mtl.MTLPlugin;
import org.inria.mtl.core.MTLCore;
import org.inria.mtl.preferences.PreferencesConstants;
import org.inria.mtl.views.MTLConsole;

/**
 *  The model used to manipulate properties of a MTL project
 *
 *@author     serge DZALE
 *@created    20 April 2004
 */
public class MTLModel {
	
	/**
	 *  property name attached to MTL tll files used to indicate the 
	 * association with mtl files. 
	 */
	public final static String MTL_OWNER_PROP = "mtlowner";
	/**
	 *  property name attached to files used to indicate when they were
	 * last used in java file generation
	 */
	public final static String TLL_LASTGENTIME = "tlllastgen";
	/**
	 *  property name attached to project used to indicate the name of the main class
	 */
	public final static String MTL_MAINCLASS = "mtlmainclass";
	/**
	 *  property name attached to project used to indicate the name of the TLL Folder path
	 */
	public final static String MTL_TLLFolder = "prjtllfolder";
	/**
	 *  property name attached to project used to indicate the name of the TLL Runtime Folder path
	 */
	public final static String TLL_RUNTIMEFOLDER = "prjtllruntime";
	/**
	*  property name attached to project used to indicate the name of the main class
	*/
   public static boolean PROCESS_REFERENCE = false;

	public  static IProject proj;
	// tll folder root
	static public IFolder tllFolder;
	// mtl folder root
	private IFolder srcMtlFolder;
	//java source folder root
	public static IFolder srcJavaFolder;
	//	java bin folder root
	private IFolder binJavaFolder;
	//MTL classpath
	private IFile mtlclasspath;
	//Source Folder 
	private IFolder srcTagFolder;
	//MTL output
	private IFolder output;
	//	MTL Main Class Folder
	private String mainClassFolder="";
	private boolean refFolder =false;
	public static IPath[] srcFolders ;
	public static IPath[] libFolders ;
	public static IPath[] projFolders ;
	public static ArrayList cFolders = new ArrayList();
	static IProgressMonitor   monitor;
	
	 /**
	 *  Constructor for MTLProjectModel
	 *@param  proj  Description of the Parameter
	 **/
	public MTLModel(IProject proj) {
		super();
		MTLModel.proj = proj;
		IPreferenceStore store=PreferencesConstants.getPreferenceStore();
		//Permanent output folder
		IPath outputFolder=new Path(store.getString( PreferencesConstants.OUTPUT_BUILDNAME));
		output =proj.getFolder(outputFolder);
		//permanent mtl source folder
		IPath srcMtl=new Path(store.getString( PreferencesConstants.FMTL_SRCNAME));
		srcMtlFolder =proj.getFolder(srcMtl);
		IPath tll=new Path(store.getString( PreferencesConstants.FMTL_BINNAME));
		tllFolder =output.getFolder(tll);
		IPath binJava=new Path(store.getString( PreferencesConstants.FJAVA_BINNAME));
		binJavaFolder =output.getFolder(binJava);
		IPath srcJava=new Path(store.getString( PreferencesConstants.FJAVA_SRCNAME));
		srcJavaFolder =output.getFolder(srcJava);
		mtlclasspath = proj.getFile(".mtlclasspath");
	}
	/**
	*  Gets the project attribute of the MTLProjectModel object
	*  @return    The project value
	**/
		public static IProject getProject() {
			return proj; 
		}
		public  void setProject(IProject project) {
			proj = project;
		}
		public IFolder getJavaFolder() {
				return srcJavaFolder;
		}
		public IFolder getMtlFolder() {
				return srcMtlFolder;
			}
		public IFolder getTllFolder() {
				return tllFolder;
				}
		public IFolder getBinJavaFolder() {
					return binJavaFolder;
			}
		public IFolder getOutputFolder() {
					return output;
			}
		public String getMainClassFolder() {
					return mainClassFolder;
			}
		public void setMainClassFolder(String mcf) {
			 mainClassFolder=mcf;
				}
	 /**
	 *  removes all tll files that were  generated  for this project.
	 **/
	public void prepareFullBuild() {
		try {
			resetMTLGenerateFiles(srcMtlFolder);
		} catch (Exception e) {
			System.out.println("Warning: deleting MTL resources: " + e.getMessage());
		}
	}
	/**
	 *  Resets the TLL_LASTGENTIME for all mtl files within this project
	 * including folder,sub folders, method is recursive
	 *
	 *@param  fldr           the folder to reset
	 *@exception  Exception  
	 **/
	private void resetMTLGenerateFiles(IFolder mtlFolder) throws Exception {
		//move the tag on the source mtl folder
		mtlFolder.setPersistentProperty(new QualifiedName(MTLPlugin.PLUGIN_ID, TLL_LASTGENTIME), "");
		IResource[] res = mtlFolder.members();
		//move the tag on all the mtl file 
		for (int i = 0; i < res.length; i++) {
			if (res[i].getType() == IResource.FILE) {
				res[i].setPersistentProperty(new QualifiedName(MTLPlugin.PLUGIN_ID, TLL_LASTGENTIME), "");
			}
		}
	}

 /**
	 * Processes incremental events from resources and directs the generation of java
	 * files from mtl files or the removal of tll and java files
	 *
	 *@param  delta  
	 *@return        true if successful
	 **/
	public boolean processDelta(IResourceDelta delta) {
		IResource res = delta.getResource();
		// verify if the ressource is in source folders 
		if (findResource(res.getFullPath()))MTLConsole.cleanConsole();
		//on est dans la même compil
		MTLBuilder.acceptClean=false;
		int kind = delta.getKind();
		if (kind == IResourceDelta.ADDED || kind == IResourceDelta.CHANGED || kind == IResourceDelta.REPLACED) {
			processResource(res);
			refFolder=false;
		} else if (kind == IResourceDelta.REMOVED) {
			removeResource(res);
		}
		return true;
		// visit the children
	}
	 /**
	 *  removes resources from the project, responds to tll and java file extensions
	 *@param  res  Description of the Parameter
	 *@return      Description of the Return Value
	 **/
	public boolean removeResource(IResource res) {
		try {
			if (res.getType() == IResource.FILE) {
				if (res.getFileExtension().equalsIgnoreCase("mtl")) {
					removeTllJava((IFile) res);
				}
			}else {
				if (res.getType() == IResource.FOLDER) {
				 removeTllJavaFolder((IFolder)res);
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
//		 visit the children
	}
	public boolean findResource(IPath res) {
			MTLCore.loadMtlClasspath();
			int taille =MTLModel.srcFolders.length;
			
			try {
					for (int i=0;i<MTLModel.srcFolders.length;i++){
							if (res.equals(MTLModel.srcFolders[i])) return true;
					}
				
			} catch (Exception e) {
				System.out.println("Erreur find ressource");
				//e.printStackTrace();
			}
			return false;
	}
	 /**
	 *  processes events from mtl files and directs the generation of tll files 
	 *@param  res  Description of the Parameter
	 *@return      Description of the Return Value
	 **/
	public boolean processResource(IResource res) {
		try {
			if (res.getType() == IResource.FOLDER) {
				if (findResource(res.getFullPath())) {
					referencesSourceFolders((IFolder)res);
					processTll((IFolder) res); 
					} 
			}
			if (res.getType() == IResource.FILE) {
				//Change the timestamp of the parent folder
				if (res.getParent().getType()==IResource.FOLDER){
					IFolder fld = (IFolder)res.getParent();
				if (findResource(fld.getFullPath())) {
					referencesSourceFolders((IFolder)fld);
				String newGen = new Long(fld.getModificationStamp()-1).toString();
				fld.setPersistentProperty(new QualifiedName(MTLPlugin.PLUGIN_ID, MTLModel.TLL_LASTGENTIME), newGen);
				processTll((IFolder) fld);
				}
			  }
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
		// visit the children
	}
	 /**
	 *  Instantiates and runs the MTLCodeGenerator for the given mtl file
	 *@param  folder           Description of the mtl folder which is modified 
	 *@exception  Exception  Description of the Exception
	 **/
	public void processTll(IFolder mtlFolder) throws Exception {
		MTLPlugin.videConsole=false;
		System.out.println("Process TLL:"+mtlFolder.toString());
		if(!cFolders.contains(mtlFolder)){
			cFolders.add(mtlFolder);
		//  Génération du code TLL
			MTLCodeGenerator jcg = new MTLCodeGenerator(mtlFolder,tllFolder,srcJavaFolder, output);
		//s'assurer que le fait que la méthode n'est pas statique ne pose aucun problème
			MTLPlugin.getWorkspace().run(jcg, null);
		}
	}
	  
	/**
	 *  removes all tll and files generated by the mtl file given
	 *@param  file           mtl file resource
	 *@exception  Exception
	 **/  
	public void removeTllJava(IFile file) throws Exception {
		IFolder fld = (IFolder)file.getParent();
		if (fld.exists())removeTllJavaFolder(fld);
	}
	
	/**
	 *  removes all tll and files generated by the mtl file
	 * given
	 *@param  mtlFolder           mtl folder resource
	 *@exception  Exception
	 **/  
	public void removeTllJavaFolder(IFolder mtlFolder) throws Exception {
		
		removeMTLResources(tllFolder, mtlFolder);
		IFolder javaFolder=srcJavaFolder.getFolder(mtlFolder.getName());
		if (javaFolder.exists())removeMTLResources(javaFolder, mtlFolder); 
	}
	
	/**
	 *  Used after generation of TLL and Java files to tag any new files that
	 * have not already been tagged. They are with the TLL_OWNER_PROP with last generated 
	 * mtl file name. (recursive method)
	 *@param  tllfolder or JavaFolder     The tll or Java folder to search
	 *@param  srcMtlFolder            the mtl  folder used in tag
	 *@exception  Exception
	 **/  
	public void tagMTLResources(IFolder srcfolder, IFolder srcMtlFolder) throws Exception {
		IResource[] res = srcfolder.members();
		for (int i = 0; i < res.length; i++) {
			if (res[i].getType() == IResource.FILE) {
				String propVal = null;
				propVal = res[i].getPersistentProperty(new QualifiedName(MTLPlugin.PLUGIN_ID, MTLModel.MTL_OWNER_PROP));
				if (propVal == null) {
					res[i].setPersistentProperty(new QualifiedName(MTLPlugin.PLUGIN_ID, MTLModel.MTL_OWNER_PROP), srcMtlFolder.getName());
				}
			}
		}
		srcfolder.setPersistentProperty(new QualifiedName(MTLPlugin.PLUGIN_ID, MTLModel.MTL_OWNER_PROP), srcMtlFolder.getName());
	}
	
	public void tagTLLResources(IFolder tllfolder, IFolder srcMtlFolder) throws Exception {
			IResource[] res = tllfolder.members();
			for (int i = 0; i < res.length; i++) {
				if (res[i].getType() == IResource.FILE) {
					String propVal = null;
					propVal = res[i].getPersistentProperty(new QualifiedName(MTLPlugin.PLUGIN_ID, MTLModel.MTL_OWNER_PROP));
					if (propVal == null) {
						res[i].setPersistentProperty(new QualifiedName(MTLPlugin.PLUGIN_ID, MTLModel.MTL_OWNER_PROP), srcMtlFolder.getName());
					}
				}
			}
	 }
	
	public void readTag(IFolder srcfolder) throws Exception {
			IResource[] res = srcfolder.members();
			for (int i = 0; i < res.length; i++) {
				if (res[i].getType() == IResource.FILE) {
					String propVal = null;
					propVal = res[i].getPersistentProperty(new QualifiedName(MTLPlugin.PLUGIN_ID, MTLModel.MTL_OWNER_PROP));
				}else{
					if (res[i].getType() == IResource.FOLDER) {
							String propVal = null;
							propVal = res[i].getPersistentProperty(new QualifiedName(MTLPlugin.PLUGIN_ID, MTLModel.MTL_OWNER_PROP));
							readTag((IFolder)res[i]);
				}
			}
	 }
	}
	/**
	 *  removes all tll or Java files that were soley generated by mtl folder given
	 * (recursive method)
	 *
	 *@param  tllFolder  or JavaFolder        the Tll or Java folder
	 *@param  mtl            the mtl folder used
	 *@exception  Exception  
	 **/
	public void removeMTLResources(IFolder destFolder, IFolder mtl) throws Exception {
	 if (destFolder.exists()){
	 		IResource[] res = destFolder.members();
		
			for (int i = 0; i < res.length; i++) {
			if (res[i].getType() == IResource.FILE) {
				String propVal = null;
				propVal = res[i].getPersistentProperty(new QualifiedName(MTLPlugin.PLUGIN_ID, MTLModel.MTL_OWNER_PROP));
				if (propVal != null && propVal.equals(mtl.getName())) {
					if (!refFolder)
					res[i].delete(true, null);
				}
			} else if (res[i].getType() == IResource.FOLDER) {
				removeMTLResources((IFolder) res[i], mtl);
				if (((IFolder) res[i]).members().length == 0) {
					res[i].delete(true, null);
				}
			}
		}
	 }
	}
	/**
	*  Compile all source folders wich are after the selected source folder
	* (recursive method)
	*
	*@param  srcFolder      the selected source  folder
	**/
	public void referencesSourceFolders(IFolder srcFolder){
		refFolder=true;
		int i=0;
		while (i<MTLModel.srcFolders.length){
		if (MTLModel.srcFolders[i].toString().equals(srcFolder.getFullPath().toString())){
				return;
			}
			IFolder srcRefFolder=proj.getFolder(MTLModel.srcFolders[i].removeFirstSegments(1));
			try{
			long oldGen = srcRefFolder.getModificationStamp();
			String lastGen = srcRefFolder.getPersistentProperty(new QualifiedName(MTLPlugin.PLUGIN_ID, MTLModel.TLL_LASTGENTIME));
			
			//On fait en sorte que le fichier soit obligatoirement compilé
			String newGen=((oldGen==5)?new Long(oldGen-1).toString():new Long(oldGen+2).toString());
			srcRefFolder.setPersistentProperty(new QualifiedName(MTLPlugin.PLUGIN_ID, MTLModel.TLL_LASTGENTIME), newGen);
			processResource(srcRefFolder);
			//
			}catch (Exception E){
				System.out.println(E.getMessage());
			}
			i++;
		}
	}
	
	/**
	*  Find and build all projects referenced in the project classpath
	*
	*@param  project      the selected project
	**/
	public static void buildReferencesProjects(IProject project){ 
		MTLCore.loadMtlClasspath();
		IPath[] prjPath=MTLModel.projFolders;
		IProject refProject=null;
		
		for (int i=0;i<prjPath.length;i++){
			try{
				IWorkspaceRoot workspaceRoot = ResourcesPlugin.getWorkspace().getRoot();
				refProject=workspaceRoot.getProject(prjPath[i].lastSegment());
				refProject.build(IncrementalProjectBuilder.FULL_BUILD,monitor);
			}catch (Exception E){
			}
		}
	}
}