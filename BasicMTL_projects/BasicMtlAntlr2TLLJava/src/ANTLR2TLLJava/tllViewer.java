/*
 * $Header: /tmp/cvs2svn/cvsroot/BasicMTL_projects/BasicMtlAntlr2TLLJava/src/ANTLR2TLLJava/tllViewer.java,v 1.1 2003-08-06 16:18:48 jpthibau Exp $
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
	static final String tllPrefix="C:\\PROJET_MTL\\ECLIPSE\\workspace\\BasicMtlAntlr2TLLJava\\ThirdParty\\TllLibraries\\";
	static final String tllSuffix=".tll";

	public static void main(String[] args)
	{	if (args.length == 1) {
			BasicMtlLibrary theLib=(BasicMtlLibrary)Library.load(tllPrefix+args[0]+tllSuffix);
			System.out.println(theLib.getName());
	}

	}
}
