/*
 * Created on May 27, 2003
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package org.irisa.triskell.MT.DataTypes.Java.commands.Bag;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.irisa.triskell.MT.DataTypes.Java.CollectionValue;
import org.irisa.triskell.MT.DataTypes.Java.Type;
import org.irisa.triskell.MT.DataTypes.Java.Value;
import org.irisa.triskell.MT.DataTypes.Java.commands.AbstractCommand;
import org.irisa.triskell.MT.DataTypes.Java.commands.OclAny.OclAnyType;
import org.irisa.triskell.MT.DataTypes.Java.defaultImpl.BagValueImpl;
import org.irisa.triskell.MT.DataTypes.Java.defaultImpl.CollectionValueImpl;

/**
 * @author ffondeme
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class Bag_excluding extends AbstractCommand {
	public static final Bag_excluding TheInstance = new Bag_excluding();

	protected Bag_excluding() {
		super("excluding", new Type [] {OclAnyType.TheInstance}, null);
	}

	protected Value invokeInternal(Value invoker, Value[] arguments) {
		List collection;
		if(invoker instanceof CollectionValueImpl) {
			collection = new ArrayList(((CollectionValueImpl)invoker).getTheCollectionAsCollection());
		} else {
			collection = new ArrayList(Arrays.asList(((CollectionValue)invoker).getTheCollection()));
		}
		int i = collection.indexOf(arguments[0]);
		while (i != -1) {
			collection.remove(i);
		}
		return new BagValueImpl(false, null, collection);
	}
}
