/*
 * $Header: /tmp/cvs2svn/cvsroot/BasicMTL_projects/BasicMtlJavaCodeGenerator/src/FirstPassGeneration/AttributeAnalyser.java,v 1.1 2003-08-08 15:41:10 jpthibau Exp $
 * Created on 4 août 2003
 *
 */
package FirstPassGeneration;

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
		QualifiedName type=ASTnode.getFeatureType();
		if (type.getIsLocalType())
			outputForClass.println(type.getLocalMangledName()+" "+ASTnode.getMangle()+";\n");
		else if ((type.getIsExternType())
				|| ((type.getIsModelType()) && (! type.getIsRepositoryModel())))
				outputForClass.println(type.getExternCompleteName()+" "+ASTnode.getMangle()+";\n");
			else if (type.getIsModelType())
					{	if (type.getIsRepositoryModel())
							outputForClass.println("API "+ASTnode.getMangle()+";\n");
					}
				else CodeGeneration.BMTLCompiler.getLog().error("Attribute has a wrong type qualifier !"+ASTnode.getName()+":"+type);;
	}

}
