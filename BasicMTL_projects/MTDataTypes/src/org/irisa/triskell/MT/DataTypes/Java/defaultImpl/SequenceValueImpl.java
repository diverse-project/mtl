/*
 * $Id: SequenceValueImpl.java,v 1.2 2004-02-16 17:01:57 dvojtise Exp $
 * @author : ffondeme
 * 
 * Copyright 2004 - INRIA - LGPL license
 */
package org.irisa.triskell.MT.DataTypes.Java.defaultImpl;

import java.util.*;

import org.irisa.triskell.MT.DataTypes.Java.commands.*;
import org.irisa.triskell.MT.DataTypes.Java.commands.Collection.CollectionType;
import org.irisa.triskell.MT.DataTypes.Java.commands.OclAny.OclAnyType;
import org.irisa.triskell.MT.DataTypes.Java.commands.Sequence.SequenceCommandGroup;
import org.irisa.triskell.MT.DataTypes.Java.commands.Sequence.SequenceType;

import org.irisa.triskell.MT.DataTypes.Java.CollectionKind;
import org.irisa.triskell.MT.DataTypes.Java.Value;

/**
 * 
 * Default implementation for SequenceValue
 */
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
