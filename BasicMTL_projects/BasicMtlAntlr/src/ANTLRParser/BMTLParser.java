/*
 * $Header: /tmp/cvs2svn/cvsroot/BasicMTL_projects/BasicMtlAntlr/src/ANTLRParser/BMTLParser.java,v 1.10 2004-06-11 11:30:21 jpthibau Exp $
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

import org.irisa.triskell.MT.utils.MessagesHandler.MSGHandler;

import ANTLRASTWalker.*;


public class BMTLParser {
	static DataInputStream input;


	public static Object Parse(String name,ANTLRWalkerActionsInterface aWalker)
	{	MSGHandler.debug(BMTLParser.class,27,"Parsing : "+name);
		boolean noPb=true;
		Object builtTree=null;
		try { input=new DataInputStream(new FileInputStream(name)); }
		catch (FileNotFoundException e) {
			MSGHandler.error(BMTLParser.class,32,"PB opening input file : "+name);}
		try {
			BasicmtlLexer lexer = new BasicmtlLexer(input);
			BasicmtlParser parser = new BasicmtlParser(lexer);
			builtTree=parser.basicMTL(lexer,aWalker);
			}
		catch (antlr.ANTLRException e) {
			noPb=false;
			MSGHandler.warn(BMTLParser.class,40,"ANTLRException on "+name+", "+e);
			MSGHandler.warn(BMTLParser.class,41,"=> "+e.getMessage());
			e.printStackTrace();
		}
		catch(Exception e) {
			noPb=false;
			MSGHandler.warn(BMTLParser.class,46,"exception: "+e+"=>"+e.getMessage());
			MSGHandler.warn(BMTLParser.class,47,"exception was raised when parsing : "+name+", ");
			e.printStackTrace(); }				
		MSGHandler.debug(BMTLParser.class,49,"For the file "+name+',');
		MSGHandler.debug(BMTLParser.class,50,"parsing is over.");
		if (noPb) return builtTree;
		else return null;
	}

	public static void main(String[] args)
	{	DummyWalker aWalker=new DummyWalker();
		if (args.length > 0)
			for (int i=0;i<args.length;i++)
				try { Parse(args[i],aWalker); }
				catch (Exception e) {
					MSGHandler.error(BMTLParser.class,61,e.getMessage());
					e.printStackTrace();}
		else MSGHandler.error(BMTLParser.class,63,"USAGE : java BMTL <sourcefiles>");
	}
}
