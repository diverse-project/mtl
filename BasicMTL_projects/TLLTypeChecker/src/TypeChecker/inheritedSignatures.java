/*
 * $Header: /tmp/cvs2svn/cvsroot/BasicMTL_projects/TLLTypeChecker/src/TypeChecker/inheritedSignatures.java,v 1.7 2003-09-23 17:16:25 ffondeme Exp $
 * Created on 30 juil. 2003
 *
 */
package TypeChecker;

import org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.BasicMtlLibrary;
import org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.InheritedOpSignature;
import org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.InheritedTypesList;
import org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.KnownClasses;
import org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.Library;
import org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.OpSignature;
import org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.QualifiedName;
import org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.TheLibraryClass;
import org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.UserClass;
import org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.UserDefinedClass;
import org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.signatures.ComputedSignature;

/**
 * @author jpthibau
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class inheritedSignatures {

	public static boolean redefinedOp(UserDefinedClass aClass,InheritedOpSignature parentSignature)
	//determine if the parentSignature is redefined in the local signatures of the class
	{	int i,limit;
		limit=aClass.cardLocalSignatures();
		for(i=0;i<limit;i++) {
			if ( (parentSignature.getOpName().equals(aClass.getLocalSignatures(i).getOpName())) //same operation name
				&& (parentSignature.getArgsCount()==aClass.getLocalSignatures(i).getArgsCount())) //same number of arguments
			return true;
		}
	return false;
	}
	
	public static boolean areIdentical (QualifiedName qn1,QualifiedName qn2)
	{	if (qn1.size() != qn2.size()) return false;
		for (int i=0;i<qn1.size();i++)
			if (! ((String)qn1.get(i)).equals((String)qn2.get(i))) return false;
		return true;
	}
	
	public static java.util.Vector compatibleAndPresentOp(UserDefinedClass aClass,InheritedOpSignature parentSignature, BasicMtlLibrary theLib)
	//determine if the parentsignature is compatible with signatures already present in the inherited set of the class
	//also determine if the signature is already present in the inherited set
	{	java.util.Vector result=new java.util.Vector();
		int i,limit;
		boolean compatible=true;
		boolean present=false;
		QualifiedName origin=null;
		limit=aClass.cardInheritedSignatures();
		for(i=0;i<limit;i++) {
			if ( (parentSignature.getOpName().equals(aClass.getInheritedSignatures(i).getOpName())) //same operation name
				&& (parentSignature.getArgsCount()==aClass.getInheritedSignatures(i).getArgsCount())) //same number of arguments
				{	present=true;
					if (! areIdentical(parentSignature.getTypeWhichDefineOp(),aClass.getInheritedSignatures(i).getTypeWhichDefineOp()))
						{	compatible=false;
							origin=aClass.getInheritedSignatures(i).getTypeWhichDefineOp();
						} 
				}
		}
		result.addElement(new Boolean(compatible));
		result.addElement(new Boolean(present));
		result.addElement(origin);
		return result;
	}
	
	public static boolean addAnInheritedSignature(UserDefinedClass aClass,InheritedOpSignature parentSignature, BasicMtlLibrary theLib)
	{	if (aClass.cardInheritedSignatures()==0)
			aClass.appendInheritedSignatures(parentSignature);
		else {
			boolean isRedefined=redefinedOp(aClass,parentSignature);
			java.util.Vector compatible_present=compatibleAndPresentOp(aClass,parentSignature, theLib);
			boolean isCompatible=((Boolean)compatible_present.get(0)).booleanValue();
			boolean isAlreadyPresent=((Boolean)compatible_present.get(1)).booleanValue();
			if (! isRedefined) {
				if (isCompatible) {
					if ((! isAlreadyPresent)
						|| (parentSignature.getOpName().startsWith("getRef_"))) //a same great-parent ref may be accepted from several direct parents 
						aClass.appendInheritedSignatures(parentSignature);
				}
				else if (! parentSignature.getOpName().startsWith("getRef_"))
					{	TLLtypechecking.getLog().error("parent signtaure is incompatible with already inherited signatures");
						TLLtypechecking.getLog().info(parentSignature.getOpName());
						TLLtypechecking.getLog().info(Integer.toString(parentSignature.getArgsCount()));
						TLLtypechecking.getLog().info("origin 1:"+parentSignature.getTypeWhichDefineOp());
						TLLtypechecking.getLog().info("origin 2:"+compatible_present.get(2)); }
				}  
		}
		return true;//no error
	}
	
	public static boolean addSignatures(UserDefinedClass aClass,UserDefinedClass parentClass,QualifiedName originType,QualifiedName relayer, BasicMtlLibrary theLib)
	{	int errors=0;
		for (int i=0;i<parentClass.cardLocalSignatures();i++) {
			//make the inherited signature from the parent local signature
			OpSignature localSignature=(OpSignature)parentClass.getLocalSignatures(i);
			InheritedOpSignature parentSignature=new InheritedOpSignature(localSignature.getOpName(),localSignature.getOpMangle());
			if (!(localSignature instanceof ComputedSignature))
				parentSignature.setThrowsException(true);
			parentSignature.setArgsCount(localSignature.getArgsCount());
			parentSignature.setReturnedType(localSignature.getReturnedType());
			for (int j=0;j<localSignature.cardArgsTypes();j++)
				parentSignature.appendArgsTypes(localSignature.getArgsTypes(j));
			parentSignature.setTypeWhichDefineOp(originType);
			parentSignature.setParentThatRelayOp(relayer);
			if (! addAnInheritedSignature(aClass,parentSignature, theLib))
				errors++;
		}
		for (int i=0;i<parentClass.cardInheritedSignatures();i++) {
			InheritedOpSignature parentSignature=(InheritedOpSignature)parentClass.getInheritedSignatures(i);
			if (! addAnInheritedSignature(aClass,parentSignature, theLib))
				errors++;
		}			
		if (errors>0) return true;
		else return false;
	}
	
	public static boolean unSolvedSingleNameParent(String parentName,UserDefinedClass aClass,BasicMtlLibrary theLib,QualifiedName relayer)
	{ 	KnownClasses knownClasses=theLib.getKnownTypes();
		if (knownClasses.containsKey(parentName)) {
			UserClass parentClass=(UserClass)knownClasses.get(parentName);
			if (! parentClass.getCompletedInheritedSignatures()) return true;
			else { QualifiedName origin=new QualifiedName();
					origin.addElement(theLib.getName());
					origin.addElement(parentName);
					allReferedTypes.checkLocalClass(origin, parentName, theLib);
				    return addSignatures(aClass,parentClass,origin,relayer, theLib);} 
		}
		if (parentName.equals(theLib.getName())) {
			TheLibraryClass parentClass=theLib.getLibraryClass();
			if (! parentClass.getCompletedInheritedSignatures()) return true;
			else { QualifiedName origin=new QualifiedName();
					origin.addElement(theLib.getName());
					origin.addElement(theLib.getName());
					allReferedTypes.checkLocalClass(origin, theLib.getName(), theLib);
				    return addSignatures(aClass,parentClass,origin,relayer, theLib); }
		}
		if (TLLtypechecking.loadedLibraries.containsKey(parentName)) {
			TheLibraryClass parentClass=((BasicMtlLibrary)TLLtypechecking.loadedLibraries.get(parentName)).getLibraryClass();
			if (! parentClass.getCompletedInheritedSignatures()) return true;
			else { QualifiedName origin=new QualifiedName();
					origin.addElement(parentName);
					origin.addElement(parentName);
					allReferedTypes.checkTLLClass(origin, parentName, theLib);
					return addSignatures(aClass,parentClass,origin,relayer, theLib);}
		}
	TLLtypechecking.getLog().error("the inherited parent"+parentName+" can't be resolved !");
	return true; 
	}

	public static boolean unsolvedExternTLLParent(String parentName,String className,UserDefinedClass aClass,QualifiedName relayer, BasicMtlLibrary theLib)
	{	if (TLLtypechecking.loadedLibraries.containsKey(parentName)) {
			KnownClasses knownClasses=((Library)TLLtypechecking.loadedLibraries.get(parentName)).getKnownTypes();
			if (knownClasses.containsKey(className)) {
				UserClass parentClass=(UserClass)knownClasses.get(className);
				if (! parentClass.getCompletedInheritedSignatures()) return true;
				else { QualifiedName origin=new QualifiedName();
						origin.addElement(parentName);
						origin.addElement(className);
						allReferedTypes.checkTLLClass(origin, parentName, theLib);
				    	return addSignatures(aClass,parentClass,origin,relayer, theLib); }
			}
		}
		TLLtypechecking.getLog().error("the inherited parent class "+parentName+"::"+className+" can't be resolved !");
		return true; 
	}
	
	public static boolean inheritanceUnsolved(UserDefinedClass aClass,BasicMtlLibrary theLib)
	{	if (aClass.getCompletedInheritedSignatures()) //empty set : already done
			return false;
		InheritedTypesList parents=aClass.getInheritance();
		if (parents.size()==0)
			{	//not already marked as completed ???
				aClass.setCompletedInheritedSignatures(true);
				return false;
			}
//		QualifiedName relayer=new QualifiedName(); //aClass is the ralayer for inherited signatures
//		relayer.addElement(theLib.getName());
//		relayer.addElement(aClass.getName());
//		allReferedTypes.checkLocalClass(relayer, aClass.getName(), theLib);
		for (int i=0;i<parents.size();i++) {
			QualifiedName parentType=(QualifiedName)parents.get(i);
			allReferedTypes.checkType(parentType, theLib);
			String parentName=(String)parentType.get(0);
			if (parentType.size()==1)
				{ if (unSolvedSingleNameParent(parentName,aClass,theLib,parentType))
				  	  return true;
				}
		   else {
				String className=(String)parentType.get(1);
		   		if (parentName.equals(theLib.getName())) {
					if (unSolvedSingleNameParent(className,aClass,theLib,parentType))
						return true;
		   		} else {
					if (unsolvedExternTLLParent(parentName,className,aClass,parentType, theLib))
						return true;
				}
		   	}
		}
		aClass.setCompletedInheritedSignatures(true);
		return false;
	}

	public static int synthetizeInheritedSignatures(BasicMtlLibrary theLib)
	{	java.util.Vector remainingUnsolved=new java.util.Vector();
		//The library class is part of the classes
		//remainingUnsolved.addElement(theLib.getLibraryClass());
		for (int i=0;i<theLib.cardClasses();i++)
			remainingUnsolved.addElement(theLib.getClasses(i));
		int lastRemainigCount=remainingUnsolved.size()+1;
		while ((remainingUnsolved.size()!=0) && (remainingUnsolved.size()<lastRemainigCount))
		{	java.util.Vector remaining=new java.util.Vector();
			for (int i=0;i<remainingUnsolved.size();i++)
			{	UserDefinedClass aClass=(UserDefinedClass)remainingUnsolved.get(i);
				if (inheritanceUnsolved(aClass,theLib))
					remaining.addElement(aClass);
			}
			lastRemainigCount=remainingUnsolved.size();
			remainingUnsolved=remaining;
		}
		if (remainingUnsolved.size()!=0)
			{	TLLtypechecking.getLog().error("There are errors or there is an inheritance loop between following classes");
				for (int i=0;i<remainingUnsolved.size();i++)
					TLLtypechecking.getLog().error(((UserDefinedClass)remainingUnsolved.get(i)).getName());
			} 
		return remainingUnsolved.size();
	}
}
