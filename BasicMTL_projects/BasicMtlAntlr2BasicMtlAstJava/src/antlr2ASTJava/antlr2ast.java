/*
 * Created on 16 juil. 2003
 * $Id: antlr2ast.java,v 1.23 2004-10-18 16:00:06 jpthibau Exp $
 * Authors : jpthibau
 * 
 * Copyright 2004 - INRIA - LGPL license
 */
package antlr2ASTJava;

/**
 * @author jpthibau
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
//import java.io.*;
import java.util.Arrays;
import java.util.Vector;

import ANTLRASTWalker.ANTLRWalkerActionsInterface;
import ANTLRParser.*;

import org.apache.log4j.Logger;
import org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.*;
import org.irisa.triskell.MT.utils.MessagesHandler.CompilerException;
import org.irisa.triskell.MT.utils.MessagesHandler.MSGHandler;
import org.irisa.triskell.MT.visitors.Java.AnalysingVisitor.Property;

public class antlr2ast implements ANTLRWalkerActionsInterface {
	static final Logger log=MSGHandler.init();

	private static Library theBuiltAST=null; //<<<Accumulation>>>
	private static String libraryName = null;

public Library buildLibraryFromText(String fileName)
{ return ((Library)BMTLParser.Parse(fileName,this)); }

public static void main(String[] args)
{	if (args.length > 0)
		for (int i=0;i<args.length;i++)
			new antlr2ast().buildLibraryFromText(args[i]);
	else log.error("USAGE : java BMTL <sourcefiles>");
}

/* usefull functions */
private java.util.Vector createVector(String value)
{	java.util.Vector qualifiedName=new java.util.Vector();
	qualifiedName.addElement(value);
	return qualifiedName; }

private void putProperty (ASTNode node,String name,Object value,String tagType)
{	if (value != null)
		node.createNewProperty(name,value,tagType);
}

private void putTags (ASTNode node,java.util.Vector tags)
{	for (int i=0;i<tags.size();i++) {
		java.util.Vector aTag=(java.util.Vector)tags.get(i);
		putProperty(node,(String)aTag.get(0),aTag.get(1),"tag");
	}
}

private Expression putPropertyCalls(Expression expr,java.util.Vector propertyCalls)
{	for (int i=0;i<propertyCalls.size();i++) {
	 Object o = propertyCalls.get(i);
	 if (o instanceof OperationCall) {
		 OperationCall op=(OperationCall)propertyCalls.get(i);
		 op.setCaller(expr);
		 expr=(Expression)op;
	 } else {//if (o instanceof OclAsType) {
	 	OclAsType oat = (OclAsType)o;
	 	oat.setExpression(expr);
	 	expr=oat;
	 }
}
return expr; }

/* ANTLRWalkerActionsInterface implemented functions */
public Object library(Object header,java.util.Vector models,java.util.Vector methods,java.util.Vector classes)
{	int i; //<<<Accumulation>>>
	if (antlr2ast.theBuiltAST ==null)
		antlr2ast.theBuiltAST=(Library)header;
	for(i=0;i<models.size();i++)
		((BasicMtlLibrary)antlr2ast.theBuiltAST).appendParameters((ModelRef)models.get(i)); 
	for(i=0;i<methods.size();i++)
	antlr2ast.theBuiltAST.appendDefinedOperations((Operation)methods.get(i)); 
	for(i=0;i<classes.size();i++)
	antlr2ast.theBuiltAST.appendDefinedClasses((UserClass)classes.get(i)); 
	return antlr2ast.theBuiltAST; }

public Object libraryHeader(String lineNumber,Object libHeader,java.util.Vector tags)
{	Library node=(Library)libHeader;
	putProperty(node,"LineNumber",lineNumber,"StringTag");
	putTags(node,tags);
	return node; }

