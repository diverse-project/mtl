/*
 * $Header: /tmp/cvs2svn/cvsroot/BasicMTL_projects/BasicMtlAntlr2TLLJava/src/TLLBuilder/AttributeAnalyser.java,v 1.1 2003-08-06 16:18:46 jpthibau Exp $
 * Created on 23 juil. 2003
 *
 */
package TLLBuilder;

import org.irisa.triskell.MT.utils.Java.AWK;
import org.irisa.triskell.MT.utils.Java.Mangler;
import org.irisa.triskell.MT.visitors.Java.AnalysingVisitor.*;
import org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.*;

/**
 * @author jpthibau
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class AttributeAnalyser extends ASTTopDownVisitor.AttributeAnalyser {

	public void AttributeAction(org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.Attribute ASTnode,java.util.Map context)
	{	String attributeName=ASTnode.getName();
		String mangle=null;
		Property mangling=(Property)ASTnode.getProperty("mangle");
		if (mangling == null)
			mangle=Mangler.mangle("BMTL_",attributeName);
		else mangle=(String)mangling.getValue();
		int lineNumber=Integer.parseInt((String)ASTnode.getProperty("LineNumber").getValue());
		Attribute theCreatedAttribute=new Attribute(attributeName,mangle,lineNumber);
		BasicMtlLibrary theCreatedLib=(BasicMtlLibrary)context.get("TheCreatedLibrary");
		Property attributeType=(Property)ASTnode.getProperty("Type");
		QualifiedName type=CommonFunctions.findOrAddType((java.util.Vector)attributeType.getValue(),theCreatedLib);
		theCreatedAttribute.setFeatureType(type);
		context.put("Attribute",theCreatedAttribute);
			
	}

}
