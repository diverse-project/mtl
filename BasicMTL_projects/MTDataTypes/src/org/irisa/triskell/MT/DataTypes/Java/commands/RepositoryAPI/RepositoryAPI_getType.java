/*
 * Created on 3 déc. 2003
 * $Id: RepositoryAPI_getType.java,v 1.1 2004-10-28 15:10:11 jpthibau Exp $
 * @author : edrezen 
 * 
 * Copyright 2004 - INRIA - LGPL license
 */
package org.irisa.triskell.MT.DataTypes.Java.commands.RepositoryAPI;

import org.irisa.triskell.MT.DataTypes.Java.RepositoryAPIValue;
import org.irisa.triskell.MT.DataTypes.Java.Type;
import org.irisa.triskell.MT.DataTypes.Java.Value;
import org.irisa.triskell.MT.DataTypes.Java.commands.AbstractCommand;
import org.irisa.triskell.MT.DataTypes.Java.defaultImpl.TypeValueImpl;

/**
 * @author edrezen
 * Action to do when receiving a getType command
 */
public class RepositoryAPI_getType extends AbstractCommand
{
	public static final RepositoryAPI_getType TheInstance = new RepositoryAPI_getType();

	protected RepositoryAPI_getType  ()
	{
		super("getType", new Type[] {}, null);
	}
    
	protected Value invokeInternal (Value invoker, Value[] arguments) 
	{
		return new TypeValueImpl (false, null, ((RepositoryAPIValue)invoker).getType());
	}

}
