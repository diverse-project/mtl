/*
 * Created on 19 août 2003
 * $Id: BMTLBag.java,v 1.5 2004-04-14 17:11:53 ffondeme Exp $
 * Authors : ffondeme
 * 
 * Copyright 2004 - INRIA - LGPL license
 */
package org.irisa.triskell.MT.BasicMTL.DataTypes.impl;

import org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLBagInterface;
import org.irisa.triskell.MT.DataTypes.Java.Value;
import org.irisa.triskell.MT.DataTypes.Java.commands.Bag.Bag_excluding;
import org.irisa.triskell.MT.DataTypes.Java.commands.Bag.Bag_including;
import org.irisa.triskell.MT.DataTypes.Java.defaultImpl.BagValueImpl;

/**
 * @author ffondeme
 * Implementation of the Bag data type for BasicMTL. It does the wrapping between BasicMTL and MTDataType impoementation (which use commands)
 */
public class BMTLBag extends BMTLCollection implements BMTLBagInterface {
	
	public BMTLBag() {
		this(new Value [0]);
	}
	
	public BMTLBag(Value [] values) {
		this(new BagValueImpl(false, null, values));
	}

	/**
	 * @param delegate
	 */
	public BMTLBag(Value delegate) {
		super(delegate);
		// TODO Auto-generated constructor stub
	}

	public BMTLBagInterface BMTL_including(Value s) {
		return (BMTLBagInterface)CommonFunctions.toBMTLDataType(Bag_including.TheInstance.invoke(this.getCollectionDelegate(), new Value [] {CommonFunctions.toMTDataType(s)}));
	}

	public BMTLBagInterface BMTL_excluding(Value s) {
		return (BMTLBagInterface)CommonFunctions.toBMTLDataType(Bag_excluding.TheInstance.invoke(this.getCollectionDelegate(), new Value [] {CommonFunctions.toMTDataType(s)}));
	}

}
