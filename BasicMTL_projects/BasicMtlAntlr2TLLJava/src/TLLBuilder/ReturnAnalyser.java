/*
 * $Header: /tmp/cvs2svn/cvsroot/BasicMTL_projects/BasicMtlAntlr2TLLJava/src/TLLBuilder/ReturnAnalyser.java,v 1.2 2003-08-08 15:26:16 jpthibau Exp $
 * Created on 24 juil. 2003
 *
 */
package TLLBuilder;

import org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.*;

/**
 * @author jpthibau
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class ReturnAnalyser extends ASTTopDownVisitor.ReturnAnalyser {

	public Object ReturnBefore(org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.Return ASTnode,java.util.Map context)
	{	Operation theContainer=(Operation)context.get("CurrentOperation");
		int lineNumber=Integer.parseInt((String)ASTnode.getProperty("LineNumber").getValue());
		Return theCreatedReturn=new Return(lineNumber);
		theCreatedReturn.setContainerOp(theContainer);
		return theCreatedReturn;
	}

	public void ReturnArgument(Object theReturn,Object objectArg,java.util.Map context)
	{	Return theCreatedReturn=(Return)theReturn;
		Expression arg=(Expression)objectArg;
		theCreatedReturn.setReturnedExpression(arg);

	}

	public void ReturnAfter(Object theReturn,org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.Return ASTnode,java.util.Map context)
	{	Return theCreatedReturn=(Return)theReturn;
		context.put("Instruction",theCreatedReturn);
	}

}