public Object bmtllibraryHeader(Object libName,Object inheritance)
{	int i;
	java.util.Vector libNames=(java.util.Vector)libName;
	String libSurname=(String)libNames.get(0);
	libraryName = libSurname;
	BasicMtlLibrary node=new BasicMtlLibrary(libSurname);
	node.appendQualifiedName(libSurname);
	for (i=1;i < libNames.size();i++) {
		libSurname=libSurname.concat("_"+(String)libNames.get(i));
	node.appendQualifiedName((String)libNames.get(i));
	}
	node.setName(libSurname);
	putProperty(node,"Inheritance",inheritance,"SpecialTag");
	return node; }

public Object nativeLibHeader(Object libName)
{	int i;
	java.util.Vector libNames=(java.util.Vector)libName;
	String libSurname=(String)libNames.get(0);
	NativeLibrary node=new NativeLibrary(libSurname);
	node.appendQualifiedName(libSurname);
	for (i=1;i < libNames.size();i++) {
		libSurname=libSurname.concat("_"+(String)libNames.get(i));
	node.appendQualifiedName((String)libNames.get(i));
	}
	node.setName(libSurname);
	return node; }

public Object model(String lineNumber,String modelName,String viewName)
{	if (viewName != null) {
		TypedModelRef node=new TypedModelRef(modelName,viewName);
	putProperty(node,"LineNumber",lineNumber,"StringTag");
		return node;
	}
	else {
		RepositoryRef node=new RepositoryRef(modelName);
		putProperty(node,"LineNumber",lineNumber,"StringTag");
		return node;
	}
}
public Object associationDefinition(String lineNumber,Object associationName,java.util.Vector tags,java.util.Vector endPoints)
{	int i;
	java.util.Vector associationNames=(java.util.Vector)associationName;
	String associationSurname=(String)associationNames.get(0);
	for (i=1;i < associationNames.size();i++)
	associationSurname=associationSurname.concat("_"+(String)associationNames.get(i));
	Association node=new Association(associationSurname);
	for (i=0;i<endPoints.size();i++) 
		node.appendEndPoints((EndPoint)endPoints.get(i));
	putProperty(node,"LineNumber",lineNumber,"StringTag");
	putTags(node,tags);
	return node; }

public Object endPoint(String lineNumber,String roleName,String className,Object multiplicity,boolean isComposition,boolean isAggregation,boolean isOrdered,boolean isNavigable,java.util.Vector theTags)
{	EndPoint node= new EndPoint(roleName,className,isComposition,isAggregation,isOrdered,isNavigable);
	node.setMultiplicity((Multiplicity)multiplicity);
	putProperty(node,"LineNumber",lineNumber,"StringTag");
	return node;
}

public Object multiplicity (String lowerBound,String upperBound)
{	Multiplicity node=new Multiplicity(Integer.parseInt(lowerBound),Integer.parseInt(upperBound));
	return node;
}

public Object classDefinition(String lineNumber,Object className,Object inheritance,Object refinement,java.util.Vector tags,java.util.Vector attributes,java.util.Vector settersGetters,java.util.Vector methods)
{	int i,j;
	java.util.Vector classNames=(java.util.Vector)className;
	String classSurname=(String)classNames.get(0);
	if (classSurname.equals(libraryName)) {
		log.error("The class name cannot have the library name"+classSurname);
		classNames=new java.util.Vector();
		classNames.add(classSurname+classSurname);
	}
	UserClass node=new UserClass(classSurname);
	node.appendQualifiedName(classSurname);
	for (i=1;i < classNames.size();i++) {
		classSurname=classSurname.concat("_"+(String)classNames.get(i));
		node.appendQualifiedName((String)classNames.get(i));
	}
	node.setName(classSurname);
	for(i=0;i<attributes.size();i++) {
		java.util.Vector declaredAttributes=(java.util.Vector)attributes.get(i);
		for (j=0;j<declaredAttributes.size();j++)
			node.appendDefinedAttributes((Attribute)declaredAttributes.get(j)); 
	}
	for(i=0;i<methods.size();i++)
		node.appendDefinedMethods((Operation)methods.get(i)); 
	for(i=0;i<settersGetters.size();i++) {
		java.util.Vector theSetterGetter = (java.util.Vector)settersGetters.get(i);
		Attribute theAttribute=null;
		Operation theOperation=null;
		for(j=0;j<node.cardDefinedAttributes();j++)
			if (node.getDefinedAttributes(j).getName().equals((String)theSetterGetter.get(1)))
				theAttribute = node.getDefinedAttributes(j);
		for(j=0;j<node.cardDefinedMethods();j++)
			if (node.getDefinedMethods(j).getName().equals((String)theSetterGetter.get(2)))
				theOperation = node.getDefinedMethods(j);
		if (theAttribute == null | theOperation==null)
			log.error("Getter/Setter definition, attribute or operation does not exist :"+theSetterGetter.get(0)+" "+theSetterGetter.get(1)+" "+theSetterGetter.get(2));
		else {
			if (((Boolean)theSetterGetter.get(0)).booleanValue())
				{	theAttribute.setGetter(theOperation);
					theOperation.setIsGetterFor(theAttribute);
			}
			else {
					theAttribute.setSetter(theOperation);
					theOperation.setIsSetterFor(theAttribute);
				}
		}
	}
	putProperty(node,"Inheritance",inheritance,"SpecialTag");
	putProperty(node,"Refinement",refinement,"SpecialTag");
	putProperty(node,"LineNumber",lineNumber,"StringTag");
	putTags(node,tags);
	return node; }

