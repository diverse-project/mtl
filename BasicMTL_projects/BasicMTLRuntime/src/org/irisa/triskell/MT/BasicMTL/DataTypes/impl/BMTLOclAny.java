/*
 * Created on 19 août 2003
 * $Id: BMTLOclAny.java,v 1.4 2004-02-17 08:58:25 dvojtise Exp $
 * Authors : ffondeme
 * 
 * Copyright 2004 - INRIA - LGPL license
 */
package org.irisa.triskell.MT.BasicMTL.DataTypes.impl;

import org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLBooleanInterface;
import org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLDataTypeInterface;
import org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOclAnyInterface;
//import org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOclTypeInterface;
import org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface;
//import org.irisa.triskell.MT.DataTypes.Java.BooleanValue;
import org.irisa.triskell.MT.DataTypes.Java.Type;
import org.irisa.triskell.MT.DataTypes.Java.TypeValue;
import org.irisa.triskell.MT.DataTypes.Java.Value;
import org.irisa.triskell.MT.DataTypes.Java.ValueVisitor;
import org.irisa.triskell.MT.DataTypes.Java.commands.MultipleCommandException;
import org.irisa.triskell.MT.DataTypes.Java.commands.UnknownCommandException;
import org.irisa.triskell.MT.DataTypes.Java.commands.OclAny.OclAny_equals;
import org.irisa.triskell.MT.DataTypes.Java.commands.OclAny.OclAny_isUndefined;
import org.irisa.triskell.MT.DataTypes.Java.commands.OclAny.OclAny_notEquals;
import org.irisa.triskell.MT.DataTypes.Java.commands.OclAny.OclAny_oclIsKindOf;
import org.irisa.triskell.MT.DataTypes.Java.commands.OclAny.OclAny_oclIsTypeOf;
import org.irisa.triskell.MT.DataTypes.Java.commands.OclAny.OclAny_toErr;
import org.irisa.triskell.MT.DataTypes.Java.commands.OclAny.OclAny_toOut;

/**
 * @author ffondeme
 * Implementation of the OclAny data type for BasicMTL. It does the wrapping between BasicMTL and MTDataType impoementation (which use commands)
 */
public class BMTLOclAny extends BMTLDataType implements BMTLOclAnyInterface, Value {

	public BMTLOclAny(Value delegate) {
		super(delegate);
	}

	public Value getOclAnyDelegate() {
		return this.getDelegate();
	}

	public BMTLBooleanInterface BMTL__3d(Value rhs) {
		return (BMTLBooleanInterface)CommonFunctions.toBMTLDataType(OclAny_equals.TheInstance.invoke(this.getOclAnyDelegate(), new Value [] {CommonFunctions.toMTDataType(rhs)}));
	}

	public BMTLBooleanInterface BMTL__3c_3e(Value rhs) {
		return (BMTLBooleanInterface)CommonFunctions.toBMTLDataType(OclAny_notEquals.TheInstance.invoke(this.getOclAnyDelegate(), new Value [] {CommonFunctions.toMTDataType(rhs)}));
	}

	public BMTLBooleanInterface BMTL_oclIsUndefined() {
		return (BMTLBooleanInterface)CommonFunctions.toBMTLDataType(OclAny_isUndefined.TheInstance.invoke(this.getOclAnyDelegate(), null));
	}

	public BMTLBooleanInterface BMTL_oclIsTypeOf(TypeValue type) {
		return (BMTLBooleanInterface)CommonFunctions.toBMTLDataType(OclAny_oclIsTypeOf.TheInstance.invoke(this.getOclAnyDelegate(), new Value [] {CommonFunctions.toMTDataType(type)}));
	}

	public BMTLBooleanInterface BMTL_oclIsKindOf(TypeValue type) {
		return (BMTLBooleanInterface)CommonFunctions.toBMTLDataType(OclAny_oclIsKindOf.TheInstance.invoke(this.getOclAnyDelegate(), new Value [] {CommonFunctions.toMTDataType(type)}));
	}

	public BMTLVoidInterface BMTL_toErr() {
		return (BMTLVoidInterface)CommonFunctions.toBMTLDataType(OclAny_toErr.TheInstance.invoke(this.getOclAnyDelegate(),null));
	}

	public BMTLVoidInterface BMTL_toOut() {
		return (BMTLVoidInterface)CommonFunctions.toBMTLDataType(OclAny_toOut.TheInstance.invoke(this.getOclAnyDelegate(),null));
	}

	public boolean isUndefined() {
		return this.getOclAnyDelegate().isUndefined();
	}

	public String getErrorMessage() {
		return this.getOclAnyDelegate().getErrorMessage();
	}

	public boolean equals(Value rhs) {
		return this.getOclAnyDelegate().equals((rhs instanceof BMTLDataTypeInterface) ? ((BMTLDataTypeInterface)rhs).getDelegate() : rhs);
	}

	public Value invoke(
		String[] scopeQualifiedName,
		String name,
		Value[] arguments,
		String[] discriminants)
		throws UnknownCommandException, MultipleCommandException {
		return this.getOclAnyDelegate().invoke(scopeQualifiedName, name, arguments, discriminants);
	}
	
	public void accept(ValueVisitor visitor) {
		this.getOclAnyDelegate().accept(visitor);
	}

	public Type getType() {
		return this.getOclAnyDelegate().getType();
	}

}
