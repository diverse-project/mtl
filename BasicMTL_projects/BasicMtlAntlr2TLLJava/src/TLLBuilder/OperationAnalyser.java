/*
 * Created on 23 juil. 2003
 * $Id: OperationAnalyser.java,v 1.7 2004-04-06 07:54:38 dvojtise Exp $
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
 * Creates the TLL structures for an operation. input is a BasicMTL AST - output is a BasicMTL TLL
 * @author jpthibau
 */
public class OperationAnalyser extends ASTTopDownVisitor.OperationAnalyser {

	/**
	 * action to take when visiting an Operation in a BasicMTL AST : create a BasicMTLTLL operation
	 */
	public Object OperationBefore(org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.Operation ASTnode,java.util.Map context)
	{	String operationName=ASTnode.getName();
		String mangle=null;
		Property FileNameProperty=null;
		String FileName; 
		
		Property mangling=(Property)ASTnode.getProperty("mangle");
		if (mangling == null)
			mangle=Mangler.mangle("BMTL_",operationName);
		else mangle=(String)((java.util.Vector)mangling.getValue()).get(2);
		Property constructorProp=(Property)ASTnode.getProperty("IsConstructor");
		boolean isConstructor=false;
		if (constructorProp !=null) isConstructor=true;
		boolean throwException=ASTnode.getThrowsException();
		int lineNumber=Integer.parseInt((String)ASTnode.getProperty("LineNumber").getValue());
		Operation theCreatedOp=new Operation(operationName,mangle,throwException,isConstructor,lineNumber);
		//		transmit the file name and line number to the new var for traceability.
		theCreatedOp.createNewProperty("LineNumber",ASTnode.getProperty("LineNumber").getValue(),"String");
		FileNameProperty = ASTnode.getProperty("FileName");
		if (FileNameProperty == null) FileName = "Unknown file location for variable "+operationName; 
		else FileName = (String)FileNameProperty.getValue();		
		if (! isConstructor) {
			Property returnedType=(Property)ASTnode.getProperty("returnedType");
			BasicMtlLibrary theCreatedLib=(BasicMtlLibrary)context.get("TheCreatedLibrary");
			QualifiedName type=CommonFunctions.findOrAddType((java.util.Vector)returnedType.getValue(),theCreatedLib);
			type.appendTypeForFeatures(theCreatedOp);
			theCreatedOp.setFeatureType(type);}
		context.put("CurrentOperation",theCreatedOp);
		context.put("DeclaredParametersandVars",new java.util.Vector());
		return theCreatedOp;
	}


	public void OperationParameter(Object theOp,Object objectVarDecl,java.util.Map context)
	{	Operation theCreatedOp=(Operation)theOp;
		VarDeclaration varDecl=(VarDeclaration)objectVarDecl;
		varDecl.setIsFormalParameter(true);
		theCreatedOp.appendParameters(varDecl);
		java.util.Vector knownVarDecls=(java.util.Vector)context.get("DeclaredParametersandVars");
		knownVarDecls.addElement(varDecl);
		context.put("DeclaredParametersandVars",knownVarDecls);
	}

	public void OperationVarDeclaration(Object theOp,Object objectVarDecl,java.util.Map context)
	{	Operation theCreatedOp=(Operation)theOp;
		VarDeclaration varDecl=(VarDeclaration)objectVarDecl;
		theCreatedOp.appendDeclaredVariables(varDecl);
		java.util.Vector knownVarDecls=(java.util.Vector)context.get("DeclaredParametersandVars");
		knownVarDecls.addElement(varDecl);
		context.put("DeclaredParametersandVars",knownVarDecls);
	}

	public void OperationInstruction(Object theOp,Object objectInstr,java.util.Map context)
	{	Operation theCreatedOp=(Operation)theOp;
		Instruction instr=(Instruction)objectInstr;
		theCreatedOp.appendInstructions(instr);
		instr.setContainerOp(theCreatedOp);
	}

	public void OperationAfter(Object theOp,org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.Operation ASTnode,java.util.Map context)
	{	Operation theCreatedOp=(Operation)theOp;
		OpSignature theOpSignature=new OpSignature(theCreatedOp.getName(),theCreatedOp.getMangle());
		if (theCreatedOp.getIsConstructor())
			theOpSignature.setReturnedType((QualifiedName)context.get("CurrentClassType"));
		else theOpSignature.setReturnedType(theCreatedOp.getFeatureType());
		theOpSignature.setArgsCount(theCreatedOp.cardParameters());
		for(int i=0;i<theCreatedOp.cardParameters();i++)
			theOpSignature.appendArgsTypes(theCreatedOp.getParameters(i).getType());
		theCreatedOp.setTheSignature(theOpSignature);
		context.put("Operation",theCreatedOp);
			
	}

}
