/*
 * $Header: /tmp/cvs2svn/cvsroot/BasicMTL_projects/BasicMtlAntlr2TLLJava/src/TLLBuilder/UserClassAnalyser.java,v 1.1 2003-08-06 16:18:46 jpthibau Exp $
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
public class UserClassAnalyser extends ASTTopDownVisitor.UserClassAnalyser {

	public Object UserClassBefore(org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.UserClass ASTnode,java.util.Map context)
	{	String userClassName=ASTnode.getName();
		String mangle=null;
		Property mangling=(Property)ASTnode.getProperty("mangle");
		if (mangling == null)
			mangle=Mangler.mangle("BMTL_",userClassName);
		else mangle=(String)mangling.getValue();
		int lineNumber=Integer.parseInt((String)ASTnode.getProperty("LineNumber").getValue());
		UserClass theCreatedClass=new UserClass(userClassName,mangle,lineNumber);
		BasicMtlLibrary theCreatedLib=(BasicMtlLibrary)context.get("TheCreatedLibrary");
		Property inheritance=(Property)ASTnode.getProperty("Inheritance");
		if (inheritance!=null) {
		java.util.Vector parents=(java.util.Vector)inheritance.getValue();
		InheritedTypesList parentsTypesList=new InheritedTypesList();
		for (int i=0;i<parents.size();i++) {
			QualifiedName type=CommonFunctions.findOrAddType((java.util.Vector)parents.get(i),theCreatedLib);
			parentsTypesList.addElement(type);
			String getRefOpName="getRef_"+type.get(0);
			if (type.size()>1) getRefOpName=getRefOpName+type.get(1);
			OpSignature getRefSignature=new OpSignature(getRefOpName,Mangler.mangle("BMTL_",getRefOpName));
			getRefSignature.setReturnedType(type);
			getRefSignature.setArgsCount(0);
			theCreatedClass.appendLocalSignatures(getRefSignature);
		}
		theCreatedClass.setInheritance(parentsTypesList);
		}
		else //set the flag "completed inheritedSignatures" for the typeChecker
			theCreatedClass.setCompletedInheritedSignatures(true);
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
		OpSignature getterSignature=new OpSignature(getterName,Mangler.mangle("BMTL_",getterName));
		OpSignature setterSignature=new OpSignature(setterName,Mangler.mangle("BMTL_",setterName));
		getterSignature.setReturnedType(attrib.getFeatureType());
		getterSignature.setArgsCount(0);
		QualifiedName returnTypeSetter=new QualifiedName();
		returnTypeSetter.addElement("VoidValueImpl");
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
