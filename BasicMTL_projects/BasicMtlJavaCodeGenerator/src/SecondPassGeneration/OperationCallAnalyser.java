/*
 * Created on 8 août 2003
 * $Id: OperationCallAnalyser.java,v 1.11 2004-02-16 17:36:43 dvojtise Exp $
 * Authors : jpthibau
 * 
 * Copyright 2004 - INRIA - LGPL license
 */
package SecondPassGeneration;

import java.io.*;
//import java.util.Iterator;
//import java.util.Map;
//import java.util.Vector;

import org.irisa.triskell.MT.utils.Java.AWK;
import org.irisa.triskell.MT.utils.Java.Mangler;
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
public class OperationCallAnalyser extends TLLTopDownVisitor.OperationCallAnalyser {

	public Object OperationCallBefore(OperationCall ASTnode,java.util.Map context)
	{	
		PrintWriter outputForClass = (PrintWriter)context.get("OutputForClass");
		CommonFunctions.generateCastBefore(outputForClass, ASTnode,ASTnode.getIsTrownExpression());
		return ASTnode; }
		
	public void OperationCallCaller(Object theOperationCall,OperationCall node,Object expr, java.util.Map context)
	{	OperationCall theOpCall=(OperationCall)theOperationCall;
		QualifiedName qn = theOpCall.getOclAsType();
		PrintWriter outputForClass = (PrintWriter)context.get("OutputForClass");
		Operation operationContainer = (Operation)context.get("OperationContainer");
		if (expr != null)
			outputForClass.print('.');
		if (theOpCall.getIsToInvoke())  {
			String oclAsType;
			if (qn == null)
				oclAsType = "null";
			else {
				oclAsType = "this.getLibrary().getMetaClass(new String [] {\"" + AWK.mergeCollection(qn, "\", \"") + "\"}).getQualifiedName()";
			}
			outputForClass.print("invoke(" + oclAsType + ",\""+theOpCall.getName()+"\",new Value[]{");
//@TODO IMPORTANT; this is a bug ; called values have to be casted into the correct BMTLDataTypes... This requires to know which operation is called.
		} else {
			if (qn != null) {
				GetReferenceSignature grs = new GetReferenceSignature(qn);
				outputForClass.print(grs.getOpMangle());
				outputForClass.print("().");
			}
			if (theOpCall.getKind().equals(OperationKind.getAttributeCall())) {
				if (operationContainer.getIsGetterFor()!=null
					&& operationContainer.getIsGetterFor().getName().equals(theOpCall.getName()))
					//call of the attribute for which this opCall is the Getter; use => this.attribute
				{	outputForClass.print("this."+Mangler.mangle("BMTL_", theOpCall.getName()));
					theOpCall.setNoEndingBracket(true);
				} 
				else outputForClass.print(AttributeGetterSignature.GetPrefix+Mangler.mangle("BMTL_", theOpCall.getName())+'(');
				}
			else if (theOpCall.getKind().equals(OperationKind.getAttributeSet())) {
					if (operationContainer.getIsSetterFor()!=null
					&& operationContainer.getIsSetterFor().getName().equals(theOpCall.getName()))
					{	outputForClass.print("this."+Mangler.mangle("BMTL_", theOpCall.getName()));
						outputForClass.print('=');
						theOpCall.setNoEndingBracket(true);
					} 
				else outputForClass.print(AttributeSetterSignature.SetPrefix+Mangler.mangle("BMTL_", theOpCall.getName())+'(');
			}
			else if (theOpCall.getKind().equals(OperationKind.getCurrentLibraryCall()))
				outputForClass.print("theLib");
			else if (theOpCall.getKind().equals(OperationKind.getLibraryCall())) {
				TheLibraryClass tlc = (TheLibraryClass)context.get("CurrentClass");
				BasicMtlLibrary lib = tlc.getTheLibrary();
				QualifiedName ref = null;
				for (int i = 0; ref == null && i < lib.cardUsedLibs(); ++i) {
					ref = lib.getUsedLibs(i);
					if (! (ref.size() == 1 && ref.get(0).equals(theOpCall.getName())))
						ref = null;
				}
				outputForClass.print("BMTLRef_"+ref.getExternMangledName());
			} else {
				String mangledOpCall=Mangler.mangle("BMTL_",theOpCall.getName());
				outputForClass.print(mangledOpCall+'(');
			} 
		}
	}

	public void OperationCallArgSeparator(java.util.Map context)
	{	PrintWriter outputForClass = (PrintWriter)context.get("OutputForClass");
		outputForClass.print(",");
	}

	public void OperationCallAfter(Object theOperationCall,OperationCall ASTnode,java.util.Map context)
	{	OperationCall theOpCall=(OperationCall)theOperationCall;
		PrintWriter outputForClass = (PrintWriter)context.get("OutputForClass");
		if (theOpCall.getIsToInvoke())  {
			String disc;
			if (ASTnode.getKind().equals(OperationKind.getOperationCall()))
				disc = "ModelElement.OperationDiscriminant";
			else if (ASTnode.getKind().equals(OperationKind.getAttributeCall()))
				disc = "ModelElement.AttributeDiscriminant, ModelElement.AssociationDiscriminant";
			else if (ASTnode.getKind().equals(OperationKind.getAttributeSet()))
				disc = "ModelElement.SetAttributeDiscriminant";
			else
				disc = "";
			outputForClass.print("},new String[]{" + disc + "})");
		} else if (! theOpCall.getKind().equals(OperationKind.getCurrentLibraryCall())
					&&! theOpCall.getKind().equals(OperationKind.getLibraryCall()))
			if (!theOpCall.getNoEndingBracket())outputForClass.print(')');
		CommonFunctions.generateCastAfter(outputForClass, theOpCall,ASTnode.getIsTrownExpression());
	}

}
