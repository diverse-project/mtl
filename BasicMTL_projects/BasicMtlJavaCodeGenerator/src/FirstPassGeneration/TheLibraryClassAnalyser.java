/*
 * $Header: /tmp/cvs2svn/cvsroot/BasicMTL_projects/BasicMtlJavaCodeGenerator/src/FirstPassGeneration/TheLibraryClassAnalyser.java,v 1.7 2003-09-17 07:15:23 jpthibau Exp $
 * Created on 21 juil. 2003
 *
 */
package FirstPassGeneration;

import java.io.*;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.*;
import org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.signatures.GetReferenceSignature;
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
				outputForClass.println("public org.irisa.triskell.MT.DataTypes.Java.Type "+Mangler.mangle("the", theLib.getClasses(i).getMangle())+';');
			}
		}
		boolean isStandard = theLib.getName().equals("Standard");
		outputForClass.println("public org.irisa.triskell.MT.DataTypes.Java.Type getMetaClass(String [] type)");
		outputForClass.println("{ if (type == null || type.length == 0)");
		outputForClass.println("    return null;");
		outputForClass.println("  if (type[0].equals(\""+JavaStringLiteralEncoder.encodeString(theLib.getName())+"\")) {");
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
				outputForClass.println("    if (type[0].equals(\""+JavaStringLiteralEncoder.encodeString(theLib.getClasses(i).getName())+"\")) return this."+Mangler.mangle("the", theLib.getClasses(i).getMangle())+';');
		}
		limit = theLib.cardUsedLibs();
		for(i=0;i<limit;i++)
			{	QualifiedName aUsedLib=(QualifiedName)theLib.getUsedLibs(i);
				if (!aUsedLib.isModelType && !aUsedLib.isRepositoryModel) {
					outputForClass.println("    if (type[0].equals(\""+JavaStringLiteralEncoder.encodeString((String)aUsedLib.get(0))+"\")) return "+aUsedLib.getExternCompleteName()+".myType;");
				}
			}
		if (isStandard)
			outputForClass.println("    return null;");
		else
			outputForClass.println("    return this.getMetaClass(new String [] {\"Standard\", type[0]});");
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
		outputForClass.println("public BMTLLibrary getLibrary() {");
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
		outputForClass.println("inheritanceMap.put(\""+generatedLibMangledName+"::"+ASTnode.getMangle()+"\",this);");
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
			outputForClass.println("BMTLRef_"+externParentName+"= new "+externCompleteParentName+"(inheritanceMap,this);");
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
		outputForClass.println("map.put(\""+generatedLibMangledName+"::"+ASTnode.getMangle()+"\",this);");
		// Call parents constructors
		//=======================================
		limit=ASTnode.getInheritance().size();
		for (i=0;i<limit;i++) {
			QualifiedName aParentType = (QualifiedName)ASTnode.getInheritance().get(i);
			String externParentName=aParentType.getExternMangledName();
			String externCompleteParentName=aParentType.getExternCompleteName(); //library mangled name . class mangled name
			String externLib=aParentType.getExternLibMangledName();
			String externLibCompleteName=aParentType.getExternLibCompleteName();
			outputForClass.println("if (map.containsKey(\""+externCompleteParentName+"\"))");
			outputForClass.println("\tBMTLRef_"+externParentName+"= ("+externCompleteParentName+")map.get(\""+externCompleteParentName+"\");");
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
//=====> Interface added
		outputForClass.println("public "+signature.getReturnedType().getDeclarationName() + "Interface " + signature.getOpMangle() + "()");
		outputForInterface.println("public "+signature.getReturnedType().getDeclarationName() + "Interface " + signature.getOpMangle() + "();");
//======>
		outputForClass.println("{ return this; }\n");
		limit=ASTnode.getInheritance().size();
		// Inheritance from other defined libraries
		//=======================================
		for (i=0;i<limit;i++) {
			aParentType = (QualifiedName)ASTnode.getInheritance().get(i);
			signature = new GetReferenceSignature(aParentType);
			externParentName=aParentType.getExternMangledName();
			outputForClass.println("public "+signature.getReturnedType().getDeclarationName() + "Interface " + signature.getOpMangle() + "()");
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