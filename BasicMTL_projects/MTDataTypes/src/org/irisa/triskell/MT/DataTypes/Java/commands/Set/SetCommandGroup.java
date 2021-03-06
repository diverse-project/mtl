/* $Id: SetCommandGroup.java,v 1.7 2004-05-19 22:21:26 ffondeme Exp $
 * Created on May 22, 2003
 *
 */
package org.irisa.triskell.MT.DataTypes.Java.commands.Set;

import java.util.Arrays;
import java.util.Hashtable;

import org.irisa.triskell.MT.DataTypes.Java.CollectionKind;
import org.irisa.triskell.MT.DataTypes.Java.CollectionValue;
// import org.irisa.triskell.MT.DataTypes.Java.IntegerValue;
// import org.irisa.triskell.MT.DataTypes.Java.RealValue;
// import org.irisa.triskell.MT.DataTypes.Java.Type;
import org.irisa.triskell.MT.DataTypes.Java.Value;
import org.irisa.triskell.MT.DataTypes.Java.commands.CommandGroup;
import org.irisa.triskell.MT.DataTypes.Java.commands.CommandGroupImpl;
// import org.irisa.triskell.MT.DataTypes.Java.commands.MultipleCommandException;
// import org.irisa.triskell.MT.DataTypes.Java.commands.UnknownCommandException;
import org.irisa.triskell.MT.DataTypes.Java.commands.Collection.CollectionCommandGroup;
// import org.irisa.triskell.MT.DataTypes.Java.commands.Collection.CollectionType;
// import org.irisa.triskell.MT.DataTypes.Java.commands.OclAny.OclAnyCommandGroup;

/**
 * @author ffondeme
 *
 * Defines the available commands for Set objects 
 */
public class SetCommandGroup extends CommandGroupImpl {
	// keys are collection types (CollectionType) and values are collection command groups (CollectionCommandGroup) - of this exact type !	private static Hashtable defined
	private static final Hashtable setCommandGroups = new Hashtable();	
	public static SetCommandGroup getSetCommandGroup (SetType setType) {
		SetCommandGroup ret = (SetCommandGroup)setCommandGroups.get(setType);
		if (ret == null) {
			ret = new SetCommandGroup(setType, CollectionCommandGroup.getCollectionCommandGroup(setType));
			setCommandGroups.put(setType, ret);
			ret.addCommand(Set_union.TheInstance);
			ret.addCommand(Set_intersection.TheInstance);
			ret.addCommand(Set_sub.TheInstance);
			ret.addCommand(Set_including.TheInstance);
			ret.addCommand(Set_excluding.TheInstance);
			ret.addCommand(Set_symmetricDifference.TheInstance);
		}
		return ret;
	}
	
	protected SetCommandGroup(SetType collectionType, CollectionCommandGroup parent) {
		super(collectionType, Arrays.asList(new CommandGroup [] {parent}));
	}

	public boolean checkInvoker(Value invoker) {
		return super.checkInvoker(invoker) && ((CollectionValue)invoker).getKind().equals(CollectionKind.set_kind);
	}

}
