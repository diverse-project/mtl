/* $Id: Associations2AttributesActions.java,v 1.3 2004-09-15 12:50:50 dvojtise Exp $
 * Created on 16 août 2004
 * Authors: dvojtise
 * Copyright 2003 - INRIA - LGPL license
 */
package org.inria.simpleUML.transformations.UI.popup.actions;

import org.eclipse.core.resources.IFile;
import org.inria.simpleUML.transformations.UI.UIPlugin;
import SimpleUmlTransformationsWithModelLoader.*;

import org.eclipse.core.runtime.IPluginDescriptor;
import org.eclipse.core.runtime.ILibrary;
import org.eclipse.core.runtime.IPath;
import java.net.URL;
import java.util.LinkedList;
import org.inria.BasicMTL.runtime.JarClassLoader;
import org.inria.BasicMTL.runtime.RuntimePlugin;
import org.irisa.triskell.MT.BasicMTL.DataTypes.impl.BMTLSequence;
import org.irisa.triskell.MT.BasicMTL.DataTypes.impl.BMTLString;

import java.net.MalformedURLException;
import java.io.*;

/**
 * @author dvojtise
 *
 */
public class Associations2AttributesActions {
	UIPlugin thePlugin;
	public Associations2AttributesActions(UIPlugin aPlugin)
	{
		thePlugin = aPlugin;
		// initialize the  Directories.RootPath property so the log4j logger will find its configuration file
		// TODO look for the rootpath from BasicMTL runtime plugin installation dir
		System.setProperty("Directories.RootPath","C:\\eclipse3.0\\eclipse\\plugins\\org.inria.BasicMTL.runtime_0.0.3");
	}
	/**
	 * @param anIFile
	 */
	public void transformSimpleUML_EMF(String sourceFileName, String destFileName)
	{
		try {
			
				BMTLString [] params = new BMTLString [3];
				params[0] = new BMTLString(sourceFileName);
				params[1] = new BMTLString(destFileName);
				params[2] = new BMTLString("EMF");
				Thread cur = Thread.currentThread();
				ClassLoader save = cur.getContextClassLoader();			
				cur.setContextClassLoader(getClass().getClassLoader());
				//cur.setContextClassLoader(getBasicMTLRuntimeClassLoader());
				try {
				    BMTLLib_SimpleUmlTransformationsWithModelLoader.TheInstance.BMTL_main(new BMTLSequence(params));
				   
				}
				catch(Exception e)
				{
					System.err.println("Exception: "+ e.getMessage());
					e.printStackTrace();
				}
				finally {
					  cur.setContextClassLoader(save);
				}
				
		
			}
			catch (Throwable e) {System.out.println("Application terminated with  exception :"+e+"\n"+e.getMessage());
								 e.printStackTrace();}

			
	}

