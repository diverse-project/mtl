/*
 * $Header: /tmp/cvs2svn/cvsroot/BasicMTL_projects/TLLTypeChecker/src/TypeChecker/allReferedTypes.java,v 1.3 2003-08-09 15:17:53 jpthibau Exp $
 * Created on 30 juil. 2003
 *
 */
package TypeChecker;

import org.irisa.triskell.MT.utils.Java.AWK;
import org.irisa.triskell.MT.utils.Java.Mangler;
import org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.*;

/**
 * @author jpthibau
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class allReferedTypes {
	
	static int errors;
	static int warnings;

	public static boolean checkIsnotAView(String TLLName,BasicMtlLibrary theLib)
	{	for (int i=0;i<theLib.cardUsedModels();i++)
		{	ModelRef model=(ModelRef)theLib.getUsedModels(i);
			if (model.getIsTyped())
			{ TypedModelRef view=(TypedModelRef)model;
				if (view.getView().equals(TLLName)) return false;
			}
		}
		return true;
	}

	public static boolean checkIsnotAnInheritedLib(String TLLName,BasicMtlLibrary theLib)
	{	InheritedTypesList parents=theLib.getLibraryClass().getInheritance();
		for (int i=0;i<parents.size();i++) {
			QualifiedName parentType=(QualifiedName)parents.get(i);
			if (TLLName.equals((String)parentType.get(0))) return false;
		}
		return true;
	}
	
	public static Library loadTLL(String libName,BasicMtlLibrary theLib)
	{	Library loadedTLL=null;
		if (TLLtypechecking.loadedLibraries.containsKey(libName))
			loadedTLL=(Library)TLLtypechecking.loadedLibraries.get(libName);
		else {
			loadedTLL=Library.load(TLLtypechecking.defaultTLLPath+libName+TLLtypechecking.tllSuffix);
			TLLtypechecking.loadedLibraries.put(libName,loadedTLL);
			if ((checkIsnotAView(loadedTLL.getName(),theLib))
				&& (checkIsnotAnInheritedLib(loadedTLL.getName(),theLib))) {
				QualifiedName usedLib=new QualifiedName();
				usedLib.setIsExternType(true);
				usedLib.setExternMangledName(loadedTLL.getMangle());
				usedLib.setExternCompleteName(loadedTLL.getPackageName()+usedLib.getExternMangledName());
				usedLib.setExternLibMangledName(loadedTLL.getMangle());
				usedLib.setExternLibCompleteName(loadedTLL.getPackageName()+usedLib.getExternLibMangledName());
				theLib.appendUsedLibs(usedLib);
			}
		}
		return loadedTLL;
	}
	
	public static boolean checkLocalClass(QualifiedName aType,String typeName,BasicMtlLibrary  theLib)
	{	if (typeName.equals(theLib.getName()))
		{	aType.setIsLocalType(true);
			aType.setLocalMangledName(theLib.getMangle());
			return true;
		}
		KnownClasses knownClasses=theLib.getKnownTypes();
		if (knownClasses.containsKey(typeName))
		{	aType.setIsLocalType(true);
			aType.setLocalMangledName(((UserClass)knownClasses.get(typeName)).getMangle());
			return true;
		}
		return false;
	}
	
	public static boolean checkModel(QualifiedName aType,String typeName,BasicMtlLibrary  theLib)
	{ 	if (typeName.equals("RepositoryModel")) {
			aType.setIsModelType(true);
			return true;
		}
		BasicMtlLibrary bmtlLib=(BasicMtlLibrary)theLib;
		for(int i=0;i<bmtlLib.cardUsedModels();i++)
		{	ModelRef model=bmtlLib.getUsedModels(i);
			if (model.getName().equals(typeName))
				{	aType.setIsModelType(true);
					return true;
				}
		}
		return false;
	}
	
	public static boolean checkStandardLib(QualifiedName aType,String libName)
	{	if (libName.equals("Standard"))
		{	aType.setIsExternType(true);
			aType.setExternMangledName(Mangler.mangle("BMTL_",(String)aType.get(1)));;
			aType.setExternCompleteName("org.irisa.triskell.MT.DataTypes.Java.defaultImpl;"+aType.getExternMangledName());
			aType.setExternLibMangledName("DefaultImpl");
			aType.setExternLibCompleteName("org.irisa.triskell.MT.DataTypes.Java.defaultImpl;DefaultImpl");
			return true;
		}
		return false;
	}
	
	public static boolean checkExternLibName(QualifiedName aType,String libName,BasicMtlLibrary theLib)
	{	Library theLoadedTll=loadTLL(libName,theLib);
		if (theLoadedTll != null)
			{	aType.setIsExternType(true);
				aType.setExternMangledName(theLoadedTll.getMangle());
				aType.setExternCompleteName(theLoadedTll.getPackageName()+aType.getExternMangledName());
				aType.setExternLibMangledName(theLoadedTll.getMangle());
				aType.setExternLibCompleteName(theLoadedTll.getPackageName()+aType.getExternLibMangledName());
				return true;
		}
		return false;
	}
	
	public static boolean checkTLLClass(QualifiedName aType,String libName,BasicMtlLibrary theLib)
	{	Library theLoadedTll=loadTLL(libName,theLib);
		if (theLoadedTll != null)
			{	String className=(String)aType.get(1);
				KnownClasses knownClasses=theLoadedTll.getKnownTypes();
				if (knownClasses.containsKey(className)) {
					aType.setIsExternType(true);
					aType.setExternMangledName(((UserClass)knownClasses.get(className)).getMangle());
					aType.setExternCompleteName(theLoadedTll.getPackageName()+aType.getExternMangledName());
					aType.setExternLibMangledName(theLoadedTll.getMangle());
					aType.setExternLibCompleteName(theLoadedTll.getPackageName()+aType.getExternLibMangledName());
					return true;
				}
		}
		return false;
	}
	
	public static int checkAllReferedTypes(BasicMtlLibrary theLib)
	{	errors=0;
		warnings=0;
		int correctlyChecked=0;
		AllReferedTypes allReferedTypes=theLib.getAllReferedTypes();
		for(int i=0;i<allReferedTypes.size();i++) {
			QualifiedName aType=(QualifiedName)allReferedTypes.get(i);
			String firstName=(String)aType.get(0);
			if (checkModel(aType,firstName,theLib)) correctlyChecked++;
			else if (aType.size()==1) { //a single name
					if (checkLocalClass(aType,firstName,theLib)) correctlyChecked++;
					else	if (checkExternLibName(aType,firstName,theLib)) correctlyChecked++;
							else {TLLtypechecking.getLog().error("Unknown Local Type"+firstName);
									errors++;} 
					}
				else //extern library class::...
					if (aType.size()==2) {
						/*if (checkStandardLib(aType,firstName)) correctlyChecked++;
						else*/ if (checkTLLClass(aType,firstName,theLib)) correctlyChecked++;
							else { TLLtypechecking.getLog().error("Extern class type not found:"+firstName+aType.get(1));
									errors++;} 
					}
					else //type which is not a model element and has more thn 2 components
						//Certainly a mistake !
							{ TLLtypechecking.getLog().error("or Undeclared model or extern class type having more than 2 components !");;
								TLLtypechecking.getLog().info(firstName+"could be the undeclared model");
								TLLtypechecking.getLog().info("The type components :");
								for(int j=0;j<aType.size();j++)
									TLLtypechecking.getLog().info(aType.get(j));
								errors++;
								}
			}
		TLLtypechecking.getLog().info(correctlyChecked+" types correctly checked.");
		return errors+warnings;
	}

}
