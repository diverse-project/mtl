/*
 * Created on May 27, 2003
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package org.irisa.triskell.MT.DataTypes.Java.commands.Set;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

import org.irisa.triskell.MT.DataTypes.Java.CollectionValue;
import org.irisa.triskell.MT.DataTypes.Java.StringValue;
import org.irisa.triskell.MT.DataTypes.Java.Type;
import org.irisa.triskell.MT.DataTypes.Java.Value;
import org.irisa.triskell.MT.DataTypes.Java.commands.AbstractCommand;
import org.irisa.triskell.MT.DataTypes.Java.commands.Collection.CollectionType;
import org.irisa.triskell.MT.DataTypes.Java.commands.OclAny.OclAnyType;
import org.irisa.triskell.MT.DataTypes.Java.defaultImpl.CollectionValueImpl;
import org.irisa.triskell.MT.DataTypes.Java.defaultImpl.SetValueImpl;
import org.irisa.triskell.MT.DataTypes.Java.defaultImpl.StringValueImpl;

/**
 * @author ffondeme
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class Set_union extends AbstractCommand {
	public static final Set_union TheInstance = new Set_union();

	protected Set_union() {
		super("union", new Type [] {SetType.getSetType(OclAnyType.TheInstance)}, null);
	}

	protected Value invokeInternal(Value invoker, Value[] arguments) {
		Collection collection, union, ret;
		if(invoker instanceof CollectionValueImpl) {
			collection = ((CollectionValueImpl)invoker).getTheCollectionAsCollection();
		} else {
			collection = Arrays.asList(((CollectionValue)invoker).getTheCollection());
		}
		ret = new ArrayList(collection);
		if (arguments[0] instanceof CollectionValueImpl) {
			union = ((CollectionValueImpl)arguments[0]).getTheCollectionAsCollection();
		} else {
			union = Arrays.asList(((CollectionValue)arguments[0]).getTheCollection());
		}
		Iterator unionIt = union.iterator();
		Value v;
		while (unionIt.hasNext()) {
			v = (Value)unionIt.next();
			//comparing to collection because union is supposed to be a set
			if (! collection.contains(v))
				ret.add(v);
		}
		return new SetValueImpl(false, null, ret, false);
	}
}
