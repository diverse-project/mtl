/*
 * $Id: OrderedSet_insertAt.java,v 1.1 2004-04-14 03:56:13 ffondeme Exp $
 * @author : ffondeme
 * 
 * Copyright 2004 - INRIA - LGPL license
 */
package org.irisa.triskell.MT.DataTypes.Java.commands.OrderedSet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.irisa.triskell.MT.DataTypes.Java.CollectionValue;
import org.irisa.triskell.MT.DataTypes.Java.IntegerValue;
import org.irisa.triskell.MT.DataTypes.Java.Type;
import org.irisa.triskell.MT.DataTypes.Java.Value;
import org.irisa.triskell.MT.DataTypes.Java.commands.AbstractCommand;
import org.irisa.triskell.MT.DataTypes.Java.commands.Integer.IntegerType;
import org.irisa.triskell.MT.DataTypes.Java.commands.OclAny.OclAnyType;
import org.irisa.triskell.MT.DataTypes.Java.defaultImpl.CollectionValueImpl;
import org.irisa.triskell.MT.DataTypes.Java.defaultImpl.OrderedSetValueImpl;

/**
 * @author ffondeme
 *
 * Action when receiving an including command 
 */
public class OrderedSet_insertAt extends AbstractCommand {
	public static final OrderedSet_insertAt TheInstance = new OrderedSet_insertAt();

	protected OrderedSet_insertAt() {
		super("insertAt", new Type [] {IntegerType.TheInstance, OclAnyType.TheInstance}, null);
	}

	protected Value invokeInternal(Value invoker, Value[] arguments) {
		List collection;
		if(invoker instanceof CollectionValueImpl) {
			collection = new ArrayList(((CollectionValueImpl)invoker).getTheCollectionAsCollection());
		} else {
			collection = new ArrayList(Arrays.asList(((CollectionValue)invoker).getTheCollection()));
		}
		//The java add on List adds at the end of the collection...
		if (! collection.contains(arguments[1]))
			collection.add(((IntegerValue)arguments[0]).getTheInteger()+1, arguments[1]);
		return new OrderedSetValueImpl(false, null, collection, false);
	}
}
