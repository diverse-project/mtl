package org.irisa.triskell.MT.DataTypes.Java.commands.OclType;

import org.irisa.triskell.MT.DataTypes.Java.Type;
import org.irisa.triskell.MT.DataTypes.Java.TypeValue;
import org.irisa.triskell.MT.DataTypes.Java.Value;
import org.irisa.triskell.MT.DataTypes.Java.commands.AbstractCommand;
import org.irisa.triskell.MT.DataTypes.Java.defaultImpl.SequenceValueImpl;
import org.irisa.triskell.MT.DataTypes.Java.defaultImpl.StringValueImpl;

public class OclType_qualifiedName 
    extends AbstractCommand
{
	public static final OclType_qualifiedName TheInstance = new OclType_qualifiedName();

    protected OclType_qualifiedName()
    {
        super("oclQualifiedName", new Type[] {}, null);
    }
    
    protected Value invokeInternal(Value invoker, Value[] arguments) {
    	String [] qn = ((TypeValue)invoker).getTheType().getQualifiedName();
    	Value [] qnv = new Value [qn.length];
    	for (int i = 0; i < qn.length; ++i)
    		qnv[i] = new StringValueImpl(false, null, qn[i]);
		return new SequenceValueImpl(false, null, qnv);
    }
}
