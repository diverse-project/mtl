/*
 * Created on 12 août 2003
 * $Id: BMTLLibraryType.java,v 1.5 2004-02-17 08:58:21 dvojtise Exp $
 * Authors : ffondeme
 * 
 * Copyright 2004 - INRIA - LGPL license
 */
package org.irisa.triskell.MT.BasicMTL.TopTypes;

import java.lang.ref.WeakReference;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
//import java.util.WeakHashMap;

import org.irisa.triskell.MT.DataTypes.Java.CollectionValue;
//import org.irisa.triskell.MT.DataTypes.Java.Type;
import org.irisa.triskell.MT.DataTypes.Java.Value;
import org.irisa.triskell.MT.DataTypes.Java.commands.CommandGroup;
import org.irisa.triskell.MT.DataTypes.Java.defaultImpl.SetValueImpl;
//import org.irisa.triskell.MT.utils.Java.FilteredCollection;
//import org.irisa.triskell.MT.utils.Java.FilteredIterator;
//import org.irisa.triskell.MT.utils.Java.IteratingFinalList;

/**
 * @author ffondeme
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class BMTLLibraryType extends BMTLType {
	private final LinkedList allInstances; 
	
	/**
	 * @param parents collection of CommandGroup
	 */
	public BMTLLibraryType(String name, Class itf, Class clazz, Collection parents) {
		super(null, new String [] {name}, itf, clazz, parents == null || parents.size() == 0 ? Arrays.asList(new CommandGroup [] {BasicMtlLibraryCommandGroup.TheInstance}) : parents);
		allInstances = new LinkedList();
	}
	
	public void register (BMTLLibInterface lib) {
		if (!this.itf.isInstance(lib))
			throw new IllegalArgumentException();
		allInstances.add(new WeakReference(lib));
		Iterator it = this.getParents().iterator();
		Object o;
		while (it.hasNext()) {
			o = it.next();
			if (o instanceof BMTLLibraryType)
				((BMTLLibraryType)o).register(lib);
		}
	}

	public CollectionValue allInstances() {
		LinkedList ret = new LinkedList();
		int i = 0; Iterator it = this.allInstances.iterator();
		BMTLLibrary l;
		while (it.hasNext()) {
			l = (BMTLLibrary)((WeakReference)it.next()).get();
			if (l == null)
				it.remove();
			else
				ret.add(l);
		}
		return new SetValueImpl(false, null, ret, false);
	}

	public Value instanciate() {
		try {
			return (BMTLObject)this.clazz.newInstance();
		} catch(IllegalAccessException x) {
			throw new RuntimeException("Unknown error", x);
		} catch (InstantiationException x) {
			throw new RuntimeException("Cannot instanciate a new object of class " + this.getQualifiedNameAsString() + " (environment said " + x.getMessage() + ")");
		}
	}

	public String toString() {
		return "BasicMTL library type " + this.getQualifiedNameAsString();
	}

	public CommandGroup getBaseCommandGroup() {
		return BasicMtlLibraryCommandGroup.TheInstance;
	}

}
