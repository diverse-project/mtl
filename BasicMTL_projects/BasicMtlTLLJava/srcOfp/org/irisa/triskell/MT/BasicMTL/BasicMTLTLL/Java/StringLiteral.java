package org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java;



/**
  * A string value.
  */
public class StringLiteral 
    extends org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.Literal
{
    public String value;
    public String getValue () {
        return this.value;
    }


    public StringLiteral(
        String theString)
    {
		this.value = theString;
    }
}
