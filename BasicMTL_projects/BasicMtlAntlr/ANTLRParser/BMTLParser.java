/*
 * $Header: /tmp/cvs2svn/cvsroot/BasicMTL_projects/BasicMtlAntlr/ANTLRParser/BMTLParser.java,v 1.1 2003-07-17 16:06:05 jpthibau Exp $
 * Created on 16 juil. 2003
 *
 */
package ANTLRParser;

/**
 * @author jpthibau
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */

import java.io.*;
import org.apache.log4j.Logger;
import org.apache.log4j.LogManager;
import org.apache.log4j.xml.DOMConfigurator;
import ANTLRASTWalker.*;


public class BMTLParser {
	static DataInputStream input;

	public static final org.apache.log4j.Logger log = Logger.getLogger("BMTLParser");

	public static org.apache.log4j.Logger getLog () {
			return BMTLParser.log;
	}

	public static Object Parse(String name,ANTLRWalkerActionsInterface aWalker)
	{	log.info("Opening source file : "+name);
		boolean noPb=true;
		Object builtTree=null;
		try { input=new DataInputStream(new FileInputStream(name)); }
		catch (FileNotFoundException e) {
			log.error("PB input file opening");}
		try {
			BasicmtlLexer lexer = new BasicmtlLexer(input);
			BasicmtlParser parser = new BasicmtlParser(lexer);
			builtTree=parser.basicMTL(lexer,aWalker);
			}
		catch(Exception e) {
			noPb=false;
			log.warn("exception: "+e+"=>"+e.getMessage());
			e.printStackTrace(); }				
		log.info("For the file "+name+",");
		log.info("parsing is over.");
		if (noPb) return builtTree;
		else return null;
	}

	public static void main(String[] args)
	{	DummyWalker aWalker=new DummyWalker();
		try {
			String filePath = new java.io.File("ThirdParty/log4j/log4j_configuration").getCanonicalPath();
			LogManager.resetConfiguration();
			DOMConfigurator.configure(filePath); }
		catch(java.io.IOException e) {
			System.err.println("Can't state log4j in BMTLParser"); }
		if (args.length > 0)
			for (int i=0;i<args.length;i++)
				try { Parse(args[i],aWalker); }
				catch (Exception e) {
					log.error(e);
					e.printStackTrace();}
		else log.error("USAGE : java BMTL <sourcefiles>");
	}
}
