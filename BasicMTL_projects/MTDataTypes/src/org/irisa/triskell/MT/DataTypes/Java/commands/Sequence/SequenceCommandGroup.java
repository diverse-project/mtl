/*
 * Created on May 22, 2003
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package org.irisa.triskell.MT.DataTypes.Java.commands.Sequence;

import java.util.Arrays;
import java.util.Hashtable;

import org.irisa.triskell.MT.DataTypes.Java.CollectionKind;
import org.irisa.triskell.MT.DataTypes.Java.CollectionValue;
import org.irisa.triskell.MT.DataTypes.Java.IntegerValue;
import org.irisa.triskell.MT.DataTypes.Java.RealValue;
import org.irisa.triskell.MT.DataTypes.Java.Type;
import org.irisa.triskell.MT.DataTypes.Java.Value;
import org.irisa.triskell.MT.DataTypes.Java.commands.CommandGroup;
import org.irisa.triskell.MT.DataTypes.Java.commands.CommandGroupImpl;
import org.irisa.triskell.MT.DataTypes.Java.commands.Collection.CollectionCommandGroup;
import org.irisa.triskell.MT.DataTypes.Java.commands.OclAny.OclAnyCommandGroup;

/**
 * @author ffondeme
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class SequenceCommandGroup extends CommandGroupImpl {
	// keys are collection types (CollectionType) and values are collection command groups (SequenceCommandGroup) - of this exact type !	private static Hashtable defined
	private static final Hashtable sequenceCommandGroups = new Hashtable();	
	public static SequenceCommandGroup getSequenceCommandGroup (SequenceType sequenceType) {
		SequenceCommandGroup ret = (SequenceCommandGroup)sequenceCommandGroups.get(sequenceType);
		if (ret == null) {
			ret = new SequenceCommandGroup(sequenceType, CollectionCommandGroup.getCollectionCommandGroup(sequenceType));
			sequenceCommandGroups.put(sequenceType, ret);
			ret.addCommand(Sequence_append.TheInstance);
			ret.addCommand(Sequence_including.TheInstance);
		}
		return ret;
	}
	
	protected SequenceCommandGroup(SequenceType collectionType, CollectionCommandGroup parent) {
		super(collectionType, Arrays.asList(new CommandGroup [] {parent}));
	}

	public boolean checkInvoker(Value invoker) {
		return super.checkInvoker(invoker) && ((CollectionValue)invoker).getKind().equals(CollectionKind.sequence_kind);
	}
	
}
