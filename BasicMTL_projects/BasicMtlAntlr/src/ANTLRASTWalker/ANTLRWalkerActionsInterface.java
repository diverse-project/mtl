/*
 * $Id: ANTLRWalkerActionsInterface.java,v 1.10 2004-04-21 18:15:27 edrezen Exp $
 * Authors : jpthibau
 * 
 * Copyright 2004 - INRIA - LGPL license
 */
package ANTLRASTWalker;

/**
 * Defines the actions that the ANTLR parser fires.
 */
public interface ANTLRWalkerActionsInterface {

public Object library(Object header,java.util.Vector models,java.util.Vector methods,java.util.Vector classes);

public Object libraryHeader(String lineNumber,Object libHeader,java.util.Vector tags);

public Object bmtllibraryHeader(Object libName,Object inheritance);

public Object nativeLibHeader(Object libName);

public Object model(String lineNumber,String modelName,String viewName);

public Object associationDefinition(String lineNumber,Object associationName,java.util.Vector tags,java.util.Vector endPoints); //+++Version1.1+++

public Object endPoint(String lineNumber,String roleName,String className,Object multiplicity,boolean isComposition,boolean isAggregation,boolean isOrdered,boolean isNavigable,java.util.Vector theTags); //+++Version1.1+++

public Object multiplicity (String lowerBound,String upperBound); //+++Version1.1+++

public Object classDefinition(String lineNumber,Object className,Object inheritance,Object refinement,java.util.Vector tags,java.util.Vector attributes,java.util.Vector gettersSetters,java.util.Vector methods); //+++Version1.1+++ Object refinement, Vector gettersSetters

public Object inheritance (Object typesList);

public Object attribute(Object localVarDef,java.util.Vector tags);

public Object setterGetter(boolean isGetter,String AttributeName,String operationName);//+++Version1.1+++

public Object method(String creation,String methodName,String lineNumber,Object parameters,Object returnedType,String throwsException,java.util.Vector localVars,java.util.Vector instructions,java.util.Vector tags);

public Object varsDeclaration(Object typedVars,String lineNumber);

public Object typedVars(java.util.Vector vars,Object type);

public Object expressionInstr(Object expression,String lineNumber);

public Object affectation(Object sourceTree,Object destTree,String lineNumber);

public Object returnInstr(Object expression,String lineNumber);

public Object whileInstr(Object expression,Object body);

public Object ifInstr(Object expression,Object thenBody,Object elseBody);

public Object throwsInstr(Object expression,String lineNumber);

public Object tryInstr(Object tryBody,java.util.Vector catches,Object finBody);

public Object associateInstr(boolean isAssociate,java.util.Vector endPoints,String lineNumber);

public Object bodyInstr(java.util.Vector instructions,String lineNumber);

public Object associateEndPoint(String role,Object endObject,Object type);

public Object newExpr(Object theClass,String methodName,Object arguments,String lineNumber,java.util.Vector propertyCalls);

public Object intLiteral(String value,java.util.Vector propertyCalls);

public Object realLiteral(String value,java.util.Vector propertyCalls);

public Object stringLiteral(String value,java.util.Vector propertyCalls);

public Object javaCodeLiteral(String value);

public Object oclTypeLiteral(Object type,java.util.Vector propertyCalls);

public Object selfLiteral(java.util.Vector propertyCalls);

public Object nullLiteral(java.util.Vector propertyCalls);

public Object trueLiteral(java.util.Vector propertyCalls);

public Object falseLiteral(java.util.Vector propertyCalls);

public Object attributeOrVariable(String name,java.util.Vector propertyCalls);

public Object attributeGetter(String attributeName);

public Object variableName(String value);

/**
 * called when a not is parsed
 * @param expr : expression to negate
 * @param lineNumber
 * @return Object : created node
 */
public Object negateExpr(Object expr,String lineNumber);

/**
 * called when parsing an operator in an expression
 * @param expr1 : first sub expression
 * @param operator
 * @param expr2 : second sub expression
 * @param lineNumber
 * @return Object
 */
public Object exprOpExpr(Object expr1,String operator,Object expr2,String lineNumber);

public Object directOperationCalls(java.util.Vector theCalls);

public Object operationCall(String operationName,Object arguments,String lineNumber);

public Object oclAsType(Object type,String lineNumber,String theType,String theMethod,String theParameter,boolean isAConstant);

public Object arguments(java.util.Vector expressions);

public Object typeName(java.util.Vector nameQualifiers);

public Object tag(String lineNumber,String tagName,Object taggedValue);

public Object boolTagValueTrue();

public Object boolTagValueFalse();

public Object intTagValue(String value);

public Object realTagValue(String value);

public Object stringTagValue(String value);

public Object specialTagValue(String value);

public Object foreachInstr (Object element, Object collection, Object body);
}