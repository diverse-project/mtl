/*
 * $Header: /tmp/cvs2svn/cvsroot/BasicMTL_projects/BasicMtlAntlr/src/ANTLRParser/BMTLParser.java,v 1.12 2004-10-18 16:00:04 jpthibau Exp $
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
import org.irisa.triskell.MT.utils.MessagesHandler.MSGHandler;

import ANTLRASTWalker.*;


public class BMTLParser {
	static DataInputStream input;
	static final Logger log=MSGHandler.init();


	public static Object Parse(String name,ANTLRWalkerActionsInterface aWalker)
	{	log.debug("Parsing : "+name);
		boolean noPb=true;
		Object builtTree=null;
		try { input=new DataInputStream(new FileInputStream(name)); }
		catch (FileNotFoundException e) {
			log.error("PB opening input file : "+name);}
		try {
			BasicmtlLexer lexer = new BasicmtlLexer(input);
			BasicmtlParser parser = new BasicmtlParser(lexer);
			builtTree=parser.basicMTL(lexer,aWalker);
			}
		catch (antlr.ANTLRException e) {
			noPb=false;
			log.warn("ANTLRException on "+name+", "+e);
			log.warn("=> "+e.getMessage());
			e.printStackTrace();
		}
		catch(Exception e) {
			noPb=false;
			log.warn("exception: "+e+"=>"+e.getMessage());
			log.warn("exception was raised when parsing : "+name+", ");
			e.printStackTrace(); }				
		log.debug("For the file "+name+',');
		log.debug("parsing is over.");
		if (noPb) return builtTree;
		else return null;
	}

	public static void main(String[] args)
	{	DummyWalker aWalker=new DummyWalker();
		if (args.length > 0)
			for (int i=0;i<args.length;i++)
				try { Parse(args[i],aWalker); }
				catch (Exception e) {
					log.error(e.getMessage());
					e.printStackTrace();}
		else log.error("USAGE : java BMTL <sourcefiles>");
	}
}
