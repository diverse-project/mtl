/*
 * Created on Jun 2, 2003
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package org.irisa.triskell.MT.repository.MDRDriver.Java.Test;

import java.io.PrintWriter;

/**
 * @author ffondeme
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class CompositeThrowable extends Exception {
	public final Throwable [] exceptions;
	
	public CompositeThrowable (Throwable [] exceptions) {
		this.exceptions = exceptions;
	}
	
	public void printStackTrace(PrintWriter s) {
		for (int  i = 0; i < exceptions.length; ++i)
			this.exceptions[i].printStackTrace(s);
	}

}
