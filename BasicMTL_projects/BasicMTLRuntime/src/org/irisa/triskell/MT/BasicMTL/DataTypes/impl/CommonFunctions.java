/*
 * Created on 20 août 2003
 * $Id: CommonFunctions.java,v 1.5 2004-10-28 15:14:43 jpthibau Exp $
 * Authors : ffondeme
 * 
 * Copyright 2004 - INRIA - LGPL license
 */
package org.irisa.triskell.MT.BasicMTL.DataTypes.impl;

//import org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLBooleanInterface;
//import org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLCollectionInterface;
import org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLDataTypeInterface;
import org.irisa.triskell.MT.BasicMTL.TopTypes.BMTLInterface;
//import org.irisa.triskell.MT.BasicMTL.TopTypes.BMTLType;
import org.irisa.triskell.MT.DataTypes.Java.BooleanValue;
import org.irisa.triskell.MT.DataTypes.Java.CollectionKind;
import org.irisa.triskell.MT.DataTypes.Java.CollectionValue;
import org.irisa.triskell.MT.DataTypes.Java.EnumValue;
import org.irisa.triskell.MT.DataTypes.Java.IntegerValue;
import org.irisa.triskell.MT.DataTypes.Java.ModelElementValue;
import org.irisa.triskell.MT.DataTypes.Java.RepositoryAPIValue;
import org.irisa.triskell.MT.DataTypes.Java.NullValue;
import org.irisa.triskell.MT.DataTypes.Java.PrimitiveValue;
import org.irisa.triskell.MT.DataTypes.Java.RealValue;
import org.irisa.triskell.MT.DataTypes.Java.StringValue;
import org.irisa.triskell.MT.DataTypes.Java.TupleValue;
import org.irisa.triskell.MT.DataTypes.Java.TypeValue;
import org.irisa.triskell.MT.DataTypes.Java.Value;
import org.irisa.triskell.MT.DataTypes.Java.ValueVisitor;
import org.irisa.triskell.MT.DataTypes.Java.VoidValue;
import org.irisa.triskell.MT.DataTypes.Java.defaultImpl.IteratorValueImpl;
//import org.irisa.triskell.MT.repository.API.Java.API;
//import org.irisa.triskell.MT.repository.API.Java.MetaAssociation;
//import org.irisa.triskell.MT.repository.API.Java.MetaAssociationEnd;
import org.irisa.triskell.MT.repository.API.Java.ModelElement;
import org.irisa.triskell.MT.repository.API.Java.API;
//import org.irisa.triskell.MT.repository.API.Java.ModelRole;

/**
 * @author ffondeme
 * a set of tools functions to be used with the BMTL to Java wrapper 
 */
public class CommonFunctions {
	private static ToBMTLDataTypeVisitor toBMTLDataType = new ToBMTLDataTypeVisitor();
	
	public static Value toBMTLDataType (Value v) {
		if (v == null)
			return null;
		if (v instanceof BMTLDataTypeInterface)
			return v;
		if (v instanceof BMTLInterface)
			return v;
		synchronized (toBMTLDataType) {
			toBMTLDataType.distilled = null;
			v.accept(toBMTLDataType);
			return toBMTLDataType.distilled;
		}
	}
	
	public static Value toMTDataType (Value v) {
		if (v == null)
			return null;
		else if (v instanceof BMTLDataTypeInterface)
			return ((BMTLDataTypeInterface)v).getDelegate();
		else
			return v;
	}
}

/**
 * @author ffondeme
 * a set of tools functions to be used with the BMTL to Java wrapper 
 */
class ToBMTLDataTypeVisitor implements ValueVisitor {
	public Value distilled;
	
	public void visitBooleanValue(BooleanValue value) {
		this.distilled = new BMTLBoolean(value);
	}

	public void visitCollectionValue(CollectionValue value) {
		CollectionKind kind = value.getKind();
		if (kind.equals(CollectionKind.getSet_kind()))
			this.distilled = new BMTLSet(value);
		else if (kind.equals(CollectionKind.getBag_kind()))
			this.distilled = new BMTLBag(value);
		else if (kind.equals(CollectionKind.getSequence_kind()))
			this.distilled = new BMTLSequence(value);
		else if (kind.equals(CollectionKind.getOrdered_set_kind()))
			this.distilled = new BMTLOrderedSet(value);
		else
			throw new IllegalArgumentException("Unrecognized collection type.");
	}

	public void visitEnumValue(EnumValue value) {
		this.distilled = new BMTLEnum(value);
	}

	public void visitIntegerValue(IntegerValue value) {
		this.distilled = new BMTLInteger(value);
	}

	public void visitModelElementValue(ModelElementValue value) {
		this.distilled = new BMTLModelElement((ModelElement)value);
	}

	public void visitRepositoryAPIValue(RepositoryAPIValue value) {
		this.distilled = new BMTLRepositoryAPI((API)value);
	}

	public void visitNullValue(NullValue value) {
		this.distilled = null;
	}

	public void visitPrimitiveValue(PrimitiveValue value) {
		throw new IllegalArgumentException("Unrecognized type: " + value.toString() + " of type " + value.getType().getName());
	}

	public void visitRealValue(RealValue value) {
		this.distilled = new BMTLReal(value);
	}

	public void visitStringValue(StringValue value) {
		this.distilled = new BMTLString(value);
	}

	public void visitTupleValue(TupleValue value) {
		this.distilled = new BMTLTuple(value);
	}

	public void visitTypeValue(TypeValue value) {
		this.distilled = new BMTLOclType(value);
	}

	public void visitValue(Value value) {
		if (value instanceof IteratorValueImpl)
			this.distilled = new BMTLIterator((IteratorValueImpl)value);
		else
			this.distilled = new BMTLOclAny(value);
	}

	public void visitVoidValue(VoidValue value) {
		if (BMTLVoid.TheInstance.getVoidDelegate() == value)
			this.distilled = BMTLVoid.TheInstance;
		else
			this.distilled = new BMTLVoid(value);
	}
}

