/*
 * $Header: /tmp/cvs2svn/cvsroot/BasicMTL_projects/BasicMtlJavaCodeGenerator/src/SecondPassGeneration/AttributeAnalyser.java,v 1.3 2003-08-14 21:31:40 ffondeme Exp $
 * Created on 4 août 2003
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
public class AttributeAnalyser extends TLLTopDownVisitor.AttributeAnalyser {

	public void AttributeAction(Attribute ASTnode,java.util.Map context)
	{	PrintWriter outputForClass = (PrintWriter)context.get("OutputForClass");
		PrintWriter outputForInterface = (PrintWriter)context.get("OutputForInterface");
		QualifiedName type=ASTnode.getFeatureType();
		if (type.getIsLocalType())
			{	outputForClass.println("public "+type.getLocalMangledName()+" get_"+ASTnode.getMangle()+"()");
				outputForClass.println("{ return this."+ASTnode.getMangle()+"; }\n");
				outputForClass.println("public VoidValueImpl set_"+ASTnode.getMangle()+" ("+type.getLocalMangledName()+" value)");
				outputForClass.println("{ this."+ASTnode.getMangle()+"=value;");
				outputForClass.println("return VoidValueImpl.getTheInstance(); }\n");
				outputForInterface.println("public "+type.getLocalMangledName()+" get_"+ASTnode.getMangle()+"();");
				outputForInterface.println("public VoidValueImpl set_"+ASTnode.getMangle()+" ("+type.getLocalMangledName()+" value);");
		} 
		else if ((type.getIsExternType())
				|| ((type.getIsModelType()) && (! type.getIsRepositoryModel())))
				{	outputForClass.println("public "+type.getExternCompleteName()+" get_"+ASTnode.getMangle()+"()");
					outputForClass.println("{ return this."+ASTnode.getMangle()+"; }\n\n");
					outputForClass.println("public VoidValueImpl set_"+ASTnode.getMangle()+" ("+type.getExternCompleteName()+" value)");
					outputForClass.println("{ this."+ASTnode.getMangle()+"=value;");
					outputForClass.println("return VoidValueImpl.getTheInstance(); }\n");
					outputForInterface.println("public "+type.getExternCompleteName()+" get_"+ASTnode.getMangle()+"();");
					outputForInterface.println("public VoidValueImpl set_"+ASTnode.getMangle()+" ("+type.getExternCompleteName()+" value);");
				} 
			else if (type.getIsModelType())
					{	if (type.getIsRepositoryModel())
							{	outputForClass.println("public API get_"+ASTnode.getMangle()+"()");
								outputForClass.println("{ return this."+ASTnode.getMangle()+"; }\n");
								outputForClass.println("public VoidValueImpl set_"+ASTnode.getMangle()+" (API value)");
								outputForClass.println("{ this."+ASTnode.getMangle()+"=value;");
								outputForClass.println("return VoidValueImpl.getTheInstance(); }\n");
								outputForInterface.println("public API get_"+ASTnode.getMangle()+"();");
								outputForInterface.println("public VoidValueImpl set_"+ASTnode.getMangle()+" (API value);");
							} 
					}
				else CodeGeneration.BMTLCompiler.getLog().error("Attribute has a wrong type qualifier !"+ASTnode.getName()+':'+type);
	}

}
