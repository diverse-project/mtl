/*
 * Created on May 22, 2003
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package org.irisa.triskell.MT.DataTypes.Java.commands.OclAny;

import org.irisa.triskell.MT.DataTypes.Java.commands.CommandGroupImpl;
import org.irisa.triskell.MT.DataTypes.Java.commands.Command;
import org.irisa.triskell.MT.DataTypes.Java.commands.CommandGroup;
import org.irisa.triskell.MT.DataTypes.Java.commands.Type;
import org.irisa.triskell.MT.DataTypes.Java.Value;

import java.util.List;
import java.util.Arrays;

/**
 * @author ffondeme
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class OclAnyCommandGroup extends CommandGroupImpl {
	public static final CommandGroup TheInstance = new OclAnyCommandGroup();
	
	private OclAnyCommandGroup() {
		super(OclAnyType.TheInstance, null);
		this.addCommand(OclAny_equals.TheInstance);
		this.addCommand(OclAny_notEquals.TheInstance);
	}
	

}