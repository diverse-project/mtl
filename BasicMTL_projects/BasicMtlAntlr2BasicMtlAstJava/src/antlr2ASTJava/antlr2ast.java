/*
 * $Header: /tmp/cvs2svn/cvsroot/BasicMTL_projects/BasicMtlAntlr2BasicMtlAstJava/src/antlr2ASTJava/antlr2ast.java,v 1.1 2003-07-17 16:08:06 jpthibau Exp $
 * Created on 16 juil. 2003
 *
 */
package antlr2ASTJava;

/**
 * @author jpthibau
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
import java.io.*;
import org.apache.log4j.Logger;
import org.apache.log4j.LogManager;
import org.apache.log4j.xml.DOMConfigurator;

import ANTLRASTWalker.ANTLRWalkerActionsInterface;
import ANTLRParser.*;
import org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.*;
import org.irisa.triskell.MT.visitors.Java.AnalysingVisitor.Property;

public class antlr2ast implements ANTLRWalkerActionsInterface {

	public static final org.apache.log4j.Logger log = Logger.getLogger("BMTLParser");

	public static org.apache.log4j.Logger getLog () {
			return BMTLParser.log;
	}

public Library buildLibraryFromText(String fileName)
{ return ((Library)BMTLParser.Parse(fileName,this)); }

public static void main(String[] args)
{	try {
		String filePath = new java.io.File("ThirdParty/log4j/log4j_configuration").getCanonicalPath();
		LogManager.resetConfiguration();
		DOMConfigurator.configure(filePath); }
	catch(java.io.IOException e) {
		System.err.println("Can't state log4j in BMTLParser"); }
	if (args.length > 0)
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

private Expression putOperationCalls(Expression expr,java.util.Vector operationCalls)
{	for (int i=0;i<operationCalls.size();i++) {
	 OperationCall op=(OperationCall)operationCalls.get(i);
	 op.setCaller(expr);
	 expr=(Expression)op;
}
return expr; }

/* ANTLRWalkerActionsInterface implemented functions */
public Object library(Object header,java.util.Vector models,java.util.Vector methods,java.util.Vector classes)
{	int i;
	Library node=(Library)header;
	if (node.isNative())
		if (models!=null) log.error("NativeLibrary should have no models !");
	else 	for(i=0;i<models.size();i++)
	((BasicMtlLibrary)node).appendParameters((ModelRef)models.get(i)); 

	for(i=0;i<methods.size();i++)
		node.appendDefinedOperations((Operation)methods.get(i)); 
	for(i=0;i<classes.size();i++)
		node.appendDefinedClasses((UserClass)classes.get(i)); 
	return node; }

public Object libraryHeader(String lineNumber,Object libHeader,java.util.Vector tags)
{	Library node=(Library)libHeader;
	putProperty(node,"LineNumber",lineNumber,"StringTag");
	putTags(node,tags);
	return node; }

public Object bmtllibraryHeader(String libName,Object inheritance)
{	BasicMtlLibrary node=new BasicMtlLibrary(libName);
	putProperty(node,"Inheritance",inheritance,"SpecialTag");
	return node; }

