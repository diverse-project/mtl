/*
* $Id: MTLModel.java,v 1.2 2004-05-17 10:17:00 sdzale Exp $
* Authors : ${user}
*
* Created on ${date}
* Copyright 2004 - INRIA - LGPL license
*/ 
package org.inria.mtl.plugin.builders;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.QualifiedName;

import org.inria.mtl.plugin.MTLPlugin;
import org.inria.mtl.plugin.core.MTLCore;
import org.inria.mtl.plugin.preferences.PreferenceConstants;

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
	 *  property name attached to schema files used to indicate when they were
	 * last used in java file generation
	 */
	public final static String TLL_LASTGENTIME = "tlllastgen";

	private  static IProject proj;
	// tll folder root
	private  IFolder tllFolder;
	// mtl folder root
	private IFolder srcMtlFolder;
	//java source folder root
	private IFolder srcJavaFolder;
	//MTL classpath
	private IFile mtlclasspath;
	//Source Folder 
	private IFolder srcTagFolder;
	//MTL output
	private IFolder output;
	
	//
	public static IPath[] srcFolders ;
	public static IPath[] libFolders ;
	public static IPath[] projFolders ;
	
	 /**
	 *  Constructor for MTLProjectModel
	 *@param  proj  Description of the Parameter
	 **/
	public MTLModel(IProject proj) {
		super();
		this.proj = proj;
		//tllFolder = (IFolder) proj.findMember(MTLPlugin.FMTL_BINNAME);
		tllFolder = PreferenceConstants.FMTL_BINNAME;
		//srcMtlFolder = (IFolder) proj.findMember(MTLPlugin.FMTL_SRCNAME);
		srcMtlFolder = PreferenceConstants.FMTL_SRCNAME;
		//srcJavaFolder = (IFolder) proj.findMember(MTLPlugin.FJAVA_SRCNAME);
		srcJavaFolder = PreferenceConstants.FJAVA_SRCNAME;
		 
		//output =(IFolder)proj.findMember(MTLPlugin.FOUTPUT_BUILDNAME);
		output =PreferenceConstants.FOUTPUT_BUILDNAME;
		mtlclasspath = proj.getFile(".mtlclasspath");
		
	}
	
	/**
		 *  Gets the project attribute of the MTLProjectModel object
		 *@return    The project value
		 **/
		public static IProject getProject() {
			return proj; 
		}
		public void setProject(IProject project) {
			proj = project;
		}
	
	 /**
	 *  removes all tll files that were  generated  for
	 * this project.
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
//			} else if (res[i].getType() == IResource.FOLDER) {
//				resetMTLGenerateFiles((IFolder) res[i]);
			}
		}
	}

 /**
	 *  processes incremental events from resources and directs the generation of java
	 * files from mtl files or the removal of tll and java files
	 *
	 *@param  delta  
	 *@return        true if successful
	 **/
	public boolean processDelta(IResourceDelta delta) {
		IResource res = delta.getResource();
		int kind = delta.getKind();
		if (kind == IResourceDelta.ADDED || kind == IResourceDelta.CHANGED || kind == IResourceDelta.REPLACED) {
			processResource(res);
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
//		// visit the children
	}
	
	/**
		*  processes events from mtl files and directs the generation of tll files 
		*@param  res  Description of the Parameter
		*@return      Description of the Return Value
		**/
	public boolean findResource(IPath res) {
		    MTLCore.findFolders();
			int taille =MTLModel.srcFolders.length;
			try {
					for (int i=0;i<MTLModel.srcFolders.length;i++){
						if (res.equals(MTLModel.srcFolders[i])) return true;
					}
				
			} catch (Exception e) {
				e.printStackTrace();
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
			//System.out.println("res :"+res.getType()+"  folder :"+IResource.FOLDER+"  folder :"+IResource.FILE);
			if (res.getType() == IResource.FOLDER) {
				if (findResource(res.getFullPath())) {
					//System.out.println("res :"+res.getName());	
					processTll((IFolder) res); 
					
					} 
			}
			if (res.getType() == IResource.FILE) {
				//Change the timestamp of the parent folder
				if (res.getParent().getType()==IResource.FOLDER){
				//System.out.println("passé ici");
				
				IFolder fld = (IFolder)res.getParent();
				//System.out.println("parent :"+fld.getName()+" "+fld.toString());
				String newGen = new Long(fld.getModificationStamp()-1).toString();
				fld.setPersistentProperty(new QualifiedName(MTLPlugin.PLUGIN_ID, MTLModel.TLL_LASTGENTIME), newGen);
				processTll((IFolder) fld);
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
		//  Génération du code TLL
		MTLCodeGenerator jcg = new MTLCodeGenerator(mtlFolder,tllFolder,srcJavaFolder, output);
		//s'assurer que le fait que la méthode n'est pas statique ne pose aucun problème
		MTLPlugin.getWorkspace().run(jcg, null);
	}
	  
	/**
	 *  removes all tll and files generated by the mtl file
	 * given
	 *@param  file           mtl file resource
	 *@exception  Exception
	 **/  
	public void removeTllJava(IFile file) throws Exception {
		IFolder fld = (IFolder)file.getParent();
		removeTllJavaFolder(fld);
	}
	
	/**
	 *  removes all tll and files generated by the mtl file
	 * given
	 *@param  mtlFolder           mtl folder resource
	 *@exception  Exception
	 **/  
	public void removeTllJavaFolder(IFolder mtlFolder) throws Exception {
		removeMTLResources(tllFolder, mtlFolder);
		removeMTLResources(srcJavaFolder, mtlFolder); 
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
//			} else if (res[i].getType() == IResource.FOLDER) {
//				tagMTLResources((IFolder) res[i], srcMtlFolder);
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
	public void removeMTLResources(IFolder srcFolder, IFolder mtl) throws Exception {
		IResource[] res = srcFolder.members();
		for (int i = 0; i < res.length; i++) {
			if (res[i].getType() == IResource.FILE) {
				String propVal = null;
				propVal = res[i].getPersistentProperty(new QualifiedName(MTLPlugin.PLUGIN_ID, MTLModel.MTL_OWNER_PROP));
				if (propVal != null && propVal.equals(mtl.getName())) {
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
