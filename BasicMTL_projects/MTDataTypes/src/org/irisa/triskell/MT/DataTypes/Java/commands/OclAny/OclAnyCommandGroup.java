/*
 * $Id: OclAnyCommandGroup.java,v 1.4 2004-02-16 17:01:54 dvojtise Exp $
 * @author : ffondeme
 * 
 * Copyright 2004 - INRIA - LGPL license
 */
package org.irisa.triskell.MT.DataTypes.Java.commands.OclAny;

import org.irisa.triskell.MT.DataTypes.Java.commands.CommandGroupImpl;
import org.irisa.triskell.MT.DataTypes.Java.commands.CommandGroup;

/**
 * @author ffondeme
 * Definition of the commands callable from OclAny objects
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
		TheInstance.addCommand(OclAny_toOut.TheInstance);
		TheInstance.addCommand(OclAny_toErr.TheInstance);
	}
	
	private OclAnyCommandGroup() {
		super(OclAnyType.TheInstance, null);
	}
	

}