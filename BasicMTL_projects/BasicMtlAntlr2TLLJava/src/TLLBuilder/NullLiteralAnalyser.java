/*
 * $Header: /tmp/cvs2svn/cvsroot/BasicMTL_projects/BasicMtlAntlr2TLLJava/src/TLLBuilder/NullLiteralAnalyser.java,v 1.2 2003-08-14 20:47:47 ffondeme Exp $
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
public class NullLiteralAnalyser extends ASTTopDownVisitor.NullLiteralAnalyser {

	public void NullLiteralAction(org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.NullLiteral ASTnode,java.util.Map context)
	{	NullLiteral theCreatedLiteral=new NullLiteral();
		theCreatedLiteral.setContainerOp((Operation)context.get("CurrentOperation"));
		context.put("Instruction",theCreatedLiteral);
	}

}
