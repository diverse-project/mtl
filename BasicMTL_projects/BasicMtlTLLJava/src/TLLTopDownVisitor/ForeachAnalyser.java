/*
 * $Header: /tmp/cvs2svn/cvsroot/BasicMTL_projects/BasicMtlTLLJava/src/TLLTopDownVisitor/ForeachAnalyser.java,v 1.3 2004-04-28 17:32:48 edrezen Exp $
 * Created on 24 juil. 2003
 *
 */
package TLLTopDownVisitor;

import org.irisa.triskell.MT.visitors.Java.AnalysingVisitor.*;
import org.irisa.triskell.MT.visitors.Java.GenericVisitor.*;
import org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.*;

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

	/** GOF template method for visiting a foreach node 
	 */
	public void analyse (Visitable node, Visitor visitor,java.util.Map context)
	{	
		Foreach ASTnode = (Foreach) node;
		
		// primitive method before other treatments
		Object theCreatedObject = ForeachBefore (ASTnode,context);

		// we visit the collection and call the primitive method for the collection
		if (ASTnode.getCollection() != null)
		{		
			ASTnode.getCollection().accept (visitor,context);
		}
		this.ForeachCollection (ASTnode, theCreatedObject, context);

		// we call the primitive method for the variable declaration
		this.ForeachVarDeclaration (ASTnode, theCreatedObject, context);

		// we visit the condition and call the primitive method for the condition
		if (ASTnode.getCondition() != null)
		{
			ASTnode.getCondition().accept (visitor,context);
		}
		this.ForeachCondition (ASTnode, theCreatedObject, context);

		// we loop over the instructions of the foreach body
		for (int i=0; i<ASTnode.cardBody(); i++) 
		{
			((Instruction)ASTnode.getBody(i)).accept (visitor,context);
			this.ForeachBodyInstruction ((Instruction)ASTnode.getBody(i), theCreatedObject, context);
		}

		// we call the primitive method for post processing
		ForeachAfter (ASTnode, theCreatedObject, context);
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
	public void ForeachVarDeclaration (
		Foreach node,
		Object createdObject, 
		java.util.Map context
	) 
	{
	}


	/** */
	public void ForeachCollection (
		Foreach node, 
		Object createdObject, 
		java.util.Map context
	) 
	{
	}


	/** */
	public void ForeachCondition (
		Foreach node,
		Object createdObject, 
		java.util.Map context
	) 
	{
	}


	/** */
	public void ForeachBodyInstruction (
		Instruction instr,
		Object createdObject, 
		java.util.Map context
	) 
	{
	}
	
	/** */
	public void ForeachAfter (
		Foreach node,
		Object createdObject, 
		java.util.Map context
	) 
	{
	}
	
}
