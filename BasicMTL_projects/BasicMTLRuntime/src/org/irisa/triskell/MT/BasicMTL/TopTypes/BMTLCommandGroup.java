/*
 * Created on 18 juin 2003
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package org.irisa.triskell.MT.BasicMTL.TopTypes;

import org.irisa.triskell.MT.DataTypes.Java.Value;
import org.irisa.triskell.MT.DataTypes.Java.commands.Command;
import org.irisa.triskell.MT.DataTypes.Java.commands.CommandGroupImpl;
import org.irisa.triskell.MT.DataTypes.Java.commands.MultipleCommandException;

public class BMTLCommandGroup extends CommandGroupImpl {
	public BMTLCommandGroup() {
		super(null, null);
	}

	public boolean checkInvoker(Value invoker) {
		return invoker instanceof BMTLObject;
	}

	public Command getInvokableCommand(
		String[] qualifiedName,
		String name,
		Value[] arguments,
		String[] discriminants)
		throws MultipleCommandException {
		return super.getDirectInvokableCommand(
			name,
			arguments,
			discriminants);
	}

}
