/*
 * Created on 19 août 2003
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package org.irisa.triskell.MT.BasicMTL.DataTypes.impl;

import org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLDataTypeInterface;
import org.irisa.triskell.MT.DataTypes.Java.Value;

/**
 * @author ffondeme
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class BMTLDataType extends Throwable implements BMTLDataTypeInterface {
	private final Value delegate;

	public BMTLDataType(Value delegate) {
		while (delegate instanceof BMTLDataTypeInterface)
			delegate = ((BMTLDataTypeInterface)delegate).getDelegate();
		this.delegate = delegate;
	}

	public Value getDelegate() {
		return this.delegate;
	}

	public boolean equals(Object obj) {
		return this == obj || ((obj instanceof BMTLDataTypeInterface) && this.getDelegate().equals(((BMTLDataTypeInterface)obj).getDelegate()));
	}

	public int hashCode() {
		return this.getDelegate().hashCode();
	}

	public String toString() {
		return this.getDelegate().toString();
	}

}
