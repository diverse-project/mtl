/* $Id: EMFAPI.java,v 1.3 2004-03-16 15:11:40 jpthibau Exp $
 * Authors : 
 * 
 * Copyright 2003 - INRIA - LGPL license
 */
package org.inria.EMFDriver;

import java.util.Hashtable;

import org.irisa.triskell.MT.BasicMTL.DataTypes.impl.BMTLBoolean;
import org.irisa.triskell.MT.BasicMTL.DataTypes.impl.BMTLString;
import org.irisa.triskell.MT.DataTypes.Java.Value;
import org.irisa.triskell.MT.repository.API.Java.MetaAssociation;
import org.irisa.triskell.MT.repository.API.Java.MetaAssociationEnd;
import org.irisa.triskell.MT.repository.API.Java.MetaAttribute;
import org.irisa.triskell.MT.repository.API.Java.MetaClass;
import org.irisa.triskell.MT.repository.API.Java.MetaFeature;
import org.irisa.triskell.MT.repository.API.Java.MetaOperation;
import org.irisa.triskell.MT.repository.API.Java.ModelElement;
import org.irisa.triskell.MT.repository.API.Java.ModelRole;
import org.irisa.triskell.MT.repository.API.Java.UnknownElementException;

import org.eclipse.emf.edit.command.*;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.resource.Resource;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

import org.irisa.triskell.MT.DataTypes.Java.CollectionKind;
import org.irisa.triskell.MT.DataTypes.Java.CollectionValue;
import org.irisa.triskell.MT.repository.API.Java.ModelElementIterator;
import org.irisa.triskell.MT.DataTypes.Java.defaultImpl.BagValueImpl;
import org.irisa.triskell.MT.DataTypes.Java.defaultImpl.OrderedSetValueImpl;
import org.irisa.triskell.MT.DataTypes.Java.defaultImpl.SequenceValueImpl;
import org.irisa.triskell.MT.DataTypes.Java.defaultImpl.SetValueImpl;
import org.irisa.triskell.MT.repository.API.Java.utils.ModelElementIteratorToJavaIteratorConverter;
import org.irisa.triskell.MT.utils.Java.IteratingFinalList;

import org.eclipse.emf.ecore.*;
import org.eclipse.emf.common.util.EList;

