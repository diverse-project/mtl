/*
 * $Header: /tmp/cvs2svn/cvsroot/BasicMTL_projects/TLLTypeChecker/src/OperationCallChecker/OperationCallAnalyser.java,v 1.2 2003-08-08 15:49:26 jpthibau Exp $
 * Created on 1 août 2003
 *
 */
package OperationCallChecker;

import org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.*;

/**
 * @author jpthibau
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class OperationCallAnalyser extends TLLTopDownVisitor.OperationCallAnalyser {

//TODO relates each operationCall to an existing operation declaration
//except for operationCall on model elements which are "invoke"d

/*	public Object OperationCallBefore(OperationCall ASTnode,java.util.Map context)
	{ return null; }

	public void OperationCallArgument(Object theOperationCall,Object arg,java.util.Map context) {}
*/
	public void OperationCallCaller(Object theOperationCall,Object expr,java.util.Map context)
	{	Expression theCaller=(Expression)expr;
		if (theCaller.getClass().getName().equals("org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.VarCall"))
			{	VarCall caller=(VarCall)theCaller;
				if (caller.getModelEltVar())
					((OperationCall)theOperationCall).setIsToInvoke(true);
			}
	}

/*	public void OperationCallAfter(Object theOperationCall,OperationCall ASTnode,java.util.Map context)
	{}*/

}
