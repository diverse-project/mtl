/*
 * Created on 16 juil. 2003
 * $Id: antlr2astJavaParser.java,v 1.2 2004-02-16 17:27:08 dvojtise Exp $
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

import org.apache.log4j.Logger;
//import org.apache.log4j.LogManager;
//import org.apache.log4j.xml.DOMConfigurator;
//
//import ANTLRASTWalker.ANTLRWalkerActionsInterface;
import ANTLRASTWalker.antlrParserInterface;
import ANTLRParser.*;

public class antlr2astJavaParser implements antlrParserInterface {

	public static final org.apache.log4j.Logger log = Logger.getLogger("BMTLParser");

	public static org.apache.log4j.Logger getLog () {
			return BMTLParser.log;
	}
	private org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.Library parsedLib=null;
	private antlr2ast ASTproducer=null;
	
	public antlrParserInterface newParser()
	{	ASTproducer=new antlr2ast();
		return (antlrParserInterface)this;
	}

	public org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.Library buildLibraryFromTexts(java.util.Vector filenames)
	{	for (int i=0;i<filenames.size();i++)
			//to add the new parsed file to the TLL when several files are given
			//the context contains the created  TLL that udpdates at each step
			parsedLib=ASTproducer.buildLibraryFromText((String)filenames.get(i));
		return parsedLib;
	}
}
