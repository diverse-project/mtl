/*
 * $Id: TC_SimpleSamples_Vehicules.java,v 1.1 2003-08-27 09:34:09 dvojtise Exp $
 * Created on 25 août 2003
 *
 * Testcase for behavioral test1
 */
package tests.java;

import junit.framework.TestCase;
import java.io.*;
import java.*;
import org.irisa.triskell.MT.utils.Java.*;

/**
 * Testcase for SimpleSamples Vehicules 
 * simply compare the output with the expected_result.log file
 * 
 * @author dvojtise
 *
 */
public class TC_SimpleSamples_Vehicules extends TC_generic_behavioral 
{
	public TC_SimpleSamples_Vehicules()
	{
		expectedLogFile = new File(Directories.getRootPath(this.getClass().getName()) + "\\..\\src\\tests\\behavioral_tests\\test1\\expected_result.log");
		testName = "TCSimpleSamplesVehicules";
		
	}

	public void callTestMethod() 
	{       
		// call the Main of the behavorial_test1
		// If you have a "Testvehicules cannot be resolved" error, this is because you need to generate the SimpleSamples example first
		// TODO find a better organisation for tests and testvehicule, (move testvehicule to test ?) 
		//Testvehicules.BMTLLib_testVehicules.main(null);  
	}	
	
}