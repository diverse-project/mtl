/*
 * $Id: TC_behavioral_test1.java,v 1.2 2003-08-27 09:34:09 dvojtise Exp $
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
 * Testcase for behavioral test1
 * simply compare the output with the expected_result.log file
 * 
 * @author dvojtise
 *
 */
public class TC_behavioral_test1 extends TC_generic_behavioral 
{
	public TC_behavioral_test1()
	{
		expectedLogFile = new File(Directories.getRootPath(this.getClass().getName()) + "\\..\\src\\tests\\behavioral_tests\\test1\\expected_result.log");
		testName = "TCtest1";
		
	}

	public void callTestMethod() 
	{       
		// call the Main of the behavorial_test1
		//	If you have a "Test1 cannot be resolved" error, this is because you need to generate the test1 example first 		
		test1.BMTLLib_aLibrary.main(null);  
	}	
	
}
