/*
 * $Header: /tmp/cvs2svn/cvsroot/BasicMTL_projects/BasicMtlAstJava/src/ASTTopDownVisitor/OperationCallAnalyser.java,v 1.1 2003-07-28 07:35:34 jpthibau Exp $
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
public class OperationCallAnalyser extends Analyser {

	public OperationCallAnalyser()
	{	super(OperationCall.class);
	}

	public void analyse(Visitable node,Visitor visitor,java.util.Map context)
	{	int i,limit;
		OperationCall ASTnode=(OperationCall) node;
		this.OperationCallBefore(ASTnode,context);
		limit=ASTnode.cardArguments();
		for (i=0;i<limit;i++) {
			((Expression)ASTnode.getArguments(i)).accept(visitor,context);
			this.OperationCallArgument(context.get("Instruction"),context);
		}
		if (ASTnode.getCaller() != null) {
			ASTnode.getCaller().accept(visitor,context);
			this.OperationCallCaller(context.get("Instruction"),context);
		}
		else this.OperationCallCaller(null,context);
		
		this.OperationCallAfter((OperationCall) node,context);
	}

	public void OperationCallBefore(OperationCall ASTnode,java.util.Map context) {}

	public void OperationCallArgument(Object arg,java.util.Map context) {}

	public void OperationCallCaller(Object expr,java.util.Map context) {}

	public void OperationCallAfter(OperationCall ASTnode,java.util.Map context) {}

}
