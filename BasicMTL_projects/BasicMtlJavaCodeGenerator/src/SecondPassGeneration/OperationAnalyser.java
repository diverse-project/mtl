/*
 * $Header: /tmp/cvs2svn/cvsroot/BasicMTL_projects/BasicMtlJavaCodeGenerator/src/SecondPassGeneration/OperationAnalyser.java,v 1.2 2003-08-09 16:02:07 jpthibau Exp $
 * Created on 7 août 2003
 *
 */
package SecondPassGeneration;

import java.io.*;
import org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.*;

import CodeGeneration.BMTLCompiler;

/**
 * @author jpthibau
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class OperationAnalyser extends TLLTopDownVisitor.OperationAnalyser {

	public Object OperationBefore(Operation ASTnode,java.util.Map context)
	{	PrintWriter outputForClass = (PrintWriter)context.get("OutputForClass");
		PrintWriter outputForInterface = (PrintWriter)context.get("OutputForInterface");
		String currentClassMangle=(String)context.get("CurrentClassMangledName");
		if (ASTnode.getName().equalsIgnoreCase("main")) {
			outputForClass.println("public static void main(String args[]) {"); 
			outputForClass.println("new "+currentClassMangle+"().BMTL_main(); }");
		}
		QualifiedName type=ASTnode.getFeatureType();
		if (type.getIsLocalType())
			if (type.getLocalMangledName().equals(ASTnode.getMangle()))
				BMTLCompiler.getLog().error("Constructors not allowed in BMTL : "+ASTnode.getName());
			else {
				outputForClass.print("public "+type.getLocalMangledName()+" "+ASTnode.getMangle()+"("); 
				outputForInterface.print("public "+type.getLocalMangledName()+" "+ASTnode.getMangle()+"(");
			}
		else { //Extern library type
			outputForClass.print("public "+type.getExternCompleteName()+" "+ASTnode.getMangle()+"("); 
			outputForInterface.print("public "+type.getExternCompleteName()+" "+ASTnode.getMangle()+"(");
		}
		return null;
	}

	public void OperationParamSeparator(java.util.Map context)
	{	PrintWriter outputForClass = (PrintWriter)context.get("OutputForClass");
		PrintWriter outputForInterface = (PrintWriter)context.get("OutputForInterface");
		outputForClass.print(",");
		outputForInterface.println(",");
	}
	
	public void OperationEndParameters(java.util.Map context)
	{	PrintWriter outputForClass = (PrintWriter)context.get("OutputForClass");
		PrintWriter outputForInterface = (PrintWriter)context.get("OutputForInterface");
		outputForClass.println(")");
		outputForInterface.println(")");
	}

	public void OperationAfter(Object theOperation,Operation ASTnode,java.util.Map context) {
		PrintWriter outputForClass = (PrintWriter)context.get("OutputForClass");
		QualifiedName type=ASTnode.getFeatureType();
		if (((type.size()==1)
				&& ((String)type.get(0)).equals("Void"))
			|| ((type.size() > 1 )
				&& ((String)type.get(0)).equals("Standard")
				&& ((String)type.get(1)).equals("Void")))
				outputForClass.println("return VoidValueImpl.getTheInstance(); }\n\n");
		else outputForClass.println("}\n\n");
	}
}
