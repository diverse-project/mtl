/* $Id: EMFMetaClass.java,v 1.3 2004-03-11 13:28:03 jpthibau Exp $
 * Authors : 
 * 
 * Copyright 2003 - INRIA - LGPL license
 */
package org.inria.EMFDriver;

import org.irisa.triskell.MT.DataTypes.Java.CollectionValue;
import org.irisa.triskell.MT.DataTypes.Java.Type;
import org.irisa.triskell.MT.DataTypes.Java.Value;
import org.irisa.triskell.MT.repository.API.Java.API;
import org.irisa.triskell.MT.repository.API.Java.CommonException;
import org.irisa.triskell.MT.repository.API.Java.IllegalAccessException;
import org.irisa.triskell.MT.repository.API.Java.LookupConstraint;
import org.irisa.triskell.MT.repository.API.Java.ModelElement;
import org.irisa.triskell.MT.repository.API.Java.ModelElementIterator;
import org.irisa.triskell.MT.repository.API.Java.UnknownElementException;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.command.*;
import org.eclipse.emf.edit.command.*;
import org.eclipse.emf.ecore.*;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.common.util.*;
import java.util.Collection;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * @author jpthibau
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class EMFMetaClass extends EMFMetaType
	implements org.irisa.triskell.MT.repository.API.Java.MetaClass
{
	private EMFChildElement refClass;
	
	private EMFMetaObject metaObject;

	public EMFMetaClass(
		EMFAPI api,
		EMFChildElement ref)
	{
		this(api, ref, retrieveQualifiedName(api,ref));
	}

	public EMFMetaClass(
		EMFAPI api,
		EMFChildElement ref,
		String[] qualifiedName)
	{
		super(api, ref, qualifiedName == null ? ref.childDescriptor.getEValue().eClass().getName() : qualifiedName[qualifiedName.length - 1], qualifiedName);
		this.refClass = ref;
		ref.metaClass = this;
	}
    
	public ModelElementIterator allInstancesIterator () {
		return new EMFModelElementIterator(this.getSpecificAPI(), this.buildAllInstances(this.getRefClass()));
	}
	
	public Collection buildAllInstances(EMFChildElement refClass) {
		Collection result = new ArrayList();
		EClass type = refClass.childDescriptor.getEValue().eClass();
		TreeIterator it = this.getSpecificAPI().resource.getAllContents();
		while (it.hasNext()) {
			EObject modelElt = (EObject)it.next();
			if (modelElt.eClass().equals(type)) {
				EMFElement elt=new EMFModelElement(false,null,this.getSpecificAPI(),modelElt,refClass.metaClass);
				this.getSpecificAPI().setElement(elt,elt);
				result.add(elt);				
			}
		}
		return result;
		
	}

	/**
	  * @see org.irisa.triskell.MT.repository.API.Java.MetaClass#getMetaObject() 
	  */
	public org.irisa.triskell.MT.repository.API.Java.ModelElement getMetaObject()
	{
		if (this.metaObject == null)
			this.metaObject = new EMFMetaObject(this);
		return this.metaObject;
	}

	/**
	  * @see org.irisa.triskell.MT.DataTypes.Java.Value#invoke(String, org.irisa.triskell.MT.DataTypes.Java.Value[], String[]) 
	  */
	public org.irisa.triskell.MT.DataTypes.Java.Value invoke(
		String [] scopeQualifiedName,
		String name,
		org.irisa.triskell.MT.DataTypes.Java.Value[] arguments,
		String[] discriminants)
		throws UnknownElementException
	{
		 if (name.equals("allInstances")) {
				return this.allInstances();
		 }
		return null;
	}
	
	public EStructuralFeature getAttribute(String name) {
		EList sfs = this.refClass.childDescriptor.getEValue().eClass().getEAllStructuralFeatures();
		Iterator it = sfs.iterator();
		while (it.hasNext()) {
			EStructuralFeature f = (EStructuralFeature)it.next();
			if (f.getName().equals(name)) return f;
		}
		return null;
	}
	/**
	  * @see org.irisa.triskell.MT.repository.API.Java.MetaClass#instanciate(org.irisa.triskell.MT.repository.API.Java.ModelElement, org.irisa.triskell.MT.DataTypes.Java.Value[]) 
	  */
	public org.irisa.triskell.MT.repository.API.Java.ModelElement instanciate(
		org.irisa.triskell.MT.repository.API.Java.ModelElement contextualElement,
		org.irisa.triskell.MT.DataTypes.Java.Value[] arguments)
		throws org.irisa.triskell.MT.repository.API.Java.UnknownElementException, org.irisa.triskell.MT.repository.API.Java.CommonException, org.irisa.triskell.MT.repository.API.Java.IllegalAccessException
	{
		org.irisa.triskell.MT.repository.API.Java.ModelElement ret =null;
		if (arguments != null && arguments.length > 0)
			EMFDriver.log.warn("Ignoring arguments in metaClass.instantiate(...). EMF provides only a 'withou parameters' constructor");
		//try to get the creation command related to this metaClass
		Command cmd = this.refClass.creationCommand;
		if (cmd == null) throw new CommonException("Cannot find a model element constructor for metaClass " + this.toString());
		cmd.execute();
		Collection builtChildren = cmd.getResult();
		if (builtChildren.isEmpty()) 
			throw new CommonException("The model elemnt constructor fails for metaClass " + this.toString());
		if (builtChildren.size() > 1)
			EMFDriver.log.warn("It seems a call to metaClass constructor created more than one object for the metaClass "+ this.toString()); 
		EObject builtChild = (EObject)builtChildren.iterator().next();
		ret = new EMFModelElement(false,null,this.getSpecificAPI(),builtChild,refClass.metaClass);
		return ret;
	}

	public static String[] retrieveQualifiedName(
		EMFAPI api,
		EMFChildElement ref)
	{
		String [] ret = new String [3];
		ret[0] = api.modelName;
		ret[1] = ref.childDescriptor.getEReference().getContainerClass().getName();
		ret[2] = ref.childDescriptor.getEValue().eClass().getName();
		return ret;
	}

	public String toString()
	{
		return "meta class " + org.irisa.triskell.MT.utils.Java.AWK.merge(this.getQualifiedName(), Type.PackageIndirection);
	}

	public EMFChildElement getRefClass()
	{
		return this.refClass;
	}

	public boolean conformsTo(Type parentType) {
		System.out.println("TO IMPLEMENT EMFMEtaClass conformsTo()");
/*		if (this.equals(parentType))
			return true;
		if (ModelElementType.TheInstance.conformsTo(parentType))
			return true;
		if (! (parentType instanceof JMIMetaClass))
			return false;
		JMIMetaClass parentClass = (JMIMetaClass)parentType;
		if (! this.getSpecificAPI().equals(parentClass.getSpecificAPI()))
			return false;
			 
		MofClass parentMOFClass = (MofClass)parentClass.getRefClass().refMetaObject();
		ArrayList conforming = new ArrayList(1);
		conforming.add((MofClass)this.getRefClass());
		Iterator it;
		do {
			it = conforming.iterator();
			while (it.hasNext()) {
				if (it.next().equals(parentMOFClass))
					return true;
			}
			
			it = conforming.iterator();
			conforming = new ArrayList();
			while (it.hasNext())
				conforming.addAll(((MofClass)it.next()).getSupertypes());
		} while (! conforming.isEmpty());*/
		return false;
	}

	public boolean isKindOf(Value v) {
		System.out.println("TO IMPLEMENT EMFMEtaClass isKindOf()");
//		return (v instanceof JMIModelElement) && ((JMIModelElement)v).isKindOf(this);
		return false;
	}

	public boolean isTypeOf(Value v) {
		System.out.println("TO IMPLEMENT EMFMEtaClass isTypeOf()");
//		return (v instanceof JMIModelElement) && ((JMIModelElement)v).isTypeOf(this);
	return false;
	}

}
