/*
 * $Header: /tmp/cvs2svn/cvsroot/BasicMTL_projects/BasicMtlJavaCodeGenerator/src/FirstPassGeneration/AttributeAnalyser.java,v 1.6 2004-10-18 15:06:05 jpthibau Exp $
 * Created on 4 août 2003
 *
 */
package FirstPassGeneration;

import java.io.*;

import org.apache.log4j.Logger;
import org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.*;
import org.irisa.triskell.MT.utils.MessagesHandler.MSGHandler;

/**
 * @author jpthibau
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class AttributeAnalyser extends TLLTopDownVisitor.AttributeAnalyser {
	static final Logger log=Logger.getLogger("MSGHandler");

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
				else log.error("Attribute has a wrong type qualifier ! "+ASTnode.getName()+':'+type);;
	}

}
