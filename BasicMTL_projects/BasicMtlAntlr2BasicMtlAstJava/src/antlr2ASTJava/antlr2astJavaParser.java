/*
 * Created on 16 juil. 2003
 * $Id: antlr2astJavaParser.java,v 1.4 2004-06-11 11:34:05 jpthibau Exp $
 * Authors : jpthibau
 * 
 * Copyright 2004 - INRIA - LGPL license
 */
package antlr2ASTJava;

/**
 * @author jpthibau
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */

//
import org.irisa.triskell.MT.utils.MessagesHandler.MSGHandler;

import ANTLRASTWalker.antlrParserInterface;
import ANTLRParser.*;

public class antlr2astJavaParser implements antlrParserInterface {

	private org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.Library parsedLib=null;
	private antlr2ast ASTproducer=null;
	
	public antlrParserInterface newParser()
	{	ASTproducer=new antlr2ast();
		return (antlrParserInterface)this;
	}

	public org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.Library buildLibraryFromTexts(java.util.Vector filenames)
	{	
		MSGHandler.info(antlr2astJavaParser.class,33,"Parsing "+filenames.size()+" file(s) for this library" );
		for (int i=0;i<filenames.size();i++)
			//to add the new parsed file to the TLL when several files are given
			//the context contains the created  TLL that udpdates at each step
			parsedLib=ASTproducer.buildLibraryFromText((String)filenames.get(i));
		return parsedLib;
	}
}
