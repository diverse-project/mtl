/*
 * $Id: OrderedSetValueImpl.java,v 1.2 2004-02-16 17:01:58 dvojtise Exp $
 * @author : ffondeme
 * 
 * Copyright 2004 - INRIA - LGPL license
 */
package org.irisa.triskell.MT.DataTypes.Java.defaultImpl;

import java.util.*;
import org.irisa.triskell.MT.DataTypes.Java.commands.*;
import org.irisa.triskell.MT.DataTypes.Java.commands.Collection.CollectionType;
import org.irisa.triskell.MT.DataTypes.Java.commands.OclAny.OclAnyType;
import org.irisa.triskell.MT.DataTypes.Java.commands.OrderedSet.OrderedSetCommandGroup;
import org.irisa.triskell.MT.DataTypes.Java.commands.OrderedSet.OrderedSetType;

import org.irisa.triskell.MT.DataTypes.Java.CollectionKind;
import org.irisa.triskell.MT.DataTypes.Java.Value;

/**
 * 
 * Default implementation for OrderedSetValue
 */
public class OrderedSetValueImpl 
    extends org.irisa.triskell.MT.DataTypes.Java.defaultImpl.CollectionValueImpl
{

    public OrderedSetValueImpl(
        boolean isUndefined,
        String errorMessage,
        org.irisa.triskell.MT.DataTypes.Java.Value[] theCollection)
    {
        this(isUndefined, errorMessage, theCollection, true);
    }

    public OrderedSetValueImpl(
        boolean isUndefined,
        String errorMessage,
        java.util.Collection theCollection)
    {
        this(isUndefined, errorMessage, theCollection, true);
    }

    public OrderedSetValueImpl(
        boolean isUndefined,
        String errorMessage,
        org.irisa.triskell.MT.DataTypes.Java.Value[] theCollection,
        boolean checkUnicityConstraint)
    {
        this(isUndefined, errorMessage, theCollection, CollectionKind.getOrdered_set_kind(), checkUnicityConstraint, OrderedSetType.getOrderedSetType(OclAnyType.TheInstance), OrderedSetCommandGroup.getOrderedSetCommandGroup(OrderedSetType.getOrderedSetType(OclAnyType.TheInstance)));
    }

    public OrderedSetValueImpl(
        boolean isUndefined,
        String errorMessage,
        java.util.Collection theCollection,
        boolean checkUnicityConstraint)
    {
        this(isUndefined, errorMessage, theCollection, CollectionKind.getOrdered_set_kind(), checkUnicityConstraint, OrderedSetType.getOrderedSetType(OclAnyType.TheInstance), OrderedSetCommandGroup.getOrderedSetCommandGroup(OrderedSetType.getOrderedSetType(OclAnyType.TheInstance)));
    }

	protected OrderedSetValueImpl(
		boolean isUndefined,
		String errorMessage,
		Value[] theCollection,
		CollectionKind theKind,
		boolean checkOrderedSet,
		CollectionType type,
		CommandGroup commands) {
		super(
			isUndefined,
			errorMessage,
			theCollection,
			theKind,
			checkOrderedSet,
			type,
			commands);
	}

	protected OrderedSetValueImpl(
		boolean isUndefined,
		String errorMessage,
		Collection theCollection,
		CollectionKind theKind,
		boolean checkOrderedSet,
		CollectionType type,
		CommandGroup commands) {
		super(
			isUndefined,
			errorMessage,
			theCollection,
			theKind,
			checkOrderedSet,
			type,
			commands);
	}
}
