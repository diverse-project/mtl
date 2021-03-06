/*
 * $Id: allReferedTypes.java,v 1.25 2005-07-15 16:04:23 ffondeme Exp $
 * Created on 30 juil. 2003
 *
 * Copyright 2004 - INRIA - LGPL license
 */
package TypeChecker;

//import java.util.Iterator;

//import org.irisa.triskell.MT.utils.Java.AWK;
//import org.irisa.triskell.MT.utils.Java.Mangler;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Iterator;

import org.apache.log4j.Logger;
import org.irisa.triskell.MT.utils.MessagesHandler.MSGHandler;
import org.irisa.triskell.MT.visitors.Java.AnalysingVisitor.Property;
import org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.*;

import CompilerEvents.CompilerMessageFactory;

/**
 * @author jpthibau
 *
 * This class give tools to verify that the types refered in a library exist 
 * (either in this library or in another which has been allready compiled).
 */
public class allReferedTypes extends CompilerObservable
{
	// we define a Singleton (see [GOF])
	static final private allReferedTypes singleton = new allReferedTypes ();
	static public allReferedTypes instance() { return singleton; }
	
	static final Logger log=MSGHandler.init();
	
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
	
	public static Library loadTLL(String libName)
	{	Library loadedTLL=null;
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
		if (loadedTLL==null) {
			//last chance : same location for loading and production
			loadedTLL=Library.load(TLLtypechecking.defaultTLLPath+libName+TLLtypechecking.tllSuffix);
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
	public static Collection loadTLLAllKnownClasses(String libName) {
		return loadTLLAllKnownClasses(loadTLL(libName));
	}
	
	public static Collection loadTLLAllKnownClasses(Library loadedTLL) {
		String libraryName = null;

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
	
	public static UserClass getUserDefinedClass(String name, Library lib) {
		Enumeration allKnownClasses = lib.knownTypes.elements();
		while (allKnownClasses.hasMoreElements())
			{ 	UserDefinedClass c = (UserDefinedClass)allKnownClasses.nextElement();
				if ( (c instanceof UserClass) && (((UserClass)c).getName().equals((name))) )
					return (UserClass)c;
			}
		return null;
	}
	
	/**
	 * The qualified names
	 * @param name
	 * @param lib
	 * @return
	 */
	public static Collection getUserClassInheritance(String name, Library lib) {
		UserClass c = getUserDefinedClass(name, lib);
		if (c == null)
			return null;
		if (c.inheritance == null)
			return new ArrayList(0);
		return (Collection)c.inheritance.clone();
	}
	
	/**
	 * The names
	 * @param name
	 * @param lib
	 * @return
	 */
	public static Collection getUserClassLocalInheritance(String name, Library lib) {
		Collection c = getUserClassInheritance(name, lib);
		if (c == null) {
			return Arrays.asList(new Object [0]);
		}
			
		Iterator it = c.iterator();
		Collection ret = new ArrayList();
		while (it.hasNext()) {
			QualifiedName qn = (QualifiedName)it.next();
			if (qn.size() == 1)
				ret.add(qn.get(0));
			else if (qn.get(0).equals(lib.getName()))
				ret.add(qn.get(1));
		}
		return ret;
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
		log.debug(correctlyChecked+" types correctly checked.");
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
						boolean found = false;
						
						log.error("Unknown Local Type : "+firstName);
						log.debug("   card " + aType.cardTypeForVarDeclarations() +" " + aType.cardTypeForFeatures());
						
						// retreive the location where this QualifiedName was used
						for(int i =0; i < aType.cardTypeForVarDeclarations(); i++)
						{
							VarDeclaration item = aType.getTypeForVarDeclarations(i);
							String fileName   = (String) item.getProperty("FileName").getValue();
							String lineNumber = (String) item.getProperty("LineNumber").getValue();
							log.error("   " + fileName + ", line " + lineNumber);

							// we build a message and send it to potential observers
							CompilerMessageFactory.instance().notifyObservers (CompilerMessageFactory.instance().createUnknownTypeInVarDeclaration (item));
							found = true;
						}
						for(int i =0; i < aType.cardTypeForFeatures(); i++)
						{
							Feature item = aType.getTypeForFeatures(i);
							String fileName   = (String) item.getProperty("FileName").getValue();
							String lineNumber = (String) item.getProperty("LineNumber").getValue();
							log.error("   " + fileName + ", line " + lineNumber);

							// we build a message and send it to potential observers
							CompilerMessageFactory.instance().notifyObservers (CompilerMessageFactory.instance().createUnknownTypeInFeature (item));
							found = true;
						}

						if (found==false)
						{
							// we build a message and send it to potential observers
							CompilerMessageFactory.instance().notifyObservers (CompilerMessageFactory.instance().createUnknownLocalType(aType));
						}

						errors++;
					} 
				}
			else //extern library class::...
				if (aType.size()==2) {
					/*if (checkStandardLib(aType,firstName)) return true;
					else*/ if (checkTLLClass(aType,firstName,theLib)) return true;
						else 
						{ 
							log.error("Extern class type not found:"+firstName + "::" +aType.get(1));

							// we build a message and send it to potential observers
							CompilerMessageFactory.instance().notifyObservers (CompilerMessageFactory.instance().createUnknownExternType (aType));
							
							errors++;
						} 
				}
				else //type which is not a model element and has more than 2 components
					//Certainly a mistake !
				{
					aType.setIsModelType(true);
					aType.setDeclarationName("org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLModelElementInterface");
					return true;
					}
/*				{ 
					String  typeComponentMsg;
					log.error("Undeclared model or extern class type having more than 2 components !");;
					log.error(firstName+" may be the undeclared model");
					typeComponentMsg  = "The type components : ";
					for(int j=0;j<aType.size();j++)
						typeComponentMsg+="<"+aType.get(j)+"> ";
					log.error(typeComponentMsg);

					// we build a message and send it to potential observers
					CompilerMessageFactory.instance().notifyObservers (CompilerMessageFactory.instance().createUnknownExternType (aType));
					
					errors++;
				}*/
		return false;
	}

}
