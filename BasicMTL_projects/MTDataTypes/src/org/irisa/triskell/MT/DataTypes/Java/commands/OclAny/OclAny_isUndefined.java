package org.irisa.triskell.MT.DataTypes.Java.commands.OclAny;

import org.irisa.triskell.MT.DataTypes.Java.commands.AbstractCommand;
import java.util.*;

import org.irisa.triskell.MT.DataTypes.Java.Type;
import org.irisa.triskell.MT.DataTypes.Java.Value;
import org.irisa.triskell.MT.DataTypes.Java.defaultImpl.BooleanValueImpl;


/**
 * Obviously, works for undefined values.
 */
public class OclAny_isUndefined 
    extends AbstractCommand
{
	public static final OclAny_isUndefined TheInstance = new OclAny_isUndefined();

    protected OclAny_isUndefined()
    {
        super("oclIsUndefined", new Type[] {}, null);
    }
    
	public Value invoke(Value invoker, Value[] arguments) {
		return this.invokeInternal(invoker, arguments);
	}
    
    protected Value invokeInternal(Value invoker, Value[] arguments)
    {
   		return invoker.isUndefined() ? BooleanValueImpl.TRUE : BooleanValueImpl.FALSE;
    }

}
