/*
 * $Header: /tmp/cvs2svn/cvsroot/BasicMTL_projects/BasicMtlAntlr2TLLJava/src/TLLBuilder/OperationCallAnalyser.java,v 1.3 2003-08-19 13:32:51 ffondeme Exp $
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
public class OperationCallAnalyser extends ASTTopDownVisitor.OperationCallAnalyser {

	public Object OperationCallBefore(org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.OperationCall ASTnode,java.util.Map context)
	{	//int lineNumber=Integer.parseInt((String)ASTnode.getProperty("LineNumber").getValue());
		//TODO memorize the line number for each operation call
		int lineNumber=-1000;
		OperationCall theCreatedOpCall=new OperationCall(ASTnode.getName(),lineNumber);
		if (ASTnode.getProperty("ClassOfGetAttribute") != null)
			theCreatedOpCall.setKind(OperationKind.getAttributeCall());
		return theCreatedOpCall;
	}

	public void OperationCallArgument(Object theOpCall,Object objectArg,java.util.Map context)
	{	OperationCall theCreatedOpCall=(OperationCall)theOpCall;
		Expression arg=(Expression)objectArg;
		theCreatedOpCall.appendArguments(arg);
	}

	public void OperationCallCaller(Object theOpCall,Object objectExpr,java.util.Map context)
	{	OperationCall theCreatedOpCall=(OperationCall)theOpCall;
		Expression expr=(Expression)objectExpr;
		theCreatedOpCall.setCaller(expr);
	}

	public void OperationCallAfter(Object theOpCall,org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.OperationCall ASTnode,java.util.Map context)
	{	OperationCall theCreatedOpCall=(OperationCall)theOpCall;
		theCreatedOpCall.setContainerOp((Operation)context.get("CurrentOperation"));
		context.put("Instruction",theCreatedOpCall);
	}

}
