/* $Id: Set_excluding.java,v 1.1 2004-04-14 17:12:00 ffondeme Exp $
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
public class Set_excluding extends AbstractCommand {
	public static final Set_excluding TheInstance = new Set_excluding();

	protected Set_excluding() {
		super("excluding", new Type [] {OclAnyType.TheInstance}, null);
	}

	protected Value invokeInternal(Value invoker, Value[] arguments) {
		List collection;
		if(invoker instanceof CollectionValueImpl) {
			collection = new ArrayList(((CollectionValueImpl)invoker).getTheCollectionAsCollection());
		} else {
			collection = new ArrayList(Arrays.asList(((CollectionValue)invoker).getTheCollection()));
		}
		collection.remove(arguments[0]);
		return new SetValueImpl(false, null, collection, false);
	}
}
