package org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java;



/**
  * A string value.
  */
public class JavaCodeLiteral 
    extends org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.Literal
{
    public String value;
    public String getValue () {
        return this.value;
    }


    public JavaCodeLiteral(
        String theCodeString)
    {
		this.value = theCodeString;
    }
}
