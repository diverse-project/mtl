/*
 * $Header: /tmp/cvs2svn/cvsroot/BasicMTL_projects/BasicMtlAntlr2TLLJava/src/TLLBuilder/OclTypeLiteralAnalyser.java,v 1.3 2003-08-21 20:03:07 ffondeme Exp $
 * Created on 30 juil. 2003
 *
 */
package TLLBuilder;

import java.util.Vector;

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
		Vector type = new Vector();
		for(int i=0;i<typeValue.size();i++)
			type.addElement(typeValue.get(i));
		BasicMtlLibrary theCreatedLib=(BasicMtlLibrary)context.get("TheCreatedLibrary");
		theCreatedLiteral.setTheType(CommonFunctions.findOrAddType(type, theCreatedLib));
		theCreatedLiteral.setContainerOp((Operation)context.get("CurrentOperation"));
		context.put("Instruction",theCreatedLiteral);
	}

}
