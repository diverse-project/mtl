/*
 * Created on 21 juil. 2003
 * $Id: TheLibraryClassAnalyser.java,v 1.11 2004-02-16 17:36:44 dvojtise Exp $
 * Authors : jpthibau
 * 
 * Copyright 2004 - INRIA - LGPL license
 */
package FirstPassGeneration;

import java.io.*;
import java.util.Collection;
import java.util.Iterator;
//import java.util.List;
import java.util.Vector;

import org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.*;
import org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.signatures.GetReferenceSignature;
import org.irisa.triskell.MT.utils.Java.AWK;
import org.irisa.triskell.MT.utils.Java.JavaStringLiteralEncoder;
import org.irisa.triskell.MT.utils.Java.Mangler;
import org.irisa.triskell.MT.visitors.Java.AnalysingVisitor.Property;

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
		CommonFunctions.generatePackageImports(outputForClass,packageName);
		CommonFunctions.generatePackageImports(outputForInterface,packageName);
		outputForClass.print("public class "+ASTnode.getMangle()+" extends BMTLLibrary");
		outputForClass.println(" implements "+ASTnode.getMangle()+"Interface {\n");
		outputForClass.println("/*==========================*/");
		outputForClass.println("/* CLASS PRIVATE ATTRIBUTES */");
		outputForClass.println("/*==========================*/");
		outputForClass.println("private "+ASTnode.getMangle()+"Interface theCaller;");
		outputForClass.println("private java.util.Hashtable inheritanceMap;");
		BasicMtlLibrary theLib=ASTnode.getTheLibrary();
		outputForClass.println("static final private BMTLString myName=new BMTLString(\""+theLib.getName()+"\");");
		outputForClass.println("static final private BMTLSetInterface allKnownClasses=initAllKnownClasses();");
		outputForClass.println("static private BMTLSetInterface initAllKnownClasses()");
		outputForClass.println("{	BMTLSetInterface theSet=new BMTLSet();");
		limit=theLib.cardClasses();
		for (i=0;i<limit;i++) {
			UserDefinedClass theClass=theLib.getClasses(i);
			if (! theClass.getName().equals(theLib.getName())) {
			outputForClass.print("	theSet=theSet.BMTL_including(new BMTLOrderedSet(new Value[]{new BMTLString(\""+ASTnode.getName()+"\"),new BMTLString(\""+theClass.getName()+"\")");
			InheritedTypesList theClassInheritance=theClass.getInheritance();
			for (int k=0;k<theClassInheritance.size();k++)
				{ QualifiedName qn=(QualifiedName)theClassInheritance.get(k);
				  if (qn.getIsLocalType())
				  	{ if (qn.size()==1) outputForClass.print(",new BMTLString(\""+(String)qn.get(0)+"\")");
				  	  else outputForClass.print(",new BMTLString(\""+(String)qn.get(1)+"\")");
				  	} 
				}
			outputForClass.println("}));");
			}
		}		
		outputForClass.println("	return theSet; }");
		outputForClass.println("/*=========================*/");
		outputForClass.println("/* PARENT(S) REFERENCE(S)  */");
		outputForClass.println("/*=========================*/");
		outputForInterface.print("public interface "+ASTnode.getMangle()+"Interface extends BMTLLibInterface");
		limit=ASTnode.getInheritance().size();
		// Inheritance from other defined libraries
		//=======================================
		for (i=0;i<limit;i++) {
			QualifiedName aParentType = (QualifiedName)ASTnode.getInheritance().get(i);
			String externParentName=aParentType.getExternMangledName();
			String declarationName = aParentType.getDeclarationName();
			outputForClass.println("public "+declarationName+" BMTLRef_"+externParentName+';');
			outputForInterface.println(", "+declarationName);
		}
		outputForInterface.println('{');
		outputForClass.println("/*==========================*/");
		outputForClass.println("/* CLASS DEFINED FEATURES */");
		outputForClass.println("/*==========================*/");
		java.util.Vector associationsNames=new java.util.Vector();
		limit=theLib.cardClasses();
		for (i=0;i<limit;i++) {
			UserDefinedClass theClass=theLib.getClasses(i);
			if (! theClass.getName().equals(theLib.getName())
				&& theClass.getName().startsWith("association"))
				associationsNames.addElement(theClass.getName());
		}
		context.put("KnownAssociations",associationsNames);
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
			if ((! (theLib.getClasses(i) instanceof TheLibraryClass)) &&  (theLib.getClasses(i).getProperty("type") != null  || !((Boolean)theLib.getClasses(i).getProperty("ManualMangling").getValue()).booleanValue())) {
				String qn = theLib.getClasses(i).getName();
				String fieldName = Mangler.mangle("the", theLib.getClasses(i).getMangle());
				outputForClass.println("public org.irisa.triskell.MT.DataTypes.Java.Type "+fieldName+';');
			}
		}
		outputForClass.println("static {");
		outputForClass.println("  java.util.LinkedList al = new java.util.LinkedList();");
		outputForClass.println("  al.add(\"" + JavaStringLiteralEncoder.encodeString(theLib.getName()) + "\");");
		limit = ASTnode.getInheritance().size();
		for (i = 0; i < limit; ++i) {
			outputForClass.println("  al.addAll(" + ((QualifiedName)ASTnode.getInheritance().get(i)).getExternLibCompleteName() + ".CompatibleNames);");
		}
		outputForClass.println("  CompatibleNames = al;");
		outputForClass.println('}');
		outputForClass.println();
		outputForClass.println("public static final java.util.List CompatibleNames;");
		outputForClass.println("public org.irisa.triskell.MT.DataTypes.Java.Type getMetaClass(String [] type)");
		outputForClass.println("{ if (type == null || type.length == 0)");
		outputForClass.println("    return null;");
		outputForClass.println("  if (CompatibleNames.contains(type[0])) {");
		outputForClass.println("   if (type.length > 2)");
		outputForClass.println("     return null;");
		outputForClass.println("   else if (type.length == 2)");
		outputForClass.println("    return this.getMetaClass(new String [] {type[1]});");
		outputForClass.println("   else //if (type.length == 1)");
		outputForClass.println("    return this.getType();");
		outputForClass.println("  }");
		outputForClass.println("  if (type.length == 1) {");
		limit=theLib.cardClasses();
		for (i=0;i<limit;i++) {
			if ((! (theLib.getClasses(i) instanceof TheLibraryClass)) &&  (theLib.getClasses(i).getProperty("type") != null || !((Boolean)theLib.getClasses(i).getProperty("ManualMangling").getValue()).booleanValue()))
				{	outputForClass.println("    if (type[0].equals(\""+JavaStringLiteralEncoder.encodeString(theLib.getClasses(i).getName())+"\")) return this."+Mangler.mangle("the", theLib.getClasses(i).getMangle())+';');
					//refinement
					InheritedTypesList refinedClasses = theLib.getClasses(i).getRefinement();
					if (refinedClasses.size() != 0)
					{	for (int j=0;j<refinedClasses.size();j++) {
							QualifiedName aRefinedClass=(QualifiedName)refinedClasses.get(j);
							String classShortName =null;
							if (aRefinedClass.size()==1) classShortName=(String)aRefinedClass.get(0);
							else if (aRefinedClass.size()==2) classShortName=(String)aRefinedClass.get(1); 
							if (! classShortName.equals(theLib.getClasses(i).getName()))
							outputForClass.println("    if (type[0].equals(\""+JavaStringLiteralEncoder.encodeString(classShortName)+"\")) return this."+Mangler.mangle("the", theLib.getClasses(i).getMangle())+';');
						}
					}
				}
		}
		limit = ASTnode.getInheritance().size();
		if (limit >= 1) {
			outputForClass.println("    Type ret;");
			outputForClass.println("    java.util.ArrayList found = new java.util.ArrayList(" + limit + ");");
			for (i = 0; i < limit; ++i) {
				outputForClass.println("    ret = this." + new GetReferenceSignature((QualifiedName)ASTnode.getInheritance().get(i)).getOpLibMangle() + "().getMetaClass(type);");
				outputForClass.println("    if (ret != null) {");
				outputForClass.println("      found.add(\"" + JavaStringLiteralEncoder.encodeString(AWK.mergeCollection((Collection)ASTnode.getInheritance().get(i), "::")) + "\");");
				outputForClass.println("    }");
			}
			outputForClass.println("    if (found.size() == 1)");
			outputForClass.println("      return ret;");
			outputForClass.println("    else if (found.size() > 1)");
			outputForClass.println("      throw new RuntimeException(\"Cannot choose metatype \" + type[0] + \" present in the inherited libraries \" + AWK.mergeCollection(found, \", \"));");
		}
		limit = theLib.cardUsedLibs();
		for(i=0;i<limit;i++)
			{	QualifiedName aUsedLib=(QualifiedName)theLib.getUsedLibs(i);
				if (!aUsedLib.isModelType && !aUsedLib.isRepositoryModel && !ASTnode.getInheritance().contains(aUsedLib)) {
					outputForClass.println("    if (type[0].equals(\""+JavaStringLiteralEncoder.encodeString((String)aUsedLib.get(0))+"\")) return "+aUsedLib.getExternCompleteName()+".myType;");
				}
			}
		outputForClass.println("    return null;");
		outputForClass.println("  }");
		limit=theLib.cardUsedLibs() + theLib.cardUsedModels();
		if (limit > 0) {
			outputForClass.println("  else {");
			outputForClass.println("    String [] unqualifiedType = new String [type.length-1];");
			outputForClass.println("    System.arraycopy(type, 1, unqualifiedType, 0, unqualifiedType.length);");
			outputForClass.println("    Type ret;");
			boolean first = true;
			limit = theLib.cardUsedModels();
			for(i=0;i<limit;i++)
				{	ModelRef aUsedLib=theLib.getUsedModels(i);
					if (first) {
						first = false;
						outputForClass.print("    ");
					} else
						outputForClass.print("    else ");
					boolean isRepRef = (aUsedLib instanceof RepositoryRef) || (aUsedLib instanceof TypedModelRef) && ((TypedModelRef)aUsedLib).getView().equals("RepositoryModel");
					outputForClass.println("if (type[0].equals(\"" + JavaStringLiteralEncoder.encodeString((String)aUsedLib.getName()) + "\")) " + (isRepRef?"try ":"") +"{");
					outputForClass.println("      return this."+Mangler.mangle("BMTL_", aUsedLib.getName())+" == null ? null : this."+Mangler.mangle("BMTL_", aUsedLib.getName())+".getMetaClass(unqualifiedType);");
					outputForClass.print("    }");
					if (isRepRef)
						outputForClass.println(" catch (UnknownElementException x) {}");
					else
						outputForClass.println();
				}
			limit = theLib.cardUsedLibs();
			for(i=0;i<limit;i++)
				{	QualifiedName aUsedLib=(QualifiedName)theLib.getUsedLibs(i);
					if (!aUsedLib.isModelType && !aUsedLib.isRepositoryModel) {
						outputForClass.println("    ret = this.BMTLRef_"+aUsedLib.getExternMangledName()+".getMetaClass(unqualifiedType);");
						outputForClass.println("    if (ret != null) return ret;");
					}
				}
			outputForClass.println("    return null;");
			outputForClass.println("  }");
		} else {
			outputForClass.println("  else {");
			outputForClass.println("    return null;");
			outputForClass.println("  }");
		}
		outputForClass.println('}');
		outputForClass.println();
		outputForClass.println("public BMTLLibInterface getLibrary() {");
		outputForClass.println("  return (BMTLLibrary)this.theCaller;");
		outputForClass.println('}');
		outputForClass.println();
		outputForClass.println("/*===========================*/");
		outputForClass.println("/* LIBRARYCLASS CONSTRUCTORS */");
		outputForClass.println("/*===========================*/");
		//@TODO check this piece of code...
		boolean hasParameters = theLib.cardUsedModels() != 0;
		for (i = theLib.cardUsedLibs()-1; (!hasParameters) && i >= 0; --i) {
			hasParameters = theLib.getUsedLibs(i).isModelType || theLib.getUsedLibs(i).isRepositoryModel;
		}
		limit=theLib.cardUsedLibs();
		for(i=0;i<limit;i++)
			{	QualifiedName aUsedLib=(QualifiedName)theLib.getUsedLibs(i);
				outputForClass.println("public " + aUsedLib.getDeclarationName() + " BMTLRef_"+aUsedLib.getExternMangledName()+';');
//TODO check ComeBack before refine introduction
//				outputForClass.println("public " + aUsedLib.getDeclarationName() + " getRef_"+aUsedLib.getExternMangledName()+"() {");
//				outputForClass.println("return BMTLRef_"+aUsedLib.getExternMangledName()+"; }");
//				outputForInterface.println("public " + aUsedLib.getDeclarationName() + " getRef_"+aUsedLib.getExternMangledName()+"();");
			}
		outputForClass.println("private void buildAllUsedLibs() {");
		for(i=0;i<limit;i++)
			{	QualifiedName aUsedLib=(QualifiedName)theLib.getUsedLibs(i);
				//outputForClass.println("BMTLRef_"+aUsedLib.getExternMangledName()+"=new "+aUsedLib.getExternCompleteName()+"();");
				//@TODO check this piece of code ; Usign the singleton ; a used lib has no parameter
				if (! aUsedLib.getIsModelType() && ! aUsedLib.getIsRepositoryModel())
					outputForClass.println("BMTLRef_"+aUsedLib.getExternMangledName()+'='+aUsedLib.getExternCompleteName()+".TheInstance;");
			}
		outputForClass.println("}\n");
		outputForClass.println("public BMTLLibInterface getUsedLibrary(String libName) {");
		outputForClass.println("//Used libraries");
		for(i=0;i<limit;i++)
			{	QualifiedName aUsedLib=(QualifiedName)theLib.getUsedLibs(i);
				if (! aUsedLib.getIsModelType() && ! aUsedLib.getIsRepositoryModel())
					outputForClass.println("if (libName.equals(\""+aUsedLib.getExternMangledName()+"\")) return this.BMTLRef_"+aUsedLib.getExternMangledName()+';');
			}
		outputForClass.println("//Inherited libraries");
		limit=ASTnode.getInheritance().size();
		// Inheritance from other defined libraries
		//=======================================
		for (i=0;i<limit;i++) {
			QualifiedName aParentType = (QualifiedName)ASTnode.getInheritance().get(i);
			String externParentName=aParentType.getExternMangledName();
			outputForClass.println("if (libName.equals(\""+externParentName+"\")) return this.BMTLRef_"+externParentName+';');
		}
		outputForClass.println("return this; } //current library by default\n");
		outputForClass.println("private void buildAllClassTypes() {");
		limit=theLib.cardClasses();
		for (i=0;i<limit;i++) {
			if (! (theLib.getClasses(i) instanceof TheLibraryClass)) {
				String qn = theLib.getClasses(i).getName();
				Property typeTag = theLib.getClasses(i).getProperty("type");
				String type = typeTag == null ? null : (String)((Vector)typeTag.getValue()).get(2);
				if (type != null || !((Boolean)theLib.getClasses(i).getProperty("ManualMangling").getValue()).booleanValue()) {
					if (type == null) 
						type = "new org.irisa.triskell.MT.BasicMTL.TopTypes.BMTLType(this, new String [] {\"" + JavaStringLiteralEncoder.encodeString(theLib.getName()) + "\", \"" + JavaStringLiteralEncoder.encodeString(theLib.getClasses(i).getName()) + "\"}, " + theLib.getClasses(i).getMangle() + "Interface.class, " + theLib.getClasses(i).getMangle() + ".class, null)";
					outputForClass.println(Mangler.mangle("the", theLib.getClasses(i).getMangle())+" = " + type + ";");
				}
			}
		}
		outputForClass.println("}\n");
		outputForClass.println("/* Direct constructor */");
		outputForClass.println("/*====================*/");
		outputForClass.println("public "+ASTnode.getMangle()+"()");
		outputForClass.println("{ super(\""+JavaStringLiteralEncoder.encodeString(ASTnode.getName())+"\");");
		outputForClass.println("inheritanceMap=new java.util.Hashtable();\n");
		outputForClass.println("this.buildAllUsedLibs();");
		outputForClass.println("this.buildAllClassTypes();");
		outputForClass.println("inheritanceMap.put(\""+JavaStringLiteralEncoder.encodeString(AWK.mergeCollection(ASTnode.getQualifiedName(), "::"))+"\",this);");
		outputForClass.println("theCaller=this;");
		outputForClass.println("((BMTLLibraryType)this.getType()).register(theCaller);");
		// Call parents constructors
		//=======================================
		limit=ASTnode.getInheritance().size();
		for (i=0;i<limit;i++) {
			QualifiedName aParentType = (QualifiedName)ASTnode.getInheritance().get(i);
			String externParentName=aParentType.getExternMangledName();
			String externCompleteParentName=aParentType.getExternCompleteName(); //library mangled name . class mangled name
			String externLib=aParentType.getExternLibMangledName();
			String externLibCompleteName=aParentType.getExternLibCompleteName();
			outputForClass.println("if (inheritanceMap.containsKey(\""+JavaStringLiteralEncoder.encodeString(AWK.mergeCollection(aParentType, "::"))+"\"))");
			outputForClass.println("\tBMTLRef_"+externParentName+"= ("+externCompleteParentName+")inheritanceMap.get(\""+JavaStringLiteralEncoder.encodeString(AWK.mergeCollection(aParentType, "::"))+"\");");
			outputForClass.println("else BMTLRef_"+externParentName+"= new "+externCompleteParentName+"(inheritanceMap,this);");
		}
		outputForClass.println('}');
		outputForClass.println();
		outputForClass.println("/* Indirect constructor              */");
		outputForClass.println("/* (called from a parent constructor */");
		outputForClass.println("/*===================================*/");
		outputForClass.println("public "+ASTnode.getMangle()+"(java.util.Hashtable map,"+ASTnode.getMangle()+"Interface o)");
		outputForClass.println("{ super(\""+JavaStringLiteralEncoder.encodeString(ASTnode.getName())+"\");");
		outputForClass.println("this.buildAllUsedLibs();");
		outputForClass.println("this.buildAllClassTypes();");
		outputForClass.println("inheritanceMap = map;");
		outputForClass.println("theCaller=o;");
		outputForClass.println("((BMTLLibraryType)this.getType()).register(theCaller);");
		outputForClass.println("map.put(\""+JavaStringLiteralEncoder.encodeString(AWK.mergeCollection(ASTnode.getQualifiedName(), "::"))+"\",this);");
		// Call parents constructors
		//=======================================
		limit=ASTnode.getInheritance().size();
		for (i=0;i<limit;i++) {
			QualifiedName aParentType = (QualifiedName)ASTnode.getInheritance().get(i);
			String externParentName=aParentType.getExternMangledName();
			String externCompleteParentName=aParentType.getExternCompleteName(); //library mangled name . class mangled name
			String externLib=aParentType.getExternLibMangledName();
			String externLibCompleteName=aParentType.getExternLibCompleteName();
			outputForClass.println("if (map.containsKey(\""+JavaStringLiteralEncoder.encodeString(AWK.mergeCollection(aParentType, "::"))+"\"))");
			outputForClass.println("\tBMTLRef_"+externParentName+"= ("+externCompleteParentName+")map.get(\""+JavaStringLiteralEncoder.encodeString(AWK.mergeCollection(aParentType, "::"))+"\");");
			outputForClass.println("else BMTLRef_"+externParentName+"= new "+externCompleteParentName+"(map,o);");
		}
		outputForClass.println('}');
		outputForClass.println();
		outputForClass.println("/*====================*/");
		outputForClass.println("/* Lib & Refs METHODS */");
		outputForClass.println("/*====================*/");
		QualifiedName aParentType = ASTnode.getQualifiedName();
		GetReferenceSignature signature = new GetReferenceSignature(aParentType);
		String externParentName=aParentType.getExternMangledName();
		outputForClass.println("public "+signature.getReturnedType().getDeclarationName() + "Interface " + signature.getOpLibMangle() + "()");
		outputForInterface.println("public "+signature.getReturnedType().getDeclarationName() + "Interface " + signature.getOpLibMangle() + "();");
		outputForClass.println("{ return this; }\n");
		outputForInterface.println("public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLSetInterface BMTL_allKnownClasses(org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface libName);");
		outputForClass.println("public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLSetInterface BMTL_allKnownClasses(org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface libName)\n{");
		if (ASTnode.getInheritance().size() > 0) outputForClass.println("	BMTLSetInterface allClasses=null;");
		outputForClass.println("	BMTLStringInterface libraryName=(BMTLStringInterface)libName.getTheCollection()[0];");
		outputForClass.println("	if (libraryName.equals(myName)) return allKnownClasses;");
		limit=ASTnode.getInheritance().size();
		// Inheritance from other defined libraries
		//=======================================
		for (i=0;i<limit;i++) {
			aParentType = (QualifiedName)ASTnode.getInheritance().get(i);
			externParentName=aParentType.getExternMangledName();
			outputForClass.println("    allClasses = this."+"BMTLRef_"+externParentName+".BMTL_allKnownClasses(libName);");
			outputForClass.println("	if (allClasses != null) return allClasses;");
		}
		outputForClass.println("	return null; }\n");
		limit=ASTnode.getInheritance().size();
		// Inheritance from other defined libraries
		//=======================================
		for (i=0;i<limit;i++) {
			aParentType = (QualifiedName)ASTnode.getInheritance().get(i);
			signature = new GetReferenceSignature(aParentType);
			externParentName=aParentType.getExternMangledName();
			outputForClass.println("public "+signature.getReturnedType().getDeclarationName() + "Interface " + signature.getOpLibMangle() + "()");
			outputForClass.println("{ return this."+"BMTLRef_"+externParentName+"; }\n");
		}
		outputForClass.print("public static final org.irisa.triskell.MT.DataTypes.Java.Type myType=new BMTLLibraryType(\"" + JavaStringLiteralEncoder.encodeString(ASTnode.getName()) + "\", " + ASTnode.getMangle()+ "Interface.class, " + ASTnode.getMangle()+".class, java.util.Arrays.asList(new BMTLLibraryType [] {");
		Iterator inherited = ASTnode.getInheritance().iterator();
		boolean first = true;
		while (inherited.hasNext()) {
			if (first)
				first = false;
			else
				outputForClass.print(", ");
			outputForClass.print("(BMTLLibraryType)"+((QualifiedName)inherited.next()).getExternCompleteName() + ".myType");
		}
		outputForClass.println("}));");
		outputForClass.println("public org.irisa.triskell.MT.DataTypes.Java.Type getType()");
		outputForClass.println("{ return myType; }");
		outputForClass.println();
		//@TODO remove the following line when the buildUsedLibs will be correctly done...
		//if (!hasParameters)
			outputForClass.println("public static final " + ASTnode.getMangle() + " TheInstance = new " + ASTnode.getMangle() + "();");
		outputForClass.flush();
		outputForInterface.flush();
		outputForClass.close();
		outputForInterface.close();	
	}
}