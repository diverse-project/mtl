/*
 * $Header: /tmp/cvs2svn/cvsroot/BasicMTL_projects/BasicMtlAntlr2ASTViewAssociation/src/antlr2ASTView/antlr2astView.java,v 1.7 2004-04-06 07:52:36 dvojtise Exp $
 * Created on 16 juil. 2003
 *
 * Copyright 2004 - INRIA - LGPL license
 */
package antlr2ASTView;


// import java.io.*;
// import java.util.Arrays;
// import java.util.Collection;
// import java.util.Vector;

import org.apache.log4j.Logger;
import org.apache.log4j.LogManager;
import org.apache.log4j.xml.DOMConfigurator;

import ANTLRASTWalker.ANTLRWalkerActionsInterface;
import ANTLRParser.*;
//import BasicMtlASTView.BMTL_ASTNodeInterface;
import BasicMtlASTWithAssociationView.*;

import org.irisa.triskell.MT.DataTypes.Java.commands.*;
import org.irisa.triskell.MT.DataTypes.Java.defaultImpl.*;
import org.irisa.triskell.MT.utils.Java.Directories;
//import org.irisa.triskell.MT.visitors.Java.AnalysingVisitor.Property;
import org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface;
import org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLSetInterface;
import org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface;
import org.irisa.triskell.MT.BasicMTL.DataTypes.impl.BMTLBoolean;
import org.irisa.triskell.MT.BasicMTL.DataTypes.impl.BMTLInteger;
import org.irisa.triskell.MT.BasicMTL.DataTypes.impl.BMTLOrderedSet;
import org.irisa.triskell.MT.BasicMTL.DataTypes.impl.BMTLReal;
//import org.irisa.triskell.MT.BasicMTL.DataTypes.impl.BMTLSequence;
import org.irisa.triskell.MT.BasicMTL.DataTypes.impl.BMTLSet;
import org.irisa.triskell.MT.BasicMTL.DataTypes.impl.BMTLString;
import org.irisa.triskell.MT.DataTypes.Java.Value;
//import org.irisa.triskell.MT.BasicMTL.TopTypes.*;

/**
 * Implements an ANTLR walker action in order to create a BasicMTL AST from a text file with mtl syntax
 * @author jpthibau
 */
public class antlr2astView implements ANTLRWalkerActionsInterface {

	public static final org.apache.log4j.Logger log = Logger.getLogger("BMTLParser");

