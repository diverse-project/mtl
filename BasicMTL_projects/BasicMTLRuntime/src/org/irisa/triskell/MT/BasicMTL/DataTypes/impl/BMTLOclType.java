/*
 * Created on 19 ao�t 2003
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package org.irisa.triskell.MT.BasicMTL.DataTypes.impl;

import org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOclTypeInterface;
import org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface;
import org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLSetInterface;
import org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface;
import org.irisa.triskell.MT.BasicMTL.TopTypes.BMTLLibInterface;
import org.irisa.triskell.MT.BasicMTL.TopTypes.BMTLLibrary;
import org.irisa.triskell.MT.DataTypes.Java.Type;
import org.irisa.triskell.MT.DataTypes.Java.TypeValue;
import org.irisa.triskell.MT.DataTypes.Java.Value;
import org.irisa.triskell.MT.DataTypes.Java.commands.OclType.OclType_allInstances;
import org.irisa.triskell.MT.DataTypes.Java.commands.OclType.OclType_name;
import org.irisa.triskell.MT.DataTypes.Java.commands.OclType.OclType_qualifiedName;
import org.irisa.triskell.MT.DataTypes.Java.defaultImpl.TypeValueImpl;
import org.irisa.triskell.MT.utils.Java.AWK;

/**
 * @author ffondeme
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class BMTLOclType extends BMTLOclAny implements BMTLOclTypeInterface {
	public static TypeValue getType (BMTLLibInterface lib, String [] typeName) {
		Type ret = lib.getMetaClass(typeName);
		if (ret == null)
			return new TypeValueImpl(true, "Unknown type " + AWK.merge(typeName, "::"), null);
		else
			return new TypeValueImpl(false, null, ret);
	}
	
	public BMTLOclType(BMTLLibInterface lib, String [] typeName) {
		this(getType(lib, typeName));
	}

	public BMTLOclType(TypeValue delegate) {
		super(delegate);
	}

	public TypeValue getOclTypeDelegate() {
		return (TypeValue)this.getDelegate();
	}

	public BMTLSetInterface BMTL_allInstances() {
		return (BMTLSetInterface)CommonFunctions.toBMTLDataType(OclType_allInstances.TheInstance.invoke(this.getOclTypeDelegate(), null));
	}

	public BMTLOrderedSetInterface BMTL_oclQualifiedName() {
		return (BMTLOrderedSetInterface)CommonFunctions.toBMTLDataType(OclType_qualifiedName.TheInstance.invoke(this.getOclTypeDelegate(), null));
	}

	public BMTLStringInterface BMTL_oclName() {
		return (BMTLString)CommonFunctions.toBMTLDataType(OclType_name.TheInstance.invoke(this.getOclTypeDelegate(), null));
	}


	public Type getTheType() {
		return this.getOclTypeDelegate().getTheType();
	}

	public String getValue() {
		return this.getOclTypeDelegate().getValue();
	}

}
