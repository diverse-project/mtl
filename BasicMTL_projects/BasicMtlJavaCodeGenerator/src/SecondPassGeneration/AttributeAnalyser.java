/*
 * Created on 4 Aout. 2003
 * $Id: AttributeAnalyser.java,v 1.9 2004-07-15 16:00:41 jpthibau Exp $
 * Authors : jpthibau
 * 
 * Copyright 2004 - INRIA - LGPL license
 */
package SecondPassGeneration;

import java.io.*;
import org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.*;
import org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.signatures.AttributeAccessor;
import org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.signatures.AttributeGetterSignature;
import org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.signatures.AttributeSetterSignature;
//import org.irisa.triskell.MT.utils.Java.Mangler;

/**
 * @author jpthibau
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class AttributeAnalyser extends TLLTopDownVisitor.AttributeAnalyser {

	public void AttributeAction(Attribute ASTnode,java.util.Map context)
	{	PrintWriter outputForClass = (PrintWriter)context.get("OutputForClass");
		PrintWriter outputForInterface = (PrintWriter)context.get("OutputForInterface");
		AttributeGetterSignature getter = null;
		AttributeSetterSignature setter = null;
		UserDefinedClass theClass = (UserDefinedClass)context.get("CurrentClass");
		for (int i = 0; (getter == null || setter == null) && (i < theClass.cardLocalSignatures()); ++i) {
			OpSignature ops = theClass.getLocalSignatures(i);
			if ((ops instanceof AttributeAccessor) && ((AttributeAccessor)ops).getTheAttribute().equals(ASTnode)) {
				if (ops instanceof AttributeGetterSignature)
					getter = (AttributeGetterSignature)ops;
				else if (ops instanceof AttributeSetterSignature)
					setter = (AttributeSetterSignature)ops;
			}
		}
		outputForClass.println("public "+getter.getReturnedType().getDeclarationName()+' '+getter.getOpMangle()+"()");
		if (ASTnode.getGetter()==null)
			outputForClass.println("{ return this."+ASTnode.getMangle()+"; }\n");
		else {
			outputForClass.println("{ try {\n");
			outputForClass.println("     return this.theCaller."+ASTnode.getGetter().getMangle()+"(); }\n");
			outputForClass.println("catch (Throwable e) { return null; } }\n");
			} 
		outputForClass.println("public " + setter.getReturnedType().getDeclarationName() + ' ' +setter.getOpMangle()+" ("+setter.getArgsTypes(0).getDeclarationName()+" value) {");
		if ((theClass.getProperty("ObservableClass")!= null && ((Boolean)theClass.getProperty("ObservableClass").getValue()).booleanValue())) {
			outputForClass.println("\treturn "+setter.getOpMangle()+" (value,BMTLBoolean.FALSE); }");
			outputForClass.println("public " + setter.getReturnedType().getDeclarationName() + ' ' +setter.getOpMangle()+" ("+setter.getArgsTypes(0).getDeclarationName()+" value,BMTLBooleanInterface trueSetter) {");
			outputForClass.println("\tboolean doSet=true;");
			outputForClass.println("\tif (! trueSetter.getTheBoolean()) {");
			outputForClass.println("\tjava.util.Vector preObservers = theLib.getPreObservsers(\"PreSet\",this.getType().getQualifiedNameAsString());");
			outputForClass.println("\tif (preObservers != null) {");
			outputForClass.println("\tjava.util.Iterator it = preObservers.iterator();");
			outputForClass.println("\twhile (it.hasNext())");
			outputForClass.println("\ttry {");
			outputForClass.println("\t\tBMTLBooleanInterface doSetCond = ((DefaultObservers.BMTL_ObserverInterface)it.next()).BMTL_notifyPreSet(this,new BMTLString(\""+ASTnode.getName()+"\"),value);");
			outputForClass.println("\t\tif (! doSetCond.getTheBoolean()) doSet=false;");
			outputForClass.println("\t} catch (Throwable e) {}");
			outputForClass.println("\t}");
			outputForClass.println("\tpreObservers = this.getPreObservsers(\"PreSet\");");
			outputForClass.println("\tif (preObservers != null) {");
			outputForClass.println("\tjava.util.Iterator it = preObservers.iterator();");
			outputForClass.println("\twhile (it.hasNext())");
			outputForClass.println("\ttry {");
			outputForClass.println("\t\tBMTLBooleanInterface doSetCond = ((DefaultObservers.BMTL_ObserverInterface)it.next()).BMTL_notifyPreSet(this,new BMTLString(\""+ASTnode.getName()+"\"),value);");
			outputForClass.println("\t\tif (! doSetCond.getTheBoolean()) doSet=false;");
			outputForClass.println("\t} catch (Throwable e) {}");
			outputForClass.println("\t}");
			outputForClass.println("\t}if (trueSetter.getTheBoolean() || doSet)"); }
		if (ASTnode.getSetter()==null)
			outputForClass.println("\tthis."+ASTnode.getMangle()+"=value;");
		else {
			outputForClass.println("\ttry {\n");
			outputForClass.println("     this.theCaller."+ASTnode.getSetter().getMangle()+"(value); }");
			outputForClass.println("catch (Throwable e) {}\n");
			} 
		if ((theClass.getProperty("ObservableClass")!= null && ((Boolean)theClass.getProperty("ObservableClass").getValue()).booleanValue())) {
			outputForClass.println("\tif (! trueSetter.getTheBoolean()) {");
			outputForClass.println("\tjava.util.Vector postObservers = theLib.getPostObservsers(\"PostSet\",this.getType().getQualifiedNameAsString());");
			outputForClass.println("\tif (postObservers != null) {");
			outputForClass.println("\tjava.util.Iterator it = postObservers.iterator();");
			outputForClass.println("\twhile (it.hasNext())");
			outputForClass.println("\ttry {");
			outputForClass.println("\t\t((DefaultObservers.BMTL_ObserverInterface)it.next()).BMTL_notifyPostSet(this,new BMTLString(\""+ASTnode.getName()+"\"),value);");
			outputForClass.println("\t} catch (Throwable e) {}");
			outputForClass.println("\t}");
			outputForClass.println("\tpostObservers = this.getPostObservsers(\"PostSet\");");
			outputForClass.println("\tif (postObservers != null) {");
			outputForClass.println("\tjava.util.Iterator it = postObservers.iterator();");
			outputForClass.println("\twhile (it.hasNext())");
			outputForClass.println("\ttry {");
			outputForClass.println("\t\t((DefaultObservers.BMTL_ObserverInterface)it.next()).BMTL_notifyPostSet(this,new BMTLString(\""+ASTnode.getName()+"\"),value);");
			outputForClass.println("\t} catch (Throwable e) {}");
			outputForClass.println("\t} }"); }
		outputForClass.println("return BMTLVoid.TheInstance; }\n");
		outputForInterface.println("public "+getter.getReturnedType().getDeclarationName()+' '+getter.getOpMangle()+"();");
		outputForInterface.println("public " + setter.getReturnedType().getDeclarationName() + ' ' +setter.getOpMangle()+" ("+setter.getArgsTypes(0).getDeclarationName()+" value);");
//		if (type.getIsLocalType())
//			{	outputForClass.println("public "+type.getDeclarationName()+' '+attributerGetterName+"()");
//				outputForClass.println("{ return this."+ASTnode.getMangle()+"; }\n");
//				outputForClass.println("public VoidValueImpl "+attributerSetterName+" ("+type.getDeclarationName()+" value)");
//				outputForClass.println("{ this."+ASTnode.getMangle()+"=value;");
//				outputForClass.println("return VoidValueImpl.getTheInstance(); }\n");
//				outputForInterface.println("public "+type.getDeclarationName()+' '+attributerGetterName+"();");
//				outputForInterface.println("public VoidValueImpl "+attributerSetterName+" ("+type.getDeclarationName()+" value);");
//		} 
//		else if ((type.getIsExternType())
//				|| ((type.getIsModelType()) && (! type.getIsRepositoryModel())))
//				{	outputForClass.println("public "+type.getDeclarationName()+' '+attributerGetterName+"()");
//					outputForClass.println("{ return this."+ASTnode.getMangle()+"; }\n\n");
//					outputForClass.println("public VoidValueImpl "+attributerSetterName+" ("+type.getDeclarationName()+" value)");
//					outputForClass.println("{ this."+ASTnode.getMangle()+"=value;");
//					outputForClass.println("return VoidValueImpl.getTheInstance(); }\n");
//					outputForInterface.println("public "+type.getDeclarationName()+' '+attributerGetterName+"();");
//					outputForInterface.println("public VoidValueImpl "+attributerSetterName+" ("+type.getDeclarationName()+" value);");
//				} 
//			else if (type.getIsModelType())
//					{	if (type.getIsRepositoryModel())
//							{	outputForClass.println("public API "+attributerGetterName+"()");
//								outputForClass.println("{ return this."+ASTnode.getMangle()+"; }\n");
//								outputForClass.println("public VoidValueImpl "+attributerSetterName+" (API value)");
//								outputForClass.println("{ this."+ASTnode.getMangle()+"=value;");
//								outputForClass.println("return VoidValueImpl.getTheInstance(); }\n");
//								outputForInterface.println("public API "+attributerGetterName+"();");
//								outputForInterface.println("public VoidValueImpl "+attributerSetterName+" (API value);");
//							} 
//					}
//				else CodeGeneration.BMTLCompiler.getLog().error("Attribute has a wrong type qualifier !"+ASTnode.getName()+':'+type);
	}

}
