/*
 * $Id: OrderedSetCommandGroup.java,v 1.8 2004-04-14 17:12:02 ffondeme Exp $
 * @author : ffondeme
 * 
 * Copyright 2004 - INRIA - LGPL license
 */
package org.irisa.triskell.MT.DataTypes.Java.commands.OrderedSet;

import java.util.Arrays;
import java.util.Hashtable;

import org.irisa.triskell.MT.DataTypes.Java.CollectionKind;
import org.irisa.triskell.MT.DataTypes.Java.CollectionValue;
import org.irisa.triskell.MT.DataTypes.Java.Value;
import org.irisa.triskell.MT.DataTypes.Java.commands.CommandGroup;
import org.irisa.triskell.MT.DataTypes.Java.commands.CommandGroupImpl;
import org.irisa.triskell.MT.DataTypes.Java.commands.Collection.CollectionCommandGroup;

/**
 * @author ffondeme
 *Declaration of callable commands form OrderedSet objects
 */
public class OrderedSetCommandGroup extends CommandGroupImpl {
	// keys are collection types (CollectionType) and values are collection command groups (CollectionCommandGroup) - of this exact type !	private static Hashtable defined
	private static final Hashtable orderedSetCommandGroups = new Hashtable();	
	public static OrderedSetCommandGroup getOrderedSetCommandGroup (OrderedSetType orderedSetType) {
		OrderedSetCommandGroup ret = (OrderedSetCommandGroup)orderedSetCommandGroups.get(orderedSetType);
		if (ret == null) {
			ret = new OrderedSetCommandGroup(orderedSetType, CollectionCommandGroup.getCollectionCommandGroup(orderedSetType));
			orderedSetCommandGroups.put(orderedSetType, ret);
			ret.addCommand(OrderedSet_append.TheInstance);
			ret.addCommand(OrderedSet_prepend.TheInstance);
			ret.addCommand(OrderedSet_insertAt.TheInstance);
			ret.addCommand(OrderedSet_including.TheInstance);
			ret.addCommand(OrderedSet_excluding.TheInstance);
		}
		return ret;
	}
	
	protected OrderedSetCommandGroup(OrderedSetType collectionType, CollectionCommandGroup parent) {
		super(collectionType, Arrays.asList(new CommandGroup [] {parent}));
	}
	
	public boolean checkInvoker(Value invoker) {
		return super.checkInvoker(invoker) && ((CollectionValue)invoker).getKind().equals(CollectionKind.ordered_set_kind);
	}
	
}
