/* $Id: FileOutput.java,v 1.2 2004-06-15 15:13:42 sdzale Exp $
 * Created on 4 déc. 2003
 * Authors : edrezen
 * 
 * Copyright 2004 - INRIA - LGPL license
 */
package io;

//import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLBagInterface;
import org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLBooleanInterface;
import org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface;
import org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLSequenceInterface;
import org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLSetInterface;
import org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface;
import org.irisa.triskell.MT.BasicMTL.DataTypes.impl.BMTLBag;
import org.irisa.triskell.MT.BasicMTL.DataTypes.impl.BMTLBoolean;
import org.irisa.triskell.MT.BasicMTL.DataTypes.impl.BMTLOrderedSet;
import org.irisa.triskell.MT.BasicMTL.DataTypes.impl.BMTLSequence;
import org.irisa.triskell.MT.BasicMTL.DataTypes.impl.BMTLSet;
import org.irisa.triskell.MT.BasicMTL.DataTypes.impl.BMTLVoid;
import org.irisa.triskell.MT.BasicMTL.DataTypes.impl.CommonFunctions;
import org.irisa.triskell.MT.BasicMTL.TopTypes.BMTLObjectInterface;
import org.irisa.triskell.MT.DataTypes.Java.CollectionValue;
//import org.irisa.triskell.MT.DataTypes.Java.IntegerValue;
import org.irisa.triskell.MT.DataTypes.Java.StringValue;
import org.irisa.triskell.MT.DataTypes.Java.Type;
import org.irisa.triskell.MT.DataTypes.Java.TypeValue;
import org.irisa.triskell.MT.DataTypes.Java.Value;
import org.irisa.triskell.MT.DataTypes.Java.ValueVisitor;
import org.irisa.triskell.MT.DataTypes.Java.commands.InstanciableType;
import org.irisa.triskell.MT.DataTypes.Java.commands.MultipleCommandException;
import org.irisa.triskell.MT.DataTypes.Java.commands.UnknownCommandException;
import org.irisa.triskell.MT.DataTypes.Java.commands.OclAny.OclAny_oclIsTypeOf;
import org.irisa.triskell.MT.DataTypes.Java.commands.OclAny.OclAny_toErr;
import org.irisa.triskell.MT.DataTypes.Java.commands.OclAny.OclAny_toOut;
import org.irisa.triskell.MT.utils.Java.AWK;

/**
 * @author edrezen
 *
 * Implementation of FileOutput BMTL object using Java io library
 */
public class FileOutput implements Value,BMTLObjectInterface
{
    protected static final String name = "FileOutput";
	protected static final String [] qualifiedName = new String [] {"io", name};

	protected java.io.DataOutputStream dataOutputStream = null;


	/////////////////////////////////////////////////////////////////////////////////////
	// Methods usable in MTL sources 
	////////////////////////////////////////////////////////////////////////////////////
	
	/** Note : in MTL, constructors with arguments are not possible; so one can instanciate
	 * a FileOutput object in MTL and setting the filename just after. The corresponding
	 * java object will be then constructed. 
	 * 
	 * */			
	public void BMTL_setName (StringValue filename)
	{
		try {
            this.dataOutputStream = new java.io.DataOutputStream (
				new java.io.FileOutputStream (filename.getValue())
            ); 
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
	}


	/** */
	public void BMTL_close ()
	{
		try {
			this.dataOutputStream.flush();
            this.dataOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
	}


	/** */
	public void BMTL_write (StringValue s)
	{
		try {
            this.dataOutputStream.writeBytes (s.getValue());
        } catch (IOException e) {
            e.printStackTrace();
        }
	}


	/** */
	public void BMTL_writeln (StringValue s)
	{
		try {
			this.dataOutputStream.writeBytes (s.getValue() + "\n");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	/////////////////////////////////////////////////////////////////////////////////////
	// Type implementation
	////////////////////////////////////////////////////////////////////////////////////

	public static final Type TheType = new InstanciableType () {

        public Value instanciate () 
        {
        	return new FileOutput ();
        }

        public String getName () 
        {
        	return name;
        }

        public String[] getQualifiedName () 
        {
            return qualifiedName;
        }

        public boolean isKindOf (Value v) 
        {
			return v instanceof FileOutput;        
		}

        public boolean isTypeOf (Value v) 
        {
			return v instanceof FileOutput;        
        }

        public boolean conformsTo (Type parentType) 
        {
			return parentType == this;
		}

        public CollectionValue allInstances () throws Exception 
        {
			return new BMTLSet();
        }

        public String getQualifiedNameAsString() 
        {
			return AWK.merge (qualifiedName, "::");        
		}
		public Value instanciateFromJavaObject (Object javaObject)
		{
			// currently, no java object allows to create a FileOutput 
		 	// DVK : currently we have no use for that 
			return null;
		}	
		public boolean isInstanciableFromJavaObject (Object javaObject)
		{
			return false;
		}

	};



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
    public Type getType() 
    {
		return TheType;
    }


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
