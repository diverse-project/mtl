package org.irisa.triskell.MT.DataTypes.Java.defaultImpl;

import java.util.Collection;

import org.irisa.triskell.MT.DataTypes.Java.CollectionKind;
import org.irisa.triskell.MT.DataTypes.Java.Value;
import org.irisa.triskell.MT.DataTypes.Java.commands.CommandGroup;
import org.irisa.triskell.MT.DataTypes.Java.commands.Bag.BagCommandGroup;
import org.irisa.triskell.MT.DataTypes.Java.commands.Bag.BagType;
import org.irisa.triskell.MT.DataTypes.Java.commands.Collection.CollectionType;
import org.irisa.triskell.MT.DataTypes.Java.commands.OclAny.OclAnyType;

public class BagValueImpl 
    extends org.irisa.triskell.MT.DataTypes.Java.defaultImpl.CollectionValueImpl
{

    public BagValueImpl(
        boolean isUndefined,
        String errorMessage,
        org.irisa.triskell.MT.DataTypes.Java.Value[] theCollection)
    {
        this(isUndefined, errorMessage, theCollection, CollectionKind.getBag_kind(), BagType.getBagType(OclAnyType.TheInstance), BagCommandGroup.getBagCommandGroup(BagType.getBagType(OclAnyType.TheInstance)));
    }

    public BagValueImpl(
        boolean isUndefined,
        String errorMessage,
        java.util.Collection theCollection)
    {
        this(isUndefined, errorMessage, theCollection, CollectionKind.getBag_kind(), BagType.getBagType(OclAnyType.TheInstance), BagCommandGroup.getBagCommandGroup(BagType.getBagType(OclAnyType.TheInstance)));
    }

	protected BagValueImpl(
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

	protected BagValueImpl(
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
}
