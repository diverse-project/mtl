/*
 * $Header: /tmp/cvs2svn/cvsroot/BasicMTL_projects/BasicMtlAntlr2TLLJava/src/TLLBuilder/IntLiteralAnalyser.java,v 1.1 2003-08-06 16:18:47 jpthibau Exp $
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
public class IntLiteralAnalyser extends ASTTopDownVisitor.IntLiteralAnalyser {

	public void IntLiteralAction(org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.IntLiteral ASTnode,java.util.Map context)
	{	IntLiteral theCreatedLiteral=new IntLiteral(ASTnode.getValue());
		context.put("Instruction",theCreatedLiteral);
	}

}
