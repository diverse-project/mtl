/*
 * $Header: /tmp/cvs2svn/cvsroot/BasicMTL_projects/BasicMtlAntlr2TLLJava/src/TLLBuilder/OclTypeLiteralAnalyser.java,v 1.1 2003-08-06 16:18:46 jpthibau Exp $
 * Created on 30 juil. 2003
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
public class OclTypeLiteralAnalyser extends ASTTopDownVisitor.OclTypeLiteralAnalyser {

	public void OclTypeLiteralAction(org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.OclTypeLiteral ASTnode,java.util.Map context)
	{	java.util.Vector typeValue=ASTnode.getTypeValue();
		OclTypeLiteral theCreatedLiteral=new OclTypeLiteral();
		theCreatedLiteral.setTheType(new QualifiedName());
		for(int i=0;i<typeValue.size();i++)
			theCreatedLiteral.getTheType().addElement(typeValue.get(i));
		context.put("Instruction",theCreatedLiteral);
	}

}
