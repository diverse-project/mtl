/*
 * $Header: /tmp/cvs2svn/cvsroot/BasicMTL_projects/TLLTypeChecker/src/OperationCallChecker/VarCallAnalyser.java,v 1.2 2003-08-14 21:00:20 ffondeme Exp $
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
public class VarCallAnalyser extends TLLTopDownVisitor.VarCallAnalyser {

	public void VarCallAction(VarCall ASTnode,java.util.Map context)
	{	VarDeclaration relatedVarDecl=CommonFunctions.getVarDeclaration(ASTnode.getContainerOp(), ASTnode.getVarName(), ASTnode.getLineNumber());
		ASTnode.setRelatedDecl(relatedVarDecl);
		QualifiedName type=relatedVarDecl.getType();
		if (type.getIsModelType()) ASTnode.setModelEltVar(true);
	}
	

}
