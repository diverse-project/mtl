/*
 * $Header: /tmp/cvs2svn/cvsroot/BasicMTL_projects/TLLTypeChecker/src/OperationCallChecker/OperationCallAnalyser.java,v 1.6 2004-03-19 17:45:32 edrezen Exp $
 * Created on 1 ao�t 2003
 *
 */
package OperationCallChecker;

import org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.*;
import org.irisa.triskell.MT.visitors.Java.AnalysingVisitor.Property;

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

		if (theCaller instanceof OperationCall) 
		{
			OperationCall opCall = (OperationCall)theCaller;

			if (opCall.getIsToInvoke())
			{			
				ASTnode.setIsToInvoke(true);
			}
			else if (opCall.getKind().equals(OperationKind.getAttributeCall()))
			{
				// In the case of an attribute call, we try to retrieve the Attribute object 
				// from the OperationCall object. This property has been set during the build
				// of the AST (VarCallAnalyser) where the full information about the attribute
				// were available
				Property prop = opCall.getProperty ("Attribute");
				if (prop!=null)
				{
					Attribute attribute = (Attribute)prop.getValue();
				
					// In the special case where the attribute is of a type coming from the
					// repository, the operation call must be an invoke.
					if (attribute.getFeatureType().getIsRepositoryModel())
					{
						ASTnode.setIsToInvoke(true);
					}
				}
			}
		}

		if (ASTnode.getOclAsType() != null && ASTnode.getOclAsType().getIsRepositoryModel()) {
			ASTnode.setIsToInvoke(true);
		}

		if (theCaller != null && theCaller.getToBeCasted() != null && theCaller.getToBeCasted().getIsRepositoryModel()) {
			ASTnode.setIsToInvoke(true);
		}

		if (theCaller != null && (theCaller instanceof OclTypeLiteral) && ((OclTypeLiteral)theCaller).getTheType().getIsRepositoryModel()) {
			ASTnode.setIsToInvoke(true);
		}
	}

}
