/* 
 * $Id: SequenceType.java,v 1.4 2004-01-22 12:39:22 dvojtise Exp $
 * Created on May 28, 2003
 */
package org.irisa.triskell.MT.DataTypes.Java.commands.Sequence;

// import java.util.Arrays;
// import java.util.Collection;
import java.util.Hashtable;

// import org.irisa.triskell.MT.DataTypes.Java.CollectionValue;
import org.irisa.triskell.MT.DataTypes.Java.Type;
import org.irisa.triskell.MT.DataTypes.Java.Value;
import org.irisa.triskell.MT.DataTypes.Java.commands.InstanciableType;
import org.irisa.triskell.MT.DataTypes.Java.commands.Collection.CollectionType;
// import org.irisa.triskell.MT.DataTypes.Java.defaultImpl.CollectionValueImpl;
import org.irisa.triskell.MT.DataTypes.Java.defaultImpl.SequenceValueImpl;

/**
 * implementation of the Type for Sequence objects
 * @author ffondeme
 *
 */
public class SequenceType extends CollectionType implements InstanciableType {
	public static String SequenceName = "Sequence";
	
	// keys are element types (Type) and values are collection collection types (SequenceType) - of this exact type !	private static Hashtable defined
	private static final Hashtable sequenceTypes = new Hashtable();	
	public static SequenceType getSequenceType (Type elementType) {
		SequenceType ret = (SequenceType)sequenceTypes.get(elementType);
		if (ret == null) {
			ret = new SequenceType(elementType);
			sequenceTypes.put(elementType, ret);
		}
		return ret;
	}
	
	protected SequenceType(Type elementType) {
		super(SequenceName, elementType, new Type [] {CollectionType.getCollectionType(elementType)});
	}

	public boolean conformsTo(Type type) throws UnsupportedOperationException {
		if (this.equals(type))
			return true;
		Class tc = type.getClass();
		if (tc.equals(CollectionType.class) || tc.equals(SequenceType.class))
			return this.getElementType().conformsTo(((CollectionType)type).getElementType());
		return false;
	}

	public Value instanciate() {
		return new SequenceValueImpl(false, null, new Value  [0]);
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
