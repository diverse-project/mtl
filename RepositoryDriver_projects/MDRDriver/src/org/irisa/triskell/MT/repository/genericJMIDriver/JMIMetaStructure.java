/*
 * $Id: JMIMetaStructure.java,v 1.1 2004-02-16 15:44:32 dvojtise Exp $
 * Authors : ffondeme dvojtise
 */
package org.irisa.triskell.MT.repository.genericJMIDriver;

import java.util.ArrayList;
import java.util.Collection;
// import java.util.Iterator;

import javax.jmi.model.StructureField;
import javax.jmi.model.StructureType;
import javax.jmi.reflect.RefClass;
import javax.jmi.reflect.RefPackage;

// import org.irisa.triskell.MT.DataTypes.Java.CollectionKind;
import org.irisa.triskell.MT.DataTypes.Java.CollectionValue;
import org.irisa.triskell.MT.DataTypes.Java.Type;
import org.irisa.triskell.MT.DataTypes.Java.TypeValue;
import org.irisa.triskell.MT.DataTypes.Java.Value;
import org.irisa.triskell.MT.DataTypes.Java.ValueVisitor;
import org.irisa.triskell.MT.DataTypes.Java.commands.MultipleCommandException;
import org.irisa.triskell.MT.DataTypes.Java.commands.UnknownCommandException;
import org.irisa.triskell.MT.DataTypes.Java.commands.ModelElement.ModelElementType;
import org.irisa.triskell.MT.DataTypes.Java.commands.OclAny.OclAnyCommandGroup;
import org.irisa.triskell.MT.DataTypes.Java.commands.Tuple.TupleType;
import org.irisa.triskell.MT.repository.API.Java.CommonException;
// import org.irisa.triskell.MT.repository.API.Java.LookupConstraint;
import org.irisa.triskell.MT.repository.API.Java.MetaAttribute;
import org.irisa.triskell.MT.repository.API.Java.MetaClass;
import org.irisa.triskell.MT.repository.API.Java.MetaFeature;
import org.irisa.triskell.MT.repository.API.Java.MetaOperation;
import org.irisa.triskell.MT.repository.API.Java.ModelElement;
import org.irisa.triskell.MT.repository.API.Java.ModelElementIterator;
import org.irisa.triskell.MT.repository.API.Java.UnknownElementException;

/**
 * Generic implementation of the repository API (org.irisa.triskell.MT.repository.API.Java.API)
 * This serve as the base for all Driver that uses JMI to connect to the repository 
 */
