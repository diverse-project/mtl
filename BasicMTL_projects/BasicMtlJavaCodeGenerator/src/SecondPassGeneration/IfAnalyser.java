/*
 * $Header: /tmp/cvs2svn/cvsroot/BasicMTL_projects/BasicMtlJavaCodeGenerator/src/SecondPassGeneration/IfAnalyser.java,v 1.4 2003-08-20 16:07:34 ffondeme Exp $
 * Created on 7 ao�t 2003
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
public class IfAnalyser extends TLLTopDownVisitor.IfAnalyser {

	public Object IfBefore(If ASTnode,java.util.Map context)
	{	PrintWriter outputForClass = (PrintWriter)context.get("OutputForClass");
		String genSymbol=CommonFunctions.generateNewSymbol();
		outputForClass.print("BooleanValue "+genSymbol+" = (BooleanValue)");
		return genSymbol;
	}

	public void IfCondition(Object theIf,Object expr,java.util.Map context)
	{	String genSymbol=(String)theIf;
		PrintWriter outputForClass = (PrintWriter)context.get("OutputForClass");
		outputForClass.println(";\nif ("+genSymbol+".getTheBoolean()) {");
	}

	public void IfElsePart(java.util.Map context)
	{	PrintWriter outputForClass = (PrintWriter)context.get("OutputForClass");
		outputForClass.println("} else {");
	}

	public void IfAfter(Object theIf,If ASTnode,java.util.Map context)
	{	PrintWriter outputForClass = (PrintWriter)context.get("OutputForClass");
		outputForClass.println('}');
		context.put("NeedsSemiColumn", Boolean.FALSE);
	}

	public void IfElseInstruction(Object theIf, Object instr, Map context) {
		this.IfInstruction(theIf, context);
	}

	public void IfThenInstruction(Object theIf, Object instr, Map context) {
		this.IfInstruction(theIf, context);
	}
	
	public void IfInstruction(Object theIf, Map context) {
		Boolean needsColumn = (Boolean)context.get("NeedsSemiColumn");
		if (needsColumn == null || needsColumn.booleanValue()) {
			PrintWriter outputForClass = (PrintWriter)context.get("OutputForClass");
			outputForClass.println(';');
		}
		context.put("NeedsSemiColumn", Boolean.TRUE);
	}

}
