/*
 * Created on May 28, 2003
 * $Id: IteratorType.java,v 1.2 2004-02-16 17:02:03 dvojtise Exp $
 * @author : ffondeme 
 * 
 * Copyright 2004 - INRIA - LGPL license
 */
package org.irisa.triskell.MT.DataTypes.Java.commands.Iterator;

import org.irisa.triskell.MT.DataTypes.Java.Type;
import org.irisa.triskell.MT.DataTypes.Java.Value;
import org.irisa.triskell.MT.DataTypes.Java.commands.PrimitiveType;
import org.irisa.triskell.MT.DataTypes.Java.commands.OclAny.OclAnyType;
import org.irisa.triskell.MT.DataTypes.Java.defaultImpl.IteratorValueImpl;

/**
 * 
 * Represents the type of Iterator objects
 */
public class IteratorType extends PrimitiveType {
	public static final Type TheInstance = new IteratorType();
	public static final String Name = "Iterator"; 
	
	private IteratorType () {
		super(Name, new Type[] {OclAnyType.TheInstance});
	}
	
	public boolean isKindOfInternal(Value v) {
		return v instanceof IteratorValueImpl;
	}

}