/*
 * $Header: /tmp/cvs2svn/cvsroot/BasicMTL_projects/BasicMtlAntlr2TLLJava/src/TLLBuilder/BooleanLiteralAnalyser.java,v 1.1 2003-08-06 16:18:46 jpthibau Exp $
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
public class BooleanLiteralAnalyser extends ASTTopDownVisitor.BooleanLiteralAnalyser {

	public void BooleanLiteralAction(org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.BooleanLiteral ASTnode,java.util.Map context)
	{	BooleanLiteral theCreatedLiteral=new BooleanLiteral(ASTnode.getValue());
		context.put("Instruction",theCreatedLiteral);
	}

}
