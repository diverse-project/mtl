/*
 * $Header: /tmp/cvs2svn/cvsroot/BasicMTL_projects/TLLTypeChecker/src/OperationCallChecker/OperationCallAnalyser.java,v 1.4 2003-08-26 13:09:59 ffondeme Exp $
 * Created on 1 ao�t 2003
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
/*	public void OperationCallCaller(Object theOperationCall,OperationCall node,Object expr, java.util.Map context)
	{}*/

	public void OperationCallAfter(Object theOperationCall,OperationCall ASTnode,java.util.Map context)
	{	Expression theCaller=(Expression)ASTnode.getCaller();
		if (theCaller instanceof org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.VarCall)
			{	VarCall caller=(VarCall)theCaller;
				if (caller.getModelEltVar())
					ASTnode.setIsToInvoke(true);
			}
		if ((theCaller instanceof OperationCall) && ((OperationCall)theCaller).getIsToInvoke()) {
			ASTnode.setIsToInvoke(true);
		}
		if (ASTnode.getOclAsType() != null && ASTnode.getOclAsType().getIsRepositoryModel()) {
			ASTnode.setIsToInvoke(true);
		}
		if (theCaller != null && theCaller.getToBeCasted() != null && theCaller.getToBeCasted().getIsRepositoryModel()) {
			ASTnode.setIsToInvoke(true);
		}
	}

}
