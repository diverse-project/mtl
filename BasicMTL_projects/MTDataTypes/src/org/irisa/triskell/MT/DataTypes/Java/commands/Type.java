/*
 * Created on May 23, 2003
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package org.irisa.triskell.MT.DataTypes.Java.commands;


import java.util.Collection;

import org.irisa.triskell.MT.DataTypes.Java.Value;
;

/**
 * @author ffondeme
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public interface Type {
	static String PackageIndirection = "::";
	
	String getName();
	String [] getQualifiedName();
	String getQualifiedNameAsString();
	
	boolean isOfType (Value v);
	boolean conformsTo (Type t) throws UnsupportedOperationException;
	
	/**
	 * 
	 * @return Collection a collection of Value
	 */
	Collection allInstances ();
	
	String toString();
}
