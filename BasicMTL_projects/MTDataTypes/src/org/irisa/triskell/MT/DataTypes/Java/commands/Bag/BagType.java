/*
 * $Id: BagType.java,v 1.4 2004-01-22 12:39:23 dvojtise Exp $
 * Created on May 28, 2003
 */
package org.irisa.triskell.MT.DataTypes.Java.commands.Bag;

// import java.util.Arrays;
// import java.util.Collection;
import java.util.Hashtable;

// import org.irisa.triskell.MT.DataTypes.Java.CollectionValue;
import org.irisa.triskell.MT.DataTypes.Java.Type;
import org.irisa.triskell.MT.DataTypes.Java.Value;
import org.irisa.triskell.MT.DataTypes.Java.commands.InstanciableType;
import org.irisa.triskell.MT.DataTypes.Java.commands.Collection.CollectionType;
import org.irisa.triskell.MT.DataTypes.Java.defaultImpl.BagValueImpl;
// import org.irisa.triskell.MT.DataTypes.Java.defaultImpl.CollectionValueImpl;

/**
 * implementation of the Type for Bag objects
 * @author ffondeme
 */
public class BagType extends CollectionType implements InstanciableType {
	public static final String BagName = "Bag";
	
	// keys are element types (Type) and values are collection collection types (CollectionType) - of this exact type !	private static Hashtable defined
	private static final Hashtable bagTypes = new Hashtable();	
	public static BagType getBagType (Type elementType) {
		BagType ret = (BagType)bagTypes.get(elementType);
		if (ret == null) {
			ret = new BagType(elementType);
			bagTypes.put(elementType, ret);
		}
		return ret;
	}
	
	protected BagType(Type elementType) {
		super(BagName, elementType, new Type [] {CollectionType.getCollectionType(elementType)});
	}

	public boolean conformsTo(Type type) throws UnsupportedOperationException {
		if (this.equals(type))
			return true;
		Class tc = type.getClass();
		if (tc.equals(CollectionType.class) || tc.equals(BagType.class))
			return this.getElementType().conformsTo(((CollectionType)type).getElementType());
		return false;
	}
	
	public Value instanciate() {
		return new BagValueImpl(false, null, new Value [0]);
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
