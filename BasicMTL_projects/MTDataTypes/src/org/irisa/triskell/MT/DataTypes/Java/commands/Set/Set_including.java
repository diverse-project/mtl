/* $Id: Set_including.java,v 1.2 2004-01-22 12:35:59 dvojtise Exp $
 * Created on May 27, 2003
 *
 */
package org.irisa.triskell.MT.DataTypes.Java.commands.Set;

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
 * Implementation of the including command applicable to a Set
 * It returns the set containing all elements of self plus object.
 * post: result->forAll(elem | self->includes(elem) or (elem = object))
 * post: self- >forAll(elem | result->includes(elem))
 * post: result->includes(object)
 */
public class Set_including extends AbstractCommand {
	public static final Set_including TheInstance = new Set_including();

	protected Set_including() {
		super("including", new Type [] {OclAnyType.TheInstance}, null);
	}

	protected Value invokeInternal(Value invoker, Value[] arguments) {
		List collection;
		if(invoker instanceof CollectionValueImpl) {
			collection = new ArrayList(((CollectionValueImpl)invoker).getTheCollectionAsCollection());
		} else {
			collection = new ArrayList(Arrays.asList(((CollectionValue)invoker).getTheCollection()));
		}
		if (! collection.contains(arguments[0]))
			collection.add(arguments[0]);
		return new SetValueImpl(false, null, collection, false);
	}
}
