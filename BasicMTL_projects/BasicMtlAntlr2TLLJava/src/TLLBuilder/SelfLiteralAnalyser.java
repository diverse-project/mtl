/*
 * $Header: /tmp/cvs2svn/cvsroot/BasicMTL_projects/BasicMtlAntlr2TLLJava/src/TLLBuilder/SelfLiteralAnalyser.java,v 1.1 2003-08-06 16:18:45 jpthibau Exp $
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
public class SelfLiteralAnalyser extends ASTTopDownVisitor.SelfLiteralAnalyser {

	public void SelfLiteralAction(org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.SelfLiteral ASTnode,java.util.Map context)
	{	SelfLiteral theCreatedLiteral=new SelfLiteral();
		context.put("Instruction",theCreatedLiteral);
	}

}
