/*
 * Created on May 28, 2003
 * $Id: BooleanType.java,v 1.3 2004-02-16 17:02:19 dvojtise Exp $
 * @author : ffondeme 
 * 
 * Copyright 2004 - INRIA - LGPL license
 */
package org.irisa.triskell.MT.DataTypes.Java.commands.Boolean;

import org.irisa.triskell.MT.DataTypes.Java.BooleanValue;
import org.irisa.triskell.MT.DataTypes.Java.Type;
import org.irisa.triskell.MT.DataTypes.Java.Value;
// import org.irisa.triskell.MT.DataTypes.Java.commands.AbstractType;
import org.irisa.triskell.MT.DataTypes.Java.commands.PrimitiveType;
import org.irisa.triskell.MT.DataTypes.Java.commands.OclAny.OclAnyType;


public class BooleanType extends PrimitiveType {
	public static final Type TheInstance = new BooleanType();
	public static final String Name = "Boolean"; 
	
	private BooleanType () {
		super(Name, new Type [] {OclAnyType.TheInstance});
	}
	
	public boolean isKindOfInternal(Value v) {
		return v instanceof BooleanValue;
	}

}