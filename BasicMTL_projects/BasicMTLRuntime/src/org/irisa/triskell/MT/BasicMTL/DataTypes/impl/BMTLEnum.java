/*
 * Created on 20 août 2003
 * $Id: BMTLEnum.java,v 1.2 2004-02-17 08:58:25 dvojtise Exp $
 * Authors : ffondeme
 * 
 * Copyright 2004 - INRIA - LGPL license
 */
package org.irisa.triskell.MT.BasicMTL.DataTypes.impl;

import org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLEnumeredInterface;
import org.irisa.triskell.MT.DataTypes.Java.EnumValue;

/**
 * @author ffondeme
 * Implementation of the Enum data type for BasicMTL. It does the wrapping between BasicMTL and MTDataType impoementation (which use commands)
 */
public class BMTLEnum extends BMTLOclAny implements BMTLEnumeredInterface {

	/**
	 * @param delegate
	 */
	public BMTLEnum(EnumValue delegate) {
		super(delegate);
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLEnumeredInterface#getEnumDelegate()
	 */
	public EnumValue getEnumDelegate() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.irisa.triskell.MT.DataTypes.Java.EnumValue#getTheEnum()
	 */
	public String getTheEnum() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.irisa.triskell.MT.DataTypes.Java.EnumValue#getEnumeration()
	 */
	public String[] getEnumeration() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.irisa.triskell.MT.DataTypes.Java.PrimitiveValue#getValue()
	 */
	public String getValue() {
		// TODO Auto-generated method stub
		return null;
	}

}
