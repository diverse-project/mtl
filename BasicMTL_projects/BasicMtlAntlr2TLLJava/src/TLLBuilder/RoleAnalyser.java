/*
 * $Header: /tmp/cvs2svn/cvsroot/BasicMTL_projects/BasicMtlAntlr2TLLJava/src/TLLBuilder/RoleAnalyser.java,v 1.2 2003-08-26 12:53:52 ffondeme Exp $
 * Created on 25 juil. 2003
 *
 */
package TLLBuilder;

import java.util.Map;

import org.irisa.triskell.MT.visitors.Java.AnalysingVisitor.*;
import org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.*;

/**
 * @author jpthibau
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class RoleAnalyser extends ASTTopDownVisitor.RoleAnalyser {
	
	public Object RoleBefore(org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.Role ASTnode,java.util.Map context)
	{	Role theCreatedRole=new Role(ASTnode.getRoleName());
		BasicMtlLibrary theCreatedLib=(BasicMtlLibrary)context.get("TheCreatedLibrary");
		Property roleType=(Property)ASTnode.getProperty("Type");
		QualifiedName type=CommonFunctions.findOrAddType((java.util.Vector)roleType.getValue(),theCreatedLib);
		theCreatedRole.setLinkedEltType(type);
		return theCreatedRole;
	}

	public void RoleExpression(Object theRole,Object objectExpr,java.util.Map context)
	{	Role theCreatedRole=(Role)theRole;
		Expression expr=(Expression)objectExpr;
		theCreatedRole.setExpression(expr);
	}

	public void RoleAfter(Object theRole, org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.Role ASTnode, Map context) {
		Role theCreatedRole=(Role)theRole;
		context.put("Role",theCreatedRole);
	}

}
