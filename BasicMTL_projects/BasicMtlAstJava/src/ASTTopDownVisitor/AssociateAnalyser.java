/*
 * $Header: /tmp/cvs2svn/cvsroot/BasicMTL_projects/BasicMtlAstJava/src/ASTTopDownVisitor/AssociateAnalyser.java,v 1.2 2003-08-06 16:27:31 jpthibau Exp $
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
public class AssociateAnalyser extends Analyser {

	public AssociateAnalyser()
	{	super(Associate.class);
	}

	public void analyse(Visitable node,Visitor visitor,java.util.Map context)
	{	int i,limit;
		Associate ASTnode=(Associate) node;
		Object theAssociate=this.AssociateBefore(ASTnode,context);
		limit=ASTnode.cardRoles();
		for (i=0;i<limit;i++) {
			((Role)ASTnode.getRoles(i)).accept(visitor,context);
			this.AssociateRole(theAssociate,context.get("Role"),context);
		}
		this.AssociateAfter(theAssociate,(Associate) node,context);
	}

	public Object AssociateBefore(Associate ASTnode,java.util.Map context)
	{	return null; }

	public void AssociateRole(Object theAssociate,Object role,java.util.Map context) {}

	public void AssociateAfter(Object theAssociate,Associate ASTnode,java.util.Map context) {}

}
