/*
 * Created on 6 juil. 2004
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package org.irisa.triskell.MT.BasicMTL.DataTypes.impl;

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
	 
	public BooleanValue BMTL_contains (Value value)
	{
		return new BooleanValueImpl (false, null, theHashtable.contains(value));
	}

	public BooleanValue BMTL_containsKey (Value key)
	{
		return new BooleanValueImpl (false, null, theHashtable.containsKey(key));
	}

	public BooleanValue BMTL_containsValue (Value value)
	{
		return new BooleanValueImpl (false, null, theHashtable.containsValue(value));
	}

	public Value BMTL_get (Value key)
	{
		return (Value) theHashtable.get(key);
	}

	public Value BMTL_put (Value key, Value value)
	{
		return (Value) theHashtable.put(key,value);
	}

	public CollectionValue BMTL_keySet ()
	{
		return new SetValueImpl (false,null, theHashtable.keySet());
	}

}
