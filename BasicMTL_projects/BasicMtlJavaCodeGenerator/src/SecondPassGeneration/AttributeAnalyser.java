/*
 * $Header: /tmp/cvs2svn/cvsroot/BasicMTL_projects/BasicMtlJavaCodeGenerator/src/SecondPassGeneration/AttributeAnalyser.java,v 1.4 2003-08-19 13:37:25 ffondeme Exp $
 * Created on 4 août 2003
 *
 */
package SecondPassGeneration;

import java.io.*;
import org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.*;
import org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.signatures.AttributeGetterSignature;
import org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.signatures.AttributeSetterSignature;
import org.irisa.triskell.MT.utils.Java.Mangler;

/**
 * @author jpthibau
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class AttributeAnalyser extends TLLTopDownVisitor.AttributeAnalyser {

	public void AttributeAction(Attribute ASTnode,java.util.Map context)
	{	PrintWriter outputForClass = (PrintWriter)context.get("OutputForClass");
		PrintWriter outputForInterface = (PrintWriter)context.get("OutputForInterface");
		AttributeGetterSignature getter = new AttributeGetterSignature(ASTnode);
		//WARNING: the following signature will return null if asked the return type
		AttributeSetterSignature setter = new AttributeSetterSignature(ASTnode, null);
		outputForClass.println("public "+getter.getReturnedType().getDeclarationName()+' '+getter.getOpMangle()+"()");
		outputForClass.println("{ return this."+ASTnode.getMangle()+"; }\n");
		outputForClass.println("public VoidValueImpl "+setter.getOpMangle()+" ("+setter.getArgsTypes(0).getDeclarationName()+" value)");
		outputForClass.println("{ this."+ASTnode.getMangle()+"=value;");
		outputForClass.println("return VoidValueImpl.getTheInstance(); }\n");
		outputForInterface.println("public "+getter.getReturnedType().getDeclarationName()+' '+getter.getOpMangle()+"();");
		outputForInterface.println("public VoidValueImpl "+setter.getOpMangle()+" ("+setter.getArgsTypes(0).getDeclarationName()+" value);");
//		if (type.getIsLocalType())
//			{	outputForClass.println("public "+type.getDeclarationName()+' '+attributerGetterName+"()");
//				outputForClass.println("{ return this."+ASTnode.getMangle()+"; }\n");
//				outputForClass.println("public VoidValueImpl "+attributerSetterName+" ("+type.getDeclarationName()+" value)");
//				outputForClass.println("{ this."+ASTnode.getMangle()+"=value;");
//				outputForClass.println("return VoidValueImpl.getTheInstance(); }\n");
//				outputForInterface.println("public "+type.getDeclarationName()+' '+attributerGetterName+"();");
//				outputForInterface.println("public VoidValueImpl "+attributerSetterName+" ("+type.getDeclarationName()+" value);");
//		} 
//		else if ((type.getIsExternType())
//				|| ((type.getIsModelType()) && (! type.getIsRepositoryModel())))
//				{	outputForClass.println("public "+type.getDeclarationName()+' '+attributerGetterName+"()");
//					outputForClass.println("{ return this."+ASTnode.getMangle()+"; }\n\n");
//					outputForClass.println("public VoidValueImpl "+attributerSetterName+" ("+type.getDeclarationName()+" value)");
//					outputForClass.println("{ this."+ASTnode.getMangle()+"=value;");
//					outputForClass.println("return VoidValueImpl.getTheInstance(); }\n");
//					outputForInterface.println("public "+type.getDeclarationName()+' '+attributerGetterName+"();");
//					outputForInterface.println("public VoidValueImpl "+attributerSetterName+" ("+type.getDeclarationName()+" value);");
//				} 
//			else if (type.getIsModelType())
//					{	if (type.getIsRepositoryModel())
//							{	outputForClass.println("public API "+attributerGetterName+"()");
//								outputForClass.println("{ return this."+ASTnode.getMangle()+"; }\n");
//								outputForClass.println("public VoidValueImpl "+attributerSetterName+" (API value)");
//								outputForClass.println("{ this."+ASTnode.getMangle()+"=value;");
//								outputForClass.println("return VoidValueImpl.getTheInstance(); }\n");
//								outputForInterface.println("public API "+attributerGetterName+"();");
//								outputForInterface.println("public VoidValueImpl "+attributerSetterName+" (API value);");
//							} 
//					}
//				else CodeGeneration.BMTLCompiler.getLog().error("Attribute has a wrong type qualifier !"+ASTnode.getName()+':'+type);
	}

}
