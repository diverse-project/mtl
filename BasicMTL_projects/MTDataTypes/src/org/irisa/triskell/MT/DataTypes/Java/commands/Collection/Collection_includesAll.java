/*
 * Created on May 27, 2003
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package org.irisa.triskell.MT.DataTypes.Java.commands.Collection;

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
import org.irisa.triskell.MT.DataTypes.Java.commands.OclAny.OclAnyType;
import org.irisa.triskell.MT.DataTypes.Java.defaultImpl.BooleanValueImpl;
import org.irisa.triskell.MT.DataTypes.Java.defaultImpl.CollectionValueImpl;
import org.irisa.triskell.MT.DataTypes.Java.defaultImpl.SetValueImpl;
import org.irisa.triskell.MT.DataTypes.Java.defaultImpl.StringValueImpl;

/**
 * @author ffondeme
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class Collection_includesAll extends AbstractCommand {
	public static final Collection_includesAll TheInstance = new Collection_includesAll();

	protected Collection_includesAll() {
		super("includesAll", new Type [] {CollectionType.getCollectionType(OclAnyType.TheInstance)}, null);
	}

	protected Value invokeInternal(Value invoker, Value[] arguments) {
		Collection collection, includesAll;
		if(invoker instanceof CollectionValueImpl) {
			collection = ((CollectionValueImpl)invoker).getTheCollectionAsCollection();
		} else {
			collection = Arrays.asList(((CollectionValue)invoker).getTheCollection());
		}
		if (arguments[0] instanceof CollectionValueImpl) {
			includesAll = ((CollectionValueImpl)arguments[0]).getTheCollectionAsCollection();
		} else {
			includesAll = Arrays.asList(((CollectionValue)arguments[0]).getTheCollection());
		}
		boolean ret = true;
		Iterator includesAllIt = includesAll.iterator();
		Value v;
		while (ret && includesAllIt.hasNext()) {
			v = (Value)includesAllIt.next();
			ret = collection.contains(v);
		}
		return ret ? BooleanValueImpl.TRUE : BooleanValueImpl.FALSE;
	}
}
