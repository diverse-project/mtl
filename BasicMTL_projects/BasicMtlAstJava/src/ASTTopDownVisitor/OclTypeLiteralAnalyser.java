/*
 * $Header: /tmp/cvs2svn/cvsroot/BasicMTL_projects/BasicMtlAstJava/src/ASTTopDownVisitor/OclTypeLiteralAnalyser.java,v 1.1 2003-08-06 16:27:31 jpthibau Exp $
 * Created on 30 juil. 2003
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
public class OclTypeLiteralAnalyser extends Analyser {

	public OclTypeLiteralAnalyser()
	{	super(OclTypeLiteral.class);
	}

	public void analyse(Visitable node,Visitor visitor,java.util.Map context)
	{	int i,limit;
		OclTypeLiteral ASTnode=(OclTypeLiteral) node;
		OclTypeLiteralAction(ASTnode,context);
	}

	public void OclTypeLiteralAction(OclTypeLiteral ASTnode,java.util.Map context) {}

}
