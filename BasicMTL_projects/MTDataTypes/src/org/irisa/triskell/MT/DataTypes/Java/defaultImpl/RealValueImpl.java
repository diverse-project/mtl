/*
 * $Id: RealValueImpl.java,v 1.3 2004-02-16 17:01:58 dvojtise Exp $
 * @author : ffondeme
 * 
 * Copyright 2004 - INRIA - LGPL license
 */
package org.irisa.triskell.MT.DataTypes.Java.defaultImpl;

import org.irisa.triskell.MT.DataTypes.Java.commands.*;
import org.irisa.triskell.MT.DataTypes.Java.commands.Real.RealCommandGroup;
import org.irisa.triskell.MT.DataTypes.Java.commands.Real.RealType;

import org.irisa.triskell.MT.DataTypes.Java.Type;
import org.irisa.triskell.MT.DataTypes.Java.RealValue;

/**
 * 
 * Default implementation for RealValue 
 */
public class RealValueImpl 
    extends org.irisa.triskell.MT.DataTypes.Java.defaultImpl.PrimitiveValueImpl
    implements org.irisa.triskell.MT.DataTypes.Java.RealValue {

    public final float theReal;


    public RealValueImpl(
        boolean isUndefined,
        String errorMessage,
        float theReal)
    {
		this(isUndefined, errorMessage, theReal, Float.toString(theReal), RealType.TheInstance, RealCommandGroup.TheInstance);
    }

    protected RealValueImpl(
        boolean isUndefined,
        String errorMessage,
        float theReal,
        String valueAsString,
        Type type,
        CommandGroup commands)
    {
        super(isUndefined, errorMessage, valueAsString, type, commands);
    	this.theReal = theReal;
    }

    public float getTheReal()
    {
		return this.theReal;
    }

    public boolean checkValueEquality(
        org.irisa.triskell.MT.DataTypes.Java.Value rhs)
    {
		return (rhs instanceof RealValue) && ((RealValue)rhs).getTheReal() == this.getTheReal();
    }

    public void accept(
        org.irisa.triskell.MT.DataTypes.Java.ValueVisitor visitor)
    {
		visitor.visitRealValue(this);
    }
}
