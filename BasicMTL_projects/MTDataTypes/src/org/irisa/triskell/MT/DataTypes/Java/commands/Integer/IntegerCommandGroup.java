/*
 * Created on May 22, 2003
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package org.irisa.triskell.MT.DataTypes.Java.commands.Integer;

import java.util.Arrays;

import org.irisa.triskell.MT.DataTypes.Java.commands.CommandGroup;
import org.irisa.triskell.MT.DataTypes.Java.commands.CommandGroupImpl;
import org.irisa.triskell.MT.DataTypes.Java.commands.Real.RealCommandGroup;

/**
 * @author ffondeme
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class IntegerCommandGroup extends CommandGroupImpl {
	public static final CommandGroup TheInstance = new IntegerCommandGroup();
	
	private IntegerCommandGroup() {
		super(IntegerType.TheInstance, Arrays.asList(new CommandGroup [] {RealCommandGroup.TheInstance}));
	}
}
