/*
 * $Header: /tmp/cvs2svn/cvsroot/BasicMTL_projects/BasicMtlJavaCodeGenerator/src/CodeGeneration/BMTLCompiler.java,v 1.3 2003-10-14 07:42:56 jpthibau Exp $
 * Created on 22 juil. 2003
 *
 */
package CodeGeneration;

import org.apache.log4j.Logger;
import org.apache.log4j.LogManager;
import org.apache.log4j.xml.DOMConfigurator;

import org.irisa.triskell.MT.utils.Java.Directories;
import org.irisa.triskell.MT.visitors.Java.AnalysingVisitor.*;
import org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.*;

/**
 * @author jpthibau
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class BMTLCompiler {

	static final String binPathName="C:\\PROJET_MTL\\ECLIPSE\\workspace\\BMTLTester\\ThirdParty\\BMTL_bin";
	static final String tllPrefix="C:\\PROJET_MTL\\ECLIPSE\\workspace\\TLLTypeChecker\\ThirdParty\\TllLibraries\\";
	static final String tllSuffix=".tll";

	public static final org.apache.log4j.Logger log = Logger.getLogger("BMTLParser");

	public static org.apache.log4j.Logger getLog () {
			return BMTLCompiler.log;
	}

	public static void compile(BasicMtlLibrary theLib,String defaultTLLPath,String defaultBinPath)
	{	
		java.util.Hashtable context=new java.util.Hashtable();
		context.put("BaseFileName",defaultBinPath);
		DefaultAnalysingVisitor visitor = new DefaultAnalysingVisitor("FirstPassGeneration");
		DefaultAnalysingVisitor visitor2 = new DefaultAnalysingVisitor("SecondPassGeneration");
		visitor.visit(theLib,context);
		visitor2.visit(theLib,context);
		log.info("Code generation is over.");
	}
	
	public static void main(String[] args)
	{	try {
			String filePath = new java.io.File(Directories.getRootPath(BMTLCompiler.class.getName()) + "/log4j_configuration.xml").getCanonicalPath();
			LogManager.resetConfiguration();
			DOMConfigurator.configure(filePath); }
		catch(java.io.IOException e) {
			System.err.println("Can't state log4j in BMTLParser"); }
		if (args.length > 0) {
			int argsEnd=args.length;
			String defaultBinPath=null;
			String defaultTLLPath=null;
			for (int j=1;j<3;j++) { //possible two optional arguments
			if ((argsEnd > 2)
			 && ((args[argsEnd-2].equalsIgnoreCase("-BinPath"))
				|| (args[argsEnd-2].equalsIgnoreCase("-TLLPath"))))
				{	if (args[argsEnd-2].equalsIgnoreCase("-BinPath"))
						defaultBinPath=args[argsEnd-1];
					else defaultTLLPath=args[argsEnd-1]+"TLL/";
					argsEnd=argsEnd-2;
				}
			}
			if (defaultBinPath==null) defaultBinPath=binPathName;
			if (defaultTLLPath==null) defaultTLLPath=tllPrefix;
			BasicMtlLibrary theLib=(BasicMtlLibrary)Library.load(defaultTLLPath+args[0]+tllSuffix);
			compile(theLib,defaultTLLPath,defaultBinPath);
		}
		else log.error("USAGE : java BMTLCompiler <Tllname> [-BinPath <genDir>] [-TLLPath <TLLDir>]");
	}


}
