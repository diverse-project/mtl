/*
 * $Header: /tmp/cvs2svn/cvsroot/BasicMTL_projects/BasicMtlTLLJava/src/TLLTopDownVisitor/TryAnalyser.java,v 1.2 2003-08-08 15:46:47 jpthibau Exp $
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
public class TryAnalyser extends Analyser {

	public TryAnalyser()
	{	super(Try.class);
	}

	public void analyse(Visitable node,Visitor visitor,java.util.Map context)
	{	int i,limit;
		Try ASTnode=(Try) node;
		Object theTry=this.TryBefore(ASTnode,context);
		limit=ASTnode.cardTryBody();
		for (i=0;i<limit;i++) {
			((Instruction)ASTnode.getTryBody(i)).accept(visitor,context);
			this.TryBodyInstruction(theTry,context.get("Instruction"),context);
		}
		this.TryEndTryBody(context);
		limit=ASTnode.cardCatchPart();
		for (i=0;i<limit;i++) {
			((Catch)ASTnode.getCatchPart(i)).accept(visitor,context);
			this.TryCatchPart(theTry,context.get("Catch"),context);
		}
		limit=ASTnode.cardFinalizeBody();
		if (limit > 0) this.TryFinallyBody(context);
		for (i=0;i<limit;i++) {
			((Instruction)ASTnode.getFinalizeBody(i)).accept(visitor,context);
			this.TryFinalizeInstruction(theTry,context.get("Instruction"),context);
		}
		if (limit > 0) this.TryFinallyEndBody(context);
		this.TryAfter(theTry,ASTnode,context);
	}

	public Object TryBefore(Try ASTnode,java.util.Map context)
	{	return null;}

	public void TryBodyInstruction(Object theTry,Object instr,java.util.Map context) {}

	public void TryEndTryBody(java.util.Map context) {}

	public void TryCatchPart(Object theTry,Object catchPart,java.util.Map context) {}

	public void TryFinallyBody(java.util.Map context) {}

	public void TryFinallyEndBody(java.util.Map context) {}

	public void TryFinalizeInstruction(Object theTry,Object instr,java.util.Map context) {}

	public void TryAfter(Object theTry,Try ASTnode,java.util.Map context) {}

}
