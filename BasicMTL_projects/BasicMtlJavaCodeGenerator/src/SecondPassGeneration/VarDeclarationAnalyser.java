/*
 * $Header: /tmp/cvs2svn/cvsroot/BasicMTL_projects/BasicMtlJavaCodeGenerator/src/SecondPassGeneration/VarDeclarationAnalyser.java,v 1.4 2003-10-14 14:35:40 jpthibau Exp $
 * Created on 7 août 2003
 *
 */
package SecondPassGeneration;

import java.io.*;
import org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.*;

/**
 * @author jpthibau
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class VarDeclarationAnalyser extends TLLTopDownVisitor.VarDeclarationAnalyser {

	public void VarDeclarationAction(VarDeclaration ASTnode,java.util.Map context)
	{	PrintWriter outputForClass = (PrintWriter)context.get("OutputForClass");
		PrintWriter outputForInterface = (PrintWriter)context.get("OutputForInterface");
		QualifiedName type=ASTnode.getType();
		outputForClass.print(type.getDeclarationName()+' '+ASTnode.getMangle());
		if (ASTnode.getIsFormalParameter())
			outputForInterface.print(type.getDeclarationName()+' '+ASTnode.getMangle());
		if (! ASTnode.getIsFormalParameter()) outputForClass.println("=null;");
	}

}
