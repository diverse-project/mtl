/*
 * $Header: /tmp/cvs2svn/cvsroot/BasicMTL_projects/BasicMtlAstJava/src/ASTTopDownVisitor/OperationAnalyser.java,v 1.1 2003-07-28 07:35:33 jpthibau Exp $
 * Created on 17 juil. 2003
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
public class OperationAnalyser extends Analyser {

	public OperationAnalyser()
	{	super(Operation.class);
	}

	public void analyse(Visitable node,Visitor visitor,java.util.Map context)
	{	int i,limit;
		Operation ASTnode=(Operation) node;
		this.OperationBefore(ASTnode,context);
		limit=ASTnode.cardParameters();
		for (i=0;i<limit;i++) {
			((VarDeclaration)ASTnode.getParameters(i)).accept(visitor,context);
			this.OperationParameter(context.get("VarDeclaration"),context);
		}
		limit=ASTnode.cardDeclaredVariables();
		for (i=0;i<limit;i++) {
			((VarDeclaration)ASTnode.getDeclaredVariables(i)).accept(visitor,context);
			this.OperationVarDeclaration(context.get("VarDeclaration"),context);
		}
		limit=ASTnode.cardInstructions();
		for (i=0;i<limit;i++) {
			((Instruction)ASTnode.getInstructions(i)).accept(visitor,context);
			this.OperationInstruction(context.get("Instruction"),context);
		}
		this.OperationAfter((Operation) node,context);
	}

	public void OperationBefore(Operation ASTnode,java.util.Map context) {}

	public void OperationParameter(Object varDecl,java.util.Map context) {}

	public void OperationVarDeclaration(Object varDecl,java.util.Map context) {}

	public void OperationInstruction(Object instr,java.util.Map context) {}

	public void OperationAfter(Operation ASTnode,java.util.Map context) {}


}
