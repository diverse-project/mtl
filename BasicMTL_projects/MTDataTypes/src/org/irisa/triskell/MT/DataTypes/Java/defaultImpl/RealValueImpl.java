package org.irisa.triskell.MT.DataTypes.Java.defaultImpl;

import org.irisa.triskell.MT.DataTypes.Java.commands.*;
import org.irisa.triskell.MT.DataTypes.Java.commands.Real.RealCommandGroup;
import org.irisa.triskell.MT.DataTypes.Java.commands.Real.RealType;

import java.io.*;
import java.util.*;

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

public class RealValueImpl 
    extends org.irisa.triskell.MT.DataTypes.Java.defaultImpl.PrimitiveValueImpl
    implements org.irisa.triskell.MT.DataTypes.Java.RealValue {

    public final float theReal;


    public RealValueImpl(
        boolean isUndefined,
        String errorMessage,
        float theReal)
    {
		this(isUndefined, errorMessage, theReal, RealType.TheInstance, RealCommandGroup.TheInstance);
    }

    protected RealValueImpl(
        boolean isUndefined,
        String errorMessage,
        float theReal,
        Type type,
        CommandGroup commands)
    {
        super(isUndefined, errorMessage, Float.toString(theReal), type, commands);
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
