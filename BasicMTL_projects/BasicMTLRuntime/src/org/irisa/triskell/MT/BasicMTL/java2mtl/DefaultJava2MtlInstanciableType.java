/*
 * Created on 21 juin 2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package org.irisa.triskell.MT.BasicMTL.java2mtl;

import org.irisa.triskell.MT.BasicMTL.DataTypes.impl.BMTLSet;
import org.irisa.triskell.MT.DataTypes.Java.*;
import org.irisa.triskell.MT.DataTypes.Java.commands.InstanciableType;

/**
 * @author odefour
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class DefaultJava2MtlInstanciableType implements InstanciableType
{
	/** */
	protected String name;
	protected String [] qualifiedName;
	protected Class theClass;

	public DefaultJava2MtlInstanciableType (String name, String[] qualifiedName, Class theClass)
	{
		this.name = name;
		this.qualifiedName = qualifiedName;
		this.theClass = theClass;
	}
	
	public Value instanciate () 
	{
		Value result = null;

		try {
			result = (Value) theClass.newInstance();
		} 
		catch (InstantiationException e) {
			e.printStackTrace();
		} 
		catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return result;
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
		return v instanceof DefaultJava2MtlMappingObject;        
	}

	public boolean isTypeOf (Value v) 
	{
		return v instanceof DefaultJava2MtlMappingObject;        
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
		return org.irisa.triskell.MT.utils.Java.AWK.merge (qualifiedName, "::");        
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


}
