/*
 * $Id: OrderedSet_prepend.java,v 1.1 2004-04-14 03:56:13 ffondeme Exp $
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
public class OrderedSet_prepend extends AbstractCommand {
	public static final OrderedSet_prepend TheInstance = new OrderedSet_prepend();

	protected OrderedSet_prepend() {
		super("prepend", new Type [] {OclAnyType.TheInstance}, null);
	}

	protected Value invokeInternal(Value invoker, Value[] arguments) {
		List collection;
		if(invoker instanceof CollectionValueImpl) {
			collection = new ArrayList(((CollectionValueImpl)invoker).getTheCollectionAsCollection());
		} else {
			collection = new ArrayList(Arrays.asList(((CollectionValue)invoker).getTheCollection()));
		}

		if (! collection.contains(arguments[0]))
			collection.add(0, arguments[0]);
		return new OrderedSetValueImpl(false, null, collection, false);
	}
}
