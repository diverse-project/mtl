/*
 * $Header: /tmp/cvs2svn/cvsroot/BasicMTL_projects/TLLTypeChecker/src/TypeChecker/TLLtypechecking.java,v 1.5 2003-08-19 13:58:28 ffondeme Exp $
 * Created on 30 juil. 2003
 *
 */
package TypeChecker;

import java.io.*;
import org.apache.log4j.Logger;
import org.apache.log4j.LogManager;
import org.apache.log4j.xml.DOMConfigurator;
import org.irisa.triskell.MT.utils.Java.Directories;
import org.irisa.triskell.MT.visitors.Java.AnalysingVisitor.*;
import org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.*;
import ANTLR2TLLJava.*;
/**
 * @author jpthibau
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class TLLtypechecking {

	static final String tllUncheckedPrefix="C:\\PROJET_MTL\\ECLIPSE\\workspace\\BasicMtlAntlr2TLLJava\\ThirdParty\\TllLibraries\\";
	static final String tllPrefix="C:\\PROJET_MTL\\ECLIPSE\\workspace\\TLLTypeChecker\\ThirdParty\\TllLibraries\\";
	static final String tllSuffix=".tll";
	public static String defaultTLLPath=null;
	public static java.util.Hashtable loadedLibraries=new java.util.Hashtable();
	public static final org.apache.log4j.Logger log = Logger.getLogger("BMTLTLLTypeChecker");

	public static org.apache.log4j.Logger getLog () {
			return TLLtypechecking.log;
	}

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

	public static void main(String[] args)
	{	
		boolean hasErrorOccured = false;
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
			for (int j=1;j<3;j++) { //possible two optional arguments
			if ((argsEnd > 2)
			 && ((args[argsEnd-2].equalsIgnoreCase("-PackageName"))
				|| (args[argsEnd-2].equalsIgnoreCase("-TLLPath"))))
				{	if (args[argsEnd-2].equalsIgnoreCase("-PackageName"))
						defaultPackagePrefix=args[argsEnd-1];
					else {	defaultTLLPath=args[argsEnd-1]+"TLL/";
							defaultUncheckedTLLPath=args[argsEnd-1]+"UncheckedTLL/"; } 
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
			String TLLName=antlr2tll.TLLProducer(filenamesArguments,defaultPackagePrefix,defaultUncheckedTLLPath);
			log.info("Opening the produced TLL "+tllPrefix+args[0]+tllSuffix);
			BasicMtlLibrary theLib=(BasicMtlLibrary)Library.load(defaultUncheckedTLLPath+TLLName+tllSuffix);
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
	}
}
