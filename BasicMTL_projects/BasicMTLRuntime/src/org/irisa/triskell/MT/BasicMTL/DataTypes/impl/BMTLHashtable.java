/*
 * Created on 6 juil. 2004
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package org.irisa.triskell.MT.BasicMTL.DataTypes.impl;

import org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLBooleanInterface;
import org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLCollectionInterface;
import org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOclAnyInterface;
import org.irisa.triskell.MT.BasicMTL.java2mtl.DefaultJava2MtlInstanciableType;
import org.irisa.triskell.MT.BasicMTL.java2mtl.DefaultJava2MtlMappingObject;
import org.irisa.triskell.MT.DataTypes.Java.BooleanValue;
import org.irisa.triskell.MT.DataTypes.Java.CollectionValue;
import org.irisa.triskell.MT.DataTypes.Java.Type;
import org.irisa.triskell.MT.DataTypes.Java.Value;
import org.irisa.triskell.MT.DataTypes.Java.defaultImpl.BooleanValueImpl;
import org.irisa.triskell.MT.DataTypes.Java.defaultImpl.SetValueImpl;

/**
 * @author edrezen
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class BMTLHashtable extends DefaultJava2MtlMappingObject 
{
	//////////////////////////////////////////////////////////////////////////
	// We define the needed information for the java 2 mtl mapping
	//////////////////////////////////////////////////////////////////////////
	static public String   getName ()          { return "Hashtable"; }
	static public String[] getQualifiedName () { return new String[] {"Standard", "Hashtable"}; }	
	static public Class    getTheClass ()      { return BMTLHashtable.class; }

	public static Type TheType =  new DefaultJava2MtlInstanciableType (getName(), getQualifiedName(), getTheClass());
	public Type getType() { return TheType; } 
	
	//////////////////////////////////////////////////////////////////////////
	// We define the API we will retrieve in MTL
	//////////////////////////////////////////////////////////////////////////
	protected java.util.Hashtable theHashtable = new java.util.Hashtable ();
	 
	public BMTLBooleanInterface BMTL_contains (Value value)
	{
		return new BMTLBoolean (theHashtable.contains(value));
	}

	public BMTLBooleanInterface BMTL_containsKey (Value key)
	{
		return new BMTLBoolean (theHashtable.containsKey(key));
	}

	public BMTLBooleanInterface BMTL_containsValue (Value value)
	{
		return new BMTLBoolean (theHashtable.containsValue(value));
	}

	public BMTLOclAnyInterface BMTL_get (Value key)
	{
		return (BMTLOclAnyInterface) CommonFunctions.toBMTLDataType ((Value) theHashtable.get (key));
	}

	public BMTLOclAnyInterface BMTL_put (Value key, Value value)
	{
		return (BMTLOclAnyInterface) CommonFunctions.toBMTLDataType ((Value) theHashtable.put (key,value));
	}
}
