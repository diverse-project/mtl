/*
 * Created on 23 juil. 2003
 * $Id: VarDeclarationAnalyser.java,v 1.5 2004-04-06 07:54:38 dvojtise Exp $
 * Authors : jpthibau
 * 
 * Copyright 2004 - INRIA - LGPL license
 */
package TLLBuilder;

//import org.irisa.triskell.MT.utils.Java.AWK;
import org.irisa.triskell.MT.utils.Java.Mangler;
import org.irisa.triskell.MT.visitors.Java.AnalysingVisitor.*;
import org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.*;

/**
 * Creates the TLL structures for a Variable declaration. input is a BasicMTL AST - output is a BasicMTL TLL
 * @author jpthibau
 */
public class VarDeclarationAnalyser extends ASTTopDownVisitor.VarDeclarationAnalyser {

	/**
	 * action to take when visiting a Variable declaration in a BasicMTL AST : create a BasicMTLTLL Variable declaration
	 */
	public void VarDeclarationAction(org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.VarDeclaration ASTnode,java.util.Map context)
	{	String varName=ASTnode.getName();
		String mangle=null;
		Property FileNameProperty=null;
		Property LineNumberProperty=null;
		String FileName; 
		int lineNumber;
		String lineNumberString;
		
		Property mangling=(Property)ASTnode.getProperty("mangle");
		if (mangling == null)
			mangle=Mangler.mangle("BMTL_",varName);
		else mangle=(String)((java.util.Vector)mangling.getValue()).get(2);

		LineNumberProperty = ASTnode.getProperty("LineNumber");
		if (LineNumberProperty == null) 
		{
			lineNumber=-1000;
			lineNumberString = "(Unknown line number)";
		} 
		else
		{ 
			lineNumber=Integer.parseInt((String)LineNumberProperty.getValue());
			lineNumberString =  (String)LineNumberProperty.getValue();
		} 
		VarDeclaration theCreatedVarDeclaration=new VarDeclaration(varName,mangle,false,lineNumber);
		// transmit the file name and line number to the new var for traceability.
		theCreatedVarDeclaration.createNewProperty("LineNumber",lineNumberString,"String");
		FileNameProperty = ASTnode.getProperty("FileName");
		if (FileNameProperty == null) FileName = "Unknown file location for variable "+varName; 
		else FileName = (String)FileNameProperty.getValue();		
		theCreatedVarDeclaration.createNewProperty("FileName",FileName,"String");BasicMtlLibrary theCreatedLib=(BasicMtlLibrary)context.get("TheCreatedLibrary");
		Property varType=(Property)ASTnode.getProperty("Type");
		QualifiedName type=CommonFunctions.findOrAddType((java.util.Vector)varType.getValue(),theCreatedLib);
		type.appendTypeForVarDeclarations(theCreatedVarDeclaration);
		theCreatedVarDeclaration.setType(type);
		context.put("VarDeclaration",theCreatedVarDeclaration);
	}

}
