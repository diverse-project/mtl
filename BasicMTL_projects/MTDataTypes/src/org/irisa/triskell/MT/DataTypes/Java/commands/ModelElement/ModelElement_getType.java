/*
 * Created on 3 déc. 2003
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package org.irisa.triskell.MT.DataTypes.Java.commands.ModelElement;

import org.irisa.triskell.MT.DataTypes.Java.ModelElementValue;
import org.irisa.triskell.MT.DataTypes.Java.Type;
import org.irisa.triskell.MT.DataTypes.Java.TypeValue;
import org.irisa.triskell.MT.DataTypes.Java.Value;
import org.irisa.triskell.MT.DataTypes.Java.commands.AbstractCommand;
import org.irisa.triskell.MT.DataTypes.Java.defaultImpl.BooleanValueImpl;
import org.irisa.triskell.MT.DataTypes.Java.defaultImpl.StringValueImpl;
import org.irisa.triskell.MT.DataTypes.Java.defaultImpl.TypeValueImpl;
import org.irisa.triskell.MT.DataTypes.Java.defaultImpl.UndefinedValueImpl;
import org.irisa.triskell.MT.DataTypes.Java.defaultImpl.VoidValueImpl;

/**
 * @author edrezen
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class ModelElement_getType extends AbstractCommand
{
	public static final ModelElement_getType TheInstance = new ModelElement_getType();

	protected ModelElement_getType  ()
	{
		super("getType", new Type[] {}, null);
	}
    
	protected Value invokeInternal (Value invoker, Value[] arguments) 
	{
		return new TypeValueImpl (false, null, ((ModelElementValue)invoker).getType());
	}

}
