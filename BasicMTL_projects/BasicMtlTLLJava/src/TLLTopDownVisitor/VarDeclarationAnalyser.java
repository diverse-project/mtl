/*
 * $Header: /tmp/cvs2svn/cvsroot/BasicMTL_projects/BasicMtlTLLJava/src/TLLTopDownVisitor/VarDeclarationAnalyser.java,v 1.1 2003-08-06 16:13:26 jpthibau Exp $
 * Created on 17 juil. 2003
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
