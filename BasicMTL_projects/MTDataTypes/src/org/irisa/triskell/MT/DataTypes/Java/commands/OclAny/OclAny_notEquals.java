package org.irisa.triskell.MT.DataTypes.Java.commands.OclAny;

import org.irisa.triskell.MT.DataTypes.Java.commands.AbstractCommand;
import org.irisa.triskell.MT.DataTypes.Java.commands.Boolean.Boolean_not;

import java.util.*;

import org.irisa.triskell.MT.DataTypes.Java.Type;
import org.irisa.triskell.MT.DataTypes.Java.Value;
import org.irisa.triskell.MT.DataTypes.Java.defaultImpl.BooleanValueImpl;

public class OclAny_notEquals 
    extends AbstractCommand
{
	public static final OclAny_notEquals TheInstance = new OclAny_notEquals();

    protected OclAny_notEquals()
    {
        super("<>", new Type[] {OclAnyType.TheInstance}, null);
    }
    
    protected Value invokeInternal(Value invoker, Value[] arguments) {
		return Boolean_not.TheInstance.invoke(OclAny_equals.TheInstance.invoke(invoker, arguments), null);
    }
}
