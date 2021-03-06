/*
 * $Id: OrderedSetType.java,v 1.5 2004-02-16 17:02:14 dvojtise Exp $
 * @author : ffondeme
 * 
 * Copyright 2004 - INRIA - LGPL license
 */
package org.irisa.triskell.MT.DataTypes.Java.commands.OrderedSet;

import java.util.Hashtable;

import org.irisa.triskell.MT.DataTypes.Java.Type;
import org.irisa.triskell.MT.DataTypes.Java.Value;
import org.irisa.triskell.MT.DataTypes.Java.commands.InstanciableType;
import org.irisa.triskell.MT.DataTypes.Java.commands.Collection.CollectionType;
import org.irisa.triskell.MT.DataTypes.Java.defaultImpl.OrderedSetValueImpl;

/**
 * @author ffondeme
 * Represent the Type of OrderedSet objects
 */
public class OrderedSetType extends CollectionType implements InstanciableType {
	public static final String OrderedSetName = "OrderedSet";
	
	// keys are element types (Type) and values are collection collection types (OrderedSetType) - of this exact type !
	private static final Hashtable orderedSetTypes = new Hashtable();	
	public static OrderedSetType getOrderedSetType (Type elementType) {
		OrderedSetType ret = (OrderedSetType)orderedSetTypes.get(elementType);
		if (ret == null) {
			ret = new OrderedSetType(elementType);
			orderedSetTypes.put(elementType, ret);
		}
		return ret;
	}
	
	protected OrderedSetType(Type elementType) {
		super(OrderedSetName, elementType, new Type [] {CollectionType.getCollectionType(elementType)});
	}

	public boolean conformsTo(Type type) throws UnsupportedOperationException {
		if (this.equals(type))
			return true;
		Class tc = type.getClass();
		if (tc.equals(CollectionType.class) || tc.equals(OrderedSetType.class))
			return this.getElementType().conformsTo(((CollectionType)type).getElementType());
		return false;
	}

	public Value instanciate() {
		return new OrderedSetValueImpl(false, null, new Value [0], false);
	}

	public Value instanciateFromJavaObject (Object javaObject)
	{
		// no java object are currently possible to create a set 
		// DVK : currently we have no use for that 
		return null;
	}	
	public boolean isInstanciableFromJavaObject (Object javaObject)
	{
		return false;
	}
}
