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
import org.irisa.triskell.MT.DataTypes.Java.Type;
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
	public static final CommandGroup TheInstance;
	
	static {
		TheInstance = new OclAnyCommandGroup();
		TheInstance.addCommand(OclAny_equals.TheInstance);
		TheInstance.addCommand(OclAny_notEquals.TheInstance);
		TheInstance.addCommand(OclAny_isUndefined.TheInstance);
		TheInstance.addCommand(OclAny_errorMessage.TheInstance);
		TheInstance.addCommand(OclAny_oclIsTypeOf.TheInstance);
		TheInstance.addCommand(OclAny_oclIsKindOf.TheInstance);
	}
	
	private OclAnyCommandGroup() {
		super(OclAnyType.TheInstance, null);
	}
	

}