package org.irisa.triskell.MT.BasicMTL.TopTypes;

import java.util.Arrays;
import java.util.Map;

import org.irisa.triskell.MT.DataTypes.Java.Value;
import org.irisa.triskell.MT.DataTypes.Java.ValueVisitor;
import org.irisa.triskell.MT.DataTypes.Java.commands.UnknownCommandException;
import org.irisa.triskell.MT.DataTypes.Java.commands.OclAny.OclAnyCommandGroup;
import org.irisa.triskell.MT.DataTypes.Java.commands.OclAny.OclAnyType;
import org.irisa.triskell.MT.utils.Java.AWK;

public abstract class BMTLObject extends Throwable implements BMTLInterface
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
	public abstract BMTLLibrary getLibrary();
	
	public BMTLObject getRef (String [] qualifiedName) {
		if (qualifiedName.length == 1)
			return (BMTLObject)this.inheritanceMap.get(this.getLibrary().getName() +"::" + qualifiedName[0]);
		else if (qualifiedName.length == 2)
			return (BMTLObject)this.inheritanceMap.get(AWK.merge(qualifiedName, "::"));
		else
			return null;
	}

}