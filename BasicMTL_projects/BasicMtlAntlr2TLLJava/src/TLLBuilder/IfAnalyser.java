/*
 * $Header: /tmp/cvs2svn/cvsroot/BasicMTL_projects/BasicMtlAntlr2TLLJava/src/TLLBuilder/IfAnalyser.java,v 1.2 2003-08-14 20:47:46 ffondeme Exp $
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
public class IfAnalyser extends ASTTopDownVisitor.IfAnalyser {

	public Object IfBefore(org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.If ASTnode,java.util.Map context)
	{	int lineNumber=Integer.parseInt((String)ASTnode.getProperty("LineNumber").getValue());
		If theCreatedIf=new If(lineNumber);
		return theCreatedIf;
	}
	
	public void IfCondition(Object theIf,Object objectExpr,java.util.Map context)
	{	If theCreatedIf=(If)theIf;
		Expression expr=(Expression)objectExpr;
		theCreatedIf.setCondition(expr);
	}

	public void IfThenInstruction(Object theIf,Object objectInstr,java.util.Map context)
	{	If theCreatedIf=(If)theIf;
		Instruction instr=(Instruction)objectInstr;
		theCreatedIf.appendThenBody(instr);
	}

	public void IfElseInstruction(Object theIf,Object objectInstr,java.util.Map context)
	{	If theCreatedIf=(If)theIf;
		Instruction instr=(Instruction)objectInstr;
		theCreatedIf.appendElseBody(instr);
	}

	public void IfAfter(Object theIf,org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.If ASTnode,java.util.Map context)
	{	If theCreatedIf=(If)theIf;
		theCreatedIf.setContainerOp((Operation)context.get("CurrentOperation"));
		context.put("Instruction",theCreatedIf);
	}

}
