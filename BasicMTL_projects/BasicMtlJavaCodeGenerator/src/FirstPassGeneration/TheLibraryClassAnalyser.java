/*
 * $Header: /tmp/cvs2svn/cvsroot/BasicMTL_projects/BasicMtlJavaCodeGenerator/src/FirstPassGeneration/TheLibraryClassAnalyser.java,v 1.1 2003-08-08 15:41:10 jpthibau Exp $
 * Created on 21 juil. 2003
 *
 */
package FirstPassGeneration;

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
		PrintWriter outputForClass = CommonFunctions.openFile(baseFileName+ASTnode.getMangle(),false);
		PrintWriter outputForInterface = CommonFunctions.openFile(baseFileName+ASTnode.getMangle()+"Interface",false);
		//Generating code for the class and its interface
		//===============================================
		CommonFunctions.generatePckgeImports(outputForClass,packageName);
		CommonFunctions.generatePckgeImports(outputForInterface,packageName);
		outputForClass.print("public class "+ASTnode.getMangle()+" extends BMTLLibrary");
		outputForClass.println(" implements "+ASTnode.getMangle()+"Interface {\n");
		outputForClass.println("/*==========================*/");
		outputForClass.println("/* CLASS PRIVATE ATTRIBUTES */");
		outputForClass.println("/*==========================*/");
		outputForClass.println("private "+ASTnode.getMangle()+"Interface theCaller;");
		outputForClass.println("private java.util.Hashtable inheritanceMap;");
		outputForClass.println("private static java.util.Hashtable InstancesMap;\n");
		outputForClass.println("/*=========================*/");
		outputForClass.println("/* PARENT(S) REFERENCE(S)  */");
		outputForClass.println("/*=========================*/");
		outputForInterface.print("public interface "+ASTnode.getMangle()+"Interface extends BMTLInterface");
		limit=ASTnode.getInheritance().size();
		// Inheritance from other defined libraries
		//=======================================
		for (i=0;i<limit;i++) {
			QualifiedName aParentType = (QualifiedName)ASTnode.getInheritance().get(i);
			if (aParentType.getIsExternType()) //libraryClass defined in another library
				{	String externParentName=aParentType.getExternMangledName();
					String externCompleteParentName=aParentType.getExternCompleteName(); //library mangled name . class mangled name
					outputForClass.println("private "+externCompleteParentName+" BMTLRef_"+externParentName+";");
					outputForInterface.println(", "+externCompleteParentName+"Interface");
				}
		}
		outputForInterface.println("{");
		outputForClass.println("/*==========================*/");
		outputForClass.println("/* CLASS DEFINED FEATURES */");
		outputForClass.println("/*==========================*/");
		context.put("OutputForClass",outputForClass);
		context.put("OutputForInterface",outputForInterface);
		return null;
}


	public void TheLibraryClassAfter(Object theClass,TheLibraryClass ASTnode,java.util.Map context) {
		int i,limit;
		String generatedLibMangledName=(String)context.get("GeneratedLibMangledName");
		PrintWriter outputForClass=(PrintWriter)context.get("OutputForClass");
		PrintWriter outputForInterface=(PrintWriter)context.get("OutputForInterface");
		BasicMtlLibrary theLib=ASTnode.getTheLibrary();
		limit=theLib.cardClasses();
		for (i=0;i<limit;i++) {
			outputForClass.println("private Class the"+theLib.getClasses(i).getMangle()+"="+theLib.getClasses(i).getMangle()+".class;");
		}
		outputForClass.println("public Class getMetaClass(String type)");
		outputForClass.println("{ if (type.getLibrary().equals(\""+theLib.getMangle()+"\")) {");
		limit=theLib.cardClasses();
		for (i=0;i<limit;i++) {
			outputForClass.println("if (type.getClass().equals(\""+theLib.getClasses(i).getMangle()+"\")) return this.the"+theLib.getClasses(i).getMangle()+";");
		}
		outputForClass.println("}\nreturn null;");
		outputForClass.println("/*===========================*/");
		outputForClass.println("/* LIBRARYCLASS CONSTRUCTORS */");
		outputForClass.println("/*===========================*/");
		outputForClass.println("private void buildAllUsedLibs() {");
		limit=theLib.cardUsedLibs();
		for(i=0;i<limit;i++)
			{	QualifiedName aUsedLib=(QualifiedName)theLib.getUsedLibs(i);
				outputForClass.println("BMTLRef_"+aUsedLib.getExternMangledName()+"=new "+aUsedLib.getExternCompleteName()+"();");
			}
		outputForClass.println("}");
		outputForClass.println("/* Direct constructor */");
		outputForClass.println("/*====================*/");
		outputForClass.println("public "+ASTnode.getMangle()+"()");
		outputForClass.println("{ super(\""+ASTnode.getMangle()+"\");");
		outputForClass.println("inheritanceMap=new java.util.Hashtable();\n");
		outputForClass.println("this.buildAllUsedLibs();");
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
					outputForClass.println("BMTLRef_"+externParentName+"= new "+externCompleteParentName+"(("+externLibCompleteName+")BMTL_MyLib.get_"+externLib+"(),inheritanceMap,this);");
				}
		}
		outputForClass.println("inheritanceMap.put(\""+generatedLibMangledName+"::"+ASTnode.getMangle()+"\",this);");
		outputForClass.println("theCaller=this; }\n");
		outputForClass.println("/* Indirect constructor              */");
		outputForClass.println("/* (called from a parent constructor */");
		outputForClass.println("/*===================================*/");
		outputForClass.println("public "+ASTnode.getMangle()+"(java.util.Hashtable map,"+ASTnode.getMangle()+"Interface o)");
		outputForClass.println("{ super(\""+ASTnode.getMangle()+"\");");
		outputForClass.println("this.buildAllUsedLibs();");
		outputForClass.println("inheritanceMap = map;");
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
					outputForClass.println("else BMTLRef_"+externParentName+"= new "+externCompleteParentName+"(("+externLibCompleteName+")BMTL_MyLib.get_"+externLib+"(),map,o);");
				}
		}
		outputForClass.println("theCaller=o;");
		outputForClass.println("map.put(\""+generatedLibMangledName+"::"+ASTnode.getMangle()+"\",this);");
		outputForClass.println("}\n");
		outputForClass.println("/*=========================*/");
		outputForClass.println("/* CLASS UTILITY FUNCTIONS */");
		outputForClass.println("/*=========================*/");
		outputForClass.println("public void recordNewInstance(String className,Object instance)");
		outputForClass.println("{ this.InstancesMap.put(className,instance); }\n");
		outputForClass.println("public void removeInstance(String className)");
		outputForClass.println("{ this.InstancesMap.remove(className); }\n");
		outputForClass.println("/*====================*/");
		outputForClass.println("/* Lib & Refs METHODS */");
		outputForClass.println("/*====================*/");
		limit=ASTnode.getInheritance().size();
			for (i=0;i<limit;i++) {
				QualifiedName aParentType = (QualifiedName)ASTnode.getInheritance().get(i);
				if (aParentType.getIsLocalType()) //class defined in this library
					{	String localParentName=aParentType.getLocalMangledName();
						outputForClass.println("public "+localParentName+" getRef_"+localParentName+"()");
						outputForClass.println("{ return ("+localParentName+")inheritanceMap.get\""+generatedLibMangledName+"::"+localParentName+"\"); }");
					} 
				if (aParentType.getIsExternType()) //class defined in another library
					{	String externParentName=aParentType.getExternMangledName();
						String externCompleteParentName=aParentType.getExternCompleteName(); //library mangled name . class mangled name
						String externLib=aParentType.getExternLibMangledName();
						String externLibCompleteName=aParentType.getExternLibCompleteName();
						outputForClass.println("public "+externCompleteParentName+" getRef_"+externParentName+"()");
						outputForClass.println("{ return ("+externCompleteParentName+")inheritanceMap.get(\""+externLib+"::"+externParentName+"\"); }");
					}
			}
			outputForClass.println("public static final String myType="+generatedLibMangledName+"::"+ASTnode.getMangle()+"\";");
			outputForClass.println("public static String getType()");
			outputForClass.println("{ return myType; }");
			outputForClass.close();
			outputForInterface.close();	
	}
}