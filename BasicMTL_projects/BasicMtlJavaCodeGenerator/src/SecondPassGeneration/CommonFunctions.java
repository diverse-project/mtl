/*
 * $Header: /tmp/cvs2svn/cvsroot/BasicMTL_projects/BasicMtlJavaCodeGenerator/src/SecondPassGeneration/CommonFunctions.java,v 1.5 2003-11-04 13:21:55 jpthibau Exp $
 * Created on 21 juil. 2003
 *
 */
package SecondPassGeneration;

import java.io.*;

import org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.Expression;
import org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.Library;
import org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.QualifiedName;
import org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.TheLibraryClass;

import CodeGeneration.*;

/**
 * @author jpthibau
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class CommonFunctions {

	static final org.apache.log4j.Logger logger=BMTLCompiler.getLog();
	private static int genSymbolNumber=0;

	public static String generateNewSymbol()
	{ 	genSymbolNumber++;
		return new String("GenSymbol"+genSymbolNumber);
	}
	
	public static PrintWriter openFile(String name,boolean append)
	{	FileOutputStream outputFile=null;
		try {
			outputFile=new FileOutputStream(name+".java",append);
			logger.info(name+".java succesfully created.");
		}
		catch (FileNotFoundException e) {logger.error("Code generation : Can't open file "+name+".java");}
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
	
	public static void generateCastBefore (PrintWriter output, Expression e) {
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
				output.print("(("); 
				output.print(cast.getDeclarationName());
				output.print(")CommonFunctions.toBMTLDataType(");
				}
		}
	}
	
	public static void generateCastAfter (PrintWriter output, Expression e) {
		QualifiedName cast = e.getToBeCasted();
		if (cast != null) {
			String castWithVar = e.getToBeCastedWithTypeVar();
			if (castWithVar != null)
				output.print(",new Object[]{BMTL_"+e.getToBeCastedWithParameterVar()+"})");
			else output.print("))");
		}

	}
}
