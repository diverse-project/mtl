/*
 * $Header: /tmp/cvs2svn/cvsroot/BasicMTL_projects/BasicMtlAntlr/ANTLRASTBuilder/ANTLRASTBuilderInterface.java,v 1.2 2003-07-10 09:17:03 dvojtise Exp $
 * 
 * @TODO file and package must be renamed in BasicmtlTreeWalkerActions as this interface defines the actions called by the visitor BasicmtlTreeWalker
 */
package ANTLRASTBuilder;

public interface ANTLRASTBuilderInterface {

public Object library(Object header,java.util.Vector models,java.util.Vector methods,java.util.Vector classes);

public Object libraryHeader(String lineNumber,String libName,Object inheritance,java.util.Vector tags);

public Object nativeLibHeader(String lineNumber,String libName,java.util.Vector tags);

public Object model(String lineNumber,String modelName,String viewName);

public Object classDefinition(String lineNumber,String className,Object inheritance,java.util.Vector tags,java.util.Vector attributes,java.util.Vector methods);

public Object inheritance (Object typesList);

public Object attribute(Object locaVarDef,java.util.Vector tags);

public Object method(String creation,String methodName,String lineNumber,Object parameters,Object returnedType,String throwsException,java.util.Vector localVars,java.util.Vector instructions,java.util.Vector tags);

public Object varsDeclaration(Object typedVars,String lineNumber);

public Object typedVars(java.util.Vector vars,Object type);

public Object expressionInstr(Object expression,String lineNumber);

public Object varSettingInstr(String varName,Object expression,String lineNumber);

public Object returnInstr(Object expression,String lineNumber);

public Object whileInstr(Object expression,java.util.Vector instructions,String lineNumber);

public Object ifInstr(Object expression,java.util.Vector thenInstructions,java.util.Vector elseInstructions,String lineNumber);

public Object throwsInstr(Object expression,String lineNumber);

public Object tryInstr(java.util.Vector instructions,java.util.Vector catches,java.util.Vector finallyInstructions,String lineNumber);

public Object associateInstr(java.util.Vector endPoints,String lineNumber);

public Object associateEndPoint(String role,String endObjectName,Object type);

public Object newExpr(Object theClass,String methodName,Object arguments,String lineNumber);

public Object intLiteral(String value);

public Object realLiteral(String value);

public Object stringLiteral(String value);

public Object selfLiteral();

public Object nullLiteral();

public Object trueLiteral();

public Object falseLiteral();

public Object variableName(String value);

public Object expression(Object expression,java.util.Vector calledOperations);

public Object operationCall(String operationName,Object arguments,String lineNumber);

public Object oclAsType(Object type,String lineNumber);

public Object arguments(java.util.Vector expressions);

public Object typeName(java.util.Vector nameQualifiers);

public Object tag(String lineNumber,String tagName,Object taggedValue);

public Object boolTagValueTrue();

public Object boolTagValueFalse();

public Object intTagValue(String value);

public Object realTagValue(String value);

public Object stringTagValue(String value);

public Object specialTagValue(String value);

}