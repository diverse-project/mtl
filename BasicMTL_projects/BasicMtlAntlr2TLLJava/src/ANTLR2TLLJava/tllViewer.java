/*
 * $Header: /tmp/cvs2svn/cvsroot/BasicMTL_projects/BasicMtlAntlr2TLLJava/src/ANTLR2TLLJava/tllViewer.java,v 1.3 2003-11-14 17:29:52 jpthibau Exp $
 * Created on 23 juil. 2003
 *
 */
package ANTLR2TLLJava;

import org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.*;

/**
 * @author jpthibau
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class tllViewer {
	static final String tllPrefix="..\\BasicMtlAntlr2TLLJava\\ThirdParty\\TllLibraries\\";
	static final String tllSuffix=".tll";

	public static void main(String[] args)
	{	
		String file;
		if (args.length == 1)
			file = tllPrefix+args[0]+tllSuffix;
		else if (args.length == 2 && args[0].equalsIgnoreCase("-completeName"))
			file = args[1];
		else	
			throw new IllegalArgumentException();
		BasicMtlLibrary theLib=(BasicMtlLibrary)Library.load(file);
		System.out.println(theLib.getName());
		 
	}
}
