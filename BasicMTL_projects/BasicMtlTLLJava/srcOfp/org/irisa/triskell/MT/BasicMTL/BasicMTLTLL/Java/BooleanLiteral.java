package org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java;



/**
  * A boolean value.
  */
public class BooleanLiteral 
    extends org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.Literal
{
    public boolean value;
    public boolean getValue () {
        return this.value;
    }


    public BooleanLiteral(
        boolean theBoolean)
    {
		this.value = theBoolean;
    }
}
