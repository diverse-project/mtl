/*
 * $Header: /tmp/cvs2svn/cvsroot/BasicMTL_projects/BasicMtlAstJava/src/ASTTopDownVisitor/ThrowsAnalyser.java,v 1.2 2003-08-06 16:27:30 jpthibau Exp $
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
public class ThrowsAnalyser extends Analyser {

	public ThrowsAnalyser()
	{	super(Throws.class);
	}

	public void analyse(Visitable node,Visitor visitor,java.util.Map context)
	{	Throws ASTnode=(Throws) node;
		Object theThrows=this.ThrowsBefore(ASTnode,context);
		((Expression)ASTnode.getThrownExpression()).accept(visitor,context);
		this.ThrowsExpression(theThrows,context.get("Instruction"),context);
		this.ThrowsAfter(theThrows,ASTnode,context);
	}

	public Object ThrowsBefore(Throws ASTnode,java.util.Map context)
	{	return null; }

	public void ThrowsExpression(Object theThrows,Object expr,java.util.Map context) {}

	public void ThrowsAfter(Object theThrows,Throws ASTnode,java.util.Map context) {}

}
