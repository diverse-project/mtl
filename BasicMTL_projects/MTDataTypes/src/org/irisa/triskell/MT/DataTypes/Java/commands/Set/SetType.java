/*
 * Created on May 28, 2003
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package org.irisa.triskell.MT.DataTypes.Java.commands.Set;

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
public class SetType extends CollectionType {
	// keys are element types (Type) and values are collection collection types (CollectionType) - of this exact type !	private static Hashtable defined
	private static final Hashtable setTypes = new Hashtable();	
	public static SetType getSetType (Type elementType) {
		SetType ret = (SetType)setTypes.get(elementType);
		if (ret == null) {
			ret = new SetType(elementType);
			setTypes.put(elementType, ret);
		}
		return ret;
	}
	
	protected SetType(Type elementType) {
		super("Set", elementType, new Type [] {CollectionType.getCollectionType(elementType)});
	}

	public boolean conformsTo(Type type) throws UnsupportedOperationException {
		if (this.equals(type))
			return true;
		Class tc = type.getClass();
		if (tc.equals(CollectionType.class) || tc.equals(SetType.class))
			return this.getElementType().conformsTo(((CollectionType)type).getElementType());
		return false;
	}

}
