package org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java;



/**
  * An integer value.
  */
public class IntLiteral 
    extends org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.Literal
{
    public int value;
    public int getValue () {
        return this.value;
    }


    public IntLiteral(
        int theInt)
    {
		this.value = theInt;
    }
}
