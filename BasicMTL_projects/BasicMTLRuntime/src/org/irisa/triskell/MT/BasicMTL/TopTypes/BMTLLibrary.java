package org.irisa.triskell.MT.BasicMTL.TopTypes;
import org.irisa.triskell.MT.DataTypes.Java.commands.*;
import org.irisa.triskell.MT.DataTypes.Java.*;
import org.irisa.triskell.MT.repository.API.Java.API;

import java.text.Collator;
import java.util.*;

/*
 * Created on May 26, 2003
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */

/**
 * @author ffondeme
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public abstract class BMTLLibrary extends BMTLObject implements BMTLLibInterface {
	private TreeMap classInstances = new TreeMap(Collator.getInstance());
	
	public BMTLLibrary (String name) {
		super(name);
	}
	
	public String [] getQualifiedName () {
		if (this.qualifiedName == null)
			this.qualifiedName = new String [] {this.getName()};
		return this.qualifiedName;
	}
	
	public List allClassInstances (String className) {
		LinkedList ret = (LinkedList)this.classInstances.get(className);
		if (ret == null) {
			ret = new LinkedList();
			this.classInstances.put(className, ret);
		}
		return ret;
	}

	public void recordNewInstance(String className,Object instance)
	{ 
		this.allClassInstances(className).add(instance);
	}
	
	public void removeInstance(String className, Object instance)
	{
		this.allClassInstances(className).remove(instance);
	}
	
	public final void delete() {
	}

}
