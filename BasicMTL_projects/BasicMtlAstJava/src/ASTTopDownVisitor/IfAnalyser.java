/*
 * $Header: /tmp/cvs2svn/cvsroot/BasicMTL_projects/BasicMtlAstJava/src/ASTTopDownVisitor/IfAnalyser.java,v 1.1 2003-07-28 07:35:34 jpthibau Exp $
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
public class IfAnalyser extends Analyser {

	public IfAnalyser()
	{	super(If.class);
	}

	public void analyse(Visitable node,Visitor visitor,java.util.Map context)
	{	int i,limit;
		If ASTnode=(If) node;
		this.IfBefore(ASTnode,context);
		((Expression)ASTnode.getCondition()).accept(visitor,context);
		this.IfCondition(context.get("Instruction"),context);
		limit=ASTnode.cardThenBody();
		for (i=0;i<limit;i++) {
			((Instruction)ASTnode.getThenBody(i)).accept(visitor,context);
			this.IfThenInstruction(context.get("Instruction"),context);
		}
		limit=ASTnode.cardElseBody();
		for (i=0;i<limit;i++) {
			((Instruction)ASTnode.getElseBody(i)).accept(visitor,context);
			this.IfElseInstruction(context.get("Instruction"),context);
		}
		this.IfAfter(ASTnode,context);
	}

	public void IfBefore(If ASTnode,java.util.Map context) {}

	public void IfCondition(Object expr,java.util.Map context) {}

	public void IfThenInstruction(Object instr,java.util.Map context) {}

	public void IfElseInstruction(Object instr,java.util.Map context) {}

	public void IfAfter(If ASTnode,java.util.Map context) {}

}
