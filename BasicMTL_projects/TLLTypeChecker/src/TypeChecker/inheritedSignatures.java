/*
 * $Header: /tmp/cvs2svn/cvsroot/BasicMTL_projects/TLLTypeChecker/src/TypeChecker/inheritedSignatures.java,v 1.1 2003-08-06 15:55:30 jpthibau Exp $
 * Created on 30 juil. 2003
 *
 */
package TypeChecker;

import org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.*;

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
	
	public static java.util.Vector compatibleAndPresentOp(UserDefinedClass aClass,InheritedOpSignature parentSignature)
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
	
	public static boolean addAnInheritedSignature(UserDefinedClass aClass,InheritedOpSignature parentSignature)
	{	if (aClass.cardInheritedSignatures()==0)
			aClass.appendInheritedSignatures(parentSignature);
		else {
			boolean isRedefined=redefinedOp(aClass,parentSignature);
			java.util.Vector compatible_present=compatibleAndPresentOp(aClass,parentSignature);
			boolean isCompatible=((Boolean)compatible_present.get(0)).booleanValue();
			boolean isAlreadyPresent=((Boolean)compatible_present.get(1)).booleanValue();
			if (! isRedefined) {
				if (isCompatible) {
					if ((! isAlreadyPresent)
						|| (parentSignature.getOpName().startsWith("getRef_"))) //a same great-parent ref may be accepted from several direct parents 
						aClass.appendInheritedSignatures(parentSignature);
				}
				else if (! parentSignature.getOpName().startsWith("getRef_"))
					{	TLLtypechecking.getLog().error("parent signtaure is incompatible with already inherited signatures");} 
						TLLtypechecking.getLog().info(parentSignature.getOpName());
						TLLtypechecking.getLog().info(Integer.toString(parentSignature.getArgsCount()));
						TLLtypechecking.getLog().info("origin 1:"+parentSignature.getTypeWhichDefineOp());
						TLLtypechecking.getLog().info("origin 2:"+compatible_present.get(2));
				}  
		}
		return false;//no error
	}
	
	public static boolean addSignatures(UserDefinedClass aClass,UserDefinedClass parentClass,QualifiedName originType,QualifiedName relayer)
	{	int errors=0;
		for (int i=0;i<parentClass.cardLocalSignatures();i++) {
			//make the inherited signature from the parent local signature
			OpSignature localSignature=(OpSignature)parentClass.getLocalSignatures(i);
			InheritedOpSignature parentSignature=new InheritedOpSignature(localSignature.getOpName(),localSignature.getOpMangle());
			parentSignature.setArgsCount(localSignature.getArgsCount());
			parentSignature.setReturnedType(localSignature.getReturnedType());
			for (int j=0;j<localSignature.cardArgsTypes();j++)
				parentSignature.appendArgsTypes(localSignature.getArgsTypes(j));
			parentSignature.setParentThatRelayOp(relayer);
			parentSignature.setTypeWhichDefineOp(originType);
			if (! addAnInheritedSignature(aClass,parentSignature))
				errors++;
		}
		for (int i=0;i<parentClass.cardLocalSignatures();i++) {
			InheritedOpSignature parentSignature=(InheritedOpSignature)parentClass.getInheritedSignatures(i);
			if (! addAnInheritedSignature(aClass,parentSignature))
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
				    return addSignatures(aClass,parentClass,origin,relayer);} 
		}
		if (parentName.equals(theLib.getName())) {
			TheLibraryClass parentClass=theLib.getLibraryClass();
			if (! parentClass.getCompletedInheritedSignatures()) return true;
			else { QualifiedName origin=new QualifiedName();
					origin.addElement(theLib.getName());
					origin.addElement(theLib.getName());
				    return addSignatures(aClass,parentClass,origin,relayer); }
		}
		if (TLLtypechecking.loadedLibraries.containsKey(parentName)) {
			TheLibraryClass parentClass=(TheLibraryClass)TLLtypechecking.loadedLibraries.get(parentName);
			if (! parentClass.getCompletedInheritedSignatures()) return true;
			else { QualifiedName origin=new QualifiedName();
					origin.addElement(parentName);
					origin.addElement(parentName);
					return addSignatures(aClass,parentClass,origin,relayer);}
		}
	TLLtypechecking.getLog().error("the inherited parent"+parentName+" can't be resolved !");
	return true; 
	}

	public static boolean unsolvedExternTLLParent(String parentName,String className,UserDefinedClass aClass,QualifiedName relayer)
	{	if (TLLtypechecking.loadedLibraries.containsKey(parentName)) {
			KnownClasses knownClasses=((Library)TLLtypechecking.loadedLibraries.get(parentName)).getKnownTypes();
			if (knownClasses.containsKey(className)) {
				UserClass parentClass=(UserClass)knownClasses.get(className);
				if (! parentClass.getCompletedInheritedSignatures()) return true;
				else { QualifiedName origin=new QualifiedName();
						origin.addElement(parentName);
						origin.addElement(className);
				    	return addSignatures(aClass,parentClass,origin,relayer); }
			}
		}
		TLLtypechecking.getLog().error("the inherited parent class"+parentName+":"+className+" can't be resolved !");
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
		QualifiedName relayer=new QualifiedName(); //aClass is the ralayer for inherited signatures
		relayer.addElement(theLib.getName());
		relayer.addElement(aClass.getName());
		for (int i=0;i<parents.size();i++) {
			QualifiedName parentType=(QualifiedName)parents.get(i);
			String parentName=(String)parentType.get(0);
			if (parentType.size()==1)
				{ if (unSolvedSingleNameParent(parentName,aClass,theLib,relayer))
				  	  return true;
				}
		   else {
				String className=(String)parentType.get(1);
				if (unsolvedExternTLLParent(parentName,className,aClass,relayer))
					return true;
		   	}
		}
		return true;
	}

	public static int synthetizeInheritedSignatures(BasicMtlLibrary theLib)
	{	java.util.Vector remainingUnsolved=new java.util.Vector();
		remainingUnsolved.addElement(theLib.getLibraryClass());
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
					TLLtypechecking.getLog().info(((UserDefinedClass)remainingUnsolved.get(i)).getName());
			} 
		return remainingUnsolved.size();
	}
}