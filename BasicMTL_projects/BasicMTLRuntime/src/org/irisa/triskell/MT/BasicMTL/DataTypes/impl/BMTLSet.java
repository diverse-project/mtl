/*
 * $Id: BMTLSet.java,v 1.7 2004-05-19 22:21:20 ffondeme Exp $
 * Created on 19 août 2003
 *
 */
package org.irisa.triskell.MT.BasicMTL.DataTypes.impl;

// import org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOclAnyInterface;
import org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLSetInterface;
import org.irisa.triskell.MT.DataTypes.Java.Value;
import org.irisa.triskell.MT.DataTypes.Java.commands.Set.Set_excluding;
import org.irisa.triskell.MT.DataTypes.Java.commands.Set.Set_including;
import org.irisa.triskell.MT.DataTypes.Java.commands.Set.Set_intersection;
import org.irisa.triskell.MT.DataTypes.Java.commands.Set.Set_sub;
import org.irisa.triskell.MT.DataTypes.Java.commands.Set.Set_union;
import org.irisa.triskell.MT.DataTypes.Java.commands.Set.Set_symmetricDifference;
import org.irisa.triskell.MT.DataTypes.Java.defaultImpl.SetValueImpl;

/**
 * @author ffondeme
 * 
 * Implementation of the BasicMTL object Standard::Set
 * It uses a delegate objects in MTDataTypes project
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

	public BMTLSetInterface BMTL__2d(BMTLSetInterface s) {
		return (BMTLSetInterface)CommonFunctions.toBMTLDataType(Set_sub.TheInstance.invoke(this.getCollectionDelegate(), new Value [] {CommonFunctions.toMTDataType(s)}));
	}

	public BMTLSetInterface BMTL_including(Value s) {
		return (BMTLSetInterface)CommonFunctions.toBMTLDataType(Set_including.TheInstance.invoke(this.getCollectionDelegate(), new Value [] {CommonFunctions.toMTDataType(s)}));
	}

	public BMTLSetInterface BMTL_excluding(Value s) {
		return (BMTLSetInterface)CommonFunctions.toBMTLDataType(Set_excluding.TheInstance.invoke(this.getCollectionDelegate(), new Value [] {CommonFunctions.toMTDataType(s)}));
	}
	
	public BMTLSetInterface BMTL_symmetricDifference(BMTLSetInterface s) {
		System.out.println("class BMTLSet BMTL_symmetricDifference(BMTLSetInterface s)");
		return (BMTLSetInterface)CommonFunctions.toBMTLDataType(Set_symmetricDifference.TheInstance.invoke(this.getCollectionDelegate(), new Value [] {CommonFunctions.toMTDataType(s)}));
	}
}
