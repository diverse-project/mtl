/*
 * $Id $
 * Created on 27 août 2003
 *
 * Generic Testcase for behavioral test2
 */
package tests.java;

import junit.framework.TestCase;
import java.io.*;
import java.*;
import org.irisa.triskell.MT.utils.Java.*;

/**
 * Testcase for behavioral test2
 * currently simply checks that there is no exceptions
 * @TODO implement a file comparison on the output xml files 
 *
 * @author dvojtise
 *
 */
public class TC_behavioral_test2 extends TestCase 
{
	
	public TC_behavioral_test2()
	{
	
	}
	
	/**
	 * main test methods
	 * @throws Exception
	 */
	public void test()  
	{
		//		call the Main of the behavorial_test2
		//	If you have a "Test cannot be resolved" error, this is because you need to generate the test1 example first 		
		//test2.BMTLLib_Test2LibraryA.main(null); 
		assertTrue(true);
	}	
	
	
}