package org.irisa.triskell.MT.DataTypes.Java.defaultImpl;

import java.util.*;

import org.irisa.triskell.MT.DataTypes.Java.commands.*;
import org.irisa.triskell.MT.DataTypes.Java.commands.Collection.CollectionType;
import org.irisa.triskell.MT.DataTypes.Java.commands.OclAny.OclAnyType;
import org.irisa.triskell.MT.DataTypes.Java.commands.Set.SetCommandGroup;
import org.irisa.triskell.MT.DataTypes.Java.commands.Set.SetType;

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

public class SetValueImpl 
    extends org.irisa.triskell.MT.DataTypes.Java.defaultImpl.CollectionValueImpl
{

    public SetValueImpl(
        boolean isUndefined,
        String errorMessage,
        org.irisa.triskell.MT.DataTypes.Java.Value[] theCollection)
    {
        this(isUndefined, errorMessage, theCollection, true);
    }

    public SetValueImpl(
        boolean isUndefined,
        String errorMessage,
        java.util.Collection theCollection)
    {
        this(isUndefined, errorMessage, theCollection, true);
    }

    public SetValueImpl(
        boolean isUndefined,
        String errorMessage,
        org.irisa.triskell.MT.DataTypes.Java.Value[] theCollection,
        boolean checkUnicityConstraint)
    {
        this(isUndefined, errorMessage, theCollection, CollectionKind.getSet_kind(), checkUnicityConstraint, SetType.getSetType(OclAnyType.TheInstance), SetCommandGroup.getSetCommandGroup(SetType.getSetType(OclAnyType.TheInstance)));
    }

    public SetValueImpl(
        boolean isUndefined,
        String errorMessage,
        java.util.Collection theCollection,
        boolean checkUnicityConstraint)
    {
        this(isUndefined, errorMessage, theCollection, CollectionKind.getSet_kind(), checkUnicityConstraint, SetType.getSetType(OclAnyType.TheInstance), SetCommandGroup.getSetCommandGroup(SetType.getSetType(OclAnyType.TheInstance)));
    }

	protected SetValueImpl(
		boolean isUndefined,
		String errorMessage,
		Value[] theCollection,
		CollectionKind theKind,
		boolean checkSet,
		CollectionType type,
		CommandGroup commands) {
		super(
			isUndefined,
			errorMessage,
			theCollection,
			theKind,
			checkSet,
			type,
			commands);
	}

	protected SetValueImpl(
		boolean isUndefined,
		String errorMessage,
		Collection theCollection,
		CollectionKind theKind,
		boolean checkSet,
		CollectionType type,
		CommandGroup commands) {
		super(
			isUndefined,
			errorMessage,
			theCollection,
			theKind,
			checkSet,
			type,
			commands);
	}
}
