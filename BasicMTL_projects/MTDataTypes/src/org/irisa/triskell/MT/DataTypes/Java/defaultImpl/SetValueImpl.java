/*
 * $Id: SetValueImpl.java,v 1.2 2004-02-16 17:01:59 dvojtise Exp $
 * @author : ffondeme
 * 
 * Copyright 2004 - INRIA - LGPL license
 */
 package org.irisa.triskell.MT.DataTypes.Java.defaultImpl;

import java.util.*;

import org.irisa.triskell.MT.DataTypes.Java.commands.*;
import org.irisa.triskell.MT.DataTypes.Java.commands.Collection.CollectionType;
import org.irisa.triskell.MT.DataTypes.Java.commands.OclAny.OclAnyType;
import org.irisa.triskell.MT.DataTypes.Java.commands.Set.SetCommandGroup;
import org.irisa.triskell.MT.DataTypes.Java.commands.Set.SetType;

import org.irisa.triskell.MT.DataTypes.Java.CollectionKind;
import org.irisa.triskell.MT.DataTypes.Java.Value;

/**
 * 
 * Default implementation for SetValue
 */
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
