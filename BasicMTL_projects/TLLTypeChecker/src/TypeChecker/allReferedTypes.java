/*
 * $Id: allReferedTypes.java,v 1.20 2004-06-09 09:36:59 jpthibau Exp $
 * Created on 30 juil. 2003
 *
 * Copyright 2004 - INRIA - LGPL license
 */
package TypeChecker;

//import java.util.Iterator;

//import org.irisa.triskell.MT.utils.Java.AWK;
//import org.irisa.triskell.MT.utils.Java.Mangler;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;

import org.irisa.triskell.MT.utils.MessagesHandler.MSGHandler;
import org.irisa.triskell.MT.visitors.Java.AnalysingVisitor.Property;
import org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.*;

/**
 * @author jpthibau
 *
 * This class give tools to verify that the types refered in a library exist 
 * (either in this library or in another which has been allready compiled).
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
	
	/**
	 * Load an allready processed library from its serialisation (TLL files)
	 * look for the TLL file in the defaultTLLPaths
	 * 
	 * @param libName  : name of the library to load
	 * @param theLib	: 
	 * @return Library : loaded library
	 */
	public static Library loadTLL(String libName,BasicMtlLibrary theLib)
	{	Library loadedTLL=null;
		if (TLLtypechecking.loadedLibraries.containsKey(libName))
			loadedTLL=(Library)TLLtypechecking.loadedLibraries.get(libName);
		else {
			String name = null;
			boolean isModel = false, isView = false;
			ModelRef mr = null;
			for (int i = 0; name == null  && i < theLib.cardUsedModels(); ++i) {
				mr = theLib.getUsedModels(i);
				if (mr.getName().equals(libName)) {
					isModel = ((mr instanceof RepositoryRef) || (mr instanceof TypedModelRef) && ((TypedModelRef)mr).getView().equals("RepositoryModel"));
					isView = !isModel;
					if (isView)
						name = ((TypedModelRef)mr).getView();
					else
						name = "API";
				}
			}
			if (name == null)
				name = libName;
			if (isModel) {
/*				QualifiedName usedLib=new QualifiedName();
				usedLib.add(libName);
				usedLib.setIsExternType(true);
				usedLib.setIsRepositoryModel(true);
				usedLib.setIsModelType(true);
				usedLib.setExternMangledName("API");
				usedLib.setExternCompleteName("org.irisa.triskell.MT.repository.API.Java.API");
				usedLib.setExternLibMangledName("API");
				usedLib.setExternLibCompleteName("org.irisa.triskell.MT.repository.API.Java.API");
				usedLib.setDeclarationName("org.irisa.triskell.MT.repository.API.Java.API");//*/
			} else {
				if (TLLtypechecking.loadedLibraries.containsKey(name))
					loadedTLL=(Library)TLLtypechecking.loadedLibraries.get(name);
				else {
					if (TLLtypechecking.defaultTLLPaths != null) {
						java.util.Vector possiblesPaths=TLLtypechecking.defaultTLLPaths;
						int triedPaths=0;
						do {
							try {
								loadedTLL=Library.load((String)possiblesPaths.get(triedPaths)+name+TLLtypechecking.tllSuffix);							
							}
							catch (Exception e){loadedTLL=null;}
							triedPaths++;
						}
						while (loadedTLL==null && triedPaths<possiblesPaths.size());
						}
					if (loadedTLL==null)
						//last chance : same location for loading and production
					loadedTLL=Library.load(TLLtypechecking.defaultTLLPath+name+TLLtypechecking.tllSuffix);
				}
				if (loadedTLL == null) 
				{ 
					return null;
				}
				TLLtypechecking.loadedLibraries.put(libName,loadedTLL);
				TLLtypechecking.loadedLibraries.put(name,loadedTLL);
				if (isView) {
/*					QualifiedName usedLib=new QualifiedName();
					usedLib.add(libName);
					usedLib.setIsExternType(true);
					usedLib.setIsModelType(true);
					usedLib.setExternMangledName(loadedTLL.getMangle());
					usedLib.setExternCompleteName(loadedTLL.getPackageName()+'.'+usedLib.getExternMangledName());
					usedLib.setExternLibMangledName(loadedTLL.getMangle());
					usedLib.setExternLibCompleteName(loadedTLL.getPackageName()+usedLib.getExternLibMangledName());
					usedLib.setDeclarationName(usedLib.getExternCompleteName()+"Interface");*/
				} else if ((checkIsnotAnInheritedLib(loadedTLL.getName(),theLib))) {
					QualifiedName usedLib=new QualifiedName();
					usedLib.add(loadedTLL.getName());
					usedLib.setIsExternType(true);
					usedLib.setExternMangledName(loadedTLL.getMangle());
					usedLib.setExternCompleteName(loadedTLL.getPackageName()+'.'+usedLib.getExternMangledName());
					usedLib.setExternLibMangledName(loadedTLL.getMangle());
					usedLib.setExternLibCompleteName(loadedTLL.getPackageName()+usedLib.getExternLibMangledName());
					usedLib.setDeclarationName(usedLib.getExternCompleteName()+"Interface");
					theLib.appendUsedLibs(usedLib);
					}
			}
		}
		return loadedTLL;
	}
	
	/**
	 * loadTLLAllKnownClasses loads the TLL and gives its AllKnownClasses names
	 * The returned collection contains first the library name then userclasses names
	 * this method is called for library inheritance.
	 * Load an allready processed library from its serialisation (TLL files)
	 * look for the TLL file in the defaultTLLPaths
	 * 
	 * @param libName  : name of the library to load
	 * @param theLib	: 
	 * @return Library : loaded library
	 */
	public static Collection loadTLLAllKnownClasses(String libName)
	{	Library loadedTLL=null;
		String libraryName = null;
		if (TLLtypechecking.defaultTLLPaths != null) {
			java.util.Vector possiblesPaths=TLLtypechecking.defaultTLLPaths;
			int triedPaths=0;
			do {
				try {
					loadedTLL=Library.load((String)possiblesPaths.get(triedPaths)+libName+TLLtypechecking.tllSuffix);							
				} catch (Exception e){loadedTLL=null;}
				triedPaths++;
			}
			while (loadedTLL==null && triedPaths<possiblesPaths.size());
			}
		if (loadedTLL==null)
			//last chance : same location for loading and production
			loadedTLL=Library.load(TLLtypechecking.defaultTLLPath+libName+TLLtypechecking.tllSuffix);
			
		if (loadedTLL == null)  
					return null;
		Enumeration allKnownClasses = loadedTLL.knownTypes.elements();
		ArrayList result = new ArrayList();
		while (allKnownClasses.hasMoreElements())
			{ 	UserDefinedClass c = (UserDefinedClass)allKnownClasses.nextElement();
				if ( c instanceof UserClass )
					result.add(((UserClass)c).getName());
				if ( c instanceof TheLibraryClass )
				libraryName = ((TheLibraryClass)c).getName();
			}
		result.add(0,libraryName);
		return result;
	}
	
	public static boolean checkLocalClass(QualifiedName aType,String typeName,BasicMtlLibrary  theLib)
	{	if (typeName.equals(theLib.getName()))
		{	aType.setIsLocalType(true);
			aType.setLocalMangledName(theLib.getMangle());
			aType.setExternCompleteName(theLib.getPackageName()+"."+theLib.getMangle());
			aType.setDeclarationName(theLib.getPackageName()+'.'+theLib.getMangle()+"Interface");
			aType.setPureDeclarationName(theLib.getPackageName()+'.'+theLib.getMangle());
			return true;
		}
		KnownClasses knownClasses=theLib.getKnownTypes();
		if (knownClasses.containsKey(typeName))
		{	aType.setIsLocalType(true);
			aType.setLocalMangledName(((UserClass)knownClasses.get(typeName)).getMangle());
			aType.setExternCompleteName(theLib.getPackageName()+"."+aType.getLocalMangledName());
			aType.setDeclarationName(theLib.getPackageName() + '.' + aType.getLocalMangledName()+"Interface");
			aType.setPureDeclarationName(theLib.getPackageName() + '.' + aType.getLocalMangledName());
			if (aType.size() == 1)
				aType.insertElementAt(theLib.getName(), 0);
			return true;
		}
		return false;
	}
	
	public static boolean checkModel(QualifiedName aType,String typeName,BasicMtlLibrary  theLib)
	{ 	if (typeName.equals("RepositoryModel")) {
			aType.setIsModelType(true);
			aType.setDeclarationName("org.irisa.triskell.MT.repository.API.Java.API");
			return true;
		}
		BasicMtlLibrary bmtlLib=(BasicMtlLibrary)theLib;
		for(int i=0;i<bmtlLib.cardUsedModels();i++)
		{	ModelRef model=bmtlLib.getUsedModels(i);
			if (model.getName().equals(typeName))
				{	if ((model instanceof RepositoryRef) || ((TypedModelRef)model).getView().equals("RepositoryModel")){
						aType.setIsExternType(true);
						aType.setIsModelType(true);
						aType.setIsRepositoryModel(true);
						QualifiedName me = new QualifiedName();
						me.add("Standard");
						me.add("ModelElement");
						checkType(me, theLib);
						aType.setDeclarationName(me.getDeclarationName());
					} else {
						Library l = loadTLL(model.getName(), theLib);
						boolean ok = checkTLLClass(aType, l.getName(), theLib);
						aType.setIsExternType(true);
						aType.setIsModelType(true);
						return ok;
					}
					return true;
				}
		}
		return false;
	}
	/*
	public static boolean checkStandardLib(QualifiedName aType,String libName)
	{	if (libName.equals("Standard"))
		{	aType.setIsExternType(true);
			aType.setExternMangledName(Mangler.mangle("BMTL_",(String)aType.get(1)));;
			aType.setExternCompleteName("org.irisa.triskell.MT.DataTypes.Java.defaultImpl;"+aType.getExternMangledName());
			aType.setExternLibMangledName("DefaultImpl");
			aType.setExternLibCompleteName("org.irisa.triskell.MT.DataTypes.Java.defaultImpl;DefaultImpl");
			aType.setDeclarationName(aType.getExternCompleteName());
			return true;
		}
		return false;
	}*/
	
	public static boolean checkExternLibName(QualifiedName aType,String libName,BasicMtlLibrary theLib)
	{	Library theLoadedTll=loadTLL(libName,theLib);
		if (theLoadedTll != null)
			{	aType.setIsExternType(true);
				aType.setExternMangledName(theLoadedTll.getMangle());
				aType.setExternCompleteName(theLoadedTll.getPackageName()+'.'+aType.getExternMangledName());
				aType.setExternLibMangledName(theLoadedTll.getMangle());
				aType.setExternLibCompleteName(theLoadedTll.getPackageName()+'.'+aType.getExternLibMangledName());
				aType.setDeclarationName(aType.getExternCompleteName() + "Interface");
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
					UserDefinedClass theClass = (UserDefinedClass)knownClasses.get(className);
					Property manualMangle = theClass.getProperty("ManualMangling");
					boolean isManuallyMangled = manualMangle != null && ((Boolean)manualMangle.getValue()).booleanValue();
					aType.setIsExternType(true);
					aType.setExternMangledName(theClass.getMangle());
					if (isManuallyMangled) {
						aType.setExternCompleteName(theClass.getMangle());
						aType.setDeclarationName(theClass.getMangle());
					} else {
						aType.setExternCompleteName(theLoadedTll.getPackageName()+'.'+aType.getExternMangledName());
						aType.setDeclarationName(aType.getExternCompleteName()+"Interface");
						aType.setPureDeclarationName(aType.getExternCompleteName());
					}
					aType.setExternLibMangledName(theLoadedTll.getMangle());
					aType.setExternLibCompleteName(theLoadedTll.getPackageName()+'.'+aType.getExternLibMangledName());
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
			if (checkType((QualifiedName)allReferedTypes.get(i), theLib))
				correctlyChecked++;
			}
		MSGHandler.debug(allReferedTypes.class,306,correctlyChecked+" types correctly checked.");
		return errors+warnings;
	}
	
	/**
	 * Verify that a type is correctly declared before usage.
	 * @param aType 	: type to verify declaration
	 * @param theLib	:
	 * @return boolean	: true is correctly declared
	 */
	public static boolean checkType (QualifiedName aType, BasicMtlLibrary theLib) {
		String firstName=(String)aType.get(0);
		if (checkModel(aType,firstName,theLib)) return true;
		else if (aType.size()==1 || firstName.equals(theLib.getName())) { //a single name
				if (checkLocalClass(aType,(String)aType.get(aType.size() - 1),theLib)) return true;
				else if (checkExternLibName(aType,firstName,theLib)) return true;
					else 
					{
						MSGHandler.error(allReferedTypes.class,324,"Unknown Local Type: "+firstName);
						MSGHandler.debug(allReferedTypes.class,325,"   card " + aType.cardTypeForVarDeclarations() +" " + aType.cardTypeForFeatures());
						// retreive the location where this QualifiedName was used
						for(int i =0; i < aType.cardTypeForVarDeclarations(); i++)
						{
							MSGHandler.error(allReferedTypes.class,329,"   " +
								aType.getTypeForVarDeclarations(i).getProperty("FileName").getValue() + ", line " +
								aType.getTypeForVarDeclarations(i).getProperty("LineNumber").getValue());
						}
						for(int i =0; i < aType.cardTypeForFeatures(); i++)
						{
							MSGHandler.error(allReferedTypes.class,335,"   " +
								aType.getTypeForFeatures(i).getProperty("FileName").getValue() + ", line " +
								aType.getTypeForFeatures(i).getProperty("LineNumber").getValue());
						}
						errors++;
					} 
				}
			else //extern library class::...
				if (aType.size()==2) {
					/*if (checkStandardLib(aType,firstName)) return true;
					else*/ if (checkTLLClass(aType,firstName,theLib)) return true;
						else { MSGHandler.error(allReferedTypes.class,346,"Extern class type not found:"+firstName + "::" +aType.get(1));
								errors++;} 
				}
				else //type which is not a model element and has more than 2 components
					//Certainly a mistake !
				{ 
					String  typeComponentMsg;
					MSGHandler.error(allReferedTypes.class,353,"Undeclared model or extern class type having more than 2 components !");;
					MSGHandler.error(allReferedTypes.class,354,firstName+" may be the undeclared model");
					typeComponentMsg  = "The type components : ";
					for(int j=0;j<aType.size();j++)
						typeComponentMsg+="<"+aType.get(j)+"> ";
					MSGHandler.error(allReferedTypes.class,358,typeComponentMsg);
					errors++;
				}
		return false;
	}

}
