package org.irisa.triskell.MT.DataTypes.Java.defaultImpl;

import org.irisa.triskell.MT.DataTypes.Java.BooleanValue;
import org.irisa.triskell.MT.DataTypes.Java.Type;
import org.irisa.triskell.MT.DataTypes.Java.commands.CommandGroup;
import org.irisa.triskell.MT.DataTypes.Java.commands.Boolean.BooleanCommandGroup;
import org.irisa.triskell.MT.DataTypes.Java.commands.Boolean.BooleanType;

public class BooleanValueImpl 
    extends org.irisa.triskell.MT.DataTypes.Java.defaultImpl.PrimitiveValueImpl
    implements org.irisa.triskell.MT.DataTypes.Java.BooleanValue
{
    public static final BooleanValue TRUE = new BooleanValueImpl(false, null, true);
    public static final BooleanValue FALSE = new BooleanValueImpl(false, null, false);

    private final boolean theBoolean;


    public BooleanValueImpl(
        boolean isUndefined,
        String errorMessage,
        boolean theBoolean)
    {
		this(isUndefined, errorMessage, theBoolean, BooleanType.TheInstance, BooleanCommandGroup.TheInstance);
    }

    protected BooleanValueImpl(
        boolean isUndefined,
        String errorMessage,
        boolean theBoolean,
        Type type,
        CommandGroup commands)
    {
        super(isUndefined, errorMessage, Boolean.toString(theBoolean), type, commands);
		this.theBoolean = theBoolean;
    }

    public boolean getTheBoolean()
    {
    	return this.theBoolean;
    }

    public boolean checkValueEquality(
        org.irisa.triskell.MT.DataTypes.Java.Value rhs)
    {
		return (rhs instanceof BooleanValue) && ((BooleanValue)rhs).getTheBoolean() == this.getTheBoolean();
    }

    public void accept(
        org.irisa.triskell.MT.DataTypes.Java.ValueVisitor visitor)
    {
		visitor.visitBooleanValue(this);
    }
}
