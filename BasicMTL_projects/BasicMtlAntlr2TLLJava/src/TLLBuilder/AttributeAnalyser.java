/*
 * Created on 23 juil. 2003
 * $Id: AttributeAnalyser.java,v 1.4 2004-04-01 12:54:52 dvojtise Exp $
 * Authors : jpthibau
 * 
 * Copyright 2004 - INRIA - LGPL license
 */
package TLLBuilder;

// import org.irisa.triskell.MT.utils.Java.AWK;
import org.irisa.triskell.MT.utils.Java.Mangler;
import org.irisa.triskell.MT.visitors.Java.AnalysingVisitor.*;
import org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.*;

/**
 * @author jpthibau
 *
 */
public class AttributeAnalyser extends ASTTopDownVisitor.AttributeAnalyser {

	public void AttributeAction(org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.Attribute ASTnode,java.util.Map context)
	{	String attributeName=ASTnode.getName();
		String mangle=null;
		Property mangling=(Property)ASTnode.getProperty("mangle");
		if (mangling == null)
			mangle=Mangler.mangle("BMTL_",attributeName);
		else mangle=(String)((java.util.Vector)mangling.getValue()).get(2);
		int lineNumber=Integer.parseInt((String)ASTnode.getProperty("LineNumber").getValue());
		Attribute theCreatedAttribute=new Attribute(attributeName,mangle,lineNumber);
		// transmit the file name and line number to the new attribute for traceability.
		theCreatedAttribute.createNewProperty("LineNumber",ASTnode.getProperty("LineNumber").getValue(),"String");
		theCreatedAttribute.createNewProperty("FileName",ASTnode.getProperty("FileName").getValue(),"String");
		BasicMtlLibrary theCreatedLib=(BasicMtlLibrary)context.get("TheCreatedLibrary");
		Property attributeType=(Property)ASTnode.getProperty("Type");
		QualifiedName type=CommonFunctions.findOrAddType((java.util.Vector)attributeType.getValue(),theCreatedLib);
		type.appendTypeForFeatures(theCreatedAttribute);
		theCreatedAttribute.setFeatureType(type);
		context.put("Attribute",theCreatedAttribute);
			
	}

}
