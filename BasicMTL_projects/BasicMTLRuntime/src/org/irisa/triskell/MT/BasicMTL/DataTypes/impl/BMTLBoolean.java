/*
 * Created on 19 août 2003
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package org.irisa.triskell.MT.BasicMTL.DataTypes.impl;

import org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLBooleanInterface;
import org.irisa.triskell.MT.DataTypes.Java.BooleanValue;
import org.irisa.triskell.MT.DataTypes.Java.defaultImpl.BooleanValueImpl;

/**
 * @author ffondeme
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class BMTLBoolean
	extends BMTLOclAny
	implements BMTLBooleanInterface, BooleanValue {
	
	public static BMTLBoolean TRUE = new BMTLBoolean(BooleanValueImpl.TRUE);
	public static BMTLBoolean FALSE = new BMTLBoolean(BooleanValueImpl.FALSE);

	public BMTLBoolean(BooleanValue delegate) {
		super(delegate);
	}
	public BooleanValue getBooleanDelegate() {
		return (BooleanValue)this.getDelegate();
	}
	
	public boolean getTheBoolean() {
		return this.getBooleanDelegate().getTheBoolean();
	}

	public String getValue() {
		return this.getBooleanDelegate().getValue();
	}

	/* (non-Javadoc)
	 * @see org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLBooleanInterface#BMTL_and(org.irisa.triskell.MT.DataTypes.Java.BooleanValue)
	 */
	public BMTLBooleanInterface BMTL_and(BooleanValue rhs) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLBooleanInterface#BMTL_implies(org.irisa.triskell.MT.DataTypes.Java.BooleanValue)
	 */
	public BMTLBooleanInterface BMTL_implies(BooleanValue rhs) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLBooleanInterface#BMTL_not()
	 */
	public BMTLBooleanInterface BMTL_not() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLBooleanInterface#BMTL_or(org.irisa.triskell.MT.DataTypes.Java.BooleanValue)
	 */
	public BMTLBooleanInterface BMTL_or(BooleanValue rhs) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLBooleanInterface#BMTL_xor(org.irisa.triskell.MT.DataTypes.Java.BooleanValue)
	 */
	public BMTLBooleanInterface BMTL_xor(BooleanValue rhs) {
		// TODO Auto-generated method stub
		return null;
	}

}
