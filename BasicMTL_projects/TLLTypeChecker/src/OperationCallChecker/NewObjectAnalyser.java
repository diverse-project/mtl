/*
 * $Header: /tmp/cvs2svn/cvsroot/BasicMTL_projects/TLLTypeChecker/src/OperationCallChecker/NewObjectAnalyser.java,v 1.1 2003-08-06 15:55:29 jpthibau Exp $
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
public class NewObjectAnalyser extends TLLTopDownVisitor.NewObjectAnalyser {

	public void NewObjectAfter(Object theNewObject,NewObject ASTnode,java.util.Map context)
	{ QualifiedName type=ASTnode.getTypeToCreate();
	  if (type.getIsModelType()) ASTnode.setModelEltCreation(true);
	}

}
