/*
 * Created on May 22, 2003
 * $Id: VoidCommandGroup.java,v 1.3 2004-02-16 17:02:24 dvojtise Exp $
 * @author : ffondeme
 * 
 * Copyright 2004 - INRIA - LGPL license
 */
package org.irisa.triskell.MT.DataTypes.Java.commands.Void;

import org.irisa.triskell.MT.DataTypes.Java.commands.CommandGroupImpl;
import org.irisa.triskell.MT.DataTypes.Java.commands.CommandGroup;

/**
 * @author ffondeme
 * Declares commands callable from a Void object
 */
public class VoidCommandGroup extends CommandGroupImpl {
	public static final CommandGroup TheInstance;
	
	static {
		TheInstance = new VoidCommandGroup();
	}
	
	private VoidCommandGroup() {
		super(VoidType.TheInstance, null);
	}
	

}