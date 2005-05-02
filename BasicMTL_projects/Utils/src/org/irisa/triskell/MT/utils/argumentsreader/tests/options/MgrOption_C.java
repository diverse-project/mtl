/*
 * Created on 11 mai 2004
 *
 */
package org.irisa.triskell.MT.utils.argumentsreader.tests.options;

import java.util.Vector;

import org.irisa.triskell.MT.utils.argumentsreader.Option;

/**
 * @author edrezen
 *
 */
public class MgrOption_C extends Option 
{
	/** */
	public MgrOption_C ()
	{
		setName ("-C");
		setNbArgs (0);
		setInclude ("");
		setExclude ("-F,-X,-x");
		setHelp ("compile definitions files (*.def); generally used with the -O option in order to generate a compressed object model file");
		setMultiple (false);
	}
	
    /** */
    public void Proceed (Vector list) 
    {
    }
}
