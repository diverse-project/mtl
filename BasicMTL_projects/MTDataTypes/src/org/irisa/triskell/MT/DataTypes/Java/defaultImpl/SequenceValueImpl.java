package org.irisa.triskell.MT.DataTypes.Java.defaultImpl;

import java.util.*;

import org.irisa.triskell.MT.DataTypes.Java.commands.*;
import org.irisa.triskell.MT.DataTypes.Java.commands.Collection.CollectionType;
import org.irisa.triskell.MT.DataTypes.Java.commands.OclAny.OclAnyCommandGroup;
import org.irisa.triskell.MT.DataTypes.Java.commands.OclAny.OclAnyType;
import org.irisa.triskell.MT.DataTypes.Java.commands.Sequence.SequenceCommandGroup;
import org.irisa.triskell.MT.DataTypes.Java.commands.Sequence.SequenceType;

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

public class SequenceValueImpl 
    extends org.irisa.triskell.MT.DataTypes.Java.defaultImpl.CollectionValueImpl
{

    public SequenceValueImpl(
        boolean isUndefined,
        String errorMessage,
        org.irisa.triskell.MT.DataTypes.Java.Value[] theCollection)
    {
        this(isUndefined, errorMessage, theCollection, CollectionKind.getSequence_kind(), SequenceType.getSequenceType(OclAnyType.TheInstance), SequenceCommandGroup.getSequenceCommandGroup(SequenceType.getSequenceType(OclAnyType.TheInstance)));
    }

    public SequenceValueImpl(
        boolean isUndefined,
        String errorMessage,
        java.util.Collection theCollection)
    {
        this(isUndefined, errorMessage, theCollection, CollectionKind.getSequence_kind(), SequenceType.getSequenceType(OclAnyType.TheInstance), SequenceCommandGroup.getSequenceCommandGroup(SequenceType.getSequenceType(OclAnyType.TheInstance)));
    }
    
	protected SequenceValueImpl(
		boolean isUndefined,
		String errorMessage,
		Value[] theCollection,
		CollectionKind theKind,
		CollectionType type,
		CommandGroup commands) {
		super(
			isUndefined,
			errorMessage,
			theCollection,
			theKind,
			false,
			type,
			commands);
	}

	protected SequenceValueImpl(
		boolean isUndefined,
		String errorMessage,
		Collection theCollection,
		CollectionKind theKind,
		CollectionType type,
		CommandGroup commands) {
		super(
			isUndefined,
			errorMessage,
			theCollection,
			theKind,
			false,
			type,
			commands);
	}
}
