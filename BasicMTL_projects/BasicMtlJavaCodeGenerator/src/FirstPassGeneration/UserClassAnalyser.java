/*
 * Created on 21 juil. 2003
 * $Id: UserClassAnalyser.java,v 1.11 2004-07-16 13:41:34 jpthibau Exp $
 * Authors : jpthibau
 * 
 * Copyright 2004 - INRIA - LGPL license
 */
package FirstPassGeneration;

import java.io.PrintWriter;
import java.util.Map;
//import java.util.Vector;

import org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.QualifiedName;
import org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.UserClass;
import org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.signatures.GetReferenceSignature;
import org.irisa.triskell.MT.utils.Java.AWK;
import org.irisa.triskell.MT.utils.Java.JavaStringLiteralEncoder;
//import org.irisa.triskell.MT.utils.Java.Mangler;
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
		CommonFunctions.generatePackageImports(outputForClass,packageName);
		CommonFunctions.generatePackageImports(outputForInterface,packageName);
		outputForClass.print("public class "+ASTnode.getMangle()+" extends BMTLObject");
		outputForClass.println(" implements "+ASTnode.getMangle()+"Interface {\n");
		outputForClass.println("\n/*==========================*/");
		outputForClass.println("/* DYNAMIC TYPE CONVERSION */");
		outputForClass.println("/*==========================*/");
		outputForClass.println("public static "+ASTnode.getMangle()+" oclAsType(Object o)");
		outputForClass.println("{     return ("+ASTnode.getMangle()+")o;  }\n");
		outputForClass.println("/*==========================*/");
		outputForClass.println("/* CLASS PRIVATE ATTRIBUTES */");
		outputForClass.println("/*==========================*/");
		outputForClass.println("private "+generatedLibMangledName+"Interface theLib;");
		outputForClass.println("private "+ASTnode.getMangle()+"Interface theCaller;");
		outputForClass.println("private java.util.Hashtable inheritanceMap;");
		if (((Boolean)ASTnode.getProperty("ObservableClass").getValue()).booleanValue()) {
			outputForClass.println("/* REGISTERED OBJECT OBSERVERS */");
			outputForClass.println("private static java.util.HashMap registeredObservers=null;");
			outputForClass.println("public static void addObserver(String observationPoint,DefaultObservers.BMTL_ObserverInterface obs) {");
			outputForClass.println("\tif (registeredObservers==null)");
			outputForClass.println("\t\tregisteredObservers=new java.util.HashMap();");
			outputForClass.println("\tif (registeredObservers.containsKey(observationPoint))");
			outputForClass.println("\t\t((java.util.Vector)registeredObservers.get(observationPoint)).add(obs);");
			outputForClass.println("\telse {");
			outputForClass.println("\t\tjava.util.Vector v = new java.util.Vector();");
			outputForClass.println("\t\tv.add(obs);");
			outputForClass.println("\t\tregisteredObservers.put(observationPoint,v);");
			outputForClass.println("\t} }");
			outputForClass.println("public static void removeObserver(String observationPoint,DefaultObservers.BMTL_ObserverInterface obs) {");
			outputForClass.println("\tif (registeredObservers!=null");
			outputForClass.println("\t\t\t&& registeredObservers.containsKey(observationPoint))");
			outputForClass.println("\t\t\t((java.util.Vector)registeredObservers.get(observationPoint)).remove(obs);");
			outputForClass.println("\t}");
			outputForClass.println("public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface BMTL_addPreObserver(DefaultObservers.BMTL_ObserverInterface obs,BMTLString opSelection) {");
			outputForClass.println("\tjava.util.Iterator it = org.irisa.triskell.MT.BasicMTL.DataTypes.impl.ObserversSelector.checkopSelection(opSelection).iterator();");
			outputForClass.println("\twhile (it.hasNext()) {");
			outputForClass.println("\t\tswitch (((Character)it.next()).charValue()) {");
			outputForClass.println("\t\tcase 'D' : "+ASTnode.getMangle()+".addObserver(\"PreDelete\",obs); break;");
			outputForClass.println("\t\tcase 'G' : "+ASTnode.getMangle()+".addObserver(\"PreGet\",obs); break;");
			outputForClass.println("\t\tcase 'S' : "+ASTnode.getMangle()+".addObserver(\"PreSet\",obs); break;");
			outputForClass.println("\t\t} }");
			outputForClass.println("return BMTLVoid.TheInstance; }");
			outputForClass.println("public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface BMTL_removePreObserver(DefaultObservers.BMTL_ObserverInterface obs,BMTLString opSelection) {");
			outputForClass.println("\tjava.util.Iterator it = org.irisa.triskell.MT.BasicMTL.DataTypes.impl.ObserversSelector.checkopSelection(opSelection).iterator();");
			outputForClass.println("\twhile (it.hasNext()) {");
			outputForClass.println("\t\tswitch (((Character)it.next()).charValue()) {");
			outputForClass.println("\t\tcase 'D' : "+ASTnode.getMangle()+".removeObserver(\"PreDelete\",obs); break;");
			outputForClass.println("\t\tcase 'G' : "+ASTnode.getMangle()+".removeObserver(\"PreGet\",obs); break;");
			outputForClass.println("\t\tcase 'S' : "+ASTnode.getMangle()+".removeObserver(\"PreSet\",obs); break;");
			outputForClass.println("\t\t} }");
			outputForClass.println("return BMTLVoid.TheInstance; }");
			outputForClass.println("public java.util.Vector getPreObservsers(String observationPoint) {");
			outputForClass.println("\tif (registeredObservers!=null");
			outputForClass.println("\t\t&& registeredObservers.get(observationPoint) != null)");
			outputForClass.println("\t\treturn (java.util.Vector)registeredObservers.get(observationPoint);");
			outputForClass.println("\treturn null; }");
			outputForClass.println("public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface BMTL_addPostObserver(DefaultObservers.BMTL_ObserverInterface obs,BMTLString opSelection) {");
			outputForClass.println("\tjava.util.Iterator it = org.irisa.triskell.MT.BasicMTL.DataTypes.impl.ObserversSelector.checkopSelection(opSelection).iterator();");
			outputForClass.println("\twhile (it.hasNext()) {");
			outputForClass.println("\t\tswitch (((Character)it.next()).charValue()) {");
			outputForClass.println("\t\tcase 'D' : "+ASTnode.getMangle()+".addObserver(\"PostDelete\",obs); break;");
			outputForClass.println("\t\tcase 'G' : System.err.println(\"addPostObserver(...) has no meaning for Getter ! Use addPreObserver instead.\"); break;");
			outputForClass.println("\t\tcase 'S' : "+ASTnode.getMangle()+".addObserver(\"PostSet\",obs); break;");
			outputForClass.println("\t\t} }");
			outputForClass.println("return BMTLVoid.TheInstance; }");
			outputForClass.println("public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface BMTL_removePostObserver(DefaultObservers.BMTL_ObserverInterface obs,BMTLString opSelection) {");
			outputForClass.println("\tjava.util.Iterator it = org.irisa.triskell.MT.BasicMTL.DataTypes.impl.ObserversSelector.checkopSelection(opSelection).iterator();");
			outputForClass.println("\twhile (it.hasNext()) {");
			outputForClass.println("\t\tswitch (((Character)it.next()).charValue()) {");
			outputForClass.println("\t\tcase 'D' : "+ASTnode.getMangle()+".removeObserver(\"PostDelete\",obs); break;");
			outputForClass.println("\t\tcase 'G' : System.err.println(\"removePostObserver(...) has no meaning for Getter ! Use removePreObserver instead.\"); break;");
			outputForClass.println("\t\tcase 'S' : "+ASTnode.getMangle()+".removeObserver(\"PostSet\",obs); break;");
			outputForClass.println("\t\t} }");
			outputForClass.println("return BMTLVoid.TheInstance; }");
			outputForClass.println("public java.util.Vector getPostObservsers(String observationPoint) {");
			outputForClass.println("\tif (registeredObservers!=null");
			outputForClass.println("\t\t&& registeredObservers.get(observationPoint) != null)");
			outputForClass.println("\t\treturn (java.util.Vector)registeredObservers.get(observationPoint);");
			outputForClass.println("\treturn null; }");
		}
		outputForClass.println("/*=========================*/");
		outputForClass.println("/* PARENT(S) REFERENCE(S)  */");
		outputForClass.println("/*=========================*/");
		outputForInterface.print("public interface "+ASTnode.getMangle()+"Interface extends BMTLObjectInterface");
		limit=ASTnode.getInheritance().size();
		// Inheritance from other defined classes
		//=======================================
		for (i=0;i<limit;i++) {
			QualifiedName aParentType = (QualifiedName)ASTnode.getInheritance().get(i);
			GetReferenceSignature signature = new GetReferenceSignature(aParentType);
			if (aParentType.getIsLocalType()) //class defined in this library
				{ 	String localParentName=aParentType.getLocalMangledName();
					String declaredParentName = aParentType.getDeclarationName(); 
					outputForClass.println("private "+signature.getReturnedType().getDeclarationName()+" BMTLRef_"+localParentName+';');
					outputForInterface.print(", "+declaredParentName);
				}
			if (aParentType.getIsExternType()) //class defined in another library
				{	String externParentName=aParentType.getExternMangledName();
					String declaredParentName = aParentType.getDeclarationName();
					outputForClass.println("private "+signature.getReturnedType().getDeclarationName()+" BMTLRef_"+externParentName+';');
					outputForInterface.println(", "+declaredParentName);
				}
		}
		outputForInterface.println('{');
		if (((Boolean)ASTnode.getProperty("ObservableClass").getValue()).booleanValue()) {
			outputForInterface.println("public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface BMTL_addPreObserver(DefaultObservers.BMTL_ObserverInterface obs,BMTLString opSelection);");
			outputForInterface.println("public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface BMTL_removePreObserver(DefaultObservers.BMTL_ObserverInterface obs,BMTLString opSelection);");
			outputForInterface.println("public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface BMTL_addPostObserver(DefaultObservers.BMTL_ObserverInterface obs,BMTLString opSelection);");
			outputForInterface.println("public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface BMTL_removePostObserver(DefaultObservers.BMTL_ObserverInterface obs,BMTLString opSelection);");
		}
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
		outputForClass.println("public "+ASTnode.getMangle()+'('+generatedLibCompleteMangledName+"Interface libRef)");
		outputForClass.println("{ super(\""+JavaStringLiteralEncoder.encodeString(ASTnode.getName())+"\");");
