/*
 * Created on 19 août 2003
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package org.irisa.triskell.MT.BasicMTL.DataTypes.impl;

import org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLBooleanInterface;
import org.irisa.triskell.MT.DataTypes.Java.BooleanValue;
import org.irisa.triskell.MT.DataTypes.Java.Value;
import org.irisa.triskell.MT.DataTypes.Java.commands.Boolean.Boolean_and;
import org.irisa.triskell.MT.DataTypes.Java.commands.Boolean.Boolean_implies;
import org.irisa.triskell.MT.DataTypes.Java.commands.Boolean.Boolean_not;
import org.irisa.triskell.MT.DataTypes.Java.commands.Boolean.Boolean_or;
import org.irisa.triskell.MT.DataTypes.Java.commands.Boolean.Boolean_xor;
import org.irisa.triskell.MT.DataTypes.Java.defaultImpl.BooleanValueImpl;

/**
 * @author ffondeme
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class BMTLBoolean
	extends BMTLOclAny
	implements BMTLBooleanInterface, BooleanValue {
	
	public static BMTLBoolean TRUE = new BMTLBoolean(BooleanValueImpl.TRUE);
	public static BMTLBoolean FALSE = new BMTLBoolean(BooleanValueImpl.FALSE);

	public BMTLBoolean(boolean value) {
		this(new BooleanValueImpl(false, null, value));
	}

	public BMTLBoolean(BooleanValue delegate) {
		super(delegate);
	}
	public BooleanValue getBooleanDelegate() {
		return (BooleanValue)this.getDelegate();
	}
	
	public boolean getTheBoolean() {
		return this.getBooleanDelegate().getTheBoolean();
	}

	public String getValue() {
		return this.getBooleanDelegate().getValue();
	}

	public BMTLBooleanInterface BMTL_and(BooleanValue rhs) {
		return (BMTLBooleanInterface)CommonFunctions.toBMTLDataType(Boolean_and.TheInstance.invoke(this.getBooleanDelegate(), new Value [] {CommonFunctions.toMTDataType(rhs)}));
	}

	public BMTLBooleanInterface BMTL_implies(BooleanValue rhs) {
		return (BMTLBooleanInterface)CommonFunctions.toBMTLDataType(Boolean_implies.TheInstance.invoke(this.getBooleanDelegate(), new Value [] {CommonFunctions.toMTDataType(rhs)}));
	}

	public BMTLBooleanInterface BMTL_not() {
		return (BMTLBooleanInterface)CommonFunctions.toBMTLDataType(Boolean_not.TheInstance.invoke(this.getBooleanDelegate(), null));
	}

	public BMTLBooleanInterface BMTL_or(BooleanValue rhs) {
		return (BMTLBooleanInterface)CommonFunctions.toBMTLDataType(Boolean_or.TheInstance.invoke(this.getBooleanDelegate(), new Value [] {CommonFunctions.toMTDataType(rhs)}));
	}

	public BMTLBooleanInterface BMTL_xor(BooleanValue rhs) {
		return (BMTLBooleanInterface)CommonFunctions.toBMTLDataType(Boolean_xor.TheInstance.invoke(this.getBooleanDelegate(), new Value [] {CommonFunctions.toMTDataType(rhs)}));
	}

}
