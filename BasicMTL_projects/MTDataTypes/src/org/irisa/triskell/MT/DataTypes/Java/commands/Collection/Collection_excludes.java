/*
 * Created on May 27, 2003
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package org.irisa.triskell.MT.DataTypes.Java.commands.Collection;

import java.util.Collection;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

import org.irisa.triskell.MT.DataTypes.Java.CollectionValue;
import org.irisa.triskell.MT.DataTypes.Java.StringValue;
import org.irisa.triskell.MT.DataTypes.Java.Type;
import org.irisa.triskell.MT.DataTypes.Java.Value;
import org.irisa.triskell.MT.DataTypes.Java.commands.AbstractCommand;
import org.irisa.triskell.MT.DataTypes.Java.commands.Boolean.Boolean_not;
import org.irisa.triskell.MT.DataTypes.Java.commands.OclAny.OclAnyType;
import org.irisa.triskell.MT.DataTypes.Java.defaultImpl.BooleanValueImpl;
import org.irisa.triskell.MT.DataTypes.Java.defaultImpl.CollectionValueImpl;
import org.irisa.triskell.MT.DataTypes.Java.defaultImpl.SetValueImpl;
import org.irisa.triskell.MT.DataTypes.Java.defaultImpl.StringValueImpl;

/**
 * @author ffondeme
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class Collection_excludes extends AbstractCommand {
	public static final Collection_excludes TheInstance = new Collection_excludes();

	protected Collection_excludes() {
		super("excludes", new Type [] {OclAnyType.TheInstance}, null);
	}

	protected Value invokeInternal(Value invoker, Value[] arguments) {
		return Boolean_not.TheInstance.invoke(Collection_includes.TheInstance.invokeInternal(invoker, arguments), null);
	}
}
