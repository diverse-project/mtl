/*
 * $Id: RepositoryAPI_oclUid.java,v 1.1 2004-10-28 15:10:11 jpthibau Exp $
 * @author : ffondemen 
 * 
 * Copyright 2004 - INRIA - LGPL license
 */
package org.irisa.triskell.MT.DataTypes.Java.commands.RepositoryAPI;

import org.irisa.triskell.MT.DataTypes.Java.RepositoryAPIValue;
import org.irisa.triskell.MT.DataTypes.Java.Type;
import org.irisa.triskell.MT.DataTypes.Java.Value;
import org.irisa.triskell.MT.DataTypes.Java.commands.AbstractCommand;
import org.irisa.triskell.MT.DataTypes.Java.defaultImpl.StringValueImpl;

/**
 * 
 * Action to don when receiving a oclUid command
 */
public class RepositoryAPI_oclUid 
    extends AbstractCommand
{
	public static final RepositoryAPI_oclUid TheInstance = new RepositoryAPI_oclUid();

    protected RepositoryAPI_oclUid()
    {
        super("oclUid", new Type[] {}, null);
    }
    
    protected Value invokeInternal(Value invoker, Value[] arguments) {
    	return new StringValueImpl(false, null, ((RepositoryAPIValue)invoker).getTheRepositoryAPI());
    }
}
