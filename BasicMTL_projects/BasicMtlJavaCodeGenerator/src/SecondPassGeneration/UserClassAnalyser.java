/*
 * $Header: /tmp/cvs2svn/cvsroot/BasicMTL_projects/BasicMtlJavaCodeGenerator/src/SecondPassGeneration/UserClassAnalyser.java,v 1.8 2003-12-16 07:51:46 jpthibau Exp $
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
	/***********************************************************
	 * ACCEPT FOR CALLABLE VISITOR
	 * ****************************
	 *
	accept (v : DefaultVisitors::CallableVisitor; context : Standard::OclAny) : Standard::OclAny
	{	try {
			return v.visit('CLASSNAME',self,context);
		}
		catch w:DefaultVisitors::CallableVisitor {
			return v.visitOclAny(self,context);
			}
	} */
	outputForClass.println("public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOclAnyInterface BMTL_accept(DefaultVisitors.BMTL_CallableVisitorInterface BMTL_v,org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOclAnyInterface BMTL_context) throws Throwable {");
	outputForClass.println("try {");
	outputForClass.println("return (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOclAnyInterface)CommonFunctions.toBMTLDataType(BMTL_v.BMTL_visit(new BMTLString(\""+ASTnode.getName()+"\"),theCaller,BMTL_context));");
	outputForClass.println("}");
	outputForClass.println("catch (java.lang.Throwable throwable) { try {");
	outputForClass.println("DefaultVisitors.BMTL_CallableVisitorInterface BMTL_w=(DefaultVisitors.BMTL_CallableVisitorInterface)throwable;");
	outputForClass.println("} catch(Exception e) { throw throwable; }");
	outputForClass.println("return (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOclAnyInterface)CommonFunctions.toBMTLDataType(BMTL_v.BMTL_visitOclAny(theCaller,BMTL_context));");
	outputForClass.println("}");
	outputForClass.println("}");
	/***********************************************************
	 * ACCEPT FOR INVOKABLE VISITOR
	 * ****************************
	 *
	accept (v : DefaultVisitors::InvokableVisitor; context : Standard::OclAny) : Standard::OclAny
	{	vFather : DefaultVisitors::CallableVisitor;
		JavaCode [try { java.lang.reflect.Method m = BMTL_v.getClass().getMethod("BMTL_visitCLASSNAME",new Class[\]{org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOclAnyInterface.class,org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOclAnyInterface.class})];
		JavaCode [return (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOclAnyInterface)m.invoke(BMTL_v,new Object[\]{this,BMTL_context})];
		JavaCode [} catch (Exception e) {} BMTL_vFather=null];
		vFather := v.getParent();
		if isNull(vFather).[not]()
			{ return self.accept(vFather,context); }
		else
			{ return v.visitOclAny(self,context); }
	}
	} */
	outputForClass.println("public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOclAnyInterface BMTL_accept(DefaultVisitors.BMTL_InvokableVisitorInterface BMTL_v,org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOclAnyInterface BMTL_context) throws Throwable {");
	outputForClass.println("DefaultVisitors.BMTL_CallableVisitorInterface BMTL_vFather=null;");
	outputForClass.println("try { java.lang.reflect.Method m = BMTL_v.getClass().getMethod(\"BMTL_visit"+ASTnode.getName()+"\",new Class[]{org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOclAnyInterface.class,org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOclAnyInterface.class});");
	outputForClass.println("return (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOclAnyInterface)m.invoke(BMTL_v,new Object[]{this,BMTL_context});");
	outputForClass.println("} catch (Exception e) {} BMTL_vFather=null;");
	outputForClass.println("BMTL_vFather=(DefaultVisitors.BMTL_CallableVisitorInterface)CommonFunctions.toBMTLDataType(BMTL_v.BMTL_getParent());");
	outputForClass.println("BooleanValue GenSymbol1 = (BooleanValue)theCaller.BMTL_isNull(BMTL_vFather).BMTL_not();");
	outputForClass.println("if (GenSymbol1.getTheBoolean()) {");
	outputForClass.println("return (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOclAnyInterface)CommonFunctions.toBMTLDataType(this.BMTL_accept(BMTL_vFather,BMTL_context));");
	outputForClass.println("} else {");
	outputForClass.println("return (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOclAnyInterface)CommonFunctions.toBMTLDataType(BMTL_v.BMTL_visitOclAny(theCaller,BMTL_context));");
	outputForClass.println("}");
	outputForClass.println("}");
	outputForClass.println('}');
	outputForInterface.println("org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOclAnyInterface BMTL_accept(DefaultVisitors.BMTL_CallableVisitorInterface BMTL_v,org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOclAnyInterface BMTL_context) throws Throwable;");
	outputForInterface.println("org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOclAnyInterface BMTL_accept(DefaultVisitors.BMTL_InvokableVisitorInterface BMTL_v,org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOclAnyInterface BMTL_context) throws Throwable;");
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