/*
 * $Header: /tmp/cvs2svn/cvsroot/BasicMTL_projects/BasicMtlAntlr2TLLJava/src/TLLBuilder/ThrowsAnalyser.java,v 1.4 2003-12-19 15:24:14 jpthibau Exp $
 * Created on 24 juil. 2003
 *
 */
package TLLBuilder;

import org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.*;

/**
 * @author jpthibau
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class ThrowsAnalyser extends ASTTopDownVisitor.ThrowsAnalyser {

	public Object ThrowsBefore(org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.Throws ASTnode,java.util.Map context)
	{	int lineNumber=Integer.parseInt((String)ASTnode.getProperty("LineNumber").getValue());
		Throws theCreatedThrows=new Throws(lineNumber);
		return theCreatedThrows;
	}
	public void ThrowsExpression(Object theThrows,Object objectExpr,java.util.Map context)
	{	Throws theCreatedThrows=(Throws)theThrows;
		Expression expr=(Expression)objectExpr;
//		expr.setIsTrownExpression(true);
		theCreatedThrows.setThrownExpression(expr);
	}

	public void ThrowsAfter(Object theThrows,org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.Throws ASTnode,java.util.Map context)
	{	Throws theCreatedThrows=(Throws)theThrows;
		theCreatedThrows.setContainerOp((Operation)context.get("CurrentOperation"));
		context.put("Instruction",theCreatedThrows);
	}

}
