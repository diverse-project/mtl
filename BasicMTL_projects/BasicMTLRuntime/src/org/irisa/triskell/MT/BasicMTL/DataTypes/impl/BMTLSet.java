/*
 * Created on 19 août 2003
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package org.irisa.triskell.MT.BasicMTL.DataTypes.impl;

import org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOclAnyInterface;
import org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLSetInterface;
import org.irisa.triskell.MT.DataTypes.Java.Value;
import org.irisa.triskell.MT.DataTypes.Java.commands.Set.Set_including;
import org.irisa.triskell.MT.DataTypes.Java.commands.Set.Set_intersection;
import org.irisa.triskell.MT.DataTypes.Java.commands.Set.Set_union;
import org.irisa.triskell.MT.DataTypes.Java.defaultImpl.SetValueImpl;

/**
 * @author ffondeme
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class BMTLSet extends BMTLCollection implements BMTLSetInterface {
	
	public BMTLSet() {
		this(new Value [0]);
	}
	
	public BMTLSet(Value [] collection) {
		this(new SetValueImpl(false, null, collection, false));
	}

	public BMTLSet(Value delegate) {
		super(delegate);
	}

	public BMTLSetInterface BMTL_union(BMTLSetInterface s) {
		return (BMTLSetInterface)CommonFunctions.toBMTLDataType(Set_union.TheInstance.invoke(this.getCollectionDelegate(), new Value [] {CommonFunctions.toMTDataType(s)}));
	}

	public BMTLSetInterface BMTL_intersection(BMTLSetInterface s) {
		return (BMTLSetInterface)CommonFunctions.toBMTLDataType(Set_intersection.TheInstance.invoke(this.getCollectionDelegate(), new Value [] {CommonFunctions.toMTDataType(s)}));
	}

	public BMTLSetInterface BMTL_including(BMTLOclAnyInterface s) {
		return (BMTLSetInterface)CommonFunctions.toBMTLDataType(Set_including.TheInstance.invoke(this.getCollectionDelegate(), new Value [] {CommonFunctions.toMTDataType(s)}));
	}

}
