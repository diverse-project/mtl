/*
 * Created on 20 mai 2003
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package org.irisa.triskell.MT.repository.ModFactDriver.Java.Test;

import junit.framework.TestCase;

/**
 * @author ffondeme
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class JUnitAdapter extends TestCase {

	/**
	 * Constructor for JUnitAdapter.
	 * @param arg0
	 */
	public JUnitAdapter(String arg0) {
		super(arg0);
	}

	public static void main(String[] args) {
		junit.textui.TestRunner.run(JUnitAdapter.class);
	}
	
	public void test () throws Exception {
		Test.entry();
		assertTrue(Test.getExceptions() == null || Test.getExceptions().size() == 0);
	}

}
