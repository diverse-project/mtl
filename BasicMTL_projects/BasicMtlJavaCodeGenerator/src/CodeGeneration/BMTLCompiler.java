/*
 * $Header: /tmp/cvs2svn/cvsroot/BasicMTL_projects/BasicMtlJavaCodeGenerator/src/CodeGeneration/BMTLCompiler.java,v 1.6 2004-06-04 13:24:11 jpthibau Exp $
 * Created on 22 juil. 2003
 *
 */
package CodeGeneration;

import org.irisa.triskell.MT.utils.MessagesHandler.MSGHandler;
import org.irisa.triskell.MT.visitors.Java.AnalysingVisitor.*;
import org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.*;

/**
 * @author jpthibau
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class BMTLCompiler {

	static final String binPathName="..\\BMTLTester\\ThirdParty\\BMTL_bin";
	static final String tllPrefix="..\\TLLTypeChecker\\ThirdParty\\TllLibraries\\";
	static final String tllSuffix=".tll";

	public static void compile(BasicMtlLibrary theLib,String defaultTLLPath,String defaultBinPath)
	{	
		java.util.Hashtable context=new java.util.Hashtable();
		context.put("BaseFileName",defaultBinPath);
		DefaultAnalysingVisitor visitor = new DefaultAnalysingVisitor("FirstPassGeneration");
		DefaultAnalysingVisitor visitor2 = new DefaultAnalysingVisitor("SecondPassGeneration");
		visitor.visit(theLib,context);
		visitor2.visit(theLib,context);
		MSGHandler.info("Code generation is over.");
	}
	
	public static void main(String[] args)
	{	if (args.length > 0) {
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
		else MSGHandler.error("USAGE : java BMTLCompiler <Tllname> [-BinPath <genDir>] [-TLLPath <TLLDir>]");
	}


}
