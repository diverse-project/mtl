package org.irisa.triskell.MT.DataTypes.Java.commands.OclAny;

import org.irisa.triskell.MT.DataTypes.Java.commands.AbstractCommand;
import org.irisa.triskell.MT.DataTypes.Java.commands.Type;
import java.util.*;
import org.irisa.triskell.MT.DataTypes.Java.Value;
import org.irisa.triskell.MT.DataTypes.Java.defaultImpl.BooleanValueImpl;
import java.lang.Exception;

public class OclAny_equals 
    extends AbstractCommand
{
	public static OclAny_equals TheInstance = new OclAny_equals();

    private OclAny_equals()
    {
        super("=", new Type[] {OclAnyType.TheInstance}, null);
    }
    
    public org.irisa.triskell.MT.DataTypes.Java.Value invoke(
        org.irisa.triskell.MT.DataTypes.Java.Value invoker,
        org.irisa.triskell.MT.DataTypes.Java.Value[] arguments)
    {
   		String errorMessage;
    	if (invoker.isUndefined())
			errorMessage = invoker.getErrorMessage();
		else if (arguments[0].isUndefined())
			errorMessage = arguments[0].getErrorMessage();
		else
			errorMessage = null;
		boolean ans = invoker.equals(arguments[0]);
		if (errorMessage != null)
			return new BooleanValueImpl(true, errorMessage, ans);
		else
			return ans ? BooleanValueImpl.TRUE : BooleanValueImpl.FALSE;
    }
}
