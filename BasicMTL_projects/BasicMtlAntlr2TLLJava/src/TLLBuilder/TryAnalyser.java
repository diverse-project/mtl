/*
 * $Header: /tmp/cvs2svn/cvsroot/BasicMTL_projects/BasicMtlAntlr2TLLJava/src/TLLBuilder/TryAnalyser.java,v 1.1 2003-08-06 16:18:45 jpthibau Exp $
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
public class TryAnalyser extends ASTTopDownVisitor.TryAnalyser {

	public Object TryBefore(org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.Try ASTnode,java.util.Map context)
	{	int lineNumber=Integer.parseInt((String)ASTnode.getProperty("LineNumber").getValue());
		Try theCreatedTry=new Try(lineNumber);
		return theCreatedTry;
	}

	public void TryBodyInstruction(Object theTry,Object objectInstr,java.util.Map context)
	{	Try theCreatedTry=(Try)theTry;
		Instruction instr=(Instruction)objectInstr;
		theCreatedTry.appendTryBody(instr);
	}

	public void TryCatchPart(Object theTry,Object objectCatchPart,java.util.Map context)
	{	Try theCreatedTry=(Try)theTry;
		Catch catchPart=(Catch)objectCatchPart;
		theCreatedTry.appendCatchPart(catchPart);
	}

	public void TryFinalizeInstruction(Object theTry,Object objectInstr,java.util.Map context)
	{	Try theCreatedTry=(Try)theTry;
		Instruction instr=(Instruction)objectInstr;
		theCreatedTry.appendFinalizeBody(instr);
	}
	
	public void TryAfter(Object theTry,org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.Try ASTnode,java.util.Map context)
	{	Try theCreatedTry=(Try)theTry;
		context.put("Instruction",theCreatedTry);
	}

}