	public void transformSimpleUML_XML(String sourceFileName, String destFileName, String driverName)
	{
		try {
			//	showRuntimeLibraries();
			//	org.irisa.triskell.MT.repository.MDRDriver.Java.MDRAPI anMDRAPI;
				
			/*	System.out.println(java.util.Arrays.asList(
						((java.net.URLClassLoader)this.getClass().getClassLoader()).getURLs() ));*/
				//this.getClass().getClassLoader().loadClass("org.openide.util.Lookup");
				//this.getClass().getClassLoader().loadClass("org.irisa.triskell.MT.repository.MDRDriver.Java.RepositoryLookup");
			/* this code fails with an unkwonClass or noclassdef exception 	
			 * 	org.irisa.triskell.MT.repository.MDRDriver.Java.SimpleStandaloneModelManager ssmm = new org.irisa.triskell.MT.repository.MDRDriver.Java.SimpleStandaloneModelManager();
				
				ssmm.init();
			
			*/
				/* classLoader code suggested here: http://eclipse-wiki.info/PDEFaq */
			/*	Thread cur = Thread.currentThread();
				ClassLoader save = cur.getContextClassLoader();			
				cur.setContextClassLoader(UIPlugin.getDefault().getBasicMTLRuntimeClassLoader());
				try {
				  // do whatever needs the contextClassLoader
					org.irisa.triskell.MT.repository.MDRDriver.Java.SimpleStandaloneModelManager ssmm = new org.irisa.triskell.MT.repository.MDRDriver.Java.SimpleStandaloneModelManager();
					
					ssmm.init();
				} finally {
				  cur.setContextClassLoader(save);
				}
			*/	/* should I try this? http://eclipsewiki.swiki.net/123 */
			
			
			//   This is what I want to run  but it fails due to class path problems with plugins
			
				BMTLString [] params = new BMTLString [3];
				params[0] = new BMTLString(sourceFileName);
				params[1] = new BMTLString(destFileName);
				params[2] = new BMTLString(driverName);
				Thread cur = Thread.currentThread();
				ClassLoader save = cur.getContextClassLoader();			
				cur.setContextClassLoader(getClass().getClassLoader());
				//cur.setContextClassLoader(getBasicMTLRuntimeClassLoader());
				try {
				    BMTLLib_SimpleUmlTransformationsWithModelLoader.TheInstance.BMTL_main(new BMTLSequence(params));
				   
				}
				catch(Exception e)
				{
					System.out.println(e.getMessage());
				}
				finally {
					  cur.setContextClassLoader(save);
				}
				
		
			}
			catch (Throwable e) {System.out.println("Application terminated with  exception :"+e+"\n"+e.getMessage());
								 e.printStackTrace();}

			
	}
	public void transformWithMDRnewJVM(IFile anIFile) throws java.io.IOException
	{
		
		// doesn't work !!!! IOException for any kink of command
		//System.setOut(new PrintStream(new FileOutputStream(new File("c:/test.log"))));
		//System.setErr(new PrintStream(new FileOutputStream(new File("c:/test_err.log"))));
//		java.lang.Runtime.getRuntime().exec("C:\\j2sdk1.4.1_01\\bin\\java.exe "
//				+ "-cp C:\\eclipse3.0\\eclipse\\workspace\\SimpleUML.transformations\\SimpleUML_transformations.jar"
//				+ " BMTLLib_SimpleUmlTransformationsWithModelLoader "
//				+ anIFile.getLocation().toString() + anIFile.getName() );
		
		//java.lang.Runtime.getRuntime().exec("dir" );
		java.lang.Runtime.getRuntime().exec("C:\\j2sdk1.4.1_01\\bin\\javac -help");
		System.out.println("exec launched\n");
	}
	private ClassLoader fBasicMTLRuntimeLoader;
	/**
	 * create or return a class loader that give precedence to the jar distributed with BasicMTL.runtime plugin
	 * @return
	 */
	private ClassLoader getBasicMTLRuntimeClassLoader() {
		try {
			if (fBasicMTLRuntimeLoader == null) {
				// Use JarClassLoader to force Xerces 2 classes to be loaded locally
				IPluginDescriptor pd = org.inria.BasicMTL.runtime.RuntimePlugin.getDefault().getDescriptor();
	 			URL url = pd.getInstallURL();
	 			String urlString = url.toString();
	 			ILibrary[] libraries = pd.getRuntimeLibraries();
				LinkedList list = new LinkedList();
	 			for (int i = 0; i < libraries.length; i++) {
					ILibrary iLibrary = libraries[i];
					IPath libPath = iLibrary.getPath();
					String libPathStr = libPath.toString();
					String libUrlStr = urlString + libPathStr;
					URL libUrl = new URL(libUrlStr);
					list.add(libUrl);
					//System.out.println(" new classloader with: "+libUrlStr);
				}
	 			URL[] libUrls = (URL[]) list.toArray(new URL[list.size()]);
	 			fBasicMTLRuntimeLoader = new JarClassLoader(libUrls, getClass().getClassLoader());
			}
			return fBasicMTLRuntimeLoader;
		} catch (MalformedURLException mue) {
			//??? log error
			mue.printStackTrace();
		}
		return null;
	}

}
