package org.irisa.triskell.MT.repository.API.Java;

import java.lang.Exception;

public class CommonException 
    extends java.lang.Exception
{

    public CommonException(
        String message)
    {
        super(message);
    }

    public String getMessage()
    {
    	return super.getMessage();
    }
}
