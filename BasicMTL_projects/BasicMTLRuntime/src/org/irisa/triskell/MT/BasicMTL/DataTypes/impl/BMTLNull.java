/*
 * Created on 20 août 2003
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package org.irisa.triskell.MT.BasicMTL.DataTypes.impl;

import java.util.Arrays;

import org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLBooleanInterface;
import org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLIntegerInterface;
import org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOclAnyInterface;
import org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOclTypeInterface;
import org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface;
import org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLRealInterface;
import org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLSetInterface;
import org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface;
import org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface;
import org.irisa.triskell.MT.DataTypes.Java.BooleanValue;
import org.irisa.triskell.MT.DataTypes.Java.CollectionKind;
import org.irisa.triskell.MT.DataTypes.Java.CollectionValue;
import org.irisa.triskell.MT.DataTypes.Java.EnumValue;
import org.irisa.triskell.MT.DataTypes.Java.IntegerValue;
import org.irisa.triskell.MT.DataTypes.Java.NullValue;
import org.irisa.triskell.MT.DataTypes.Java.RealValue;
import org.irisa.triskell.MT.DataTypes.Java.StringValue;
import org.irisa.triskell.MT.DataTypes.Java.TupleValue;
import org.irisa.triskell.MT.DataTypes.Java.Type;
import org.irisa.triskell.MT.DataTypes.Java.TypeValue;
import org.irisa.triskell.MT.DataTypes.Java.Value;
import org.irisa.triskell.MT.DataTypes.Java.ValueVisitor;
import org.irisa.triskell.MT.DataTypes.Java.VoidValue;
import org.irisa.triskell.MT.DataTypes.Java.commands.MultipleCommandException;
import org.irisa.triskell.MT.DataTypes.Java.commands.UnknownCommandException;
import org.irisa.triskell.MT.DataTypes.Java.commands.OclAny.OclAny_toErr;
import org.irisa.triskell.MT.DataTypes.Java.commands.OclAny.OclAny_toOut;
import org.irisa.triskell.MT.DataTypes.Java.defaultImpl.BooleanValueImpl;
import org.irisa.triskell.MT.DataTypes.Java.defaultImpl.CollectionValueImpl;
import org.irisa.triskell.MT.DataTypes.Java.defaultImpl.IteratorValueImpl;
import org.irisa.triskell.MT.DataTypes.Java.defaultImpl.NullValueImpl;
import org.irisa.triskell.MT.DataTypes.Java.defaultImpl.SetValueImpl;
import org.irisa.triskell.MT.DataTypes.Java.defaultImpl.UndefinedValueImpl;
import org.irisa.triskell.MT.repository.API.Java.API;
import org.irisa.triskell.MT.repository.API.Java.CommonException;
import org.irisa.triskell.MT.repository.API.Java.IllegalAccessException;
import org.irisa.triskell.MT.repository.API.Java.IsQueryException;
import org.irisa.triskell.MT.repository.API.Java.MetaAttribute;
import org.irisa.triskell.MT.repository.API.Java.MetaClass;
import org.irisa.triskell.MT.repository.API.Java.MetaFeature;
import org.irisa.triskell.MT.repository.API.Java.MetaOperation;
import org.irisa.triskell.MT.repository.API.Java.ModelElement;
import org.irisa.triskell.MT.repository.API.Java.UnknownElementException;

/**
 * @author ffondeme
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class BMTLNull extends BMTLDataType implements BMTLNullInterface {
	public static final String NullPointerMessage = "Null pointer exception.";
	
	public static final  BMTLNull TheInstance = new BMTLNull(NullValueImpl.getTheInstance());
	
	public static final BMTLNull NullPointer = new BMTLNull(new UndefinedValueImpl(NullPointerMessage));

	private IteratorValueImpl iteratorDelegate;
	
	private BMTLNull(Value delegate) {
		super(delegate);
		iteratorDelegate = new IteratorValueImpl(delegate.isUndefined(), delegate.getErrorMessage(), new SetValueImpl(delegate.isUndefined(), delegate.getErrorMessage(), new Value [0], false));
	}

	public BooleanValue getBooleanDelegate() {
		return NullPointer;
	}

	public BMTLBooleanInterface BMTL_not() {
		return NullPointer;
	}

	public CollectionValue getCollectionDelegate() {
		return this;
	}

	public Value getDelegate() {
		return NullPointer;
	}

	public EnumValue getEnumDelegate() {
		return this;
	}

	public IntegerValue getIntegerDelegate() {
		return this;
	}

	public IteratorValueImpl getIteratorDelegate() {
		return iteratorDelegate;
	}

	public Type getMetaClass(String[] qualifiedName) {
		return this.getDelegate().getType();
	}

	public ModelElement getModelElementDelegate() {
		return this;
	}

	public BMTLVoidInterface BMTL_delete() {
		return NullPointer;
	}

	public BMTLStringInterface BMTL_oclUid() {
		return NullPointer;
	}

	public void delete() {
	}

	public Value getOclAnyDelegate() {
		return this;
	}

	public BMTLBooleanInterface BMTL__3d(Value rhs) {
		return this.equals(rhs) ? BMTLBoolean.TRUE : BMTLBoolean.FALSE;
	}

	public BMTLBooleanInterface BMTL__3c_3e(Value rhs) {
		return this.BMTL__3d(rhs).BMTL_not();
	}

	public BMTLBooleanInterface BMTL_oclIsUndefined() {
		return NullPointer;
	}

	public BMTLBooleanInterface BMTL_oclIsTypeOf(TypeValue type) {
		return NullPointer;
	}

	public BMTLBooleanInterface BMTL_oclIsKindOf(TypeValue type) {
		return NullPointer;
	}

	public TypeValue getOclTypeDelegate() {
		return this;
	}

	public BMTLSetInterface BMTL_allInstances() {
		return NullPointer;
	}

	public BMTLOrderedSetInterface BMTL_oclQualifiedName() {
		return NullPointer;
	}

	public BMTLStringInterface BMTL_oclName() {
		return NullPointer;
	}

	public RealValue getRealDelegate() {
		return NullPointer;
	}

	public StringValue getStringDelegate() {
		return this;
	}

	public TupleValue getTupleDelegate() {
		return this;
	}

	public Value[] getTheCollection() {
		return new Value [0];
	}

	public CollectionKind getKind() {
		return CollectionKind.getSet_kind();
	}

	public boolean isUndefined() {
		return this.getDelegate().isUndefined();
	}

	public String getErrorMessage() {
		return this.getDelegate().getErrorMessage();
	}

	public boolean equals(Value rhs) {
		return (rhs instanceof NullValue) && (this.isUndefined() == rhs.isUndefined()) && this.getErrorMessage().equals(rhs.getErrorMessage());
	}

	public Value invoke(
		String[] scopeQualifiedName,
		String name,
		Value[] arguments,
		String[] discriminants)
		throws UnknownCommandException, MultipleCommandException {
		return this.getDelegate().invoke(scopeQualifiedName, name, arguments, discriminants);
	}

	public void accept(ValueVisitor visitor) {
		visitor.visitNullValue(this);
	}

	public Type getType() {
		return this.getDelegate().getType();
	}

	public boolean getTheBoolean() {
		return false;
	}

	public String getValue() {
		return "";
	}

	public String getTheEnum() {
		return "";
	}

	public String[] getEnumeration() {
		return new String [0];
	}

	public int getTheInteger() {
		return 0;
	}

	public float getTheReal() {
		return 0;
	}

	public boolean isMetaObject() {
		return false;
	}

	public boolean isTypeOf(MetaClass classifier) {
		return true;
	}

	public boolean isKindOf(MetaClass classifier) {
		return true;
	}

	public Value getFeatureValue(
		ModelElement contextualElement,
		MetaFeature feature,
		Value[] arguments)
		throws UnknownElementException, IllegalAccessException, CommonException {
		return NullPointer;
	}

	public void setAttributeValue(
		ModelElement contextualElement,
		MetaAttribute argument,
		Value value)
		throws UnknownElementException, CommonException, IllegalAccessException {
		throw new CommonException(NullPointerMessage);
	}

	public Value invokeQueryOperation(
		ModelElement contextualElement,
		MetaOperation feature,
		Value[] arguments)
		throws
			UnknownElementException,
			IllegalAccessException,
			CommonException,
			IsQueryException {
		throw new CommonException(NullPointerMessage);
	}

	public String getUniqId() {
		return "";
	}

	public API getAPI() {
		return null;
	}

	public String getTheModelElement() {
		return "";
	}

	public void deleteTheModelElement() throws Exception {
		this.delete();
	}

	public Type getTheType() {
		return null;
	}

	public String getTheString() {
		return "";
	}

	public String[] getPartNames() {
		return new String [0];
	}

	public Value getPart(String partName) {
		return NullPointer;
	}

	public VoidValue getVoidDelegate() {
		return this;
	}

	public BMTLBooleanInterface BMTL_and(BooleanValue rhs) {
		return NullPointer;
	}

	public BMTLBooleanInterface BMTL_implies(BooleanValue rhs) {
		return NullPointer;
	}

	public BMTLBooleanInterface BMTL_or(BooleanValue rhs) {
		return NullPointer;
	}
	
	public BMTLRealInterface BMTL__2a(RealValue r) {
		return NullPointer;
	}

	public BMTLRealInterface BMTL__2b(RealValue r) {
		return NullPointer;
	}

	public BMTLRealInterface BMTL__2d(RealValue r) {
		return NullPointer;
	}

	public BMTLRealInterface BMTL__2f(RealValue r) {
		return NullPointer;
	}

	public BMTLBooleanInterface BMTL__3c_3d(RealValue r) {
		return NullPointer;
	}

	public BMTLBooleanInterface BMTL__3c(RealValue r) {
		return NullPointer;
	}

	public BMTLBooleanInterface BMTL__3e_3d(RealValue r) {
		return NullPointer;
	}

	public BMTLBooleanInterface BMTL__3e(RealValue r) {
		return NullPointer;
	}

	public BMTLRealInterface BMTL_abs() {
		return NullPointer;
	}

	public BMTLIntegerInterface BMTL_floor() {
		return NullPointer;
	}

	public BMTLRealInterface BMTL_max(RealValue r) {
		return NullPointer;
	}

	public BMTLRealInterface BMTL_min(RealValue r) {
		return NullPointer;
	}

	public BMTLIntegerInterface BMTL__round() {
		return NullPointer;
	}

	public BMTLIntegerInterface BMTL_round() {
		return NullPointer;
	}

	public BMTLStringInterface BMTL_toString() {
		return NullPointer;
	}

	public BMTLRealInterface BMTL__2d() {
		return NullPointer;
	}

	public BMTLIntegerInterface BMTL__2a(IntegerValue i) {
		return NullPointer;
	}

	public BMTLIntegerInterface BMTL__2b(IntegerValue i) {
		return NullPointer;
	}

	public BMTLIntegerInterface BMTL__2d(IntegerValue i) {
		return NullPointer;
	}

	public BMTLIntegerInterface BMTL_div(IntegerValue i) {
		return NullPointer;
	}

	public BMTLIntegerInterface BMTL_max(IntegerValue i) {
		return NullPointer;
	}

	public BMTLIntegerInterface BMTL_min(IntegerValue i) {
		return NullPointer;
	}

	public BMTLIntegerInterface BMTL_mod(IntegerValue i) {
		return NullPointer;
	}

	public BMTLBooleanInterface BMTL_xor(BooleanValue rhs) {
		return NullPointer;
	}

	public BMTLStringInterface BMTL_concat(BMTLStringInterface s) {
		return NullPointer;
	}

	public BMTLIntegerInterface BMTL_size() {
		return NullPointer;
	}

	public BMTLStringInterface BMTL_substring(
		BMTLIntegerInterface lower,
		BMTLIntegerInterface upper) {
		return NullPointer;
	}

	public BMTLIntegerInterface BMTL_toInteger() {
		return NullPointer;
	}

	public BMTLRealInterface BMTL_toReal() {
		return NullPointer;
	}

	public BMTLVoidInterface BMTL_toErr() {
		OclAny_toErr.TheInstance.invoke(this,null);
		return BMTLVoid.TheInstance;
	}

	public BMTLVoidInterface BMTL_toOut() {
		OclAny_toOut.TheInstance.invoke(this,null);
		return BMTLVoid.TheInstance;
	}

}
