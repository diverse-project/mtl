/*
 * Created on 19 août 2003
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package org.irisa.triskell.MT.BasicMTL.DataTypes.impl;

import org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface;
import org.irisa.triskell.MT.DataTypes.Java.Value;
import org.irisa.triskell.MT.DataTypes.Java.VoidValue;
import org.irisa.triskell.MT.DataTypes.Java.defaultImpl.VoidValueImpl;

/**
 * @author ffondeme
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
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
