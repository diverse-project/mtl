/*
 * Created on May 28, 2003
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package org.irisa.triskell.MT.DataTypes.Java.commands.enum;

import java.util.ArrayList;
import java.util.Collection;

import org.irisa.triskell.MT.DataTypes.Java.CollectionValue;
import org.irisa.triskell.MT.DataTypes.Java.EnumValue;
import org.irisa.triskell.MT.DataTypes.Java.Type;
import org.irisa.triskell.MT.DataTypes.Java.Value;
import org.irisa.triskell.MT.DataTypes.Java.commands.PrimitiveType;
import org.irisa.triskell.MT.DataTypes.Java.commands.OclAny.OclAnyType;
import org.irisa.triskell.MT.DataTypes.Java.defaultImpl.CollectionValueImpl;
import org.irisa.triskell.MT.DataTypes.Java.defaultImpl.EnumValueImpl;
import org.irisa.triskell.MT.DataTypes.Java.defaultImpl.SetValueImpl;


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
		super(makeName(parts), new Type [] {OclAnyType.TheInstance});
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