/*
 * Created on May 28, 2003
 * $Id: EnumType.java,v 1.4 2004-02-16 17:02:15 dvojtise Exp $
 * @author : ffondeme 
 * 
 * Copyright 2004 - INRIA - LGPL license
 */
package org.irisa.triskell.MT.DataTypes.Java.commands.enum;

import org.irisa.triskell.MT.DataTypes.Java.CollectionValue;
import org.irisa.triskell.MT.DataTypes.Java.EnumValue;
import org.irisa.triskell.MT.DataTypes.Java.Type;
import org.irisa.triskell.MT.DataTypes.Java.Value;
import org.irisa.triskell.MT.DataTypes.Java.commands.PrimitiveType;
import org.irisa.triskell.MT.DataTypes.Java.commands.OclAny.OclAnyType;
import org.irisa.triskell.MT.DataTypes.Java.defaultImpl.CollectionValueImpl;
import org.irisa.triskell.MT.DataTypes.Java.defaultImpl.EnumValueImpl;
import org.irisa.triskell.MT.DataTypes.Java.defaultImpl.SetValueImpl;


/**
 * Object representing the type of an Enum object
 */
public class EnumType extends PrimitiveType {
	private static String makeName (String [] parts) {
		StringBuffer sb = new StringBuffer();
		sb.append("enum {");
		for (int i = 0; i < parts.length; ++i) {
			if (i > 0)
				sb.append(", ");
			sb.append(parts[i]);
		}
		sb.append('}');
		return sb.toString();
	}
	
	protected final String [] parts;
	private transient CollectionValue allInstances = null;
	
	public EnumType (String [] parts) {
		this(parts, new Type [] {OclAnyType.TheInstance});
	}
	
	protected EnumType (String [] parts, Type [] ancestors) {
		super(makeName(parts), ancestors);
		this.parts = parts;
	}

	public String [] getParts() {
		return this.parts;
	}

	protected boolean hasPart(String name) {
		String [] ps = this.getParts();
		for (int i = 0; i < ps.length; ++i)
			if (name.equals(ps[i]))
				return true;
		return false;
	}
	
	public boolean isKindOfInternal (Value v) {
		if (! (v instanceof EnumValue))
			return false;
		return CollectionValueImpl.compareSet(this.getParts(), ((EnumValue)v).getEnumeration());
	}
	
	public boolean equals (Object o) {
		return this == o || ((o instanceof EnumType) && CollectionValueImpl.compareSet(this.getParts(), ((EnumType)o).getParts()));
	}

	public CollectionValue allInstances() throws Exception {
		if (this.allInstances == null) {
			String [] ps = this.getParts();
			EnumValue [] ret = new EnumValue [ps.length];
			for (int i = 0; i < ps.length; ++i) {
				ret[i] = new EnumValueImpl(false, null, ps[i], this.getQualifiedName());
			}
			this.allInstances = new SetValueImpl(false, null, ret, false);
		}
		return this.allInstances;
	}

}