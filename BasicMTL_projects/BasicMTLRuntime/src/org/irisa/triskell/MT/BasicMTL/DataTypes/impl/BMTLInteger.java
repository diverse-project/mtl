/*
 * Created on 20 août 2003
 * $Id: BMTLInteger.java,v 1.3 2004-02-17 08:58:24 dvojtise Exp $
 * Authors : ffondeme
 * 
 * Copyright 2004 - INRIA - LGPL license
 */
package org.irisa.triskell.MT.BasicMTL.DataTypes.impl;

import org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLIntegerInterface;
import org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLRealInterface;
//import org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface;
import org.irisa.triskell.MT.DataTypes.Java.IntegerValue;
//import org.irisa.triskell.MT.DataTypes.Java.RealValue;
import org.irisa.triskell.MT.DataTypes.Java.Value;
import org.irisa.triskell.MT.DataTypes.Java.commands.Integer.Integer_add;
import org.irisa.triskell.MT.DataTypes.Java.commands.Integer.Integer_idiv;
import org.irisa.triskell.MT.DataTypes.Java.commands.Integer.Integer_mod;
import org.irisa.triskell.MT.DataTypes.Java.commands.Integer.Integer_mul;
import org.irisa.triskell.MT.DataTypes.Java.commands.Integer.Integer_sub;
import org.irisa.triskell.MT.DataTypes.Java.commands.Integer.Integer_unaryMinus;
import org.irisa.triskell.MT.DataTypes.Java.commands.Real.Real_max;
import org.irisa.triskell.MT.DataTypes.Java.commands.Real.Real_min;
import org.irisa.triskell.MT.DataTypes.Java.defaultImpl.IntegerValueImpl;

/**
 * @author ffondeme
 * Implementation of the Integer data type for BasicMTL. It does the wrapping between BasicMTL and MTDataType impoementation (which use commands)
 */
public class BMTLInteger extends BMTLReal implements BMTLIntegerInterface {

	public BMTLInteger(int value) {
		this(new IntegerValueImpl(false, null, value));
	}
	
	public BMTLInteger(IntegerValue delegate) {
		super(delegate);
	}

	public IntegerValue getIntegerDelegate() {
		return (IntegerValue)this.getDelegate();
	}

	public int getTheInteger() {
		return this.getIntegerDelegate().getTheInteger();
	}

	//*
	public BMTLIntegerInterface BMTL__2a(IntegerValue i) {
		return (BMTLIntegerInterface)CommonFunctions.toBMTLDataType(Integer_mul.TheInstance.invoke(this.getIntegerDelegate(), new Value [] {CommonFunctions.toMTDataType(i)}));
	}

	//+
	public BMTLIntegerInterface BMTL__2b(IntegerValue i) {
		return (BMTLIntegerInterface)CommonFunctions.toBMTLDataType(Integer_add.TheInstance.invoke(this.getIntegerDelegate(), new Value [] {CommonFunctions.toMTDataType(i)}));
	}

	//-
	public BMTLIntegerInterface BMTL__2d(IntegerValue i) {
		return (BMTLIntegerInterface)CommonFunctions.toBMTLDataType(Integer_sub.TheInstance.invoke(this.getIntegerDelegate(), new Value [] {CommonFunctions.toMTDataType(i)}));
	}

	public BMTLIntegerInterface BMTL_div(IntegerValue i) {
		return (BMTLIntegerInterface)CommonFunctions.toBMTLDataType(Integer_idiv.TheInstance.invoke(this.getIntegerDelegate(), new Value [] {CommonFunctions.toMTDataType(i)}));
	}

	public BMTLIntegerInterface BMTL_max(IntegerValue i) {
		return (BMTLIntegerInterface)CommonFunctions.toBMTLDataType(Real_max.TheInstance.invoke(this.getIntegerDelegate(), new Value [] {CommonFunctions.toMTDataType(i)}));
	}

	public BMTLIntegerInterface BMTL_min(IntegerValue i) {
		return (BMTLIntegerInterface)CommonFunctions.toBMTLDataType(Real_min.TheInstance.invoke(this.getIntegerDelegate(), new Value [] {CommonFunctions.toMTDataType(i)}));
	}

	public BMTLIntegerInterface BMTL_mod(IntegerValue i) {
		return (BMTLIntegerInterface)CommonFunctions.toBMTLDataType(Integer_mod.TheInstance.invoke(this.getIntegerDelegate(), new Value [] {CommonFunctions.toMTDataType(i)}));
	}

	//-
	public BMTLRealInterface BMTL__2d() {
		return (BMTLIntegerInterface)CommonFunctions.toBMTLDataType(Integer_unaryMinus.TheInstance.invoke(this.getIntegerDelegate(), null));
	}

}
