/*
 * $Header: /tmp/cvs2svn/cvsroot/BasicMTL_projects/BasicMtlJavaCodeGenerator/src/SecondPassGeneration/IfAnalyser.java,v 1.2 2003-08-14 21:31:40 ffondeme Exp $
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
public class IfAnalyser extends TLLTopDownVisitor.IfAnalyser {

	public Object IfBefore(If ASTnode,java.util.Map context)
	{	PrintWriter outputForClass = (PrintWriter)context.get("OutputForClass");
		String genSymbol=CommonFunctions.generateNewSymbol();
		outputForClass.print("BooleanValueImpl "+genSymbol+" = (BooleanValueImpl)");
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
	}

}
