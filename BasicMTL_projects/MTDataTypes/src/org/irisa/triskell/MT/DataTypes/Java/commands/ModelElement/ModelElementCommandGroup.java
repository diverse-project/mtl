/*
 * Created on May 22, 2003
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package org.irisa.triskell.MT.DataTypes.Java.commands.ModelElement;

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
public class ModelElementCommandGroup extends CommandGroupImpl {
	public static final CommandGroup TheInstance;
	
	static {
		TheInstance = new ModelElementCommandGroup();
		TheInstance.addCommand(ModelElement_delete.TheInstance);
		TheInstance.addCommand(ModelElement_oclUid.TheInstance);
	}
	
	private ModelElementCommandGroup() {
		super(ModelElementType.TheInstance, Arrays.asList(new CommandGroup [] {OclAnyCommandGroup.TheInstance}));
	}
}
