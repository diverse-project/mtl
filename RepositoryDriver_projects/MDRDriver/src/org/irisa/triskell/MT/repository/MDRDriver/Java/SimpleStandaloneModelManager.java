/*
 * Created on 1 août 2003
 *
 * $Id: SimpleStandaloneModelManager.java,v 1.3 2003-08-25 13:24:39 ffondeme Exp $
 */
package org.irisa.triskell.MT.repository.MDRDriver.Java;

/*import javax.jmi.model.Classifier;
import javax.jmi.model.ClassifierClass;
import javax.jmi.model.EnumerationType;
import javax.jmi.model.MofClass;
import javax.jmi.reflect.*;*/
import java.util.*;
import java.io.*;
import java.lang.reflect.*;

/*import org.irisa.triskell.MT.DataTypes.Java.*;
import org.irisa.triskell.MT.DataTypes.Java.defaultImpl.*;
import org.irisa.triskell.MT.repository.API.Java.*;
import org.irisa.triskell.MT.repository.MDRDriver.Java.*;*/
import org.irisa.triskell.MT.utils.Java.*;
import org.netbeans.api.mdr.CreationFailedException;
import org.apache.log4j.Logger;
import org.apache.log4j.LogManager;
import org.apache.log4j.xml.DOMConfigurator;
/**
 * @author dvojtise 
 * @version $Revision: 1.3 $
 * 
 * This Model Manager is intended to be the start of a BasicMTL or MTL library
 * it does all the initialization stuffes needed by the MDRdriver
 * it also provide a simple interface to manageMDr models before sending them to the 
 * BasicMTL part as a library (API)
 * 
 * this class is needed in order to simplify the BasicMTL code 
 * 
 * Note : it was mainly created by insparation of test.java
 */
public class SimpleStandaloneModelManager {

	protected boolean isInitialized = false;
	protected static final org.apache.log4j.Logger log = Logger.getLogger("MDRDriver");
	
	
	/**
	 * @return org.apache.log4j.Logger
	 */
	public static org.apache.log4j.Logger getLog () {
			return SimpleStandaloneModelManager.log;
	}

	/**
	 * Initialize the Model Manager with all default values
	 */
	public SimpleStandaloneModelManager init() throws Exception{
		final StringBuffer decompiler = new StringBuffer();
		
		try {			
			// initialization needed by MDR, 
			// use an anonymous class
/*			org.netbeans.mdr.handlers.BaseObjectHandler.setClassLoaderProvider(new org.netbeans.mdr.handlers.ClassLoaderProvider () {
				public ClassLoader getClassLoader() {
					return ClassLoader.getSystemClassLoader();
				}
				

				public Class defineClass(String className, byte[] classFile) {
					try {
						String byteCodeDir = System.getProperty("org.netbeans.mdr.byteCodeDir");
						if (byteCodeDir != null && byteCodeDir.length() != 0) {
							final java.io.File outFile = new java.io.File(byteCodeDir + java.io.File.separatorChar + AWK.replace(className, ".", "/") + ".class");
							if (! outFile.exists()) {
								SimpleStandaloneModelManager.getLog().debug("Archiving bytecode for class " + className);
								outFile.getParentFile().mkdirs();
								outFile.createNewFile();
								BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(outFile));
								out.write(classFile);
								out.flush();
								out.close();
							}
							String javaDir = System.getProperty("org.netbeans.mdr.javaDir");
							if (javaDir != null && javaDir.length() > 0) {
								final java.io.File javaOutFile = new java.io.File(javaDir + java.io.File.separatorChar + AWK.replace(className, ".", "/") + ".java");
								if (! javaOutFile.exists()) {
									javaOutFile.getParentFile().mkdirs();
									decompiler.append("jad -o -sjava -d " + javaOutFile.getParent() + ' ' + outFile.getAbsolutePath() + '\n');
								}								
							}
						}
					} catch (Exception x) {
						x.printStackTrace();
					} finally {
						return null;
					}
				}
			});*/

			// look for the log4j configuration file in the current directory
			String filePath = new java.io.File(Directories.getRootPath(SimpleStandaloneModelManager.class.getName()) + "/log4j_configuration.xml").getCanonicalPath();
			LogManager.resetConfiguration();
			DOMConfigurator.configure(filePath);
			SimpleStandaloneModelManager.getLog().info("MDR driver initialized...");
			isInitialized = true;	
			return this;
		} 
		catch (Exception e) {
			SimpleStandaloneModelManager.getLog().error("MDR driver bugged !!!", e);
			throw e;
		}
	}
	
	/**	  
	 * getModel	Simply create an starts a new RepositoryModel for BasicMTL 
	 * 		this function gives access to the full MDRAPI syntax
	 * @param repository
	 * @param metamodel
	 * @param modelName
	 * @param model
	 * @return MDRAPI
	 * @throws java.lang.Exception
	 */
	public MDRAPI getModel(
		String repository,
		org.irisa.triskell.MT.repository.MDRDriver.Java.Metamodel metamodel,
		String modelName,
		org.irisa.triskell.MT.repository.MDRDriver.Java.Model model)
		throws java.lang.Exception
	{
		if (! isInitialized) throw new Exception("Driver not correctly initialized");
		
		MDRAPI api = new MDRAPI(repository, metamodel, modelName, model);
		api.startup(null);
		return api;
	}
	
	

	/**
	 * getModelFromXMI return a model from a XMI MM and a XMI model files
	 * 	this is a basic version 
	 * @param metamodelXmiFileName
	 * @param modelName
	 * @param modelXmiInputFileName
	 * @param modelXmiOuputFileName
	 * @return MDRAPI
	 * @throws java.lang.Exception
	 */
	public MDRAPI getModelFromXMI(
		String metamodelXmiFileName,
		String modelName,
		String modelXmiInputFileName,
		String modelXmiOuputFileName)
		throws java.lang.Exception
	{
		
		if (! isInitialized) throw new Exception("Driver not correctly initialized");
		
		MDRAPI api = new MDRAPI(null, 
								new XmiMetamodel(metamodelXmiFileName),
								modelName, 
								new XmiModel(modelXmiInputFileName,
											  modelXmiOuputFileName));			
		api.startup(null);
		return api;
	}
	
	
	/**
	 * 	getModelfromGUI open a graphical user interface that allow the user to choose his model
	 * 
	 * @param userMessage indicates for which purpose the model will be used
	 * @return MDRAPI
	 * @throws java.lang.Exception
	 */
	public MDRAPI getModelfromGUI(
			String userMessage)
			throws java.lang.Exception
	{
		if (! isInitialized) throw new Exception("Driver not correctly initialized");
		
		throw new Exception("Method Not Implemented");
		//return null;
	}

	// autotest
	public static void main (String [] args) {
		try {
			SimpleStandaloneModelManager aSimpleStandaloneModelManager = new SimpleStandaloneModelManager();		
			aSimpleStandaloneModelManager.init();
			System.exit(0);
		} catch (Exception x) {
			x.printStackTrace();
			System.exit(-1);
		}
	}
	
}
