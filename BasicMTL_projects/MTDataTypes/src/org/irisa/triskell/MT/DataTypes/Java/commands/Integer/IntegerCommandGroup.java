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
	public static final CommandGroup TheInstance;
	
	static {
		TheInstance = new IntegerCommandGroup();
		TheInstance.addCommand(Integer_unaryMinus.TheInstance);
		TheInstance.addCommand(Integer_add.TheInstance);
		TheInstance.addCommand(Integer_sub.TheInstance);
		TheInstance.addCommand(Integer_mul.TheInstance);
		TheInstance.addCommand(Integer_idiv.TheInstance);
		TheInstance.addCommand(Integer_mod.TheInstance);
	}
	
	private IntegerCommandGroup() {
		super(IntegerType.TheInstance, Arrays.asList(new CommandGroup [] {RealCommandGroup.TheInstance}));
	}
}
