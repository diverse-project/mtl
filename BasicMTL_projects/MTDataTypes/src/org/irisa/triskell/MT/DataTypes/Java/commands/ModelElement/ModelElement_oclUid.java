package org.irisa.triskell.MT.DataTypes.Java.commands.ModelElement;

import org.irisa.triskell.MT.DataTypes.Java.ModelElementValue;
import org.irisa.triskell.MT.DataTypes.Java.Type;
import org.irisa.triskell.MT.DataTypes.Java.TypeValue;
import org.irisa.triskell.MT.DataTypes.Java.Value;
import org.irisa.triskell.MT.DataTypes.Java.commands.AbstractCommand;
import org.irisa.triskell.MT.DataTypes.Java.defaultImpl.BooleanValueImpl;
import org.irisa.triskell.MT.DataTypes.Java.defaultImpl.StringValueImpl;
import org.irisa.triskell.MT.DataTypes.Java.defaultImpl.UndefinedValueImpl;
import org.irisa.triskell.MT.DataTypes.Java.defaultImpl.VoidValueImpl;

public class ModelElement_oclUid 
    extends AbstractCommand
{
	public static final ModelElement_oclUid TheInstance = new ModelElement_oclUid();

    protected ModelElement_oclUid()
    {
        super("oclUid", new Type[] {}, null);
    }
    
    protected Value invokeInternal(Value invoker, Value[] arguments) {
    	return new StringValueImpl(false, null, ((ModelElementValue)invoker).getTheModelElement());
    }
}