public Object inheritance (Object typesList)
{	return typesList; }

public Object attribute(Object localVarDef,java.util.Vector tags)
{	java.util.Vector declaredVars=(java.util.Vector)localVarDef;
	java.util.Vector declaredAttributes=new java.util.Vector();
	for(int i=0;i<declaredVars.size();i++) {
		Attribute attrib=new Attribute(((VarDeclaration)declaredVars.get(i)).getName());
		attrib.appendDecoration(((ASTNode)declaredVars.get(i)).getDecoration(0));
		attrib.appendDecoration(((ASTNode)declaredVars.get(i)).getDecoration(1));
		putTags(attrib,tags);
		declaredAttributes.addElement(attrib);
	}
	return declaredAttributes; }

public Object setterGetter(boolean isGetter,String attributeName,String operationName)
{	java.util.Vector theSetterGetter=new java.util.Vector();
	theSetterGetter.addElement(new Boolean(isGetter));
	theSetterGetter.addElement(attributeName);
	theSetterGetter.addElement(operationName);
	return theSetterGetter;
}

public Object method(String creation,String methodName,String lineNumber,Object parameters,Object returnedType,String throwsException,java.util.Vector localVars,java.util.Vector instructions,java.util.Vector tags)
{	int i,j;
	java.util.Vector params=(java.util.Vector)parameters;
	Operation node=new Operation(methodName);
	if (parameters !=null) {
	for(i=0;i<params.size();i++) {
		java.util.Vector typedVars=(java.util.Vector)params.get(i);
		for(j=1;j<typedVars.size();j++) {
			VarDeclaration var=new VarDeclaration((String)typedVars.get(j),true);
			putProperty(var,"Type",typedVars.get(0),"SpecialTag");
			node.appendParameters(var); 
		}
	}
	}
	for(i=0;i<localVars.size();i++) {
		java.util.Vector declaredVars=(java.util.Vector)localVars.get(i);
		for(j=0;j<declaredVars.size();j++)
			node.appendDeclaredVariables((VarDeclaration)declaredVars.get(j)); 
	}
	for(i=0;i<instructions.size();i++)
		node.appendInstructions((Instruction)instructions.get(i)); 
	putProperty(node,"IsConstructor",creation,"StringTag");
	putProperty(node,"returnedType",returnedType == null ? new Vector(Arrays.asList(new String [] {"Standard", "Void"})) : returnedType,"SpecialTag");
	if (throwsException != null) node.setThrowsException(true);
	putProperty(node,"LineNumber",lineNumber,"StringTag");
	putTags(node,tags);
	return node; }

