/*
 * Created on 19 août 2003
 * $Id: BMTLVoid.java,v 1.2 2004-02-17 08:58:25 dvojtise Exp $
 * Authors : ffondeme
 * 
 * Copyright 2004 - INRIA - LGPL license
 */
package org.irisa.triskell.MT.BasicMTL.DataTypes.impl;

import org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface;
//import org.irisa.triskell.MT.DataTypes.Java.Value;
import org.irisa.triskell.MT.DataTypes.Java.VoidValue;
import org.irisa.triskell.MT.DataTypes.Java.defaultImpl.VoidValueImpl;

/**
 * @author ffondeme
 * Implementation of the Void data type for BasicMTL. It does the wrapping between BasicMTL and MTDataType impoementation (which use commands)
 */
public class BMTLVoid extends BMTLOclAny implements BMTLVoidInterface {
	public static final BMTLVoid TheInstance = new BMTLVoid(VoidValueImpl.getTheInstance());

	public BMTLVoid(VoidValue delegate) {
		super(delegate);
	}

	public VoidValue getVoidDelegate() {
		return (VoidValue)this.getDelegate();
	}

}
