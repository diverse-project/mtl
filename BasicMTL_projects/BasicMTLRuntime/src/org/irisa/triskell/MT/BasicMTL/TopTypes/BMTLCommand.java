/*
 * Created on 18 juin 2003
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package org.irisa.triskell.MT.BasicMTL.TopTypes;

import java.util.Arrays;
import java.util.Set;

import org.irisa.triskell.MT.DataTypes.Java.Value;
import org.irisa.triskell.MT.DataTypes.Java.commands.Command;

public abstract class BMTLCommand implements Command {
	protected final String name;
	protected final Class[] parameters;
	protected final Set discriminants;
	
	public BMTLCommand (String name, Class[] parameters, Set discriminants) {
		this.name = name;
		this.parameters = parameters;
		this.discriminants = discriminants;
	}

	public String getName() {
		return this.name;//this.getName();
	}

	public boolean isInvokable(Value[] arguments, String[] discriminants) {
		return this.checkAll(arguments, discriminants);
	}
	
	protected boolean checkAll (Value [] arguments, String[] discriminants) {
		return (this.checkDiscriminants(arguments, discriminants)) && (this.checkArguments(arguments, discriminants));
	}
	
	protected boolean checkArguments (Value [] arguments, String[] discriminants) {
		if (this.parameters == null || this.parameters.length == 0)
			return arguments == null || arguments.length == 0;
		if (this.parameters.length != arguments.length)
			return false;
		for (int i = 0; i < this.parameters.length; ++i)
			if (! this.parameters[i].isInstance(arguments[i]))
				return false;
		return true;
	}
	
	protected boolean checkDiscriminants (Value [] arguments, String[] discriminants) {
		return this.discriminants.containsAll(Arrays.asList(discriminants));
	}
	
	public abstract Value invoke(Value invoker, Value[] arguments);

}
