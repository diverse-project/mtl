/*
 * $Header: /tmp/cvs2svn/cvsroot/BasicMTL_projects/BasicMtlAntlr2TLLJava/src/TLLBuilder/RepositoryRefAnalyser.java,v 1.1 2003-08-06 16:18:46 jpthibau Exp $
 * Created on 25 juil. 2003
 *
 */
package TLLBuilder;

import org.irisa.triskell.MT.utils.Java.Mangler;
import org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.*;

/**
 * @author jpthibau
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class RepositoryRefAnalyser extends ASTTopDownVisitor.RepositoryRefAnalyser {

	public void RepositoryRefAction(org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.RepositoryRef ASTnode,java.util.Map context)
	{	int lineNumber=Integer.parseInt((String)ASTnode.getProperty("LineNumber").getValue());
		String modelName=ASTnode.getName();
		ModelRef theCreatedRepositoryRef=new RepositoryRef(modelName,lineNumber);
		String mangle=Mangler.mangle("BMTL_",modelName);
		Attribute repositoryAttribute=new Attribute(modelName,mangle,lineNumber);
		QualifiedName type=new QualifiedName();
		type.addElement("API");
		type.setIsModelType(true);
		type.setIsRepositoryModel(true);
		type.setExternMangledName("org.irisa.triskell.MT.repository.API.Java.API");
		repositoryAttribute.setFeatureType(type);
		java.util.Vector modelAndAttribute=new java.util.Vector();
		modelAndAttribute.addElement(theCreatedRepositoryRef);
		modelAndAttribute.addElement(repositoryAttribute);
		context.put("ModelRef",modelAndAttribute);
			
	}
}
