/*
 * $Header: /tmp/cvs2svn/cvsroot/BasicMTL_projects/BasicMtlAntlr2TLLJava/src/TLLBuilder/WhileAnalyser.java,v 1.1 2003-08-06 16:18:45 jpthibau Exp $
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
public class WhileAnalyser extends ASTTopDownVisitor.WhileAnalyser {

	public Object WhileBefore(org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.While ASTnode,java.util.Map context)
	{	int lineNumber=Integer.parseInt((String)ASTnode.getProperty("LineNumber").getValue());
		While theCreatedWhile=new While(lineNumber);
		return theCreatedWhile;
	}

	public void WhileCondition(Object theWhile,Object objectExpr,java.util.Map context)
	{	While theCreatedWhile=(While)theWhile;
		Expression expr=(Expression)objectExpr;
		theCreatedWhile.setCondition(expr);
	}

	public void WhileBodyInstruction(Object theWhile,Object objectInstr,java.util.Map context)
	{	While theCreatedWhile=(While)theWhile;
		Instruction instr=(Instruction)objectInstr;
		theCreatedWhile.appendBody(instr);
	}


	public void WhileAfter(Object theWhile,org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.While ASTnode,java.util.Map context)
	{	While theCreatedWhile=(While)theWhile;
		context.put("Instruction",theCreatedWhile);
	}
}
