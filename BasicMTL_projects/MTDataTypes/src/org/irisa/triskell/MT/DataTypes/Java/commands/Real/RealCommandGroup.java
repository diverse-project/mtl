/*
 * Created on May 22, 2003
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package org.irisa.triskell.MT.DataTypes.Java.commands.Real;

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
public class RealCommandGroup extends CommandGroupImpl {
	public static final CommandGroup TheInstance;
	
	static {
		TheInstance = new RealCommandGroup();
		TheInstance.addCommand(Real_unaryPlus.TheInstance);
		TheInstance.addCommand(Real_unaryMinus.TheInstance);
		TheInstance.addCommand(Real_add.TheInstance);
		TheInstance.addCommand(Real_sub.TheInstance);
		TheInstance.addCommand(Real_mul.TheInstance);
		TheInstance.addCommand(Real_div.TheInstance);
		TheInstance.addCommand(Real_abs.TheInstance);
		TheInstance.addCommand(Real_floor.TheInstance);
		TheInstance.addCommand(Real_round.TheInstance);
		TheInstance.addCommand(Real_max.TheInstance);
		TheInstance.addCommand(Real_min.TheInstance);
		TheInstance.addCommand(Real_lt.TheInstance);
		TheInstance.addCommand(Real_gt.TheInstance);
		TheInstance.addCommand(Real_le.TheInstance);
		TheInstance.addCommand(Real_ge.TheInstance);
	}
	
	protected RealCommandGroup() {
		super(RealType.TheInstance, Arrays.asList(new CommandGroup [] {OclAnyCommandGroup.TheInstance}));
	}
}
