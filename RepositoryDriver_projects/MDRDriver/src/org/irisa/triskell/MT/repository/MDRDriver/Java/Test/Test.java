/*
 * $Id: Test.java,v 1.9 2004-07-30 13:23:22 ffondeme Exp $
 * Authors : ffondeme
 * 
 * Copyright 2004 - INRIA - LGPL license
 */
package org.irisa.triskell.MT.repository.MDRDriver.Java.Test;

//import javax.jmi.model.Classifier;
//import javax.jmi.model.ClassifierClass;
//import javax.jmi.model.EnumerationType;
//import javax.jmi.model.MofClass;
import javax.jmi.reflect.*;
import java.util.*;
import java.io.*;
import java.lang.reflect.*;

import org.irisa.triskell.MT.DataTypes.Java.*;
import org.irisa.triskell.MT.DataTypes.Java.commands.ModelElement.ModelElementType;
import org.irisa.triskell.MT.DataTypes.Java.commands.OclAny.OclAnyType;
import org.irisa.triskell.MT.DataTypes.Java.defaultImpl.*;
import org.irisa.triskell.MT.repository.API.Java.*;
import org.irisa.triskell.MT.repository.MDRDriver.Java.*;
import org.irisa.triskell.MT.repository.genericJMIDriver.*;
import org.irisa.triskell.MT.utils.Java.*;
import org.netbeans.api.mdr.CreationFailedException;
import org.apache.log4j.Logger;
import org.apache.log4j.LogManager;
import org.apache.log4j.xml.DOMConfigurator;

/**
 * This class is a multithreaded test for the MDR driver. It requires the models<br>
 *  - <install_dir>/ThirdParty/MDR/Test/models/test.xml<br>
 *  - <install_dir>/ThirdParty/MDR/Test/models/UML13-JMI.xml<br>
 *  - <install_dir>/ThirdParty/MDR/Test/models/UML13.xml<br>
 *  - <install_dir>/ThirdParty/MDR/Test/models/TableMM.xml<br>
 *  - <install_dir>/ThirdParty/MDR/Test/models/Championship.xml<br>
 * 
 * It uses log4j, whose configuration should be in <install_dir>/log4j_configuration.xml
 * 
 * It is possibel to force <install_dir> by setting a non null value to the syatem property Directories.RootPath (see below)
 * 
 * "org.netbeans.mdr.byteCodeDir" is a system property indicating where to place the bytecode that MDR generates, according to the manipulated metamodels.
 * Tu use it, you may do System.setProperty or invoke the JVM with option -Dorg.netbeans.mdr.byteCodeDir=<Path to put the binaries>
 */
public class Test 
{
	public static String rootPath;
	
	protected static final org.apache.log4j.Logger log = Logger.getLogger("MDRDriverTest");
	public static org.apache.log4j.Logger getLog () {
		return Test.log;
	}
	public static int cardLog () {
		if ( Test.log == null ) return 0;
		else return 1;
	}
	
	protected static Vector exceptions;
	public static Vector getExceptions () {
		return exceptions;
	}
	
	public static class TestFailure extends Throwable {
		public final String method;
		public final Throwable failure;
		
		public TestFailure (String method, Throwable failure) {
			super("Failure in test " + method + ": " + failure.getMessage(), failure);
			this.method = method;
			this.failure = failure;
		}
	}
	
	public static final class TestRunnable implements Runnable {
		protected final TestThread [] waitFor;
		protected final Method method;
		
		public TestRunnable(String method) throws Exception {
			this(method, null);
		}
		
		public TestRunnable(String method, TestThread [] waitFor) throws Exception {
		this.method = Test.class.getMethod(method, new Class[0]);
		this.waitFor = waitFor;
		}

		public void run() {
			try {
				if (this.waitFor != null) {
					Thread current = Thread.currentThread();
					for (int i = 0; i < this.waitFor.length; ++i)
						if (! current.equals(this.waitFor[i]))
							this.waitFor[i].join();
				}
				Test.getLog().debug("Test " + this.method.getName() + " started.");
				this.method.invoke(null, new Object [0]);
				Test.getLog().debug("Test " + this.method.getName() + " passed.");
			} catch (InvocationTargetException x) {
				if (x.getCause() instanceof Exception)
					exceptions.addElement(new TestFailure(this.method.getName(), x.getCause())); 
				else
					exceptions.addElement(new TestFailure(this.method.getName(), x));
			} catch (Throwable x) {
				exceptions.addElement(new TestFailure(this.method.getName(), x));
			}
		}

	}
	
	public static class TestThread extends Thread {
		public TestThread (String method) throws Exception {
			super (new TestRunnable(method), method);
		}
		
		public TestThread (String method, TestThread [] waitFor) throws Exception {
			super (new TestRunnable(method, waitFor), method);
		}
	}
	
