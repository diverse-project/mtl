/*
 * $Id: OclTypeCommandGroup.java,v 1.2 2004-02-16 17:02:08 dvojtise Exp $
 * @author : ffondeme
 * 
 * Copyright 2004 - INRIA - LGPL license
 */
package org.irisa.triskell.MT.DataTypes.Java.commands.OclType;

import java.util.Arrays;

import org.irisa.triskell.MT.DataTypes.Java.commands.CommandGroup;
import org.irisa.triskell.MT.DataTypes.Java.commands.CommandGroupImpl;
import org.irisa.triskell.MT.DataTypes.Java.commands.OclAny.OclAnyCommandGroup;

/**
 * @author ffondeme
 *
 * Declaration of commands callable from a oclType object
 */
public class OclTypeCommandGroup extends CommandGroupImpl {
	public static final CommandGroup TheInstance;
	
	static {
		TheInstance = new OclTypeCommandGroup();
		TheInstance.addCommand(OclType_allInstances.TheInstance);
		TheInstance.addCommand(OclType_name.TheInstance);
		TheInstance.addCommand(OclType_qualifiedName.TheInstance);
	}
	
	private OclTypeCommandGroup() {
		super(OclTypeType.TheInstance, Arrays.asList(new CommandGroup [] {OclAnyCommandGroup.TheInstance}));
	}

}