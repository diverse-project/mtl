/*
 * $Header: /tmp/cvs2svn/cvsroot/BasicMTL_projects/BasicMtlJavaCodeGenerator/src/SecondPassGeneration/WhileAnalyser.java,v 1.4 2003-08-20 16:07:33 ffondeme Exp $
 * Created on 7 août 2003
 *
 */
package SecondPassGeneration;

import java.io.*;
import java.util.Map;

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
		outputForClass.print("do {\nBooleanValue "+genSymbol+" = (BooleanValue)");
		return genSymbol;}

	public void WhileCondition(Object theWhile,Object expr,java.util.Map context)
	{	String genSymbol=(String)theWhile;
		PrintWriter outputForClass = (PrintWriter)context.get("OutputForClass");
		outputForClass.println(';');
		outputForClass.println();
		outputForClass.println("if ("+genSymbol+".getTheBoolean()) {");
	}

	public void WhileAfter(Object theWhile,While ASTnode,java.util.Map context)
	{	String genSymbol=(String)theWhile;
		PrintWriter outputForClass = (PrintWriter)context.get("OutputForClass");
		outputForClass.println('}');
		outputForClass.println();
		outputForClass.println("} while ("+genSymbol+".getTheBoolean());");
		context.put("NeedsSemiColumn", Boolean.FALSE);
	}

	public void WhileBodyInstruction(
		Object theWhile,
		Object instr,
		Map context) {
		Boolean needsColumn = (Boolean)context.get("NeedsSemiColumn");
		if (needsColumn == null || needsColumn.booleanValue()) {
			PrintWriter outputForClass = (PrintWriter)context.get("OutputForClass");
			outputForClass.println(';');
		}
		context.put("NeedsSemiColumn", Boolean.TRUE);
	}

}
