/*
 * $Id: Sequence_excluding.java,v 1.1 2004-04-14 17:12:01 ffondeme Exp $
 * @author : ffondeme
 * 
 * Copyright 2004 - INRIA - LGPL license
 */
package org.irisa.triskell.MT.DataTypes.Java.commands.Sequence;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.irisa.triskell.MT.DataTypes.Java.CollectionValue;
import org.irisa.triskell.MT.DataTypes.Java.Type;
import org.irisa.triskell.MT.DataTypes.Java.Value;
import org.irisa.triskell.MT.DataTypes.Java.commands.AbstractCommand;
import org.irisa.triskell.MT.DataTypes.Java.commands.OclAny.OclAnyType;
import org.irisa.triskell.MT.DataTypes.Java.defaultImpl.CollectionValueImpl;
import org.irisa.triskell.MT.DataTypes.Java.defaultImpl.SequenceValueImpl;

/**
 * @author ffondeme
 *
 * including command for Sequence objects
 */
public class Sequence_excluding extends AbstractCommand {
	public static final Sequence_excluding TheInstance = new Sequence_excluding();

	protected Sequence_excluding() {
		super("including", new Type [] {OclAnyType.TheInstance}, null);
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
		return new SequenceValueImpl(false, null, collection);
	}
}
