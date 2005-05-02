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
public class MgrOption_F extends Option 
{
	/** */
	public MgrOption_F ()
	{
		setName ("-F");
		setNbArgs (0);
		setInclude ("");
		setExclude ("-C");
		setHelp ("allow to run mgr with a model object given in a compressed file (like the dbase.mgr).");
		setMultiple (false);
	}
	
    /** */
    public void Proceed (Vector list) 
    {
    }
}
