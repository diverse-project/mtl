/*
 * $Header: /tmp/cvs2svn/cvsroot/BasicMTL_projects/BasicMtlAntlr2TLLJava/src/TLLBuilder/ReturnAnalyser.java,v 1.1 2003-08-06 16:18:45 jpthibau Exp $
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
	{	int lineNumber=Integer.parseInt((String)ASTnode.getProperty("LineNumber").getValue());
		Return theCreatedReturn=new Return(lineNumber);
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
