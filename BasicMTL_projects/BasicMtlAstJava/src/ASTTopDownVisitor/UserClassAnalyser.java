/*
 * $Header: /tmp/cvs2svn/cvsroot/BasicMTL_projects/BasicMtlAstJava/src/ASTTopDownVisitor/UserClassAnalyser.java,v 1.1 2003-07-28 07:35:35 jpthibau Exp $
 * Created on 17 juil. 2003
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
public class UserClassAnalyser extends Analyser {

	public UserClassAnalyser()
	{	super(UserClass.class);
	}

	public void analyse(Visitable node,Visitor visitor,java.util.Map context)
	{	int i,limit;
		UserClass ASTnode=(UserClass) node;
		this.UserClassBefore(ASTnode,context);
		limit=ASTnode.cardDefinedAttributes();
		for (i=0;i<limit;i++) {
			((Attribute)ASTnode.getDefinedAttributes(i)).accept(visitor,context);
			this.UserClassAttribute(context.get("Attribute"),context);
		}
		limit=ASTnode.cardDefinedMethods();
		for (i=0;i<limit;i++) {
			((Operation)ASTnode.getDefinedMethods(i)).accept(visitor,context);
			this.UserClassMethod(context.get("Operation"),context);
		}
		this.UserClassAfter(ASTnode,context);
	}

	public void UserClassBefore(UserClass ASTnode,java.util.Map context) {}

	public void UserClassAttribute(Object attrib,java.util.Map context) {}

	public void UserClassMethod(Object op,java.util.Map context) {}

	public void UserClassAfter(UserClass ASTnode,java.util.Map context) {}


}
