/*
 * Created on May 22, 2003
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package org.irisa.triskell.MT.DataTypes.Java.commands.Set;

import java.util.Arrays;
import java.util.Hashtable;

import org.irisa.triskell.MT.DataTypes.Java.IntegerValue;
import org.irisa.triskell.MT.DataTypes.Java.RealValue;
import org.irisa.triskell.MT.DataTypes.Java.Type;
import org.irisa.triskell.MT.DataTypes.Java.Value;
import org.irisa.triskell.MT.DataTypes.Java.commands.CommandGroup;
import org.irisa.triskell.MT.DataTypes.Java.commands.CommandGroupImpl;
import org.irisa.triskell.MT.DataTypes.Java.commands.Collection.CollectionCommandGroup;
import org.irisa.triskell.MT.DataTypes.Java.commands.Collection.CollectionType;
import org.irisa.triskell.MT.DataTypes.Java.commands.OclAny.OclAnyCommandGroup;

/**
 * @author ffondeme
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class SetCommandGroup extends CommandGroupImpl {
	// keys are collection types (CollectionType) and values are collection command groups (CollectionCommandGroup) - of this exact type !	private static Hashtable defined
	private static final Hashtable setCommandGroups = new Hashtable();	
	public static SetCommandGroup getSetCommandGroup (SetType setType) {
		SetCommandGroup ret = (SetCommandGroup)setCommandGroups.get(setType);
		if (ret == null) {
			ret = new SetCommandGroup(setType, CollectionCommandGroup.getCollectionCommandGroup(setType));
			setCommandGroups.put(setType, ret);
		}
		return ret;
	}
	
	protected SetCommandGroup(SetType collectionType, CollectionCommandGroup parent) {
		super(collectionType, Arrays.asList(new CommandGroup [] {parent}));
	}
	
}
