/*
 * $Header: /tmp/cvs2svn/cvsroot/BasicMTL_projects/BasicMtlAntlr2ASTView/src/antlr2ASTView/antlr2astViewParser.java,v 1.3 2004-06-11 11:31:45 jpthibau Exp $
 * Created on 16 juil. 2003
 *
 */
package antlr2ASTView;

/**
 * @author jpthibau
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */


import ANTLRASTWalker.ANTLRWalkerActionsInterface;
import ANTLRASTWalker.antlrParserInterface;
import ANTLRParser.*;
import BasicMtlASTView.*;

public class antlr2astViewParser implements antlrParserInterface {

	private BMTL_LibraryInterface parsedBMTLLib=null;
	private antlr2astView ASTViewproducer=null;

	public antlrParserInterface newParser()
	{	ASTViewproducer=new antlr2astView();
		return (antlrParserInterface)this;
	}

	public org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.Library buildLibraryFromTexts(java.util.Vector filenames)
	{	org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.Library parsedLib=null;
		for (int i=0;i<filenames.size();i++)
			parsedBMTLLib=ASTViewproducer.buildLibraryFromText((String)filenames.get(i));
		try {
		//transform library inheritance
		parsedBMTLLib.BMTL_transformInheritedLibrary();
		//transform to ASTjava
		parsedLib=(org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.Library)parsedBMTLLib.BMTL_toASTJava(); //+++++ Added for View production +++
		} catch (Throwable e) {e.printStackTrace();}
		return parsedLib;
	}
}
