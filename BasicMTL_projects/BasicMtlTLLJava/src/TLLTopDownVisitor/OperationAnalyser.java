/*
 * $Header: /tmp/cvs2svn/cvsroot/BasicMTL_projects/BasicMtlTLLJava/src/TLLTopDownVisitor/OperationAnalyser.java,v 1.2 2003-08-08 15:46:47 jpthibau Exp $
 * Created on 17 juil. 2003
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
public class OperationAnalyser extends Analyser {

	public OperationAnalyser()
	{	super(Operation.class);
	}

	public void analyse(Visitable node,Visitor visitor,java.util.Map context)
	{	int i,limit;
		Operation ASTnode=(Operation) node;
		Object theOperation=this.OperationBefore(ASTnode,context);
		limit=ASTnode.cardParameters();
		for (i=0;i<limit;i++) {
			((VarDeclaration)ASTnode.getParameters(i)).accept(visitor,context);
			this.OperationParameter(theOperation,context.get("VarDeclaration"),context);
			if (i<limit-1) this.OperationParamSeparator(context);
			else this.OperationEndParameters(context);
		}
		limit=ASTnode.cardDeclaredVariables();
		for (i=0;i<limit;i++) {
			((VarDeclaration)ASTnode.getDeclaredVariables(i)).accept(visitor,context);
			this.OperationVarDeclaration(theOperation,context.get("VarDeclaration"),context);
		}
		limit=ASTnode.cardInstructions();
		for (i=0;i<limit;i++) {
			((Instruction)ASTnode.getInstructions(i)).accept(visitor,context);
			this.OperationInstruction(theOperation,context.get("Instruction"),context);
		}
		this.OperationAfter(theOperation,(Operation) node,context);
	}

	public Object OperationBefore(Operation ASTnode,java.util.Map context)
	{	return null; }

	public void OperationParameter(Object theOperation,Object varDecl,java.util.Map context) {}

	public void OperationParamSeparator(java.util.Map context) {}
	
	public void OperationEndParameters(java.util.Map context) {}
				
	public void OperationVarDeclaration(Object theOperation,Object varDecl,java.util.Map context) {}

	public void OperationInstruction(Object theOperation,Object instr,java.util.Map context) {}

	public void OperationAfter(Object theOperation,Operation ASTnode,java.util.Map context) {}


}
