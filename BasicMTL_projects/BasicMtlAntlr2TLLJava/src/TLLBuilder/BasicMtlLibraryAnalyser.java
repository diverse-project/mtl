/*
 * $Header: /tmp/cvs2svn/cvsroot/BasicMTL_projects/BasicMtlAntlr2TLLJava/src/TLLBuilder/BasicMtlLibraryAnalyser.java,v 1.2 2003-08-08 15:26:16 jpthibau Exp $
 * Created on 22 juil. 2003
 *
 */
package TLLBuilder;

import org.irisa.triskell.MT.utils.Java.AWK;
import org.irisa.triskell.MT.utils.Java.Mangler;
import org.irisa.triskell.MT.visitors.Java.AnalysingVisitor.*;
import org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.*;

/**
 * @author jpthibau
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class BasicMtlLibraryAnalyser extends ASTTopDownVisitor.BasicMtlLibraryAnalyser {

	BasicMtlLibrary theCreatedLib;
	
	public void BasicMtlLibraryBefore(org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.BasicMtlLibrary Lib,java.util.Map context)
	{	String libName=Lib.getName();
		String libPackageName=null;
		String mangle=null;
		Property mangling=(Property)Lib.getProperty("mangle");
		if (mangling == null) {
			mangle=Mangler.mangle("BMTLLib_",libName);
			libPackageName=(String)context.get("LibrraryDefaultPackage");
		}
		else { mangle=(String)mangling.getValue();
			libPackageName=AWK.eliminateLastFieldOf(mangle,".");
			mangle=AWK.lastFieldOf(mangle,".");}
		if (context.containsKey("TheCreatedLibrary")) 
			theCreatedLib=(BasicMtlLibrary)context.get("TheCreatedLibrary");
		else {
			//new TLL creation with all of its necessary features initialized
			int lineNumber=Integer.parseInt((String)Lib.getProperty("LineNumber").getValue());
			theCreatedLib=new BasicMtlLibrary(libName,mangle,libPackageName,lineNumber);
			theCreatedLib.setAllReferedTypes(new AllReferedTypes());
			TheLibraryClass theLibraryClass=new TheLibraryClass(libName,mangle,lineNumber);
			Property inheritance=(Property)Lib.getProperty("Inheritance");
			java.util.Vector parents;
			if (inheritance == null) parents=new java.util.Vector();
			else parents=(java.util.Vector)inheritance.getValue();
			InheritedTypesList parentsTypesList=new InheritedTypesList();
			for (int i=0;i<parents.size();i++) {
				QualifiedName type=CommonFunctions.findOrAddType((java.util.Vector)parents.get(i),theCreatedLib);
				parentsTypesList.addElement(type);
			}
			theLibraryClass.setInheritance(parentsTypesList);
			if (parents.size()==0) //set the flag "completed inheritedSignatures" for the typeChecker
				theLibraryClass.setCompletedInheritedSignatures(true);
			theCreatedLib.appendClasses(theLibraryClass);
			theCreatedLib.setLibraryClass(theLibraryClass);
			theLibraryClass.setTheLibrary(theCreatedLib);
			context.put("TheCreatedLibrary",theCreatedLib);
 			} 
		QualifiedName theClassType=new QualifiedName();
		theClassType.addElement(theCreatedLib.getName());
		context.put("CurrentClassType",theClassType);
	}

	public void BasicMtlLibraryModelRef(Object objectModel,java.util.Map context)
	{	java.util.Vector modelAndAttribute=(java.util.Vector)objectModel;
		ModelRef model=(ModelRef)modelAndAttribute.get(0);
		Attribute attrib=(Attribute)modelAndAttribute.get(1);
		theCreatedLib.appendUsedModels(model);
		theCreatedLib.getLibraryClass().appendDefinedFeatures(attrib);
	}

	public void BasicMtlLibraryOperation(Object objectOp,java.util.Map context)
	{	Operation op=(Operation)objectOp;
		theCreatedLib.getLibraryClass().appendLocalSignatures(op.getTheSignature());
		theCreatedLib.getLibraryClass().appendDefinedFeatures(op);
	}
	
	public void BasicMtlLibraryUserClass(Object objectUserClass,java.util.Map context)
	{	UserClass userClass=(UserClass)objectUserClass;
		theCreatedLib.appendClasses(userClass);
	}
}
