/*
 * $Header: /tmp/cvs2svn/cvsroot/BasicMTL_projects/BasicMtlTLLJava/src/TLLTopDownVisitor/IfAnalyser.java,v 1.2 2003-08-08 15:46:47 jpthibau Exp $
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
public class IfAnalyser extends Analyser {

	public IfAnalyser()
	{	super(If.class);
	}

	public void analyse(Visitable node,Visitor visitor,java.util.Map context)
	{	int i,limit;
		If ASTnode=(If) node;
		Object theIf=this.IfBefore(ASTnode,context);
		((Expression)ASTnode.getCondition()).accept(visitor,context);
		this.IfCondition(theIf,context.get("Instruction"),context);
		limit=ASTnode.cardThenBody();
		for (i=0;i<limit;i++) {
			((Instruction)ASTnode.getThenBody(i)).accept(visitor,context);
			this.IfThenInstruction(theIf,context.get("Instruction"),context);
		}
		limit=ASTnode.cardElseBody();
		if (limit > 0) this.IfElsePart(context);
		for (i=0;i<limit;i++) {
			((Instruction)ASTnode.getElseBody(i)).accept(visitor,context);
			this.IfElseInstruction(theIf,context.get("Instruction"),context);
		}
		this.IfAfter(theIf,ASTnode,context);
	}

	public Object IfBefore(If ASTnode,java.util.Map context)
	{	return null;}

	public void IfCondition(Object theIf,Object expr,java.util.Map context) {}

	public void IfElsePart(java.util.Map context) {}

	public void IfThenInstruction(Object theIf,Object instr,java.util.Map context) {}

	public void IfElseInstruction(Object theIf,Object instr,java.util.Map context) {}

	public void IfAfter(Object theIf,If ASTnode,java.util.Map context) {}

}
