/*
 * $Id: BMTLString.java,v 1.3 2003-11-24 13:38:24 dvojtise Exp $
 * Created on 19 août 2003
 */
package org.irisa.triskell.MT.BasicMTL.DataTypes.impl;

import org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLIntegerInterface;
import org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLRealInterface;
import org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface;
import org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface;
import org.irisa.triskell.MT.DataTypes.Java.StringValue;
import org.irisa.triskell.MT.DataTypes.Java.Value;
import org.irisa.triskell.MT.DataTypes.Java.commands.OclString.String_concat;
import org.irisa.triskell.MT.DataTypes.Java.commands.OclString.String_size;
import org.irisa.triskell.MT.DataTypes.Java.commands.OclString.String_substring;
import org.irisa.triskell.MT.DataTypes.Java.commands.OclString.String_toInteger;
import org.irisa.triskell.MT.DataTypes.Java.commands.OclString.String_toReal;
import org.irisa.triskell.MT.DataTypes.Java.commands.OclString.String_toOut;
import org.irisa.triskell.MT.DataTypes.Java.commands.OclString.String_toErr;
import org.irisa.triskell.MT.DataTypes.Java.defaultImpl.StringValueImpl;

/**
 * @author ffondeme
 * BasicMTL implementation of the Datatype string, uses the java data type and commands
 */
public class BMTLString extends BMTLOclAny implements BMTLStringInterface {

	public BMTLString(String value) {
		this(new StringValueImpl(false, null, value));
	}

	public BMTLString(Value delegate) {
		super(delegate);
	}

	public StringValue getStringDelegate() {
		return (StringValue)this.getDelegate();
	}

	public String getTheString() {
		return this.getStringDelegate().getTheString();
	}

	public String getValue() {
		return this.getStringDelegate().getValue();
	}

	public BMTLStringInterface BMTL_concat(BMTLStringInterface s) {
		return (BMTLStringInterface)CommonFunctions.toBMTLDataType(String_concat.TheInstance.invoke(this.getStringDelegate(), new Value [] {CommonFunctions.toMTDataType(s)}));
	}

	public BMTLIntegerInterface BMTL_size() {
		return (BMTLIntegerInterface)CommonFunctions.toBMTLDataType(String_size.TheInstance.invoke(this.getStringDelegate(), null));
	}

	public BMTLStringInterface BMTL_substring(
		BMTLIntegerInterface lower,
		BMTLIntegerInterface upper) {
		return (BMTLStringInterface)CommonFunctions.toBMTLDataType(String_substring.TheInstance.invoke(this.getStringDelegate(), new Value [] {CommonFunctions.toMTDataType(lower), CommonFunctions.toMTDataType(upper)}));
	}

	public BMTLIntegerInterface BMTL_toInteger() {
		return (BMTLIntegerInterface)CommonFunctions.toBMTLDataType(String_toInteger.TheInstance.invoke(this.getStringDelegate(), null));
	}

	public BMTLRealInterface BMTL_toReal() {
		return (BMTLRealInterface)CommonFunctions.toBMTLDataType(String_toReal.TheInstance.invoke(this.getStringDelegate(), null));
	}
	
	public BMTLVoidInterface BMTL_toOut() {
			return (BMTLVoidInterface)CommonFunctions.toBMTLDataType(String_toOut.TheInstance.invoke(this.getOclAnyDelegate(),null));
	}
	public BMTLVoidInterface BMTL_toErr() {
				return (BMTLVoidInterface)CommonFunctions.toBMTLDataType(String_toErr.TheInstance.invoke(this.getOclAnyDelegate(),null));
	}
}
