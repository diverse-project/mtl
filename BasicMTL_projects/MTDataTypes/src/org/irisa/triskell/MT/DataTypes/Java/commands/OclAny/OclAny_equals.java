package org.irisa.triskell.MT.DataTypes.Java.commands.OclAny;

import org.irisa.triskell.MT.DataTypes.Java.Type;
import org.irisa.triskell.MT.DataTypes.Java.Value;
import org.irisa.triskell.MT.DataTypes.Java.commands.AbstractCommand;
import org.irisa.triskell.MT.DataTypes.Java.defaultImpl.BooleanValueImpl;

public class OclAny_equals 
    extends AbstractCommand
{
	public static final OclAny_equals TheInstance = new OclAny_equals();

    protected OclAny_equals()
    {
        super("=", new Type[] {OclAnyType.TheInstance}, null);
    }
    
    protected Value invokeInternal(Value invoker, Value[] arguments) {
		return invoker.equals(arguments[0]) ? BooleanValueImpl.TRUE : BooleanValueImpl.FALSE;
    }
}
