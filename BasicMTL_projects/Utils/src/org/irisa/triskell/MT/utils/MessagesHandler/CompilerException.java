/*
 * $Id: CompilerException.java,v 1.1 2004-06-22 15:05:10 dvojtise Exp $
 * Authors : dvojtise
 *
 * Created on 22 juin 2004
 * Copyright 2004 - INRIA - LGPL license
 */ 
package org.irisa.triskell.MT.utils.MessagesHandler;

/**
 * @author dvojtise
 * specific exception raised by the compiler when it has an error
 */
public class CompilerException extends Exception 
{
	CompilerException(String arg)
	{
		super(arg);
	}
}
