/*
 * Created on 12 ao�t 2003
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package org.irisa.triskell.MT.BasicMTL.TopTypes;

import java.lang.ref.WeakReference;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.WeakHashMap;

import org.irisa.triskell.MT.DataTypes.Java.CollectionValue;
import org.irisa.triskell.MT.DataTypes.Java.Type;
import org.irisa.triskell.MT.DataTypes.Java.defaultImpl.SetValueImpl;
import org.irisa.triskell.MT.utils.Java.FilteredCollection;
import org.irisa.triskell.MT.utils.Java.FilteredIterator;
import org.irisa.triskell.MT.utils.Java.IteratingFinalList;

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
		super(null, new String [] {name}, itf, clazz, parents);
		allInstances = new LinkedList();
	}
	
	public void register (BMTLLibrary lib) {
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

	public BMTLObject instanciate() {
		try {
			return (BMTLObject)this.clazz.newInstance();
		} catch(IllegalAccessException x) {
			throw new RuntimeException("Unknown error", x);
		} catch (InstantiationException x) {
			throw new RuntimeException("Cannot instanciate a new object of class " + this.getQualifiedNameAsString() + " (environment said " + x.getMessage() + ")");
		}
	}

}
