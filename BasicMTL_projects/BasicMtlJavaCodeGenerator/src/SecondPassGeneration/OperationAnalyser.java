/*
 * $Header: /tmp/cvs2svn/cvsroot/BasicMTL_projects/BasicMtlJavaCodeGenerator/src/SecondPassGeneration/OperationAnalyser.java,v 1.15 2004-11-03 09:42:24 jpthibau Exp $
 * Created on 7 août 2003
 *
 */
package SecondPassGeneration;

import java.io.*;
import java.util.Arrays;
import java.util.Map;

import org.apache.log4j.Logger;
import org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.*;
import org.irisa.triskell.MT.utils.Java.AWK;
import org.irisa.triskell.MT.utils.Java.JavaStringLiteralEncoder;
import org.irisa.triskell.MT.utils.MessagesHandler.MSGHandler;

import CodeGeneration.BMTLCompiler;

/**
 * @author jpthibau
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class OperationAnalyser extends TLLTopDownVisitor.OperationAnalyser {
	static final Logger log=MSGHandler.init();

	public Object OperationBefore(Operation ASTnode,java.util.Map context)
	{	PrintWriter outputForClass = (PrintWriter)context.get("OutputForClass");
		PrintWriter outputForInterface = (PrintWriter)context.get("OutputForInterface");
			UserDefinedClass clazz = (UserDefinedClass)context.get("CurrentClass");
		String currentClassMangle=(String)context.get("CurrentClassMangledName");
		if (ASTnode.getName().equalsIgnoreCase("main") && (clazz instanceof TheLibraryClass)) {
			boolean isMain, hasParams;
			int limit = ASTnode.cardParameters();
			hasParams = limit != 0;
			if (hasParams) {
				if (limit == 1) {
					isMain = ASTnode.getParameters(0).getType().equals(Arrays.asList(new String [] {"Standard", "Sequence"}));
				} else
					isMain = false;
			} else
				isMain = true;
			if (isMain) {
				outputForClass.println("public static void main(String args[]) {");
				outputForClass.println("  try {");
				if (hasParams) {
					outputForClass.println("  BMTLString [] params = new BMTLString [args.length];");
					outputForClass.println("  for (int i= 0; i < params.length; ++i)");
					outputForClass.println("    params[i] = new BMTLString(args[i]);");
				}
				//@TODO check this piece of code
				//if(((TheLibraryClass)clazz).getTheLibrary().cardUsedModels()==0)
					outputForClass.print("    "+currentClassMangle+".TheInstance");
				//else
				//	outputForClass.print("    new "+currentClassMangle+"()");
				if (hasParams) {
					outputForClass.println(".BMTL_main(new BMTLSequence(params));");
				} else
					outputForClass.println(".BMTL_main();");
				outputForClass.println("    System.exit(0);"); 
				outputForClass.println("  } catch (Throwable t) {"); 
				boolean isLib = clazz instanceof TheLibraryClass; 
				outputForClass.println("    System.err.println(\"Problem while executing the main function of " + (isLib ? "library " : "class ") + JavaStringLiteralEncoder.encodeString(AWK.mergeCollection(clazz.getQualifiedName(), "::")) + ":\");");
				outputForClass.println("    t.printStackTrace();");
				outputForClass.println("    System.exit(-1);");				
				outputForClass.println("  }");
				outputForClass.println('}'); 
			}
		}
		QualifiedName type=ASTnode.getFeatureType();
		if (type.getIsLocalType())
			if (type.getLocalMangledName().equals(ASTnode.getMangle()))
				log.error("Constructors not allowed in BMTL : "+ASTnode.getName());
			else {
				outputForClass.print("public "+type.getDeclarationName()+' '+ASTnode.getMangle()+'(');
				outputForInterface.print("public "+type.getDeclarationName()+' '+ASTnode.getMangle()+'(');
			}
		else { //Extern library type
			outputForClass.print("public "+type.getDeclarationName()+' '+ASTnode.getMangle()+'('); 
			outputForInterface.print("public "+type.getDeclarationName()+' '+ASTnode.getMangle()+'(');
		}
		context.put("OperationContainer",ASTnode);
		return null;
	}

	public void OperationParamSeparator(java.util.Map context)
	{	PrintWriter outputForClass = (PrintWriter)context.get("OutputForClass");
		PrintWriter outputForInterface = (PrintWriter)context.get("OutputForInterface");
		outputForClass.print(',');
		outputForInterface.print(',');
	}
	
	public void OperationEndParameters(Object theOperation, java.util.Map context)
	{	PrintWriter outputForClass = (PrintWriter)context.get("OutputForClass");
		PrintWriter outputForInterface = (PrintWriter)context.get("OutputForInterface");
		outputForClass.println(") throws Throwable {");
		outputForInterface.print(") throws Throwable");
		/*Operation theOp = (Operation)theOperation;
		for (int i = 0; i < theOp.cardParameters(); ++i) {
			VarDeclaration p = theOp.getParameters(i);
			outputForClass.println(p.getMangle() + " = (" + p.getType().getDeclarationName() + ")CommonFunctions.toBMTLDataType(" + p.getMangle() + ");");
		}*/
	}

	public void OperationAfter(Object theOperation,Operation ASTnode,java.util.Map context) {
		PrintWriter outputForClass = (PrintWriter)context.get("OutputForClass");
		PrintWriter outputForInterface = (PrintWriter)context.get("OutputForInterface");
		if (ASTnode.isAbstract)
			outputForClass.print("System.err.println(\""+ASTnode.getMangle()+" : This method is abstract and should not be called.\");");
		QualifiedName type=ASTnode.getFeatureType();
		if (((type.size()==1)
				&& ((String)type.get(0)).equals("Void"))
			|| ((type.size() > 1 )
				&& ((String)type.get(0)).equals("Standard")
				&& ((String)type.get(1)).equals("Void")))
				outputForClass.println("return BMTLVoid.TheInstance; }\n\n");
		else outputForClass.println("}\n\n");
		outputForInterface.println(';');
	}

	public void OperationInstruction(Object theOperation, Object instr, Map context) {
		Boolean needsColumn = (Boolean)context.get("NeedsSemiColumn");
		if (needsColumn == null || needsColumn.booleanValue()) {
			PrintWriter outputForClass = (PrintWriter)context.get("OutputForClass");
			outputForClass.println(';');
		}
		context.put("NeedsSemiColumn", Boolean.TRUE);
	}

}