/*
 * Created on 11 mai 2004
 *
 */
package org.irisa.triskell.MT.utils.argumentsreader.tests.options;

import java.util.Vector;

import org.irisa.triskell.MT.utils.argumentsreader.Option;

/**
*/
public class MgrOption_x extends Option 
{
	/** */
	public MgrOption_x (Vector args)
	{
		setName ("-x");
		setNbArgs (2);
		setInclude ("");
		setExclude ("-C -X");
		setHelp ("command_file : check the syntax of a command file.");
		setMultiple (true);
		setParameters (args);
	}
	
    /** */
    public void Proceed (Vector list) 
    {
		getParameters().addAll (list);
    }
}
