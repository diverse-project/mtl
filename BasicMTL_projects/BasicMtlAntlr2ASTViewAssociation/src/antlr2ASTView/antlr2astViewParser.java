/*
 * $Id: antlr2astViewParser.java,v 1.8 2004-10-18 16:00:00 jpthibau Exp $
 * Created on 16 juil. 2003
 *
 */
package antlr2ASTView;

/**
 * @author jpthibau
 *
 * This class allows to load a library from a set of mtl files
 * It also deals with associations and inheritance
 */

import org.apache.log4j.Logger;
import org.irisa.triskell.MT.utils.MessagesHandler.MSGHandler;
//import org.apache.log4j.LogManager;
//import org.apache.log4j.xml.DOMConfigurator;

//import ANTLRASTWalker.ANTLRWalkerActionsInterface;
import ANTLRASTWalker.antlrParserInterface;
import ANTLRParser.*;
import BasicMtlASTWithAssociationView.*;

public class antlr2astViewParser implements antlrParserInterface {
	static final Logger log=MSGHandler.init();
	
	private BMTL_LibraryInterface parsedBMTLLib=null;
	private antlr2astView ASTViewproducer=null;

	public antlrParserInterface newParser()
	{	ASTViewproducer=new antlr2astView();
		return (antlrParserInterface)this;
	}

	public org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.Library buildLibraryFromTexts(java.util.Vector filenames)
	{	org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.Library parsedLib=null;
		BMTL_LibraryInterface parsedAssociationTemplatesLib=null;
		
		log.info("Parsing "+filenames.size()+" file(s) for this library" );
		for (int i=0;i<filenames.size();i++) {
			MSGHandler.processedMtlFile=(String)filenames.get(i);
			parsedBMTLLib=ASTViewproducer.buildLibraryFromText(MSGHandler.processedMtlFile);
		}
		MSGHandler.processedMtlFile = null;
		if (parsedBMTLLib.get_BMTL_hasAssociation().getTheBoolean()) {
			parsedAssociationTemplatesLib=new antlr2astView().buildLibraryFromText(System.getProperty("TEMPLATEPATH",".\\Template\\")+"AssociationTemplates.mtl");
			try {
				//transform all associations
				parsedBMTLLib.BMTL_transformAllAssociations(parsedAssociationTemplatesLib);
			} catch (Throwable e) {e.printStackTrace();}
		}
		if (parsedBMTLLib.get_BMTL_hasInheritance().getTheBoolean()) {
			try {
				//transform library inheritance
				parsedBMTLLib.BMTL_transformInheritedLibrary();
		} catch (Throwable e) {e.printStackTrace();}
	}
		try {
		//transform to ASTjava
		parsedLib=(org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.Library)parsedBMTLLib.BMTL_toASTJava(); //+++++ Added for View production +++
		} catch (Throwable e) {e.printStackTrace();}
		return parsedLib;
	}
}
