package org.irisa.triskell.MT.DataTypes.Java.defaultImpl;

import java.util.*;
import org.irisa.triskell.MT.DataTypes.Java.commands.*;
import org.irisa.triskell.MT.DataTypes.Java.commands.Collection.CollectionType;
import org.irisa.triskell.MT.DataTypes.Java.commands.OclAny.OclAnyType;
import org.irisa.triskell.MT.DataTypes.Java.commands.OrderedSet.OrderedSetCommandGroup;
import org.irisa.triskell.MT.DataTypes.Java.commands.OrderedSet.OrderedSetType;

import java.io.*;

import org.irisa.triskell.MT.DataTypes.Java.PrimitiveValue;
import org.irisa.triskell.MT.DataTypes.Java.BooleanValue;
import org.irisa.triskell.MT.DataTypes.Java.StringValue;
import org.irisa.triskell.MT.DataTypes.Java.RealValue;
import org.irisa.triskell.MT.DataTypes.Java.EnumValue;
import org.irisa.triskell.MT.DataTypes.Java.IntegerValue;
import org.irisa.triskell.MT.DataTypes.Java.CollectionValue;
import org.irisa.triskell.MT.DataTypes.Java.CollectionKind;
import org.irisa.triskell.MT.DataTypes.Java.TupleValue;
import org.irisa.triskell.MT.DataTypes.Java.Value;
import org.irisa.triskell.MT.DataTypes.Java.ValueVisitor;

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
