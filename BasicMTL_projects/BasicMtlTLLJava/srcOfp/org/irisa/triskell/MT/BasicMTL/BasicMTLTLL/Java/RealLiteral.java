package org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java;



/**
  * A real value.
  */
public class RealLiteral 
    extends org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.Literal
{
    public float value;
    public float getValue () {
        return this.value;
    }


    public RealLiteral(
        float theReal)
    {
		this.value = theReal;		
    }
}
