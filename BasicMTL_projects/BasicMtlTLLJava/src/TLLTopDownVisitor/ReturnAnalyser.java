/*
 * $Header: /tmp/cvs2svn/cvsroot/BasicMTL_projects/BasicMtlTLLJava/src/TLLTopDownVisitor/ReturnAnalyser.java,v 1.2 2003-08-26 13:02:47 ffondeme Exp $
 * Created on 24 juil. 2003
 *
 */
package TLLTopDownVisitor;

import org.irisa.triskell.MT.visitors.Java.AnalysingVisitor.*;
import org.irisa.triskell.MT.visitors.Java.GenericVisitor.*;
import org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.*;

/**
 * @author jpthibau
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class ReturnAnalyser extends Analyser {

	public ReturnAnalyser()
	{	super(Return.class);
	}

	public void analyse(Visitable node,Visitor visitor,java.util.Map context)
	{	Return ASTnode=(Return) node;
		Object theReturn=this.ReturnBefore(ASTnode,context);
		Expression returned = (Expression)ASTnode.getReturnedExpression();
		if (returned != null) {
			returned.accept(visitor,context);
			this.ReturnArgument(theReturn,context.get("Instruction"),context);
		}
		this.ReturnAfter(theReturn,ASTnode,context);
	}

	public Object ReturnBefore(Return ASTnode,java.util.Map context)
	{	return null; }

	public void ReturnArgument(Object theReturn,Object arg,java.util.Map context) {}

	public void ReturnAfter(Object theReturn,Return ASTnode,java.util.Map context) {}

}