public Object varsDeclaration(Object typedVars,String lineNumber)
{	java.util.Vector declaredVars=new java.util.Vector();
	java.util.Vector typedVarsList=(java.util.Vector)typedVars;
	for(int i=1;i<typedVarsList.size();i++) {
		VarDeclaration var=new VarDeclaration((String)typedVarsList.get(i),false);
		putProperty(var,"Type",typedVarsList.get(0),"SpecialTag");
		putProperty(var,"LineNumber",lineNumber,"StringTag");
		declaredVars.addElement(var);
	}
	return declaredVars; }

public Object typedVars(java.util.Vector vars,Object type)
{	java.util.Vector type_vars=new java.util.Vector();
	type_vars.addElement(type);
	for(int i=0;i<vars.size();i++) {
		type_vars.addElement(vars.get(i));
	}
	return type_vars; }

public Object expressionInstr(Object expression,String lineNumber)
{	return (Instruction)expression; }

public Object affectation(Object sourceTree,Object destTree,String lineNumber) {
	ASTNode node;
	Property kindProp = ((ASTNode)destTree).getProperty("kind");
	if ((destTree instanceof OperationCall) && kindProp != null && kindProp.getValue().equals("AttributeGetter")) {
		node= (OperationCall)destTree;
		((OperationCall)node).setProperty("kind", "AttributeSetter");
		((OperationCall)node).appendArguments((Expression)sourceTree);
	} else if (destTree instanceof VarCall) {
		node= new VarSetting(((VarCall)destTree).getVarName(),(Expression)sourceTree);
	} else {
		log.error(lineNumber + ": Can just affect variable or attributes.");
		return null;
	}
	putProperty(node,"LineNumber",lineNumber,"StringTag");
	return node; }

public Object returnInstr(Object expression,String lineNumber)
{	Return node= new Return((Expression)expression);
	putProperty(node,"LineNumber",lineNumber,"StringTag");
	return node; }

public Object whileInstr(Object expression,Object body)
{	java.util.Vector instructions=(java.util.Vector)((java.util.Vector)body).get(0);
	String lineNumber=(String)((java.util.Vector)body).get(1);
	While node=new While((Expression)expression);
	for(int i=0;i<instructions.size();i++)
		node.appendBody((Instruction)instructions.get(i));
	putProperty(node,"LineNumber",lineNumber,"StringTag");
	return node; }

public Object ifInstr(Object expression,Object thenBody,Object elseBody)
{	int i;
	if (thenBody == null) {
		log.error("If body has to contain at least one instruction.");
		return null;
	}
	java.util.Vector thenInstructions=(java.util.Vector)((java.util.Vector)thenBody).get(0);
	java.util.Vector elseInstructions=elseBody == null ? null : (java.util.Vector)((java.util.Vector)elseBody).get(0);
	String lineNumber=(String)((java.util.Vector)thenBody).get(1);
	If node=new If((Expression)expression);
	for(i=0;i<thenInstructions.size();i++)
		node.appendThenBody((Instruction)thenInstructions.get(i));
	if (elseInstructions != null)
		for(i=0;i<elseInstructions.size();i++)
			node.appendElseBody((Instruction)elseInstructions.get(i));
	putProperty(node,"LineNumber",lineNumber,"StringTag");
	return node; }

public Object throwsInstr(Object expression,String lineNumber)
{	Throws node= new Throws((Expression)expression);
	putProperty(node,"LineNumber",lineNumber,"StringTag");
	return node; }

