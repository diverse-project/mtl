/*
 * Created on 21 juil. 2003
 * $Id: CommonFunctions.java,v 1.12 2004-10-18 16:01:26 jpthibau Exp $
 * Authors : jpthibau
 * 
 * Copyright 2004 - INRIA - LGPL license
 */
package SecondPassGeneration;

import java.io.*;

import org.apache.log4j.Logger;
import org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.Expression;
//import org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.Library;
import org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.QualifiedName;
import org.irisa.triskell.MT.utils.MessagesHandler.MSGHandler;
//import org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.TheLibraryClass;

import CodeGeneration.*;

/**
 * @author jpthibau
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class CommonFunctions {
	static final Logger log=MSGHandler.init();

	private static int genSymbolNumber=0;

	public static String generateNewSymbol()
	{ 	genSymbolNumber++;
		return new String("GenSymbol"+genSymbolNumber);
	}
	
	public static PrintWriter openFile(String name,boolean append)
	{	FileOutputStream outputFile=null;
		try {
			outputFile=new FileOutputStream(name+".java",append);
			log.debug(name+".java succesfully created.");
		}
		catch (FileNotFoundException e) {log.error("Code generation : Can't open file "+name+".java");}
		PrintWriter output = new PrintWriter(outputFile);
		return output;
	}
	
	public static void generatePackgeImports(PrintWriter output,String packageName)
	{	output.println("package "+packageName+";\n\n");
		output.println("import org.irisa.triskell.MT.DataTypes.Java.defaultImpl.*;");
		output.println("import org.irisa.triskell.MT.BasicMTL.TopTypes.*;");
		output.println("import org.irisa.triskell.MT.repository.API.Java.*;");
		output.println("import org.irisa.triskell.MT.DataTypes.Java.*;\n\n");
	}
	
	public static void generateCastBefore (PrintWriter output, Expression e,boolean isThrownExpr) {
		QualifiedName cast = e.getToBeCasted();
		if (cast != null) {
			String castWithVar = e.getToBeCastedWithTypeVar();
			if (castWithVar != null) {
				output.print("(Value)((BMTLType)BMTL_"+castWithVar+".getTheType()).getTheClass()");
				output.print(".getMethod(\"BMTL_\"+BMTL_"+e.getToBeCastedWithMethodVar()+".getTheString(),");
				output.print("new Class[]{org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOclAnyInterface.class})");
				output.print(".invoke(");
				}
			else {
				if (isThrownExpr)
					output.print("("+cast.getPureDeclarationName()+")"); 
				else {
					output.print("(("); 
					output.print(cast.getDeclarationName());
					output.print(")CommonFunctions.toBMTLDataType(");
					}
				}
		}
	}
	
	public static void generateCastAfter (PrintWriter output, Expression e,boolean isThrownExpr) {
		QualifiedName cast = e.getToBeCasted();
		if (cast != null) {
			String castWithVar = e.getToBeCastedWithTypeVar();
			if (castWithVar != null)
				output.print(",new Object[]{BMTL_"+e.getToBeCastedWithParameterVar()+"})");
			else {	if (! isThrownExpr) output.print("))");
					} 
		}

	}
}
