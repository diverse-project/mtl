/*
 * $Id: TupleElementImpl.java,v 1.2 2004-02-16 17:01:59 dvojtise Exp $
 * @author : ffondeme
 * 
 * Copyright 2004 - INRIA - LGPL license
 */
package org.irisa.triskell.MT.DataTypes.Java.defaultImpl;

/**
 * 
 * Default implementation for TupleElement
 */
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
