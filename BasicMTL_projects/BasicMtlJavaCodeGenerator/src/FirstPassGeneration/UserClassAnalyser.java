/*
 * $Header: /tmp/cvs2svn/cvsroot/BasicMTL_projects/BasicMtlJavaCodeGenerator/src/FirstPassGeneration/UserClassAnalyser.java,v 1.2 2003-08-14 21:31:41 ffondeme Exp $
 * Created on 21 juil. 2003
 *
 */
package FirstPassGeneration;

import java.io.PrintWriter;
import java.util.Map;

import org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.QualifiedName;
import org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.UserClass;
import org.irisa.triskell.MT.utils.Java.JavaStringLiteralEncoder;
import org.irisa.triskell.MT.utils.Java.Mangler;
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
		int i,limit;
		String packageName=(String)context.get("PackageName");
		String baseFileName=(String)context.get("BaseFileName");
		String generatedLibMangledName=(String)context.get("GeneratedLibMangledName");
		//Opening files for generating class code
		//=======================================
		PrintWriter outputForClass = CommonFunctions.openFile(baseFileName+ASTnode.getMangle(),false);
		PrintWriter outputForInterface = CommonFunctions.openFile(baseFileName+ASTnode.getMangle()+"Interface",false);
		//Generating code for the class and its interface
		//===============================================
		CommonFunctions.generatePckgeImports(outputForClass,packageName);
		CommonFunctions.generatePckgeImports(outputForInterface,packageName);
		outputForClass.print("public class "+ASTnode.getMangle()+" extends BMTLObject");
		outputForClass.println(" implements "+ASTnode.getMangle()+"Interface {\n");
		outputForClass.println("/*==========================*/");
		outputForClass.println("/* CLASS PRIVATE ATTRIBUTES */");
		outputForClass.println("/*==========================*/");
		outputForClass.println("private "+generatedLibMangledName+" BMTL_MyLib;");
		outputForClass.println("private "+ASTnode.getMangle()+"Interface theCaller;");
		outputForClass.println("private java.util.Hashtable inheritanceMap;");
		outputForClass.println("/*=========================*/");
		outputForClass.println("/* PARENT(S) REFERENCE(S)  */");
		outputForClass.println("/*=========================*/");
		outputForInterface.print("public interface "+ASTnode.getMangle()+"Interface extends BMTLInterface");
		limit=ASTnode.getInheritance().size();
		// Inheritance from other defined classes
		//=======================================
		for (i=0;i<limit;i++) {
			QualifiedName aParentType = (QualifiedName)ASTnode.getInheritance().get(i);
			if (aParentType.getIsLocalType()) //class defined in this library
				{ 	String localParentName=aParentType.getLocalMangledName(); 
					outputForClass.println("private "+localParentName+" BMTLRef_"+localParentName+';');
					outputForInterface.print(", "+localParentName+"Interface");
				}
			if (aParentType.getIsExternType()) //class defined in another library
				{	String externParentName=aParentType.getExternMangledName();
					String externCompleteParentName=aParentType.getExternCompleteName(); //library mangled name . class mangled name
					outputForClass.println("private "+externCompleteParentName+" BMTLRef_"+externParentName+';');
					outputForInterface.println(", "+externCompleteParentName+"Interface");
				}
		}
		outputForInterface.println('{');
		outputForClass.println("/*==========================*/");
		outputForClass.println("/* CLASS DEFINED FEATURES */");
		outputForClass.println("/*==========================*/");
		context.put("OutputForClass",outputForClass);
		context.put("OutputForInterface",outputForInterface);
		return null;
}

	public void UserClassAfter(Object theClass,UserClass ASTnode,java.util.Map context) {
		int i,limit;
		String generatedLibMangledName=(String)context.get("GeneratedLibMangledName");
		String generatedLibCompleteMangledName=(String)context.get("GeneratedLibCompleteMangledName");
		PrintWriter outputForClass=(PrintWriter)context.get("OutputForClass");
		PrintWriter outputForInterface=(PrintWriter)context.get("OutputForInterface");
		outputForClass.println("/*====================*/");
		outputForClass.println("/* CLASS CONSTRUCTORS */");
		outputForClass.println("/*====================*/");
		outputForClass.println("/* Direct constructor */");
		outputForClass.println("/*====================*/");
		outputForClass.println("public "+ASTnode.getMangle()+'('+generatedLibCompleteMangledName+" libRef)");
		outputForClass.println("{ super(\""+JavaStringLiteralEncoder.encodeString(ASTnode.getName())+"\");");
		outputForClass.println("BMTL_MyLib = libRef;");
		outputForClass.println("BMTL_MyLib.recordNewInstance(\""+JavaStringLiteralEncoder.encodeString(ASTnode.getName())+"\",this);");
		outputForClass.println("inheritanceMap=new java.util.Hashtable();");
		outputForClass.println();
		// Call parents constructors
		//=======================================
		limit=ASTnode.getInheritance().size();
		for (i=0;i<limit;i++) {
			QualifiedName aParentType = (QualifiedName)ASTnode.getInheritance().get(i);
			if (aParentType.getIsLocalType()) //class defined in this library
				{	String localParentName=aParentType.getLocalMangledName();
					outputForClass.println("BMTLRef_"+localParentName+"= new "+localParentName+"(BMTL_MyLib,inheritanceMap,this);");
				} 
			if (aParentType.getIsExternType()) //class defined in another library
				{	String externParentName=aParentType.getExternMangledName();
					String externCompleteParentName=aParentType.getExternCompleteName(); //library mangled name . class mangled name
					String externLib=aParentType.getExternLibMangledName();
					String externLibCompleteName=aParentType.getExternLibCompleteName();
					outputForClass.println("BMTLRef_"+externParentName+"= new "+externCompleteParentName+"(("+externLibCompleteName+")BMTL_MyLib.BMTLRef_"+externLib+",inheritanceMap,this);");
				}
		}
		outputForClass.println("inheritanceMap.put(\""+generatedLibMangledName+"::"+ASTnode.getMangle()+"\",this);");
		outputForClass.println("theCaller=this; }\n");
		outputForClass.println("/* Indirect constructor              */");
		outputForClass.println("/* (called from a parent constructor */");
		outputForClass.println("/*===================================*/");
		outputForClass.println("public "+ASTnode.getMangle()+"("+generatedLibCompleteMangledName+" libRef,java.util.Hashtable map,"+ASTnode.getMangle()+"Interface o)");
		outputForClass.println("{ super(\""+JavaStringLiteralEncoder.encodeString(ASTnode.getName())+"\");");
		outputForClass.println("BMTL_MyLib = libRef;");
		outputForClass.println("inheritanceMap = map;");
		outputForClass.println("BMTL_MyLib.recordNewInstance(\""+JavaStringLiteralEncoder.encodeString(ASTnode.getName())+"\",theCaller);");
		// Call parents constructors
		//=======================================
		limit=ASTnode.getInheritance().size();
		for (i=0;i<limit;i++) {
			QualifiedName aParentType = (QualifiedName)ASTnode.getInheritance().get(i);
			if (aParentType.getIsLocalType()) //class defined in this library
				{	String localParentName=aParentType.getLocalMangledName();
					outputForClass.println("if (map.containsKey(\""+generatedLibMangledName+"::"+localParentName+"\")) ");
					outputForClass.println("\tBMTLRef_"+localParentName+"= ("+localParentName+")map.get(\""+generatedLibMangledName+"::"+localParentName+"\");");
					outputForClass.println("else BMTLRef_"+localParentName+"= new "+localParentName+"(BMTL_MyLib,map,o);");
				} 
			if (aParentType.getIsExternType()) //class defined in another library
				{	String externParentName=aParentType.getExternMangledName();
					String externCompleteParentName=aParentType.getExternCompleteName(); //library mangled name . class mangled name
					String externLib=aParentType.getExternLibMangledName();
					String externLibCompleteName=aParentType.getExternLibCompleteName();
					outputForClass.println("if (map.containsKey(\""+externCompleteParentName+"\"))");
					outputForClass.println("\tBMTLRef_"+externParentName+"= ("+externCompleteParentName+")map.get(\""+externCompleteParentName+"\");");
					outputForClass.println("else BMTLRef_"+externParentName+"= new "+externCompleteParentName+"(("+externLibCompleteName+")BMTL_MyLib.BMTLRef_"+externLib+",map,o);");
				}
		}
		outputForClass.println("theCaller=o;");
		outputForClass.println("map.put(\""+generatedLibMangledName+"::"+ASTnode.getMangle()+"\",this);");
		outputForClass.println("}");
		outputForClass.println();
		outputForClass.println("/*=================*/");
		outputForClass.println("/* CLASS FINALIZER */");
		outputForClass.println("/*=================*/");
		outputForClass.println("public boolean isValid = true;");
		outputForClass.println("public void delete()");
		outputForClass.println("{ if (isValid) {");
		limit=ASTnode.getInheritance().size();
		for (i=0;i<limit;i++) {
			QualifiedName aParentType = (QualifiedName)ASTnode.getInheritance().get(i);
			if (aParentType.getIsLocalType()) //class defined in this library
				{	String localParentName=aParentType.getLocalMangledName();
					outputForClass.println("    this.getRef_"+localParentName+"().delete();");
				} 
			if (aParentType.getIsExternType()) //class defined in another library
				{	String externParentName=aParentType.getExternMangledName();
					outputForClass.println("    this.getRef_"+externParentName+"().delete();");
				}
		}
		outputForClass.println("    BMTL_MyLib.removeInstance(\""+JavaStringLiteralEncoder.encodeString(ASTnode.getName())+"\",this);");
		outputForClass.println("    this.isValid = false;");
		outputForClass.println("  }");
		outputForClass.println('}');
		outputForClass.println();
		outputForClass.println("/*====================*/");
		outputForClass.println("/* Lib & Refs METHODS */");
		outputForClass.println("/*====================*/");
		outputForClass.println("public BMTLLibrary getLibrary()");
		outputForClass.println("{ return (BMTLLibrary)BMTL_MyLib; }");
		limit=ASTnode.getInheritance().size();
		// Inheritance from other defined classes
		//=======================================
		for (i=0;i<limit;i++) {
			QualifiedName aParentType = (QualifiedName)ASTnode.getInheritance().get(i);
			if (aParentType.getIsLocalType()) //class defined in this library
				{ 	String localParentName=aParentType.getLocalMangledName(); 
					outputForClass.println("public "+localParentName+" getRef_"+localParentName+"()");
					outputForClass.println("{ return this."+"BMTLRef_"+localParentName+"; }");
					outputForClass.println();
				}
			if (aParentType.getIsExternType()) //class defined in another library
				{	String externParentName=aParentType.getExternMangledName();
					String externCompleteParentName=aParentType.getExternCompleteName(); //library mangled name . class mangled name
					outputForClass.println("public "+externCompleteParentName+" getRef_"+externParentName+"()");
					outputForClass.println("{ return this."+"BMTLRef_"+externParentName+"; }");
					outputForClass.println();
				}
		}
		
		outputForClass.println("public org.irisa.triskell.MT.DataTypes.Java.Type getType()");
		outputForClass.println("{ return this.BMTL_MyLib."+Mangler.mangle("the", ASTnode.getMangle())+"; }");
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