public Object nativeLibHeader(String libName)
{	NativeLibrary node=new NativeLibrary(libName);
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
	
public Object classDefinition(String lineNumber,String className,Object inheritance,java.util.Vector tags,java.util.Vector attributes,java.util.Vector methods)
{	int i;
	UserClass node=new UserClass(className);
	for(i=0;i<attributes.size();i++)
		node.appendDefinedAttributes((Attribute)attributes.get(i)); 
	for(i=0;i<methods.size();i++)
		node.appendDefinedMethods((Operation)methods.get(i)); 
	putProperty(node,"Inheritance",inheritance,"SpecialTag");
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
	putProperty(node,"returnedType",returnedType,"SpecialTag");
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

public Object varSettingInstr(String classVarName,String varOrAttributeName,Object expression,String lineNumber)
{	VarSetting node= new VarSetting(varOrAttributeName,(Expression)expression);
	putProperty(node,"ClassOfSetAttribute",(Object)classVarName,"StringTag");
	putProperty(node,"LineNumber",lineNumber,"StringTag");
	return node; }

public Object returnInstr(Object expression,String lineNumber)
{	Return node= new Return((Expression)expression);
	putProperty(node,"LineNumber",lineNumber,"StringTag");
	return node; }

public Object whileInstr(Object expression,java.util.Vector instructions,String lineNumber)
{	While node=new While((Expression)expression);
	for(int i=0;i<instructions.size();i++)
		node.appendBody((Instruction)instructions.get(i));
	putProperty(node,"LineNumber",lineNumber,"StringTag");
	return node; }

public Object ifInstr(Object expression,java.util.Vector thenInstructions,java.util.Vector elseInstructions,String lineNumber)
{	int i;
	If node=new If((Expression)expression);
	for(i=0;i<thenInstructions.size();i++)
		node.appendThenBody((Instruction)thenInstructions.get(i));
	for(i=0;i<elseInstructions.size();i++)
		node.appendElseBody((Instruction)elseInstructions.get(i));
	putProperty(node,"LineNumber",lineNumber,"StringTag");
	return node; }

public Object throwsInstr(Object expression,String lineNumber)
{	Throws node= new Throws((Expression)expression);
	putProperty(node,"LineNumber",lineNumber,"StringTag");
	return node; }

public Object tryInstr(java.util.Vector instructions,java.util.Vector catches,java.util.Vector finallyInstructions,String lineNumber)
{	int i;
	Try node=new Try();
	for(i=0;i<instructions.size();i++)
		node.appendTryBody((Instruction)instructions.get(i));
	for(i=0;i<catches.size();i++) {
		java.util.Vector theCatch=(java.util.Vector)catches.get(i);
		String varName=(String)theCatch.get(0);
		java.util.Vector varType=(java.util.Vector)theCatch.get(1);
		String line=(String)theCatch.get(2);
		java.util.Vector catchInstructions=(java.util.Vector)theCatch.get(3);
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

public Object associateInstr(java.util.Vector endPoints,String lineNumber)
{	Associate node=new Associate();
	for(int i=0;i<endPoints.size();i++)
		node.appendRoles((Role)endPoints.get(i));
	putProperty(node,"LineNumber",lineNumber,"StringTag");
	return node; }

public Object associateEndPoint(String role,Object endObject,Object type)
{	Role node=new Role(role);
	node.setExpression((Expression)endObject);
	putProperty(node,"Type",type,"SpecialTag");
	return node; }

public Object newExpr(Object theClass,String methodName,Object arguments,String lineNumber,java.util.Vector operationCalls)
{	java.util.Vector args=(java.util.Vector)arguments;
	NewObject node=new NewObject();
	if (arguments!=null)
		for(int i=0;i<args.size();i++)
			node.appendArguments((Expression)args.get(i));
	putProperty(node,"TypeToCreate",theClass,"SpecialTag");
	putProperty(node,"CreationMethod",methodName,"StringTag");
	putProperty(node,"LineNumber",lineNumber,"StringTag");
	return (Expression)node; }

public Object intLiteral(String value,java.util.Vector operationCalls)
{	Expression expr=(Expression)new IntLiteral(Integer.parseInt(value));
	return (Expression)putOperationCalls(expr,operationCalls); }

public Object realLiteral(String value,java.util.Vector operationCalls)
{	Expression expr=(Expression)new RealLiteral(Float.parseFloat(value));
	return (Expression)putOperationCalls(expr,operationCalls); }

public Object stringLiteral(String value,java.util.Vector operationCalls)
{	Expression expr=(Expression)new StringLiteral(value);
	return (Expression)putOperationCalls(expr,operationCalls); }

public Object selfLiteral(java.util.Vector operationCalls)
{	Expression expr=(Expression)new SelfLiteral();
	return (Expression)putOperationCalls(expr,operationCalls); }

public Object nullLiteral(java.util.Vector operationCalls)
{	Expression expr=(Expression)new NullLiteral();
	return (Expression)putOperationCalls(expr,operationCalls); }

public Object trueLiteral(java.util.Vector operationCalls)
{	Expression expr=(Expression)new BooleanLiteral(true);
	return (Expression)putOperationCalls(expr,operationCalls); }

public Object falseLiteral(java.util.Vector operationCalls)
{	Expression expr=(Expression)new BooleanLiteral(false);
	return (Expression)putOperationCalls(expr,operationCalls); }

public Object libraryOrVariable(Object type,java.util.Vector operationCalls)
{	VarCall var = new VarCall((java.util.Vector)type);
	Expression expr=(Expression) var;
	return (Expression)putOperationCalls(expr,operationCalls); }

public Object attributeGetter(String classVarName,java.util.Vector gotAttributes)
{	VarCall var = new VarCall(createVector(classVarName));
	Expression expr=(Expression)var;
	for (int i=0;i<gotAttributes.size();i++) {
	     OperationCall op=new OperationCall("get_"+gotAttributes.get(i));
	     op.setCaller(expr);
	     expr=(Expression)op;
	}
	return expr; }

public Object variableName(String value)
{	return (Expression)new VarCall(createVector(value)); }

public Object directOperationCalls(java.util.Vector operationCalls)
{ return (Expression)putOperationCalls(null,operationCalls); }
	
public Object operationCall(String operationName,Object arguments,String lineNumber)
{	java.util.Vector args=(java.util.Vector)arguments;
	OperationCall node=new OperationCall(operationName);
	if (arguments !=null)
		for(int i=0;i<args.size();i++) {
			node.appendArguments((Expression)args.get(i));
		}
	putProperty(node,"LineNumber",lineNumber,"StringTag");
	return (Expression)node; }

public Object oclAsType(Object type,String lineNumber)
{	java.util.Vector qualifiers=(java.util.Vector)type;
	OperationCall node=new OperationCall("oclAsType");
	for(int i=0;i<qualifiers.size();i++)
		node.appendArguments((Expression)new StringLiteral((String)qualifiers.get(i)));
	putProperty(node,"LineNumber",lineNumber,"StringTag");
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

}
