package org.irisa.triskell.MT.DataTypes.Java.defaultImpl;

import org.irisa.triskell.MT.DataTypes.Java.commands.*;
import java.io.*;
import java.util.*;
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

public class TupleElementImpl 
{
    protected final String name;
    public String getName () {
        return this.name;
    }

    protected final org.irisa.triskell.MT.DataTypes.Java.Value value;
    public org.irisa.triskell.MT.DataTypes.Java.Value getValue () {
        return this.value;
    }
    public int cardValue () {
        if ( this.value == null ) return 0;
        else return 1;
    }


    public TupleElementImpl(
        String name,
        org.irisa.triskell.MT.DataTypes.Java.Value value)
    {
		this.name = name;
		this.value = value;
    }
}