public class JMIMetaStructure
	extends org.irisa.triskell.MT.repository.genericJMIDriver.JMIMetaType
	implements MetaClass, ModelElement,TypeValue {
	private final javax.jmi.reflect.RefClass classContainer;

	private final javax.jmi.reflect.RefPackage packageContainer;

	private final javax.jmi.model.StructureType refMetaObject;

	private transient javax.jmi.model.StructureField[] fields = null;

	protected Collection getTupleParts() {
		StructureField[] fields = this.getStructureFields();
		Collection ret = new ArrayList(fields.length);
		for (int i = 0; i < fields.length; ++i) {
			ret.add(
				new TupleType.TupleTypePart(
					fields[i].getName(),
					this.getSpecificAPI().getTypeOf(
						fields[i].getType(),
						null)));
		}
		return ret;
	}
	private class JMIStructType extends TupleType {

		public JMIStructType() {
			super(
				JMIMetaStructure.this.getQualifiedName(),
				new Type[] { ModelElementType.TheInstance },
				JMIMetaStructure.this.getTupleParts());
		}

		public boolean isKindOfInternal(Value v) {
			return (v instanceof JMIStruct)
				&& ((JMIStruct) v).isKindOf(JMIMetaStructure.this);
		}

		public boolean equals(Type parentType) {
			return parentType == this
				|| ((parentType instanceof JMIStructType)
					&& JMIMetaStructure.this.equals(
						((JMIStructType) parentType).getOwner()));
		}

		public CollectionValue allInstances() throws Exception {
			return JMIMetaStructure.this.allInstances();
		}

		protected final JMIMetaStructure getOwner() {
			return JMIMetaStructure.this;
		}

		public boolean conformsTo(Type type)
			throws UnsupportedOperationException {
			return super.equals(type)
				|| ModelElementType.TheInstance.conformsTo(type);
		}

	}
	private transient JMIStructType structType = null;
	public JMIStructType getStructType() {
		if (this.structType == null)
			this.structType = new JMIStructType();
		return this.structType;
	}

	public Type getType() {
		return this.structType;
	}

	public JMIMetaStructure(JMIAPI api,StructureType metaObject,RefPackage packageContainer,RefClass classContainer) {
		super(api, metaObject, null, retreiveQualifiedName(metaObject));
		this.refMetaObject = metaObject;
		this.packageContainer = packageContainer;
		this.classContainer = classContainer;
	}

	public JMIMetaStructure(JMIAPI api,StructureType metaObject,RefPackage packageContainer) {
		this(api, metaObject, packageContainer, null);
	}

	public JMIMetaStructure(JMIAPI api, StructureType metaObject, RefClass classContainer) {
		this(api, metaObject, null, classContainer);
	}

	public boolean isUndefined() {
		return false;
	}

	public org.irisa.triskell.MT.DataTypes.Java.Value invoke(
		String[] scopeQualifiedName,
		String name,
		org.irisa.triskell.MT.DataTypes.Java.Value[] arguments,
		String[] discriminants)
		throws UnknownCommandException, MultipleCommandException {
		return OclAnyCommandGroup.TheInstance.invoke(scopeQualifiedName,this,name,arguments,discriminants);
	}

	public String getErrorMessage() {
		return null;
	}

	public boolean equals(Value rhs) {
		return (this == rhs)
			|| ((rhs instanceof JMIMetaStructure)
				&& (this.refMetaObject.equals(((JMIMetaStructure) rhs).refMetaObject)));
	}

	public void accept(ValueVisitor visitor) {
		visitor.visitModelElementValue(this);
	}

	public String getTheModelElement() {
		return this.refMetaObject.refMofId();
	}

	public void setAttributeValue(ModelElement contextualElement, MetaAttribute argument, Value value)
		throws org.irisa.triskell.MT.repository.API.Java.UnknownElementException {
		throw new UnknownElementException(argument);
	}

	public boolean isTypeOf(MetaClass classifier) {
		return this.equals(classifier);
	}

	public boolean isMetaObject() {
		return true;
	}

	public boolean isKindOf(MetaClass classifier) {
		return this.isTypeOf(classifier);
	}

	public Value invokeQueryOperation(ModelElement contextualElement, MetaOperation feature, Value[] arguments)
		throws UnknownElementException {
		throw new UnknownElementException(feature);
	}

	public String getUniqId() {
		return this.refMetaObject.refMofId();
	}

	public org.irisa.triskell.MT.DataTypes.Java.Value getFeatureValue(ModelElement contextualElement, MetaFeature feature, Value[] arguments)
		throws UnknownElementException {
		throw new UnknownElementException(feature);
	}

	public ModelElement getMetaObject() {
		return this;
	}

	public ModelElementIterator allInstancesIterator() {
		return new JMIModelElementIterator(
			this.getSpecificAPI(),
			new java.util.ArrayList(0));
	}

	public String getName() {
		return this.refMetaObject.getName();
	}

	public String toString() {
		return "structure "
			+ org.irisa.triskell.MT.utils.Java.AWK.merge(
				this.getQualifiedName(),
				Type.PackageIndirection);
	}

	public void delete() {}
	
	public void deleteTheModelElement () {
		this.delete();
	}

	public ModelElement instanciate(ModelElement contextualElement, Value[] arguments)
		throws UnknownElementException, CommonException,
			org.irisa.triskell.MT.repository.API.Java.IllegalAccessException {
		try {
			java.util.List javaArguments =
				new java.util.ArrayList(arguments.length);
			for (int i = 0; i < arguments.length; ++i)
				javaArguments.add(
					this.getSpecificAPI().value2java(
						arguments[i],
						false,
						false));
			if (this.packageContainer != null)
				return this.getSpecificAPI().getStruct(
					this.packageContainer.refCreateStruct(
						this.refMetaObject,
						javaArguments));
			else
				return this.getSpecificAPI().getStruct(
					this.classContainer.refCreateStruct(
						this.refMetaObject,
						javaArguments));
		} catch (Exception x) {
			return new JMIException(x.getMessage(), this.getSpecificAPI());
		}
	}

	public StructureType getStructureType() {
		return this.refMetaObject;
	}

	public StructureField[] getStructureFields() {
		if (this.fields == null) {
			java.util.List ret =
				new java.util.ArrayList(this.getStructureType().getContents());
			java.util.Iterator it = ret.iterator();
			Object o;
			while (it.hasNext()) {
				o = it.next();
				if (!(o instanceof StructureField))
					it.remove();
			}
			this.fields =
				(StructureField[]) ret.toArray(new StructureField[ret.size()]);
		}
		return this.fields;
	}

	public StructureField getStructureField(String name) {
		StructureField[] fields = this.getStructureFields();
		for (int i = 0; i < fields.length; ++i)
			if (fields[i].getName().equals(name))
				return (fields[i]);
		return null;
	}

	public static String[] retreiveQualifiedName(
		javax.jmi.model.StructureType ref) {
		java.util.Collection qualifiedNameAsCollection = ref.getQualifiedName();
		String[] ret = new String[qualifiedNameAsCollection.size()];
		qualifiedNameAsCollection.toArray(ret);
		return ret;
	}

	public boolean conformsTo(Type parentType) {
		return this.getStructType().conformsTo(parentType);
	}

	public boolean isKindOf(Value v) {
		return (v instanceof JMIStruct) && ((JMIStruct) v).isKindOf(this);
	}

	public boolean isTypeOf(Value v) {
		return (v instanceof JMIStruct) && ((JMIStruct) v).isTypeOf(this);
	}

	/* (non-Javadoc)
	 * @see org.irisa.triskell.MT.DataTypes.Java.TypeValue#getTheType()
	 */
	public Type getTheType() {
		return this;
	}

	/* (non-Javadoc)
	 * @see org.irisa.triskell.MT.DataTypes.Java.PrimitiveValue#getValue()
	 */
	public String getValue() {
		return this.getQualifiedNameAsString();
	}

}