/*		if (((Boolean)ASTnode.getProperty("ObservableClass").getValue()).booleanValue()) {
			outputForClass.println("\tboolean doNew=true;");
			outputForClass.println("\tjava.util.Vector preObservers = theLib.getPreObservsers(\"PreNew\",this.getType().getQualifiedNameAsString());");
			outputForClass.println("\tif (preObservers != null) {");
			outputForClass.println("\tjava.util.Iterator it = preObservers.iterator();");
			outputForClass.println("\twhile (it.hasNext())");
			outputForClass.println("\ttry {");
			outputForClass.println("\t\tBMTLBooleanInterface doNewCond = ((DefaultObservers.BMTL_ObserverInterface)it.next()).BMTL_notifyPreNew(this);");
			outputForClass.println("\t\tif (! doNewCond.getTheBoolean()) doNew=false;");
			outputForClass.println("\t} catch (Throwable e) {}");
			outputForClass.println("\t}");
			outputForClass.println("\tif (doNew) {"); }*/
		outputForClass.println("theLib = libRef;");
		outputForClass.println("inheritanceMap=new java.util.Hashtable();");
		outputForClass.println("inheritanceMap.put(\""+JavaStringLiteralEncoder.encodeString(AWK.mergeCollection(ASTnode.getQualifiedName(), "::"))+"\",this);");
		outputForClass.println("theCaller=this;");
		outputForClass.println("theLib.recordNewInstance(\""+JavaStringLiteralEncoder.encodeString(ASTnode.getName())+"\",this);");
		outputForClass.println();
		// Call parents constructors
		//=======================================
		limit=ASTnode.getInheritance().size();
		for (i=0;i<limit;i++) {
			QualifiedName aParentType = (QualifiedName)ASTnode.getInheritance().get(i);
			if (aParentType.getIsLocalType()) //class defined in this library
				{	String localParentName=aParentType.getLocalMangledName();
					outputForClass.println("if (inheritanceMap.containsKey(\""+JavaStringLiteralEncoder.encodeString(AWK.mergeCollection(aParentType, "::"))+"\")) ");
					outputForClass.println("\tBMTLRef_"+localParentName+"= ("+localParentName+")inheritanceMap.get(\""+JavaStringLiteralEncoder.encodeString(AWK.mergeCollection(aParentType, "::"))+"\");");
					outputForClass.println("else BMTLRef_"+localParentName+"= new "+localParentName+"(theLib,inheritanceMap,this);");// ("+localParentName+")((BMTLType)this.getLibrary().getMetaClass(new String [] {\"" + AWK.mergeCollection(aParentType, "\", \"") + "\"})).instanciateReference(inheritanceMap,this);");
				} 
			if (aParentType.getIsExternType()) //class defined in another library
				{	String externParentName=aParentType.getExternMangledName();
					String externCompleteParentName=aParentType.getExternCompleteName(); //library mangled name . class mangled name
					String externLib=aParentType.getExternLibMangledName();
					String externLibCompleteName=aParentType.getExternLibCompleteName();
					outputForClass.println("if (inheritanceMap.containsKey(\""+JavaStringLiteralEncoder.encodeString(AWK.mergeCollection(aParentType, "::"))+"\"))");
					outputForClass.println("\tBMTLRef_"+externParentName+"= ("+externCompleteParentName+")inheritanceMap.get(\""+JavaStringLiteralEncoder.encodeString(AWK.mergeCollection(aParentType, "::"))+"\");");
					outputForClass.println("else BMTLRef_"+externParentName+"= new "+externCompleteParentName+"(("+externLibCompleteName+")theLib.getUsedLibrary(\""+externLib+"\"),inheritanceMap,this);");// ("+externCompleteParentName+")((BMTLType)this.getLibrary().getMetaClass(new String [] {\"" + AWK.mergeCollection(aParentType, "\", \"") + "\"})).instanciateReference(inheritanceMap,this);");
				}
		}
		if (((Boolean)ASTnode.getProperty("ObservableClass").getValue()).booleanValue()) {
			outputForClass.println("\tjava.util.Vector postObservers = theLib.getPostObservsers(\"PostNew\",this.getType().getQualifiedNameAsString());");
			outputForClass.println("\tif (postObservers != null) {");
			outputForClass.println("\tjava.util.Iterator it = postObservers.iterator();");
			outputForClass.println("\twhile (it.hasNext())");
			outputForClass.println("\ttry {");
			outputForClass.println("\t\t((DefaultObservers.BMTL_ObserverInterface)it.next()).BMTL_notifyPostNew(this);");
			outputForClass.println("\t} catch (Throwable e) {}");
			outputForClass.println("\t}"); }
		outputForClass.println('}');
		outputForClass.println();
		outputForClass.println("/* Indirect constructor              */");
		outputForClass.println("/* (called from a parent constructor */");
		outputForClass.println("/*===================================*/");
		outputForClass.println("public "+ASTnode.getMangle()+"("+generatedLibCompleteMangledName+"Interface libRef,java.util.Hashtable map,"+ASTnode.getMangle()+"Interface o)");
		outputForClass.println("{ super(\""+JavaStringLiteralEncoder.encodeString(ASTnode.getName())+"\");");
