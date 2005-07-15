/*
 * Created on May 26, 2003
 * $Id: BMTLLibrary.java,v 1.6 2005-07-15 14:05:35 ffondeme Exp $
 * Authors : ffondeme
 * 
 * Copyright 2004 - INRIA - LGPL license
 */
package org.irisa.triskell.MT.BasicMTL.TopTypes;
//import org.irisa.triskell.MT.DataTypes.Java.commands.*;
//import org.irisa.triskell.MT.DataTypes.Java.*;
//import org.irisa.triskell.MT.repository.API.Java.API;

import java.text.Collator;
import java.util.*;

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
		Collection c = this.allClassInstances(className);
		if (!c.contains(instance))
			c.add(instance);
	}
	
	public void removeInstance(String className, Object instance)
	{
		this.allClassInstances(className).remove(instance);
	}
	
	public final void delete() {
	}

}
