/*
 * $Header: /tmp/cvs2svn/cvsroot/BasicMTL_projects/BasicMtlAntlr2TLLJava/src/TLLBuilder/AssociateAnalyser.java,v 1.4 2003-08-27 13:40:42 jpthibau Exp $
 * Created on 25 juil. 2003
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
public class AssociateAnalyser extends ASTTopDownVisitor.AssociateAnalyser {

	public Object AssociateBefore(org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.Associate ASTnode,java.util.Map context)
	{	int lineNumber=Integer.parseInt((String)ASTnode.getProperty("LineNumber").getValue());
		Associate theCreatedAssociate=new Associate(lineNumber,ASTnode.getIsAssociate());
		return theCreatedAssociate;
	}

	public void AssociateRole(Object theAssociate,Object objectRole,java.util.Map context)
	{	Associate theCreatedAssociate=(Associate)theAssociate;
		Role role=(Role)objectRole;
		theCreatedAssociate.appendRoles(role);
	}

	public void AssociateAfter(Object theAssociate,org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.Associate ASTnode,java.util.Map context)
	{	Associate theCreatedAssociate=(Associate)theAssociate;
		theCreatedAssociate.setContainerOp((Operation)context.get("CurrentOperation"));
		context.put("Associate",theCreatedAssociate);
		context.put("Instruction",theCreatedAssociate);
	}

}
