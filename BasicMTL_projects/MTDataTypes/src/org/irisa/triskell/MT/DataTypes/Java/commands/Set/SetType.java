/*
 * $Id: SetType.java,v 1.4 2004-01-22 12:35:59 dvojtise Exp $
 * Created on May 28, 2003
 *
 */
package org.irisa.triskell.MT.DataTypes.Java.commands.Set;

// import java.util.Arrays;
// import java.util.Collection;
import java.util.Hashtable;

// import org.irisa.triskell.MT.DataTypes.Java.CollectionValue;
import org.irisa.triskell.MT.DataTypes.Java.Type;
import org.irisa.triskell.MT.DataTypes.Java.Value;
import org.irisa.triskell.MT.DataTypes.Java.commands.InstanciableType;
import org.irisa.triskell.MT.DataTypes.Java.commands.Collection.CollectionType;
// import org.irisa.triskell.MT.DataTypes.Java.defaultImpl.CollectionValueImpl;
import org.irisa.triskell.MT.DataTypes.Java.defaultImpl.SetValueImpl;

/**
 * implementation of the Type for Set objects
 * @author ffondeme
 */
public class SetType extends CollectionType implements InstanciableType {
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

	public Value instanciate() {
		return new SetValueImpl(false, null, new Value [0], false);
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