public Object tryInstr(Object tryBody,java.util.Vector catches,Object finBody)
{	int i;
	java.util.Vector instructions=(java.util.Vector)((java.util.Vector)tryBody).get(0);
	java.util.Vector finallyInstructions=(java.util.Vector)((java.util.Vector)finBody).get(0);
	String lineNumber=(String)((java.util.Vector)tryBody).get(0);
	Try node=new Try();
	for(i=0;i<instructions.size();i++)
		node.appendTryBody((Instruction)instructions.get(i));
	for(i=0;i<catches.size();i++) {
		java.util.Vector theCatch=(java.util.Vector)catches.get(i);
		String varName=(String)theCatch.get(0);
		java.util.Vector varType=(java.util.Vector)theCatch.get(1);
		java.util.Vector theCatchBody=(java.util.Vector)theCatch.get(2);
		java.util.Vector catchInstructions=(java.util.Vector)theCatchBody.get(0);
		String line=(String)theCatchBody.get(1);
		Catch aCatch=new Catch();
		VarDeclaration var=new VarDeclaration(varName,false);
		putProperty(var,"Type",varType,"SpecialTag");
		aCatch.setCatchedException(var);
		for(int j=0;j<catchInstructions.size();j++)
			aCatch.appendCatchBody((Instruction)catchInstructions.get(j));
		putProperty(aCatch,"LineNumber",(Object)line,"StringTag");
		node.appendCatchPart(aCatch);
	}
	for(i=0;i<finallyInstructions.size();i++)
		node.appendFinalizeBody((Instruction)finallyInstructions.get(i));
	putProperty(node,"LineNumber",lineNumber,"StringTag");
	return node; }

public Object associateInstr(boolean isAssociate,java.util.Vector endPoints,String lineNumber)
{	Associate node=new Associate(isAssociate);
	for(int i=0;i<endPoints.size();i++)
		node.appendRoles((Role)endPoints.get(i));
	putProperty(node,"LineNumber",lineNumber,"StringTag");
	return node; }

public Object associateEndPoint(String role,Object endObject,Object type)
{	Role node=new Role(role);
	node.setExpression((Expression)endObject);
	putProperty(node,"Type",type,"SpecialTag");
	return node; }

public Object bodyInstr(java.util.Vector instructions,String lineNumber)
{	java.util.Vector result=new java.util.Vector();
	result.addElement(instructions);
	result.addElement(lineNumber);
	return result; }

public Object newExpr(Object theClass,String methodName,Object arguments,String lineNumber,java.util.Vector propertyCalls)
{	java.util.Vector args=(java.util.Vector)arguments;
	NewObject node=new NewObject();
	if (arguments!=null)
		for(int i=0;i<args.size();i++)
			node.appendArguments((Expression)args.get(i));
	putProperty(node,"TypeToCreate",theClass,"SpecialTag");
	putProperty(node,"CreationMethod",methodName,"StringTag");
	putProperty(node,"LineNumber",lineNumber,"StringTag");
	return (Expression)putPropertyCalls(node,propertyCalls); }

public Object intLiteral(String value,java.util.Vector propertyCalls)
{	Expression expr=(Expression)new IntLiteral(Integer.parseInt(value));
	return (Expression)putPropertyCalls(expr,propertyCalls); }

public Object realLiteral(String value,java.util.Vector propertyCalls)
{	Expression expr=(Expression)new RealLiteral(Float.parseFloat(value));
	return (Expression)putPropertyCalls(expr,propertyCalls); }

public Object stringLiteral(String value,java.util.Vector propertyCalls)
{	Expression expr=(Expression)new StringLiteral(value);
	return (Expression)putPropertyCalls(expr,propertyCalls); }

public Object javaCodeLiteral(String value)
{	return (Expression)new JavaCodeLiteral(value);}
		
public Object oclTypeLiteral(Object type,java.util.Vector propertyCalls)
{	Expression expr=(Expression)new OclTypeLiteral((java.util.Vector)type);
	return (Expression)putPropertyCalls(expr,propertyCalls); }

public Object selfLiteral(java.util.Vector propertyCalls)
{	Expression expr=(Expression)new SelfLiteral();
	return (Expression)putPropertyCalls(expr,propertyCalls); }

public Object nullLiteral(java.util.Vector propertyCalls)
{	Expression expr=(Expression)new NullLiteral();
	return (Expression)putPropertyCalls(expr,propertyCalls); }

public Object trueLiteral(java.util.Vector propertyCalls)
{	Expression expr=(Expression)new BooleanLiteral(true);
	return (Expression)putPropertyCalls(expr,propertyCalls); }

public Object falseLiteral(java.util.Vector propertyCalls)
{	Expression expr=(Expression)new BooleanLiteral(false);
	return (Expression)putPropertyCalls(expr,propertyCalls); }

