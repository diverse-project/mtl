/*
 * $Header: /tmp/cvs2svn/cvsroot/BasicMTL_projects/FacadeAssociation/src/BasicMtlCompiler/Compiler.java,v 1.18 2004-10-19 08:29:56 dvojtise Exp $
 * Created on 25 sept. 2003
 *
 */
package BasicMtlCompiler;

import java.io.*;
//import java.util.Vector;

import org.apache.log4j.Logger;
import org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.*;
import org.irisa.triskell.MT.utils.Java.Directories;
import org.irisa.triskell.MT.utils.MessagesHandler.MSGHandler;
import org.irisa.triskell.MT.utils.MessagesHandler.CompilerException;

import ANTLRASTWalker.antlrParserInterface;
import TypeChecker.TLLtypechecking;
import CodeGeneration.BMTLCompiler;
import antlr2ASTView.antlr2astViewParser;

/**
 * @author jpthibau
 *
 * Entry point on the compiler. It defines the main and get the different parameters from the command line. 
 * This version of the basicMtlFacade is for use with BMTLBasicMtlASTView AST
 */
public class Compiler {

	static final String tllSuffix=".tll";
	static final Logger log=MSGHandler.init();
	public boolean hasErrorOccured = false;

	public static String checkPathEnd(String path)
	{	if (path.charAt(path.length()-1)=='/'
			|| path.charAt(path.length()-1)=='\\')
			return path;
		return path+'/';
	}
		
	/**
	 * @param args command line arguments
	 */
	public static void main(String[] args) 
	{
		// parses the arguments in order to find the necessary data
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
				log.error("No -TLLPath option,don't known where to store produced TLL");
			sourcesDir=checkPathEnd(args[0]);
			
			// ok let's do the stuff...
			Compiler thisCompilerFacade = new Compiler();
			try {
				thisCompilerFacade.compileFromDirectory(sourcesDir,defaultPackagePrefix, defaultTLLPath, TLLLoadingPaths, defaultBinPath);
			}
			catch (CompilerException e)
			{
				System.err.println("Compilation error found");
				System.exit(-1);		
			}
			if (thisCompilerFacade.hasErrorOccured)
			{
				System.err.println("error occured");
				System.exit(-1);
			}
		}
		else showUsage();
	}
	
	/**
	 * Compiles a library from a list of mtl files.
	 * Generates a tll file and java files
	 * @param filenamesArguments	vector containing all the files that must be parsed to build this library
	 * @param defaultPackagePrefix indicates the name of the java package that will contain your library
	 * @param defaultTLLPath is the place where the definition of your library will be stored. kind of precompiled step for further compile
	 * @param TLLLoadingPaths is a list of places (separated by ;) which contain allready precompiled libraries, usually the minimum is the path runtime TLL which contain the standard types and the driver definition
	 * @param defaultBinPath indicates where to generate the java files (it doesn't take into account the package name, so you have to be sure they matches in order to successfully compile your generated java files
	 */
	public void compileFromFiles (
		java.util.Vector filenamesArguments,
		String defaultPackagePrefix,
		String defaultTLLPath,
		String TLLLoadingPaths,
		String defaultBinPath)
		throws CompilerException
	{
		// create destination directory
		java.io.File directoryFile=new java.io.File(defaultBinPath);
		if (! directoryFile.isDirectory())
			try { directoryFile.mkdir();} 
			catch (SecurityException e) {System.err.println("Can't make directory "+defaultBinPath); }
		
		// parse and typecheck  (ie. generate the tll file)	
		antlrParserInterface parser=new antlr2astViewParser().newParser();
		TLLtypechecking.reinitLoadedLibraries();	// it is better to reinit the LoadedLibraries static variable to be sure that this is the latest version of these tll 
		BasicMtlLibrary theLib=(BasicMtlLibrary)TLLtypechecking.checkedTLLProducer(filenamesArguments,defaultPackagePrefix,defaultTLLPath,TLLLoadingPaths,parser);
		if (theLib!=null)
			// compile, ie. generate the java files
			BMTLCompiler.compile(theLib,defaultTLLPath,defaultBinPath);	
		else {
			log.fatal("no generated library");
			hasErrorOccured = true;
		}
	}
	
	/**
	 * Compiles a library from a directory containing mtl files.
	 * Generates a tll file and java files
	 * @param sourcesDir : vector containing all the files that must be parsed to build this library
	 * @param defaultPackagePrefix : indicates the name of the java package that will contain your library
	 * @param defaultTLLPath : is the place where the definition of your library will be stored. kind of precompiled step for further compile
	 * @param TLLLoadingPaths : is a list of places (separated by ;) which contain allready precompiled libraries, usually the minimum is the path runtime TLL which contain the standard types and the driver definition
	 * @param defaultBinPath : indicates where to generate the java files (it doesn't take into account the package name, so you have to be sure they matches in order to successfully compile your generated java files
	 */
	public void compileFromDirectory(
			String sourcesDir,
			String defaultPackagePrefix,
			String defaultTLLPath,
			String TLLLoadingPaths,
			String defaultBinPath)
			throws CompilerException
	{
		// look in the directory only for mtl files. 
		// now, this version does search recursively
		java.util.Vector filenamesArguments=new java.util.Vector();
		String filesList[] = Directories.getFilesFromDirectory (sourcesDir);
		if(filesList == null)
		{
			showUsage();
			log.error("No file to process in "+sourcesDir);
		}
		for (int i=0;i<filesList.length;i++)
		{ 
			// checks if file exists
			// ignore files in CVS directories
			boolean isWithCVSDir = false; 
			String[] result = filesList[i].split("\\\\|/");
			for (int x=0; x<result.length; x++)
		    {
		    	if(result[x].equalsIgnoreCase("CVS"))
		    	{
		    		isWithCVSDir = true;
		    	}
		    }
			if (filesList[i].endsWith(".mtl") && !isWithCVSDir)
			 {
				log.debug("Including file "+filesList[i]+"...");
				File aFile = new File(sourcesDir+filesList[i]);
				if (aFile.canRead())
					filenamesArguments.addElement(sourcesDir+filesList[i]);
				else				
					log.warn("File not readable : "+sourcesDir+filesList[i]+" => file ignored !!!");
			 }
			else log.debug("EXCLUDING file "+filesList[i]+"!!!");
		}
		if (filenamesArguments.size() == 0)
		{
			log.fatal("No file to process");
			hasErrorOccured = true;
		}
		else 
		{
			compileFromFiles(filenamesArguments, defaultPackagePrefix, defaultTLLPath, TLLLoadingPaths, defaultBinPath);	
		}
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


