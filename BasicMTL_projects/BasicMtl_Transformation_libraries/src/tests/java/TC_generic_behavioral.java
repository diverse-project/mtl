/*
 * $Id: TC_generic_behavioral.java,v 1.1 2003-08-25 14:50:07 dvojtise Exp $
 * Created on 25 août 2003
 *
 * Generic Testcase for behavioral tests
 */
package tests.java;

import junit.framework.TestCase;
import java.io.*;
import java.*;
import org.irisa.triskell.MT.utils.Java.*;

/**
 * Testcase for behavioral test
 * simply compare the output with the expected_result.log file
 * 
 * @author dvojtise
 *
 */
public abstract class TC_generic_behavioral extends TestCase 
{
	public File expectedLogFile;
	public String testName;
	
	public TC_generic_behavioral()
	{
	
	}
	private TC_generic_behavioral(File anExpectedLogFile, String aTestName)
	{
		expectedLogFile = anExpectedLogFile;
		testName = aTestName;
	}
	public void test() throws Exception 
	{
		PrintStream stdOut;
		// redirect standard output to a file
		stdOut = System.out;
		try
		{
			File tempFile = File.createTempFile(testName, null);
			System.out.println(tempFile.getCanonicalPath());
			FileOutputStream tempOut = new FileOutputStream(tempFile);
			System.setOut(new PrintStream(tempOut));
			
        
			// call the Main of the behavorial_test1
			callTestMethod();  
	         
			tempOut.close();  
			System.setOut(stdOut) ;
			
			
			// compare the results with expected result file					
			//File expectedLogFile = new File("C:\\PROJET_MTL\\ECLIPSE\\workspace\\BasicMtl_Transformation_libraries\\src\\tests\\behavioral_tests\\test1\\expected_result.log");
			//System.out.println(Directories.getRootPath(this.getClass().getName()) + "\\..\\src\\tests\\behavioral_tests\\test1\\expected_result.log");
			//expectedLogFile = new File(Directories.getRootPath(this.getClass().getName()) + "\\..\\src\\tests\\behavioral_tests\\test1\\expected_result.log");
			

			// @TODO Improve the file comparison test (and separate in an utility class)
			// System.out.println(tempFile.length());
			assertEquals(tempFile.length(), expectedLogFile.length());
			
			FileInputStream tempIn = new FileInputStream(tempFile);
			FileInputStream expectedLog = new FileInputStream(expectedLogFile);
			// for each line must be equals
			while ((tempIn.available() != 0) && (expectedLog.available() != 0))
			{
				int tempInChar = tempIn.read();
				assertEquals(tempInChar, expectedLog.read());
				// System.out.print((char)tempInChar);	// print out last correct char
			}
			tempIn.close();
			expectedLog.close();
			
			tempFile.delete();
			tempFile.deleteOnExit(); 
		} 
		catch (Exception e)
		{
			throw e;			 
		}
	}	
	
	public abstract void callTestMethod(); 
	
}