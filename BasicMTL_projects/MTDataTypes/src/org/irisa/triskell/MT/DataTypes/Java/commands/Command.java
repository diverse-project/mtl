package org.irisa.triskell.MT.DataTypes.Java.commands;

import java.util.*;
import org.irisa.triskell.MT.DataTypes.Java.Value;
import org.irisa.triskell.MT.DataTypes.Java.PrimitiveValue;
import org.irisa.triskell.MT.DataTypes.Java.BooleanValue;
import org.irisa.triskell.MT.DataTypes.Java.StringValue;
import org.irisa.triskell.MT.DataTypes.Java.RealValue;
import org.irisa.triskell.MT.DataTypes.Java.EnumValue;
import org.irisa.triskell.MT.DataTypes.Java.IntegerValue;
import org.irisa.triskell.MT.DataTypes.Java.CollectionValue;
import org.irisa.triskell.MT.DataTypes.Java.CollectionKind;
import org.irisa.triskell.MT.DataTypes.Java.TupleValue;
import java.lang.Exception;

public interface Command 
{

     String getName();

	/**
	 * @param arguments null means no arguments
	 * @param discriminants not tested if null
	 * @return boolean
	 */
     boolean isInvokable(
        org.irisa.triskell.MT.DataTypes.Java.Value[] arguments,
        String[] discriminants);

     org.irisa.triskell.MT.DataTypes.Java.Value invoke(
        org.irisa.triskell.MT.DataTypes.Java.Value invoker,
        org.irisa.triskell.MT.DataTypes.Java.Value[] arguments);

     String toString ();       
}
