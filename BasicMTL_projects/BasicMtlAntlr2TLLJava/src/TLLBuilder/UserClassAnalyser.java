/*
 * $Header: /tmp/cvs2svn/cvsroot/BasicMTL_projects/BasicMtlAntlr2TLLJava/src/TLLBuilder/UserClassAnalyser.java,v 1.4 2003-08-14 20:47:46 ffondeme Exp $
 * Created on 23 juil. 2003
 *
 */
package TLLBuilder;

import java.util.Arrays;
import java.util.Vector;

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
public class UserClassAnalyser extends ASTTopDownVisitor.UserClassAnalyser {

	public Object UserClassBefore(org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.UserClass ASTnode,java.util.Map context)
	{	String userClassName=ASTnode.getName();
		String mangle=null;
		Property mangling=(Property)ASTnode.getProperty("mangle");
		boolean manualMangling = mangling != null;
		if (manualMangling)
			mangle=(String)((java.util.Vector)mangling.getValue()).get(2);
		else
			mangle=Mangler.mangle("BMTL_",userClassName);
		int lineNumber=Integer.parseInt((String)ASTnode.getProperty("LineNumber").getValue());
		UserClass theCreatedClass=new UserClass(userClassName,mangle,lineNumber);
		theCreatedClass.createNewProperty("ManualMangling", manualMangling ? Boolean.TRUE : Boolean.FALSE, "Boolean");
		Property typeTag = ASTnode.getProperty("type");
		if (typeTag != null)
			theCreatedClass.createNewProperty("type", typeTag.getValue(), "String");
		BasicMtlLibrary theCreatedLib=(BasicMtlLibrary)context.get("TheCreatedLibrary");
		Property inheritance=(Property)ASTnode.getProperty("Inheritance");
		InheritedTypesList parentsTypesList=new InheritedTypesList();
		if (inheritance!=null) {
		java.util.Vector parents=(java.util.Vector)inheritance.getValue();
		for (int i=0;i<parents.size();i++) {
			QualifiedName type=CommonFunctions.findOrAddType((java.util.Vector)parents.get(i),theCreatedLib);
			parentsTypesList.addElement(type);
			String name = type.size()>1 ? (String) type.get(1) : (String) type.get(0);
			String getRefOpName="getRef_"+name;
			//@TODO cette opération devrait être faite au controle des types...
			String getRefOpMangle ="getRef_"+Mangler.mangle("BMTL_", name);
			OpSignature getRefSignature=new OpSignature(getRefOpName,getRefOpMangle);
			getRefSignature.setReturnedType(type);
			getRefSignature.setArgsCount(0);
			theCreatedClass.appendLocalSignatures(getRefSignature);
		}
		}
		else //set the flag "completed inheritedSignatures" for the typeChecker
			theCreatedClass.setCompletedInheritedSignatures(true);
		theCreatedClass.setInheritance(parentsTypesList);
		QualifiedName theClassType=new QualifiedName();
		theClassType.addElement(userClassName);
		context.put("CurrentClassType",theClassType);
		context.put("KnownAttributes",new java.util.Vector()); //to check if VarCAll is an attribute Getter
		return theCreatedClass;
	}

	public void UserClassAttribute(Object theUserClass,Object objectAttrib,java.util.Map context)
	{	UserClass theCreatedClass=(UserClass)theUserClass;
		Attribute attrib=(Attribute)objectAttrib;
		theCreatedClass.appendDefinedFeatures(attrib);
		java.util.Vector knownAttributes=(java.util.Vector)context.get("KnownAttributes");
		knownAttributes.addElement(attrib.getName());
		context.put("KnownAttributes",knownAttributes);
		String getterName="get_"+attrib.getName();
		String setterName="set_"+attrib.getName();
		String getterMangle="get_"+attrib.getMangle();
		String setterMangle="set_"+attrib.getMangle();
		OpSignature getterSignature=new OpSignature(getterName,getterMangle);
		OpSignature setterSignature=new OpSignature(setterName,setterMangle);
		getterSignature.setReturnedType(attrib.getFeatureType());
		getterSignature.setArgsCount(0);
//		QualifiedName returnTypeSetter=new QualifiedName();
//		returnTypeSetter.addElement("Standard");
//		returnTypeSetter.addElement("Void");
		BasicMtlLibrary theCreatedLib=(BasicMtlLibrary)context.get("TheCreatedLibrary");
		QualifiedName returnTypeSetter=CommonFunctions.findOrAddType(new Vector (Arrays.asList(new String [] {"Standard", "Void"})), theCreatedLib);
		setterSignature.setReturnedType(returnTypeSetter);
		setterSignature.setArgsCount(1);
		setterSignature.appendArgsTypes(attrib.getFeatureType());
		theCreatedClass.appendLocalSignatures(getterSignature);
		theCreatedClass.appendLocalSignatures(setterSignature);
	}

	public void UserClassMethod(Object theUserClass,Object objectOp,java.util.Map context)
	{	UserClass theCreatedClass=(UserClass)theUserClass;
		Operation op=(Operation)objectOp;
		theCreatedClass.appendLocalSignatures(op.getTheSignature());
		theCreatedClass.appendDefinedFeatures(op);
	}

	public void UserClassAfter(Object theUserClass,org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.UserClass ASTnode,java.util.Map context)
	{	UserClass theCreatedClass=(UserClass)theUserClass;
		context.put("UserClass",theCreatedClass);
	}
}
