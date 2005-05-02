/*
 * Created on 11 mai 2004
 *
 */
package org.irisa.triskell.MT.utils.argumentsreader.tests;

import java.util.Iterator;
import java.util.Vector;

import org.irisa.triskell.MT.utils.argumentsreader.CheckOption;
import org.irisa.triskell.MT.utils.argumentsreader.NoOption;
import org.irisa.triskell.MT.utils.argumentsreader.Option;
import org.irisa.triskell.MT.utils.argumentsreader.tests.options.MgrOption_C;
import org.irisa.triskell.MT.utils.argumentsreader.tests.options.MgrOption_F;
import org.irisa.triskell.MT.utils.argumentsreader.tests.options.MgrOption_x;


/**
 * @author edrezen
 *
 * */
public class Test {

    public static void main(String[] args) 
    {
		// We build the object that will check the arguments with the wanted options.
		CheckOption check = new CheckOption (
			new Option[] {
				new NoOption (new Vector()),
				new MgrOption_C (),
				new MgrOption_F (),
				new MgrOption_x (new Vector())
			} 
		);

		// Then, we perform the check.
		int nb_errors = check.Proceed (args);

		// We display eventual errors and warnings.
		check.DisplayErrors   (System.out);
		check.DisplayWarnings (System.out);

		if (check.Saw ("-C"))
		{
			System.out.println ("option -C was seen...");
		}

		if (check.Saw ("-F"))
		{
			System.out.println ("option -F was seen...");
		}

		if (check.Saw ("-x"))
		{
			System.out.println ("option -x was seen with arguments : ");
			Iterator it = check.getOption("-x").getParameters().iterator();			
			while (it.hasNext())
			{
				System.out.println ("\t" + it.next());
			}
		}
		
		System.out.println ("The arguments not linked to an option are : ");
		Iterator it = check.getOption("").getParameters().iterator();			
		while (it.hasNext())
		{
			System.out.println ("\t" + it.next());
		}
		
		// we display the help
		check.DisplayHelp (System.out);		
	}
}
