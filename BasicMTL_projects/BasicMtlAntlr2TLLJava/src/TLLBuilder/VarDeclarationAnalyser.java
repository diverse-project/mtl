/*
 * $Header: /tmp/cvs2svn/cvsroot/BasicMTL_projects/BasicMtlAntlr2TLLJava/src/TLLBuilder/VarDeclarationAnalyser.java,v 1.2 2003-08-09 15:16:08 jpthibau Exp $
 * Created on 23 juil. 2003
 *
 */
package TLLBuilder;

import org.irisa.triskell.MT.utils.Java.AWK;
import org.irisa.triskell.MT.utils.Java.Mangler;
import org.irisa.triskell.MT.visitors.Java.AnalysingVisitor.*;
import org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.*;

/**
 * @author jpthibau
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class VarDeclarationAnalyser extends ASTTopDownVisitor.VarDeclarationAnalyser {

	public void VarDeclarationAction(org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.VarDeclaration ASTnode,java.util.Map context)
	{	String varName=ASTnode.getName();
		String mangle=null;
		Property mangling=(Property)ASTnode.getProperty("mangle");
		if (mangling == null)
			mangle=Mangler.mangle("BMTL_",varName);
		else mangle=(String)((java.util.Vector)mangling.getValue()).get(2);
		int lineNumber=-1000; //do we have to add a lineNumber Property ?
//		int lineNumber=Integer.parseInt((String)ASTnode.getProperty("LineNumber").getValue());
		VarDeclaration theCreatedVarDeclaration=new VarDeclaration(varName,mangle,false,lineNumber);
		BasicMtlLibrary theCreatedLib=(BasicMtlLibrary)context.get("TheCreatedLibrary");
		Property varType=(Property)ASTnode.getProperty("Type");
		QualifiedName type=CommonFunctions.findOrAddType((java.util.Vector)varType.getValue(),theCreatedLib);
		theCreatedVarDeclaration.setType(type);
		context.put("VarDeclaration",theCreatedVarDeclaration);
	}

}
