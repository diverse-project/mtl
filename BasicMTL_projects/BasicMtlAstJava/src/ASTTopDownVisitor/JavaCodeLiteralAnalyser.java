/*
 * $Header: /tmp/cvs2svn/cvsroot/BasicMTL_projects/BasicMtlAstJava/src/ASTTopDownVisitor/JavaCodeLiteralAnalyser.java,v 1.1 2003-08-28 16:41:01 jpthibau Exp $
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
public class JavaCodeLiteralAnalyser extends Analyser {

	public JavaCodeLiteralAnalyser()
	{	super(JavaCodeLiteral.class);
	}

	public void analyse(Visitable node,Visitor visitor,java.util.Map context)
	{	int i,limit;
		JavaCodeLiteral ASTnode=(JavaCodeLiteral) node;
		JavaCodeLiteralAction(ASTnode,context);
	}

	public void JavaCodeLiteralAction(JavaCodeLiteral ASTnode,java.util.Map context) {}

}
