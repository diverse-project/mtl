/*
 * $Header: /tmp/cvs2svn/cvsroot/BasicMTL_projects/BasicMtlJavaCodeGenerator/src/SecondPassGeneration/WhileAnalyser.java,v 1.1 2003-08-08 15:41:12 jpthibau Exp $
 * Created on 7 août 2003
 *
 */
package SecondPassGeneration;

import java.io.*;
import org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.*;

/**
 * @author jpthibau
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class WhileAnalyser extends TLLTopDownVisitor.WhileAnalyser {

	public Object WhileBefore(While ASTnode,java.util.Map context)
	{	PrintWriter outputForClass = (PrintWriter)context.get("OutputForClass");
		String genSymbol=CommonFunctions.generateNewSymbol();
		outputForClass.print("do {\nBooleanValueImpl "+genSymbol+" = (BooleanValueImpl)");
		return genSymbol;}

	public void WhileCondition(Object theWhile,Object expr,java.util.Map context)
	{	String genSymbol=(String)theWhile;
		PrintWriter outputForClass = (PrintWriter)context.get("OutputForClass");
		outputForClass.println(";\n");
		outputForClass.println("if ("+genSymbol+".getTheBoolean()) {");
	}

	public void WhileAfter(Object theWhile,While ASTnode,java.util.Map context)
	{	String genSymbol=(String)theWhile;
		PrintWriter outputForClass = (PrintWriter)context.get("OutputForClass");
		outputForClass.println("}\n");
		outputForClass.println("} while ("+genSymbol+".getTheBoolean());");
	}

}
