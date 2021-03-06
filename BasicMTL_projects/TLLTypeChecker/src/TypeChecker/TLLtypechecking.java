/*
 * $Header: /tmp/cvs2svn/cvsroot/BasicMTL_projects/TLLTypeChecker/src/TypeChecker/TLLtypechecking.java,v 1.16 2004-10-18 16:01:30 jpthibau Exp $
 * Created on 30 juil. 2003
 *
 */
package TypeChecker;

import java.io.*;

import org.apache.log4j.Logger;
import org.irisa.triskell.MT.utils.MessagesHandler.MSGHandler;
import org.irisa.triskell.MT.visitors.Java.AnalysingVisitor.*;
import org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.*;
import ANTLR2TLLJava.*;
import ANTLRASTWalker.antlrParserInterface;
/**
 * @author jpthibau
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class TLLtypechecking {

	static final String tllUncheckedPrefix="..\\BasicMtlAntlr2TLLJava\\ThirdParty\\TllLibraries\\";
	static final String tllPrefix=".\\ThirdParty\\TllLibraries\\";
	static final String tllSuffix=".tll";
	public static java.util.Vector defaultTLLPaths=null;
	public static String defaultTLLPath=null;
	public static java.util.Hashtable loadedLibraries=new java.util.Hashtable();
	static final Logger log=MSGHandler.init();

/*	public static void main(String[] args)
	{	try {
			String filePath = new java.io.File(Directories.getRootPath(TLLtypechecking.class.getName()) + "/log4j_configuration.xml").getCanonicalPath();
			LogManager.resetConfiguration();
			DOMConfigurator.configure(filePath); }
		catch(java.io.IOException e) {
							System.err.println("Can't state log4j in BMTLParser"); }
		if (args.length == 1) {
			log.info("Opening the TLL "+tllPrefix+args[0]+tllSuffix);
			BasicMtlLibrary theLib=(BasicMtlLibrary)Library.load(tllPrefix+args[0]+tllSuffix);
			//Prepare the KnownTypes Hashtable
			KnownClasses knownClasses=new KnownClasses();
			for (int i=0;i<theLib.cardClasses();i++)
				knownClasses.put(theLib.getClasses(i).getName(),theLib.getClasses(i));				
			theLib.setKnownTypes(knownClasses);
			if (allReferedTypes.checkAllReferedTypes(theLib) ==0) {
				if (inheritedSignatures.synthetizeInheritedSignatures(theLib)==0) {
					java.util.Hashtable context=new java.util.Hashtable();
					DefaultAnalysingVisitor visitor = new DefaultAnalysingVisitor("OperationCallChecker");
					visitor.visit(theLib,context);
					log.info("Writing the checked TLL to"+tllCheckedPrefix+args[0]+tllSuffix);
					Library.store(theLib.getName()+tllSuffix,theLib,tllCheckedPrefix);
				}
			}
			else log.info("There are warnings or errors, TLL not synthetized !");
		}
		else log.error("USAGE TLLtypechecking <TLLname>");
	} */

