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
import org.irisa.triskell.MT.DataTypes.Java.Value;
import org.irisa.triskell.MT.DataTypes.Java.commands.CommandGroup;
import org.irisa.triskell.MT.DataTypes.Java.commands.CommandGroupImpl;
import org.irisa.triskell.MT.DataTypes.Java.commands.Type;
import org.irisa.triskell.MT.DataTypes.Java.commands.OclAny.OclAnyCommandGroup;

/**
 * @author ffondeme
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class RealCommandGroup extends CommandGroupImpl {
	public static final CommandGroup TheInstance = new RealCommandGroup();
	
	private RealCommandGroup() {
		/*@TODO heritage de RealCommandGroup*/
		super(RealType.TheInstance, Arrays.asList(new CommandGroup [] {OclAnyCommandGroup.TheInstance}));
		this.addCommand(Real_mul.TheInstance);
		this.addCommand(Real_div.TheInstance);
		this.addCommand(Real_floor.TheInstance);
		this.addCommand(Real_lt.TheInstance);
	}
}
