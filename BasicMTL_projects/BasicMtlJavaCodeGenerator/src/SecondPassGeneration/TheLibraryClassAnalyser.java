/*
 * $Header: /tmp/cvs2svn/cvsroot/BasicMTL_projects/BasicMtlJavaCodeGenerator/src/SecondPassGeneration/TheLibraryClassAnalyser.java,v 1.4 2003-08-20 16:07:34 ffondeme Exp $
 * Created on 21 juil. 2003
 *
 */
package SecondPassGeneration;

import java.io.*;
import java.util.HashMap;
import java.util.Hashtable;

import org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.*;
import org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.signatures.GetReferenceSignature;
import org.irisa.triskell.MT.utils.Java.AWK;

/**
 * @author jpthibau
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class TheLibraryClassAnalyser extends TLLTopDownVisitor.TheLibraryClassAnalyser {

	public Object TheLibraryClassBefore(TheLibraryClass ASTnode,java.util.Map context) {
		int i,limit;
		context.put("CurrentClass", ASTnode);
		String packageName=(String)context.get("PackageName");
		String baseFileName=(String)context.get("BaseFileName");
		//Opening files for generating class code
		//=======================================
		PrintWriter outputForClass = CommonFunctions.openFile(baseFileName+ASTnode.getMangle(),true);
		PrintWriter outputForInterface = CommonFunctions.openFile(baseFileName+ASTnode.getMangle()+"Interface",true);
		//Generating code for the class and its interface
		//===============================================
		outputForClass.println("/*===================*/");
		outputForClass.println("/* INHERITED METHODS */");
		outputForClass.println("/*===================*/");
		HashMap refOps = new HashMap();
		for (i=0;i<ASTnode.cardInheritedSignatures();i++)
			{	java.util.Vector argtsGenSymbols=null;
				InheritedOpSignature aSignature=ASTnode.getInheritedSignatures(i);
				String returnType = aSignature.getReturnedType().getDeclarationName();//aSignature.getReturnedType().getIsLocalType() ? aSignature.getReturnedType().getLocalMangledName() : aSignature.getReturnedType().getExternCompleteName();
				outputForClass.print("public "+returnType+' '+aSignature.getOpMangle()+" (");
				int arguments=aSignature.getArgsCount();
				if (arguments > 0) {
						argtsGenSymbols=new java.util.Vector();
						for(int j=0;j<aSignature.getArgsCount();j++) {
							String genSymbol=CommonFunctions.generateNewSymbol();
							outputForClass.print(aSignature.getArgsTypes(j).getExternMangledName()+' '+genSymbol);
							argtsGenSymbols.addElement(genSymbol);
							if (j<arguments-1) outputForClass.print(',');
						}
				}
				String relayName = AWK.mergeCollection(aSignature.getParentThatRelayOp(), "::");
				GetReferenceSignature relay = (GetReferenceSignature)refOps.get(relayName);
				if (relay == null) {
					relay = new GetReferenceSignature(aSignature.getParentThatRelayOp());
					refOps.put(relayName, relay);
				}
				outputForClass.print(")\n{ return ("+relay.getOpMangle()+"()."+aSignature.getOpMangle()+" (");
				if (arguments>0)
						for (int j=0;j<arguments;j++) {
							outputForClass.print((String)argtsGenSymbols.get(j));
							if (j<arguments-1) outputForClass.print(',');
						}
				outputForClass.print(")); }\n\n");	
			}
		context.put("CurrentClassMangledName",ASTnode.getMangle());
		context.put("OutputForClass",outputForClass);
		context.put("OutputForInterface",outputForInterface);
		return null;
}

	public void TheLibraryClassAfter(Object theClass,TheLibraryClass ASTnode,java.util.Map context) {
	context.remove("CurrentClass");
	PrintWriter outputForClass=(PrintWriter)context.get("OutputForClass");
	PrintWriter outputForInterface=(PrintWriter)context.get("OutputForInterface");
	outputForClass.println('}');
	outputForInterface.println('}');
	outputForClass.flush();
	outputForInterface.flush();
	outputForClass.close();
	outputForInterface.close();	
	}
}