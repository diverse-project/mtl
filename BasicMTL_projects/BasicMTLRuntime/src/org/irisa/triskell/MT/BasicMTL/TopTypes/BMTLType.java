/*
 * Created on 18 juin 2003
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package org.irisa.triskell.MT.BasicMTL.TopTypes;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

import org.irisa.triskell.MT.DataTypes.Java.CollectionValue;
import org.irisa.triskell.MT.DataTypes.Java.Type;
import org.irisa.triskell.MT.DataTypes.Java.Value;
import org.irisa.triskell.MT.DataTypes.Java.commands.CommandGroup;
import org.irisa.triskell.MT.DataTypes.Java.commands.CommandGroupImpl;
import org.irisa.triskell.MT.DataTypes.Java.commands.OclAny.OclAnyCommandGroup;
import org.irisa.triskell.MT.DataTypes.Java.commands.OclAny.OclAnyType;
import org.irisa.triskell.MT.DataTypes.Java.defaultImpl.CollectionValueImpl;
import org.irisa.triskell.MT.DataTypes.Java.defaultImpl.SetValueImpl;
import org.irisa.triskell.MT.utils.Java.AWK;

public class BMTLType extends CommandGroupImpl implements InstanciableType {
	
	protected final Class itf;
	protected final Class clazz;
	protected final BMTLLibrary library;
	protected final String [] qualifiedName;
	
	/**
	 * 
	 * @param qualifiedName
	 * @param clazz Must be the interface representent
	 * @param parents collection of CommandGroup
	 */
	public BMTLType(BMTLLibrary library, String [] qualifiedName, Class itf, Class clazz, Collection parents) {
		super(null, parents == null  || parents.size() == 0 ? Arrays.asList(new CommandGroup [] {BasicMtlObjectCommandGroup.TheInstance}) : parents);
		this.qualifiedName = qualifiedName;		
		this.itf = itf;
		this.clazz = clazz;
		this.library = library;
	}
	
	public CommandGroup getBaseCommandGroup () {
		return BasicMtlObjectCommandGroup.TheInstance;
	}

	public boolean conformsTo(Type t) throws UnsupportedOperationException {
		if (this.equals(t))
			return true;
		if (this.getBaseCommandGroup().getOwner().conformsTo(t))
			return true;
		if (! (t instanceof BMTLType))
			return false;
		return (conformsTo(this.itf, ((BMTLType)t).itf));
	}
	
	private static boolean conformsTo (Class itfChild, Class itfAncestor) {
		if (itfChild.equals(itfAncestor))
			return true;
		Class [] itfDirectAncestors = itfChild.getInterfaces();
		for (int i = 0; i < itfDirectAncestors.length; ++i)
			if (conformsTo(itfDirectAncestors[i], itfAncestor))
				return true;
		return false;
	}

	public String getName() {
		String [] qn = this.getQualifiedName();
		return qn[qn.length - 1];
	}

	public String[] getQualifiedName() {
		return this.qualifiedName;
	}

	public String getQualifiedNameAsString() {
		return AWK.merge(this.getQualifiedName(), Type.PackageIndirection);
	}

	public boolean isOfType(Value v) {
		return this.itf.isInstance(v);
	}

	public Type getOwner() {
		return this;
	}

	public boolean equals(Object obj) {
		return this == obj || ((obj instanceof BMTLType) && ((BMTLType)obj).itf.equals(this.itf)) && ((BMTLType)obj).clazz.equals(this.clazz) && ((BMTLType)obj).getLibrary() == this.getLibrary();
	}

	public boolean isKindOf(Value v) {
		return this.itf.isInstance(v) && ((BMTLType)v.getType()).getLibrary() == this.getLibrary();
	}

	public boolean isTypeOf(Value v) {
		return this.clazz.isInstance(v) && ((BMTLType)v.getType()).getLibrary() == this.getLibrary();
	}

	public CollectionValue allInstances() throws Exception {
		return new SetValueImpl(false, null, this.getLibrary().allClassInstances(this.getName()), false);
	}
	
	public BMTLObjectInterface instanciate () {
		try {
			Constructor c = this.clazz.getConstructor(new Class [] {this.getLibrary().getClass()});
			return (BMTLObject)c.newInstance(new Object [] {this.getLibrary()});
		} catch(NoSuchMethodException x) {
			throw new RuntimeException("Unknown error", x);
		} catch(IllegalAccessException x) {
			throw new RuntimeException("Unknown error", x);
		} catch(InvocationTargetException x) {
			throw new RuntimeException("Unknown error", x);
		} catch (InstantiationException x) {
			throw new RuntimeException("Cannot instanciate a new object of class " + this.getQualifiedNameAsString() + " (environment said " + x.getMessage() + ")");
		}
	}

	public String toString() {
		return "BasicMTL type " + this.getQualifiedNameAsString();
	}

	public BMTLLibrary getLibrary() {
		return library == null ? null : library.getLibrary();
	}

}
