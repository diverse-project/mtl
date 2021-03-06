/*
 * $Id: BMTLType.java,v 1.10 2004-11-03 09:11:28 jpthibau Exp $
 * Created on 18 juin 2003
 *
 */
package org.irisa.triskell.MT.BasicMTL.TopTypes;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Hashtable;
// import java.util.Iterator;

import org.irisa.triskell.MT.DataTypes.Java.CollectionValue;
import org.irisa.triskell.MT.DataTypes.Java.Type;
import org.irisa.triskell.MT.DataTypes.Java.Value;
import org.irisa.triskell.MT.DataTypes.Java.commands.*;
import org.irisa.triskell.MT.DataTypes.Java.commands.CommandGroup;
import org.irisa.triskell.MT.DataTypes.Java.commands.CommandGroupImpl;
// import org.irisa.triskell.MT.DataTypes.Java.commands.OclAny.OclAnyCommandGroup;
// import org.irisa.triskell.MT.DataTypes.Java.commands.OclAny.OclAnyType;
// import org.irisa.triskell.MT.DataTypes.Java.defaultImpl.CollectionValueImpl;
import org.irisa.triskell.MT.DataTypes.Java.defaultImpl.SetValueImpl;
import org.irisa.triskell.MT.utils.Java.AWK;

public class BMTLType extends CommandGroupImpl implements InstanciableType {
	
	protected final Class itf;
	protected final Class clazz;
	protected final BMTLLibrary library;
	protected final String [] qualifiedName;
	
	/**
	 * constructor
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

	public boolean isKindOf(Value v) 
	{
		// We should rely only on java objects test and not on the libraries names test. The previous test was : 
		// return this.itf.isInstance(v) && ((BMTLType)v.getType()).getLibrary() == this.getLibrary();		
		return this.itf.isInstance(v); 
	}

	public boolean isTypeOf(Value v) 
	{
		// We could rely only on java objects test and not on the libraries names test. 
		return  this.clazz.isInstance(v) && ((BMTLType)v.getType()).getLibrary() == this.getLibrary();
	}

	public CollectionValue allInstances() throws Exception {
		return new SetValueImpl(false, null, this.getLibrary().allClassInstances(this.getName()), false);
	}
	
	private transient Constructor directConstructor = null;
	public Constructor getDirectConstructor () {
		try {
		if (this.clazz.getDeclaredMethod("isAbstract",null)!=null)
			System.err.println("You are trying to create an instance of abstract class"+clazz.getName());
		} catch(NoSuchMethodException e) {}
		if (this.directConstructor == null) {
			Constructor [] cs = this.clazz.getConstructors();
			Class [] pt;
			for (int i = 0; this.directConstructor == null && i < cs.length; ++i) {
				pt = cs[i].getParameterTypes();
				if (pt.length == 1 && pt[0].isInstance(this.getLibrary()))
					this.directConstructor = cs[i];
			}
		}
		return this.directConstructor;
	}
	
	public Value instanciate () {
		try {
			return (BMTLObject)this.getDirectConstructor().newInstance(new Object [] {this.getLibrary()});
		} catch(IllegalAccessException x) {
			System.err.println("IllegalAccessException");
			x.printStackTrace();
			throw new RuntimeException("Unknown error", x);
		} catch(InvocationTargetException x) {
			System.err.println("InvocationTargetException");
			x.printStackTrace();
			throw new RuntimeException("Unknown error", x);
		} catch (InstantiationException x) {
			System.err.println("InstantiationException");
			x.printStackTrace();
			throw new RuntimeException("Cannot instanciate a new object of class " + this.getQualifiedNameAsString() + " (environment said " + x.getMessage() + ")");
		}
	}
	
	public Value instanciateFromJavaObject (Object javaObject)
	{
		// default behavior : no java object can be used to create a mtl object of this type.
		// currently only SystemException objects overwrite this method
		return null;
	}	
	public boolean isInstanciableFromJavaObject (Object javaObject)
	{
		return false;
	}
	
	private transient Constructor refConstructor = null;
	public Constructor getRefConstructor () {
		if (this.refConstructor == null) {
			Constructor [] cs = this.clazz.getConstructors();
			Class [] pt;
			for (int i = 0; this.refConstructor == null && i < cs.length; ++i) {
				pt = cs[i].getParameterTypes();
				if (pt.length == 3 && pt[0].isInstance(this.getLibrary()) && pt[1].equals(Hashtable.class) && pt[2].equals(this.itf))
					this.refConstructor = cs[i];
			}
		}
		return this.refConstructor;
	}
	
	public Value instanciateReference (Hashtable instanciationMap, BMTLObject theCaller) {
		try {
			return (BMTLObject)this.getRefConstructor().newInstance(new Object [] {this.getLibrary(), instanciationMap, theCaller});
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

	public BMTLLibInterface getLibrary() {
		return library == null ? null : library.getLibrary();
	}

	public Class getTheClass() {
		return clazz;
	}
}
