/*
 * $Header: /tmp/cvs2svn/cvsroot/BasicMTL_projects/BasicMtlJavaCodeGenerator/src/FirstPassGeneration/AttributeAnalyser.java,v 1.4 2004-06-04 13:24:07 jpthibau Exp $
 * Created on 4 ao�t 2003
 *
 */
package FirstPassGeneration;

import java.io.*;
import org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.*;
import org.irisa.triskell.MT.utils.MessagesHandler.MSGHandler;

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
			outputForClass.println(type.getDeclarationName()+' '+ASTnode.getMangle()+";\n");
		else if ((type.getIsExternType())
				|| ((type.getIsModelType()) && (! type.getIsRepositoryModel())))
				outputForClass.println(type.getDeclarationName()+' '+ASTnode.getMangle()+";\n");
			else if (type.getIsModelType())
					{	if (type.getIsRepositoryModel())
							outputForClass.println("API "+ASTnode.getMangle()+';');
							outputForClass.println();
					}
				else MSGHandler.error("Attribute has a wrong type qualifier ! "+ASTnode.getName()+':'+type);;
	}

}
