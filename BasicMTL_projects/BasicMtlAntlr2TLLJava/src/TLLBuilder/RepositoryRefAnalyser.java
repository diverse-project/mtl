/*
 * $Header: /tmp/cvs2svn/cvsroot/BasicMTL_projects/BasicMtlAntlr2TLLJava/src/TLLBuilder/RepositoryRefAnalyser.java,v 1.3 2003-08-21 20:03:07 ffondeme Exp $
 * Created on 25 juil. 2003
 *
 */
package TLLBuilder;

import java.util.Arrays;
import java.util.Vector;

import org.irisa.triskell.MT.utils.Java.Mangler;
import org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.*;
import org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.signatures.AttributeGetterSignature;
import org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.signatures.AttributeSetterSignature;

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
		type.setDeclarationName(type.getExternMangledName());
		repositoryAttribute.setFeatureType(type);
		java.util.Vector modelAndAttribute=new java.util.Vector();
		modelAndAttribute.addElement(theCreatedRepositoryRef);
		modelAndAttribute.addElement(repositoryAttribute);
		BasicMtlLibrary theCreatedLib=(BasicMtlLibrary)context.get("TheCreatedLibrary");
		TheLibraryClass tlc = null;
		for (int i = 0;tlc == null && i < theCreatedLib.cardClasses(); ++i)
			if (theCreatedLib.getClasses(i) instanceof TheLibraryClass)
				tlc = (TheLibraryClass)theCreatedLib.getClasses(i);
		AttributeGetterSignature ags = new AttributeGetterSignature(repositoryAttribute);
		AttributeSetterSignature ass = new AttributeSetterSignature(repositoryAttribute, CommonFunctions.findOrAddType(new Vector(Arrays.asList(new String [] {"Standard", "Void"})), theCreatedLib));
		tlc.appendLocalSignatures(ags);
		tlc.appendLocalSignatures(ass);
		context.put("ModelRef",modelAndAttribute);
			
	}
}
