/*
 * Created on May 28, 2003
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package org.irisa.triskell.MT.DataTypes.Java.commands;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;

import org.irisa.triskell.MT.DataTypes.Java.*;
import org.irisa.triskell.MT.DataTypes.Java.Value;
import org.irisa.triskell.MT.DataTypes.Java.defaultImpl.SetValueImpl;

/**
 * @author ffondeme
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
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
