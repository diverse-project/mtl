/*
 * Created on 18 juin 2003
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package org.irisa.triskell.MT.BasicMTL.TopTypes;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

import org.irisa.triskell.MT.DataTypes.Java.Value;
import org.irisa.triskell.MT.DataTypes.Java.commands.Command;
import org.irisa.triskell.MT.DataTypes.Java.commands.CommandGroup;
import org.irisa.triskell.MT.DataTypes.Java.commands.CommandGroupImpl;
import org.irisa.triskell.MT.DataTypes.Java.commands.MultipleCommandException;
import org.irisa.triskell.MT.DataTypes.Java.commands.Type;
import org.irisa.triskell.MT.DataTypes.Java.commands.UnknownCommandException;
import org.irisa.triskell.MT.DataTypes.Java.commands.OclAny.OclAnyCommandGroup;
import org.irisa.triskell.MT.DataTypes.Java.commands.OclAny.OclAnyType;
import org.irisa.triskell.MT.utils.Java.AWK;

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
