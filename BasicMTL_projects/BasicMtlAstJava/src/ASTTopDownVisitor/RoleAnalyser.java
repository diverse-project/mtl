/*
 * $Header: /tmp/cvs2svn/cvsroot/BasicMTL_projects/BasicMtlAstJava/src/ASTTopDownVisitor/RoleAnalyser.java,v 1.1 2003-07-28 07:35:33 jpthibau Exp $
 * Created on 24 juil. 2003
 *
 */
package ASTTopDownVisitor;

import org.irisa.triskell.MT.visitors.Java.AnalysingVisitor.*;
import org.irisa.triskell.MT.visitors.Java.GenericVisitor.*;
import org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.*;

/**
 * @author jpthibau
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class RoleAnalyser extends Analyser {

	public RoleAnalyser()
	{	super(Role.class);
	}

	public void analyse(Visitable node,Visitor visitor,java.util.Map context)
	{	Role ASTnode=(Role) node;
		this.RoleBefore(ASTnode,context);
		((Expression)ASTnode.getExpression()).accept(visitor,context);
		this.RoleExpression(context.get("Instruction"),context);
		this.RoleAfter(ASTnode,context);
	}

	public void RoleBefore(Role ASTnode,java.util.Map context) {}

	public void RoleExpression(Object expr,java.util.Map context) {}

	public void RoleAfter(Role ASTnode,java.util.Map context) {}

}
