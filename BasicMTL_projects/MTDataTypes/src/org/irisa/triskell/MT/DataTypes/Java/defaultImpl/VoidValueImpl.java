package org.irisa.triskell.MT.DataTypes.Java.defaultImpl;

import java.util.*;
import org.irisa.triskell.MT.DataTypes.Java.commands.*;
import org.irisa.triskell.MT.DataTypes.Java.commands.Tuple.TupleCommandGroup;
import org.irisa.triskell.MT.DataTypes.Java.commands.Void.VoidCommandGroup;
import org.irisa.triskell.MT.DataTypes.Java.commands.Void.VoidType;

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

public class VoidValueImpl 
    extends org.irisa.triskell.MT.DataTypes.Java.defaultImpl.ValueImpl
    implements org.irisa.triskell.MT.DataTypes.Java.VoidValue
{
    protected static final VoidValueImpl TheInstance = new VoidValueImpl();
    public static VoidValueImpl getTheInstance () {
        return VoidValueImpl.TheInstance;
    }
    public static int cardTheInstance () {
        if ( VoidValueImpl.TheInstance == null ) return 0;
        else return 1;
    }


    private VoidValueImpl()
    {
        super(false, null, VoidType.TheInstance, VoidCommandGroup.TheInstance);
    }

    public boolean checkValueEquality(Value rhs)
    {
		return rhs instanceof org.irisa.triskell.MT.DataTypes.Java.VoidValue;
    }

    public void accept(
        org.irisa.triskell.MT.DataTypes.Java.ValueVisitor visitor)
    {
		visitor.visitVoidValue(this);
    }

	public String getValueAsString() {
		return "<Void>";
	}

}
