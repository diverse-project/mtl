/*
 * $Header: /tmp/cvs2svn/cvsroot/BasicMTL_projects/BasicMtlAntlr2TLLJava/src/TLLBuilder/ForeachAnalyser.java,v 1.2 2004-04-21 18:42:18 edrezen Exp $
 * Created on 24 juil. 2003
 *
 */
package TLLBuilder;

import java.util.Map;
import java.util.Vector;

import org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.*;

/**
 * @author jpthibau
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class ForeachAnalyser extends ASTTopDownVisitor.ForeachAnalyser 
{
	/** */
	public Object ForeachBefore (
		org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.Foreach  ASTnode,
		java.util.Map context
	)
	{	
		int lineNumber = Integer.parseInt((String)ASTnode.getProperty("LineNumber").getValue());
		
		// we build the AST TLL node for the Foreach object
		Foreach theCreatedForeach = new Foreach (lineNumber);

		theCreatedForeach.setContainerOp ((Operation)context.get("CurrentOperation"));
		
		return theCreatedForeach;
	}

	/** */
	public void ForeachVarDeclaration (
        Object theForeach,
        Object varDeclaration,
        Map context
	) 
	{
		Foreach theCreatedForeach = (Foreach) theForeach;
		VarDeclaration varDec = (VarDeclaration) varDeclaration;
		theCreatedForeach.setVarDeclaration (varDec);

		theCreatedForeach.getContainerOp().appendDeclaredVariables (varDec);
		java.util.Vector knownVarDecls = (java.util.Vector)context.get("DeclaredParametersandVars");
		knownVarDecls.addElement(varDec);
		context.put("DeclaredParametersandVars",knownVarDecls);
	}


	/** */
    public void ForeachCollection (
        Object theForeach,
        Object collection,
        Map context
	) 
	{
		Foreach theCreatedForeach = (Foreach) theForeach;
		Expression col = (Expression) collection;
		theCreatedForeach.setCollection (col);		
    }


	/** */
	public void ForeachBodyInstruction (
		Object theForeach,
		Object objectInstr,
		java.util.Map context
	)
	{	
		Foreach theCreatedForeach = (Foreach)theForeach;
		Instruction instr = (Instruction)objectInstr;
		theCreatedForeach.appendBody(instr);
	}


	public void ForeachAfter (
		Object theForeach,
		org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.Foreach ASTnode,
		java.util.Map context
	)
	{	
		Foreach theCreatedForeach = (Foreach)theForeach;
		context.put("Instruction",theCreatedForeach);

	}
}
