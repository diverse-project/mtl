/*
 * Created on 23 juil. 2003
 * $Id: UserClassAnalyser.java,v 1.14 2004-11-03 09:34:52 jpthibau Exp $
 * Authors : jpthibau
 * 
 * Copyright 2004 - INRIA - LGPL license
 */
package TLLBuilder;

import java.util.Arrays;
import java.util.Vector;

// import org.irisa.triskell.MT.utils.Java.AWK;
import org.irisa.triskell.MT.utils.Java.Mangler;
import org.irisa.triskell.MT.visitors.Java.AnalysingVisitor.*;
import org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.*;
import org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.signatures.AttributeGetterSignature;
import org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.signatures.AttributeSetterSignature;
import org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.signatures.GetReferenceSignature;

/**
 * @author jpthibau
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class UserClassAnalyser extends ASTTopDownVisitor.UserClassAnalyser {

	public Object UserClassBefore(org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.UserClass ASTnode,java.util.Map context)
	{	context.put("InClass", Boolean.TRUE);
		String userClassName=ASTnode.getName();
		String mangle=null;
		Property observableClass=(Property)ASTnode.getProperty("observable");
		boolean observable = observableClass != null;
		Property mangling=(Property)ASTnode.getProperty("mangle");
		boolean manualMangling = mangling != null;
		if (manualMangling)
			mangle=(String)((java.util.Vector)mangling.getValue()).get(2);
		else
			mangle=Mangler.mangle("BMTL_",userClassName);
		int lineNumber=Integer.parseInt((String)ASTnode.getProperty("LineNumber").getValue());
		UserClass theCreatedClass=new UserClass(userClassName,mangle,lineNumber);
		theCreatedClass.setIsAbstract(ASTnode.getIsAbstract());
		QualifiedName qn = new QualifiedName();
		for (int i=0;i<ASTnode.cardQualifiedName();i++)
			qn.addElement(ASTnode.getQualifiedName(i));
		theCreatedClass.setQualifiedName(qn);
		theCreatedClass.createNewProperty("ObservableClass", observable ? Boolean.TRUE : Boolean.FALSE, "Boolean");
		theCreatedClass.createNewProperty("ManualMangling", manualMangling ? Boolean.TRUE : Boolean.FALSE, "Boolean");
		
		// We retrieve the LineNumber Property from the AST node.
		// We create a new Property from it for the TLL node.
		Property fileNumberProperty = ASTnode.getProperty("LineNumber");
		theCreatedClass.createNewProperty("LineNumber",fileNumberProperty.getValue(),"String");
		
		// We retrieve the FileName Property from the AST node. 
		Property FileNameProperty = ASTnode.getProperty("FileName");
		String FileName = 
			(FileNameProperty == null ?
				"Unknown file location for variable " + userClassName :
				(String)FileNameProperty.getValue()
			);
		// We create a new Property from it for the TLL node.
		theCreatedClass.createNewProperty ("FileName", FileName, "String");

		
		Property typeTag = ASTnode.getProperty("type");
		if (typeTag != null)
			theCreatedClass.createNewProperty("type", typeTag.getValue(), "String");
		BasicMtlLibrary theCreatedLib=(BasicMtlLibrary)context.get("TheCreatedLibrary");
		Property refinement=(Property)ASTnode.getProperty("Refinement");
		InheritedTypesList refinedTypesList=new InheritedTypesList();
		if (refinement!=null) {
		java.util.Vector refinedTypes=(java.util.Vector)refinement.getValue();
		for (int i=0;i<refinedTypes.size();i++) {
			QualifiedName refinedName=new QualifiedName();
			java.util.Vector aRefinedType=(java.util.Vector)refinedTypes.get(i);
			for(int j=0;j<aRefinedType.size();j++) refinedName.addElement(aRefinedType.get(j));
			refinedTypesList.addElement(refinedName);
		}
		}
		theCreatedClass.setRefinement(refinedTypesList);
		Property inheritance=(Property)ASTnode.getProperty("Inheritance");
		InheritedTypesList parentsTypesList=new InheritedTypesList();
		if (inheritance!=null) {
		java.util.Vector parents=(java.util.Vector)inheritance.getValue();
		for (int i=0;i<parents.size();i++) {
			QualifiedName type=CommonFunctions.findOrAddType((java.util.Vector)parents.get(i),theCreatedLib);
			parentsTypesList.addElement(type);
/*			String name = type.size()>1 ? (String) type.get(1) : (String) type.get(0);
			String getRefOpName="getRef_"+name;
			//@DONE cette opération devrait être faite au controle des types...
			String getRefOpMangle ="getRef_"+Mangler.mangle("BMTL_", name);
			OpSignature getRefSignature=new OpSignature(getRefOpName,getRefOpMangle);
			getRefSignature.setReturnedType(type);
			getRefSignature.setArgsCount(0);*/
			OpSignature getRefSignature = new GetReferenceSignature(type);
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

		// we add the attribute (full object) in the "KnownAttributes" context
		java.util.Vector knownAttributes=(java.util.Vector)context.get("KnownAttributes");
		knownAttributes.addElement(attrib);
		context.put("KnownAttributes",knownAttributes);

		String getterName="get_"+attrib.getName();
		String setterName="set_"+attrib.getName();
		String getterMangle=Mangler.mangle("BMTL_", getterName);
		String setterMangle=Mangler.mangle("BMTL_", setterName);
