/* $Id: Set_sub.java,v 1.1 2004-05-19 22:21:27 ffondeme Exp $
 * Created on May 27, 2003
 *
 */
package org.irisa.triskell.MT.DataTypes.Java.commands.Set;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
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
 *
 * Implementation of the - command applicable to a Set
 * The elements of self, which are not in s.
 * post: result->forAll(elem | self->includes(elem) and s->excludes(elem))
 * post: self ->forAll(elem | result->includes(elem) = s->excludes(elem))
 */
public class Set_sub extends AbstractCommand {
	public static final Set_sub TheInstance = new Set_sub();

	protected Set_sub() {
		super("-", new Type [] {SetType.getSetType(OclAnyType.TheInstance)}, null);
	}

	protected Value invokeInternal(Value invoker, Value[] arguments) {
		Collection collection, diff, ret;
		if(invoker instanceof CollectionValueImpl) {
			collection = ((CollectionValueImpl)invoker).getTheCollectionAsCollection();
		} else {
			collection = Arrays.asList(((CollectionValue)invoker).getTheCollection());
		}
		if (arguments[0] instanceof CollectionValueImpl) {
			diff = ((CollectionValueImpl)arguments[0]).getTheCollectionAsCollection();
		} else {
			diff = Arrays.asList(((CollectionValue)arguments[0]).getTheCollection());
		}
		ret = new ArrayList(collection.size());
		Iterator collIt = collection.iterator();
		Value v;
		while (collIt.hasNext()) {
			v = (Value)collIt.next();
			if (! diff.contains(v))
				ret.add(v);
		}
		return new SetValueImpl(false, null, ret, false);
	}
}
