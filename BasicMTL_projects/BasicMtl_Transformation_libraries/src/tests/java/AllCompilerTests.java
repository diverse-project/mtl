/*
 * $Id: AllCompilerTests.java,v 1.1 2003-08-25 14:50:07 dvojtise Exp $
 * Created on 25.08.2003
 *
 * Test suite for the BasicMTLCompiler
 */
package tests.java;

import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * Test suite for the BasicMTLCompiler
 * @author dvojtise
 *
 */
public class AllCompilerTests 
{
	
	public static Test suite() {
		TestSuite suite =
			new TestSuite("Test for the BMTL compiler");
		//$JUnit-BEGIN$
		suite.addTest(new TestSuite(TC_behavioral_test1.class));
		//suite.addTest(new TestSuite(SetValueImplTest.class));
		//$JUnit-END$
		return suite;
	}
}