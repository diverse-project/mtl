/*
 * $Id: OclTypeType.java,v 1.2 2004-02-16 17:02:08 dvojtise Exp $
 * @author : ffondeme
 * 
 * Copyright 2004 - INRIA - LGPL license
 */
package org.irisa.triskell.MT.DataTypes.Java.commands.OclType;

import org.irisa.triskell.MT.DataTypes.Java.Type;
import org.irisa.triskell.MT.DataTypes.Java.TypeValue;
import org.irisa.triskell.MT.DataTypes.Java.Value;
import org.irisa.triskell.MT.DataTypes.Java.commands.PrimitiveType;
import org.irisa.triskell.MT.DataTypes.Java.commands.OclAny.OclAnyType;

/**
 * 
 * Represents the type of an OclType object
 */
public class OclTypeType extends PrimitiveType {
	public static final Type TheInstance = new OclTypeType();
	public static final String Name = "OclType";
	
	private OclTypeType () {
		super(Name, new Type [] {OclAnyType.TheInstance});
	}

	public boolean isKindOfInternal(Value c) {
		return c instanceof TypeValue;
	}

}