/*	public static void main(String[] args)
	{	
		boolean hasErrorOccured = false;
		boolean viaView=false;
		try {
			String filePath = new java.io.File(Directories.getRootPath(TLLtypechecking.class.getName()) + "/log4j_configuration.xml").getCanonicalPath();
			LogManager.resetConfiguration();
			DOMConfigurator.configure(filePath); }
		catch(java.io.IOException e) {
							System.err.println("Can't state log4j in BMTLParser"); }
		if (args.length > 0) {
			int argsEnd=args.length;
			String defaultPackagePrefix=null;
			String defaultUncheckedTLLPath=null;
			for (int j=1;j<4;j++) { //possible three optional arguments
			if ((argsEnd > 2)
			 && (args[argsEnd-2].equalsIgnoreCase("-PackageName")
				|| args[argsEnd-2].equalsIgnoreCase("-TLLPath")
				|| args[argsEnd-2].equalsIgnoreCase("-ViaView")))
				{	if (args[argsEnd-2].equalsIgnoreCase("-PackageName"))
						defaultPackagePrefix=args[argsEnd-1];
					else if (args[argsEnd-2].equalsIgnoreCase("-TLLPath"))
							{	defaultTLLPath=args[argsEnd-1]+"TLL/";
								defaultUncheckedTLLPath=args[argsEnd-1]+"UncheckedTLL/"; }
							else viaView=true; 
					argsEnd=argsEnd-2;
				}
			}
			if (defaultPackagePrefix==null) defaultPackagePrefix="org.irisa.triskell.MT.ThirdParty.";
			if (defaultTLLPath==null)
				{ defaultTLLPath=tllPrefix;
				  defaultUncheckedTLLPath=tllUncheckedPrefix; } 
			java.util.Vector filenamesArguments=new java.util.Vector();
			for (int i=0;i<argsEnd;i++)
			{
				// checks if file exists
				File aFile = new File(args[i]);
				if (aFile.canRead())
				{ 
				filenamesArguments.addElement(args[i]);
				}
				else{				
					log.warn("File not readable : "+args[i]+" => file ignored !!!");
				}
			}
			if (filenamesArguments.size() == 0)
			{
				log.error("No file to process");
				hasErrorOccured = true;
			}
			else
			{
			String TLLName=antlr2tll.TLLProducer(filenamesArguments,defaultPackagePrefix,defaultUncheckedTLLPath,null);
			log.info("Opening the produced TLL "+tllPrefix+args[0]+tllSuffix);
			BasicMtlLibrary theLib=(BasicMtlLibrary)Library.load(defaultUncheckedTLLPath+TLLName+tllSuffix);
			//Prepare the KnownTypes Hashtable
			KnownClasses knownClasses=new KnownClasses();
			theLib.setKnownTypes(knownClasses);
			for (int i=0;i<theLib.cardClasses();i++) {
				UserDefinedClass c = theLib.getClasses(i);
				knownClasses.put(c.getName(),c);
				QualifiedName qn = new QualifiedName();
//				qn.setExternLibCompleteName(theLib.getPackageName());
				qn.add(c.getName());
				allReferedTypes.checkLocalClass(qn, c.getName(), theLib);
				c.setQualifiedName(qn);
			}
			if (allReferedTypes.checkAllReferedTypes(theLib) ==0) {
				if (inheritedSignatures.synthetizeInheritedSignatures(theLib)==0) {
					java.util.Hashtable context=new java.util.Hashtable();
					DefaultAnalysingVisitor visitor = new DefaultAnalysingVisitor("OperationCallChecker");
					context.put("Error", Boolean.FALSE);
					visitor.visit(theLib,context);
					hasErrorOccured = hasErrorOccured || ((Boolean)context.get("Error")).booleanValue();
					log.info("Writing the checked TLL to"+defaultTLLPath+TLLName+tllSuffix);
					Library.store(TLLName+tllSuffix,theLib,defaultTLLPath);
				}
			}
				else hasErrorOccured = true;
		}
			if (hasErrorOccured) 
			{
				log.info("There are warnings or errors, TLL not synthetized !"); 
	}
}
		else showUsage();
	}
	static void showUsage()
	{
		log.error("USAGE TLLtypechecking <sourcefile>+ [-TLLPath <path>] [-PackageName <TllPackageName>]");
	}*/
	private static java.util.Vector vectorize(String paths)
	{	if (paths==null) return null;
		java.util.Vector pathsVector=new java.util.Vector();
		int pathStart=0;
		int indexOfSemiColon=0;
		do {
			indexOfSemiColon=paths.indexOf(';',pathStart);
			if (indexOfSemiColon>0) {
					pathsVector.addElement(paths.substring(pathStart,indexOfSemiColon));
					pathStart=indexOfSemiColon+1;
			}
		}
		while (indexOfSemiColon > 0);
		pathsVector.addElement(paths.substring(pathStart,paths.length()));
		return pathsVector;
	}
	

	public static BasicMtlLibrary checkedTLLProducer(java.util.Vector filenamesArguments,String defaultPackagePrefix,String defaultTLLPath,String TLLLoadingPaths,antlrParserInterface ASTproducer)
	{
		TLLtypechecking.defaultTLLPath=defaultTLLPath;
		TLLtypechecking.defaultTLLPaths=vectorize(TLLLoadingPaths);
		boolean errorOccured = false;
		BasicMtlLibrary theLib=(BasicMtlLibrary)antlr2tll.TLLProducer(filenamesArguments,defaultPackagePrefix,ASTproducer);
		//Prepare the KnownTypes Hashtable
		KnownClasses knownClasses=new KnownClasses();
		theLib.setKnownTypes(knownClasses);
		for (int i=0;i<theLib.cardClasses();i++) {
			UserDefinedClass c = theLib.getClasses(i);
			knownClasses.put(c.getName(),c);
			QualifiedName qn = new QualifiedName();
//			qn.setExternLibCompleteName(theLib.getPackageName());
			qn.add(c.getName());
			allReferedTypes.checkLocalClass(qn, c.getName(), theLib);
			c.setQualifiedName(qn);
			/*************************************
			 * TODO prepend Lib QualiedName to class QualifiedName
			 */
		}
		if (allReferedTypes.checkAllReferedTypes(theLib) ==0) {
			if (inheritedSignatures.synthetizeInheritedSignatures(theLib)==0) {
				java.util.Hashtable context=new java.util.Hashtable();
				DefaultAnalysingVisitor visitor = new DefaultAnalysingVisitor("OperationCallChecker");
				context.put("Error", Boolean.FALSE);
				visitor.visit(theLib,context);
				errorOccured = errorOccured || ((Boolean)context.get("Error")).booleanValue();
				log.info("Writing checked TLL "+defaultTLLPath+theLib.getName()+tllSuffix);
				Library.store(theLib.getName()+tllSuffix,theLib,defaultTLLPath);
			}
		}
			else errorOccured = true;
	if (errorOccured) {
			log.info("There are warnings or errors, TLL not synthetized !");
			return null; 
	}
	return theLib;
	}
		
	public static void main(String[] args)
	{	
		boolean viaView=false;
		if (args.length > 0) {
			int argsEnd=args.length;
			String defaultPackagePrefix=null;
			String defaultUncheckedTLLPath=null;
			for (int j=1;j<4;j++) { //possible three optional arguments
			if ((argsEnd > 2)
			 && (args[argsEnd-2].equalsIgnoreCase("-PackageName")
				|| args[argsEnd-2].equalsIgnoreCase("-TLLPath")
				|| args[argsEnd-2].equalsIgnoreCase("-ViaView")))
				{	if (args[argsEnd-2].equalsIgnoreCase("-PackageName"))
						defaultPackagePrefix=args[argsEnd-1];
					else if (args[argsEnd-2].equalsIgnoreCase("-TLLPath"))
							{	defaultTLLPath=args[argsEnd-1]+"TLL/";
								defaultUncheckedTLLPath=args[argsEnd-1]+"UncheckedTLL/"; }
							else viaView=true; 
					argsEnd=argsEnd-2;
				}
			}
			if (defaultPackagePrefix==null) defaultPackagePrefix="org.irisa.triskell.MT.ThirdParty.";
			if (defaultTLLPath==null)
				{ defaultTLLPath=tllPrefix;
				  defaultUncheckedTLLPath=tllUncheckedPrefix; } 
			java.util.Vector filenamesArguments=new java.util.Vector();
			for (int i=0;i<argsEnd;i++)
			{
				// checks if file exists
				File aFile = new File(args[i]);
				if (aFile.canRead())
				{ 
				filenamesArguments.addElement(args[i]);
				}
				else{				
					log.warn("File not readable : "+args[i]+" => file ignored !!!");
				}
			}
			if (filenamesArguments.size() == 0)
			{
				log.error("No file to process");
			}
			else
			  checkedTLLProducer(filenamesArguments,defaultPackagePrefix,defaultTLLPath,null,null);
}
		else showUsage();
	}
	public static void reinitLoadedLibraries(){
		loadedLibraries=new java.util.Hashtable();
	}
	static void showUsage()
	{
		log.error("USAGE TLLtypechecking <sourcefile>+ [-TLLPath <path>] [-PackageName <TllPackageName>]");
	}
}
