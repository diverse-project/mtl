/*
 * $Header: /tmp/cvs2svn/cvsroot/BasicMTL_projects/BasicMtlJavaCodeGenerator/src/SecondPassGeneration/UserClassAnalyser.java,v 1.6 2003-09-17 07:15:23 jpthibau Exp $
 * Created on 21 juil. 2003
 *
 */
package SecondPassGeneration;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

import org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.*;
import org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.signatures.GetReferenceSignature;
import org.irisa.triskell.MT.utils.Java.AWK;
import org.irisa.triskell.MT.visitors.Java.GenericVisitor.Visitable;
import org.irisa.triskell.MT.visitors.Java.GenericVisitor.Visitor;

/**
 * @author jpthibau
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class UserClassAnalyser extends TLLTopDownVisitor.UserClassAnalyser {

	public Object UserClassBefore(UserClass ASTnode,java.util.Map context) {
		context.put("CurrentClass", ASTnode);
		int i,limit;
		String packageName=(String)context.get("PackageName");
		String baseFileName=(String)context.get("BaseFileName");
		String generatedLibMangledName=(String)context.get("GeneratedLibMangledName");
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
				//TODO find a better solution to improve the code
				//the 8 following lines is a patch to the following PB
				//when BasicMtlASTView::Library is inherited in
				//BasicMtlASTWithAssociationView::aClass, ASTNode just appear alone
				//in the generated code but BasicMtlASTView.ASTNode is required ! 
				QualifiedName relayer = aSignature.getParentThatRelayOp();
				if ((aSignature.getReturnedType().getIsLocalType())
				 && (aSignature.getReturnedType().getDeclarationName().indexOf('.')==-1) //no point in the type
				 && (relayer.size()>1)) {//comes from another lib
					if (relayer.size()>2)
						System.err.println("relayer having more than 2 strings in its qualified name ????");
					else //add the extern lib package name for this returned type
						returnType = relayer.get(0)+"."+returnType;
				}
				outputForClass.print("public "+returnType+' '+aSignature.getOpMangle()+" (");
				int arguments=aSignature.getArgsCount();
				if (arguments > 0) {
						argtsGenSymbols=new java.util.Vector();
						for(int j=0;j<aSignature.getArgsCount();j++) {
							String genSymbol=CommonFunctions.generateNewSymbol();
							outputForClass.print(aSignature.getArgsTypes(j).getDeclarationName()+' '+genSymbol);
							argtsGenSymbols.addElement(genSymbol);
							if (j<arguments-1) outputForClass.print(',');
						}
				}
				outputForClass.print(')');
				if (aSignature.getThrowsException())
					outputForClass.print(" throws Throwable");
				outputForClass.println();
				String relayName = AWK.mergeCollection(aSignature.getParentThatRelayOp(), "::");
				GetReferenceSignature relay = (GetReferenceSignature)refOps.get(relayName);
				if (relay == null) {
					relay = new GetReferenceSignature(aSignature.getParentThatRelayOp());
					refOps.put(relayName, relay);
				}
				outputForClass.print("{ return ("+relay.getOpMangle()+"()."+aSignature.getOpMangle()+" (");
				if (arguments>0)
						for (int j=0;j<arguments;j++) {
							outputForClass.print((String)argtsGenSymbols.get(j));
							if (j<arguments-1) outputForClass.print(',');
						}
				outputForClass.println(")); }");
				outputForClass.println();
			}
		context.put("CurrentClassMangledName",ASTnode.getMangle());
		context.put("OutputForClass",outputForClass);
		context.put("OutputForInterface",outputForInterface);
		return null;
}
	public void UserClassAfter(Object theClass,UserClass ASTnode,java.util.Map context) {
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

	public void analyse(Visitable node, Visitor visitor, Map context) {
		if (!((Boolean)((UserClass)node).getProperty("ManualMangling").getValue()).booleanValue())
			super.analyse(node, visitor, context);
	}

}