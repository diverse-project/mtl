/*
 * Created on May 28, 2003
 * $Id: BasicMtlObjectType.java,v 1.3 2004-02-17 08:58:21 dvojtise Exp $
 * Authors : ffondeme
 * 
 * Copyright 2004 - INRIA - LGPL license
 */
package org.irisa.triskell.MT.BasicMTL.TopTypes;

//import org.irisa.triskell.MT.DataTypes.Java.IntegerValue;
//import org.irisa.triskell.MT.DataTypes.Java.RealValue;
import org.irisa.triskell.MT.DataTypes.Java.Type;
import org.irisa.triskell.MT.DataTypes.Java.Value;
import org.irisa.triskell.MT.DataTypes.Java.commands.PrimitiveType;
import org.irisa.triskell.MT.DataTypes.Java.commands.OclAny.OclAnyType;


public class BasicMtlObjectType extends PrimitiveType {
	public static final Type TheInstance = new BasicMtlObjectType();
	public static final String Name = "BMTLObject"; 
	
	private BasicMtlObjectType () {
		super(Name, new Type[] {OclAnyType.TheInstance});
	}
	
	public boolean isKindOfInternal(Value v) {
		return v instanceof BMTLObjectInterface;
	}

	public String toString() {
		return "BasicMTL object type " + this.getQualifiedNameAsString();
	}

}