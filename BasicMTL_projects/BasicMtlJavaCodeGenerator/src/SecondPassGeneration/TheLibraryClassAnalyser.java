/*
 * $Header: /tmp/cvs2svn/cvsroot/BasicMTL_projects/BasicMtlJavaCodeGenerator/src/SecondPassGeneration/TheLibraryClassAnalyser.java,v 1.2 2003-08-14 21:31:40 ffondeme Exp $
 * Created on 21 juil. 2003
 *
 */
package SecondPassGeneration;

import java.io.*;
import org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.*;

/**
 * @author jpthibau
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class TheLibraryClassAnalyser extends TLLTopDownVisitor.TheLibraryClassAnalyser {

	public Object TheLibraryClassBefore(TheLibraryClass ASTnode,java.util.Map context) {
		int i,limit;
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
		for (i=0;i<ASTnode.cardInheritedSignatures();i++)
			{	java.util.Vector argtsGenSymbols=null;
				InheritedOpSignature aSignature=ASTnode.getInheritedSignatures(i);
				String returnType = aSignature.getReturnedType().getIsLocalType() ? aSignature.getReturnedType().getLocalMangledName() : aSignature.getReturnedType().getExternCompleteName();
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
				String relay = aSignature.getParentThatRelayOp().getIsLocalType() ? aSignature.getParentThatRelayOp().getLocalMangledName() : aSignature.getParentThatRelayOp().getExternMangledName();
				outputForClass.print(")\n{ return (getRef_"+relay+"()."+aSignature.getOpMangle()+" (");
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