/*		if (((Boolean)ASTnode.getProperty("ObservableClass").getValue()).booleanValue()) {
			outputForClass.println("\tboolean doNew=true;");
			outputForClass.println("\tjava.util.Vector preObservers = theLib.getPreObservsers(\"PreNew\",this.getType().getQualifiedNameAsString());");
			outputForClass.println("\tif (preObservers != null) {");
			outputForClass.println("\tjava.util.Iterator it = preObservers.iterator();");
			outputForClass.println("\twhile (it.hasNext())");
			outputForClass.println("\ttry {");
			outputForClass.println("\t\tBMTLBooleanInterface doNewCond = ((DefaultObservers.BMTL_ObserverInterface)it.next()).BMTL_notifyPreNew(this);");
			outputForClass.println("\t\tif (! doNewCond.getTheBoolean()) doNew=false;");
			outputForClass.println("\t} catch (Throwable e) {}");
			outputForClass.println("\t}");
			outputForClass.println("\tif (doNew) {"); }*/
		outputForClass.println("theLib = (" + generatedLibCompleteMangledName + "Interface)libRef.getLibrary();");
		outputForClass.println("inheritanceMap = map;");
		outputForClass.println("theCaller=o;");
		outputForClass.println("map.put(\""+JavaStringLiteralEncoder.encodeString(AWK.mergeCollection(ASTnode.getQualifiedName(), "::"))+"\",this);");
		outputForClass.println("theLib.recordNewInstance(\""+JavaStringLiteralEncoder.encodeString(ASTnode.getName())+"\",theCaller);");
		// Call parents constructors
		//=======================================
		limit=ASTnode.getInheritance().size();
		for (i=0;i<limit;i++) {
			QualifiedName aParentType = (QualifiedName)ASTnode.getInheritance().get(i);
			if (aParentType.getIsLocalType()) //class defined in this library
				{	String localParentName=aParentType.getLocalMangledName();
					outputForClass.println("if (map.containsKey(\""+JavaStringLiteralEncoder.encodeString(AWK.mergeCollection(aParentType, "::"))+"\")) ");
					outputForClass.println("\tBMTLRef_"+localParentName+"= ("+localParentName+")map.get(\""+JavaStringLiteralEncoder.encodeString(AWK.mergeCollection(aParentType, "::"))+"\");");
					outputForClass.println("else BMTLRef_"+localParentName+"= new "+localParentName+"(theLib,map,o);");// ("+localParentName+")((BMTLType)this.getLibrary().getMetaClass(new String [] {\"" + AWK.mergeCollection(aParentType, "\", \"") + "\"})).instanciateReference(map,(BMTLObject)o);");
				} 
			if (aParentType.getIsExternType()) //class defined in another library
				{	String externParentName=aParentType.getExternMangledName();
					String externCompleteParentName=aParentType.getExternCompleteName(); //library mangled name . class mangled name
					String externLib=aParentType.getExternLibMangledName();
					String externLibCompleteName=aParentType.getExternLibCompleteName();
					outputForClass.println("if (map.containsKey(\""+JavaStringLiteralEncoder.encodeString(AWK.mergeCollection(aParentType, "::"))+"\"))");
					outputForClass.println("\tBMTLRef_"+externParentName+"= ("+externCompleteParentName+")map.get(\""+JavaStringLiteralEncoder.encodeString(AWK.mergeCollection(aParentType, "::"))+"\");");
					outputForClass.println("else BMTLRef_"+externParentName+"= new "+externCompleteParentName+"(("+externLibCompleteName+")theLib.getUsedLibrary(\""+externLib+"\"),map,o);");// ("+externCompleteParentName+")((BMTLType)this.getLibrary().getMetaClass(new String [] {\"" + AWK.mergeCollection(aParentType, "\", \"") + "\"})).instanciateReference(map,(BMTLObject)o);");
				}
		}
		if (((Boolean)ASTnode.getProperty("ObservableClass").getValue()).booleanValue()) {
			outputForClass.println("\tjava.util.Vector postObservers = theLib.getPostObservsers(\"PostNew\",this.getType().getQualifiedNameAsString());");
			outputForClass.println("\tif (postObservers != null) {");
			outputForClass.println("\tjava.util.Iterator it = postObservers.iterator();");
			outputForClass.println("\twhile (it.hasNext())");
			outputForClass.println("\ttry {");
			outputForClass.println("\t\t((DefaultObservers.BMTL_ObserverInterface)it.next()).BMTL_notifyPostNew(this);");
			outputForClass.println("\t} catch (Throwable e) {}");
			outputForClass.println("\t}"); }
		outputForClass.println("}");
		outputForClass.println();
		outputForClass.println("/*=================*/");
		outputForClass.println("/* CLASS FINALIZER */");
		outputForClass.println("/*=================*/");
		outputForClass.println("public boolean isValid = true;");
		outputForClass.println("public void delete() {");
		if (((Boolean)ASTnode.getProperty("ObservableClass").getValue()).booleanValue()) {
			outputForClass.println("\tthis.BMTL_delete (BMTLBoolean.FALSE); }");
			outputForClass.println("public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface BMTL_delete(BMTLBooleanInterface trueDelete) {");
			outputForClass.println("\tboolean doDelete=true;");
			outputForClass.println("\tif (! trueDelete.getTheBoolean()) {");
			outputForClass.println("\tjava.util.Vector preObservers = theLib.getPreObservsers(\"PreDelete\",this.getType().getQualifiedNameAsString());");
			outputForClass.println("\tif (preObservers != null) {");
			outputForClass.println("\tjava.util.Iterator it = preObservers.iterator();");
			outputForClass.println("\twhile (it.hasNext())");
			outputForClass.println("\ttry {");
			outputForClass.println("\t\tBMTLBooleanInterface doDeleteCond = ((DefaultObservers.BMTL_ObserverInterface)it.next()).BMTL_notifyPreDelete(this);");
			outputForClass.println("\t\tif (! doDeleteCond.getTheBoolean()) doDelete=false;");
			outputForClass.println("\t} catch (Throwable e) {}");
			outputForClass.println("\t}");
			outputForClass.println("\tpreObservers = this.getPreObservsers(\"PreDelete\");");
			outputForClass.println("\tif (preObservers != null) {");
			outputForClass.println("\tjava.util.Iterator it = preObservers.iterator();");
			outputForClass.println("\twhile (it.hasNext())");
			outputForClass.println("\ttry {");
			outputForClass.println("\t\tBMTLBooleanInterface doDeleteCond = ((DefaultObservers.BMTL_ObserverInterface)it.next()).BMTL_notifyPreDelete(this);");
			outputForClass.println("\t\tif (! doDeleteCond.getTheBoolean()) doDelete=false;");
			outputForClass.println("\t} catch (Throwable e) {}");
			outputForClass.println("\t}");
			outputForClass.println("\t}if (trueDelete.getTheBoolean() || doDelete)"); }
		outputForClass.println("\tif (isValid) {");
		limit=ASTnode.getInheritance().size();
		for (i=0;i<limit;i++) {
			QualifiedName aParentType = (QualifiedName)ASTnode.getInheritance().get(i);
			GetReferenceSignature signature = new GetReferenceSignature(aParentType);
			outputForClass.println("    this."+signature.getOpMangle()+"().delete();");
		}
		outputForClass.println("    theLib.removeInstance(\""+JavaStringLiteralEncoder.encodeString(ASTnode.getName())+"\",this);");
		outputForClass.println("    this.isValid = false;");
		outputForClass.println("  }");
		if (((Boolean)ASTnode.getProperty("ObservableClass").getValue()).booleanValue()) {
			outputForClass.println("\tif (! trueDelete.getTheBoolean()) {");
			outputForClass.println("\tjava.util.Vector postObservers = theLib.getPostObservsers(\"PostDelete\",this.getType().getQualifiedNameAsString());");
			outputForClass.println("\tif (postObservers != null) {");
			outputForClass.println("\tjava.util.Iterator it = postObservers.iterator();");
			outputForClass.println("\twhile (it.hasNext())");
			outputForClass.println("\ttry {");
			outputForClass.println("\t\t((DefaultObservers.BMTL_ObserverInterface)it.next()).BMTL_notifyPostDelete(this);");
			outputForClass.println("\t} catch (Throwable e) {}");
			outputForClass.println("\t}");
			outputForClass.println("\tpostObservers = this.getPostObservsers(\"PostDelete\");");
			outputForClass.println("\tif (postObservers != null) {");
			outputForClass.println("\tjava.util.Iterator it = postObservers.iterator();");
			outputForClass.println("\twhile (it.hasNext())");
			outputForClass.println("\ttry {");
			outputForClass.println("\t\t((DefaultObservers.BMTL_ObserverInterface)it.next()).BMTL_notifyPostDelete(this);");
			outputForClass.println("\t} catch (Throwable e) {}");
			outputForClass.println("\t} }");
			outputForClass.println("return BMTLVoid.TheInstance;"); }
		outputForClass.println('}');
		outputForClass.println();
		outputForClass.println("/*====================*/");
		outputForClass.println("/* Lib & Refs METHODS */");
		outputForClass.println("/*====================*/");
		outputForClass.println("public BMTLLibInterface getLibrary()");
		outputForClass.println("{ return theLib; }");
		QualifiedName aParentType = ASTnode.getQualifiedName();
		GetReferenceSignature signature = new GetReferenceSignature(aParentType);
		outputForClass.println("public "+signature.getReturnedType().getDeclarationName()+' '+signature.getOpMangle()+"()");
		outputForInterface.println("public "+signature.getReturnedType().getDeclarationName()+' '+signature.getOpMangle()+"();");
		String localParentName=aParentType.getLocalMangledName(); 
		outputForClass.println("{ return this; }");
		outputForClass.println();
		outputForClass.println("public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLSetInterface BMTL_allKnownClasses(org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface libName)");
		outputForClass.println("");
		outputForInterface.println("public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLSetInterface BMTL_allKnownClasses(org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface libName);");
		outputForClass.println("{	return theLib.BMTL_allKnownClasses(libName); }");
		limit=ASTnode.getInheritance().size();
		// Inheritance from other defined classes
		//=======================================
		for (i=0;i<limit;i++) {
			aParentType = (QualifiedName)ASTnode.getInheritance().get(i);
			signature = new GetReferenceSignature(aParentType);
			outputForClass.println("public "+signature.getReturnedType().getDeclarationName()+' '+signature.getOpMangle()+"()");
			if (aParentType.getIsLocalType()) //class defined in this library
				{ 	localParentName=aParentType.getLocalMangledName(); 
					outputForClass.println("{ return this."+"BMTLRef_"+localParentName+"; }");
					outputForClass.println();
				}
			if (aParentType.getIsExternType()) //class defined in another library
				{	String externParentName=aParentType.getExternMangledName();
					outputForClass.println("{ return this."+"BMTLRef_"+externParentName+"; }");
					outputForClass.println();
				}
		}
		
		outputForClass.println("private transient org.irisa.triskell.MT.DataTypes.Java.Type theType;");
		outputForClass.println("public org.irisa.triskell.MT.DataTypes.Java.Type getType()");
		outputForClass.println("{ if (this.theType == null) {");
		outputForClass.println("    this.theType = this.getLibrary().getMetaClass(new String [] {\"" + JavaStringLiteralEncoder.encodeString(ASTnode.getName()) + "\"});");
		outputForClass.println("  }");
		outputForClass.println("  return this.theType; }");
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