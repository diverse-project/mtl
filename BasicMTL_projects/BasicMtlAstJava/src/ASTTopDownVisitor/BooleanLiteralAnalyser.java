/*
 * $Header: /tmp/cvs2svn/cvsroot/BasicMTL_projects/BasicMtlAstJava/src/ASTTopDownVisitor/BooleanLiteralAnalyser.java,v 1.1 2003-07-28 07:35:33 jpthibau Exp $
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
public class BooleanLiteralAnalyser extends Analyser {

	public BooleanLiteralAnalyser()
	{	super(BooleanLiteral.class);
	}

	public void analyse(Visitable node,Visitor visitor,java.util.Map context)
	{	int i,limit;
		BooleanLiteral ASTnode=(BooleanLiteral) node;
		BooleanLiteralAction(ASTnode,context);
	}

	public void BooleanLiteralAction(BooleanLiteral ASTnode,java.util.Map context) {}

}
