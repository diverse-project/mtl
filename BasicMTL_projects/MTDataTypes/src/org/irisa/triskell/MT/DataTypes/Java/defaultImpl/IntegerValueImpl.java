package org.irisa.triskell.MT.DataTypes.Java.defaultImpl;

import org.irisa.triskell.MT.DataTypes.Java.IntegerValue;
import org.irisa.triskell.MT.DataTypes.Java.RealValue;
import org.irisa.triskell.MT.DataTypes.Java.Type;
import org.irisa.triskell.MT.DataTypes.Java.commands.CommandGroup;
import org.irisa.triskell.MT.DataTypes.Java.commands.Integer.IntegerCommandGroup;
import org.irisa.triskell.MT.DataTypes.Java.commands.Integer.IntegerType;

public class IntegerValueImpl 
    extends org.irisa.triskell.MT.DataTypes.Java.defaultImpl.RealValueImpl
    implements org.irisa.triskell.MT.DataTypes.Java.IntegerValue
{
    public final int theInteger;


    public IntegerValueImpl(
        boolean isUndefined,
        String errorMessage,
        int theInteger)
    {
		this(isUndefined, errorMessage, theInteger, IntegerType.TheInstance, IntegerCommandGroup.TheInstance);
    }

    protected IntegerValueImpl(
        boolean isUndefined,
        String errorMessage,
        int theInteger,
        Type type,
        CommandGroup commands)
    {
        super(isUndefined, errorMessage, theInteger, type, commands);
    	this.theInteger = theInteger;
    }

    public boolean checkValueEquality(
        org.irisa.triskell.MT.DataTypes.Java.Value rhs)
    {
		return (rhs instanceof IntegerValue) ? (((IntegerValue)rhs).getTheInteger() == this.getTheInteger()) : ((rhs instanceof RealValue) && rhs.equals(this));
    }

    public int getTheInteger()
    {
		return this.theInteger;
    }

    public void accept(
        org.irisa.triskell.MT.DataTypes.Java.ValueVisitor visitor)
    {
		visitor.visitIntegerValue(this);
    }
}
