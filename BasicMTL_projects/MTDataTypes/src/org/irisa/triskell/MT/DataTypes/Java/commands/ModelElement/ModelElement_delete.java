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

public class ModelElement_delete 
    extends AbstractCommand
{
	public static final ModelElement_delete TheInstance = new ModelElement_delete();

    protected ModelElement_delete()
    {
        super("delete", new Type[] {}, null);
    }
    
    protected Value invokeInternal(Value invoker, Value[] arguments) {
    	try {
    		((ModelElementValue)invoker).deleteTheModelElement();
			return VoidValueImpl.getTheInstance();
    	} catch (Exception x) {
    		return new UndefinedValueImpl(x.getMessage());
    	}
    }
}