public Object attributeOrVariable(String name,java.util.Vector propertyCalls)
{	Expression expr;
	VarCall var = new VarCall(name);
	expr=(Expression) var;
	return (Expression)putPropertyCalls(expr,propertyCalls); }

public Object attributeGetter(String attributeName)
{	OperationCall ret = new OperationCall(attributeName);
	ret.createNewProperty("kind", "AttributeGetter", "stringtag");
	return ret; }

public Object variableName(String value)
{	return (Expression)new VarCall(value); }

public Object directOperationCalls(java.util.Vector propertyCalls)
{ return (Expression)putPropertyCalls(null,propertyCalls); }
	
public Object operationCall(String operationName,Object arguments,String lineNumber)
{	java.util.Vector args=(java.util.Vector)arguments;
	OperationCall node=new OperationCall(operationName);
	if (arguments !=null)
		for(int i=0;i<args.size();i++) {
			node.appendArguments((Expression)args.get(i));
		}
	putProperty(node,"LineNumber",lineNumber,"StringTag");
	return (Expression)node; }

public Object negateExpr(Object expr,String lineNumber)
{	OperationCall node=new OperationCall("not");
	putProperty(node,"LineNumber",lineNumber,"StringTag");
	return (Expression)node; }

public Object exprOpExpr(Object expr1,String operator,Object expr2,String lineNumber)
{	OperationCall node=new OperationCall(operator);
	node.appendArguments((Expression)expr2);
	node.setCaller((Expression)expr1);
	putProperty(node,"LineNumber",lineNumber,"StringTag");
	return (Expression)node; }

public Object oclAsType(Object type,String lineNumber,String theType,String theMethod,String theParameter,boolean isAConstant)
{	OclAsType node=new OclAsType();
	node.setIsAConstant(isAConstant);
	java.util.Vector qualifiers=(java.util.Vector)type;
	for(int i=0;i<qualifiers.size();i++)
		node.appendType((String)qualifiers.get(i));
	putProperty(node,"LineNumber",lineNumber,"StringTag");
	if (! isAConstant)
	{	node.setTypeVar(theType);
		node.setMethodVar(theMethod);
		node.setParameterVar(theParameter); }
	return (Expression)node; }

public Object arguments(java.util.Vector expressions)
{	java.util.Vector exprVector=new java.util.Vector();
	for(int i=0;i<expressions.size();i++)
		exprVector.addElement((Expression)expressions.get(i));
	return exprVector; }

public Object typeName(java.util.Vector nameQualifiers)
{ return nameQualifiers; }

public Object tag(String lineNumber,String tagName,Object taggedValue)
{	java.util.Vector tag=new java.util.Vector();
	((java.util.Vector)taggedValue).add(0,lineNumber);
	tag.addElement(tagName);
	tag.addElement(taggedValue);
	return tag; }

public Object boolTagValueTrue()
{	java.util.Vector tag=new java.util.Vector();
	tag.addElement("BooleanTag");
	tag.addElement("true");
	return tag; }

public Object boolTagValueFalse()
{	java.util.Vector tag=new java.util.Vector();
	tag.addElement("BooleanTag");
	tag.addElement("false");
	return tag; }

public Object intTagValue(String value)
{	java.util.Vector tag=new java.util.Vector();
	tag.addElement("IntTag");
	tag.addElement(value);
	return tag; }

public Object realTagValue(String value)
{	java.util.Vector tag=new java.util.Vector();
	tag.addElement("RealTag");
	tag.addElement(value);
	return tag; }

public Object stringTagValue(String value)
{	java.util.Vector tag=new java.util.Vector();
	tag.addElement("StringTag");
	tag.addElement(value);
	return tag; }

public Object specialTagValue(String value)
{	java.util.Vector tag=new java.util.Vector();
	tag.addElement("SpecialTag");
	tag.addElement(value);
	return tag; }

public Object foreachInstr(Object element, Object collection, Object condition, Object body) 
{
	return null;
}

}
