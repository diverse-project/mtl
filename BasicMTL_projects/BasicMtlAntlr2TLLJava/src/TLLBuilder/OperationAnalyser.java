/*
 * $Header: /tmp/cvs2svn/cvsroot/BasicMTL_projects/BasicMtlAntlr2TLLJava/src/TLLBuilder/OperationAnalyser.java,v 1.1 2003-08-06 16:18:45 jpthibau Exp $
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
public class OperationAnalyser extends ASTTopDownVisitor.OperationAnalyser {

	public Object OperationBefore(org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.Operation ASTnode,java.util.Map context)
	{	String operationName=ASTnode.getName();
		String mangle=null;
		Property mangling=(Property)ASTnode.getProperty("mangle");
		if (mangling == null)
			mangle=Mangler.mangle("BMTL_",operationName);
		else mangle=(String)mangling.getValue();
		Property constructorProp=(Property)ASTnode.getProperty("IsConstructor");
		boolean isConstructor=false;
		if (constructorProp !=null) isConstructor=true;
		boolean throwException=ASTnode.getThrowsException();
		int lineNumber=Integer.parseInt((String)ASTnode.getProperty("LineNumber").getValue());
		Operation theCreatedOp=new Operation(operationName,mangle,throwException,isConstructor,lineNumber);
		if (! isConstructor) {
			Property returnedType=(Property)ASTnode.getProperty("returnedType");
			BasicMtlLibrary theCreatedLib=(BasicMtlLibrary)context.get("TheCreatedLibrary");
			QualifiedName type=CommonFunctions.findOrAddType((java.util.Vector)returnedType.getValue(),theCreatedLib);
			theCreatedOp.setFeatureType(type);}
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
