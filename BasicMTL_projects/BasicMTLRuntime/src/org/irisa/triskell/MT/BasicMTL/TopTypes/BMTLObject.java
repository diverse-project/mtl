package org.irisa.triskell.MT.BasicMTL.TopTypes;

import java.util.Arrays;
import java.util.Map;

import org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLBagInterface;
import org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLBooleanInterface;
import org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOclAnyInterface;
import org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOclTypeInterface;
import org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface;
import org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLSequenceInterface;
import org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLSetInterface;
import org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface;
import org.irisa.triskell.MT.BasicMTL.DataTypes.impl.BMTLBag;
import org.irisa.triskell.MT.BasicMTL.DataTypes.impl.BMTLBoolean;
import org.irisa.triskell.MT.BasicMTL.DataTypes.impl.BMTLOrderedSet;
import org.irisa.triskell.MT.BasicMTL.DataTypes.impl.BMTLSequence;
import org.irisa.triskell.MT.BasicMTL.DataTypes.impl.BMTLSet;
import org.irisa.triskell.MT.BasicMTL.DataTypes.impl.BMTLVoid;
import org.irisa.triskell.MT.DataTypes.Java.Type;
import org.irisa.triskell.MT.DataTypes.Java.TypeValue;
import org.irisa.triskell.MT.DataTypes.Java.Value;
import org.irisa.triskell.MT.DataTypes.Java.ValueVisitor;
import org.irisa.triskell.MT.DataTypes.Java.commands.UnknownCommandException;
import org.irisa.triskell.MT.DataTypes.Java.commands.OclAny.OclAnyCommandGroup;
import org.irisa.triskell.MT.DataTypes.Java.commands.OclAny.OclAnyType;
import org.irisa.triskell.MT.DataTypes.Java.commands.OclAny.OclAny_toErr;
import org.irisa.triskell.MT.DataTypes.Java.commands.OclAny.OclAny_toOut;
import org.irisa.triskell.MT.utils.Java.AWK;

public abstract class BMTLObject extends Throwable implements BMTLObjectInterface
{
	protected Map inheritanceMap;
	protected String name;
	protected transient String [] qualifiedName;

	public BMTLObject(String name) {
		this.name = name;
	}

	public void accept(ValueVisitor visitor) {
		visitor.visitValue(this);
	}

	public boolean equals(Value rhs) {
		return this == rhs;
	}

	public String getErrorMessage() {
		return null;
	}

	public boolean isUndefined() {
		return false;
	}

	public boolean equals(Object obj) {
		return this == obj || ((obj instanceof BMTLObject) && this.equals((Value)obj));
	}
	
	public String getName () {
		return this.name;
	}
	
	public String [] getQualifiedName () {
		if (this.qualifiedName == null)
			this.qualifiedName = new String [] {this.getLibrary().getName(), this.getName()};
		return this.qualifiedName;
	}

//we do not (yet) plan to use invoke on BMTL Objects
//but only on model elements	
	public org.irisa.triskell.MT.DataTypes.Java.Value invoke(
			String[] scopeQualifiedName,
			String name,
			org.irisa.triskell.MT.DataTypes.Java.Value[] arguments,
			String[] discriminants)
			throws org.irisa.triskell.MT.DataTypes.Java.commands.UnknownCommandException, org.irisa.triskell.MT.DataTypes.Java.commands.MultipleCommandException
	{ throw new org.irisa.triskell.MT.DataTypes.Java.commands.UnknownCommandException(null,"invoke not implemented on BMTL objects",null,null,null);}
/*		if (scopeQualifiedName == null)
			return this.getCommandGroup().invoke(null, this, name, arguments, discriminants);
		else {
			if (Arrays.equals(OclAnyType.TheInstance.getQualifiedName(), scopeQualifiedName))
				return OclAnyCommandGroup.TheInstance.invoke(null, this, name, arguments, discriminants);
			else {
				BMTLObject ref = this.getRef(scopeQualifiedName);
				if (ref == null)
					throw new UnknownCommandException(this, name, arguments, discriminants, "Unknown type: " + AWK.merge(scopeQualifiedName, "::"));
				else
					return ref.invoke(null, name, arguments, discriminants);
			}
		}
	}
	}
	public abstract BMTLCommandGroup getCommandGroup();*/
	public abstract BMTLLibInterface getLibrary();
	
	public BMTLObject getRef (String [] qualifiedName) {
		if (qualifiedName.length == 1)
			return (BMTLObject)this.inheritanceMap.get(this.getLibrary().getName() +"::" + qualifiedName[0]);
		else if (qualifiedName.length == 2)
			return (BMTLObject)this.inheritanceMap.get(AWK.merge(qualifiedName, "::"));
		else
			return null;
	}

	public BMTLVoidInterface BMTL_delete() {
		this.delete();
		return BMTLVoid.TheInstance;
	}

	public BMTLBooleanInterface BMTL__3c_3e(Value rhs) {
		return this.BMTL__3d(rhs).BMTL_not();
	}

	public BMTLBooleanInterface BMTL__3d(Value rhs) {
		return this.equals(rhs) ? BMTLBoolean.TRUE : BMTLBoolean.FALSE;
	}

	public BMTLBooleanInterface BMTL_oclIsKindOf(TypeValue type) {
		return type.getTheType().isKindOf(this) ? BMTLBoolean.TRUE : BMTLBoolean.FALSE;
	}

	public BMTLBooleanInterface BMTL_oclIsTypeOf(TypeValue type) {
		return type.getTheType().isTypeOf(this) ? BMTLBoolean.TRUE : BMTLBoolean.FALSE;
	}

	public BMTLBooleanInterface BMTL_oclIsUndefined() {
		return BMTLBoolean.FALSE;
	}

	public BMTLVoidInterface BMTL_toErr() {
		OclAny_toErr.TheInstance.invoke(this,null);
		return BMTLVoid.TheInstance;
	}

	public BMTLVoidInterface BMTL_toOut() {
		OclAny_toOut.TheInstance.invoke(this,null);
		return BMTLVoid.TheInstance;
	}

	public Value getOclAnyDelegate() {
		return this;
	}
	public Value getDelegate() {
		return this;
	}

	public String toString() {
		return "" + Integer.toHexString(this.hashCode()) + " : " + this.getType();
	}

	public BMTLBooleanInterface BMTL_isNull(Value v) {
		return v == null ? BMTLBoolean.TRUE : BMTLBoolean.FALSE;
	}

	public BMTLSetInterface BMTL_newSet() {
		return new BMTLSet(new Value [0]);
	}

	public BMTLBagInterface BMTL_newBag() {
		return new BMTLBag(new Value [0]);
	}

	public BMTLSequenceInterface BMTL_newSequence() {
		return new BMTLSequence(new Value [0]);
	}

	public BMTLOrderedSetInterface BMTL_newOrderedSet() {
		return new BMTLOrderedSet(new Value [0]);
	}

}