/*
 * $Header: /tmp/cvs2svn/cvsroot/BasicMTL_projects/BasicMtlAstJava/src/ASTTopDownVisitor/WhileAnalyser.java,v 1.2 2003-08-06 16:27:30 jpthibau Exp $
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
public class WhileAnalyser extends Analyser {

	public WhileAnalyser()
	{	super(While.class);
	}

	public void analyse(Visitable node,Visitor visitor,java.util.Map context)
	{	int i,limit;
		While ASTnode=(While) node;
		Object theWhile=WhileBefore(ASTnode,context);
		((Expression)ASTnode.getCondition()).accept(visitor,context);
		this.WhileCondition(theWhile,context.get("Instruction"),context);
		limit=ASTnode.cardBody();
		for (i=0;i<limit;i++) {
			((Instruction)ASTnode.getBody(i)).accept(visitor,context);
			this.WhileBodyInstruction(theWhile,context.get("Instruction"),context);
		}
		WhileAfter(theWhile,ASTnode,context);
	}

	public Object WhileBefore(While ASTnode,java.util.Map context)
	{	return null; }

	public void WhileCondition(Object theWhile,Object expr,java.util.Map context) {}

	public void WhileBodyInstruction(Object theWhile,Object instr,java.util.Map context) {}

	public void WhileAfter(Object theWhile,While ASTnode,java.util.Map context) {}

}
