/*
 * $Header: /tmp/cvs2svn/cvsroot/BasicMTL_projects/BasicMtlAstJava/src/ASTTopDownVisitor/IntLiteralAnalyser.java,v 1.1 2003-07-28 07:35:34 jpthibau Exp $
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
public class IntLiteralAnalyser extends Analyser {

	public IntLiteralAnalyser()
	{	super(IntLiteral.class);
	}

	public void analyse(Visitable node,Visitor visitor,java.util.Map context)
	{	int i,limit;
		IntLiteral ASTnode=(IntLiteral) node;
		IntLiteralAction(ASTnode,context);
	}

	public void IntLiteralAction(IntLiteral ASTnode,java.util.Map context) {}

}
