/*
 * $Id: VoidValueImpl.java,v 1.2 2004-02-16 17:01:59 dvojtise Exp $
 * @author : ffondeme
 * 
 * Copyright 2004 - INRIA - LGPL license
 */
package org.irisa.triskell.MT.DataTypes.Java.defaultImpl;

import org.irisa.triskell.MT.DataTypes.Java.commands.Void.VoidCommandGroup;
import org.irisa.triskell.MT.DataTypes.Java.commands.Void.VoidType;

import org.irisa.triskell.MT.DataTypes.Java.Value;

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
