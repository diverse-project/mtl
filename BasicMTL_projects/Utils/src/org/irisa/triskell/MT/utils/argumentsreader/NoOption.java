/*
 * Created on 11 mai 2004
 *
 */
package org.irisa.triskell.MT.utils.argumentsreader;

/** This is a special option (with no name) that memorize the arguments that are not 
    involved with a known option.
 */
public class NoOption extends Option 
{
	/** */
	public NoOption (java.util.Vector args)
	{
		setName ("");
		setNbArgs (0);
		setHelp ("no option");
		setMultiple (true);
		setInclude ("");
		setExclude ("");
		setParameters (args);
	}

	/** */
	public void Proceed (java.util.Vector list) 
	{
		getParameters().addAll (list);
	}
}
