/*
 * $Header: /tmp/cvs2svn/cvsroot/BasicMTL_projects/BasicMtlAntlr2TLLJava/src/TLLBuilder/JavaCodeLiteralAnalyser.java,v 1.1 2003-08-28 16:48:42 jpthibau Exp $
 * Created on 25 juil. 2003
 *
 */
package TLLBuilder;

import org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.*;

/**
 * @author jpthibau
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class JavaCodeLiteralAnalyser extends ASTTopDownVisitor.JavaCodeLiteralAnalyser {

	public void JavaCodeLiteralAction(org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.JavaCodeLiteral ASTnode,java.util.Map context)
	{	JavaCodeLiteral theCreatedLiteral=new JavaCodeLiteral(ASTnode.getValue());
		theCreatedLiteral.setContainerOp((Operation)context.get("CurrentOperation"));
		context.put("Instruction",theCreatedLiteral);
	}

}
