/*
 * Created on May 28, 2003
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package org.irisa.triskell.MT.DataTypes.Java.commands.Collection;

import java.util.Arrays;
import java.util.Collection;
import java.util.Hashtable;

import org.irisa.triskell.MT.DataTypes.Java.CollectionValue;
import org.irisa.triskell.MT.DataTypes.Java.Type;
import org.irisa.triskell.MT.DataTypes.Java.Value;
import org.irisa.triskell.MT.DataTypes.Java.commands.AbstractType;
import org.irisa.triskell.MT.DataTypes.Java.defaultImpl.CollectionValueImpl;
import org.irisa.triskell.MT.DataTypes.Java.defaultImpl.SetValueImpl;

/**
 * @author ffondeme
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class CollectionType extends AbstractType {
	public static final String CollectionName = "Collection";
	
	// keys are element types (Type) and values are collection collection types (CollectionType) - of this exact type !	private static Hashtable defined
	private static final Hashtable collectionTypes = new Hashtable();	
	public static CollectionType getCollectionType (Type elementType) {
		CollectionType ret = (CollectionType)collectionTypes.get(elementType);
		if (ret == null) {
			ret = new CollectionType(elementType);
			collectionTypes.put(elementType, ret);
		}
		return ret;
	}
	
	protected final Type elementType;

	public CollectionType(String collectionKind, Type elementType, Type[] parents) {
		super(new String [] {collectionKind + " (" + elementType.getQualifiedNameAsString() + ')'}, parents);
		this.elementType = elementType;
	}

	private CollectionType(Type elementType) {
		this(CollectionName, elementType, new Type [0]);
	}

	public boolean isKindOfInternal(Value c) {
		if (! (c instanceof CollectionValue))
			return false;
		if (c instanceof CollectionValueImpl) {
			try {
				return this.conformsTo(((CollectionValueImpl)c).getType());
			} catch (UnsupportedOperationException x) {
			}
		}
		Value [] values = ((CollectionValue)c).getTheCollection();
		Type elt = this.getElementType();
		for (int i = 0; i < values.length; ++i)
			if (! elt.isKindOf(values[i]))
				return false;
		return true;
	}

	private static CollectionValue allInstances;
	public CollectionValue allInstances() {
		if (allInstances == null) {
			allInstances = new SetValueImpl(false, null, new Value [0], false);
		}
		return allInstances;
	}
	
	public Type getElementType() {
		return elementType;
	}

	public boolean conformsTo(Type type) throws UnsupportedOperationException {
		return this.equals(type) || ((type.getClass().equals(CollectionType.class)) && ((CollectionType)type).getElementType().conformsTo(this.getElementType()));
	}

}