/*		OpSignature getterSignature=new OpSignature(getterName,getterMangle);
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
		setterSignature.appendArgsTypes(attrib.getFeatureType());*/
		BasicMtlLibrary theCreatedLib=(BasicMtlLibrary)context.get("TheCreatedLibrary");
		QualifiedName voidType=CommonFunctions.findOrAddType(new Vector (Arrays.asList(new String [] {"Standard", "Void"})), theCreatedLib);
		OpSignature getterSignature=new AttributeGetterSignature(attrib);
		OpSignature setterSignature=new AttributeSetterSignature(attrib, voidType);
		theCreatedClass.appendLocalSignatures(getterSignature);
		theCreatedClass.appendLocalSignatures(setterSignature);
	}

	public void UserClassMethod(Object theUserClass,Object objectOp,java.util.Map context)
	{	UserClass theCreatedClass=(UserClass)theUserClass;
		Operation op=(Operation)objectOp;
		theCreatedClass.appendLocalSignatures(op.getTheSignature());
		theCreatedClass.appendDefinedFeatures(op);
	}

	public void joinAttributeWithOp(UserClass theCreatedClass,String attributeName,String OpName,boolean getterlink)
	{	Attribute theAttribute=null;
		Operation theOperation=null;
		for(int i=0;i<theCreatedClass.cardDefinedFeatures();i++)
		{	if (theCreatedClass.getDefinedFeatures(i).getName().equals(attributeName))
				theAttribute=(Attribute)theCreatedClass.getDefinedFeatures(i);
			if (theCreatedClass.getDefinedFeatures(i).getName().equals(OpName))
						theOperation=(Operation)theCreatedClass.getDefinedFeatures(i);
		}
		if (theAttribute!=null && theOperation!=null)
		{	if (getterlink) {
				theAttribute.setGetter(theOperation);
				theOperation.setIsGetterFor(theAttribute);
			}
			else {
				theAttribute.setSetter(theOperation);
				theOperation.setIsSetterFor(theAttribute);
			}
		}
	}
	
	public void UserClassAfter(Object theUserClass,org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.UserClass ASTnode,java.util.Map context)
	{	UserClass theCreatedClass=(UserClass)theUserClass;
		//rely attributes getters and setters to their methods
		for (int i=0;i<ASTnode.cardDefinedAttributes();i++)
		{	org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.Operation theGetter=ASTnode.getDefinedAttributes(i).getGetter();
			org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.Operation theSetter=ASTnode.getDefinedAttributes(i).getSetter();
			if (theGetter!=null)
				joinAttributeWithOp(theCreatedClass,ASTnode.getDefinedAttributes(i).getName(),theGetter.getName(),true);
			if (theSetter!=null)
				joinAttributeWithOp(theCreatedClass,ASTnode.getDefinedAttributes(i).getName(),theSetter.getName(),false);
		}
		context.put("UserClass",theCreatedClass);
		context.remove("InClass");
	}
}
