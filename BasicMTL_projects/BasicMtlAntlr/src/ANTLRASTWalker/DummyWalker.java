//$Id: DummyWalker.java,v 1.8 2003-12-08 11:13:16 jpthibau Exp $

package ANTLRASTWalker;

public class DummyWalker implements ANTLRWalkerActionsInterface {

	public Object library(Object header,java.util.Vector models,java.util.Vector methods,java.util.Vector classes) { return null; }

	public Object libraryHeader(String lineNumber,Object libHeader,java.util.Vector tags) {return null; }

	public Object bmtllibraryHeader(Object libName,Object inheritance) {return null; }

	public Object nativeLibHeader(Object libName) {return null; }

	public Object model(String lineNumber,String modelName,String viewName) { return null; }

	public Object associationDefinition(String lineNumber,Object associationName,java.util.Vector tags,java.util.Vector endPoints) { return null; } //+++Version1.1+++

	public Object endPoint(String lineNumber,String roleName,String className,Object multiplicity,boolean isComposition,boolean isAggregation,boolean isOrdered,boolean isNavigable,java.util.Vector theTags) { return null; } //+++Version1.1+++

	public Object multiplicity (String lowerBound,String upperBound) { return null; } //+++Version1.1+++

	public Object classDefinition(String lineNumber,Object className,Object inheritance,Object refinement,java.util.Vector tags,java.util.Vector attributes,java.util.Vector gettersSetters,java.util.Vector methods) { return null; } //+++Version1.1+++ Object refinement, Vector gettersSetters

	public Object inheritance (Object typesList) { return null; }

	public Object attribute(Object localVarDef,java.util.Vector tags) { return null; }

	public Object setterGetter(boolean isGetter,String AttributeName,String operationName) { return null; }//+++Version1.1+++

	public Object method(String creation,String methodName,String lineNumber,Object parameters,Object returnedType,String throwsException,java.util.Vector localVars,java.util.Vector instructions,java.util.Vector tags) { return null; }

	public Object varsDeclaration(Object typedVars,String lineNumber) { return null; }

	public Object typedVars(java.util.Vector vars,Object type) { return null; }

	public Object expressionInstr(Object expression,String lineNumber) { return null; }

	public Object affectation(Object sourceTree,Object destTree, String line) { return null; }

	public Object returnInstr(Object expression,String lineNumber) { return null; }

	public Object whileInstr(Object expression,Object body) { return null; }

	public Object ifInstr(Object expression,Object thenBody,Object elseBody) { return null; }

	public Object throwsInstr(Object expression,String lineNumber) { return null; }

	public Object tryInstr(Object tryBody,java.util.Vector catches,Object finBody) { return null; }

	public Object associateInstr(boolean isAssociate,java.util.Vector endPoints,String lineNumber) { return null; }

	public Object associateEndPoint(String role,Object endObject,Object type) { return null; }

	public Object bodyInstr(java.util.Vector instructions,String lineNumber) { return null; }

	public Object newExpr(Object theClass,String methodName,Object arguments,String lineNumber,java.util.Vector operationCalls) { return null; }

	public Object intLiteral(String value,java.util.Vector operationCalls) { return null; }

	public Object realLiteral(String value,java.util.Vector operationCalls) { return null; }

	public Object stringLiteral(String value,java.util.Vector operationCalls) { return null; }

	public Object javaCodeLiteral(String value) { return null; }

	public Object oclTypeLiteral(Object type,java.util.Vector operationCalls) { return null; }

	public Object selfLiteral(java.util.Vector operationCalls) { return null; }

	public Object nullLiteral(java.util.Vector operationCalls) { return null; }

	public Object trueLiteral(java.util.Vector operationCalls) { return null; }

	public Object falseLiteral(java.util.Vector operationCalls) { return null; }

	public Object attributeOrVariable(String name,java.util.Vector operationCalls) { return null; }

	public Object variableName(String value) { return null; }
	
	public Object negateExpr(Object expr,String lineNumber) { return null; }

	public Object exprOpExpr(Object expr1,String operator,Object expr2,String lineNumber) { return null; }

	public Object directOperationCalls(java.util.Vector theCalls) { return null; }
	
	public Object operationCall(String operationName,Object arguments,String lineNumber) { return null; }

	public Object oclAsType(Object type,String lineNumber,String theType,String theMethod,String theParameter,boolean isAConstant) { return null; }

	public Object arguments(java.util.Vector expressions) { return null; }

	public Object typeName(java.util.Vector nameQualifiers) { return null; }

	public Object tag(String lineNumber,String tagName,Object taggedValue) { return null; }

	public Object boolTagValueTrue() { return null; }

	public Object boolTagValueFalse() { return null; }

	public Object intTagValue(String value) { return null; }

	public Object realTagValue(String value) { return null; }

	public Object stringTagValue(String value) { return null; }

	public Object specialTagValue(String value) { return null; }

	public Object attributeGetter(String attributeName) {return null;}

}