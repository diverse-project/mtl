package org.irisa.triskell.MT.DataTypes.Java.defaultImpl;

import java.util.*;
import org.irisa.triskell.MT.DataTypes.Java.commands.*;
import org.irisa.triskell.MT.DataTypes.Java.commands.enum.EnumCommandGroup;
import org.irisa.triskell.MT.DataTypes.Java.commands.enum.EnumType;

import java.io.*;

import org.irisa.triskell.MT.DataTypes.Java.*;
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

public class EnumValueImpl 
    extends org.irisa.triskell.MT.DataTypes.Java.defaultImpl.PrimitiveValueImpl
    implements org.irisa.triskell.MT.DataTypes.Java.EnumValue
{
    protected String[] enumeration = null;


    public EnumValueImpl(
        boolean isUndefined,
        String errorMessage,
        String theEnum,
        String[] enumeration)
    {
        this(isUndefined, errorMessage, theEnum, enumeration, new EnumType(enumeration), null);
    }


    protected EnumValueImpl(
        boolean isUndefined,
        String errorMessage,
        String theEnum,
        String[] enumeration,
        Type type,
        CommandGroup commands)
    {
        super(isUndefined, errorMessage, theEnum, type, commands == null && (type instanceof EnumType) ? new EnumCommandGroup((EnumType)type) : commands);
		this.enumeration = enumeration;
    }

    public String getTheEnum()
    {
    	return super.getValue();
    }

    public String[] getEnumeration()
    {
    	return this.enumeration;
    }

    public void accept(
        org.irisa.triskell.MT.DataTypes.Java.ValueVisitor visitor)
    {
		visitor.visitEnumValue(this);
    }
}
