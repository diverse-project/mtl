/*
 * Created on May 22, 2003
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package org.irisa.triskell.MT.DataTypes.Java.commands.Boolean;

import java.util.Arrays;

import org.irisa.triskell.MT.DataTypes.Java.BooleanValue;
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
public class BooleanCommandGroup extends CommandGroupImpl {
	public static final CommandGroup TheInstance = new BooleanCommandGroup();
	
	private BooleanCommandGroup() {
		super(BooleanType.TheInstance, Arrays.asList(new CommandGroup [] {OclAnyCommandGroup.TheInstance}));
		this.addCommand(Boolean_or.TheInstance);
		this.addCommand(Boolean_not.TheInstance);
	}

}