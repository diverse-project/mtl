/*
 * $Header: /tmp/cvs2svn/cvsroot/BasicMTL_projects/FacadeAssociation/src/BasicMtlCompiler/Compiler.java,v 1.2 2003-10-31 12:20:11 jpthibau Exp $
 * Created on 25 sept. 2003
 *
 */
package BasicMtlCompiler;

import java.io.*;
import org.apache.log4j.Logger;
import org.apache.log4j.LogManager;
import org.apache.log4j.xml.DOMConfigurator;
import org.irisa.triskell.MT.utils.Java.Directories;
import org.irisa.triskell.MT.visitors.Java.AnalysingVisitor.*;
import org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.*;
import ANTLR2TLLJava.*;
import ANTLRASTWalker.antlrParserInterface;
import TypeChecker.TLLtypechecking;
import CodeGeneration.BMTLCompiler;
import antlr2ASTView.antlr2astViewParser;

/**
 * @author jpthibau
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class Compiler {

	static final String tllSuffix=".tll";
	public static final org.apache.log4j.Logger log = Logger.getLogger("BMTLTLLTypeChecker");

	public static String checkPathEnd(String path)
	{	if (path.charAt(path.length()-1)=='/'
			|| path.charAt(path.length()-1)=='\\')
			return path;
		return path+'/';
}
		
	public static void main(String[] args)
	{
		try {
			String filePath = new java.io.File(Directories.getRootPath(Compiler.class.getName()) + "/log4j_configuration.xml").getCanonicalPath();
			LogManager.resetConfiguration();
			DOMConfigurator.configure(filePath); }
		catch(java.io.IOException e) {
							System.err.println("Can't state log4j in BMTLParser"); }
		if (args.length > 0) {
			int argsEnd=args.length;
			String defaultPackagePrefix=null;
			String defaultTLLPath=null;
			String TLLLoadingPaths=null;
			String defaultBinPath=null;
			String sourcesDir=null;
			for (int j=1;j<5;j++) { //possible four optional arguments
			if ((argsEnd > 2)
			 && (args[argsEnd-2].equalsIgnoreCase("-PackageName")
				|| args[argsEnd-2].equalsIgnoreCase("-TLLPath")
				|| args[argsEnd-2].equalsIgnoreCase("-TLLLoadingPaths")
				|| args[argsEnd-2].equalsIgnoreCase("-BinPath")))
				{	if (args[argsEnd-2].equalsIgnoreCase("-PackageName"))
						defaultPackagePrefix=args[argsEnd-1];
					else if (args[argsEnd-2].equalsIgnoreCase("-TLLPath"))
							defaultTLLPath=checkPathEnd(args[argsEnd-1]);
						else if (args[argsEnd-2].equalsIgnoreCase("-TLLLoadingPaths"))
								TLLLoadingPaths=checkPathEnd(args[argsEnd-1]);
							else defaultBinPath=args[argsEnd-1]; 
					argsEnd=argsEnd-2;
				}
			}
			if (defaultPackagePrefix==null) defaultPackagePrefix="org.irisa.triskell.MT.ThirdParty.";
			if (defaultTLLPath==null)
				log.error("No -TLLPath option,don't known where tostore produced TLL");
			java.util.Vector filenamesArguments=new java.util.Vector();
			sourcesDir=checkPathEnd(args[0]);
			String filesList[]=new java.io.File(sourcesDir).list();
			for (int i=0;i<filesList.length;i++)
			{
				// checks if file exists
				if (filesList[i].endsWith(".mtl"))
				 {
					log.info("Including file "+filesList[i]+"...");
					File aFile = new File(args[0]+filesList[i]);
					if (aFile.canRead())
						filenamesArguments.addElement(args[0]+filesList[i]);
					else				
						log.warn("File not readable : "+args[i]+" => file ignored !!!");
				 }
				else log.info("EXCLUDING file "+filesList[i]+"!!!");
			}
			if (filenamesArguments.size() == 0)
			{
				log.error("No file to process");
			}
			else {
				java.io.File directoryFile=new java.io.File(defaultBinPath);
				if (! directoryFile.isDirectory())
					try { directoryFile.mkdir();
					} catch (SecurityException e) {System.err.println("Can't make directory "+defaultBinPath); }
				antlrParserInterface parser=new antlr2astViewParser().newParser();
				BasicMtlLibrary theLib=(BasicMtlLibrary)TLLtypechecking.checkedTLLProducer(filenamesArguments,defaultPackagePrefix,defaultTLLPath,TLLLoadingPaths,parser);
				if (theLib!=null)
					BMTLCompiler.compile(theLib,defaultTLLPath,defaultBinPath);			}
}
		else showUsage();
	}
	static void showUsage()
	{
		log.error("USAGE TLLtypechecking <sourcefile>+ [-TLLPath <path>] [-PackageName <TllPackageName>]");
	}
	}


