/*
 * $Header: /tmp/cvs2svn/cvsroot/BasicMTL_projects/BasicMtlAstJava/src/ASTTopDownVisitor/VarDeclarationAnalyser.java,v 1.1 2003-07-28 07:35:34 jpthibau Exp $
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
public class VarDeclarationAnalyser extends Analyser {

	public VarDeclarationAnalyser()
	{	super(VarDeclaration.class);
	}

	public void analyse(Visitable node,Visitor visitor,java.util.Map context)
	{	int i,limit;
		VarDeclaration ASTnode=(VarDeclaration) node;
		VarDeclarationAction(ASTnode,context);
	}

	public void VarDeclarationAction(VarDeclaration ASTnode,java.util.Map context) {}

}
