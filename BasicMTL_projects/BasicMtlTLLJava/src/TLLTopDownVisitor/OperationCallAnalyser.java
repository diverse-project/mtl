/*
 * $Header: /tmp/cvs2svn/cvsroot/BasicMTL_projects/BasicMtlTLLJava/src/TLLTopDownVisitor/OperationCallAnalyser.java,v 1.4 2003-08-21 20:16:55 ffondeme Exp $
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
public class OperationCallAnalyser extends Analyser {

	public OperationCallAnalyser()
	{	super(OperationCall.class);
	}

	public void analyse(Visitable node,Visitor visitor,java.util.Map context)
	{	int i,limit;
		OperationCall ASTnode=(OperationCall) node;
		Object theOperationCall=this.OperationCallBefore(ASTnode,context);
		if (ASTnode.getCaller() != null) {
			ASTnode.getCaller().accept(visitor,context);
			//this.OperationCallCaller(theOperationCall,context.get("Instruction"),context);
			//@TODO this is a Frd hack... Plese, check its correctness !
			this.OperationCallCaller(theOperationCall, ASTnode,ASTnode.getCaller(), context);
		}
		else this.OperationCallCaller(theOperationCall, ASTnode,null, context);
		limit=ASTnode.cardArguments();
		for (i=0;i<limit;i++) {
			((Expression)ASTnode.getArguments(i)).accept(visitor,context);
			this.OperationCallArgument(theOperationCall,context.get("Instruction"),context);
			if (i < limit-1) this.OperationCallArgSeparator(context);
		}		
		this.OperationCallAfter(theOperationCall,(OperationCall) node,context);
	}

	public Object OperationCallBefore(OperationCall ASTnode,java.util.Map context)
	{ return null; }

	public void OperationCallArgument(Object theOperationCall,Object arg,java.util.Map context) {}

	public void OperationCallArgSeparator(java.util.Map context) {}

	public void OperationCallCaller(Object theOperationCall,OperationCall node,Object expr, java.util.Map context) {}

	public void OperationCallAfter(Object theOperationCall,OperationCall ASTnode,java.util.Map context) {}

}
