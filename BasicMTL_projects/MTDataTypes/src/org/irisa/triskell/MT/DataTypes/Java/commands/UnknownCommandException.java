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

public class UnknownCommandException 
    extends java.lang.Exception
{
	private static String createMessage(
        org.irisa.triskell.MT.DataTypes.Java.Value invoker,
        String name,
        org.irisa.triskell.MT.DataTypes.Java.Value[] arguments,
        String[] discriminants,
        String additionalInforations) {
        StringBuffer sb = new StringBuffer();
        sb.append("Problem accessing resource ");
        sb.append(name);
        if (arguments != null && arguments.length > 0) {
        	sb.append(" with arguments");
        	for (int i = 0; i < arguments.length; ++i) {
        		if (i > 0)
        			sb.append("\n\t");
        		sb.append('[');
        		sb.append(arguments[0].toString());
        		sb.append(']');
        	}
        	sb.append('\n');
        }
        if (discriminants != null && discriminants.length > 0) {
        	sb.append(" with discriminants ");
        	for (int i = 0; i < discriminants.length; ++i) {
        		if (i > 0)
        			sb.append(' ');
        		sb.append(discriminants[0]);
        	}
        }
        if (additionalInforations != null && additionalInforations.length() > 0) {
        	sb.append(": ");
        	sb.append(additionalInforations);
        }
        return sb.toString();
    }
	
    public String name;
    public String getName () {
        return this.name;
    }

    public String[] discriminants = null;
    public String getDiscriminants (int i) {
        return this.discriminants[i];
    }
    public String[] getDiscriminants () {
        return this.discriminants;
    }
    public int cardDiscriminants () {
        return this.discriminants.length;
    }

    protected org.irisa.triskell.MT.DataTypes.Java.Value invoker;
    public org.irisa.triskell.MT.DataTypes.Java.Value getInvoker () {
        return this.invoker;
    }
    public int cardInvoker () {
        if ( this.invoker == null ) return 0;
        else return 1;
    }

    protected org.irisa.triskell.MT.DataTypes.Java.Value[] arguments = null;
    public org.irisa.triskell.MT.DataTypes.Java.Value getArguments (int i) {
        return this.arguments[i];
    }
    public org.irisa.triskell.MT.DataTypes.Java.Value[] getArguments () {
        return this.arguments;
    }
    public int cardArguments () {
        return this.arguments.length;
    }


    public UnknownCommandException(
        org.irisa.triskell.MT.DataTypes.Java.Value invoker,
        String name,
        org.irisa.triskell.MT.DataTypes.Java.Value[] arguments,
        String[] discriminants,
        String additionalInformations)
    {
    	super(createMessage(invoker, name, arguments, discriminants, additionalInformations));
		this.invoker = invoker;
		this.name = name;
		this.arguments = arguments;
		this.discriminants = discriminants;
    }
}
