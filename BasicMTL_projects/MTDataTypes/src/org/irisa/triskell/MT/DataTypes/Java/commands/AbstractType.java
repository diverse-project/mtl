/*
 * Created on May 28, 2003
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package org.irisa.triskell.MT.DataTypes.Java.commands;

import java.io.Serializable;
import java.util.Arrays;

import org.irisa.triskell.MT.DataTypes.Java.*;
import org.irisa.triskell.MT.DataTypes.Java.NullValue;
import org.irisa.triskell.MT.DataTypes.Java.Value;
import org.irisa.triskell.MT.DataTypes.Java.defaultImpl.ValueImpl;
import org.irisa.triskell.MT.utils.Java.AWK;

/**
 * @author ffondeme
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public abstract class AbstractType implements Type, Serializable {
	
	protected final Type [] parents;
	private transient String name;
	protected final String [] qualifiedName;
	private transient String qualifiedNameAsString;
	private transient String toString;
	
	public AbstractType (String [] qualifiedName, Type [] parents) {
		this.qualifiedName = qualifiedName;
		this.parents = parents;
	}

	public String getName() {
		if (this.name == null) {
			String [] qn = this.getQualifiedName();
			this.name = qn[qn.length-1];
		}
		return this.name;
	}

	public String[] getQualifiedName() {
		return this.qualifiedName;
	}

	public String getQualifiedNameAsString() {
		if (this.qualifiedNameAsString == null)
			this.qualifiedNameAsString = AWK.merge(this.getQualifiedName(), Type.PackageIndirection);
		return this.qualifiedNameAsString;
	}
	
	public Type[] getParents() {
		return parents;
	}
	
	public boolean conformsTo (Type type) throws UnsupportedOperationException {
		if (this.equals(type))
			return true;
		else {
			Type [] p = this.getParents();
			for (int i = 0; i < p.length; ++i)
				if (p[i].conformsTo(type))
					return true;
			return false;
		}
	}
	
	protected boolean isUndefined (Value v) {
		return (v instanceof NullValue) || v.isUndefined();
	}

	public boolean isKindOf(Value v) {
		if (v instanceof ValueImpl) {
			try {
				return ((ValueImpl)v).getType().conformsTo(this);
			} catch (UnsupportedOperationException x) {
			}
		}
		return this.isKindOfInternal(v) || this.isUndefined(v);
	}
	
	public boolean isTypeOf (Value v) {
		if (v instanceof ValueImpl) {
			try {
				return this.equals(((ValueImpl)v).getType());
			} catch (UnsupportedOperationException x) {
			}
		}
		return this.isUndefined(v) || v.getType().equals(this);
	}
	
	public abstract boolean isKindOfInternal (Value c);
	
	public boolean equals (Object rhs) {
		return rhs == this || ((rhs instanceof Type) && (Arrays.equals(this.getQualifiedName(), ((Type)rhs).getQualifiedName())));
	}

	public String toString() {
		if (this.toString == null) {
			this.toString = "primitive type " + this.getQualifiedNameAsString();
		}
		return this.toString;
	}

}
