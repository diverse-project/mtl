/*
 * $Header: /tmp/cvs2svn/cvsroot/BasicMTL_projects/BasicMtlAstJava/src/ASTTopDownVisitor/CatchAnalyser.java,v 1.1 2003-07-28 07:35:34 jpthibau Exp $
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
public class CatchAnalyser extends Analyser {

	public CatchAnalyser()
	{	super(Catch.class);
	}

	public void analyse(Visitable node,Visitor visitor,java.util.Map context)
	{	int i,limit;
		Catch ASTnode=(Catch) node;
		this.CatchBefore(ASTnode,context);
		((VarDeclaration)ASTnode.getCatchedException()).accept(visitor,context);
		this.CatchVarDeclaration(context.get("VarDeclaration"),context);
		limit=ASTnode.cardCatchBody();
		for (i=0;i<limit;i++) {
			((Instruction)ASTnode.getCatchBody(i)).accept(visitor,context);
			this.CatchInstruction(context.get("Instruction"),context);
		}
		this.CatchAfter(ASTnode,context);
	}

	public void CatchBefore(Catch ASTnode,java.util.Map context) {}

	public void CatchVarDeclaration(Object varDecl,java.util.Map context) {}

	public void CatchInstruction(Object instr,java.util.Map context) {}

	public void CatchAfter(Catch ASTnode,java.util.Map context) {}

}
