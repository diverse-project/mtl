/*
 * Created on May 28, 2003
 * $Id: BasicMtlLibraryType.java,v 1.3 2004-02-17 08:58:21 dvojtise Exp $
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


public class BasicMtlLibraryType extends PrimitiveType {
	public static final Type TheInstance = new BasicMtlLibraryType();
	public static final String Name = "BMTLLibrary"; 
	
	private BasicMtlLibraryType () {
		super(Name, new Type[] {OclAnyType.TheInstance});
	}
	
	public boolean isKindOfInternal(Value v) {
		return v instanceof BMTLLibInterface;
	}

	public String toString() {
		return " BasicMTL library type " + this.getQualifiedNameAsString();
	}

}