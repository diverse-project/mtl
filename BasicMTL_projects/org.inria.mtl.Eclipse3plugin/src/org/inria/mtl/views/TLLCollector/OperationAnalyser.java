/*
 * $Header: /tmp/cvs2svn/cvsroot/BasicMTL_projects/org.inria.mtl.Eclipse3plugin/src/org/inria/mtl/views/TLLCollector/OperationAnalyser.java,v 1.1 2004-08-26 12:40:35 sdzale Exp $
 * Created on 4 août 2003
 *
 */
package org.inria.mtl.views.TLLCollector;

import java.util.Vector;

import org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.Operation;

/**
 * @author jpthibau
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class OperationAnalyser extends TLLTopDownVisitor.OperationAnalyser {

	public void OperationAction(Operation ASTnode,java.util.Map context)
	{	
		Vector result = (Vector)context.get("CollectedElts");
	    tllObject obj=new tllObject(ASTnode.getName(),null,3,"","");
	    result.add(obj);
	}

}
