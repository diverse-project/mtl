/*
 * $Id: InstanciableType.java,v 1.2 2004-01-22 12:36:00 dvojtise Exp $
 * Created on 25 août 2003
 */
package org.irisa.triskell.MT.DataTypes.Java.commands;

import org.irisa.triskell.MT.DataTypes.Java.Type;
import org.irisa.triskell.MT.DataTypes.Java.Value;

/**
 * @author ffondeme
 *
 * Interface for types that are instanciables
 */
public interface InstanciableType extends Type {
	
	/**
	 * instanciates a basicMtl object with this type
	 * @return Value
	 */
	Value instanciate ();
	
	/**
	 * instanciates a basicMtl object with the value of this java object
	 * note : the basicmtl object may (or may not be) a proxy to this java object	 
	 * @param javaObject
	 * @return Value
	 */
	Value instanciateFromJavaObject (Object javaObject);
	
	
	/**
	 * tests if we can instanciate a basicMtl object from this java object
	 * @param javaObject
	 * @return boolean
	 */
	boolean isInstanciableFromJavaObject (Object javaObject);
}