	public static void entry () throws Exception {
		if (rootPath == null)
			rootPath = Directories.getRootPath(Test.class.getName());
			
		final StringBuffer decompiler = new StringBuffer();
		
		try {			

			org.netbeans.mdr.handlers.BaseObjectHandler.setClassLoaderProvider(new org.netbeans.mdr.handlers.ClassLoaderProvider () {
				public ClassLoader getClassLoader() {
					return ClassLoader.getSystemClassLoader();
				}
				

				public Class defineClass(String className, byte[] classFile) {
					try {
						String byteCodeDir = System.getProperty("org.netbeans.mdr.byteCodeDir");
						if (byteCodeDir != null && byteCodeDir.length() != 0) {
							final java.io.File outFile = new java.io.File(byteCodeDir + java.io.File.separatorChar + AWK.replace(className, ".", "/") + ".class");
							if (! outFile.exists()) {
								Test.getLog().debug("Archiving bytecode for class " + className);
								outFile.getParentFile().mkdirs();
								outFile.createNewFile();
								BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(outFile));
								out.write(classFile);
								out.flush();
								out.close();
							}
							String javaDir = System.getProperty("org.netbeans.mdr.javaDir");
							if (javaDir != null && javaDir.length() > 0) {
								final java.io.File javaOutFile = new java.io.File(javaDir + java.io.File.separatorChar + AWK.replace(className, ".", "/") + ".java");
								if (! javaOutFile.exists()) {
									javaOutFile.getParentFile().mkdirs();
									decompiler.append("jad -o -sjava -d " + javaOutFile.getParent() + ' ' + outFile.getAbsolutePath() + '\n');
								}								
							}
						}
					} catch (Exception x) {
						x.printStackTrace();
					}
					return null;
				}
			});
					
			String filePath = new java.io.File(rootPath + "/log4j_configuration.xml").getCanonicalPath();
			LogManager.resetConfiguration();
			DOMConfigurator.configure(filePath);
			Test.getLog().info("Starting MDR driver test...");
			exceptions = new Vector();
			TestThread uml13Loading = new TestThread("testMOFModel");
			TestThread simpleDBLoading = new TestThread("testSimpleTableDBModel");
			TestThread [] testThreads = new TestThread [] {
				simpleDBLoading,
				new TestThread("testSimpleTableDBModel2", new TestThread [] {simpleDBLoading}),
				new TestThread("testSimpleTableDBModel3", new TestThread [] {simpleDBLoading}),
				new TestThread("testUnknownMetamodel", new TestThread [] {simpleDBLoading}),
				uml13Loading,
				new TestThread("testBadMetamodel", new TestThread [] {uml13Loading}),
				new TestThread("testUML13Model", new TestThread [] {uml13Loading}),
				new TestThread("testLoadStore", new TestThread [] {uml13Loading}),
				new TestThread("testErwan")
/**/			};
			for (int i = 0; i < testThreads.length; ++i)
				testThreads[i].start();
			for (int i = 0; i < testThreads.length; ++i)
				testThreads[i].join();
			if (System.getProperty(MDRAPI.getIGNORE_ASSOCIATION_ENDS_FOR_NAVIGATION_KEY(), "true").equalsIgnoreCase("true"))
				System.setProperty(MDRAPI.getIGNORE_ASSOCIATION_ENDS_FOR_NAVIGATION_KEY(), "false");
			else
				System.setProperty(MDRAPI.getIGNORE_ASSOCIATION_ENDS_FOR_NAVIGATION_KEY(), "true");
			simpleDBLoading = new TestThread("testSimpleTableDBModelBis");
			simpleDBLoading.start();
			simpleDBLoading.join();
			if (exceptions.isEmpty())
				Test.getLog().info("MDR driver test OK !");
			else {
				Iterator it = exceptions.iterator();
				TestFailure failure;
				while (it.hasNext()) {
					failure = (TestFailure)it.next();
					writeError(failure.getMessage(), failure.failure, 0);
				}
			}
			
			if (decompiler.length() > 0) {
				Test.getLog().debug("Decompilling files.");
				File decompilerBatch = File.createTempFile("MDRDecompilation", ".bat");
				decompilerBatch.deleteOnExit();
				BufferedOutputStream decompilerStream = new BufferedOutputStream(new FileOutputStream(decompilerBatch));
				decompilerStream.write(decompiler.toString().getBytes());
/*/				Runtime.getRuntime().exec(decompilerBatch.getAbsolutePath());//*/
				Test.getLog().info("Please, run " + decompilerBatch.getAbsolutePath() + " manually to decompile files...");//*/
			}
			if (!exceptions.isEmpty())
				throw new CompositeThrowable((Throwable[])exceptions.toArray(new Throwable[exceptions.size()]));
		} catch (CompositeThrowable x) {
			if (exceptions == null)
				exceptions = new Vector();
			exceptions.add(x);
			throw x;
		} catch (Exception x) {
			Test.getLog().error("MDR driver test bugged !!!", x);
			if (exceptions == null)
				exceptions = new Vector();
			exceptions.add(x);
			throw x;
		}
	}
	
	public static void main (String [] args) {
		try {
			entry();
			System.exit(0);
		} catch (Exception x) {
			x.printStackTrace();
			System.exit(-1);
		}
	}
	
	private static void writeError (String msg, Throwable t, int level) {
		Test.getLog().error(msg, t);
		org.openide.ErrorManager.Annotation [] annotations = org.netbeans.lib.jmi.util.Logger.getDefault().findAnnotations(t);
		if (annotations != null)
			for (int i = 0; i < annotations.length; ++i) {
				Throwable stackTrace = annotations[i].getStackTrace();
				if (stackTrace != null)
					writeError("Cause: " + stackTrace.getMessage(), stackTrace, level+1);
			}
	}
	
	public static void rien() {}
	
	public static void testLoadStore () throws Exception {
		MDRAPI api = new MDRAPI(null, new LoadedMetamodel("UML 1.3", new String [] {"UML"}), "unpriv", new XmiModel(rootPath + "/../ThirdParty/MDR/Test/models/test.xml", rootPath + "/../ThirdParty/MDR/Test/models/testResult.xml"));
		api.startup(null);
		ModelElementIterator attributes = api.getMetaClass(new String [] {"Foundation", "Core", "Attribute"}).allInstancesIterator(null);
		MetaAttribute vis = api.getMetaAttribute("visibility", null);
		Value priv = api.getMetaClass(new String [] {"Foundation", "Data_Types", "VisibilityKind"}).getMetaObject().getFeatureValue(null, api.getMetaFeature("vk_private", null), null);
		ModelElement att = null;
		while (attributes.hasNext()) {
			att = attributes.next();
//			att.setAttributeValue(null, vis, priv);
			att.invoke(null, vis.getName(), new Value [] {priv}, new String [] {ModelElement.SetAttributeDiscriminant});
			//Accessor response from driver is abandonned
//			att.invoke(null, "set_visibility", new Value [] {priv}, new String [] {ModelElement.OperationDiscriminant});
			if (! priv.equals(att.invoke(null, "visibility", null, null)))
				throw new RuntimeException("Invalid attribute value (through invoke)");
			if (! priv.equals(att.invoke(null, "visibility", null, new String [] {ModelElement.AttributeDiscriminant})))
				throw new RuntimeException("Invalid attribute value (through invoke 2");
			if (! priv.equals(att.invoke(null, "visibility", null, new String [] {ModelElement.OperationDiscriminant, ModelElement.AttributeDiscriminant})))
				throw new RuntimeException("Invalid attribute value (through invoke 3");
			//Accessor response from driver is abandonned
//			if (! priv.equals(att.invoke(null, "get_visibility", null, new String [] {ModelElement.OperationDiscriminant, ModelElement.AttributeDiscriminant})))
//				throw new RuntimeException("Invalid attribute value (through invoke 4; accessor get");
			try {
				att.invoke(null, "visibility", null, new String [] {ModelElement.AssociationDiscriminant, ModelElement.OperationDiscriminant});
				throw new RuntimeException("invoke should fail: attribute accessed while discriminant are not allowing this");
			} catch (RuntimeException x) {
				throw x;
			} catch (Exception x) {
			}
		}
		if (! att.invoke(null, "oclIsUndefined", null, null).equals(BooleanValueImpl.FALSE))
			throw new RuntimeException("Cannt invoke predefined operation isUndefined on a ModelElement");
		if (! att.invoke(new String [] {"OclAny"}, "oclIsUndefined", null, null).equals(BooleanValueImpl.FALSE))
			throw new RuntimeException("Cannt invoke predefined operation isUndefined on a ModelElement");
		try {
			att.invoke(null, "xfgnikbht", null, new String [] {ModelElement.AssociationDiscriminant, ModelElement.OperationDiscriminant});
			throw new RuntimeException("invoke should fail: this operation does not exists");
		} catch (RuntimeException x) {
			throw x;
		} catch (Exception x) {
		}
		
		attributes.reset();
		boolean exploresAE = !System.getProperty(MDRAPI.getIGNORE_ASSOCIATION_ENDS_FOR_NAVIGATION_KEY(), "true").equalsIgnoreCase("true");
		ModelElement anAttribute = exploresAE && attributes.hasNext() ? attributes.next() : null;
		final Value anAttributeName = anAttribute == null ? null : anAttribute.getFeatureValue(null, api.getMetaFeature("name", null), null);
		final Value anAttributeTypeName = anAttribute == null ? null : ((ModelElement)anAttribute.getFeatureValue(null, api.getMetaAssociationEnd("type", api.getMetaClass(new String [] {"Foundation", "Core", "GeneralizableElement"}), null), null)).getFeatureValue(null, api.getMetaAttribute("name", null), null);
		
		api.shutdown(null);
		
		api = new MDRAPI(null, new LoadedMetamodel("UML 1.3", new String [] {"UML"}), "priv", new XmiModel(rootPath + "/../ThirdParty/MDR/Test/models/testResult.xml", XmiModel.Read));
		api.startup(null);
		attributes = api.getMetaClass(new String [] {"Foundation", "Core", "Attribute"}).allInstancesIterator(null);
		//To be reloaded because the api has changed...
		vis = api.getMetaAttribute("visibility", null);
		priv = api.getMetaClass(new String [] {"Foundation", "Data_Types", "VisibilityKind"}).getMetaObject().getFeatureValue(null, api.getMetaFeature("vk_private", null), null);
		while (attributes.hasNext())
			if (!attributes.next().getFeatureValue(null, vis, null).equals(priv))
				throw new Exception("Bad visibility.");
				
		if (anAttribute != null && !anAttributeName.isUndefined() && !anAttributeTypeName.isUndefined()) {
			attributes.reset();
			final MetaFeature nameFeature = api.getMetaAttribute("name", null), typeFeature = api.getMetaAssociationEnd("type", null, null);
			attributes = new JMIConstrainedModelElementIterator(new JMIConstrainedModelElementIterator(attributes, new LookupConstraint() {
				public boolean match(org.irisa.triskell.MT.repository.API.Java.ModelElement element) {
					try {
						return ((ModelElement)element.getFeatureValue(null, typeFeature, null)).getFeatureValue(null, nameFeature, null).equals(anAttributeTypeName);
					} catch (Exception x) {
						return false;
					}
				}
			}), new FeatureValueConstraint(api, "name", anAttributeName));
			if (attributes.size() != 1)
				throw new Exception("Cannot find an attribute according to its name and the name of its type.");
		}
	
		api.shutdown(null);
	}
	
	public static void testMOFModel () throws Exception {
		MDRAPI api = new MDRAPI(null, MofMetamodel.getTheInstance(), "UML 1.3", new XmiModel(rootPath + "/../ThirdParty/MDR/Test/models/UML13-JMI.xml", XmiModel.Read));
		api.startup(null);
		if (api.getMdrRepository().getExtent("UML 1.3") == null)
			throw new Exception("Error: MDR extent UML 1.3 not found...");
		if (api.getMdrRepository().getExtent("UML 1.3") != api.getModel())
			throw new Exception("Error: MDR extent UML 1.3 is not the API model...");		

		//Checks the existence of a namespace Foundation containing the package Core conatiniong the class Classifier
		//OCL equivalent is let umlCorePackage = Namespace.allInstances->select(qualifiedName = 'Foundation').contents->select(i | name = 'Core' and i.oclIsKindOf(Package)) in umlCorePackage.contents->exists(i | i.name = 'Classifier' and i.oclIsKindOf('Class')) && not umlCorePackage.content->exists(name = 'Truc') 
		RefClass mofNamespace = api.getModel().refClass("Namespace"); 
		RefClass mofPackage = api.getModel().refClass("Package"); 
		RefClass mofClass = api.getModel().refClass("Class");
		RefObject o;
		Iterator it = mofNamespace.refAllOfType().iterator();
		RefObject umlFoundationNamespace = null;
		while (it.hasNext() && (umlFoundationNamespace == null)){
			o = (RefObject)it.next();
			if (o.refGetValue("qualifiedName").equals(Arrays.asList(new String [] {"Foundation"})))
				umlFoundationNamespace = o;
		}
		if (umlFoundationNamespace == null)
			throw new Exception("Cannot find the Foundation namespace in model UML 1.3");
		
		it = ((Collection)umlFoundationNamespace.refGetValue("contents")).iterator();
		RefObject umlCorePackage = null;
		while (it.hasNext() && (umlCorePackage == null)) {
			o = (RefObject)it.next();
			if (o.refGetValue("name").equals("Core") && o.refIsInstanceOf((RefObject)mofPackage.refMetaObject(), true))
				umlCorePackage = o;
		}
		if (umlCorePackage == null)
			throw new Exception("Cannot find the Core package in the Foundation namespace in model UML 1.3");
		
		it = ((Collection)umlCorePackage.refGetValue("contents")).iterator();
		RefObject umlClassifierClass = null;
		while (it.hasNext() && (umlClassifierClass == null)) {
			o = (RefObject)it.next();
			if (o.refGetValue("name").equals("Classifier") && o.refIsInstanceOf((RefObject)mofClass.refMetaObject(), true))
				umlClassifierClass = o;
		}
		if (umlClassifierClass == null)
			throw new Exception("Cannot find the Classifier class in the Core package in the Foundation namespace in model UML 1.3");

		it = ((Collection)umlCorePackage.refGetValue("contents")).iterator();
		RefObject umlTrucClass = null;
		while (it.hasNext() && (umlTrucClass == null)) {
			o = (RefObject)it.next();
			if (o.refGetValue("name").equals("Truc") && o.refIsInstanceOf((RefObject)mofClass.refMetaObject(), true))
			umlTrucClass = o;
		}
		if (umlTrucClass != null)
			throw new Exception("Found the Truc class in the Core package in the Foundation namespace in model UML 1.3");

		Object q = api.getModel();
		q = ((RefPackage)q).refMetaObject();
		q = ((RefBaseObject)q).refOutermostPackage();
		q = ((RefPackage)q).refClass("PrimitiveType");
		it = ((RefClass)q).refAllOfClass().iterator();
		while (it.hasNext()) {
			q = it.next();
			if (((RefFeatured)q).refGetValue("name").equals("String"))
				break;
		}
		Object p = api.getPrimitiveType("String");
		if (! p.equals(q))
			throw new Exception ("Problem with primitive type referencing...");
		
		p = api.getModel();
		p = ((RefPackage)p).refMetaObject();
		p = ((RefBaseObject)p).refOutermostPackage();
		p = ((RefPackage)p).refClass("Parameter");
		it = ((RefClass)p).refAllOfClass().iterator();
		while (it.hasNext()) {
			p = it.next();
			if (((RefFeatured)p).refGetValue("name").equals("name"))
				break;
		}
		p = ((RefObject)p).refGetValue("type");
		if (! p.equals(q))
			throw new Exception ("Problem with primitive type referencing...");

		
		ModelElementIterator umlclazziterator = api.getMetaClass(new String [] {"Model", "Class"}).allInstancesIterator(new FeatureValueConstraint(api, "name", new StringValueImpl(false, null, "Class"))); 
		ModelElement umlclazz = umlclazziterator.next();
		
		if (umlclazziterator.hasNext())
			throw new Exception("Found multiple Class in UML 13 !");
		try {
			umlclazziterator.next();
			throw new Exception("An element appeared in the iterator while it told there was no more !");
		} catch (NoMoreElementException x) {
		}
		
		umlclazziterator.reset();
		if (!umlclazziterator.hasNext())
			throw new Exception ("The iterator is not properly reseted");
		
		if (umlclazziterator.next() != umlclazz)
			throw new Exception ("Sporadic iteration !");
		
		if (umlclazziterator.hasNext())
			throw new Exception("Found multiple Class in UML 13 ONCE RESETED !");
		try {
			umlclazziterator.next();
			throw new Exception("An element appeared in the iterator while it told there was no more ONCE RESETED !");
		} catch (NoMoreElementException x) {
		}
		
		final CollectionValue umlclazzcontents = (CollectionValue)umlclazz.getFeatureValue(null, api.getMetaFeature("contents", null), null);
		
		if (!umlclazzcontents.equals(umlclazz.invoke(new String [] {"Model", "Namespace"}, "contents", null, null)))
			throw new RuntimeException("Cannot retreive the same class contents with classic and invoke method.");
		if (!umlclazzcontents.equals(umlclazz.invoke(new String [] {"Namespace"}, "contents", null, new String [] {ModelElement.AssociationDiscriminant})))
			throw new RuntimeException("Cannot retreive the same class contents with classic and invoke method. (with discriminants)");
		if (!umlclazzcontents.equals(umlclazz.invoke(new String [] {"Namespace"}, "contents", null, new String [] {ModelElement.AssociationDiscriminant, ModelElement.AttributeDiscriminant})))
			throw new RuntimeException("Cannot retreive the same class contents with classic and invoke method. (with 2 discriminants)");
		try {
			umlclazz.invoke(new String [] {"ModelElement"}, "contents", null, null);
			throw new RuntimeException("Found impossible method (exists but oclAsType excluding it).");
		} catch (RuntimeException x) {
			throw x;
		} catch (Exception x) {
		}
		
		
		ModelElementIterator umlclazzcontentsiterator = new JMIConstrainedModelElementIterator(new ModelElementIterator () {
			int index = 0;
			
			public int size() {
				return umlclazzcontents.getTheCollection().length;
			}

			public boolean hasNext() {
				return index < this.size();
			}

			public org.irisa.triskell.MT.repository.API.Java.ModelElement next()
			   throws NoMoreElementException {
				if (! this.hasNext())
					throw new NoMoreElementException();
				return (JMIModelElement)umlclazzcontents.getTheCollection()[index++];
			}

			public void reset() {
				this.index = 0;
			}
		}, new FeatureValueConstraint(api, "name", new StringValueImpl(false, null, "isActive")));
		
		if (!umlclazzcontentsiterator.hasNext())
			throw new Exception ("Cannot find field isActive in the contents of the uml class");
		if (! umlclazzcontentsiterator.next().getFeatureValue(null, api.getMetaAttribute("name", null), null).equals(new StringValueImpl(false, null, "isActive")))
			throw new Exception ("Found isActive but has no name isActive !!!");

		ModelElement umlpackage = api.getMetaClass(new String [] {"Model", "Class"}).allInstancesIterator(new FeatureValueConstraint(api, "name", new StringValueImpl(false, null, "Package"))).next(); 
		TupleValue objectDependent = (TupleValue)umlclazz.getFeatureValue(null, api.getMetaOperation("isRequiredBecause", null), new Value [] {umlpackage});
		if (objectDependent.isUndefined())
			throw new Exception("isRequiredBecause failed: " + objectDependent.getErrorMessage());
		if (objectDependent.getPartNames().length != 2)
			throw new Exception("isRequiredBecause failed: invalit result " + objectDependent.toString());
		if (objectDependent.getPartNames()[0].equals("result")) {
			if (! objectDependent.getPartNames()[1].equals("reason"))
				throw new Exception("isRequiredBecause failed: invalit result " + objectDependent.toString()); 
		} else {
			if (! objectDependent.getPartNames()[0].equals("reason") || ! objectDependent.getPartNames()[1].equals("result"))
				throw new Exception("isRequiredBecause failed: invalit result " + objectDependent.toString());
		}
		if (! new BooleanValueImpl(false, null, true).equals(objectDependent.getPart("result")))
			throw new Exception ("Bad operation isRequiredBecause result.");
		if (! new StringValueImpl(false, null, "indirect").equals(objectDependent.getPart("reason")))
			throw new Exception("Bad operation isRequiredBecause cause.");
		if (! new TupleValueImpl(false, null, new TupleElementImpl [] {new TupleElementImpl("result", new BooleanValueImpl(false, null, true)), new TupleElementImpl("reason", new StringValueImpl(false, null, "indirect"))}).equals(objectDependent))
			throw new Exception("Problem with tuple comparison.");
		if (! new TupleValueImpl(false, null, new TupleElementImpl [] {new TupleElementImpl("reason", new StringValueImpl(false, null, "indirect")), new TupleElementImpl("result", new BooleanValueImpl(false, null, true))}).equals(objectDependent))
			throw new Exception("Problem with tuple comparison (relative to element order).");
		if (! objectDependent.getPart("toto").isUndefined())
			throw new Exception("Found unknown tuple part toto !");
			
		ModelElement ownedElement = api.getMetaClass(new String [] {"Model", "AssociationEnd"}).allInstancesIterator(new FeatureValueConstraint(api, "name", new StringValueImpl(false, null, "ownedElement"))).next();
		Value v = ownedElement.getFeatureValue(null, api.getMetaFeature("multiplicity", null), null);
		if (! new TupleValueImpl(false, null, new TupleElementImpl[] {new TupleElementImpl("lower", new IntegerValueImpl(false, null, 0)), new TupleElementImpl("upper", new IntegerValueImpl(false, null, -1)), new TupleElementImpl("isOrdered", new BooleanValueImpl(false, null, false)), new TupleElementImpl("isUnique", new BooleanValueImpl(false, null, true))}).equals(v))
			throw new Exception("Bad multiplicity for AssociationEnd.");
	}
	
	public static void testSimpleTableDBModel () throws Exception {
		testSimpleTableDBModel("championship");
	}
	
	public static void testSimpleTableDBModelBis () throws Exception {
		testSimpleTableDBModel("re-championship");
	}
	
	public static void testSimpleTableDBModel (String repName) throws Exception {
		MDRAPI api = new MDRAPI(null, new XmiMetamodel(rootPath + "/../ThirdParty/MDR/Test/models/TableMM.xml"), repName, new XmiModel(rootPath + "/../ThirdParty/MDR/Test/models/Championship.xml", XmiModel.Write));
		api.startup(null);

		MetaClass table = api.getMetaClass(new String [] {"SimpleDB", "Table"});
		MetaClass column = api.getMetaClass(new String [] {"SimpleDB", "Column"});
		
		ModelElement championship = table.instanciate(null, new Value [] {new StringValueImpl(false, null, "Championship")});
		ModelElement date = column.instanciate(null, new Value [] {new StringValueImpl(false, null, "Date")});
		ModelElement team1 = column.instanciate(null, new Value [] {new StringValueImpl(false, null, "Team 1")});
		ModelElement score1 = column.instanciate(null, new Value [] {new StringValueImpl(false, null, "Score 1")});
		ModelElement team2 = column.instanciate(null, new Value [] {new StringValueImpl(false, null, "Team 2")});
		ModelElement score2 = column.instanciate(null, new Value [] {new StringValueImpl(false, null, "Score 2")});
		
		MetaAssociation ass1 = api.getMetaAssociation(new String [] {"SimpleDB", "A_column_table"});
		MetaAssociationEnd ae1_1 = api.getMetaAssociationEnd(null, table, null);
		MetaAssociationEnd ae1_2 = api.getMetaAssociationEnd("table", null, null);
		MetaAssociationEnd ae1_3 = api.getMetaAssociationEnd("table", table, null);
		MetaAssociationEnd ae2_1 = api.getMetaAssociationEnd(null, column, null);
		MetaAssociationEnd ae2_2 = api.getMetaAssociationEnd("column", null, null);
		MetaAssociationEnd ae2_3 = api.getMetaAssociationEnd("column", column, null);
		MetaAssociationEnd ae_null = api.getMetaAssociationEnd(null, null, null);
		
		MetaAssociation ass2;
		
		MetaAssociationEnd [] ends1 = new MetaAssociationEnd [] {ae1_1, ae1_2, ae1_3};
		MetaAssociationEnd [] ends2 = new MetaAssociationEnd [] {ae2_1, ae2_2, ae2_3};
		
		for (int i = 0, j; i < ends1.length; ++i) {
			ass2 = api.getMetaAssociationWithAssociationEnds(new MetaAssociationEnd[] {ends1[i], ae_null});
			if (!((JMIMetaAssociation)ass1).getRefMetaObject().equals(((JMIMetaAssociation)ass2).getRefMetaObject()))
				throw new Exception("Different proxies for the same association !");
				
			ass2 = api.getMetaAssociationWithAssociationEnds(new MetaAssociationEnd[] {ae_null, ends1[i]});
			if (!((JMIMetaAssociation)ass1).getRefMetaObject().equals(((JMIMetaAssociation)ass2).getRefMetaObject()))
				throw new Exception("Different proxies for the same association !");
				
			for (j = 0; j < ends2.length; ++j) {
				ass2 = api.getMetaAssociationWithAssociationEnds(new MetaAssociationEnd[] {ends1[i], ends2[j]});
				if (!((JMIMetaAssociation)ass1).getRefMetaObject().equals(((JMIMetaAssociation)ass2).getRefMetaObject()))
					throw new Exception("Different proxies for the same association !");
					
				ass2 = api.getMetaAssociationWithAssociationEnds(new MetaAssociationEnd[] {ends2[j], ends1[i]});
				if (!((JMIMetaAssociation)ass1).getRefMetaObject().equals(((JMIMetaAssociation)ass2).getRefMetaObject()))
					throw new Exception("Different proxies for the same association !");
			}
		}
		
		for (int j = 0; j < ends2.length; ++j) { 
			ass2 = api.getMetaAssociationWithAssociationEnds(new MetaAssociationEnd[] {ends2[j], ae_null});
			if (!((JMIMetaAssociation)ass1).getRefMetaObject().equals(((JMIMetaAssociation)ass2).getRefMetaObject()))
				throw new Exception("Different proxies for the same association !");
				
			ass2 = api.getMetaAssociationWithAssociationEnds(new MetaAssociationEnd[] {ae_null, ends2[j]});
			if (!((JMIMetaAssociation)ass1).getRefMetaObject().equals(((JMIMetaAssociation)ass2).getRefMetaObject()))
				throw new Exception("Different proxies for the same association !");
		}
		
		try {
			ass2 = api.getMetaAssociationWithAssociationEnds(new MetaAssociationEnd[] {ae1_1, ae1_1});
			throw new Exception("Found impossible association ! " + ass2.toString());
		} catch (UnknownElementException x) {
		}
		
		ass1.associateModelElements(null, new ModelRole [] {api.getRole(date, ae2_3), api.getRole(championship, ae1_2)});
		ass1.associateModelElements(null, new ModelRole [] {api.getRole(championship, ae1_2), api.getRole(team1, ae2_3)});
		ass1.associateModelElements(null, new ModelRole [] {api.getRole(score1, ae2_2), api.getRole(championship, ae1_1)});
		ass1.associateModelElements(null, new ModelRole [] {api.getRole(team2, ae2_1), api.getRole(championship, ae1_1)});
		ass1.associateModelElements(null, new ModelRole [] {api.getRole(score2, ae2_3), api.getRole(championship, ae1_2)});
		
		//A seccond call should not lead to an error
		ass1.associateModelElements(null, new ModelRole [] {api.getRole(score2, ae2_3), api.getRole(championship, ae1_2)});
		
		
		boolean exploresAE = !System.getProperty(MDRAPI.getIGNORE_ASSOCIATION_ENDS_FOR_NAVIGATION_KEY(), "true").equalsIgnoreCase("true");
		//Now, the feature is available:
		MetaFeature tableAE = api.getMetaAssociationEnd("table", null, null);
		if (exploresAE && ! score2.getFeatureValue(null, tableAE, null).equals(championship))
			throw new Exception("Cannot re-read created link");
		
		ass1.dissociateModelElements(null, new ModelRole [] {api.getRole(score2, ae2_3), api.getRole(championship, ae1_2)});
		if (exploresAE && ! (score2.getFeatureValue(null, tableAE, null) instanceof NullValue))
			throw new Exception("Link deletion not active");
			
		//Should not lead to an error...
		ass1.dissociateModelElements(null, new ModelRole [] {api.getRole(score2, ae2_3), api.getRole(championship, ae1_2)});
			
		ass1.associateModelElements(null, new ModelRole [] {api.getRole(score2, ae2_3), api.getRole(championship, ae1_2)});
			
		api.shutdown(null);
		
		api = new MDRAPI(null, new XmiMetamodel(rootPath + "/../ThirdParty/MDR/Test/models/TableMM.xml"), repName+"Res", new XmiModel(rootPath + "/../ThirdParty/MDR/Test/models/Championship.xml", XmiModel.Read));
		api.startup(null);
		
		
		table = api.getMetaClass(new String [] {"SimpleDB", "Table"});
		ModelElementIterator it = table.allInstancesIterator(null);
		if (it.size() != 1)
			throw new Exception ("Invalid number of tables !");
		
		MetaAttribute name = api.getMetaAttribute("name", null);
		
		championship = table.allInstancesIterator(null).next();
		if (! championship.getFeatureValue(null, name, null).equals(new StringValueImpl(false, null, "Championship")))
			throw new Exception ("The instanciated table has not the righht name.");
		
		column = api.getMetaClass(new String [] {"SimpleDB", "Column"});
		it = column.allInstancesIterator(null);
		if (it.size() != 5)
			throw new Exception ("Invalid number of columns !");
			
		date = null; team1 = null; score1 = null; team2 = null; score2 = null;
		while (it.hasNext()) {
			ModelElement o = it.next();
			String nm = ((StringValue)o.getFeatureValue(null, name, null)).getTheString();
			if (nm.equals("Date"))
				date = o;
			else if (nm.equals("Team 1"))
				team1 = o;
			else if (nm.equals("Score 1"))
				score1=o;
			else if (nm.equals("Team 2"))
				team2=o;
			else if (nm.equals("Score 2"))
				score2=o;
			else
				throw new Exception("Invalid column found.");
		}
		if (date == null || team1 == null || score1 == null || team2 == null || score2 == null)
			throw new Exception ("Cannot retreive all columns.");
				
		RefAssociation ass = api.getModel().refAssociation("A_column_table");
		int assSize = ass.refAllLinks().size();
		if (assSize != 5)
			throw new Exception("Bad number of links");
		
		it.reset();
		while (it.hasNext()) {
			if (! ass.refLinkExists(((RefObject)((JMIModelElement)championship).getRef()), ((RefObject)((JMIModelElement)it.next()).getRef())))
				throw new Exception ("Cannot retreive all links.");
		}
		
		if (exploresAE) {
			if (! new SetValueImpl(false, null, new Value [] {date, team1, team2, score1, score2, date}, true).equals(championship.getFeatureValue(null, api.getMetaFeature("column", null), null)))
				throw new Exception("AssociationEnd navigation fails");
				
			if (! championship.equals(date.getFeatureValue(null, api.getMetaAssociationEnd(null, table, null), null)))
				throw new Exception("AssociationEnd navigation fails");
				
			if (! championship.equals(team1.getFeatureValue(null, api.getMetaAssociationEnd("table", null, null), null)))
				throw new Exception("AssociationEnd navigation fails");
				
			if (! championship.equals(score1.getFeatureValue(null, api.getMetaAssociationEnd(null, table, null), null)))
				throw new Exception("AssociationEnd navigation fails");
				
			if (! championship.equals(team2.getFeatureValue(null, api.getMetaAssociationEnd(null, table, null), null)))
				throw new Exception("AssociationEnd navigation fails");
				
			if (! championship.equals(score2.getFeatureValue(null, api.getMetaAssociationEnd(null, table, null), null)))
				throw new Exception("AssociationEnd navigation fails");
		}
		
		if (! championship.invoke(null, "oclIsTypeOf", new Value [] {new TypeValueImpl(false, null, table)}, null).equals(BooleanValueImpl.TRUE))
			throw new RuntimeException("the table Championship seems to not be of meta-type Table");
		if (! championship.invoke(null, "oclIsKindOf", new Value [] {new TypeValueImpl(false, null, table)}, null).equals(BooleanValueImpl.TRUE))
			throw new RuntimeException("the table Championship seems to not be of meta-kind Table");
		if (championship.invoke(null, "oclIsTypeOf", new Value [] {new TypeValueImpl(false, null, column)}, null).equals(BooleanValueImpl.TRUE))
			throw new RuntimeException("the table Championship seems to be of meta-type Column");
		if (championship.invoke(null, "oclIsKindOf", new Value [] {new TypeValueImpl(false, null, column)}, null).equals(BooleanValueImpl.TRUE))
			throw new RuntimeException("the table Championship seems to be of meta-kind Column");
		if (championship.invoke(null, "oclIsTypeOf", new Value [] {new TypeValueImpl(false, null, ModelElementType.TheInstance)}, null).equals(BooleanValueImpl.TRUE))
			throw new RuntimeException("the table Championship seems to be of abstract meta-kind ModelElement");
		if (! championship.invoke(null, "oclIsKindOf", new Value [] {new TypeValueImpl(false, null, ModelElementType.TheInstance)}, null).equals(BooleanValueImpl.TRUE))
			throw new RuntimeException("the table Championship seems not to be of meta-type ModelElement");
		if (! championship.invoke(null, "oclIsKindOf", new Value [] {new TypeValueImpl(false, null, OclAnyType.TheInstance)}, null).equals(BooleanValueImpl.TRUE))
			throw new RuntimeException("the table Championship seems not to be of meta-kind OclAny");
			
		
		if (! championship.invoke(null, "oclIsTypeOf", new Value [] {table.getMetaObject()}, null).equals(BooleanValueImpl.TRUE))
			throw new RuntimeException("the table Championship seems to not be of meta-type Table");
		if (! championship.invoke(null, "oclIsKindOf", new Value [] {table.getMetaObject()}, null).equals(BooleanValueImpl.TRUE))
			throw new RuntimeException("the table Championship seems to not be of meta-kind Table");
		if (championship.invoke(null, "oclIsTypeOf", new Value [] {column.getMetaObject()}, null).equals(BooleanValueImpl.TRUE))
			throw new RuntimeException("the table Championship seems to be of meta-type Column");
		if (championship.invoke(null, "oclIsKindOf", new Value [] {column.getMetaObject()}, null).equals(BooleanValueImpl.TRUE))
			throw new RuntimeException("the table Championship seems to be of meta-kind Column");
		
		if (!column.allInstances().equals(column.getMetaObject().invoke(null, "allInstances", null, null)))
			throw new RuntimeException("Problem with allInstances operation over meta type.");
		if (! column.getMetaObject().invoke(null, "oclName", null, null).equals(new StringValueImpl(false, null, "Column")))
			throw new RuntimeException("Problem with name operation over meta type.");
		if (! column.getMetaObject().invoke(null, "oclQualifiedName", null, null).equals(new SequenceValueImpl(false, null, new Value [] {new StringValueImpl(false, null, "SimpleDB"), new StringValueImpl(false, null, "Column")})))
			throw new RuntimeException("Problem with qualifiedName operation over meta type.");
			
			
		
		api.shutdown(null);
	}
	
	public static void testSimpleTableDBModel2 () throws Exception {
		try {
			MDRAPI api = new MDRAPI(null, new XmiMetamodel(rootPath + "/../ThirdParty/MDR/Test/models/TableMM.xml"), "championship", new XmiModel(rootPath + "/../ThirdParty/MDR/Test/models/Championship.xml", XmiModel.Read));
			api.startup(null);
			throw new Exception("Test testSimpleTableDBModel2 passed while should not...");
		} catch (CreationFailedException x) {
		}
	}
	
	public static void testSimpleTableDBModel3 () throws Exception {
		MDRAPI api = new MDRAPI(null, new XmiMetamodel(rootPath + "/../ThirdParty/MDR/Test/models/TableMM.xml"), "championship2", new XmiModel(rootPath + "/../ThirdParty/MDR/Test/models/Championship.xml", XmiModel.Read));
		api.startup(null);
	}
	
	public static void testUnknownMetamodel () throws Exception {
		try {
			MDRAPI api = new MDRAPI(null, new XmiMetamodel(rootPath + "/../ThirdParty/MDR/Test/models/truc.xml"), "truc", new XmiModel(new String [0], null));
			api.startup(null);
			throw new Exception("Test testUnknownMetamodel passed while should not...");
		} catch (java.io.FileNotFoundException x) {
		}
	}
	
	public static void testBadMetamodel () throws Exception {
		final PrintStream err = System.err;
		try {
			System.setErr(new PrintStream(new OutputStream () {public void write(int b) throws IOException{}}));
			MDRAPI api = new MDRAPI(null, new LoadedMetamodel("UML 1.3", new String [] {"UML"}), "badMM", new XmiModel(rootPath + "/../ThirdParty/MDR/Test/models/Championship.xml", XmiModel.Read));
			api.startup(null);
			throw new Exception("Test testBadMetamodel passed while should not...");
		} catch (javax.jmi.xmi.MalformedXMIException x) {
		} finally {
			System.setErr(err);
		}
	}
	
	public static void testErwan () throws Exception {
        MDRAPI api = new MDRAPI(null, new XmiMetamodel(rootPath + "/../ThirdParty/MDR/Test/models/MOFmetamodel.xml",new String [] {"SimpleUmlMM"}), "MyModel", new XmiModel(rootPath + "/../ThirdParty/MDR/Test/models/instance.xmi", XmiModel.Write));
        
        api.startup(null); 


        MetaClass mc_PrimitiveDataType = api.getMetaClass(new String [] {"SimpleUmlMM","PrimitiveDataType"});
        MetaClass mc_Class = api.getMetaClass(new String [] {"SimpleUmlMM","Class"});
        MetaClass mc_Attribute = api.getMetaClass(new String [] {"SimpleUmlMM","Attribute"});
        MetaClass mc_Association = api.getMetaClass(new String [] {"SimpleUmlMM","Association"});
        MetaClass mc_ModelElement = api.getMetaClass(new String [] {"SimpleUmlMM","ModelElement"});
        MetaClass mc_Classifier = api.getMetaClass(new String [] {"SimpleUmlMM","Classifier"});


        MetaAssociationEnd mae_attribute_Attribute = api.getMetaAssociationEnd("attribute",mc_Attribute,mc_Class);
        MetaAssociationEnd mae_forward_Association = api.getMetaAssociationEnd("forward",mc_Association,mc_Class);
        MetaAssociationEnd mae_reverse_Association = api.getMetaAssociationEnd("reverse",mc_Association,mc_Class);
        MetaAssociationEnd mae_type_Classifier = api.getMetaAssociationEnd("type",mc_Classifier,mc_Attribute);
        MetaAssociationEnd mae_owner_Class = api.getMetaAssociationEnd("owner",mc_Class,mc_Attribute);
        MetaAssociationEnd mae_source_Class = api.getMetaAssociationEnd("source",mc_Class,mc_Association);
        MetaAssociationEnd mae_destination_Class = api.getMetaAssociationEnd("destination",mc_Class,mc_Association);
        MetaAttribute ma_name = api.getMetaAttribute("name",null);
        MetaAttribute ma_kind = api.getMetaAttribute("kind",null);
        MetaAssociationEnd mae_typed_Attribute = api.getMetaAssociationEnd("typed",mc_Attribute,mc_Classifier);


        ModelElement me_compositeur = mc_Class.instanciate (null,null);
        ModelElement me_composition = mc_Association.instanciate (null,null);
        ModelElement me_interprete = mc_Class.instanciate (null,null);
        ModelElement me_interpretation = mc_Association.instanciate (null,null);
        ModelElement me_oeuvre = mc_Class.instanciate (null,null);
        ModelElement me_annees_etudes = mc_Attribute.instanciate (null,null);
        ModelElement me_integer = mc_PrimitiveDataType.instanciate (null,null);
        ModelElement me_date = mc_Class.instanciate (null,null);
        ModelElement me_jour = mc_Attribute.instanciate (null,null);
        ModelElement me_mois = mc_Attribute.instanciate (null,null);
        ModelElement me_annee = mc_Attribute.instanciate (null,null);
        ModelElement me_date_creation = mc_Attribute.instanciate (null,null);
        ModelElement me_date_naissance = mc_Attribute.instanciate (null,null);


        linkTwoModelElements (api, me_compositeur,mae_source_Class,me_composition,mae_forward_Association);
        linkTwoModelElements (api, me_compositeur,mae_owner_Class,me_date_naissance,mae_attribute_Attribute);
        linkTwoModelElements (api, me_composition,mae_forward_Association,me_compositeur,mae_source_Class);
        linkTwoModelElements (api, me_composition,mae_reverse_Association,me_oeuvre,mae_destination_Class);
        linkTwoModelElements (api, me_interprete,mae_source_Class,me_interpretation,mae_forward_Association);
        linkTwoModelElements (api, me_interprete,mae_owner_Class,me_annees_etudes,mae_attribute_Attribute);
        linkTwoModelElements (api, me_interpretation,mae_forward_Association,me_interprete,mae_source_Class);
        linkTwoModelElements (api, me_interpretation,mae_reverse_Association,me_oeuvre,mae_destination_Class);
        linkTwoModelElements (api, me_oeuvre,mae_destination_Class,me_composition,mae_reverse_Association);
        linkTwoModelElements (api, me_oeuvre,mae_destination_Class,me_interpretation,mae_reverse_Association);
        linkTwoModelElements (api, me_oeuvre,mae_owner_Class,me_date_creation,mae_attribute_Attribute);
        linkTwoModelElements (api, me_annees_etudes,mae_attribute_Attribute,me_interprete,mae_owner_Class);
        linkTwoModelElements (api, me_annees_etudes,mae_typed_Attribute,me_integer,mae_type_Classifier);
        linkTwoModelElements (api, me_integer,mae_type_Classifier,me_annees_etudes,mae_typed_Attribute);
        linkTwoModelElements (api, me_integer,mae_type_Classifier,me_jour,mae_typed_Attribute);
        linkTwoModelElements (api, me_integer,mae_type_Classifier,me_mois,mae_typed_Attribute);
        linkTwoModelElements (api, me_integer,mae_type_Classifier,me_annee,mae_typed_Attribute);
        linkTwoModelElements (api, me_date,mae_owner_Class,me_mois,mae_attribute_Attribute);
        linkTwoModelElements (api, me_date,mae_owner_Class,me_annee,mae_attribute_Attribute);
        linkTwoModelElements (api, me_date,mae_type_Classifier,me_date_creation,mae_typed_Attribute);
        linkTwoModelElements (api, me_date,mae_type_Classifier,me_date_naissance,mae_typed_Attribute);
        linkTwoModelElements (api, me_date,mae_owner_Class,me_jour,mae_attribute_Attribute);
        linkTwoModelElements (api, me_jour,mae_typed_Attribute,me_integer,mae_type_Classifier);
        linkTwoModelElements (api, me_jour,mae_attribute_Attribute,me_date,mae_owner_Class);
        linkTwoModelElements (api, me_mois,mae_typed_Attribute,me_integer,mae_type_Classifier);
        linkTwoModelElements (api, me_mois,mae_attribute_Attribute,me_date,mae_owner_Class);
        linkTwoModelElements (api, me_annee,mae_typed_Attribute,me_integer,mae_type_Classifier);
        linkTwoModelElements (api, me_annee,mae_attribute_Attribute,me_date,mae_owner_Class);
        linkTwoModelElements (api, me_date_creation,mae_attribute_Attribute,me_oeuvre,mae_owner_Class);
        linkTwoModelElements (api, me_date_creation,mae_typed_Attribute,me_date,mae_type_Classifier);
        linkTwoModelElements (api, me_date_naissance,mae_attribute_Attribute,me_compositeur,mae_owner_Class);
        linkTwoModelElements (api, me_date_naissance,mae_typed_Attribute,me_date,mae_type_Classifier);


        me_compositeur.setAttributeValue (me_compositeur,ma_name,new StringValueImpl(false,null,"compositeur"));
        me_compositeur.setAttributeValue (me_compositeur,ma_kind,new StringValueImpl(false,null,"persistent"));
        me_composition.setAttributeValue (me_composition,ma_name,new StringValueImpl(false,null,"composition"));
        me_composition.setAttributeValue (me_composition,ma_kind,new StringValueImpl(false,null,""));
        me_interprete.setAttributeValue (me_interprete,ma_name,new StringValueImpl(false,null,"interprete"));
        me_interprete.setAttributeValue (me_interprete,ma_kind,new StringValueImpl(false,null,"persistent"));
        me_interpretation.setAttributeValue (me_interpretation,ma_name,new StringValueImpl(false,null,"interpretation"));
        me_interpretation.setAttributeValue (me_interpretation,ma_kind,new StringValueImpl(false,null,""));
        me_oeuvre.setAttributeValue (me_oeuvre,ma_name,new StringValueImpl(false,null,"oeuvre"));
        me_oeuvre.setAttributeValue (me_oeuvre,ma_kind,new StringValueImpl(false,null,"persistent"));
        me_annees_etudes.setAttributeValue (me_annees_etudes,ma_name,new StringValueImpl(false,null,"années_études"));
        me_annees_etudes.setAttributeValue (me_annees_etudes,ma_kind,new StringValueImpl(false,null,"primary"));
        me_integer.setAttributeValue (me_integer,ma_name,new StringValueImpl(false,null,"integer"));
        me_integer.setAttributeValue (me_integer,ma_kind,new StringValueImpl(false,null,""));
        me_date.setAttributeValue (me_date,ma_name,new StringValueImpl(false,null,"date"));
        me_date.setAttributeValue (me_date,ma_kind,new StringValueImpl(false,null,"persistent"));
        me_jour.setAttributeValue (me_jour,ma_name,new StringValueImpl(false,null,"jour"));
        me_jour.setAttributeValue (me_jour,ma_kind,new StringValueImpl(false,null,"primary"));
        me_mois.setAttributeValue (me_mois,ma_name,new StringValueImpl(false,null,"mois"));
        me_mois.setAttributeValue (me_mois,ma_kind,new StringValueImpl(false,null,"primary"));
        me_annee.setAttributeValue (me_annee,ma_name,new StringValueImpl(false,null,"année"));
        me_annee.setAttributeValue (me_annee,ma_kind,new StringValueImpl(false,null,"primary"));
        me_date_creation.setAttributeValue (me_date_creation,ma_name,new StringValueImpl(false,null,"date_création"));
        me_date_creation.setAttributeValue (me_date_creation,ma_kind,new StringValueImpl(false,null,"composed"));
        me_date_naissance.setAttributeValue (me_date_naissance,ma_name,new StringValueImpl(false,null,"date_naissance"));
        me_date_naissance.setAttributeValue (me_date_naissance,ma_kind,new StringValueImpl(false,null,"composed"));


        api.shutdown(null);
        
	}

    private static void linkTwoModelElements (API api, ModelElement me_source, MetaAssociationEnd ae_source, ModelElement me_target, MetaAssociationEnd ae_target) throws Exception
    {
        MetaAssociation assoc = api.getMetaAssociationWithAssociationEnds(new MetaAssociationEnd[] { ae_source, ae_target });
        assoc.associateModelElements (null, new ModelRole [] { api.getRole(me_target, ae_target), api.getRole(me_source, ae_source) } );
    }
	
	public static void testUML13Model () throws Exception {
		final MDRAPI api = new MDRAPI(null, new LoadedMetamodel("UML 1.3", new String [] {"UML"}), "test", new XmiModel(rootPath + "/../ThirdParty/MDR/Test/models/test.xml", XmiModel.Read));
		api.startup(null);

		MetaClass classifier = api.getMetaClass(new String [] {"UML", "Foundation", "Core", "Classifier"});

		MetaClass classifier2 = api.getMetaClass(new String [] {"Foundation", "Core", "Classifier"});
		
		if (classifier != classifier2)
			throw new Exception("A single class leads to several internal proxies...");
		

		MetaClass clazz = api.getMetaClass(new String [] {"Foundation", "Core", "Class"});
		MetaClass stereotype = api.getMetaClass(new String [] {"Foundation", "Extension_Mechanisms", "Stereotype"});
		
		
		ModelElementIterator instances = classifier.allInstancesIterator(null);
		StringBuffer sbuf = new StringBuffer();
		while (instances.hasNext()) {
			sbuf.append(((JMIModelElement)instances.next()));
		}
		
		try {

			classifier = api.getMetaClass(new String [] {"Foundation", "Core", "Toto"});
			throw new Exception ("Test testUML13Model passed while should not: found classifier Toto !");
		} catch (UnknownElementException x) {
		}

		MetaClass visibilityKind = api.getMetaClass(new String [] {"Foundation", "Data_Types", "VisibilityKind"});
		MetaClass visibilityKind2 = api.getMetaClass(new String [] {"Foundation", "Data_Types", "VisibilityKind"});
		
		if (visibilityKind != visibilityKind2)
			throw new Exception("A single class enumeration leads to several internal proxies...");
		
		ModelElement object = clazz.instanciate(null, new Value [] {/**/
				new StringValueImpl(false, null, "MaClasse"), //ModelElement.name
				visibilityKind.instanciate(null, new Value [] {new StringValueImpl(false, null, "vk_protected")}), //ModelElement.visibility  -- null is default value...
				new BooleanValueImpl(false, null, false), //ModelElement.isSpecification
				new BooleanValueImpl(false, null, true), //GeneralizableElement.isRoot
				new BooleanValueImpl(false, null, false), //GeneralizableElement.isLeaf
				new BooleanValueImpl(false, null, false), //GeneralizableElement.isAbstract
				new BooleanValueImpl(false, null, false) //Class.isActive/**/
			});
		
		if (object.isUndefined())
			throw new Exception("JMIMetaClass.instanciate test failed (" + object.getErrorMessage() + ").");
		
		Value objectName = object.getFeatureValue(null, api.getMetaFeature("name", null), null); 
		if (! new StringValueImpl(false, null, "MaClasse").equals(objectName))
			throw new Exception("JMIMetaClass.instanciate test failed (attribute name was not set to MaClass but to " + (objectName == null ? "null" : objectName.toString()) + ").");
		Value objectVis = object.getFeatureValue(null, api.getMetaFeature("visibility", null), null); 
		if (! new StringValueImpl(false, null, "MaClasse").equals(objectName))
			throw new Exception("JMIMetaClass.instanciate test failed (attribute visibility was not set to vk_protected but to " + (objectVis == null ? "null" : objectVis.toString()) + ").");
		
		try {
			Value v = object.getFeatureValue(null, api.getMetaFeature("toto", null), null);
			throw new Exception("Found a value for toto ! (" + v.toString() + ')');
		} catch (UnknownElementException x) {}
		
		
		if (! object.isKindOf(clazz))
			throw new Exception("JMIModelElement.isKindOf direct test failed.");
		if (! object.isTypeOf(clazz))
			throw new Exception("JMIModelElement.isTypeOf direct test failed.");
		if (! object.isKindOf(classifier))
			throw new Exception("JMIModelElement.isKindOf transitive test failed.");
		if (object.isTypeOf(classifier))
			throw new Exception("JMIModelElement.isTypeOf transitive test failed.");
		if (object.isKindOf(stereotype))
			throw new Exception("JMIModelElement.isKindOf false test failed.");
		if (object.isTypeOf(stereotype))
			throw new Exception("JMIModelElement.isTypeOf false test failed.");
			
		try {
			ModelElement unk = classifier.instanciate(null, null);
			throw new Exception("JMIMetaClass.instanciate test failed (can instanciate abstract class).");
		} catch (Exception x) {
		}
			
		try {
			ModelElement enum = visibilityKind.instanciate(null, new Value [] {new StringValueImpl(false, null, "toto")});
			if (! enum.isUndefined())
				throw new Exception("JMIMetaEnumeration.instanciate: found " + enum.toString());
		} catch (UnknownElementException x) {
		}
		
		ModelElement object2 = clazz.instanciate(null, null);
		
		if (object2.isUndefined())
			throw new Exception ("Cannot instanciate class...");
		
		
		if (! object.isKindOf(clazz))
			throw new Exception("JMIModelElement.isKindOf direct test failed.");
		if (! object.isTypeOf(clazz))
			throw new Exception("JMIModelElement.isTypeOf direct test failed.");
		if (! object.isKindOf(classifier))
			throw new Exception("JMIModelElement.isKindOf transitive test failed.");
		if (object.isTypeOf(classifier))
			throw new Exception("JMIModelElement.isTypeOf transitive test failed.");
		if (object.isKindOf(stereotype))
			throw new Exception("JMIModelElement.isKindOf false test failed.");
		if (object.isTypeOf(stereotype))
			throw new Exception("JMIModelElement.isTypeOf false test failed.");
		

		ModelElement vkpriv = visibilityKind.instanciate(null, new Value [] {new StringValueImpl(false, null, "vk_private")});
		if (vkpriv != visibilityKind2.getMetaObject().getFeatureValue(null, api.getMetaFeature("vk_private", null), null))
			throw new Exception ("JMIMetaEnumeration: a single enumered leads to several proxies...");
		
		

		// TODO Some tests using enumeration contained by a classifier...
		
		
	}

}

class FeatureValueConstraint implements LookupConstraint {
	public final MetaFeature feature;
	public final Value value;
	
	public FeatureValueConstraint (API api, String featureName, Value featureValue) {
		this.feature = api.getMetaFeature(featureName, null);
		this.value = featureValue;
	}
	
	public boolean match(org.irisa.triskell.MT.repository.API.Java.ModelElement element) {
		try {
			return element.getFeatureValue(null, this.feature, null).equals(this.value);
		} catch (Exception x) {
			throw new RuntimeException(x);
		}
	} 
}
