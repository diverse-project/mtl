/*
 * Created on May 28, 2003
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package org.irisa.triskell.MT.DataTypes.Java.commands.Tuple;

import java.util.Collection;
import java.util.Iterator;
import java.util.TreeMap;

import org.irisa.triskell.MT.DataTypes.Java.TupleValue;
import org.irisa.triskell.MT.DataTypes.Java.Type;
import org.irisa.triskell.MT.DataTypes.Java.Value;
import org.irisa.triskell.MT.DataTypes.Java.VoidValue;
import org.irisa.triskell.MT.DataTypes.Java.commands.PrimitiveType;
import org.irisa.triskell.MT.DataTypes.Java.commands.OclAny.OclAnyType;
import org.irisa.triskell.MT.DataTypes.Java.defaultImpl.CollectionValueImpl;


public class TupleType extends PrimitiveType {
	public static class TupleTypePart {
		protected final String name;
		protected final Type type;
		
		public TupleTypePart (String name, Type type) {
			this.name = name;
			this.type = type;
		}

		public String getName() {
			return name;
		}

		public Type getType() {
			return type;
		}
		
		public boolean equals (Object o) {
			return this == o || ((o instanceof TupleTypePart) && ((TupleTypePart)o).getName().equals(this.getName()) && ((TupleTypePart)o).getType().equals(this.getType()));
		}

	}
	
	private static String makeName (Collection parts) {
		StringBuffer sb = new StringBuffer();
		sb.append("Tuple (");
		Iterator it = parts.iterator();
		TupleTypePart p;
		boolean first = true;
		while (it.hasNext()) {
			if (first)
				first = false;
			else
				sb.append(", ");
			p = (TupleTypePart)it.next();
			sb.append(p.getName());
			sb.append(": ");
			sb.append(p.getType().getQualifiedName());
		}
		sb.append(')');
		return sb.toString();
	}
	
	private static boolean compareParts (TupleType t1, TupleType t2) {
		return CollectionValueImpl.compareSet(t1.getParts(), t2.getParts());
	}
	
	protected final TreeMap parts = new TreeMap();
	
	private void registerParts (Collection parts) {
		Iterator it = parts.iterator();
		TupleTypePart p;
		while (it.hasNext()) {
			p = (TupleTypePart)it.next();
			this.parts.put(p.getName(), p);
		}
	}
	
	protected TupleType (Collection parts) {
		super(makeName(parts), new Type [] {OclAnyType.TheInstance});
		this.registerParts(parts);
	}
	
	protected TupleType (String [] name, Type [] parents, Collection parts) {
		super(name, parents);
		this.registerParts(parts);
	}

	public Collection getParts() {
		return parts.values();
	}

	protected TupleTypePart getPart(String name) {
		return (TupleTypePart)this.parts.get(name);
	}
	
	public boolean isKindOfInternal (Value v) {
		if (! (v instanceof TupleValue))
			return false;
		String [] vpartNames = ((TupleValue)v).getPartNames();
		if (this.parts.size() != vpartNames.length)
			return false;
		TupleTypePart p;
		for (int i = 0; i < vpartNames.length; ++i) {
			p = this.getPart(vpartNames[i]);
			if (p == null || (!p.getType().isKindOf(((TupleValue)v).getPart(vpartNames[i]))))
				return false;
		}
		return true;
	}
	
	public boolean equals (Object o) {
		return this == o || ((o instanceof TupleType) && compareParts(this, (TupleType)o));
	}

}