/**
 * @author jpthibau
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class EMFAPI
implements org.irisa.triskell.MT.repository.API.Java.API
{
	// these Map are here to create a sort of cache of the objects in the JMI repository
	// it helps accessing them quickier 
	protected final Map elements = new Hashtable();

	private Hashtable metaclassesTable;
	public String modelName;
	public String modelFileExtension;
	public String modelXmiFileName;
	public Resource resource;
	public EditingDomain editingDomain;
	public boolean needSave;

	protected static final org.irisa.triskell.MT.repository.API.Java.CommonException nullPointerException = new org.irisa.triskell.MT.repository.API.Java.CommonException("Null pointer exception.");
	public static org.irisa.triskell.MT.repository.API.Java.CommonException getNullPointerException () {
		return nullPointerException;
	}
	public static int cardNullPointerException () {
		if (nullPointerException == null) return 0;
		else return 1;
	}

	
	protected Value2Java values2javaConverter;
	public Value2Java getValues2javaConverter () {
	   return this.values2javaConverter;
	}
	public int cardValues2javaConverter () {
	   if ( this.values2javaConverter == null ) return 0;
	   else return 1;
	}
	   
	public class Value2Java 
		implements org.irisa.triskell.MT.DataTypes.Java.ValueVisitor
	{
		private java.lang.Object distilled;
		protected java.lang.Object getDistilled () {
			return this.distilled;
		}
		protected int cardDistilled () {
			if ( this.distilled == null ) return 0;
			else return 1;
		}

		protected final boolean out;
		public boolean getOut () {
			return this.out;
		}

		protected final boolean multiple;
		public boolean getMultiple () {
			return this.multiple;
		}


		public Value2Java(
			boolean out,
			boolean multiple)
		{
			this.out = out;
			this.multiple = multiple;
		}

		public void visitValue(
			org.irisa.triskell.MT.DataTypes.Java.Value value)
		{
			this.distilled = null;
		}

		public void visitPrimitiveValue(
			org.irisa.triskell.MT.DataTypes.Java.PrimitiveValue value)
		{
			this.distilled = value.getValue();
			if (multiple) {
				Object val = this.distilled;
				this.distilled = new java.util.ArrayList(1);
				((java.util.List)this.distilled).add(val);
			}
			if (out) {
				if (multiple)
					this.distilled = new java.util.List [] {(java.util.List)this.distilled};
				else
					this.distilled = new String [] {(String)this.distilled};
			}
		}

		public void visitBooleanValue(
			org.irisa.triskell.MT.DataTypes.Java.BooleanValue value)
		{
			this.distilled = value.getTheBoolean() ? Boolean.TRUE : Boolean.FALSE;
			if (multiple) {
				Object val = this.distilled;
				this.distilled = new java.util.ArrayList(1);
				((java.util.List)this.distilled).add(val);
			}
			if (out) {
				if (multiple)
					this.distilled = new java.util.List [] {(java.util.List)this.distilled};
				else
					this.distilled = new Boolean [] {(Boolean)this.distilled};
			}
		}

		public void visitStringValue(
			org.irisa.triskell.MT.DataTypes.Java.StringValue value)
		{
			this.distilled = value.getTheString();
			if (multiple) {
				Object val = this.distilled;
				this.distilled = new java.util.ArrayList(1);
				((java.util.List)this.distilled).add(val);
			}
			if (out) {
				if (multiple)
					this.distilled = new java.util.List [] {(java.util.List)this.distilled};
				else
					this.distilled = new String [] {(String)this.distilled};
			}
		}

		public void visitRealValue(
			org.irisa.triskell.MT.DataTypes.Java.RealValue value)
		{
			this.distilled = new Float(value.getTheReal());
			if (multiple) {
				Object val = this.distilled;
				this.distilled = new java.util.ArrayList(1);
				((java.util.List)this.distilled).add(val);
			}
			if (out) {
				if (multiple)
					this.distilled = new java.util.List [] {(java.util.List)this.distilled};
				else
					this.distilled = new Float [] {(Float)this.distilled};
			}
		}

		public void visitIntegerValue(
			org.irisa.triskell.MT.DataTypes.Java.IntegerValue value)
		{
			this.distilled = new Integer(value.getTheInteger());
			if (multiple) {
				Object val = this.distilled;
				this.distilled = new java.util.ArrayList(1);
				((java.util.List)this.distilled).add(val);
			}
			if (out) {
				if (multiple)
					this.distilled = new java.util.List [] {(java.util.List)this.distilled};
				else
					this.distilled = new Integer [] {(Integer)this.distilled};
			}
		}

		public void visitEnumValue(
			org.irisa.triskell.MT.DataTypes.Java.EnumValue value)
		{
/*			if (! (value instanceof JMIEnumered))
				throw new RuntimeException("This enumered is not from the good model");*/
			this.distilled = ((EMFEnumered)value).getRef();
			if (multiple) {
				Object val = this.distilled;
				this.distilled = new java.util.ArrayList(1);
				((java.util.List)this.distilled).add(val);
			}
			if (out) {
				if (multiple)
					this.distilled = new java.util.List [] {(java.util.List)this.distilled};
				else {
					Object val = this.distilled;
					this.distilled = java.lang.reflect.Array.newInstance(this.distilled.getClass(), 1);
					java.lang.reflect.Array.set(this.distilled, 1, val);
				}
			}
		}

		public void visitCollectionValue(
			org.irisa.triskell.MT.DataTypes.Java.CollectionValue value)
		{
	org.irisa.triskell.MT.DataTypes.Java.Value [] theCollection = value.getTheCollection();
//			CollectionKind theKind = value.getKind();
			Object [] result = new Object [value.getTheCollection().length];
			for (int i = 0; i < theCollection.length; ++i) {
				theCollection[i].accept(this);
				result[i] = this.distilled;
			}
			this.distilled = java.util.Arrays.asList(result);
			if (out)
				this.distilled = new java.util.List [] {(java.util.List)this.distilled};
		}

		public void visitModelElementValue(
			org.irisa.triskell.MT.DataTypes.Java.ModelElementValue value)
		{
			if (value instanceof EMFElement) {
				this.distilled = ((EMFElement)value).getRef();
			} else if (value instanceof EMFEnumered) {
					this.distilled = ((EMFEnumered)value).getRef();
			} else /*if (value instanceof JMIStruct) {
					this.distilled = ((JMIStruct)value).getRef();
			} else*/ if (value.isUndefined()) {
				this.distilled = new Throwable(value.getErrorMessage());
			} else
				throw new RuntimeException("This element is not from the good model");

			if (multiple) {
				Object val = this.distilled;
				this.distilled = new java.util.ArrayList(1);
				((java.util.List)this.distilled).add(val);
			}

			if (out) {
				if (multiple)
					this.distilled = new java.util.List [] {(java.util.List)this.distilled};
				else {
					Object val = this.distilled;
					this.distilled = java.lang.reflect.Array.newInstance(this.distilled.getClass(), 1);
					java.lang.reflect.Array.set(this.distilled, 1, val);
				}
			}
		}

		public void visitTupleValue(
			org.irisa.triskell.MT.DataTypes.Java.TupleValue value)
		{
			System.out.println("TO implement ? : visitTupleVlaue in value2java => tuples don't exist in EMF");
/*			if (! (value instanceof JMIStruct))
				throw new RuntimeException("This tuple is not from the good model");
			this.distilled = ((JMIStruct)value).getRef();
			if (multiple) {
				Object val = this.distilled;
				this.distilled = new java.util.ArrayList(1);
				((java.util.List)this.distilled).add(val);
			}
			if (out) {
				if (multiple)
					this.distilled = new java.util.List [] {(java.util.List)this.distilled};
				else {
					Object val = this.distilled;
					this.distilled = java.lang.reflect.Array.newInstance(this.distilled.getClass(), 1);
					java.lang.reflect.Array.set(this.distilled, 1, val);
				}
			}*/
		}

		public void visitVoidValue(
			org.irisa.triskell.MT.DataTypes.Java.VoidValue value)
		{
			throw new RuntimeException("Cannot convert a Void value.");
		}

		public void visitTypeValue(
			org.irisa.triskell.MT.DataTypes.Java.TypeValue value)
		{
			throw new RuntimeException("Cannot convert a Type value.");
		}

		public void visitNullValue(
			org.irisa.triskell.MT.DataTypes.Java.NullValue value)
		{
			this.distilled = null;
		}
	}

	public EMFAPI(String modelName,String modelFileExtension,String modelXmiInputFileName,EditingDomain ed,Resource resource) {
		this.modelName = modelName;
		this.modelFileExtension = modelFileExtension;
		this.modelXmiFileName = modelXmiInputFileName;
		this.editingDomain = ed;
		this.resource = resource;
		this.needSave = false;
		this.metaclassesTable = new Hashtable();
	}
	
		/* (non-Javadoc)
		 * @see org.irisa.triskell.MT.repository.API.Java.API#startup(org.irisa.triskell.MT.DataTypes.Java.Value[])
		 */
		public void startup(Value[] arg0) {
			// TODO Auto-generated method stub

		}

	/* (non-Javadoc)
	 * @see org.irisa.triskell.MT.repository.API.Java.API#shutdown(org.irisa.triskell.MT.DataTypes.Java.Value[])
	 */
	public void shutdown(Value[] arg0) {
		// TODO Auto-generated method stub

	}

	public EMFElement getElement(
		java.lang.Object ref)
	{
		return (EMFElement) this.elements.get(ref);
	}

	public void setElement(
		EMFElement element,
		java.lang.Object ref)
{
	this.elements.put(ref, element);
	}

	/* (non-Javadoc)
	 * @see org.irisa.triskell.MT.repository.API.Java.API#getMetaClass(java.lang.String[])
	 */
	public MetaClass getMetaClass(String[] name)
		throws UnknownElementException {
		if (name.length != 2)
			throw new org.irisa.triskell.MT.repository.API.Java.UnknownElementException(new EMFUnknownElement(this, "meta class " + name));
		String fullName = name[0]+name[1];
		MetaClass mc = (MetaClass)this.metaclassesTable.get(fullName);
		if (mc == null) {
			boolean found = false;
			Hashtable metaClasses = EMFDriver.collectMetaClasses(this);
			Iterator metaClassesIter = metaClasses.keySet().iterator();
			while (metaClassesIter.hasNext() && !found) {
				EMFChildElement elt = (EMFChildElement)metaClasses.get(metaClassesIter.next());
				if (name[0].equals(elt.childDescriptor.getEReference().getContainerClass().getName())
					&& name[1].equals(elt.childDescriptor.getEValue().eClass().getName()) ) {
					mc = new EMFMetaClass(this,elt);
					found = true;
				}
			}
			if (! found) throw new org.irisa.triskell.MT.repository.API.Java.UnknownElementException(new EMFUnknownElement(this, "meta class " + name));
		}
		return mc;
	}

	public org.irisa.triskell.MT.repository.API.Java.MetaClass getMetaClass(
		EMFChildElement ref)
	{
		EMFMetaClass ret = (EMFMetaClass) this.getElement(ref);
		if (ret == null) {
			ret = new EMFMetaClass(this, ref);
//			this.refClasses.put(java.util.Arrays.asList(ret.getQualifiedName()), ref);
		}
		return ret;
	}


	/* (non-Javadoc)
	 * @see org.irisa.triskell.MT.repository.API.Java.API#getMetaAttribute(java.lang.String, org.irisa.triskell.MT.repository.API.Java.MetaClass)
	 */
	public MetaAttribute getMetaAttribute(String arg0, MetaClass arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.irisa.triskell.MT.repository.API.Java.API#getMetaFeature(java.lang.String, org.irisa.triskell.MT.repository.API.Java.MetaClass)
	 */
	public MetaFeature getMetaFeature(String arg0, MetaClass arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.irisa.triskell.MT.repository.API.Java.API#getMetaOperation(java.lang.String, org.irisa.triskell.MT.repository.API.Java.MetaClass)
	 */
	public MetaOperation getMetaOperation(String arg0, MetaClass arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.irisa.triskell.MT.repository.API.Java.API#getMetaAssociation(java.lang.String[])
	 */
	public MetaAssociation getMetaAssociation(String[] arg0)
		throws UnknownElementException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.irisa.triskell.MT.repository.API.Java.API#getMetaAssociationWithAssociationEnds(org.irisa.triskell.MT.repository.API.Java.MetaAssociationEnd[])
 	*/
	public MetaAssociation getMetaAssociationWithAssociationEnds(MetaAssociationEnd[] arg0)
		throws UnknownElementException {
		// TODO Auto-generated method stub
		return null;
}

	/* (non-Javadoc)
	 * @see org.irisa.triskell.MT.repository.API.Java.API#getMetaAssociationEnd(java.lang.String, org.irisa.triskell.MT.repository.API.Java.MetaClass, org.irisa.triskell.MT.repository.API.Java.MetaClass)
	 */
	public MetaAssociationEnd getMetaAssociationEnd(
		String arg0,
		MetaClass arg1,
		MetaClass arg2) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.irisa.triskell.MT.repository.API.Java.API#getRole(org.irisa.triskell.MT.repository.API.Java.ModelElement, org.irisa.triskell.MT.repository.API.Java.MetaAssociationEnd)
 	*/
	public ModelRole getRole(ModelElement arg0, MetaAssociationEnd arg1) {
		// TODO Auto-generated method stub
		return null;
}

	public org.irisa.triskell.MT.repository.API.Java.ModelElement getModelElement(
		Object ref)
{
	org.irisa.triskell.MT.repository.API.Java.ModelElement ret;
	if (ref == null)
		ret = null;
	else {
		ret = (org.irisa.triskell.MT.repository.API.Java.ModelElement)this.getElement(ref);
		if (ret == null)
			ret = new EMFModelElement(false, null, this, (EObject)ref);
	}
	return ret;
}

	public static CollectionValue toCollectionValue (ModelElementIterator it, CollectionKind collectionKind, boolean checkSet) {
		Collection c = new IteratingFinalList(new ModelElementIteratorToJavaIteratorConverter(it));
		if (collectionKind == CollectionKind.set_kind) return new SetValueImpl(false, null, c, checkSet);
		if (collectionKind == CollectionKind.bag_kind) return new BagValueImpl(false, null, c);
		if (collectionKind == CollectionKind.sequence_kind) return new SequenceValueImpl(false, null, c);
		if (collectionKind == CollectionKind.ordered_set_kind) return new OrderedSetValueImpl(false, null, c, checkSet);
		throw new RuntimeException ("Unsupported collection kind.");
	}

	public java.lang.Object value2java(
		org.irisa.triskell.MT.DataTypes.Java.Value value,
		boolean out, //pour creer vraiment la valeur ????
		boolean multiple) //List ou String[]
	{
		Value2Java translator = new Value2Java(out, multiple);
		value.accept(translator);
		return translator.getDistilled();
	}

	public org.irisa.triskell.MT.DataTypes.Java.Value java2value(
		java.lang.Object object,
		boolean isOrdered, //for Sequence and orderedSet : each element may be ordered to another
		boolean isUnique, //for Set and OrderedSet : each element is only once
		boolean checkSet)
	{
		//Ce code est très laid; il serait intéressant de forcer MDR à utiliser les "Value"s nativement... 

		if (object == null)
			return org.irisa.triskell.MT.DataTypes.Java.defaultImpl.NullValueImpl.getTheInstance();
    	
		//Primitive types
		if (object instanceof Boolean)
			return new org.irisa.triskell.MT.DataTypes.Java.defaultImpl.BooleanValueImpl(false, null, ((Boolean)object).booleanValue());
		if (object instanceof String)
			return new org.irisa.triskell.MT.DataTypes.Java.defaultImpl.StringValueImpl(false, null, (String)object);
		if (object instanceof Long) 
			return new org.irisa.triskell.MT.DataTypes.Java.defaultImpl.IntegerValueImpl(false, null, ((Long)object).intValue());
		if (object instanceof Integer) 
			return new org.irisa.triskell.MT.DataTypes.Java.defaultImpl.IntegerValueImpl(false, null, ((Integer)object).intValue());
		if (object instanceof Double) 
			return new org.irisa.triskell.MT.DataTypes.Java.defaultImpl.RealValueImpl(false, null, ((Double)object).floatValue());
		if (object instanceof Float) 
			return new org.irisa.triskell.MT.DataTypes.Java.defaultImpl.RealValueImpl(false, null, ((Float)object).floatValue());
		if (object instanceof EObject) {
			String [] objectType = new String[2];
			EMFMetaClass metaclass = null;
			objectType[0] = ((EObject)object).eContainmentFeature().getContainerClass().getName();
			objectType[1] = ((EObject)object).eClass().getName();
			try {
				metaclass = (EMFMetaClass)this.getMetaClass(objectType);
			} catch (UnknownElementException e) {return new EMFException(e.getMessage(),this); }
			if (metaclass != null)
				return new EMFModelElement(false,null,this,(EObject)object,metaclass);
			else
				return new EMFException(object.toString() + " : EObject can't access its related objectMClass.", this);
		}
		
		//Model types
		Object me = this.getElement(object);
		if (me instanceof org.irisa.triskell.MT.DataTypes.Java.Value)	
			return (org.irisa.triskell.MT.DataTypes.Java.Value)me;
		 
/*		if (object instanceof RefEnum)
			return this.getEnumered((RefEnum)object);
		if (object instanceof RefStruct)
			return this.getStruct((RefStruct)object);
		if (object instanceof RefObject)
			return this.getModelElement((RefObject)object);*/
		
		//Collection type
		//WARNING Nested collection are interpreted as "Sequence"s
		boolean isArray =  object instanceof Object[];
		boolean isEList = object instanceof EList;
		boolean isCollection = object instanceof java.util.Collection;
		if (isArray || isEList || isCollection) {
			if (isArray) {
				Object [] objects = (object instanceof Object[]) ? (Object [])object : ((java.util.Collection)object).toArray();
				java.util.ArrayList valueList = new java.util.ArrayList(objects.length);
				for (int i = 0; i < objects.length; ++i)
					if (objects[i] != null)
						valueList.add(this.java2value(objects[i], true, false, true));
				object = (org.irisa.triskell.MT.DataTypes.Java.Value[])valueList.toArray(new org.irisa.triskell.MT.DataTypes.Java.Value[valueList.size()]);
			} else if (isEList) 
						object = new IteratingFinalList(new EMF2JavaConverterIterator(((EList)object).iterator(), true, false));
					else
						object = new IteratingFinalList(new EMF2JavaConverterIterator(((java.util.Collection)object).iterator(), true, false));
			if (isOrdered && isUnique) {
				if (isArray)
					return new org.irisa.triskell.MT.DataTypes.Java.defaultImpl.OrderedSetValueImpl(false, null, (org.irisa.triskell.MT.DataTypes.Java.Value[])object, checkSet);
				else
					return new org.irisa.triskell.MT.DataTypes.Java.defaultImpl.OrderedSetValueImpl(false, null, (java.util.Collection)object, checkSet);
			} else if (isOrdered && !isUnique) {
				if (isArray)
					return new org.irisa.triskell.MT.DataTypes.Java.defaultImpl.SequenceValueImpl(false, null, (org.irisa.triskell.MT.DataTypes.Java.Value[])object);
				else
					return new org.irisa.triskell.MT.DataTypes.Java.defaultImpl.SequenceValueImpl(false, null, (java.util.Collection)object);
			} else if (!isOrdered && isUnique) {
				if (isArray)
					return new org.irisa.triskell.MT.DataTypes.Java.defaultImpl.SetValueImpl(false, null, (org.irisa.triskell.MT.DataTypes.Java.Value[])object, checkSet);
				else
					return new org.irisa.triskell.MT.DataTypes.Java.defaultImpl.SetValueImpl(false, null, (java.util.Collection)object, checkSet);
			} else /*if (!isOrdered && !isUnique)*/ {
				if (isArray)
					return new org.irisa.triskell.MT.DataTypes.Java.defaultImpl.BagValueImpl(false, null, (org.irisa.triskell.MT.DataTypes.Java.Value[])object);
				else
					return new org.irisa.triskell.MT.DataTypes.Java.defaultImpl.BagValueImpl(false, null, (java.util.Collection)object);
			}
		}

		if (object instanceof Throwable)
				return new EMFException(((Throwable)object).getMessage(), this);
		
		return new EMFException(object.toString() + " : unrecognized value.", this);
	}
    
	public class EMF2JavaConverterIterator implements Iterator {
		protected final Iterator delegate;
		protected final boolean isOrdered;
		protected final boolean isUnique;
    	
		public EMF2JavaConverterIterator (Iterator it, boolean isOrdered, boolean isUnique) {
			this.delegate = it;
			this.isOrdered = isOrdered;
			this.isUnique = isUnique;
		}

		public boolean hasNext() {
			return this.delegate.hasNext();
		}

		public Object next() {
			return EMFAPI.this.java2value(this.delegate.next(), this.isOrdered, this.isUnique, false);
		}

		public void remove() {
			this.delegate.remove();
		}

	}

}
