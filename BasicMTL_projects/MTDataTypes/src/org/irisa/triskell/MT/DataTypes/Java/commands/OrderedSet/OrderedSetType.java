/*
 * Created on May 28, 2003
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package org.irisa.triskell.MT.DataTypes.Java.commands.OrderedSet;

import java.util.Arrays;
import java.util.Collection;
import java.util.Hashtable;

import org.irisa.triskell.MT.DataTypes.Java.CollectionValue;
import org.irisa.triskell.MT.DataTypes.Java.Type;
import org.irisa.triskell.MT.DataTypes.Java.Value;
import org.irisa.triskell.MT.DataTypes.Java.commands.Collection.CollectionType;
import org.irisa.triskell.MT.DataTypes.Java.defaultImpl.CollectionValueImpl;

/**
 * @author ffondeme
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class OrderedSetType extends CollectionType {
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

}
