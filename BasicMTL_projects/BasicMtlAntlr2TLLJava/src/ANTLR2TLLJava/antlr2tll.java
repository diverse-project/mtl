/*
 * $Header: /tmp/cvs2svn/cvsroot/BasicMTL_projects/BasicMtlAntlr2TLLJava/src/ANTLR2TLLJava/antlr2tll.java,v 1.4 2003-08-22 18:26:29 ffondeme Exp $
 * Created on 22 juil. 2003
 *
 */
package ANTLR2TLLJava;

import java.io.*;
import org.apache.log4j.Logger;
import org.apache.log4j.LogManager;
import org.apache.log4j.xml.DOMConfigurator;

import org.irisa.triskell.MT.utils.Java.Directories;
import org.irisa.triskell.MT.visitors.Java.AnalysingVisitor.*;
import org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.*;
import antlr2ASTJava.*;

/**
 * @author jpthibau
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class antlr2tll {

	static final String tllPrefix="C:\\PROJET_MTL\\ECLIPSE\\workspace\\BasicMtlAntlr2TLLJava\\ThirdParty\\TllLibraries\\";
	static final String tllSuffix=".tll";
	public static final org.apache.log4j.Logger log = Logger.getLogger("BMTLAntlr2TLLJava");

	public static org.apache.log4j.Logger getLog () {
			return antlr2tll.log;
	}

	public static String TLLProducer(java.util.Vector filenames,String defaultPackagePrefix,String defaultUncheckedTLLPath)
	{	org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.Library parsedLib;
		antlr2ast ASTproducer=new antlr2ast();
		BasicMtlLibrary theCreatedLib=null;
		java.util.Hashtable context=new java.util.Hashtable();
		context.put("LibrraryDefaultPackage",defaultPackagePrefix);
		DefaultAnalysingVisitor visitor = new DefaultAnalysingVisitor("TLLBuilder");
		for (int i=0;i<filenames.size();i++) {
			//to add the new parsed file to the TLL when several files are given
			//the context contains the created  TLL that udpdates at each step
			if (theCreatedLib != null) context.put("TheCreatedLibrary",theCreatedLib);
			parsedLib=ASTproducer.buildLibraryFromText((String)filenames.get(i));
			context.put("visitor", visitor);
			visitor.visit(parsedLib,context);
			theCreatedLib=(BasicMtlLibrary)context.get("TheCreatedLibrary");
		}
		if (defaultUncheckedTLLPath==null) {
			log.info("TLL library wrote to "+tllPrefix+theCreatedLib.getName()+tllSuffix);
			Library.store(theCreatedLib.getName()+tllSuffix,theCreatedLib,tllPrefix);
		} else {
			log.info("TLL library wrote to "+defaultUncheckedTLLPath+theCreatedLib.getName()+tllSuffix);
			Library.store(theCreatedLib.getName()+tllSuffix,theCreatedLib,defaultUncheckedTLLPath);} 
		return theCreatedLib.getName();
	}
	
	public static void main(String[] args)
	{	try {
			String filePath = new java.io.File(Directories.getRootPath(antlr2tll.class.getName()) + "/log4j_configuration.xml").getCanonicalPath();
			LogManager.resetConfiguration();
			DOMConfigurator.configure(filePath); }
		catch(java.io.IOException e) {
			System.err.println("Can't state log4j in BMTLParser"); }
		if (args.length > 0) {
			int argsEnd=args.length;
			String defaultPackagePrefix=null;
			String defaultUncheckedTLLPath=null;
			for (int j=1;j<3;j++) { //possible two optional arguments
			if ((argsEnd > 2)
			 && ((args[argsEnd-2].equalsIgnoreCase("-PackageName"))
			 	|| (args[argsEnd-2].equalsIgnoreCase("-UncheckedTLLPath"))))
				{	if (args[argsEnd-2].equalsIgnoreCase("-PackageName"))
						defaultPackagePrefix=args[argsEnd-1];
					else defaultUncheckedTLLPath=args[argsEnd-1];
					argsEnd=argsEnd-2;
				}
			}
			if (defaultPackagePrefix==null) defaultPackagePrefix="org.irisa.triskell.MT.ThirdParty.";
			if (defaultUncheckedTLLPath==null) defaultUncheckedTLLPath="C:\\PROJET_MTL\\ECLIPSE\\workspace\\BasicMtlAntlr2TLLJava\\ThirdParty\\TllLibraries\\";
			java.util.Vector filenamesArguments=new java.util.Vector();
			for (int i=0;i<argsEnd;i++)
				filenamesArguments.addElement(args[i]);
			TLLProducer(filenamesArguments,defaultPackagePrefix,defaultUncheckedTLLPath);
			}
		else log.error("USAGE : java antlr2tll <sourcefiles>+ [-UnCheckedTLLPath <path>] [-PackageName <TllPackageName>]");
	}

/*	public static void main(String[] args)
	{	try {
			String filePath = new java.io.File(Directories.getRootPath(antlr2tll.class.getName()) + "/log4j_configuration.xml").getCanonicalPath();
			LogManager.resetConfiguration();
			DOMConfigurator.configure(filePath); }
		catch(java.io.IOException e) {
			System.err.println("Can't state log4j in BMTLParser"); }
		if (args.length > 0) {
			int argsEnd=args.length;
			String defaultPackagePrefix=null;
			String defaultUncheckedTLLPath=null;
			for (int j=1;j<3;j++) { //possible two optional arguments
			if ((argsEnd > 2)
			 && ((args[argsEnd-2].equalsIgnoreCase("-PackageName"))
				|| (args[argsEnd-2].equalsIgnoreCase("-UncheckedTLLPath"))))
				{	if (args[argsEnd-2].equalsIgnoreCase("-PackageName"))
						defaultPackagePrefix=args[argsEnd-1];
					else defaultUncheckedTLLPath=args[argsEnd-1];
					argsEnd=argsEnd-2;
				}
			}
			if (defaultPackagePrefix==null) defaultPackagePrefix="org.irisa.triskell.MT.ThirdParty.";
			if (defaultUncheckedTLLPath==null) defaultUncheckedTLLPath="C:\\PROJET_MTL\\ECLIPSE\\workspace\\BasicMtlAntlr2TLLJava\\ThirdParty\\TllLibraries\\";
			org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.Library parsedLib;
			antlr2ast ASTproducer=new antlr2ast();
			BasicMtlLibrary theCreatedLib=null;
			java.util.Hashtable context=new java.util.Hashtable();
			context.put("LibrraryDefaultPackage",defaultPackagePrefix);
			DefaultAnalysingVisitor visitor = new DefaultAnalysingVisitor("TLLBuilder");
			for (int i=0;i<argsEnd;i++) {
				//to add the new parsed file to the TLL when several files are given
				//the context contains the created  TLL that udpdates at each step
				if (theCreatedLib != null) context.put("TheCreatedLibrary",theCreatedLib);
				parsedLib=ASTproducer.buildLibraryFromText(args[i]);
				visitor.visit(parsedLib,context);
				theCreatedLib=(BasicMtlLibrary)context.get("TheCreatedLibrary");
			}
			if (defaultUncheckedTLLPath==null) {
				log.info("TLL library wrote to "+tllPrefix+theCreatedLib.getName()+tllSuffix);
				Library.store(theCreatedLib.getName()+tllSuffix,theCreatedLib,tllPrefix);
			} else {
				log.info("TLL library wrote to "+defaultUncheckedTLLPath+theCreatedLib.getName()+tllSuffix);
				Library.store(theCreatedLib.getName()+tllSuffix,theCreatedLib,defaultUncheckedTLLPath);} 
		}
		else log.error("USAGE : java antlr2tll <sourcefiles>+ [-UnCheckedTLLPath <path>] [-PackageName <TllPackageName>]");
	}*/


}
