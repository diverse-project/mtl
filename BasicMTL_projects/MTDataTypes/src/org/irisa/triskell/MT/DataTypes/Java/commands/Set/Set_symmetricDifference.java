/* $Id: Set_symmetricDifference.java,v 1.1 2004-01-22 12:35:59 dvojtise Exp $
 * Created on Jan 18, 2004
 */
package org.irisa.triskell.MT.DataTypes.Java.commands.Set;

import java.util.Collection;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.Arrays;

import org.irisa.triskell.MT.DataTypes.Java.CollectionValue;
import org.irisa.triskell.MT.DataTypes.Java.Type;
import org.irisa.triskell.MT.DataTypes.Java.Value;
import org.irisa.triskell.MT.DataTypes.Java.commands.AbstractCommand;
import org.irisa.triskell.MT.DataTypes.Java.commands.OclAny.OclAnyType;
import org.irisa.triskell.MT.DataTypes.Java.defaultImpl.CollectionValueImpl;
import org.irisa.triskell.MT.DataTypes.Java.defaultImpl.SetValueImpl;

/**
 * @author dvojtise
 *
 * Implementation of the symmetricDifference command applicable to a Set
 * Returns the sets containing all the elements that are in self or s, but not in both.
 * post: result->forAll(elem | self->includes(elem) xor s->includes(elem))
 * post: self->forAll(elem | result->includes(elem) = s ->excludes(elem))
 * post: s ->forAll(elem | result->includes(elem) = self->excludes(elem))
 */
public class Set_symmetricDifference extends AbstractCommand {
	public static final Set_symmetricDifference TheInstance = new Set_symmetricDifference();

	protected Set_symmetricDifference() {
		super("symmetricDifference", new Type [] {SetType.getSetType(OclAnyType.TheInstance)}, null);
	}

	protected Value invokeInternal(Value invoker, Value[] arguments) {
		Collection collection, symmetricDifference, ret;
		Value v;
		
		// System.out.println("Set_symmetricDifference invokeInternal(Value invoker, Value[] arguments)");		
		if(invoker instanceof CollectionValueImpl) {
			collection = ((CollectionValueImpl)invoker).getTheCollectionAsCollection();
		} else {
			collection = Arrays.asList(((CollectionValue)invoker).getTheCollection());
		}
		if (arguments[0] instanceof CollectionValueImpl) {
			symmetricDifference = ((CollectionValueImpl)arguments[0]).getTheCollectionAsCollection();
		} else {
			symmetricDifference = Arrays.asList(((CollectionValue)arguments[0]).getTheCollection());
		}
		ret = new ArrayList(collection.size() + symmetricDifference.size());
		
		// adds all elements from this Set which aren't present in the parameter Set
		Iterator unionIt2 = collection.iterator();
		while (unionIt2.hasNext()) {
			v = (Value)unionIt2.next();
			if (!(collection.contains(v) && symmetricDifference.contains(v)))
				ret.add(v);
		}
		// adds all elements from the parameter Set which aren't present in this set
		Iterator unionIt1 = symmetricDifference.iterator();
		while (unionIt1.hasNext()) {
			v = (Value)unionIt1.next();
			if (!(collection.contains(v) && symmetricDifference.contains(v)))
				ret.add(v);
		}
		return new SetValueImpl(false, null, ret, false);
	}
}
