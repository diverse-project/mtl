/*
 * Created on 20 août 2003
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package org.irisa.triskell.MT.BasicMTL.DataTypes.impl;

import org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLBooleanInterface;
import org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLIntegerInterface;
import org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLRealInterface;
import org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface;
import org.irisa.triskell.MT.DataTypes.Java.RealValue;
import org.irisa.triskell.MT.DataTypes.Java.Value;
import org.irisa.triskell.MT.DataTypes.Java.commands.Real.Real_abs;
import org.irisa.triskell.MT.DataTypes.Java.commands.Real.Real_add;
import org.irisa.triskell.MT.DataTypes.Java.commands.Real.Real_div;
import org.irisa.triskell.MT.DataTypes.Java.commands.Real.Real_floor;
import org.irisa.triskell.MT.DataTypes.Java.commands.Real.Real_ge;
import org.irisa.triskell.MT.DataTypes.Java.commands.Real.Real_gt;
import org.irisa.triskell.MT.DataTypes.Java.commands.Real.Real_le;
import org.irisa.triskell.MT.DataTypes.Java.commands.Real.Real_lt;
import org.irisa.triskell.MT.DataTypes.Java.commands.Real.Real_max;
import org.irisa.triskell.MT.DataTypes.Java.commands.Real.Real_min;
import org.irisa.triskell.MT.DataTypes.Java.commands.Real.Real_mul;
import org.irisa.triskell.MT.DataTypes.Java.commands.Real.Real_round;
import org.irisa.triskell.MT.DataTypes.Java.commands.Real.Real_sub;
import org.irisa.triskell.MT.DataTypes.Java.commands.Real.Real_toString;
import org.irisa.triskell.MT.DataTypes.Java.commands.Real.Real_unaryMinus;
import org.irisa.triskell.MT.DataTypes.Java.defaultImpl.RealValueImpl;

/**
 * @author ffondeme
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class BMTLReal extends BMTLOclAny implements BMTLRealInterface {
	
	public BMTLReal(double value) {
		this(new RealValueImpl(false, null, (float)value));
	}

	public BMTLReal(RealValue delegate) {
		super(delegate);
	}

	public RealValue getRealDelegate() {
		return (RealValue)this.getDelegate();
	}

	public float getTheReal() {
		return this.getRealDelegate().getTheReal();
	}

	public String getValue() {
		return this.getRealDelegate().getValue();
	}

	//*
	public BMTLRealInterface BMTL__2a(RealValue r) {
		return (BMTLRealInterface)CommonFunctions.toBMTLDataType(Real_mul.TheInstance.invoke(this.getRealDelegate(), new Value [] {CommonFunctions.toMTDataType(r)}));
	}

	//+
	public BMTLRealInterface BMTL__2b(RealValue r) {
		return (BMTLRealInterface)CommonFunctions.toBMTLDataType(Real_add.TheInstance.invoke(this.getRealDelegate(), new Value [] {CommonFunctions.toMTDataType(r)}));
	}

	//-
	public BMTLRealInterface BMTL__2d(RealValue r) {
		return (BMTLRealInterface)CommonFunctions.toBMTLDataType(Real_sub.TheInstance.invoke(this.getRealDelegate(), new Value [] {CommonFunctions.toMTDataType(r)}));
	}

	///
	public BMTLRealInterface BMTL__2f(RealValue r) {
		return (BMTLRealInterface)CommonFunctions.toBMTLDataType(Real_div.TheInstance.invoke(this.getRealDelegate(), new Value [] {CommonFunctions.toMTDataType(r)}));
	}

	//<=
	public BMTLBooleanInterface BMTL__3c_3d(RealValue r) {
		return (BMTLBooleanInterface)CommonFunctions.toBMTLDataType(Real_le.TheInstance.invoke(this.getRealDelegate(), new Value [] {CommonFunctions.toMTDataType(r)}));
	}

	//<
	public BMTLBooleanInterface BMTL__3c(RealValue r) {
		return (BMTLBooleanInterface)CommonFunctions.toBMTLDataType(Real_lt.TheInstance.invoke(this.getRealDelegate(), new Value [] {CommonFunctions.toMTDataType(r)}));
	}

	//>=
	public BMTLBooleanInterface BMTL__3e_3d(RealValue r) {
		return (BMTLBooleanInterface)CommonFunctions.toBMTLDataType(Real_ge.TheInstance.invoke(this.getRealDelegate(), new Value [] {CommonFunctions.toMTDataType(r)}));
	}

	//>
	public BMTLBooleanInterface BMTL__3e(RealValue r) {
		return (BMTLBooleanInterface)CommonFunctions.toBMTLDataType(Real_gt.TheInstance.invoke(this.getRealDelegate(), new Value [] {CommonFunctions.toMTDataType(r)}));
	}

	public BMTLRealInterface BMTL_abs() {
		return (BMTLRealInterface)CommonFunctions.toBMTLDataType(Real_abs.TheInstance.invoke(this.getRealDelegate(), null));
	}

	public BMTLIntegerInterface BMTL_floor() {
		return (BMTLIntegerInterface)CommonFunctions.toBMTLDataType(Real_floor.TheInstance.invoke(this.getRealDelegate(), null));
	}

	public BMTLRealInterface BMTL_max(RealValue r) {
		return (BMTLRealInterface)CommonFunctions.toBMTLDataType(Real_max.TheInstance.invoke(this.getRealDelegate(), new Value [] {CommonFunctions.toMTDataType(r)}));
	}

	public BMTLRealInterface BMTL_min(RealValue r) {
		return (BMTLRealInterface)CommonFunctions.toBMTLDataType(Real_min.TheInstance.invoke(this.getRealDelegate(), new Value [] {CommonFunctions.toMTDataType(r)}));
	}

	public BMTLIntegerInterface BMTL_round() {
		return (BMTLIntegerInterface)CommonFunctions.toBMTLDataType(Real_round.TheInstance.invoke(this.getRealDelegate(), null));
	}

	public BMTLStringInterface BMTL_toString() {
		return (BMTLStringInterface)CommonFunctions.toBMTLDataType(Real_toString.TheInstance.invoke(this.getRealDelegate(), null));
	}

	public BMTLRealInterface BMTL__2d() {
		return (BMTLRealInterface)CommonFunctions.toBMTLDataType(Real_unaryMinus.TheInstance.invoke(this.getRealDelegate(), null));
	}

}