	public static org.apache.log4j.Logger getLog () {
			return BMTLParser.log;
	}
	private static BMTLLib_BasicMtlASTWithAssociationView theCreatedLib;
	private BMTL_LibraryInterface theBuiltAST=null;
	private boolean hasInheritance=false;
	private boolean hasAssociation=false;
	private String currentFile;

public BMTL_LibraryInterface buildLibraryFromText(String fileName)
{ 
	// save the origin filename for traceability
	currentFile = fileName;
	return ((BMTL_LibraryInterface)BMTLParser.Parse(fileName,this)); 
}

public static void main(String[] args)
{	try {
		String filePath = new java.io.File(Directories.getRootPath(antlr2astView.class.getName()) + "/log4j_configuration.xml").getCanonicalPath();
		LogManager.resetConfiguration();
		DOMConfigurator.configure(filePath); }
	catch(java.io.IOException e) {
		System.err.println("Can't state log4j in BMTLParser"); }
	if (args.length > 0)
		for (int i=0;i<args.length;i++)
			new antlr2astView().buildLibraryFromText(args[i]);
	else log.error("USAGE : java BMTL <sourcefiles>");
}

/* usefull functions */
private java.util.Vector createVector(String value)
{	java.util.Vector qualifiedName=new java.util.Vector();
	qualifiedName.addElement(value);
	return qualifiedName; }

private BMTLOrderedSetInterface BMTLTypeConverter(java.util.Vector value)
{	Value [] BMTLType=new Value[value.size()];
	for (int i=0;i<value.size();i++)
		BMTLType[i]=(Value)new BMTLString((String)value.get(i));
	return new BMTLOrderedSet(BMTLType);
}

private BMTLSetInterface BMTLInheritanceConverter(java.util.Vector value)
{	Value [] BMTLInheritance=new Value[value.size()];
	for (int i=0;i<value.size();i++)
		BMTLInheritance[i]=(Value)BMTLTypeConverter((java.util.Vector)value.get(i));
	return new BMTLSet(BMTLInheritance);
}

private void putProperty (BMTL_ASTNodeInterface node,BMTLStringInterface name,Object value,String tagType) throws Throwable
{	if (value != null) {
		if (tagType.equals("StringTag")) {
			node.BMTL_createNewStringProperty(name,(BMTLStringInterface)value,new BMTLString(tagType));
			return;
		}
		if (tagType.equals("TypeTag")) {
			BMTLOrderedSetInterface theType=BMTLTypeConverter((java.util.Vector)value);
			node.BMTL_createNewBMTLTypeProperty(name,theType,new BMTLString(tagType));
			return;
		}
		if (tagType.equals("InheritanceTag")) {
			BMTLSetInterface theInheritance=BMTLInheritanceConverter((java.util.Vector)value);
			node.BMTL_createNewInheritanceProperty(name,theInheritance,new BMTLString(tagType));
			return;
		}
		if (tagType.equals("Tag")) {
			//Suppose "value" is a Vector of strings like ("linenumber" "SpecialTag" "String value")
			BMTLOrderedSetInterface theType=BMTLTypeConverter((java.util.Vector)value);
			node.BMTL_createNewBMTLTypeProperty(name,theType,new BMTLString(tagType));
			return;
		}
		log.error("PutProperty on an unknown tagType"+tagType);
	}
}

private void putTags (BMTL_ASTNodeInterface node,java.util.Vector tags) throws Throwable
{	for (int i=0;i<tags.size();i++) {
		java.util.Vector aTag=(java.util.Vector)tags.get(i);
		putProperty(node,new BMTLString((String)aTag.get(0)),aTag.get(1),"Tag");
	}
}

private BMTL_ExpressionInterface putPropertyCalls(BMTL_ExpressionInterface expr,java.util.Vector propertyCalls)
{	for (int i=0;i<propertyCalls.size();i++) {
	 Object o = propertyCalls.get(i);
	 if (o instanceof BMTL_OperationCall) {
		 BMTL_OperationCall op=(BMTL_OperationCall)propertyCalls.get(i);
		 op.set_BMTL_caller(expr);
		 expr=(BMTL_ExpressionInterface)op;
	 } else {//if (o instanceof OclAsType) {
	 	BMTL_OclAsType oat = (BMTL_OclAsType)o;
	 	oat.set_BMTL_expression(expr);
	 	expr=(BMTL_ExpressionInterface)oat;
	 }
}
return expr; }

/*---------------------------------------------------*/
/* ANTLRWalkerActionsInterface implemented functions */
/*---------------------------------------------------*/
public Object library(Object header,java.util.Vector models,java.util.Vector methods,java.util.Vector classes)
{	int i;
//	BMTL_LibraryInterface node=(BMTL_LibraryInterface)header;
	if (this.theBuiltAST==null)
	this.theBuiltAST=(BMTL_LibraryInterface)header;
	try {
	for(i=0;i<models.size();i++)
		((BMTL_BasicMtlLibraryInterface)this.theBuiltAST).BMTL_appendParameters((BMTL_ModelRefInterface)models.get(i)); 
//		((BMTL_BasicMtlLibraryInterface)node).BMTL_appendParameters((BMTL_ModelRefInterface)models.get(i)); 
	for(i=0;i<methods.size();i++)
		this.theBuiltAST.BMTL_appendDefinedOperations((BMTL_OperationInterface)methods.get(i)); 
//		node.BMTL_appendDefinedOperations((BMTL_OperationInterface)methods.get(i)); 
	for(i=0;i<classes.size();i++)
		this.theBuiltAST.BMTL_appendDefinedClasses((BMTL_UserClassInterface)classes.get(i)); 
//		node.BMTL_appendDefinedClasses((BMTL_UserClassInterface)classes.get(i)); 
	} catch (Throwable e) {e.printStackTrace();}
	this.theBuiltAST.set_BMTL_hasInheritance(new BMTLBoolean(this.hasInheritance));
	this.theBuiltAST.set_BMTL_hasAssociation(new BMTLBoolean(this.hasAssociation));
	return this.theBuiltAST; }
//	return node; }

public Object libraryHeader(String lineNumber,Object libHeader,java.util.Vector tags)
{//test => to remove	BMTL_BasicMtlLibraryInterface BMLInode=(BMTL_BasicMtlLibraryInterface)libHeader;
//	test => to remove	BasicMtlASTView.BMTL_LibraryInterface BLInode=BMLInode.getRef_BMTL_BasicMtlASTView_5fLibrary();
	BMTL_LibraryInterface node=(BMTL_LibraryInterface)libHeader;
	try {
	putProperty((BMTL_ASTNodeInterface)node,new BMTLString("LineNumber"),new BMTLString(lineNumber),"StringTag");
	putTags((BMTL_ASTNodeInterface)node,tags);
	} catch (Throwable e) {e.printStackTrace();}
	return node; }

public Object bmtllibraryHeader(Object libName,Object inheritance)
{	int i;
	theCreatedLib = new BMTLLib_BasicMtlASTWithAssociationView();
	java.util.Vector libNames=(java.util.Vector)libName;
	String libSurname=(String)libNames.get(0);
	BMTLOrderedSetInterface qn=BMTLTypeConverter(libNames);;
	for (i=1;i < libNames.size();i++) {
		libSurname=libSurname.concat("_"+(String)libNames.get(i));
	}
	BMTL_BasicMtlLibrary node=(BMTL_BasicMtlLibrary)((InstanciableType)theCreatedLib.getMetaClass(new String [] {"BasicMtlLibrary"})).instanciate();
	node.set_BMTL_name(new BMTLString(libSurname));
	node.set_BMTL_QualifiedName(qn);
	if (inheritance != null
		&& ((java.util.Vector)inheritance).size() > 0) this.hasInheritance =true;
	try {
	putProperty((BMTL_ASTNodeInterface)node,new BMTLString("Inheritance"),(java.util.Vector)inheritance,"InheritanceTag");
	} catch (Throwable e) {e.printStackTrace();}
	return node; }

public Object nativeLibHeader(Object libName)
{	//NativeLibrary node=new NativeLibrary(libName);
	//return node;
	try {
	throw new Exception("No more implemented");
	} catch (Throwable e) {e.printStackTrace();}
	return null; }

public Object model(String lineNumber,String modelName,String viewName)
{	if (viewName != null) {
		BMTL_TypedModelRef node=(BMTL_TypedModelRef)((InstanciableType)theCreatedLib.getMetaClass(new String [] {"TypedModelRef"})).instanciate();
		node.set_BMTL_name(new BMTLString(modelName));
		node.set_BMTL_typeName(new BMTLString(viewName));
		try {
		putProperty((BMTL_ASTNodeInterface)node,new BMTLString("LineNumber"),new BMTLString(lineNumber),"StringTag");
		} catch (Throwable e) {e.printStackTrace();}
		return node;
	}
	else {
		BMTL_RepositoryRef node=(BMTL_RepositoryRef)((InstanciableType)theCreatedLib.getMetaClass(new String [] {"RepositoryRef"})).instanciate();
		node.set_BMTL_name(new BMTLString(modelName));
		try {
		putProperty((BMTL_ASTNodeInterface)node,new BMTLString("LineNumber"),new BMTLString(lineNumber),"StringTag");
		} catch (Throwable e) {e.printStackTrace();}
		return node;
	}
}

public Object associationDefinition(String lineNumber,Object associationName,java.util.Vector tags,java.util.Vector endPoints)
{	int i;
	java.util.Vector associationNames=(java.util.Vector)associationName;
	String associationSurname=associationNames == null ? null : (String)associationNames.get(0);
	if (associationNames != null)
		for (i=1;i < associationNames.size();i++)
	associationSurname=associationSurname.concat("_"+(String)associationNames.get(i));
	BMTL_Association node=(BMTL_Association)((InstanciableType)theCreatedLib.getMetaClass(new String [] {"Association"})).instanciate();
	node.set_BMTL_name(new BMTLString(associationSurname));
	try {
	for (i=0;i<endPoints.size();i++) 
		node.BMTL_appendEndPoints((BMTL_EndPointInterface)endPoints.get(i));
	putProperty((BMTL_ASTNodeInterface)node,new BMTLString("LineNumber"),new BMTLString(lineNumber),"StringTag");
	putTags((BMTL_ASTNodeInterface)node,tags);
	} catch (Throwable e) {e.printStackTrace();}
	this.hasAssociation = true;
	return node;
}

public Object endPoint(String lineNumber,String roleName,String className,Object multiplicity,boolean isComposition,boolean isAggregation,boolean isOrdered,boolean isNavigable,java.util.Vector theTags)
{	BMTL_EndPoint node=(BMTL_EndPoint)((InstanciableType)theCreatedLib.getMetaClass(new String [] {"EndPoint"})).instanciate();
	node.set_BMTL_roleName(new BMTLString(roleName));
	node.set_BMTL_className(new BMTLString(className));
	node.set_BMTL_isComposition(new BMTLBoolean(isComposition));
	node.set_BMTL_isAggregation(new BMTLBoolean(isAggregation));
	node.set_BMTL_isOrdered(new BMTLBoolean(isOrdered));
	node.set_BMTL_isNavigable(new BMTLBoolean(isNavigable));
	node.set_BMTL_multiplicity((BMTL_MultiplicityInterface)multiplicity);
	try {
	putProperty((BMTL_ASTNodeInterface)node,new BMTLString("LineNumber"),new BMTLString(lineNumber),"StringTag");
	} catch (Throwable e) {e.printStackTrace();}
	return node;
}

public Object multiplicity (String lowerBound,String upperBound)
{	BMTL_Multiplicity node=(BMTL_Multiplicity)((InstanciableType)theCreatedLib.getMetaClass(new String [] {"Multiplicity"})).instanciate();
	node.set_BMTL_lowerBound(new BMTLInteger(Integer.parseInt(lowerBound)));
	node.set_BMTL_upperBound(new BMTLInteger(Integer.parseInt(upperBound)));
	return node;

}
	
public Object classDefinition(String lineNumber,Object className,Object inheritance,Object refinement,java.util.Vector tags,java.util.Vector attributes,java.util.Vector settersGetters,java.util.Vector methods)
{	int i;
	java.util.Vector classNames=(java.util.Vector)className;
	String classSurname=(String)classNames.get(0);
	BMTLOrderedSetInterface qn=BMTLTypeConverter(classNames);;
	for (i=1;i < classNames.size();i++) {
		classSurname=classSurname.concat("_"+(String)classNames.get(i));
	}
	BMTL_UserClass node=(BMTL_UserClass)((InstanciableType)theCreatedLib.getMetaClass(new String [] {"UserClass"})).instanciate();
	node.set_BMTL_name(new BMTLString(classSurname));
	node.set_BMTL_QualifiedName(qn);
	try {
	for(i=0;i<attributes.size();i++) {
		java.util.Vector declaredAttributes=(java.util.Vector)attributes.get(i);
		for (int j=0;j<declaredAttributes.size();j++)
			node.BMTL_appendDefinedAttributes((BMTL_AttributeInterface)declaredAttributes.get(j)); 
	}
	for(i=0;i<methods.size();i++)
		node.BMTL_appendDefinedMethods((BMTL_OperationInterface)methods.get(i));
	for(i=0;i<settersGetters.size();i++) {
		java.util.Vector theSetterGetter = (java.util.Vector)settersGetters.get(i);
		node.BMTL_joinAttributeOperation(new BMTLBoolean(((Boolean)theSetterGetter.get(0)).booleanValue()),new BMTLString((String)theSetterGetter.get(1)),new BMTLString((String)theSetterGetter.get(2)));
	}
	putProperty((BMTL_ASTNodeInterface)node,new BMTLString("Inheritance"),(java.util.Vector)inheritance,"InheritanceTag");
	putProperty((BMTL_ASTNodeInterface)node,new BMTLString("Refinement"),(java.util.Vector)refinement,"InheritanceTag");
	putProperty((BMTL_ASTNodeInterface)node,new BMTLString("LineNumber"),new BMTLString(lineNumber),"StringTag");
	putTags((BMTL_ASTNodeInterface)node,tags);
	} catch (Throwable e) {e.printStackTrace();}
	return node; }

public Object inheritance (Object typesList)
{	return typesList; }

public Object attribute(Object localVarDef,java.util.Vector tags)
{	java.util.Vector declaredVars=(java.util.Vector)localVarDef;
	java.util.Vector declaredAttributes=new java.util.Vector();
	for(int i=0;i<declaredVars.size();i++) {
		BMTL_Attribute attrib=(BMTL_Attribute)((InstanciableType)theCreatedLib.getMetaClass(new String [] {"Attribute"})).instanciate();
		attrib.set_BMTL_name(((BMTL_VarDeclaration)declaredVars.get(i)).get_BMTL_name());
		attrib.set_BMTL_decoration(((BMTL_VarDeclaration)declaredVars.get(i)).get_BMTL_decoration());
		try {
		putTags((BMTL_ASTNodeInterface)attrib,tags);
		putProperty((BMTL_ASTNodeInterface)attrib,new BMTLString("FileName"),new BMTLString(currentFile),"StringTag");
		
		} catch (Throwable e) {e.printStackTrace();}
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
	BMTL_Operation node=(BMTL_Operation)((InstanciableType)theCreatedLib.getMetaClass(new String [] {"Operation"})).instanciate();
	node.set_BMTL_name(new BMTLString(methodName));
			
	try {
			
	if (parameters !=null) {
	for(i=0;i<params.size();i++) {
		java.util.Vector typedVars=(java.util.Vector)params.get(i);
		for(j=1;j<typedVars.size();j++) {
			BMTL_VarDeclaration var=(BMTL_VarDeclaration)((InstanciableType)theCreatedLib.getMetaClass(new String [] {"VarDeclaration"})).instanciate();
			var.set_BMTL_name(new BMTLString((String)typedVars.get(j)));
			var.set_BMTL_isFormalParameter(BMTLBoolean.TRUE);
			putProperty((BMTL_ASTNodeInterface)var,new BMTLString("Type"),(java.util.Vector)typedVars.get(0),"TypeTag");
			putProperty((BMTL_ASTNodeInterface)var,new BMTLString("LineNumber"),new BMTLString(lineNumber),"StringTag");
			putProperty((BMTL_ASTNodeInterface)var,new BMTLString("FileName"),new BMTLString(currentFile),"StringTag");
			node.BMTL_appendParameters(var); 
		}
	}
	}
	for(i=0;i<localVars.size();i++) {
		java.util.Vector declaredVars=(java.util.Vector)localVars.get(i);
		for(j=0;j<declaredVars.size();j++)
			node.BMTL_appendDeclaredVariables((BMTL_VarDeclaration)declaredVars.get(j)); 
	}
	for(i=0;i<instructions.size();i++)
		node.BMTL_appendInstructions((BMTL_InstructionInterface)instructions.get(i)); 
	if (creation!=null) putProperty((BMTL_ASTNodeInterface)node,new BMTLString("IsConstructor"),new BMTLString(creation),"StringTag");
	java.util.Vector theReturnedType;
	if (returnedType == null) {
		theReturnedType=new java.util.Vector();
		theReturnedType.addElement("Standard");
		theReturnedType.addElement("Void");
	}
	else theReturnedType=(java.util.Vector)returnedType;
	putProperty((BMTL_ASTNodeInterface)node,new BMTLString("returnedType"),theReturnedType,"TypeTag");
	if (throwsException != null) node.set_BMTL_throwsExceptionValue(BMTLBoolean.TRUE);
	else  node.set_BMTL_throwsExceptionValue(BMTLBoolean.FALSE);
	putProperty((BMTL_ASTNodeInterface)node,new BMTLString("LineNumber"),new BMTLString(lineNumber),"StringTag");
	putProperty((BMTL_ASTNodeInterface)node,new BMTLString("FileName"),new BMTLString(currentFile),"StringTag");
	
	putTags((BMTL_ASTNodeInterface)node,tags);
	} catch (Throwable e) {e.printStackTrace();}
	return node; }

public Object varsDeclaration(Object typedVars,String lineNumber)
{	java.util.Vector declaredVars=new java.util.Vector();
	java.util.Vector typedVarsList=(java.util.Vector)typedVars;
	for(int i=1;i<typedVarsList.size();i++) {
		BMTL_VarDeclaration var=(BMTL_VarDeclaration)((InstanciableType)theCreatedLib.getMetaClass(new String [] {"VarDeclaration"})).instanciate();
		var.set_BMTL_name(new BMTLString((String)typedVarsList.get(i)));
		var.set_BMTL_isFormalParameter(BMTLBoolean.FALSE);
		try {
		putProperty((BMTL_ASTNodeInterface)var,new BMTLString("Type"),(java.util.Vector)typedVarsList.get(0),"TypeTag");
		putProperty((BMTL_ASTNodeInterface)var,new BMTLString("LineNumber"),new BMTLString(lineNumber),"StringTag");
		putProperty((BMTL_ASTNodeInterface)var,new BMTLString("FileName"),new BMTLString(currentFile),"StringTag");
		} catch (Throwable e) {e.printStackTrace();}
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
{	return (BMTL_InstructionInterface)expression; }

public Object affectation(Object sourceTree,Object destTree,String lineNumber) {
	BMTL_ASTNodeInterface node=null;
	try {
	BMTL_PropertyInterface kindProp = (BMTL_PropertyInterface)((BMTL_ASTNodeInterface)destTree).BMTL_getProperty(new BMTLString("kind"));
	if ((destTree instanceof BMTL_OperationCall) && !kindProp.BMTL_isNull(kindProp).getTheBoolean() && kindProp.BMTL_getTheValue().equals(new BMTLString("AttributeGetter"))) {
		node= (BMTL_ASTNodeInterface)destTree;
		node.BMTL_setProperty(new BMTLString("kind"), new BMTLString("AttributeSetter"));
		((BMTL_OperationCall)destTree).BMTL_appendArguments((BMTL_ExpressionInterface)sourceTree);
	} else if (destTree instanceof BMTL_VarCall) {
		BMTL_VarSetting createdNode = (BMTL_VarSetting)((InstanciableType)theCreatedLib.getMetaClass(new String [] {"VarSetting"})).instanciate();
		createdNode.set_BMTL_varName(((BMTL_VarCall)destTree).get_BMTL_varName());
		createdNode.set_BMTL_value((BMTL_ExpressionInterface)sourceTree);
		node=(BMTL_ASTNodeInterface)createdNode;
	} else {
		getLog().error(lineNumber + ": Can just affect variable or attributes."+sourceTree);
		return null;
	}
	putProperty((BMTL_ASTNodeInterface)node,new BMTLString("LineNumber"),new BMTLString(lineNumber),"StringTag");
	} catch (Throwable e) {e.printStackTrace();}
	return node; }

public Object returnInstr(Object expression,String lineNumber)
{	BMTL_Return node=(BMTL_Return)((InstanciableType)theCreatedLib.getMetaClass(new String [] {"Return"})).instanciate();
	node.set_BMTL_returnedExpression((BMTL_ExpressionInterface)expression);
	try {
	putProperty((BMTL_ASTNodeInterface)node,new BMTLString("LineNumber"),new BMTLString(lineNumber),"StringTag");
	} catch (Throwable e) {e.printStackTrace();}
	return node; }

public Object whileInstr(Object expression,Object body)
{	java.util.Vector instructions=(java.util.Vector)((java.util.Vector)body).get(0);
	String lineNumber=(String)((java.util.Vector)body).get(1);
	BMTL_While node=(BMTL_While)((InstanciableType)theCreatedLib.getMetaClass(new String [] {"While"})).instanciate();
	node.set_BMTL_condition((BMTL_ExpressionInterface)expression);
	try {
	for(int i=0;i<instructions.size();i++)
		node.BMTL_appendBody((BMTL_InstructionInterface)instructions.get(i));
	putProperty((BMTL_ASTNodeInterface)node,new BMTLString("LineNumber"),new BMTLString(lineNumber),"StringTag");
	} catch (Throwable e) {e.printStackTrace();}
	return node; }

public Object ifInstr(Object expression,Object thenBody,Object elseBody)
{	int i;
	java.util.Vector thenInstructions=(java.util.Vector)((java.util.Vector)thenBody).get(0);
	java.util.Vector elseInstructions=null;
	if (elseBody!=null)
		elseInstructions=(java.util.Vector)((java.util.Vector)elseBody).get(0);
	String lineNumber=(String)((java.util.Vector)thenBody).get(1);
	BMTL_If node=(BMTL_If)((InstanciableType)theCreatedLib.getMetaClass(new String [] {"If"})).instanciate();
	node.set_BMTL_condition((BMTL_ExpressionInterface)expression);
	try {
	for(i=0;i<thenInstructions.size();i++)
		node.BMTL_appendThenBody((BMTL_InstructionInterface)thenInstructions.get(i));
	if (elseInstructions != null)
		for(i=0;i<elseInstructions.size();i++)
			node.BMTL_appendElseBody((BMTL_InstructionInterface)elseInstructions.get(i));
	putProperty((BMTL_ASTNodeInterface)node,new BMTLString("LineNumber"),new BMTLString(lineNumber),"StringTag");
	} catch (Throwable e) {e.printStackTrace();}
	return node; }

public Object throwsInstr(Object expression,String lineNumber)
{	BMTL_Throws node=(BMTL_Throws)((InstanciableType)theCreatedLib.getMetaClass(new String [] {"Throws"})).instanciate();
	node.set_BMTL_thrownExpression((BMTL_ExpressionInterface)expression);
	try {
	putProperty((BMTL_ASTNodeInterface)node,new BMTLString("LineNumber"),new BMTLString(lineNumber),"StringTag");
	} catch (Throwable e) {e.printStackTrace();}
	return node; }

public Object tryInstr(Object tryBody,java.util.Vector catches,Object finBody)
{	int i;
	java.util.Vector instructions=(java.util.Vector)((java.util.Vector)tryBody).get(0);
	java.util.Vector finallyInstructions=null;
	if (finBody !=null) finallyInstructions=(java.util.Vector)((java.util.Vector)finBody).get(0);
	String lineNumber=(String)((java.util.Vector)tryBody).get(1);
	BMTL_Try node=(BMTL_Try)((InstanciableType)theCreatedLib.getMetaClass(new String [] {"Try"})).instanciate();
	try {
	for(i=0;i<instructions.size();i++)
		node.BMTL_appendTryBody((BMTL_InstructionInterface)instructions.get(i));
	for(i=0;i<catches.size();i++) {
		java.util.Vector theCatch=(java.util.Vector)catches.get(i);
		String varName=(String)theCatch.get(0);
		java.util.Vector varType=(java.util.Vector)theCatch.get(1);
		java.util.Vector theCatchBody=(java.util.Vector)theCatch.get(2);
		java.util.Vector catchInstructions=(java.util.Vector)theCatchBody.get(0);
		String line=(String)theCatchBody.get(1);
		BMTL_Catch aCatch=(BMTL_Catch)((InstanciableType)theCreatedLib.getMetaClass(new String [] {"Catch"})).instanciate();
		BMTL_VarDeclaration var=(BMTL_VarDeclaration)((InstanciableType)theCreatedLib.getMetaClass(new String [] {"VarDeclaration"})).instanciate();
		var.set_BMTL_name(new BMTLString(varName));
		var.set_BMTL_isFormalParameter(BMTLBoolean.FALSE);
		putProperty((BMTL_ASTNodeInterface)var,new BMTLString("Type"),(java.util.Vector)varType,"TypeTag");
		aCatch.set_BMTL_catchedException(var);
		for(int j=0;j<catchInstructions.size();j++)
			aCatch.BMTL_appendCatchBody((BMTL_InstructionInterface)catchInstructions.get(j));
		putProperty((BMTL_ASTNodeInterface)aCatch,new BMTLString("LineNumber"),new BMTLString(lineNumber),"StringTag");
		node.BMTL_appendCatchPart(aCatch);
	}
	if (finallyInstructions != null) {
	for(i=0;i<finallyInstructions.size();i++)
		node.BMTL_appendFinalizeBody((BMTL_InstructionInterface)finallyInstructions.get(i));
	}
	putProperty((BMTL_ASTNodeInterface)node,new BMTLString("LineNumber"),new BMTLString(lineNumber),"StringTag");
	} catch (Throwable e) {e.printStackTrace();}
	return node; }

public Object associateInstr(boolean isAssociate,java.util.Vector endPoints,String lineNumber)
{	BMTL_Associate node=(BMTL_Associate)((InstanciableType)theCreatedLib.getMetaClass(new String [] {"Associate"})).instanciate();
	node.set_BMTL_isAssociate(new BMTLBoolean(new BooleanValueImpl(false,null,isAssociate)));
	try {
	for(int i=0;i<endPoints.size();i++)
		node.BMTL_appendRoles((BMTL_RoleInterface)endPoints.get(i));
	putProperty((BMTL_ASTNodeInterface)node,new BMTLString("LineNumber"),new BMTLString(lineNumber),"StringTag");
	} catch (Throwable e) {e.printStackTrace();}
	return node; }

public Object associateEndPoint(String role,Object endObject,Object type)
{	BMTL_Role node=(BMTL_Role)((InstanciableType)theCreatedLib.getMetaClass(new String [] {"Role"})).instanciate();
	node.set_BMTL_RoleName(new BMTLString(role));
	node.set_BMTL_expression((BMTL_ExpressionInterface)endObject);
	try {
	putProperty((BMTL_ASTNodeInterface)node,new BMTLString("Type"),(java.util.Vector)type,"TypeTag");
	} catch (Throwable e) {e.printStackTrace();}
	return node; }

public Object bodyInstr(java.util.Vector instructions,String lineNumber)
{	java.util.Vector result=new java.util.Vector();
	result.addElement(instructions);
	result.addElement(lineNumber);
	return result; }

public Object newExpr(Object theClass,String methodName,Object arguments,String lineNumber,java.util.Vector propertyCalls)
{	java.util.Vector args=(java.util.Vector)arguments;
	BMTL_NewObject node=(BMTL_NewObject)((InstanciableType)theCreatedLib.getMetaClass(new String [] {"NewObject"})).instanciate();
	try {
	if (arguments!=null)
		for(int i=0;i<args.size();i++)
			node.BMTL_appendArguments((BMTL_ExpressionInterface)args.get(i));
	putProperty((BMTL_ASTNodeInterface)node,new BMTLString("TypeToCreate"),(java.util.Vector)theClass,"TypeTag");
	putProperty((BMTL_ASTNodeInterface)node,new BMTLString("CreationMethod"),new BMTLString(methodName),"StringTag");
	putProperty((BMTL_ASTNodeInterface)node,new BMTLString("LineNumber"),new BMTLString(lineNumber),"StringTag");
	} catch (Throwable e) {e.printStackTrace();}
	return (BMTL_ExpressionInterface)putPropertyCalls((BMTL_ExpressionInterface)node,propertyCalls); }

public Object intLiteral(String value,java.util.Vector propertyCalls)
{	BMTL_IntLiteral theLiteral=(BMTL_IntLiteral)((InstanciableType)theCreatedLib.getMetaClass(new String [] {"IntLiteral"})).instanciate();
	theLiteral.set_BMTL_value (new BMTLInteger(Integer.parseInt(value)));
	return (BMTL_ExpressionInterface)putPropertyCalls((BMTL_ExpressionInterface)theLiteral,propertyCalls); }

public Object realLiteral(String value,java.util.Vector propertyCalls)
{	BMTL_RealLiteral theLiteral=(BMTL_RealLiteral)((InstanciableType)theCreatedLib.getMetaClass(new String [] {"RealLiteral"})).instanciate();
	theLiteral.set_BMTL_value (new BMTLReal(Double.parseDouble(value)));
	return (BMTL_ExpressionInterface)putPropertyCalls((BMTL_ExpressionInterface)theLiteral,propertyCalls); }

public Object stringLiteral(String value,java.util.Vector propertyCalls)
{	BMTL_StringLiteral theLiteral=(BMTL_StringLiteral)((InstanciableType)theCreatedLib.getMetaClass(new String [] {"StringLiteral"})).instanciate();
	theLiteral.set_BMTL_value (new BMTLString(value));
	return (BMTL_ExpressionInterface)putPropertyCalls((BMTL_ExpressionInterface)theLiteral,propertyCalls); }

public Object javaCodeLiteral(String value)
{	BMTL_JavaCodeLiteral theLiteral=(BMTL_JavaCodeLiteral)((InstanciableType)theCreatedLib.getMetaClass(new String [] {"JavaCodeLiteral"})).instanciate();
	theLiteral.set_BMTL_value (new BMTLString(value));
	return (BMTL_ExpressionInterface)theLiteral;}
		
public Object oclTypeLiteral(Object type,java.util.Vector propertyCalls)
{	BMTL_OclTypeLiteral theLiteral=(BMTL_OclTypeLiteral)((InstanciableType)theCreatedLib.getMetaClass(new String [] {"OclTypeLiteral"})).instanciate();
	java.util.Vector theType=(java.util.Vector)type;
	Value theCollection[]=new Value[theType.size()];
	for (int i=0;i<theType.size();i++)
		theCollection[i]=new BMTLString((String)theType.get(i));
	theLiteral.set_BMTL_typeValue (new BMTLOrderedSet(theCollection));
	return (BMTL_ExpressionInterface)putPropertyCalls((BMTL_ExpressionInterface)theLiteral,propertyCalls); }

public Object selfLiteral(java.util.Vector propertyCalls)
{	BMTL_SelfLiteral theLiteral=(BMTL_SelfLiteral)((InstanciableType)theCreatedLib.getMetaClass(new String [] {"SelfLiteral"})).instanciate();
	return (BMTL_ExpressionInterface)putPropertyCalls((BMTL_ExpressionInterface)theLiteral,propertyCalls); }

public Object nullLiteral(java.util.Vector propertyCalls)
{	BMTL_NullLiteral theLiteral=(BMTL_NullLiteral)((InstanciableType)theCreatedLib.getMetaClass(new String [] {"NullLiteral"})).instanciate();
	return (BMTL_ExpressionInterface)putPropertyCalls((BMTL_ExpressionInterface)theLiteral,propertyCalls); }

public Object trueLiteral(java.util.Vector propertyCalls)
{	BMTL_BooleanLiteral theLiteral=(BMTL_BooleanLiteral)((InstanciableType)theCreatedLib.getMetaClass(new String [] {"BooleanLiteral"})).instanciate();
	theLiteral.set_BMTL_value(BMTLBoolean.TRUE);
	return (BMTL_ExpressionInterface)putPropertyCalls((BMTL_ExpressionInterface)theLiteral,propertyCalls); }

public Object falseLiteral(java.util.Vector propertyCalls)
{	BMTL_BooleanLiteral theLiteral=(BMTL_BooleanLiteral)((InstanciableType)theCreatedLib.getMetaClass(new String [] {"BooleanLiteral"})).instanciate();
	theLiteral.set_BMTL_value(BMTLBoolean.FALSE);
	return (BMTL_ExpressionInterface)putPropertyCalls((BMTL_ExpressionInterface)theLiteral,propertyCalls); }

public Object attributeOrVariable(String name,java.util.Vector propertyCalls)
{	BMTL_ExpressionInterface expr;
	BMTL_VarCall var = (BMTL_VarCall)((InstanciableType)theCreatedLib.getMetaClass(new String [] {"VarCall"})).instanciate();
	var.set_BMTL_varName(new BMTLString(name));
	expr=(BMTL_ExpressionInterface) var;
	return (BMTL_ExpressionInterface)putPropertyCalls(expr,propertyCalls); }

public Object attributeGetter(String attributeName)
{	BMTL_OperationCall ret = (BMTL_OperationCall)((InstanciableType)theCreatedLib.getMetaClass(new String [] {"OperationCall"})).instanciate();
	ret.set_BMTL_name(new BMTLString(attributeName));
	try {
	putProperty((BMTL_ASTNodeInterface)ret,new BMTLString("kind"), new BMTLString("AttributeGetter"), "StringTag");
	} catch (Throwable e) {e.printStackTrace();}
	return ret; }

public Object variableName(String value)
{	BMTL_VarCall theVar=(BMTL_VarCall)((InstanciableType)theCreatedLib.getMetaClass(new String [] {"VarCall"})).instanciate();
	theVar.set_BMTL_varName(new BMTLString(value));
	return (BMTL_ExpressionInterface)theVar; }

public Object directOperationCalls(java.util.Vector propertyCalls)
{ return (BMTL_ExpressionInterface)putPropertyCalls(null,propertyCalls); }
	
public Object operationCall(String operationName,Object arguments,String lineNumber)
{	java.util.Vector args=(java.util.Vector)arguments;
	BMTL_OperationCall node=(BMTL_OperationCall)((InstanciableType)theCreatedLib.getMetaClass(new String [] {"OperationCall"})).instanciate();
	node.set_BMTL_name(new BMTLString(operationName));
	try {
	if (arguments !=null)
		for(int i=0;i<args.size();i++) {
			node.BMTL_appendArguments((BMTL_ExpressionInterface)args.get(i));
		}
	putProperty((BMTL_ASTNodeInterface)node,new BMTLString("LineNumber"),new BMTLString(lineNumber),"StringTag");
	} catch (Throwable e) {e.printStackTrace();}
	return (BMTL_ExpressionInterface)node; }

public Object negateExpr(Object expr,String lineNumber)
{	BMTL_OperationCall node=(BMTL_OperationCall)((InstanciableType)theCreatedLib.getMetaClass(new String [] {"OperationCall"})).instanciate();
	node.set_BMTL_name(new BMTLString("not"));
	try {
	putProperty((BMTL_ASTNodeInterface)node,new BMTLString("LineNumber"),new BMTLString(lineNumber),"StringTag");
	} catch (Throwable e) {e.printStackTrace();}
	node.set_BMTL_caller((BMTL_ExpressionInterface)expr);
	return (BMTL_ExpressionInterface)node; }

public Object exprOpExpr(Object expr1,String operator,Object expr2,String lineNumber)
{	BMTL_OperationCall node=(BMTL_OperationCall)((InstanciableType)theCreatedLib.getMetaClass(new String [] {"OperationCall"})).instanciate();
	node.set_BMTL_name(new BMTLString(operator));
	try {
	node.BMTL_appendArguments((BMTL_ExpressionInterface)expr2);
	putProperty((BMTL_ASTNodeInterface)node,new BMTLString("LineNumber"),new BMTLString(lineNumber),"StringTag");
	} catch (Throwable e) {e.printStackTrace();}
	node.set_BMTL_caller((BMTL_ExpressionInterface)expr1);
	return (BMTL_ExpressionInterface)node; }

public Object oclAsType(Object type,String lineNumber,String theType,String theMethod,String theParameter,boolean isAConstant)
{	BMTL_OclAsType node=(BMTL_OclAsType)((InstanciableType)theCreatedLib.getMetaClass(new String [] {"OclAsType"})).instanciate();
	java.util.Vector qualifiers=(java.util.Vector)type;
	try {
		for(int i=0;i<qualifiers.size();i++)
			node.BMTL_appendType(new BMTLString((String)qualifiers.get(i)));
		putProperty((BMTL_ASTNodeInterface)node,new BMTLString("LineNumber"),new BMTLString(lineNumber),"StringTag");
	} catch (Throwable e) {e.printStackTrace();}
	if (isAConstant)
	{	node.set_BMTL_isAConstant(BMTLBoolean.TRUE);
	}
	else {
		node.set_BMTL_isAConstant(BMTLBoolean.FALSE);
		node.set_BMTL_typeVar(new BMTLString(theType));
		node.set_BMTL_methodVar(new BMTLString(theMethod));
		node.set_BMTL_parameterVar(new BMTLString(theParameter)); }
	return (BMTL_ExpressionInterface)node; }

public Object arguments(java.util.Vector expressions)
{	java.util.Vector exprVector=new java.util.Vector();
	for(int i=0;i<expressions.size();i++)
		exprVector.addElement((BMTL_ExpressionInterface)expressions.get(i));
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
