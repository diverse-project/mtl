/*
 * $Header: /tmp/cvs2svn/cvsroot/BasicMTL_projects/BasicMtlAstJava/src/ASTTopDownVisitor/OclAsTypeAnalyser.java,v 1.1 2003-08-21 20:03:52 ffondeme Exp $
 * Created on 24 juil. 2003
 *
 */
package ASTTopDownVisitor;

import java.util.Vector;

import org.irisa.triskell.MT.visitors.Java.AnalysingVisitor.*;
import org.irisa.triskell.MT.visitors.Java.GenericVisitor.*;
import org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.*;

/**
 * @author jpthibau
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class OclAsTypeAnalyser extends Analyser {

	public OclAsTypeAnalyser()
	{	super(OclAsType.class);
	}

	public void analyse(Visitable node,Visitor visitor,java.util.Map context)
	{	int i,limit;
		OclAsType ASTnode=(OclAsType) node;
		this.OclAsTypeBefore(ASTnode,context);
		if (ASTnode.getExpression() != null) {
			ASTnode.getExpression().accept(visitor,context);
		} else
			context.remove("Instruction");
		Object expression = context.get("Instruction");
		this.OclAsTypeExpression(expression,context);
		limit = ASTnode.cardType();
		Vector type = new Vector(limit);
		for (i = 0; i < limit; ++i)
			type.add(ASTnode.getType(i));
		this.OclAsTypeType(expression, type, context);
		this.OclAsTypeAfter(expression,context);
	}

	public void OclAsTypeBefore(OclAsType ASTnode,java.util.Map context) {}

	public void OclAsTypeExpression(Object arg,java.util.Map context) {}

	public void OclAsTypeType(Object expr, Vector type,java.util.Map context) {}

	public void OclAsTypeAfter(Object expression,java.util.Map context) {}

}
