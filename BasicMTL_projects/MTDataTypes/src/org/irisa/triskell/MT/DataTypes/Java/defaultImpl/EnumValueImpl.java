/*
 * $Id: EnumValueImpl.java,v 1.2 2004-02-16 17:01:58 dvojtise Exp $
 * @author : ffondeme
 * 
 * Copyright 2004 - INRIA - LGPL license
 */
package org.irisa.triskell.MT.DataTypes.Java.defaultImpl;

import org.irisa.triskell.MT.DataTypes.Java.commands.*;
import org.irisa.triskell.MT.DataTypes.Java.commands.enum.EnumCommandGroup;
import org.irisa.triskell.MT.DataTypes.Java.commands.enum.EnumType;


import org.irisa.triskell.MT.DataTypes.Java.Type;

/**
 * 
 * Default implementation for Enum objects
 */
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
