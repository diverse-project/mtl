/*
 * Created on May 22, 2003
 * $Id: BooleanCommandGroup.java,v 1.4 2004-02-16 17:02:19 dvojtise Exp $
 * @author : ffondeme 
 * 
 * Copyright 2004 - INRIA - LGPL license
 */
package org.irisa.triskell.MT.DataTypes.Java.commands.Boolean;

import java.util.Arrays;

//import org.irisa.triskell.MT.DataTypes.Java.BooleanValue;
//import org.irisa.triskell.MT.DataTypes.Java.Type;
//import org.irisa.triskell.MT.DataTypes.Java.Value;
import org.irisa.triskell.MT.DataTypes.Java.commands.CommandGroup;
import org.irisa.triskell.MT.DataTypes.Java.commands.CommandGroupImpl;
import org.irisa.triskell.MT.DataTypes.Java.commands.OclAny.OclAnyCommandGroup;

/**
 * Declaration of commands callable on Boolean
 */
public class BooleanCommandGroup extends CommandGroupImpl {
	public static final CommandGroup TheInstance;
	
	static {
		TheInstance = new BooleanCommandGroup();
		TheInstance.addCommand(Boolean_not.TheInstance);
		TheInstance.addCommand(Boolean_or.TheInstance);
		TheInstance.addCommand(Boolean_xor.TheInstance);
		TheInstance.addCommand(Boolean_and.TheInstance);
		TheInstance.addCommand(Boolean_implies.TheInstance);
	}
	
	private BooleanCommandGroup() {
		super(BooleanType.TheInstance, Arrays.asList(new CommandGroup [] {OclAnyCommandGroup.TheInstance}));
	}

}