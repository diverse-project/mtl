/*
 * $Header: /tmp/cvs2svn/cvsroot/BasicMTL_projects/BasicMtlAntlr2TLLJava/src/TLLBuilder/RealLiteralAnalyser.java,v 1.1 2003-08-06 16:18:45 jpthibau Exp $
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
public class RealLiteralAnalyser extends ASTTopDownVisitor.RealLiteralAnalyser {

	public void RealLiteralAction(org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.RealLiteral ASTnode,java.util.Map context)
	{	RealLiteral theCreatedLiteral=new RealLiteral(ASTnode.getValue());
		context.put("Instruction",theCreatedLiteral);
	}

}
