/*
 * Created on May 22, 2003
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package org.irisa.triskell.MT.DataTypes.Java.commands.OrderedSet;

import java.util.Arrays;
import java.util.Hashtable;

import org.irisa.triskell.MT.DataTypes.Java.IntegerValue;
import org.irisa.triskell.MT.DataTypes.Java.RealValue;
import org.irisa.triskell.MT.DataTypes.Java.Type;
import org.irisa.triskell.MT.DataTypes.Java.Value;
import org.irisa.triskell.MT.DataTypes.Java.commands.CommandGroup;
import org.irisa.triskell.MT.DataTypes.Java.commands.CommandGroupImpl;
import org.irisa.triskell.MT.DataTypes.Java.commands.OclAny.OclAnyCommandGroup;

/**
 * @author ffondeme
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class OrderedSetCommandGroup extends CommandGroupImpl {
	// keys are collection types (CollectionType) and values are collection command groups (CollectionCommandGroup) - of this exact type !	private static Hashtable defined
	private static final Hashtable orderedSetCommandGroups = new Hashtable();	
	public static OrderedSetCommandGroup getOrderedSetCommandGroup (OrderedSetType setType) {
		OrderedSetCommandGroup ret = (OrderedSetCommandGroup)orderedSetCommandGroups.get(setType);
		if (ret == null) {
			ret = new OrderedSetCommandGroup(setType);
			orderedSetCommandGroups.put(setType, ret);
		}
		return ret;
	}
	
	protected OrderedSetCommandGroup(OrderedSetType collectionType) {
		super(collectionType, Arrays.asList(new CommandGroup [0]));
	}
}
