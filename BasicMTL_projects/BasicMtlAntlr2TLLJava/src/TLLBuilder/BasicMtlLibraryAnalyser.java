/*
 * $Header: /tmp/cvs2svn/cvsroot/BasicMTL_projects/BasicMtlAntlr2TLLJava/src/TLLBuilder/BasicMtlLibraryAnalyser.java,v 1.4 2003-09-23 17:11:59 ffondeme Exp $
 * Created on 22 juil. 2003
 *
 */
package TLLBuilder;

import org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.AllReferedTypes;
import org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.Attribute;
import org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.BasicMtlLibrary;
import org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.InheritedTypesList;
import org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.ModelRef;
import org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.Operation;
import org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.QualifiedName;
import org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.TheLibraryClass;
import org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.UserClass;
import org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.UserDefinedClass;
import org.irisa.triskell.MT.utils.Java.AWK;
import org.irisa.triskell.MT.utils.Java.Mangler;
import org.irisa.triskell.MT.visitors.Java.AnalysingVisitor.Property;

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
		else { mangle=(String)((java.util.Vector)mangling.getValue()).get(2);
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
	
	private transient TheLibraryClass lastLibraryClass;
	public TheLibraryClass getTheLibraryClassFor (BasicMtlLibrary theLib) {
		if (this.lastLibraryClass == null || !this.lastLibraryClass.getTheLibrary().equals(theLib)) {
			this.lastLibraryClass = null;
			UserDefinedClass c;
			int limit = theLib.cardLibraryClass();
			for (int i = 0; i < limit; ++i) {
				c = theLib.getClasses(i);
				if ((c instanceof TheLibraryClass) && ((TheLibraryClass)c).getTheLibrary().equals(theLib))
					this.lastLibraryClass = (TheLibraryClass)c;
			}
		}
		return this.lastLibraryClass;
	}
	
	public void BasicMtlLibraryUserClass(Object objectUserClass,java.util.Map context)
	{	UserClass userClass=(UserClass)objectUserClass;
		theCreatedLib.appendClasses(userClass);
	}
}
