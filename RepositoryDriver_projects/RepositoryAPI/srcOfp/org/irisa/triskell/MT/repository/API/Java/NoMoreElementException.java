package org.irisa.triskell.MT.repository.API.Java;

import java.lang.Exception;

public class NoMoreElementException 
    extends java.lang.Exception
{

    public NoMoreElementException()
    {
        super("No more elements in the collection.");
    }
}
