/*
 * $Id: OrderedSet_append.java,v 1.2 2004-02-16 17:02:09 dvojtise Exp $
 * @author : ffondeme
 * 
 * Copyright 2004 - INRIA - LGPL license
 */
package org.irisa.triskell.MT.DataTypes.Java.commands.OrderedSet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.irisa.triskell.MT.DataTypes.Java.CollectionValue;
import org.irisa.triskell.MT.DataTypes.Java.Type;
import org.irisa.triskell.MT.DataTypes.Java.Value;
import org.irisa.triskell.MT.DataTypes.Java.commands.AbstractCommand;
import org.irisa.triskell.MT.DataTypes.Java.commands.OclAny.OclAnyType;
import org.irisa.triskell.MT.DataTypes.Java.defaultImpl.CollectionValueImpl;
import org.irisa.triskell.MT.DataTypes.Java.defaultImpl.OrderedSetValueImpl;

/**
 * @author ffondeme
 * append command for OrderedSet object 
 */
public class OrderedSet_append extends AbstractCommand {
	public static final OrderedSet_append TheInstance = new OrderedSet_append();

	protected OrderedSet_append() {
		super("append", new Type [] {OclAnyType.TheInstance}, null);
	}

	protected Value invokeInternal(Value invoker, Value[] arguments) {
		List collection;
		if(invoker instanceof CollectionValueImpl) {
			collection = new ArrayList(((CollectionValueImpl)invoker).getTheCollectionAsCollection());
		} else {
			collection = new ArrayList(Arrays.asList(((CollectionValue)invoker).getTheCollection()));
		}
		//The java add on List adds at the end of the collection...
		if (! collection.contains(arguments[0]))
			collection.add(arguments[0]);
		return new OrderedSetValueImpl(false, null, collection, false);
	}
}
