/*
 * $Header: /tmp/cvs2svn/cvsroot/BasicMTL_projects/BasicMtlAstJava/src/ASTTopDownVisitor/ForeachAnalyser.java,v 1.2 2004-04-28 07:27:16 edrezen Exp $
 * Created on 24 juil. 2003
 *
 */
package ASTTopDownVisitor;

import org.irisa.triskell.MT.visitors.Java.AnalysingVisitor.*;
import org.irisa.triskell.MT.visitors.Java.GenericVisitor.*;
import org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.*;

/**
 * @author jpthibau
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class ForeachAnalyser extends Analyser {

	public ForeachAnalyser()
	{	super(Foreach.class);
	}

	public void analyse (Visitable node, Visitor visitor, java.util.Map context)
	{	
		Foreach ASTnode = (Foreach) node;
		Object  theCreatedForeach = ForeachBefore (ASTnode, context);

		ASTnode.getVarDeclaration().accept (visitor,context);
		this.ForeachVarDeclaration (theCreatedForeach, context.get("VarDeclaration"), context);

		ASTnode.getCollection().accept (visitor,context);
		this.ForeachCollection (theCreatedForeach, context.get("Instruction"), context);

		if (ASTnode.getCondition() != null)
		{
			ASTnode.getCondition().accept (visitor,context);
			this.ForeachCondition (theCreatedForeach, context.get("Instruction"), context);
		}

		// we loop over the instructions of the foreach body
		for (int i=0; i<ASTnode.cardBody(); i++) 
		{
			((Instruction)ASTnode.getBody(i)).accept (visitor,context);
			this.ForeachBodyInstruction (theCreatedForeach,context.get("Instruction"),context);
		}

		ForeachAfter (theCreatedForeach, ASTnode, context);
	}

	/** */
	public Object ForeachBefore (
		Foreach ASTnode,
		java.util.Map context
	)	
	{	
		return null; 
	}

	/** */
	public void ForeachAfter (
		Object theForeach, 
		Foreach ASTnode,
		java.util.Map context
	) 
	{
	}


	/** */
	public void ForeachVarDeclaration (
		Object theForeach, 
		Object varDeclaration, 
		java.util.Map context
	) 
	{
	}


	/** */
	public void ForeachCollection (
		Object theForeach, 
		Object collection, 
		java.util.Map context
	) 
	{
	}

	/** */
	public void ForeachCondition (
		Object theForeach, 
		Object condition, 
		java.util.Map context
	) 
	{
	}

	/** */
	public void ForeachBodyInstruction (
		Object theForeach, 
		Object instr, 
		java.util.Map context
	) 
	{
	}


}
