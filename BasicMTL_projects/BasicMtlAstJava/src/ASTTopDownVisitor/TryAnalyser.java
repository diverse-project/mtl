/*
 * $Header: /tmp/cvs2svn/cvsroot/BasicMTL_projects/BasicMtlAstJava/src/ASTTopDownVisitor/TryAnalyser.java,v 1.1 2003-07-28 07:35:33 jpthibau Exp $
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
public class TryAnalyser extends Analyser {

	public TryAnalyser()
	{	super(Try.class);
	}

	public void analyse(Visitable node,Visitor visitor,java.util.Map context)
	{	int i,limit;
		Try ASTnode=(Try) node;
		this.TryBefore(ASTnode,context);
		limit=ASTnode.cardTryBody();
		for (i=0;i<limit;i++) {
			((Instruction)ASTnode.getTryBody(i)).accept(visitor,context);
			this.TryBodyInstruction(context.get("Instruction"),context);
		}
		limit=ASTnode.cardCatchPart();
		for (i=0;i<limit;i++) {
			((Catch)ASTnode.getCatchPart(i)).accept(visitor,context);
			this.TryCatchPart(context.get("Catch"),context);
		}
		limit=ASTnode.cardFinalizeBody();
		for (i=0;i<limit;i++) {
			((Instruction)ASTnode.getFinalizeBody(i)).accept(visitor,context);
			this.TryFinalizeInstruction(context.get("Instruction"),context);
		}
		this.TryAfter(ASTnode,context);
	}

	public void TryBefore(Try ASTnode,java.util.Map context) {}

	public void TryBodyInstruction(Object instr,java.util.Map context) {}

	public void TryCatchPart(Object catchPart,java.util.Map context) {}

	public void TryFinalizeInstruction(Object instr,java.util.Map context) {}

	public void TryAfter(Try ASTnode,java.util.Map context) {}

}
