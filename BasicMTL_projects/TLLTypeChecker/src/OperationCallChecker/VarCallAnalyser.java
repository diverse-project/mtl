/*
 * $Header: /tmp/cvs2svn/cvsroot/BasicMTL_projects/TLLTypeChecker/src/OperationCallChecker/VarCallAnalyser.java,v 1.1 2003-08-06 15:55:29 jpthibau Exp $
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
	{	VarDeclaration relatedVarDecl=ASTnode.relatedDecl;
		QualifiedName type=relatedVarDecl.getType();
		if (type.getIsModelType()) ASTnode.setModelEltVar(true);
	}
	

}
