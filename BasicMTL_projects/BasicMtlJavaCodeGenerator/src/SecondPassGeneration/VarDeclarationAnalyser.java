/*
 * $Header: /tmp/cvs2svn/cvsroot/BasicMTL_projects/BasicMtlJavaCodeGenerator/src/SecondPassGeneration/VarDeclarationAnalyser.java,v 1.5 2003-12-16 07:51:45 jpthibau Exp $
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
		if (ASTnode.getIsCatchParameter()) {
			outputForClass.println("java.lang.Throwable throwable) { try {");
			outputForClass.println(type.getDeclarationName()+' '+ASTnode.getMangle()+"=("+type.getDeclarationName()+")throwable;");
			outputForClass.println("} catch(Exception e) { throw throwable; }");
		} else outputForClass.print(type.getDeclarationName()+' '+ASTnode.getMangle());
		if (ASTnode.getIsFormalParameter()
			&& ! ASTnode.getIsCatchParameter())
			outputForInterface.print(type.getDeclarationName()+' '+ASTnode.getMangle());
		if (! ASTnode.getIsFormalParameter()) outputForClass.println("=null;");
	}

}
