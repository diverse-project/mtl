/* $Id: EMFMetaEnumeration.java,v 1.1 2004-03-08 08:18:17 jpthibau Exp $
 * Authors : 
 * 
 * Copyright 2003 - INRIA - LGPL license
 */
package org.inria.EMFDriver;

import org.irisa.triskell.MT.DataTypes.Java.CollectionValue;
import org.irisa.triskell.MT.DataTypes.Java.Type;
import org.irisa.triskell.MT.DataTypes.Java.TypeValue;
import org.irisa.triskell.MT.DataTypes.Java.Value;
import org.irisa.triskell.MT.DataTypes.Java.ValueVisitor;
import org.irisa.triskell.MT.DataTypes.Java.commands.MultipleCommandException;
import org.irisa.triskell.MT.DataTypes.Java.commands.UnknownCommandException;
import org.irisa.triskell.MT.repository.API.Java.CommonException;
import org.irisa.triskell.MT.repository.API.Java.IllegalAccessException;
import org.irisa.triskell.MT.repository.API.Java.LookupConstraint;
import org.irisa.triskell.MT.repository.API.Java.MetaAttribute;
import org.irisa.triskell.MT.repository.API.Java.MetaClass;
import org.irisa.triskell.MT.repository.API.Java.ModelElement;
import org.irisa.triskell.MT.repository.API.Java.ModelElementIterator;
import org.irisa.triskell.MT.repository.API.Java.UnknownElementException;

import org.eclipse.emf.ecore.*;
/**
 * @author jpthibau
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class EMFMetaEnumeration
	extends EMFMetaType
//	implements org.irisa.triskell.MT.repository.API.Java.MetaClass, org.irisa.triskell.MT.repository.API.Java.ModelElement, TypeValue
{

	public EMFMetaEnumeration(
		EMFAPI api,
		EEnum metaObject,
		EPackage container)
	{
		this(api, metaObject, container, null);
	}

	public EMFMetaEnumeration(
		EMFAPI api,
		EEnum metaObject,
		EClass container)
	{
		this(api, metaObject, null, container);
	}

	public EMFMetaEnumeration(
		EMFAPI api,
		EEnum metaObject,
		EPackage packageContainer,
		EClass classContainer)
	{
		super(api, metaObject, null, retrieveQualifiedName(metaObject));
/*		this.refMetaObject = metaObject;
		this.packageContainer = packageContainer;
		this.classContainer = classContainer;*/
	}

	public static String[] retrieveQualifiedName(
		EEnum ref)
	{
		String [] ret = new String [2];
		return ret;
	}
	
	/* (non-Javadoc)
	 * @see org.irisa.triskell.MT.DataTypes.Java.Value#invoke(java.lang.String[], java.lang.String, org.irisa.triskell.MT.DataTypes.Java.Value[], java.lang.String[])
	 */
	public Value invoke(
		String[] arg0,
		String arg1,
		Value[] arg2,
		String[] arg3)
		throws UnknownCommandException, MultipleCommandException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.irisa.triskell.MT.repository.API.Java.MetaClass#allInstances()
	 */
	public CollectionValue allInstances() throws UnknownElementException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.inria.EMFDriver.EMFMetaType#allInstancesWithConstraint(org.irisa.triskell.MT.repository.API.Java.LookupConstraint)
	 */
	public CollectionValue allInstancesWithConstraint(LookupConstraint constraint)
		throws UnknownElementException {
		// TODO Auto-generated method stub
		return super.allInstancesWithConstraint(constraint);
	}

	/* (non-Javadoc)
	 * @see org.inria.EMFDriver.EMFMetaType#allInstancesIterator()
	 */
	public ModelElementIterator allInstancesIterator() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.inria.EMFDriver.EMFMetaType#allInstancesIterator(org.irisa.triskell.MT.repository.API.Java.LookupConstraint)
	 */
	public ModelElementIterator allInstancesIterator(LookupConstraint constraint) {
		// TODO Auto-generated method stub
		return super.allInstancesIterator(constraint);
	}

	/* (non-Javadoc)
	 * @see org.irisa.triskell.MT.DataTypes.Java.Value#getType()
	 */
	public Type getType() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.irisa.triskell.MT.DataTypes.Java.Type#conformsTo(org.irisa.triskell.MT.DataTypes.Java.Type)
	 */
	public boolean conformsTo(Type arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see org.irisa.triskell.MT.repository.API.Java.MetaClass#getMetaObject()
	 */
	public ModelElement getMetaObject() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.irisa.triskell.MT.repository.API.Java.ModelElement#isMetaObject()
	 */
	public boolean isMetaObject() {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see org.irisa.triskell.MT.repository.API.Java.ModelElement#isTypeOf(org.irisa.triskell.MT.repository.API.Java.MetaClass)
	 */
	public boolean isTypeOf(Value arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see org.irisa.triskell.MT.repository.API.Java.ModelElement#isKindOf(org.irisa.triskell.MT.repository.API.Java.MetaClass)
	 */
	public boolean isKindOf(Value arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see org.irisa.triskell.MT.DataTypes.Java.Type#toString()
	 */
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.irisa.triskell.MT.repository.API.Java.ModelElement#getUniqId()
	 */
	public String getUniqId() {
		// TODO Auto-generated method stub
		return null;
	}
	
	/* (non-Javadoc)
	 * @see org.irisa.triskell.MT.repository.API.Java.ModelElement#setAttributeValue(org.irisa.triskell.MT.repository.API.Java.ModelElement, org.irisa.triskell.MT.repository.API.Java.MetaAttribute, org.irisa.triskell.MT.DataTypes.Java.Value)
	 */
	public void setAttributeValue(
		ModelElement arg0,
		MetaAttribute arg1,
		Value arg2)
		throws UnknownElementException, CommonException, IllegalAccessException {
		// TODO Auto-generated method stub

	}
	
	/* (non-Javadoc)
	 * @see org.irisa.triskell.MT.DataTypes.Java.Value#accept(org.irisa.triskell.MT.DataTypes.Java.ValueVisitor)
	 */
	public void accept(ValueVisitor arg0) {
		// TODO Auto-generated method stub

	}


}
