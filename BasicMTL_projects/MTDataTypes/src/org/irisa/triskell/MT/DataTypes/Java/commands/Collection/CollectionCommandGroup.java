/*
 * Created on May 22, 2003
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package org.irisa.triskell.MT.DataTypes.Java.commands.Collection;

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
public class CollectionCommandGroup extends CommandGroupImpl {
	// keys are collection types (CollectionType) and values are collection command groups (CollectionCommandGroup) - of this exact type !	private static Hashtable defined
	private static final Hashtable collectionCommandGroups = new Hashtable();	
	public static CollectionCommandGroup getCollectionCommandGroup (CollectionType collectionType) {
		CollectionCommandGroup ret = (CollectionCommandGroup)collectionCommandGroups.get(collectionType);
		if (ret == null) {
			ret = new CollectionCommandGroup(collectionType);
			collectionCommandGroups.put(collectionType, ret);
		}
		return ret;
	}
	
	protected final CollectionType type;
	
	protected CollectionCommandGroup(CollectionType collectionType) {
		super(collectionType, Arrays.asList(new CommandGroup [0]));
		this.type = collectionType;
	}
	
	public CollectionType getCollectionType () {
		return this.type;
	}
}
