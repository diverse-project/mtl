/* $Id: DefaultJava2MtlMappingObject.java,v 1.1 2004-10-25 13:23:11 jpthibau Exp $
 * Created on 4 déc. 2003
 * Authors : edrezen
 * 
 * Copyright 2004 - INRIA - LGPL license
 */
package org.irisa.triskell.MT.BasicMTL.java2mtl;

import org.irisa.triskell.MT.BasicMTL.DataTypes.*;
import org.irisa.triskell.MT.BasicMTL.DataTypes.impl.*;
import org.irisa.triskell.MT.BasicMTL.TopTypes.BMTLObjectInterface;
import org.irisa.triskell.MT.DataTypes.Java.*;
import org.irisa.triskell.MT.DataTypes.Java.commands.*;
import org.irisa.triskell.MT.DataTypes.Java.commands.OclAny.*;

/**
 * @author edrezen
 *
 * Implementation of FileOutput BMTL object using Java io library
 */
abstract public class DefaultJava2MtlMappingObject implements Value, BMTLObjectInterface
{
 	/////////////////////////////////////////////////////////////////////////////////////
	// Type implementation
	////////////////////////////////////////////////////////////////////////////////////


	/////////////////////////////////////////////////////////////////////////////////////
	// Value implementation
	////////////////////////////////////////////////////////////////////////////////////

	/** */
    public boolean isUndefined() {
        return false;
    }

	/** */
    public String getErrorMessage() {
        return "";
    }

	/** */
    public boolean equals(Value rhs) {
        return this == rhs;
    }

	/** */
    public Value invoke (
    	String[] scopeQualifiedName, 
    	String name, 
    	Value[] arguments, 
    	String[] discriminants
    ) throws UnknownCommandException, MultipleCommandException 
    {
		return BMTLVoid.TheInstance;
	}

	/** */
    public void accept(ValueVisitor visitor) 
    {
		visitor.visitValue(this);
    }

	/** */
    abstract public Type getType(); 


	/////////////////////////////////////////////////////////////////////////////////////
	// BMTLObjectInterface implementation
	////////////////////////////////////////////////////////////////////////////////////
    
	/** */
	public void delete () 
	{
    }

	/** */
    public BMTLVoidInterface BMTL_delete () 
    {
		return BMTLVoid.TheInstance;    
	}

	/** */
    public BMTLBooleanInterface BMTL_isNull (Value v) 
    {
		return v == null ? BMTLBoolean.TRUE : BMTLBoolean.FALSE;    
	}

	/** */
    public BMTLSetInterface BMTL_newSet () 
    {
		return new BMTLSet(new Value [0]);    	
    }

	/** */
    public BMTLBagInterface BMTL_newBag () 
    {
		return new BMTLBag (new Value [0]);    
	}

	/** */
    public BMTLSequenceInterface BMTL_newSequence () 
    {
		return new BMTLSequence (new Value [0]);    	
    }

	/** */
    public BMTLOrderedSetInterface BMTL_newOrderedSet () 
    {
		return new BMTLOrderedSet(new Value [0]);    
	}

	/** */
    public Value getOclAnyDelegate() 
    {
    	return this;
    }

	/** */
    public BMTLBooleanInterface BMTL__3d (Value rhs) 
    {
		return this.equals(rhs) ? BMTLBoolean.TRUE : BMTLBoolean.FALSE;    
	}

	/** */
    public BMTLBooleanInterface BMTL__3c_3e (Value rhs) 
    {
		return this.BMTL__3d(rhs).BMTL_not();
    }

	/** */
    public BMTLBooleanInterface BMTL_oclIsUndefined () 
    {
		return BMTLBoolean.FALSE;
    }

	/** */
    public BMTLBooleanInterface BMTL_oclIsTypeOf (TypeValue type) 
    {
		return (BMTLBooleanInterface)CommonFunctions.toBMTLDataType (OclAny_oclIsTypeOf.TheInstance.invoke(this, new Value [] {type}));    
	}

	/** */
    public BMTLBooleanInterface BMTL_oclIsKindOf (TypeValue type) 
    {
		return (BMTLBooleanInterface)CommonFunctions.toBMTLDataType (OclAny_oclIsTypeOf.TheInstance.invoke(this, new Value [] {type}));    
    }

	/** */
    public BMTLVoidInterface BMTL_toOut () 
    {
		OclAny_toOut.TheInstance.invoke (this, null);
		return BMTLVoid.TheInstance;
    }

	/** */
    public BMTLVoidInterface BMTL_toErr () 
    {
		OclAny_toErr.TheInstance.invoke(this, null);
		return BMTLVoid.TheInstance;
    }

	/** */
    public Value getDelegate() 
    {
		return this;
    }	
}
