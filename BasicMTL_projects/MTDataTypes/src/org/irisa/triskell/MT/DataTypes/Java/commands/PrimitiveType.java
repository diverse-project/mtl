/**
 * Created on May 28, 2003
 * $Id: PrimitiveType.java,v 1.2 2004-02-16 17:01:50 dvojtise Exp $
 * @author : ffondeme 
 * 
 * Copyright 2004 - INRIA - LGPL license
 */
package org.irisa.triskell.MT.DataTypes.Java.commands;

import java.io.Serializable;
// import java.util.Arrays;
// import java.util.Collection;

import org.irisa.triskell.MT.DataTypes.Java.*;
import org.irisa.triskell.MT.DataTypes.Java.Value;
import org.irisa.triskell.MT.DataTypes.Java.defaultImpl.SetValueImpl;

/**
 * Base for all Primitive MTL Type
 */
public abstract class PrimitiveType extends AbstractType implements Serializable {
	private transient CollectionValue allInstances;

	public PrimitiveType(String[] qualifiedName, Type[] parents) {
		super(qualifiedName, parents);
	}

	public PrimitiveType(String name, Type[] parents) {
		this(new String [] {name}, parents);
	}

	public CollectionValue allInstances() throws Exception {
		if (this.allInstances == null)
			this.allInstances = new SetValueImpl(false, null, new Value [0], false);
		return this.allInstances;
	}

}
