/*
 * $Header: /tmp/cvs2svn/cvsroot/BasicMTL_projects/BasicMtlAntlr2ASTView/src/antlr2ASTView/antlr2astViewParser.java,v 1.2 2003-10-17 14:45:42 jpthibau Exp $
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

import org.apache.log4j.Logger;
import org.apache.log4j.LogManager;
import org.apache.log4j.xml.DOMConfigurator;

import ANTLRASTWalker.ANTLRWalkerActionsInterface;
import ANTLRASTWalker.antlrParserInterface;
import ANTLRParser.*;
import BasicMtlASTView.*;

public class antlr2astViewParser implements antlrParserInterface {

	public static final org.apache.log4j.Logger log = Logger.getLogger("BMTLParser");

	public static org.apache.log4j.Logger getLog () {
			return BMTLParser.log;
	}
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