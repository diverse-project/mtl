/*
 * $Id: OclAnyType.java,v 1.3 2004-02-16 17:01:56 dvojtise Exp $
 * @author : ffondeme
 * 
 * Copyright 2004 - INRIA - LGPL license
 */
package org.irisa.triskell.MT.DataTypes.Java.commands.OclAny;

import org.irisa.triskell.MT.DataTypes.Java.CollectionValue;
import org.irisa.triskell.MT.DataTypes.Java.Type;
import org.irisa.triskell.MT.DataTypes.Java.Value;
import org.irisa.triskell.MT.DataTypes.Java.commands.PrimitiveType;
import org.irisa.triskell.MT.DataTypes.Java.commands.Void.VoidType;


public class OclAnyType extends PrimitiveType {
	public static final Type TheInstance = new OclAnyType();
	public static final String Name = "OclAny"; 
	
	private OclAnyType () {
		super(Name, null);
	}
	
	public boolean isKindOfInternal(Value v) {
		return (! VoidType.TheInstance.isKindOf(v)) && (! (v instanceof CollectionValue));
	}

	public boolean conformsTo(Type type) throws UnsupportedOperationException {
		return this.equals(type);
	}

}