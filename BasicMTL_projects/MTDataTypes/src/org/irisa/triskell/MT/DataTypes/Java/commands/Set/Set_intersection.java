/* $Id: Set_intersection.java,v 1.2 2004-01-22 12:35:58 dvojtise Exp $
 * Created on May 27, 2003
 *
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
 * @author ffondeme
 * implementation of the symmetricDifference command applicable to a Set
 * The intersection of self and s (i.e, the set of all elements that are in both self and s).
 * post: result->forAll(elem | self->includes(elem) and s->includes(elem))
 * post: self->forAll(elem | s ->includes(elem) = result->includes(elem))
 * post: s ->forAll(elem | self->includes(elem) = result->includes(elem))
 */
public class Set_intersection extends AbstractCommand {
	public static final Set_intersection TheInstance = new Set_intersection();

	protected Set_intersection() {
		super("intersection", new Type [] {SetType.getSetType(OclAnyType.TheInstance)}, null);
	}

	protected Value invokeInternal(Value invoker, Value[] arguments) {
		Collection collection, intersection, ret;
		if(invoker instanceof CollectionValueImpl) {
			collection = ((CollectionValueImpl)invoker).getTheCollectionAsCollection();
		} else {
			collection = Arrays.asList(((CollectionValue)invoker).getTheCollection());
		}
		if (arguments[0] instanceof CollectionValueImpl) {
			intersection = ((CollectionValueImpl)arguments[0]).getTheCollectionAsCollection();
		} else {
			intersection = Arrays.asList(((CollectionValue)arguments[0]).getTheCollection());
		}
		ret = new ArrayList(collection.size() + intersection.size());
		Iterator unionIt = intersection.iterator();
		Value v;
		while (unionIt.hasNext()) {
			v = (Value)unionIt.next();
			if (collection.contains(v) && intersection.contains(v))
				ret.add(v);
		}
		return new SetValueImpl(false, null, ret, false);
	}
}
