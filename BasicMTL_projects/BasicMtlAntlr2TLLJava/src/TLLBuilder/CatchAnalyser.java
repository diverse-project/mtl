/*
 * $Header: /tmp/cvs2svn/cvsroot/BasicMTL_projects/BasicMtlAntlr2TLLJava/src/TLLBuilder/CatchAnalyser.java,v 1.2 2003-08-08 15:26:16 jpthibau Exp $
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
public class CatchAnalyser extends ASTTopDownVisitor.CatchAnalyser {

	public Object CatchBefore(org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.Catch ASTnode,java.util.Map context)
	{	int lineNumber=Integer.parseInt((String)ASTnode.getProperty("LineNumber").getValue());
		Catch theCreatedCatch=new Catch(lineNumber);
		return theCreatedCatch;
	}

	public void CatchVarDeclaration(Object theCatch,Object objectVarDecl,java.util.Map context)
	{	Catch theCreatedCatch=(Catch)theCatch;
		VarDeclaration varDecl=(VarDeclaration)objectVarDecl;
		varDecl.setIsFormalParameter(true);
		theCreatedCatch.setCatchedException(varDecl);
		java.util.Vector knownVarDecls=(java.util.Vector)context.get("DeclaredParametersandVars");
		knownVarDecls.addElement(varDecl);
		context.put("DeclaredParametersandVars",knownVarDecls);
	}

	public void CatchInstruction(Object theCatch,Object objectInstr,java.util.Map context)
	{	Catch theCreatedCatch=(Catch)theCatch;
		Instruction instr=(Instruction)objectInstr;
		theCreatedCatch.appendCatchBody(instr);
	}

	public void CatchAfter(Object theCatch,org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.Catch ASTnode,java.util.Map context)
	{	Catch theCreatedCatch=(Catch)theCatch;
		context.put("Catch",theCreatedCatch);
	}

}
