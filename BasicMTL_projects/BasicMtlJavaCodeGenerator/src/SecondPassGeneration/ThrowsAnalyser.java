/*
 * $Header: /tmp/cvs2svn/cvsroot/BasicMTL_projects/BasicMtlJavaCodeGenerator/src/SecondPassGeneration/ThrowsAnalyser.java,v 1.6 2003-12-16 07:51:44 jpthibau Exp $
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
public class ThrowsAnalyser extends TLLTopDownVisitor.ThrowsAnalyser {

	public Object ThrowsBefore(Throws ASTnode,java.util.Map context)
	{	PrintWriter outputForClass = (PrintWriter)context.get("OutputForClass");
		outputForClass.print("throw ");
		return null; }

/*	public Object ThrowsBefore(Throws ASTnode,java.util.Map context)
	{	PrintWriter outputForClass = (PrintWriter)context.get("OutputForClass");
		String genSymbol = CommonFunctions.generateNewSymbol();
		outputForClass.print("Throwable " + genSymbol + " = (Throwable)CommonFunctions.toBMTLDataType(");
		return genSymbol; }

	public void ThrowsExpression(Object theThrows, Object expr, Map context) {
		PrintWriter outputForClass = (PrintWriter)context.get("OutputForClass");
		outputForClass.println(");");
	}

	public void ThrowsAfter(Object theThrows, Throws ASTnode, Map context) {
		PrintWriter outputForClass = (PrintWriter)context.get("OutputForClass");
		outputForClass.println(((String)theThrows) + ".initCause(new Exception(\"Manually raised exception at line " + ASTnode.getLineNumber() +"\"));");
		outputForClass.print("throw " + theThrows);
	} */

}
