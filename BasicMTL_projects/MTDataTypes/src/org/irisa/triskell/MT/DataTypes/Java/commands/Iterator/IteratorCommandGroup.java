/*
 * Created on May 22, 2003
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package org.irisa.triskell.MT.DataTypes.Java.commands.Iterator;

import java.util.Arrays;

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
public class IteratorCommandGroup extends CommandGroupImpl {
	public static final CommandGroup TheInstance;
	
	static {
		TheInstance = new IteratorCommandGroup();
		TheInstance.addCommand(Iterator_start.TheInstance);
		TheInstance.addCommand(Iterator_isOff.TheInstance);
		TheInstance.addCommand(Iterator_isOn.TheInstance);
		TheInstance.addCommand(Iterator_hasNext.TheInstance);
		TheInstance.addCommand(Iterator_item.TheInstance);
		TheInstance.addCommand(Iterator_next.TheInstance);
		TheInstance.addCommand(Iterator_nextItem.TheInstance);
	}
	
	private IteratorCommandGroup() {
		super(IteratorType.TheInstance, Arrays.asList(new CommandGroup [] {OclAnyCommandGroup.TheInstance}));
	}
}
