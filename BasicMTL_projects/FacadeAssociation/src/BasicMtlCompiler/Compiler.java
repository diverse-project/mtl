/*
 * $Header: /tmp/cvs2svn/cvsroot/BasicMTL_projects/FacadeAssociation/src/BasicMtlCompiler/Compiler.java,v 1.4 2003-11-24 15:02:11 dvojtise Exp $
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
			if(filesList == null)
			{
				log.error("No file to process in "+sourcesDir);
				showUsage();
				System.exit(-1);				
			}
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
				System.exit(-1);
			}
			else 
			{
				java.io.File directoryFile=new java.io.File(defaultBinPath);
				if (! directoryFile.isDirectory())
					try { directoryFile.mkdir();} 
					catch (SecurityException e) {System.err.println("Can't make directory "+defaultBinPath); }
				antlrParserInterface parser=new antlr2astViewParser().newParser();
				BasicMtlLibrary theLib=(BasicMtlLibrary)TLLtypechecking.checkedTLLProducer(filenamesArguments,defaultPackagePrefix,defaultTLLPath,TLLLoadingPaths,parser);
				if (theLib!=null)
					BMTLCompiler.compile(theLib,defaultTLLPath,defaultBinPath);	
				else System.exit(-1);	
			}
		}
		else showUsage();
	}
	static void showUsage()
	{
		log.error("USAGE : java -jar BasicMTLc.jar <sourcesDirectory> [-TLLPath <path>] [-TLLLoadingPaths <pathes>] [-PackageName <name>] [-BinPath <path>]");
		log.error("where : pathes are list of path sepatate with ; ");
		log.error("where : sourcesDirectory is the directory which contain your mtl files. (it will take all your mtl files) ");
		log.error("where : TLLPath is the palce where the definition of your libraries are stored. kind of precompiled step for further compile");
		log.error("where : TLLLoadingPaths is the palces which contains allready precompiled libraries, usaually the minimum is the path runtime TLL which contain the standard types and the driver definition");
		log.error("where : PackageName indicates the name of the java package that will contain your library(/ies)");
		log.error("where : BinPath indicates where to generate the java files (it doesn't take into account the package name, so you have to be sure they matches in order to successfully compile your generated java files");
	}
}


