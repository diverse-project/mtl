package org.irisa.triskell.MT.DataTypes.Java.defaultImpl;

import org.irisa.triskell.MT.DataTypes.Java.commands.*;
import org.irisa.triskell.MT.DataTypes.Java.commands.Iterator.IteratorType;
import org.irisa.triskell.MT.DataTypes.Java.commands.OclString.StringCommandGroup;
import org.irisa.triskell.MT.DataTypes.Java.commands.OclString.StringType;

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

public class IteratorValueImpl 
    extends org.irisa.triskell.MT.DataTypes.Java.defaultImpl.ValueImpl
{
	public static UndefinedValueImpl NoMoreElement = new UndefinedValueImpl("No more element in the collection.");
	
	private final Collection values;
	private transient int index;
	private transient Iterator currentIterator;
	private transient Value latestValue;

    public IteratorValueImpl(
        boolean isUndefined,
        String errorMessage,
        CollectionValue theCollection)
    {
		this(isUndefined, errorMessage, theCollection, IteratorType.TheInstance, StringCommandGroup.TheInstance);
    }

    protected IteratorValueImpl(
        boolean isUndefined,
        String errorMessage,
        CollectionValue theCollection,
        Type type, 
        CommandGroup commands)
    {
        super(isUndefined, errorMessage, type, commands);
        if (theCollection instanceof CollectionValueImpl) {
        	this.values = ((CollectionValueImpl)theCollection).getTheCollectionAsCollection();
        } else {
        	this.values = Arrays.asList(theCollection.getTheCollection());
        }
        this.reset();
    }
    
    private void buildIterator () {
    	if (this.currentIterator == null) {
    		this.index = 0;
    		this.currentIterator = this.values.iterator();
    		this.latestValue = NullValueImpl.TheInstance;
    	}
    }
    
    public void reset () {
    	this.currentIterator = null;
    	this.buildIterator();
    }
    
    public boolean hasNext () {
    	this.buildIterator();
    	return this.latestValue == NoMoreElement ? false : this.currentIterator.hasNext();
    }
    
    public void nextPosition () {
    	this.buildIterator();
    	if (this.latestValue != NoMoreElement) {
	    	try {
	    		this.latestValue = (Value)this.currentIterator.next();
	    	} catch (NoSuchElementException x) {
	    		this.latestValue = NoMoreElement;
	    	}
    	}
    }
    
    public Value getCurrent () {
    	this.buildIterator();
    	return this.latestValue;
    }
    
    public Value next () {
    	this.nextPosition();
    	return this.getCurrent();
    }

    public boolean checkValueEquality(
        org.irisa.triskell.MT.DataTypes.Java.Value rhs)
    {
		return false;
    }

    public void accept(
        org.irisa.triskell.MT.DataTypes.Java.ValueVisitor visitor)
    {
		visitor.visitValue(this);
    }
    
	public String getValueAsString() {
		return "Iterator";
	}

}
