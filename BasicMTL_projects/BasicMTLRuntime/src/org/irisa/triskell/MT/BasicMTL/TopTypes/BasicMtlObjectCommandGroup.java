/*
 * Created on May 22, 2003
 * $Id: BasicMtlObjectCommandGroup.java,v 1.2 2004-02-17 08:58:21 dvojtise Exp $
 * Authors : ffondeme
 * 
 * Copyright 2004 - INRIA - LGPL license
 */
package org.irisa.triskell.MT.BasicMTL.TopTypes;

import org.irisa.triskell.MT.DataTypes.Java.commands.CommandGroupImpl;
//import org.irisa.triskell.MT.DataTypes.Java.commands.Command;
import org.irisa.triskell.MT.DataTypes.Java.commands.CommandGroup;
import org.irisa.triskell.MT.DataTypes.Java.commands.OclAny.OclAnyCommandGroup;
//import org.irisa.triskell.MT.DataTypes.Java.Type;
//import org.irisa.triskell.MT.DataTypes.Java.Value;

//import java.util.List;
import java.util.Arrays;

/**
 * @author ffondeme
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class BasicMtlObjectCommandGroup extends CommandGroupImpl {
	public static final CommandGroup TheInstance;
	
	static {
		TheInstance = new BasicMtlObjectCommandGroup();
	}
	
	private BasicMtlObjectCommandGroup() {
		super(BasicMtlObjectType.TheInstance, Arrays.asList((new CommandGroup [] {OclAnyCommandGroup.TheInstance})));
	}
	

}